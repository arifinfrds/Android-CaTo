package com.example.arifinfirdaus.cato;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;

    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = (EditText) findViewById(R.id.et_email_sign_up);
        etPassword = (EditText) findViewById(R.id.et_password_sign_up);

        btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        btnSignUp.setOnClickListener(this);

    }

    // click view
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_sign_in) {
            Toast.makeText(this, "btn_sign_up", Toast.LENGTH_SHORT).show();
        }
    }
}

