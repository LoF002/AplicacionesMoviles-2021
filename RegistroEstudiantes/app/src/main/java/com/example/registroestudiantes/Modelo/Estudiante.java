package com.example.registroestudiantes.Modelo;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Estudiante implements Parcelable {

    private String nombre, carnet, carrera, telefono;
    private Uri imgUser;

    public Estudiante(String nombre, String carnet, String carrera, String telefono, Uri imgUser) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.carrera = carrera;
        this.telefono = telefono;
        this.imgUser = imgUser;
    }

    public Estudiante() {
        this.nombre = "";
        this.carnet = "";
        this.carrera = "";
        this.telefono = "";
        this.imgUser = null;
    }

    protected Estudiante(Parcel in) {
        nombre = in.readString();
        carnet = in.readString();
        carrera = in.readString();
        telefono = in.readString();
        imgUser = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Estudiante> CREATOR = new Creator<Estudiante>() {
        @Override
        public Estudiante createFromParcel(Parcel in) {
            return new Estudiante(in);
        }

        @Override
        public Estudiante[] newArray(int size) {
            return new Estudiante[size];
        }
    };

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Uri getImgUser() {
        return imgUser;
    }

    public void setImgUser(Uri imgUser) {
        this.imgUser = imgUser;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", carnet='" + carnet + '\'' +
                ", carrera='" + carrera + '\'' +
                ", telefono='" + telefono + '\'' +
                ", imgUser=" + imgUser +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(carnet);
        parcel.writeString(carrera);
        parcel.writeString(telefono);
        parcel.writeParcelable(imgUser,i);
    }
}//Fin clase
