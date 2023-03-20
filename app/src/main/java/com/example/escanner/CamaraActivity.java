package com.example.escanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class CamaraActivity extends AppCompatActivity {

    Button btnCamara;
    ImageView imgView;
    String mPhotoPath;
    String mAbsolutePhotoPath;
    File mPhotoFile;
    Button btnIrPantallaDatos;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnCamara = findViewById(R.id.btnCamara);
        imgView = findViewById(R.id.imageView);
        btnIrPantallaDatos = findViewById(R.id.btnIrPantallaDatos);

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                takePhoto(1);

            }

            private void takePictureIntent ( ) {
            }
        });




    }

    private void takePhoto (int requestCode) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createPhotoFile(requestCode);
            } catch (Exception e) {
                Toast.makeText(this, "Hubo un error con el archivo " + e.getMessage(), Toast.LENGTH_LONG).show();
            }

            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(CamaraActivity.this, "com.example.escanner", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, requestCode);
            }
        }
    }

    private File createPhotoFile(int requestCode) throws IOException {
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File photoFile = File.createTempFile(
                new Date() + "_photo",
                ".jpg",
                storageDir
        );
        if (requestCode ==  1) {
            mPhotoPath = "file:" + photoFile.getAbsolutePath();
            mAbsolutePhotoPath = photoFile.getAbsolutePath();
        }
        return photoFile;
    }





    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            mPhotoFile = new File(mAbsolutePhotoPath);
            Picasso.get().load(mPhotoPath).into(imgView);
        }



        if(btnIrPantallaDatos != null) {
            btnIrPantallaDatos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    Intent intent = new Intent(CamaraActivity.this, DatosActivity.class);
                    startActivity(intent);
                }
            });
        }











    }
    }





