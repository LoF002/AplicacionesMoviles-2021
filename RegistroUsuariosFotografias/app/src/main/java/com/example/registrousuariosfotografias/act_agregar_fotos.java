package com.example.registrousuariosfotografias;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.registrousuariosfotografias.Modelo.Fotografia;
import com.example.registrousuariosfotografias.Modelo.RegistroFotografias;
import com.example.registrousuariosfotografias.Modelo.RegistroUsuarios;
import com.example.registrousuariosfotografias.Modelo.Usuario;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class act_agregar_fotos extends AppCompatActivity {

    TextView lblAutor_AgregarFoto;
    EditText txtDescripcion, txtAutor;
    Button btnAgregar, btnVolver_AgregarFotos, btnVerFotos;
    ImageView imgFoto;

    Usuario usuarioLog = null;
    RegistroUsuarios registroUsuarios;

    Fotografia foto;
    RegistroFotografias registroFotografias = new RegistroFotografias();

    Uri direccionFoto;

    private final int galeria = 1;
    private final int camara = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_agregar_fotos);

        lblAutor_AgregarFoto = findViewById(R.id.lblAutor_AgregarFoto);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnVolver_AgregarFotos = findViewById(R.id.btnVolver_AgregarFotos);
        btnVerFotos = findViewById(R.id.btnVerFotos);

        imgFoto = findViewById(R.id.imgFoto);

        Bundle objetoEnviado = getIntent().getExtras();
        registroUsuarios = (RegistroUsuarios) objetoEnviado.getSerializable("objetoRegistro");

        if (registroUsuarios != null) {

            usuarioLog = registroUsuarios.getUsuarioLog();
            lblAutor_AgregarFoto.setText(usuarioLog.getNombreUsuario());

        }

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarFotos();
            }
        });

        btnVerFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verFotos();
            }
        });

        btnVolver_AgregarFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver();
            }
        });

        imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Tab", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(act_agregar_fotos.this);
                alertDialog.setTitle("Selecciones la Fotografía");
                alertDialog.setMessage("¿Qué desea utilizar?");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setCancelable(false);

                alertDialog.setPositiveButton("Galería", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(intent.ACTION_GET_CONTENT);

                        startActivityForResult(intent.createChooser(intent, "Seleccione una fotografía"), galeria);

                    }
                });

                alertDialog.setNegativeButton("Cámara", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, camara);

                    }
                });

                alertDialog.show();

            }//Fin onClick

        });

    }//Fin onCreate

    public void onActivityResult(int rqCode, int resCode, Intent data) {

        super.onActivityResult(rqCode, resCode, data);

        if (resCode == RESULT_OK) {

            switch (rqCode) {
                case galeria:

                    direccionFoto = data.getData();
                    imgFoto.setImageURI(direccionFoto);
                    Toast.makeText(getApplicationContext(), "Imagen cargada", Toast.LENGTH_SHORT).show();

                    break;

                case camara:
                    if (data != null) {

                        Bitmap thumnail = (Bitmap) data.getExtras().get("data");
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        thumnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

                        File destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
                        FileOutputStream fileOutputStream;

                        try {

                            destination.createNewFile();
                            fileOutputStream = new FileOutputStream(destination);
                            fileOutputStream.write(bytes.toByteArray());
                            fileOutputStream.close();

                        } catch (FileNotFoundException e) {

                            e.printStackTrace();

                        } catch (IOException ex) {

                            ex.printStackTrace();

                        }

                        imgFoto.setImageBitmap(thumnail);
                        direccionFoto = (Uri) data.getData();
                        Toast.makeText(getApplicationContext(), "Fotografía capturada", Toast.LENGTH_SHORT).show();

                    }
                    break;
            }

        } else {
            Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
        }

    }

    public void agregarFotos() {

        if (!txtDescripcion.getText().toString().isEmpty()/* && direccionFoto != null*/) {

            foto = new Fotografia(lblAutor_AgregarFoto.getText().toString(), txtDescripcion.getText().toString(), direccionFoto);

            Toast.makeText(getApplicationContext(), registroFotografias.agregarFotografia(foto), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "Rellene los campos", Toast.LENGTH_SHORT).show();
        }

    }

    public void verFotos() {

        Intent intent = new Intent(act_agregar_fotos.this, act_lista_fotos.class);
        intent.putParcelableArrayListExtra("listaFotografias", registroFotografias.getListaFotos());

        startActivity(intent);

    }

    public void volver() {
        finish();
    }
}