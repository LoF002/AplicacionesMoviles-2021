package com.example.registrousuariosfotografias;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.registrousuariosfotografias.Modelo.AdapterFotografias;
import com.example.registrousuariosfotografias.Modelo.Fotografia;

import java.util.ArrayList;

public class act_lista_fotos extends AppCompatActivity {

    ListView listViewFotografias;
    Button btnVolver_ListaFotos;

    Fotografia foto;
    ArrayList<Fotografia> listaFotografias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_lista_fotos);

        listaFotografias = getIntent().getParcelableArrayListExtra("listaFotografias");

        listViewFotografias = findViewById(R.id.listaFotografias);
        btnVolver_ListaFotos = findViewById(R.id.btnVolver_ListaFotos);

        if (!listaFotografias.isEmpty()) {
            AdapterFotografias adapterFotografias = new AdapterFotografias(getApplicationContext(), listaFotografias);
            listViewFotografias.setAdapter(adapterFotografias);
        }

        btnVolver_ListaFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }//Fin onCreate
}