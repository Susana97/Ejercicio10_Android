package com.example.alumno.ejercicio10_android;

import android.animation.Animator;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton botonAdd;
    private final int SECONDARY_ACTIVITY_TAG = 0;
    private SQLiteHelper SQLhelper;
    private SQLiteDatabase db;
    private AdaptadorNotasDDBB adaptadorNotas;
    private ListView listaNotas;
    private AdaptadorNotas adaptadorListaNotas;
    private TextView textViewTitulo;
    private ArrayList<InformacionNotas> arrayNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //poner el icono
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        textViewTitulo = (TextView)findViewById(R.id.textViewPrincipal);
        adaptadorNotas = new AdaptadorNotasDDBB(this);
        botonAdd = (FloatingActionButton) findViewById(R.id.FloatingAniadir);
        final Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                android.R.interpolator.fast_out_slow_in);

        botonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarSegundaActividad(this);

                botonAdd.animate()
                        .scaleX(0)
                        .scaleY(0)
                        .setInterpolator(interpolador)
                        .setDuration(600)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                botonAdd.animate()
                                        .scaleY(1)
                                        .scaleX(1)
                                        .setInterpolator(interpolador)
                                        .setDuration(600)
                                        .start();
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {
                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {
                            }
                        });
            }
        });

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        adaptadorNotas =  new AdaptadorNotasDDBB(this);
        db = adaptadorNotas.abrir();//abrimos la base de datos.

        arrayNotas = leerInformacionBaseDatos();
        adaptadorListaNotas = new AdaptadorNotas(this, arrayNotas);
        listaNotas = (ListView)findViewById(R.id.ListaNotas);
        listaNotas.setAdapter(adaptadorListaNotas);
        registerForContextMenu(listaNotas);

        if(arrayNotas != null){
            textViewTitulo.setText("TUS NOTAS");
            textViewTitulo.setGravity(Gravity.CENTER);
            listaNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    lanzarSegundaActividad(this, arrayNotas.get(position).getId(),
                            arrayNotas.get(position).getTipo(),
                            arrayNotas.get(position).getTitulo(), arrayNotas.get(position).getDescripcion());

                }
            });
            listaNotas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                public boolean onItemLongClick(AdapterView<?> arg0, View v, int index, long arg3) {
                    return false;
                }
            });
        }
    }

    public ArrayList<InformacionNotas> leerInformacionBaseDatos() {
        Cursor notasDB = adaptadorNotas.obtenerNotas();
        ArrayList<InformacionNotas> mArrayList = new ArrayList<InformacionNotas>();
        if (notasDB.moveToFirst() == false) {//el cursor esta vacio.
            return null;
        } else {//el cursor tiene datos.
            mArrayList.add(new InformacionNotas(notasDB.getInt(0), notasDB.getInt(4),
                    notasDB.getString(2), notasDB.getString(3),
                    notasDB.getString(1)));
            while (notasDB.moveToNext()) {
                mArrayList.add(new InformacionNotas(notasDB.getInt(0), notasDB.getInt(4),
                        notasDB.getString(2), notasDB.getString(3),
                        notasDB.getString(1)));

            }
            return mArrayList;
        }
    }

    public void lanzarSegundaActividad(AdapterView.OnItemClickListener v, int id, String tipo, String titulo, String descripcion){
        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("id", id);
        i.putExtra("tipo", tipo);
        i.putExtra("titulo", titulo);
        i.putExtra("descripcion", descripcion);
        startActivityForResult(i, SECONDARY_ACTIVITY_TAG);
        //creamos el intent que lanza la actividad y le pasamos la imagen como
        //datos extra.
    }

    public void lanzarSegundaActividad(View.OnClickListener v){
        Intent i = new Intent(this, MainActivity2.class);
        startActivityForResult(i, SECONDARY_ACTIVITY_TAG);
        //creamos el intent que lanza la actividad y le pasamos la imagen como
        //datos extra.
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        //INFLADOR DEL MENU CONTEXTUAL.
        MenuInflater inflater = getMenuInflater();
        //PARA OBTENER LA INFORMACION DEL ELEMENTO DE LA LISTA SOBRE LA QUE SE ACTIVA EL MENU.
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //INFLAMOS EL MENU CONTEXTUAL
        inflater.inflate(R.menu.menu_flotante_borrar, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch(item.getItemId()){
            case R.id.BotonBorrar:
                adaptadorNotas.borrarNota(arrayNotas.get(info.position).getId());
                arrayNotas.remove(info.position);
                adaptadorListaNotas.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_CANCELED){
            Toast toastSalir = Toast.makeText(this, "Accion Cancelada", Toast.LENGTH_SHORT);
            toastSalir.show();
        }else{
            if(requestCode == SECONDARY_ACTIVITY_TAG){//si vuelve de la actividad Añadir
                InformacionNotas notaCreada = new InformacionNotas((int)data.getLongExtra("id",-1),
                        data.getIntExtra("imagen",-1), data.getStringExtra("titulo"),
                        data.getStringExtra("descripcion"), data.getStringExtra("tipo"));

                boolean encontrado = false;
                for(int i=0; i<arrayNotas.size(); i++){
                    if(arrayNotas.get(i).getId() == notaCreada.getId()){
                        encontrado = true;
                        arrayNotas.set(i, notaCreada);
                    }
                }
                if(encontrado == false){
                    arrayNotas.add(notaCreada);
                }
                adaptadorListaNotas.notifyDataSetChanged();
            }
        }
    } // onActivityResult
}
