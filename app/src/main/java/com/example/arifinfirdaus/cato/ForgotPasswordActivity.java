package com.example.arifinfirdaus.cato;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private Button btnSend;

    private FirebaseAuth auth;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        auth = FirebaseAuth.getInstance();

        etEmail = (EditText) findViewById(R.id.et_forgot_password);
        btnSend = (Button) findViewById(R.id.btn_send_forgot_password);
        btnSend.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_send_forgot_password) {
            Toast.makeText(this, "handle send", Toast.LENGTH_SHORT).show();

            if (TextUtils.isEmpty(etEmail.getText().toString())) {
                Toast.makeText(getApplication(), "Plese fill email", Toast.LENGTH_SHORT).show();
            } else {
                handleSend(etEmail.getText().toString());
            }
        }
    }

    private void handleSend(String email) {
        Toast.makeText(this, "handle send", Toast.LENGTH_SHORT).show();
        showProgressDialog(email);

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgotPasswordActivity.this, "We have send you an email to change your password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Email is not valid", Toast.LENGTH_SHORT).show();
                }
                hideProgressDialog();
            }
        });
    }

    public void showProgressDialog(String email) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Sending to " + email + "...");
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
