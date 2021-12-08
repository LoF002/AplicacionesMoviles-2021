package com.example.practicalogin.Modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practicalogin.R;

import java.util.ArrayList;

public class AdapterFoto extends BaseAdapter {

    Context context;
    ArrayList<Cliente> listaClientesAdapFotos;

    public AdapterFoto(Context context, ArrayList<Cliente> listaClientesAdapFotos) {
        this.context = context;
        this.listaClientesAdapFotos = listaClientesAdapFotos;
    }//Fin constructor


    @Override
    public int getCount() {
        return listaClientesAdapFotos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaClientesAdapFotos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (listaClientesAdapFotos != null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_fotos, null);
        }//Fin if

        TextView lbUsuarioFotos = view.findViewById(R.id.lbUsuarioFotos);
        TextView lbDescripcion = view.findViewById(R.id.lbDescripcion);
        ImageView imgFotoLista = view.findViewById(R.id.imgFotoLista);

        lbUsuarioFotos.setText(listaClientesAdapFotos.get(i).getUsuario());
        lbDescripcion.setText(listaClientesAdapFotos.get(i).getDescripcion());
        imgFotoLista.setImageURI(listaClientesAdapFotos.get(i).getImgFoto());

        return view;
    }
}//Fin clase
