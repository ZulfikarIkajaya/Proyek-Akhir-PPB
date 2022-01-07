package com.example.StockIn;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class katalog extends AppCompatActivity {
    private Databases46 dbHelp;
    private Button btn_edit, btn_hapus;
    private TableLayout tb_bgr;
    private TableRow trBrg;
    private TextView tv1, tv2, tv3, tv4;
    private ArrayList<Barang> barangArrayList=new ArrayList<>();
    private String namaBrg, hBeli, hJual;
    private Integer stok;
    Intent intend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Katalog Barang");

        dbHelp=new Databases46(this);
        btn_edit=findViewById(R.id.btnEdit);
        btn_hapus=findViewById(R.id.btnhapus);
        tb_bgr=findViewById(R.id.tbl_brg);
        tampilData();

        btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelp.hapusData(dbHelp.getWritableDatabase(), namaBrg);
                tampilData();
                aksiHapus();
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goEdit=new Intent();
                goEdit.putExtra("namaBrg", namaBrg);
                goEdit.putExtra("hrgBeli", hBeli);
                goEdit.putExtra("hrgJual", hJual);
                goEdit.putExtra("stok", stok);
                dbHelp.close();
                setResult(1, goEdit);
                finish();
            }
        });

    }
    public void aksiHapus(){
        Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
    }
    public void tampilData(){
        tb_bgr.removeAllViews();
        barangArrayList=dbHelp.getBarangArrayList(dbHelp.getWritableDatabase());

        trBrg=new TableRow(this);
        tv1=new TextView(this);
        tv2=new TextView(this);
        tv3=new TextView(this);
        tv4=new TextView(this);

        tv1.setText("Nama Barang");
        tv2.setText("Modal");
        tv3.setText("H.Jual");
        tv4.setText("Stok");

        tv1.setTextColor(Color.RED);
        tv2.setTextColor(Color.RED);
        tv3.setTextColor(Color.RED);
        tv4.setTextColor(Color.DKGRAY);

        tv1.setWidth(500);
        tv2.setWidth(210);
        tv3.setWidth(210);
        tv4.setWidth(100);

        trBrg.addView(tv1);
        trBrg.addView(tv2);
        trBrg.addView(tv3);
        trBrg.addView(tv4);

        tb_bgr.addView(trBrg);

        for (final Barang brg : barangArrayList){
            trBrg=new TableRow(this);
            tv1=new TextView(this);
            tv2=new TextView(this);
            tv3=new TextView(this);
            tv4=new TextView(this);

            tv1.setText(brg.getNamaBrg());
            tv2.setText("Rp. "+brg.getBeli());
            tv3.setText("Rp. "+brg.getJual());
            tv4.setText(String.valueOf(brg.getStok()));

            tv1.setWidth(500);
            tv2.setWidth(210);
            tv3.setWidth(210);
            tv4.setWidth(100);

            trBrg.addView(tv1);
            trBrg.addView(tv2);
            trBrg.addView(tv3);
            trBrg.addView(tv4);

            tb_bgr.addView(trBrg);

            trBrg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i=0; i<tb_bgr.getChildCount(); i++){
                        namaBrg=brg.getNamaBrg();
                        hBeli=brg.getBeli();
                        hJual=brg.getJual();
                        stok=brg.getStok();
                        if(tb_bgr.getChildAt(i)==v)
                            tb_bgr.getChildAt(i).setBackgroundColor(Color.LTGRAY);
                        else
                            tb_bgr.getChildAt(i).setBackgroundColor(Color.WHITE);
                    }
                }
            });
        }
    }
}
