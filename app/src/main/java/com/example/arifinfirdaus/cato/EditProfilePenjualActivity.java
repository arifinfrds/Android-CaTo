package com.example.arifinfirdaus.cato;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arifinfirdaus.cato.Model.BaseUser;
import com.example.arifinfirdaus.cato.Model.Penjual;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditProfilePenjualActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_store_name, et_description, et_address, et_contacts, et_office_hours;

    private Button btn_next_edit_penjual;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_penjual);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        et_store_name = (EditText) findViewById(R.id.et_store_name);
        et_description = (EditText) findViewById(R.id.et_description);
        et_address = (EditText) findViewById(R.id.et_address);
        et_contacts = (EditText) findViewById(R.id.et_contacts);
        et_office_hours = (EditText) findViewById(R.id.et_office_hours);

        btn_next_edit_penjual = (Button) findViewById(R.id.btn_next_edit_penjual);
        btn_next_edit_penjual.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
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

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn_next_edit_penjual) {
            // ambil Stringnya
            String str_et_store_name = et_store_name.getText().toString();
            String str_et_description = et_description.getText().toString();
            String str_et_address = et_address.getText().toString();
            String str_et_contacts = et_contacts.getText().toString();
            String str_et_office_hours = et_office_hours.getText().toString();

            // check null
            if (TextUtils.isEmpty(str_et_store_name)) {
                Toast.makeText(this, "store tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(str_et_description)) {
                Toast.makeText(this, "deskripsi tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(str_et_address)) {
                Toast.makeText(this, "address tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(str_et_contacts)) {
                Toast.makeText(this, "kontak tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(str_et_office_hours)) {
                Toast.makeText(this, "jam kerja tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }


            DatabaseReference userRef = databaseReference.child("user");
            firebaseUser = firebaseAuth.getCurrentUser();
            String userUid = firebaseUser.getUid();
            // Penjual penjual = new Penjual(userUid, str_et_store_name, );

            // databaseReference.child(firebaseUser.getUid()).setValue()
            Map<String, Penjual> updatedReviewmap = new HashMap<>();
            // storeNameMap.put("nama", str_et_store_name);
            Map<String, Object> updatedReview = new HashMap<String, Object>();
            updatedReview.put("nama", et_store_name);
            updatedReview.put("deskripsi", et_description);
            updatedReview.put("alamat", et_address);
            updatedReview.put("kontak", et_contacts);
            updatedReview.put("jamKerja", et_office_hours);

            BaseUser baseUser = new Penjual(
                    firebaseUser.getUid(),
                    str_et_store_name,
                    firebaseUser.getEmail(),
                    "penjual",
                    str_et_description,
                    str_et_address,
                    str_et_contacts,
                    str_et_office_hours,
                    new LatLng(12312313, 123123132)
            );
            // userRef.child(firebaseUser.getUid()).updateChildren(updatedReview);
            // userRef.child(userRef.getKey()).setValue(baseUser);

            // databaseReference.child(firebase1User.getUid()).update
            Log.w("edit_profile_penjual", "firebaseUser.getUid(): " + firebaseUser.getUid());
            Log.w("edit_profile_penjual", "firebaseUser.firebaseAuth.getCurrentUser().getUid(): " + firebaseAuth.getCurrentUser().getUid());
            Log.w("edit_profile_penjual", "baseUser: " + baseUser);
            // userRef.child(PenjualPembeliActivity.firebasePushIdKey).setValue(baseUser);


        }
    }
}
