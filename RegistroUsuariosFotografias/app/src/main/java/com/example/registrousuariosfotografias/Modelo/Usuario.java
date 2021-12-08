package com.example.registrousuariosfotografias.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Usuario implements Parcelable, Serializable {

    //private int id;
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String contrasenha;

    public Usuario(String nombreUsuario, String nombre, String apellido, String contrasenha) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenha = contrasenha;
    }

    public Usuario() {

    }

    protected Usuario(Parcel in) {
        //id = Integer.parseInt(in.readString());
        nombreUsuario = in.readString();
        nombre = in.readString();
        apellido = in.readString();
        contrasenha = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };


    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", contrasenha='" + contrasenha + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombreUsuario);
        parcel.writeString(nombre);
        parcel.writeString(apellido);
        parcel.writeString(contrasenha);
    }
}//Fin clase Usuario
