package com.example.arifinfirdaus.cato;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arifinfirdaus.cato.Model.Pembeli;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EditProfilePembeliActivity extends AppCompatActivity implements FirebaseNetworkCalls {

    private EditText mEtNama;

    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private String mTipeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_pembeli);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mEtNama = (EditText) findViewById(R.id.et_edit_nama);

        getDataFromPreviousActivity();

        initFirebase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    @Override
    public void initFirebase() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void fetchData() {
        fetchUserData();
    }

    private void fetchUserData() {
        // ambil tipe user
        mDatabaseReference.child("user").child(mFirebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Pembeli pembeli = dataSnapshot.getValue(Pembeli.class);
                        mTipeUser = pembeli.getTipeUser();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            saveUserData();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveUserData() {
        if (isEditTextKosong()) {
            Toast.makeText(this, "Isi data", Toast.LENGTH_SHORT).show();
            mEtNama.setError("Isi data");
            return;
        } else {
            // save
            Pembeli pembeli = new Pembeli(
                    mFirebaseUser.getUid(),
                    mEtNama.getText().toString(),
                    mFirebaseUser.getEmail(),
                    mTipeUser,
                    null);
            mDatabaseReference.child("user").child(mFirebaseUser.getUid()).setValue(pembeli);
        }

    }

    private boolean isEditTextKosong() {
        // get etNama
        String nama = mEtNama.getText().toString();

        if (TextUtils.isEmpty(nama)) {
            return true;
        } else {
            return false;
        }
    }

    private void getDataFromPreviousActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nama = extras.getString(Const.KEY.NAMA_USER);

            mEtNama.setText(nama);

        }
    }


}
