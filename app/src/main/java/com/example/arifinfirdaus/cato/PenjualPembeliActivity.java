package com.example.arifinfirdaus.cato;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PenjualPembeliActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPenjual;
    private Button btnPembeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnPenjual = (Button) findViewById(R.id.btn_penjual);
        btnPembeli = (Button) findViewById(R.id.btn_pembeli);

        btnPenjual.setOnClickListener(this);
        btnPembeli.setOnClickListener(this);

    }

    // click view
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_penjual:
                Toast.makeText(this, "btn_penjual", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_pembeli:
                Toast.makeText(this, "btn_pembeli", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
