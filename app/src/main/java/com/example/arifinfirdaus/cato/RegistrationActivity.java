package com.example.arifinfirdaus.cato;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etStoreName;
    private EditText etDescriptionn;
    private EditText etAddress;
    private EditText etContact;
    private EditText etOfficeHours;

    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etStoreName = (EditText) findViewById(R.id.et_store_name);
        etDescriptionn = (EditText) findViewById(R.id.et_description);
        etAddress = (EditText) findViewById(R.id.et_address);
        etContact = (EditText) findViewById(R.id.et_contact);
        etOfficeHours = (EditText) findViewById(R.id.et_office_hours);

        btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);

    }

    // click view
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_next) {
            Toast.makeText(this, "btn_next", Toast.LENGTH_SHORT).show();
            ;
        }

    }

}
