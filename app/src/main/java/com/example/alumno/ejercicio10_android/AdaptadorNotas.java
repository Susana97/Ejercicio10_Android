package com.example.alumno.ejercicio10_android;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorNotas extends ArrayAdapter<InformacionNotas> {

    Activity contexto;
    // Contructor del adaptador usando el contexto de la aplicacion actual

    AdaptadorNotas(Activity contexto, ArrayList<InformacionNotas> datos) {

        // Llamamos al constructor de la clase superior
        //se le pasa el xml que genera la fila y el array de objetos
        super(contexto, R.layout.list_item_option, datos);
        this.contexto = contexto;
    }

    // Metodo que dibuja la Vista de cada Opcion
    // Se invoca cada vez que haya que mostrar un elemento de la lista.
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View item = convertView;
        ViewHolder holder;
        if(item==null) {
            LayoutInflater inflater = contexto.getLayoutInflater();
            item = inflater.inflate(R.layout.list_item_option, null);

            holder = new ViewHolder();

            holder.icono = (ImageView)item.findViewById(R.id.imageViewIcono);
            holder.titulo = (TextView)item.findViewById(R.id.textViewTitulo);
            holder.tipo = (TextView)item.findViewById(R.id.textViewTipo);
            item.setTag(holder);
        }else{
            holder = (ViewHolder)item.getTag();
        }

        //Mediante getItem cargamos cada uno de los objetos del array
        InformacionNotas mielemento=getItem(position);

        holder.icono.setImageResource(mielemento.getIcono());
        holder.titulo.setText(mielemento.getTitulo());
        holder.tipo.setText(mielemento.getTipo());

        // Devolvemos la Vista (nueva o reutilizada) que dibuja la opcion
        return(item);
    }

    class ViewHolder {
        ImageView icono;
        TextView titulo;
        TextView tipo;
    }


}
