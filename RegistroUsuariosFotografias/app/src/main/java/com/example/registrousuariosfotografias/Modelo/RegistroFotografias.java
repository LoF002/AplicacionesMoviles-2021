package com.example.registrousuariosfotografias.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class RegistroFotografias implements Serializable, Parcelable {

    ArrayList<Fotografia> listaFotografias;

    Fotografia foto;

    public RegistroFotografias() {
        listaFotografias = new ArrayList<Fotografia>();


    }

    protected RegistroFotografias(Parcel in) {
    }

    public static final Creator<RegistroFotografias> CREATOR = new Creator<RegistroFotografias>() {
        @Override
        public RegistroFotografias createFromParcel(Parcel in) {
            return new RegistroFotografias(in);
        }

        @Override
        public RegistroFotografias[] newArray(int size) {
            return new RegistroFotografias[size];
        }
    };

    public String agregarFotografia(Fotografia foto) {

        if (foto != null) {
            listaFotografias.add(foto);
            //return "Fotografía registrada";
            return foto.toString();
        }

        return "Datos inválidos";

    }

    public ArrayList getListaFotos() {
        return listaFotografias;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
