package com.example.registrousuariosfotografias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.registrousuariosfotografias.Modelo.RegistroUsuarios;

public class act_login extends AppCompatActivity {

    EditText txtUser_login, txtPassword_login;
    TextView btnRegistrarse;
    Button btnLogin;

    RegistroUsuarios registroUsuarios = new RegistroUsuarios();

    Intent intentGeneral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_login);

        //registroUsuarios.abrirDataBase();

        txtUser_login = findViewById(R.id.txtUser_login);
        txtPassword_login = findViewById(R.id.txtPassword_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();

            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registrar();

            }
        });

    }//Fin onCreate

    public void login() {

        //Toast.makeText(getApplicationContext(), "Iniciar sesión", Toast.LENGTH_SHORT).show();
        if (!txtUser_login.getText().toString().equals("") && !txtPassword_login.getText().toString().equals("")) {

            int posicion = registroUsuarios.buscarPosicionUsuario(txtUser_login.getText().toString());

            if (posicion != -1) {

                if (txtPassword_login.getText().toString().equals(registroUsuarios.getUsuario(posicion).getContrasenha())) {

                    if (registroUsuarios.getUsuario(posicion) != null) {

                        registroUsuarios.setUsuarioLog(registroUsuarios.getUsuario(posicion));

                        intentGeneral = new Intent(act_login.this, act_agregar_fotos.class);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("objetoRegistro", registroUsuarios);

                        intentGeneral.putExtras(bundle);

                        startActivity(intentGeneral);

                    } else {
                        Toast.makeText(getApplicationContext(), "Usuario vacío", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "El usuario no está registrado", Toast.LENGTH_LONG).show();

                intentGeneral = new Intent(act_login.this, act_registrar_usuarios.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("objetoRegistro", registroUsuarios);

                if (!txtUser_login.getText().toString().equals("") || !txtPassword_login.getText().toString().equals("")) {
                    bundle.putString("userName", txtUser_login.getText().toString());
                    bundle.putString("password", txtPassword_login.getText().toString());
                }

                intentGeneral.putExtras(bundle);

                startActivity(intentGeneral);
            }

        } else {
            Toast.makeText(getApplicationContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
        }

    }//Fin Login

    public void registrar() {

        intentGeneral = new Intent(act_login.this, act_registrar_usuarios.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("objetoRegistro", registroUsuarios);

        intentGeneral.putExtras(bundle);

        startActivity(intentGeneral);

    }//Fin registrar

    /*protected void onActivityResult(int rqCode, int resCode, Intent data) {

        super.onActivityResult(rqCode, resCode, data);

        if(rqCode == 100){

            if(resCode == RESULT_OK){

            }

        }

    }*/
}