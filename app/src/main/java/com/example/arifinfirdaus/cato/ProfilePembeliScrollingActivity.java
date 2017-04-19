package com.example.arifinfirdaus.cato;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arifinfirdaus.cato.Model.Pembeli;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilePembeliScrollingActivity extends AppCompatActivity implements FirebaseNetworkCalls {

    private TextView mTvNamaUser;
    private TextView mTvTipeUser;
    private TextView mTvEmailuser;

    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private FloatingActionButton mFabEdit;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pembeli_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initFirebase();

        mFabEdit = (FloatingActionButton) findViewById(R.id.fab);
        mFabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toEditProfilePembeliActivity();
            }
        });

        mCollapsingToolbarLayout = ((CollapsingToolbarLayout) findViewById(R.id.toolbar_layout));


        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        mTvNamaUser = (TextView) findViewById(R.id.tv_nama_user_profile_pembeli);
        mTvTipeUser = (TextView) findViewById(R.id.tv_tipe_user_profile_pembeli);
        mTvEmailuser = (TextView) findViewById(R.id.tv_email_user_profile_pembeli);
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
        mDatabaseReference.child("user").child(mFirebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.exists()) {
                            Toast.makeText(ProfilePembeliScrollingActivity.this, "Database user tidak ada", Toast.LENGTH_SHORT).show();
                        } else {
                            Pembeli pembeli = dataSnapshot.getValue(Pembeli.class);
                            mCollapsingToolbarLayout.setTitle(pembeli.getNama());
                            mTvNamaUser.setText(pembeli.getNama());
                            mTvTipeUser.setText(pembeli.getTipeUser());
                            mTvEmailuser.setText(pembeli.getEmail());
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }


    // intent.putExtra(Const.KEY.NAMA_BARANG, tvNamaBarang.getText().toString());
    private void toEditProfilePembeliActivity() {
        Intent intent = new Intent(ProfilePembeliScrollingActivity.this, EditProfilePembeliActivity.class);
        intent.putExtra(Const.KEY.NAMA_USER, mTvNamaUser.getText().toString());
        startActivity(intent);

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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
