package com.example.registrousuariosfotografias;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.registrousuariosfotografias.Modelo.RegistroUsuarios;
import com.example.registrousuariosfotografias.Modelo.Usuario;

public class act_registrar_usuarios extends AppCompatActivity {

    EditText txtUser_registrar, txtNombreUsuario, txtApellidoUsuario, txtPassword_registrar;

    Button btnRegistrarUsuario, btnActualizarUsuario, btnVerUsuarios;

    RegistroUsuarios registroUsuarios;

    private final int codigoUsuarioSeleccionado = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_registrar_usuarios);

        Bundle objetoEnviado = getIntent().getExtras();
        registroUsuarios = (RegistroUsuarios) objetoEnviado.getSerializable("objetoRegistro");

        txtUser_registrar = findViewById(R.id.txtUser_registrar);
        txtNombreUsuario = findViewById(R.id.txtNombreUsuario);
        txtApellidoUsuario = findViewById(R.id.txtApellidoUsuario);
        txtPassword_registrar = findViewById(R.id.txtPassword_registrar);

        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario);
        btnActualizarUsuario = findViewById(R.id.btnActualizarUsuario);
        btnVerUsuarios = findViewById(R.id.btnVerUsuarios);

        if (objetoEnviado != null) {
            if (objetoEnviado.getString("userName") != null || objetoEnviado.getString("password") != null) {
                txtUser_registrar.setText(objetoEnviado.getString("userName"));
                txtPassword_registrar.setText(objetoEnviado.getString("password"));
                Log.d("gg", "Llegan las claves");
            }

        }

        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Registrar", Toast.LENGTH_SHORT).show();
                agregar();
            }
        });

        btnActualizarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Actualizar", Toast.LENGTH_SHORT).show();
                actualizar();
            }
        });

        btnVerUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Ver lista", Toast.LENGTH_SHORT).show();
                verLista();
            }
        });

    }//Fin onCreate

    public void agregar() {
        if (verificarCampos()) {

            Usuario usuario = new Usuario(txtUser_registrar.getText().toString(), txtNombreUsuario.getText().toString(), txtApellidoUsuario.getText().toString(),
                    txtPassword_registrar.getText().toString());

            Toast.makeText(getApplicationContext(), registroUsuarios.agregarUsuario(usuario), Toast.LENGTH_SHORT).show();

            registroUsuarios.setUsuarioLog(usuario);

            Intent intent = new Intent(act_registrar_usuarios.this, act_agregar_fotos.class);

            Bundle bundle = new Bundle();
            bundle.putSerializable("objetoRegistro", registroUsuarios);

            intent.putExtras(bundle);

            startActivity(intent);

        } else {
            Toast.makeText(getApplicationContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizar() {

        if (verificarCampos()) {
            //guardar info actualizada
            int id = registroUsuarios.buscarPosicionUsuario(txtUser_registrar.getText().toString());

            Usuario usuario = new Usuario(txtUser_registrar.getText().toString(), txtNombreUsuario.getText().toString(), txtApellidoUsuario.getText().toString(),
                    txtPassword_registrar.getText().toString());

            Toast.makeText(getApplicationContext(), registroUsuarios.actualizarUsuario(usuario, id), Toast.LENGTH_SHORT).show();
            limpiar();
        } else {

            if (!txtUser_registrar.getText().toString().equalsIgnoreCase("")) {

                int posicion = registroUsuarios.buscarPosicionUsuario(txtUser_registrar.getText().toString());

                Usuario usuario = registroUsuarios.getUsuario(posicion);

                if (usuario != null) {
                    //cargar datos usuario
                    cargarDatos(usuario);
                } else {
                    Toast.makeText(getApplicationContext(), "El usuario no fue encontrado", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Coloque un nombre de usuario", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void verLista() {

        Intent intent = new Intent(act_registrar_usuarios.this, act_lista_usuarios.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("objetoRegistro", registroUsuarios);

        intent.putExtras(bundle);
        //intent.putParcelableArrayListExtra("listaUsuarios", registroUsuarios.getListaUsuarios());

        //startActivity(intent);
        startActivityForResult(intent, codigoUsuarioSeleccionado);

    }

    public boolean verificarCampos() {
        if (!txtUser_registrar.getText().toString().equalsIgnoreCase("") && !txtNombreUsuario.getText().toString().equalsIgnoreCase("")
                && !txtApellidoUsuario.getText().toString().equalsIgnoreCase("") && !txtPassword_registrar.getText().toString().equalsIgnoreCase("")) {
            return true;
        }
        return false;
    }

    public void limpiar() {
        txtUser_registrar.setText("");
        txtNombreUsuario.setText("");
        txtApellidoUsuario.setText("");
        txtPassword_registrar.setText("");
    }

    public void cargarDatos(Usuario usuario) {
        txtUser_registrar.setText(usuario.getNombreUsuario());
        txtNombreUsuario.setText(usuario.getNombre());
        txtApellidoUsuario.setText(usuario.getApellido());
        txtPassword_registrar.setText(usuario.getContrasenha());
    }

    protected void onActivityResult(int rqCode, int resCode, Intent data) {

        super.onActivityResult(rqCode, resCode, data);

        if (rqCode == codigoUsuarioSeleccionado) {

            if (resCode == RESULT_OK) {

                Bundle objetoRecivido = data.getExtras();
                Usuario usuario = (Usuario) objetoRecivido.getSerializable("usuarioSeleccionado");

                if (usuario != null) {
                    cargarDatos(usuario);
                } else {
                    Toast.makeText(getApplicationContext(), "Error al cargar usuario", Toast.LENGTH_SHORT).show();
                }

            }

        }

    }
}