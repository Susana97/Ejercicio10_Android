package com.example.alumno.ejercicio10_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE NOTAS (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "categoria TEXT NOT NULL, " +
            "titulo TEXT NOT NULL," +
            "descripcion TEXT NOT NULL," +
            "icono INT NOT NULL)";

    public SQLiteHelper(Context contexto, String nombredb,SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(contexto, nombredb, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {

    }
}
