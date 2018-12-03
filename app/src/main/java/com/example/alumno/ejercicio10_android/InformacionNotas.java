package com.example.alumno.ejercicio10_android;

public class InformacionNotas {

    private int id;
    private int icono;
    private String titulo;
    private String descripcion;
    private String tipo;

    public InformacionNotas(int id, int icono, String titulo, String descripcion, String tipo) {
        this.id=id;
        this.icono = icono;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public int getId() {return id;}

    public void setId(int id) { this.id = id;}

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "InformacionNotas{" +
                "icono = '" + icono + '\''+
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
