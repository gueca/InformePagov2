package com.example.escanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnScan;
    TextView txtResultado;
    Button btnpasarcam;
    Button btnPantallaCamara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnScan = findViewById(R.id.btnScan);
        txtResultado = findViewById(R.id.txtResultado);
        btnpasarcam = findViewById(R.id.btnpasarcam);
        btnPantallaCamara = findViewById(R.id.btnPantallaCamara);


        //aca declaro el boton para ir a la pantalla de la camara
        if (btnPantallaCamara != null) {
            btnPantallaCamara.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CamaraActivity.class);
                    startActivity(intent);
                }
            });
        }

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scanear el codigo qr de su factura");
                integrator.setCameraId(0);
                integrator.setOrientationLocked(false);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }
        });
    }


    private void validateField() {
        String resultado = txtResultado.toString();
        if (!resultado.isEmpty()) {
            btnPantallaCamara.setEnabled(true);
        }
    }

    // aca traigo el resultado scaneado o el uso al scanear algo

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "LECTURA CANCELADA", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                validateField();
                txtResultado.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}






