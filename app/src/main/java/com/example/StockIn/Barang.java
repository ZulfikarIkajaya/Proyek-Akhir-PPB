package com.example.StockIn;

public class Barang {
    private String namaBrg, beli, jual;
    private int stok;

    public Barang(String namaBrg, String beli, String jual, int stok) {
        this.namaBrg = namaBrg;
        this.beli = beli;
        this.jual = jual;
        this.stok = stok;
    }

    public String getNamaBrg() {
        return namaBrg;
    }

    public String getBeli() {
        return beli;
    }

    public String getJual() {
        return jual;
    }

    public int getStok() {
        return stok;
    }
}
