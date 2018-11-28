package com.example.alumno.ejercicio10_android;

import android.animation.Animator;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton botonAdd;
    private final int SECONDARY_ACTIVITY_TAG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //poner el icono
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        botonAdd= (FloatingActionButton) findViewById(R.id.FloatingAniadir);
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
    }

    public void lanzarSegundaActividad (View.OnClickListener v){
        Intent i = new Intent(this, MainActivity2.class);
        startActivityForResult(i, SECONDARY_ACTIVITY_TAG);
        //creamos el intent que lanza la actividad y le pasamos la imagen como
        //datos extra.
    }
}
