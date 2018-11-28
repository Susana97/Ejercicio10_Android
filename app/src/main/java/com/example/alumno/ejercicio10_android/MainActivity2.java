package com.example.alumno.ejercicio10_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //poner el icono
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        // Activar la flecha para volver al activity principal
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Metodo para volver a la actividad principal si se pulsa la flecha.
        switch (item.getItemId()) {
            case android.R.id.home:
                //do whatever
                Intent datos = new Intent();
                setResult(RESULT_CANCELED, datos);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
