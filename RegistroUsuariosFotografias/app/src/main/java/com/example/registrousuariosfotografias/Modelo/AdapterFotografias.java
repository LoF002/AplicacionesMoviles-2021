package com.example.registrousuariosfotografias.Modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.registrousuariosfotografias.R;

import java.util.ArrayList;

public class AdapterFotografias extends BaseAdapter {

    Context contexto;
    ArrayList<Fotografia> listaFotografias;

    public AdapterFotografias(Context contexto, ArrayList<Fotografia> listaFotografias) {
        this.contexto = contexto;
        this.listaFotografias = listaFotografias;
    }

    @Override
    public int getCount() {
        return listaFotografias.size();
    }

    @Override
    public Object getItem(int i) {
        return listaFotografias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_lista_fotos, null);
        }

        TextView lblAutor = view.findViewById(R.id.lblAutor);
        TextView lblDescripcion = view.findViewById(R.id.lblDescripcion);

        ImageView imgFoto = view.findViewById(R.id.imgFoto);

        lblAutor.setText(listaFotografias.get(i).getAutor());
        lblDescripcion.setText(listaFotografias.get(i).getDescripcion());

        if (listaFotografias.get(i).getImgFoto() != null) {
            imgFoto.setImageURI(listaFotografias.get(i).getImgFoto());
        }

        return view;
    }
}
