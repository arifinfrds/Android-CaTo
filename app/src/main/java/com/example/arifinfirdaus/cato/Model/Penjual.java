package com.example.arifinfirdaus.cato.Model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by arifinfirdaus on 4/7/17.
 */

public class Penjual extends BaseUser {

    private String dekripsi;
    private String alamat;
    private String kontak;
    private String jamKerja;

    private LatLng latLng;
    private ArrayList<Barang> barangs;

    public Penjual(String uid, String nama, String email, String tipeUser, String dekripsi,
                   String alamat, String kontak, String jamKerja, LatLng latLng) {
        super(uid, nama, email, tipeUser);
        this.dekripsi = dekripsi;
        this.alamat = alamat;
        this.kontak = kontak;
        this.jamKerja = jamKerja;
        this.latLng = latLng;
    }

    public Penjual(String uid, String nama, String email, String tipeUser, String dekripsi,
                   String alamat, String kontak, String jamKerja, LatLng latLng, ArrayList<Barang> barangs) {
        super(uid, nama, email, tipeUser);
        this.dekripsi = dekripsi;
        this.alamat = alamat;
        this.kontak = kontak;
        this.jamKerja = jamKerja;
        this.latLng = latLng;
        this.barangs = barangs;
    }


    @Override
    public String getUid() {
        return super.getUid();
    }

    @Override
    protected String getNama() {
        return super.getNama();
    }

    @Override
    protected String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getTipeUser() {
        return super.getTipeUser();
    }

    public String getDekripsi() {
        return dekripsi;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getKontak() {
        return kontak;
    }

    public String getJamKerja() {
        return jamKerja;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public ArrayList<Barang> getBarangs() {
        return barangs;
    }

    public void setBarangs(ArrayList<Barang> barangs) {
        this.barangs = barangs;
    }
}
