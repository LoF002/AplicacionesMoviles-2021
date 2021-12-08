package com.example.practicalogin.Modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.practicalogin.R;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    Context context;
    ArrayList<Cliente> listaClienteAdap;

    public Adapter(Context context, ArrayList<Cliente> listaClienteAdap) {
        this.context = context;
        this.listaClienteAdap = listaClienteAdap;
    }

    @Override
    public int getCount() {
        return listaClienteAdap.size();
    }

    @Override
    public Object getItem(int i) {
        return listaClienteAdap.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (listaClienteAdap != null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_cliente, null);
        }//Fin

        TextView lbNombres = view.findViewById(R.id.lbNombres);
        TextView lbApellidos = view.findViewById(R.id.lbApellidos);
        TextView lbUsuario = view.findViewById(R.id.lbUsuario);
        TextView lbContrasena = view.findViewById(R.id.lbContrasena);

        lbNombres.setText(listaClienteAdap.get(i).getNombre());
        lbApellidos.setText(listaClienteAdap.get(i).getApellidos());
        lbUsuario.setText(listaClienteAdap.get(i).getUsuario());
        lbContrasena.setText(listaClienteAdap.get(i).getConstrasena());

        return view;
    }//Fin getView

}//Fin clase
