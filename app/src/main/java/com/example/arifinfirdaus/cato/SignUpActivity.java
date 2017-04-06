package com.example.arifinfirdaus.cato;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;

    private Button btnSignUp;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = (EditText) findViewById(R.id.et_email_sign_up);
        etPassword = (EditText) findViewById(R.id.et_password_sign_up);

        btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        btnSignUp.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    // click view
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_sign_up) {
            handleSignUp();
        }
    }

    private void handleSignUp() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        // check null
        if (email.matches("") || email.equals(null) || password.matches("") || password.equals("null") ||
                password.length() <= 6) {
            etEmail.setError("");
            etPassword.setError("");
        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("onComplete", "createUserWithEmail:onComplete:" + task.isSuccessful());
                            Toast.makeText(SignUpActivity.this, "BaseUser created", Toast.LENGTH_SHORT).show();
                            finish();

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "auth failed",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
    }
}

