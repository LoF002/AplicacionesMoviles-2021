package com.example.practicalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practicalogin.Modelo.Cliente;
import com.example.practicalogin.Modelo.RegistroCliente;

import java.util.ArrayList;

public class act_registro extends AppCompatActivity {

    EditText txtNombre, txtApellidos, txtUsuarioRegistro, txtConstrasenaRegistro;
    Button btnRegistrar, btnActualizar, btnVerLista;

    Cliente cliente;
    RegistroCliente registroCliente = new RegistroCliente();
    ArrayList<Cliente> lista;

    String mensaje="";
    int posicion=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_registro);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtUsuarioRegistro = findViewById(R.id.txtUsuarioRegistro);
        txtConstrasenaRegistro = findViewById(R.id.txtConstrasenaRegistro);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnVerLista = findViewById(R.id.btnVerLista);

        lista = getIntent().getParcelableArrayListExtra("listaClientes");

        //registroCliente.igualarListas(lista);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNombre.getText().toString().isEmpty() || txtApellidos.getText().toString().isEmpty() || txtUsuarioRegistro.getText().toString().isEmpty() || txtConstrasenaRegistro.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
                }//Fin if
                else{
                    cliente = new Cliente(txtUsuarioRegistro.getText().toString(), txtConstrasenaRegistro.getText().toString(), txtNombre.getText().toString(), txtApellidos.getText().toString(), "", null);
                    mensaje = registroCliente.agregarCliente(cliente);

                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();

                    limpiar();

                    Intent intent = new Intent(act_registro.this, act_login.class);
                    intent.putParcelableArrayListExtra("listaClientes", registroCliente.devolverLista());
                    startActivity(intent);
                }//Fin else
            }//Fin onClick
        });//Fin btnRegistrar

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNombre.getText().toString().isEmpty() || txtApellidos.getText().toString().isEmpty() || txtUsuarioRegistro.getText().toString().isEmpty() || txtConstrasenaRegistro.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
                }//Fin if
                else{
                    cliente = new Cliente(txtUsuarioRegistro.getText().toString(), txtConstrasenaRegistro.getText().toString(), txtNombre.getText().toString(), txtApellidos.getText().toString(), "", null);
                    posicion = registroCliente.buscarPosicion(cliente.getUsuario());

                    mensaje = registroCliente.actualizarCliente(cliente, posicion);

                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();

                    limpiar();
                }//Fin else
            }//Fin onClick
        });//Fin btnActualizar

        btnVerLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(act_registro.this, act_listaClientes.class);
                intent.putParcelableArrayListExtra("listaClientes", registroCliente.devolverLista());
                startActivity(intent);
            }//Fin on Click
        });//Fin btnVerLista

    }//Fin onCreate

    public void limpiar(){
        txtNombre.setText("");
        txtApellidos.setText("");
        txtUsuarioRegistro.setText("");
        txtConstrasenaRegistro.setText("");
    }//Fin limpiar

}//Fin clase