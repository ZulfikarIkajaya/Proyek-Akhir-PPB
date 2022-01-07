package com.example.StockIn;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText nmBrg, hJual, hBeli, stok;
    private Button btn_simpan, btn_lihat, btn_tmb, btn_krg;
    private Databases46 dbHelp;
    private Barang brg;
    private Integer bantu=0, a=0, b=0;
    Intent edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelp=new Databases46(this);

        btn_simpan=findViewById(R.id.btnSimpan);
        btn_lihat=findViewById(R.id.btnLihat);
        nmBrg=findViewById(R.id.txt_namaBrg);
        hBeli=findViewById(R.id.txt_HargaBeli);
        hJual=findViewById(R.id.txt_HargaJual);
        btn_tmb=findViewById(R.id.btnTambah);
        btn_krg=findViewById(R.id.btnKurang);
        stok=findViewById(R.id.txt_stok);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit==null)
                    simpanData();
                else editData();
            }
        });

        btn_lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit=null;
                Intent goTampil=new Intent(MainActivity.this, katalog.class);
                startActivityForResult(goTampil, 1);
                dbHelp.close();
            }
        });

        btn_tmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stok.getText().toString().equals("")){
                    stok.setText("0");
                }
                else{
                    a=Integer.parseInt(stok.getText().toString());
                    if(a>=0)
                        a=a+1;
                    else
                        b=0;
                    stok.setText(String.valueOf(a));
                }
            }
        });
        btn_krg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stok.getText().toString().equals("")){
                    stok.setText("0");
                }
                else{
                    b=Integer.parseInt(stok.getText().toString());
                    if(b>0)
                        b=b-1;
                    else
                        b=0;
                    stok.setText(String.valueOf(b));
                }
            }
        });
    }

    private void simpanData(){
        brg=new Barang(
                nmBrg.getText().toString(),
                hBeli.getText().toString(),
                hJual.getText().toString(),
                Integer.parseInt(stok.getText().toString())
        );
        dbHelp.insertData(dbHelp.getWritableDatabase(), brg);
        Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
        clcs();
    }

    private void editData(){
        brg=new Barang(
                nmBrg.getText().toString(),
                hBeli.getText().toString(),
                hJual.getText().toString(),
                Integer.parseInt(stok.getText().toString())
        );
        dbHelp.editData(dbHelp.getWritableDatabase(), brg, edit.getStringExtra("namaBrg"));
        Toast.makeText(this, "Data berhasil diedit !", Toast.LENGTH_SHORT).show();
        clcs();
    }

    private void clcs(){
        nmBrg.setText("");
        hJual.setText("");
        hBeli.setText("");
        stok.setText("");
        edit=null;
        nmBrg.requestFocus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            edit=data;
            nmBrg.setText(data.getStringExtra("namaBrg"));
            hBeli.setText(data.getStringExtra("hrgBeli"));
            hJual.setText(data.getStringExtra("hrgJual"));
            stok.setText(String.valueOf(data.getIntExtra("stok", 0)));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.title){
            startActivity(new Intent(MainActivity.this, about.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
