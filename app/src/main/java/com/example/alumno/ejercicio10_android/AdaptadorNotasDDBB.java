package com.example.alumno.ejercicio10_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class AdaptadorNotasDDBB {

    private Context contexto;
    private SQLiteDatabase baseDatos;
    private SQLiteHelper bdHelper;


    public AdaptadorNotasDDBB(Context context){
        this.contexto = context;
    }

    //Metodo que abre la base de datos.
    public SQLiteHelper abrir() throws SQLException{
        return null;
    }

    //metodo que cierra la base de datos.
    public void cerrar(){
        bdHelper.close();
    }

    //metodo que crea la nota. Devuelve el id del registro nuevo si se ha dado de alta
    //correctamente o -1 si no lo ha hecho.
    public long crearNota(String categoria, String titulo, String descripcion, int icono){
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("categoria", categoria);
        nuevoRegistro.put("titulo", titulo);
        nuevoRegistro.put("descripcion", descripcion);
        nuevoRegistro.put("icono", icono);

        baseDatos.insert("NOTAS", null, nuevoRegistro);
        return -1;
    }

    //Metodo que actualiza la nota
    //icono vacio = 0;
    public boolean actualizarNota(int _id, String categoria, String titulo, String descripcion,
                                  int icono){

        ContentValues registroModificar = new ContentValues();
        if(categoria != null){
            registroModificar.put("categoria", categoria);
        }else{
            if(titulo != null){
                registroModificar.put("titulo", titulo);
            }else{
                if(descripcion!=null){
                    registroModificar.put("descripcion", descripcion);
                }else{
                    if(icono != 0) {
                        registroModificar.put("icono", icono);
                    }else{
                        return false;
                        //no se ha modificado nada.
                    }
                }
            }
        }
        baseDatos.update("NOTAS", registroModificar, "_id=" + _id, null);
        return true;
    }

    //metodo que borra la nota.
    public boolean borrarNota(long id){
        baseDatos.delete("NOTAS", "_id=" + id, null);
        return true;
    }

    //devuelve un cursor con la consulta con todos los registros de la BD
    public Cursor obtenerNotas(){
        Cursor notas = baseDatos.rawQuery(" SELECT categoria, titulo, descripcion, " +
                "icono FROM NOTAS", null);
        return notas;
    }

    //devuelve una nota de la base de datos.
    public Cursor getNota(long id) throws SQLException{
        Cursor notasPorId = baseDatos.rawQuery(" SELECT categoria, titulo, descripcion, icono FROM NOTAS" +
                " WHERE _id=" + id, null);
        return notasPorId;
    }

    //metodo que crea un objeto ContentValues con los parámetros indicados.
    private ContentValues crearContentValues(String categoria, String titulo, String descripcion,
                                             int icono){
        return null;
    }
}


