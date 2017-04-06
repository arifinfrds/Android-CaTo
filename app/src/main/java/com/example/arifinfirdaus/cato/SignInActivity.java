package com.example.arifinfirdaus.cato;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.google.firebase.auth.FirebaseUser;


public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;

    private Button btnLoginFacebook;
    private Button btnLoginGoogle;
    private Button btnSignIn;

    private Button btnCreateAccount;
    private Button btnForgotPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

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

        etEmail.setText("arifin.firdaus1996@gmail.com");
        etPassword.setText("catocato");

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // BaseUser is signed in
                    toPenjualPembeliActivity();
                    Log.d("onAuthStateChanged", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // BaseUser is signed out
                    Log.d("onAuthStateChanged", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

//        /**snip **/
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("com.package.ACTION_LOGOUT");
//        registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Log.d("onReceive", "Logout in progress");
//                //At this point you should start the login activity and finish this one
//                toSignInActivity();
//                finish();
//            }
//        }, intentFilter);
//        //** snip **//
    }


    // MARK : - FirebaseAuth listener
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    // MARK : - FirebaseAuth listener
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
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
                handleSignIn();
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

    private void handleSignIn() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        // check null
        if (email.matches("") || email.equals(null) || password.matches("") || password.equals("null") ||
                password.length() <= 6) {
            etEmail.setError("");
            etPassword.setError("");
        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("onComplete", "signInWithEmail:onComplete:" + task.isSuccessful());
                            Toast.makeText(SignInActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            toPenjualPembeliActivity();
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w("onComplete", "signInWithEmail:failed", task.getException());
                                Toast.makeText(SignInActivity.this, "auth failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                            // ...
                        }
                    });
        }
    }

    private void toPenjualPembeliActivity() {
        Intent intent = new Intent(SignInActivity.this, PenjualPembeliActivity.class);
        startActivity(intent);
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
