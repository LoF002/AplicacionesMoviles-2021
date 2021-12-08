package com.example.practicalogin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.practicalogin.Modelo.Cliente;
import com.example.practicalogin.Modelo.RegistroCliente;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class act_agregarFotos extends AppCompatActivity {

    EditText txtUsuarioAgregar, txtDescripcion;
    ImageView imgFoto;
    Button btnAgregar, btnVerListaFotos, btnVolverLogin;

    Cliente cliente;
    RegistroCliente registroCliente = new RegistroCliente();
    ArrayList<Cliente> lista;

    String mensaje="", usuario = "", contrasena="", nombres="", apellidos="", descripcion="";
    int posicion = 0;

    Uri fotoTemp;

    private final int Galeria = 1;
    private final int Camara = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_agregar_fotos);

        txtUsuarioAgregar = findViewById(R.id.txtUsuarioAgregar);
        txtDescripcion = findViewById(R.id.txtDescripcion);

        imgFoto = findViewById(R.id.imgFoto);

        btnAgregar = findViewById(R.id.btnAgregar);
        btnVerListaFotos = findViewById(R.id.btnVerListaFotos);
        btnVolverLogin = findViewById(R.id.btnVolverLogin);

        lista = getIntent().getParcelableArrayListExtra("listaClientes");

        imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(act_agregarFotos.this);
                alertDialog.setTitle("Seleccione la imagen de usuario");
                alertDialog.setMessage("Que desea utilizar");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setCancelable(false);

                alertDialog.setPositiveButton("Galeria", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Seleccionar foto"),Galeria);
                    }//fin onclick galeria
                });
                alertDialog.setNegativeButton("Camara", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,Camara);
                    }//fin onclick camara
                });
                alertDialog.show();
            }//Fin onClick
        });//Fin imgFoto

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtUsuarioAgregar.getText().toString().isEmpty() || txtDescripcion.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
                }//Fin if
                else{
                    usuario = txtUsuarioAgregar.getText().toString();
                    posicion = registroCliente.buscarUsuario(lista, usuario);

                    if (posicion!=-1){
                        nombres = lista.get(posicion).getNombre();
                        apellidos = lista.get(posicion).getApellidos();
                        contrasena = lista.get(posicion).getConstrasena();
                        descripcion = txtDescripcion.getText().toString();

                        cliente = new Cliente(usuario, contrasena, nombres, apellidos, descripcion, fotoTemp);
                        mensaje = registroCliente.agregarFoto(lista, cliente, posicion);
                        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                        limpiar();
                    }//Fin if
                    else{
                        Toast.makeText(getApplicationContext(), "Usuario invalido", Toast.LENGTH_SHORT).show();
                        limpiar();
                    }//Fin else
                }//Fin else
            }//Fin onClick
        });//Fin btnAgregar

        btnVerListaFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(act_agregarFotos.this, act_listaFotos.class);
                intent.putParcelableArrayListExtra("listaClientes", lista);
                startActivity(intent);
            }//Fin onClick
        });//Fin btnVerListaFotos

        btnVolverLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(act_agregarFotos.this, act_login.class);
                intent.putParcelableArrayListExtra("listaClientes", registroCliente.devolverLista());
                startActivity(intent);
            }//Fin onClick
        });//Fin btnVolverLogin

    }//Fin onCreate

    public void onActivityResult(int rqCode, int resCode, Intent data) {
        super.onActivityResult(rqCode, resCode, data);
        if(resCode == RESULT_OK){
            switch(rqCode){
                case Galeria:
                    fotoTemp = data.getData();
                    imgFoto.setImageURI(fotoTemp);
                    Toast.makeText(getApplicationContext(), "cargada correctamente", Toast.LENGTH_SHORT).show();
                    break;
                case Camara:
                    if(data != null){
                        Bitmap thumbail = (Bitmap)data.getExtras().get("data");
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        thumbail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                        File destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis()+".jpg");
                        FileOutputStream fo;
                        try {
                            destination.createNewFile();
                            fo = new FileOutputStream(destination);
                            fo.write(bytes.toByteArray());
                            fo.close();
                        }//Fin try
                        catch (FileNotFoundException e){
                            e.printStackTrace();
                        }//Fin catch
                        catch (IOException ex){
                            ex.printStackTrace();
                        }//fin catch
                        imgFoto.setImageBitmap(thumbail);
                        fotoTemp = data.getData();
                        Toast.makeText(getApplicationContext(), "foto correctamente", Toast.LENGTH_SHORT).show();
                    }//fin if
                    break;
            }//Fin switch
        }//Fin if
        else {
            Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_SHORT).show();
        }//Fin else
    }//Fin metodo onActivityResult

    public void limpiar(){
        txtUsuarioAgregar.setText("");
        txtDescripcion.setText("");
        //imgFoto.setImageIcon(android.R.drawable.ic_menu_gallery);
    }//Fin metodo

}//Fin clase