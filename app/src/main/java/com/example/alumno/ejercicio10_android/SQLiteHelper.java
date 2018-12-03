package com.example.alumno.ejercicio10_android;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios
    private Context contexto;
    String sqlCreate = "CREATE TABLE NOTAS (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "categoria TEXT NOT NULL, " +
            "titulo TEXT NOT NULL," +
            "descripcion TEXT NOT NULL," +
            "icono INT NOT NULL)";

    public SQLiteHelper(Context contexto, String nombredb,SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(contexto, nombredb, factory, version);
        this.contexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        try{
            InputStream ficheroRaw = contexto.getResources().openRawResource(R.raw.bdnombres);
            BufferedReader in = new BufferedReader(new InputStreamReader(ficheroRaw));
            String auxStr = "";
            while((auxStr = in.readLine())!= null){
                db.execSQL(auxStr);
            }//end while
            in.close();
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {

    }
}
