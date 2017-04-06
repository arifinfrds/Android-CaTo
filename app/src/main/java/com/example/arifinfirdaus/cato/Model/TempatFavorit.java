package com.example.arifinfirdaus.cato.Model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by arifinfirdaus on 4/7/17.
 */

public class TempatFavorit {

    private String nama;
    private String alamat;
    private LatLng latLng;
    // private uri foto;


    public TempatFavorit(String nama, String alamat, LatLng latLng) {
        this.nama = nama;
        this.alamat = alamat;
        this.latLng = latLng;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
