package com.example.arifinfirdaus.cato.Model;

import android.net.Uri;

/**
 * Created by arifinfirdaus on 4/7/17.
 */


public class BaseUser {

    private String uid;
    private String nama;
    private String email;
    private String tipeUser; // pembeli || penjual
    // private Uri fotoProfil;

    public BaseUser(String uid, String nama, String email, String tipeUser) {
        this.uid = uid;
        this.nama = nama;
        this.email = email;
        this.tipeUser = tipeUser;
    }

    protected String getUid() {
        return uid;
    }

    protected String getNama() {
        return nama;
    }

    protected String getEmail() {
        return email;
    }

    protected String getTipeUser() {
        return tipeUser;
    }
}
