package com.example.arifinfirdaus.cato.Model;

import java.util.ArrayList;

/**
 * Created by arifinfirdaus on 4/7/17.
 */

public class Pembeli extends BaseUser {

    // private TempatFavorit[] tempatFavorits;
    private ArrayList<TempatFavorit> tempatFavorits;


    public Pembeli(String uid, String nama, String email, String tipeUser, ArrayList<TempatFavorit> tempatFavorits) {
        super(uid, nama, email, tipeUser);
        this.tempatFavorits = tempatFavorits;
    }

    @Override
    public String getUid() {
        return super.getUid();
    }

    @Override
    public String getNama() {
        return super.getNama();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getTipeUser() {
        return super.getTipeUser();
    }

    public ArrayList<TempatFavorit> getTempatFavorits() {
        return tempatFavorits;
    }
}
