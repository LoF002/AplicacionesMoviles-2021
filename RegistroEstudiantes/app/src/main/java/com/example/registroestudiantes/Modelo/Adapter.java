package com.example.registroestudiantes.Modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.registroestudiantes.R;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    Context context;
    ArrayList<Estudiante> lista;

    public Adapter(Context context, ArrayList<Estudiante> lista) {
        this.context = context;
        this.lista = lista;
    }

    //devuelve el tamanno de la lista
    @Override
    public int getCount() {
        return lista.size();
    }

    //devolver un objeto
    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(lista != null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item,null);
        }
        TextView lbNombre = view.findViewById(R.id.lbNombre);
        TextView lbCarnet = view.findViewById(R.id.lbCarnet);
        TextView lbCarrera = view.findViewById(R.id.lbCarrera);
        TextView lbTelefono = view.findViewById(R.id.lbTelefono);
        ImageView imgU = view.findViewById(R.id.imgU);

        lbNombre.setText(lista.get(i).getNombre());
        lbCarnet.setText(lista.get(i).getCarnet());
        lbCarrera.setText(lista.get(i).getCarrera());
        lbTelefono.setText(lista.get(i).getTelefono());
        imgU.setImageURI(lista.get(i).getImgUser());

        return view;
    }
}//fin clase
