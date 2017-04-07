package com.example.arifinfirdaus.cato;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEditStoreName;
    private EditText etEditDescriptionn;
    private EditText etEditAddress;
    private EditText etEditContact;
    private EditText etOfficeHours;

    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etEditStoreName = (EditText) findViewById(R.id.et_edit_store_name);
        etEditDescriptionn = (EditText) findViewById(R.id.et_edit_description);
        etEditAddress = (EditText) findViewById(R.id.et_edit_address);
        etEditContact = (EditText) findViewById(R.id.et_edit_contact);
        etOfficeHours = (EditText) findViewById(R.id.et_edit_office_hours);

        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
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

    // click view
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
            Toast.makeText(this, "btn_next", Toast.LENGTH_SHORT).show();
            ;
        }
    }

}
