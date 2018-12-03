package com.example.alumno.ejercicio10_android;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private EditText ETtitulo;
    private EditText ETdescripcion;
    private Spinner spinerTipo;
    private ArrayAdapter<String> adaptadorSppiner;
    private Button botonAceptar;
    private AdaptadorNotasDDBB adaptadorBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //poner el icono
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        // Activar la flecha para volver al activity principal
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adaptadorBD = new AdaptadorNotasDDBB(this);
        String titulo = getIntent().getStringExtra("titulo");
        String descripcion = getIntent().getStringExtra("descripcion");
        String tipo = getIntent().getStringExtra("tipo");
        ETtitulo = (EditText)findViewById(R.id.editTextTitulo);
        ETdescripcion = (EditText)findViewById(R.id.editTextDesc);
        ETtitulo.setText(titulo);
        ETdescripcion.setText(descripcion);

        final String [] tipos =  new String []{"AVISO", "REUNION", "VARIOS"};
        adaptadorSppiner = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, tipos);
        spinerTipo = (Spinner)findViewById(R.id.spinnerTipos);
        adaptadorSppiner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerTipo.setAdapter(adaptadorSppiner);

        if(tipo != null){
            if(tipo.equals("AVISO")){
                spinerTipo.setSelection(0);
            }else if(tipo.equals("REUNION")){
                spinerTipo.setSelection(1);
            }else{
                spinerTipo.setSelection(2);
            }
        }

        botonAceptar = (Button)findViewById(R.id.buttonAceptar);
        adaptadorBD.abrir();
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "@drawable/" + (tipos[spinerTipo.getSelectedItemPosition()]).toLowerCase();
                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                adaptadorBD.crearNota(tipos[spinerTipo.getSelectedItemPosition()],
                        ETtitulo.getText().toString(), ETdescripcion.getText().toString(), imageResource);
                Toast toastCreado = Toast.makeText(getApplicationContext(),"Nota creada.", Toast.LENGTH_SHORT);
                toastCreado.show();
                Intent i2 = new Intent();
                setResult(RESULT_OK, i2);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Metodo para volver a la actividad principal si se pulsa la flecha.
        switch (item.getItemId()) {
            case android.R.id.home:
                //VOLVER A LA PRIMERA ACTIVIDAD
                Intent datos = new Intent();
                setResult(RESULT_CANCELED, datos);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
