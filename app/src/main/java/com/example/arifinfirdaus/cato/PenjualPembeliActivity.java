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


public class PenjualPembeliActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

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

        btnPembeli.setOnLongClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

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

    @Override
    protected void onResume() {
        super.onResume();
        cekTipeUser();
    }

    private void cekTipeUser() {
        showProgressDialog();

        // get userRef
        final DatabaseReference userRef = databaseReference.child("user");
        // System.out.println(firebaseUser.getUid());
        Log.d("asdasd", "current UID : " + firebaseUser.getUid());
        Log.d("asdasd", "current email : " + firebaseUser.getEmail());

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BaseUser baseUser = dataSnapshot.getValue(BaseUser.class);

                // Toast.makeText(PenjualPembeliActivity.this, dataSnapshot.toString(), Toast.LENGTH_SHORT).show();

                Log.d("asdasd", "current tipeUser : " + baseUser.getTipeUser());

                // loop untuk cek uid user sekarang sudah terbuat atau belum

                fetchCurrentUIDFromDatabase(baseUser);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private boolean fetchCurrentUIDFromDatabase(final BaseUser baseUser) {

        // cek uid apa sudah ada di database atau blm
        DatabaseReference userRef = databaseReference.child("user");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Post newPost = dataSnapshot.getValue(Post.class);
                // BaseUser bu = dataSnapshot.getValue(BaseUser.class);
                String tipe = baseUser.getTipeUser();
                Log.w("asdasd", "onDataChange tipe: " + tipe);

                Log.w("asdasd", "onDataChange dataSnapshot: " + dataSnapshot);
                // BaseUser baseUser = dataSnapshot.getValue(dataSnapshot.getClass());

                // cek apakah uid current user ada pada databse. klo gk ada, set tipe baru.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.d("asdasd", "snapshot.toString : " + snapshot.toString());
                    Log.i("asdasd", "snapshot : " + snapshot);
                    Log.i("asdasd", "snapshot : " + snapshot);
                    boolean ketemu = false;
                    String uidDataSnaphot = String.valueOf(snapshot.getValue());
                    Log.w("asdasd", "uidDataSnaphot String.valueOf : " + uidDataSnaphot);

                    final String currentUID = firebaseUser.getUid();
                    BaseUser bu = snapshot.getValue(BaseUser.class);

                    Log.e("asdasd", "currentUID                 : " + currentUID); // pembanding
                    Log.e("asdasd", "baseUser.getUid()          : " + baseUser.getUid());
                    Log.e("asdasd", "uidDataSnaphot             : " + uidDataSnaphot);
                    Log.e("asdasd", "snapshot.getKey()          : " + snapshot.getKey());
                    Log.e("asdasd", "snapshot.getValue()        : " + snapshot.getValue());
                    Log.e("asdasd", "bu.getUid()                : " + bu.getUid());

                    if (currentUID.equals(bu.getUid())) {
                        Log.w("asdasd", "if currentUID.equals(bu.getUid()) : " + bu.getUid());

                        // seleksi kalo true (user terdaftar dgn tipe user tertentu, maka ke user screen
                        String tipeBu = bu.getTipeUser();

                        if (tipeBu.equals("pembeli")) {
                            hideProgressDialog();
                            Log.w("asdasd", "if uidDataSnaphot.equals(pembeli) : " + uidDataSnaphot);
                            // Toast.makeText(PenjualPembeliActivity.this, "uidDataSnaphot = pembeli", Toast.LENGTH_SHORT).show();
                            toMainActivity();
                        } else if (tipeBu.equals("penjual")) {
                            hideProgressDialog();
                            Toast.makeText(PenjualPembeliActivity.this, "toPenjualActivity dari uidDataSnapshot", Toast.LENGTH_SHORT).show();
                            toMainPenjualActivity();
                        }
                    } else {
                        hideProgressDialog();
                        // Toast.makeText(PenjualPembeliActivity.this, "You have not choose user type yet. Please choose one below.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        return false;
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
                tipeUser = "pembeli";
                saveUserPembeliInfo(tipeUser);
                toMainActivity();
                break;
            case R.id.btn_penjual:
                Toast.makeText(this, "btn_penjual", Toast.LENGTH_SHORT).show();
                tipeUser = "penjual";
                saveUserPenjualInfo(tipeUser);
                break;
            default:
                break;
        }
    }

    private void saveUserPembeliInfo(String tipeUser) {
        final BaseUser pembeli = new Pembeli(
                firebaseUser.getUid(),
                firebaseUser.getDisplayName(),
                firebaseUser.getEmail(),
                tipeUser,
                null);

        final DatabaseReference userRef = databaseReference.child("user");
        userRef.push().setValue(pembeli);
    }

    private void saveUserPenjualInfo(String tipeUser) {
        final BaseUser penjual = new Penjual(
                firebaseUser.getUid(),
                firebaseUser.getDisplayName(),
                firebaseUser.getEmail(),
                tipeUser, null, null, null, null, null, null);

        final DatabaseReference userRef = databaseReference.child("user");
        userRef.push().setValue(penjual);
    }

    private void toSignInActivity() {
        Intent intent = new Intent(PenjualPembeliActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_pembeli) {
            toMainActivity();
        }
        return false;
    }

    private void toMainActivity() {
        Intent intent = new Intent(PenjualPembeliActivity.this, MainPembeliActivity.class);
        startActivity(intent);
    }


    private void toMainPenjualActivity() {
        Intent intent = new Intent(PenjualPembeliActivity.this, MainPenjualActivity.class);
        startActivity(intent);
    }


}
