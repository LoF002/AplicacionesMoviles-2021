package com.example.practicalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.practicalogin.Modelo.AdapterFoto;
import com.example.practicalogin.Modelo.Cliente;

import java.util.ArrayList;

public class act_listaFotos extends AppCompatActivity {

    ListView listaFotos;
    Button btnVolverAgregar;

    ArrayList<Cliente> lista;
    AdapterFoto adapterFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_lista_fotos);

        listaFotos = findViewById(R.id.listaFotos);
        btnVolverAgregar = findViewById(R.id.btnVolverAgregar);

        lista = getIntent().getParcelableArrayListExtra("listaClientes");
        adapterFoto = new AdapterFoto(getApplicationContext(), lista);

        listaFotos.setAdapter(adapterFoto);

        btnVolverAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(act_listaFotos.this, act_agregarFotos.class);
                startActivity(intent);
            }//Fin onClick
        });//Fin btnVolverAgregar

    }//Fin onCreate

}//Fin clase