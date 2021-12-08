package com.example.practicalogin.Modelo;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Cliente implements Parcelable {

    private String usuario, constrasena, nombre, apellidos, descripcion;
    private Uri imgFoto;

    public Cliente(String usuario, String constrasena, String nombre, String apellidos, String descripcion, Uri imgFoto) {
        this.usuario = usuario;
        this.constrasena = constrasena;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.descripcion = descripcion;
        this.imgFoto = imgFoto;
    }

    public Cliente() {
        this.nombre = "";
        this.apellidos = "";
        this.usuario = "";
        this.constrasena = "";
        this.descripcion = "";
        this.imgFoto = null;
    }

    protected Cliente(Parcel in) {
        usuario = in.readString();
        constrasena = in.readString();
        nombre = in.readString();
        apellidos = in.readString();
        descripcion = in.readString();
        imgFoto = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Cliente> CREATOR = new Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel in) {
            return new Cliente(in);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Uri getImgFoto() {
        return imgFoto;
    }

    public void setImgFoto(Uri imgFoto) {
        this.imgFoto = imgFoto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getConstrasena() {
        return constrasena;
    }

    public void setConstrasena(String constrasena) {
        this.constrasena = constrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "usuario='" + usuario + '\'' +
                ", constrasena='" + constrasena + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imgFoto=" + imgFoto +
                '}' ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(usuario);
        parcel.writeString(constrasena);
        parcel.writeString(nombre);
        parcel.writeString(apellidos);
        parcel.writeString(descripcion);
        parcel.writeParcelable(imgFoto,i);
    }
}//Fin clase
