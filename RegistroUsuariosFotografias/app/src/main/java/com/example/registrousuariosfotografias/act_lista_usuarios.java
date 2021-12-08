package com.example.registrousuariosfotografias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.registrousuariosfotografias.Modelo.AdapterUsuarios;
import com.example.registrousuariosfotografias.Modelo.RegistroUsuarios;
import com.example.registrousuariosfotografias.Modelo.Usuario;

import java.util.ArrayList;

public class act_lista_usuarios extends AppCompatActivity {

    ListView listViewUsuarios;
    Button btnVolver_ListUsuarios;

    ArrayList<Usuario> listaUsuarios;

    RegistroUsuarios registroUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_lista_usuarios);

        Bundle objetoEnviado = getIntent().getExtras();
        registroUsuarios = (RegistroUsuarios) objetoEnviado.getSerializable("objetoRegistro");

        listaUsuarios = registroUsuarios.getListaUsuarios();

        listViewUsuarios = findViewById(R.id.listaUsuarios);
        btnVolver_ListUsuarios = findViewById(R.id.btnVolver_ListUsuarios);

        if (!listaUsuarios.isEmpty()) {
            AdapterUsuarios adapterUsuarios = new AdapterUsuarios(getApplicationContext(), listaUsuarios);
            listViewUsuarios.setAdapter(adapterUsuarios);
        }

        btnVolver_ListUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        listViewUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Usuario usuario = registroUsuarios.getUsuario(posicion);

                Intent intent = new Intent();

                Bundle bundle = new Bundle();
                bundle.putSerializable("usuarioSeleccionado", usuario);
                intent.putExtras(bundle);

                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }//Fin onCreate
}