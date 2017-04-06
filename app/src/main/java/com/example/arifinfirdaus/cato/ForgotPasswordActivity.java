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

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etEmail = (EditText) findViewById(R.id.et_forgot_password);
        btnSend = (Button) findViewById(R.id.btn_send_forgot_password);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // cek null
                if (etEmail.getText().toString().matches("") || etEmail.getText().toString().equals(null)) {
                    etEmail.setError("empty");
                } else {
                    handleSend(etEmail.getText().toString());
                }
            }
        });


    }

    private void handleSend(String email) {
        Toast.makeText(this, "handle send", Toast.LENGTH_SHORT).show();
    }


}
