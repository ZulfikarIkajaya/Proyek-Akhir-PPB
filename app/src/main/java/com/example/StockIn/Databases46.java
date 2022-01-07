package com.example.StockIn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Databases46 extends SQLiteOpenHelper {

    ArrayList<Barang> barangArrayList=new ArrayList<>();

    public Databases46(Context contex){
        super(contex, "DB Barang", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table tb_barang(namaBrg text(60) primary key, hrgBeli text(10), hrgJual text(10), stok text(10))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(SQLiteDatabase db, Barang brg){
        ContentValues cv=new ContentValues();
        cv.put("namaBrg", brg.getNamaBrg());
        cv.put("hrgBeli", brg.getBeli());
        cv.put("hrgJual", brg.getJual());
        cv.put("stok", brg.getStok());
        db.insert("tb_barang", null, cv);
    }

    public void hapusData(SQLiteDatabase db, String nama){
        db.delete("tb_barang", "namaBrg=?", new String[]{nama});
    }

    public void editData(SQLiteDatabase db, Barang brg, String nama){
        ContentValues cv=new ContentValues();
        cv.put("namaBrg", brg.getNamaBrg());
        cv.put("hrgBeli", brg.getBeli());
        cv.put("hrgJual", brg.getJual());
        cv.put("stok", brg.getStok());
        db.update("tb_barang", cv, "namaBrg=?", new String[]{nama});
    }

    public ArrayList<Barang> getBarangArrayList(SQLiteDatabase db){
        barangArrayList.clear();
        Cursor cursor=db.rawQuery("select * from tb_barang", null);
        if(cursor.getCount()>0){
           cursor.moveToFirst();
           do{
               barangArrayList.add(new Barang(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
           }while (cursor.moveToNext());
        }
        return barangArrayList;
    }
}
