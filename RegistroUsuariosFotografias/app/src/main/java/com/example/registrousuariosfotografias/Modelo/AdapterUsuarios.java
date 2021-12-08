package com.example.registrousuariosfotografias.Modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.registrousuariosfotografias.R;

import java.util.ArrayList;

public class AdapterUsuarios extends BaseAdapter {

    Context contexto;
    ArrayList<Usuario> listaUsuarios;

    public AdapterUsuarios(Context contexto, ArrayList<Usuario> listaUsuarios) {
        this.contexto = contexto;
        this.listaUsuarios = listaUsuarios;
    }

    @Override
    public int getCount() {
        return listaUsuarios.size();
    }

    @Override
    public Object getItem(int i) {
        return listaUsuarios.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_lista_usuarios, null);
        }
        TextView lblAutor = view.findViewById(R.id.lblAutor);
        TextView lblNombre = view.findViewById(R.id.lblDescripcion);
        TextView lblPassword = view.findViewById(R.id.lblPassword);

        lblAutor.setText(listaUsuarios.get(i).getNombreUsuario());
        lblNombre.setText(listaUsuarios.get(i).getNombre() + " " + listaUsuarios.get(i).getApellido());
        lblPassword.setText(listaUsuarios.get(i).getContrasenha());


        return view;
    }
}
