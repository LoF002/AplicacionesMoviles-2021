package com.example.registrousuariosfotografias.Modelo;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Fotografia implements Parcelable {

    private String autor;
    private String descripcion;
    private Uri imgFoto;

    public Fotografia(String autor, String descripcion, Uri imgFoto) {
        this.autor = autor;
        this.descripcion = descripcion;
        this.imgFoto = imgFoto;
    }

    public Fotografia() {

    }

    protected Fotografia(Parcel in) {
        autor = in.readString();
        descripcion = in.readString();
        imgFoto = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Fotografia> CREATOR = new Creator<Fotografia>() {
        @Override
        public Fotografia createFromParcel(Parcel in) {
            return new Fotografia(in);
        }

        @Override
        public Fotografia[] newArray(int size) {
            return new Fotografia[size];
        }
    };

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

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

    @Override
    public String toString() {
        return "Fotografia{" +
                "autor='" + autor + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imgFoto=" + imgFoto +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(autor);
        parcel.writeString(descripcion);
        parcel.writeParcelable(imgFoto, i);
    }
}//Fin clase Fotografia
