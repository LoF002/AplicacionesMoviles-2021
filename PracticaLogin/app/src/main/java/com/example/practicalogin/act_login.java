package com.example.practicalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practicalogin.Modelo.Cliente;
import com.example.practicalogin.Modelo.RegistroCliente;

import java.util.ArrayList;

public class act_login extends AppCompatActivity {

    EditText txtUsuario, txtConstrasena;
    Button btnLogin, btnRegistro;

    RegistroCliente registroCliente = new RegistroCliente();
    ArrayList<Cliente> lista;

    String usuario="", contrasena="";
    int posicion=0;
    boolean login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_login);

        txtUsuario = findViewById(R.id.txtUsuarioLogin);
        txtConstrasena = findViewById(R.id.txtContrasenaLogin);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegistro = findViewById(R.id.btnRegistro);

        lista = getIntent().getParcelableArrayListExtra("listaClientes");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtUsuario.getText().toString().isEmpty() || txtConstrasena.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese todos los datos", Toast.LENGTH_LONG).show();
                }//Fin if
                else{
                    if (lista!=null){
                        usuario = txtUsuario.getText().toString();
                        contrasena = txtConstrasena.getText().toString();
                        posicion = registroCliente.buscarUsuario(lista, usuario);

                        login = registroCliente.verificarContrasena(lista, posicion, contrasena);

                        if(login){
                            Toast.makeText(getApplicationContext(), "Bienvenidx", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(act_login.this, act_agregarFotos.class);
                            intent.putParcelableArrayListExtra("listaClientes", lista);
                            startActivity(intent);
                        }//Fin if
                        else{
                            Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecta", Toast.LENGTH_LONG).show();
                            limpiar();
                        }//Fin else
                    }//Fin if
                    else {
                        Toast.makeText(getApplicationContext(), "Usuario no registrado", Toast.LENGTH_LONG).show();
                        limpiar();
                    }//Fin else

                }//Fin else
            }//Fin onClick
        });//Fin btnLogin

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(act_login.this, act_registro.class);
                startActivity(intent);
            }//Fin onClick
        });//Fin btnRegistro

    }//Fin onCreate

    public void limpiar(){
       this.txtUsuario.setText("");
       this.txtConstrasena.setText("");
    }//Fin metodo

}//Fin clase