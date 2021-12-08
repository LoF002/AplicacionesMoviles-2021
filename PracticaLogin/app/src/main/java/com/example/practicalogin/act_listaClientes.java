package com.example.practicalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.practicalogin.Modelo.Adapter;
import com.example.practicalogin.Modelo.Cliente;
import com.example.practicalogin.Modelo.RegistroCliente;

import java.util.ArrayList;

public class act_listaClientes extends AppCompatActivity {

    ListView listaClientes;
    Button btnVolverRegistro;

    ArrayList<Cliente> lista;
    Adapter adapter;

    //RegistroCliente registroCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_lista_clientes);

        listaClientes = findViewById(R.id.listaClientes);
        btnVolverRegistro = findViewById(R.id.btnVolverRegistro);

        lista = getIntent().getParcelableArrayListExtra("listaClientes");
        adapter = new Adapter(getApplicationContext(),lista);

        listaClientes.setAdapter(adapter);

        btnVolverRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(act_listaClientes.this, act_registro.class);
                //intent.putParcelableArrayListExtra("listaClientes", lista);
                startActivity(intent);
            }//Fin onClick
        });//Fin btnVolverRegistro

    }//Fin onCreate

}//Fin clase