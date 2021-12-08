package com.example.registroestudiantes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.registroestudiantes.Modelo.Adapter;
import com.example.registroestudiantes.Modelo.Estudiante;
import com.example.registroestudiantes.Modelo.Registro;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class act_Registro extends AppCompatActivity {

    EditText txtNombre, txtCarnet, txtCarrera, txtTelefono;
    Button btnAgregar, btnModificar, btnEliminar, btnBuscar, btnLlamar;

    Estudiante estudiante;
    Registro registro=new Registro();

    String mensaje="";
    int posicion=0;

    ArrayAdapter adapter;
    ListView listaE;
    Adapter adapterp;

    Uri fotoTemp;
    ImageView imgUser;
    private final int Galeria = 1;
    private final int Camara = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_registro);

        txtNombre = findViewById(R.id.txtNombre);
        txtCarnet = findViewById(R.id.txtCarnet);
        txtCarrera = findViewById(R.id.txtCarrera);
        txtTelefono = findViewById(R.id.txtTelefono);

        btnAgregar = findViewById(R.id.btnAgregar);
        btnModificar = findViewById(R.id.btnModificar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnLlamar = findViewById(R.id.btnLlamar);

        listaE = findViewById(R.id.listaE);

        imgUser = findViewById(R.id.imgUser);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNombre.getText().toString().isEmpty() || txtCarnet.getText().toString().isEmpty() || txtCarrera.getText().toString().isEmpty() || txtTelefono.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingrese todos los datos",Toast.LENGTH_SHORT).show();
                }else{
                    estudiante = new Estudiante(txtNombre.getText().toString(), txtCarnet.getText().toString(), txtCarrera.getText().toString(), txtTelefono.getText().toString(), fotoTemp);
                    mensaje = registro.agregarEstudiante(estudiante);
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                    limpiar();

                    //adapter = new ArrayAdapter(act_Registro.this, android.R.layout.simple_list_item_1, registro.devolverLista());
                    adapterp = new Adapter(getApplicationContext(), registro.devolverLista());
                    listaE.setAdapter(adapterp);

                }//fin else
            }//fin onClick
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNombre.getText().toString().isEmpty() || txtCarnet.getText().toString().isEmpty() || txtCarrera.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingrese todos los datos",Toast.LENGTH_SHORT).show();
                }else{
                    estudiante = new Estudiante(txtNombre.getText().toString(), txtCarnet.getText().toString(), txtCarrera.getText().toString(), txtTelefono.getText().toString(), fotoTemp);
                    posicion = registro.buscarPosicion(estudiante.getCarnet());
                    mensaje = registro.modificarEstudiante(estudiante, posicion);
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                    limpiar();
                }//fin else
            }//fin onClick
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtCarnet.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingrese el carnet",Toast.LENGTH_SHORT).show();
                }else{
                    posicion = registro.buscarPosicion(txtCarnet.getText().toString());
                    mensaje = registro.eliminarEstudiante(posicion);
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                    limpiar();
                }//fin else
            }//fin onClick
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                if(txtCarnet.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingrese el carnet",Toast.LENGTH_SHORT).show();
                }else{
                    posicion = registro.buscarPosicion(txtCarnet.getText().toString());
                    mensaje = registro.getInformacionEstudiante(posicion);
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();

                    estudiante = registro.devolverEstudiante(posicion);
                    if(estudiante==null){
                        Toast.makeText(getApplicationContext(),"No existe",Toast.LENGTH_SHORT).show();
                    }else{
                        txtNombre.setText(estudiante.getNombre());
                        txtCarrera.setText(estudiante.getCarrera());
                    }
                }//fin else
                */

                Intent intent = new Intent(act_Registro.this,act_lista.class);
                Bundle bundle =new Bundle();
                bundle.putParcelableArrayList("miLista",registro.devolverLista());
                intent.putParcelableArrayListExtra("miLista",registro.devolverLista());
                startActivity(intent);

            }//fin onClick
        });

        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(act_Registro.this);
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
            }//fin onclick
        });

        listaE.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                estudiante = registro.devolverEstudiante(posicion);
                txtNombre.setText(estudiante.getNombre());
                txtCarnet.setText(estudiante.getCarnet());
                txtCarrera.setText(estudiante.getCarrera());
                txtTelefono.setText(estudiante.getTelefono());
            }//Fin onItemClick
        });

        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtTelefono.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingrese el telefono",Toast.LENGTH_SHORT).show();
                }else{
                    int numero = Integer.parseInt(txtTelefono.getText().toString());
                    Intent intentLlamar = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+numero));
                    startActivity(intentLlamar);
                }//Fin else
            }//Fin onclick
        });

    }//Fin onCreate

    public void onActivityResult(int rqCode, int resCode, Intent data) {
        super.onActivityResult(rqCode, resCode, data);
        if(resCode == RESULT_OK){
            switch(rqCode){
                case Galeria:
                    fotoTemp = data.getData();
                    imgUser.setImageURI(fotoTemp);
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
                        imgUser.setImageBitmap(thumbail);
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
        txtNombre.setText("");
        txtCarnet.setText("");
        txtCarrera.setText("");
        txtTelefono.setText("");
        imgUser.setImageURI(null);
    }

}//Fin clase