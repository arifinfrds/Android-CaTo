package com.example.arifinfirdaus.cato;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import com.example.arifinfirdaus.cato.Model.BaseUser;
import com.example.arifinfirdaus.cato.Model.Pembeli;
import com.example.arifinfirdaus.cato.Model.Penjual;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PenjualPembeliActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPenjual;
    private Button btnPembeli;

    private String tipeUser;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjual_pembeli);

        btnPenjual = (Button) findViewById(R.id.btn_penjual);
        btnPembeli = (Button) findViewById(R.id.btn_pembeli);

        btnPenjual.setOnClickListener(this);
        btnPembeli.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

    }


    @Override
    protected void onResume() {
        super.onResume();
        cekUserType();
    }

    private void cekUserType() {
        showProgressDialog();
        // cek user db ada atau tidak
        DatabaseReference userRef = databaseReference.child("user");
        userRef.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    hideProgressDialog();
                    Toast.makeText(
                            PenjualPembeliActivity.this,
                            "database user belum ada. Silahkan pilih tipe user",
                            Toast.LENGTH_SHORT).show();
                } else {
                    handleNavigasiTipeUser(dataSnapshot);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void handleNavigasiTipeUser(DataSnapshot dataSnapshot) {
        // ambil tipenya
        BaseUser baseUser = dataSnapshot.getValue(BaseUser.class);
        String tipeUser = baseUser.getTipeUser();
        hideProgressDialog();
        // navigasi
        if (tipeUser.equals(Const.KEY.TIPE_USER.PEMBELI)) {
            toMainPembeliActivity();
        } else {
            toMainPenjualActivity();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    // click view
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pembeli:
                tipeUser = Const.KEY.TIPE_USER.PEMBELI;
                createUserDatabase(tipeUser);
                toMainPembeliActivity();
                break;
            case R.id.btn_penjual:
                tipeUser = Const.KEY.TIPE_USER.PENJUAL;
                createUserDatabase(tipeUser);
                break;
            default:
                break;
        }
    }

    private void createUserDatabase(String tipeUser) {
        if (tipeUser == Const.KEY.TIPE_USER.PEMBELI) {
            final BaseUser pembeli = new Pembeli(
                    firebaseUser.getUid(),
                    firebaseUser.getDisplayName(),
                    firebaseUser.getEmail(),
                    tipeUser,
                    null);
            final DatabaseReference userRef = databaseReference.child("user");
            userRef.child(firebaseUser.getUid()).setValue(pembeli);

        } else {
            final BaseUser penjual = new Penjual(
                    firebaseUser.getUid(),
                    firebaseUser.getDisplayName(),
                    firebaseUser.getEmail(),
                    tipeUser, null, null, null, null, null, null);

            final DatabaseReference userRef = databaseReference.child("user");
            userRef.child(firebaseUser.getUid()).setValue(penjual);
        }

    }

    private void toMainPembeliActivity() {
        Intent intent = new Intent(PenjualPembeliActivity.this, MainPembeliActivity.class);
        startActivity(intent);
    }


    private void toMainPenjualActivity() {
        Intent intent = new Intent(PenjualPembeliActivity.this, MainPenjualActivity.class);
        startActivity(intent);
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading_fetching_user_type));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


}
