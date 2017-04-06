package com.example.arifinfirdaus.cato;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;

    private Button btnLoginFacebook;
    private Button btnLoginGoogle;
    private Button btnSignIn;

    private Button btnCreateAccount;
    private Button btnForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_passowrd);

        btnLoginFacebook = (Button) findViewById(R.id.btn_login_facebook);
        btnLoginGoogle = (Button) findViewById(R.id.btn_login_google);
        btnSignIn = (Button) findViewById(R.id.btn_sign_in);

        btnCreateAccount = (Button) findViewById(R.id.btn_create_account);
        btnForgotPassword = (Button) findViewById(R.id.btn_forgot_password);

        btnLoginFacebook.setOnClickListener(this);
        btnLoginGoogle.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
        btnCreateAccount.setOnClickListener(this);
        btnForgotPassword.setOnClickListener(this);

    }

    // click view
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_facebook:
                Toast.makeText(this, "btn_login_facebook", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_login_google:
                Toast.makeText(this, "btn_login_google", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_sign_in:
                Toast.makeText(this, "btn_sign_in", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_create_account:
                toSignInActivity();
                break;
            case R.id.btn_forgot_password:
                toForgotPasswordActivity();
                break;
            default:
                break;
        }
    }

    private void toForgotPasswordActivity() {
        Intent intent = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    private void toSignInActivity() {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
