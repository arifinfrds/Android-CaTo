package com.example.arifinfirdaus.cato;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PenjualPembeliActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private Button btnPenjual;
    private Button btnPembeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjual_pembeli);

        btnPenjual = (Button) findViewById(R.id.btn_penjual);
        btnPembeli = (Button) findViewById(R.id.btn_pembeli);

        btnPenjual.setOnClickListener(this);
        btnPembeli.setOnClickListener(this);

        btnPembeli.setOnLongClickListener(this);

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    // click view
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_penjual:
                Toast.makeText(this, "btn_penjual", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_pembeli:
                toMainActivity();
                break;
            default:
                break;
        }
    }

    private void toSignInActivity() {
        Intent intent = new Intent(PenjualPembeliActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_pembeli) {
            toMainActivity();
        }
        return false;
    }

    private void toMainActivity() {
        Intent intent = new Intent(PenjualPembeliActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
