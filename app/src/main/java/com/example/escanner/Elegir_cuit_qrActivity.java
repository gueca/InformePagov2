package com.example.escanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Elegir_cuit_qrActivity extends AppCompatActivity {

    Button btnPantallaQr;



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_qr);


        btnPantallaQr = findViewById(R.id.btnPantallaQr);





        // declaro ir a la pantalla del qr
        if(btnPantallaQr != null) {
            btnPantallaQr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    Intent intent = new Intent(Elegir_cuit_qrActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }






    }
}