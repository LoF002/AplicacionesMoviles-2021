package com.example.ejemplobd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOutOfMemoryException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static SQLiteDatabase db; //se crea la bd
    private static final String NOMBRE_DB = "MultiSQlite"; //se le asigna un nombre
    private final String TABLA_OPERACION = "operacion"; //se define el nombre de la tabla

    EditText txtFactor1, txtFactor2;
    TextView identificador;
    Button btnGuardar, btnModificar, btnEliminar,btnActualizar;
    ListView listView;
    Float factor1, factor2;
    int idOperacion;

    ArrayList<Operacion> lista;
    ArrayAdapter<Operacion> adapter;
    String f1, f2, id;

    //creacion de variable con consulta para crear tabla si no existe
    public static final String tbOperacion = "CREATE TABLE IF NOT EXISTS operacion( id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "factor1 FLOAT NOT NULL,"+"factor2 FLOAT NOT NULL,"+"resultado FLOAT NOT NULL);";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        abrirDataBase();

        setContentView(R.layout.activity_main);

        identificador = findViewById(R.id.id);
        txtFactor1 = findViewById(R.id.txtNumero1);
        txtFactor2 = findViewById(R.id.txtNumero2);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnModificar = findViewById(R.id.btnModificar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnActualizar = findViewById(R.id.btnActualizar);
        listView = findViewById(R.id.lista);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f1 = txtFactor1.getText().toString();
                f2 = txtFactor2.getText().toString();

                if(!TextUtils.isEmpty(f1) && !TextUtils.isEmpty(f2)){
                    factor1 = Float.parseFloat(f1);
                    factor2 = Float.parseFloat(f2);

                    if(addOperacion(factor1, factor2)){
                        Toast.makeText(getApplicationContext(), "Agregado correctamente", Toast.LENGTH_SHORT).show();
                        limpiar();
                    }//Fin if
                    else {
                        Toast.makeText(getApplicationContext(), "Error al agregar", Toast.LENGTH_SHORT).show();
                    }//Fin else

                }//Fin if
            }//Fin onClick
        });//Fin btnGuardar

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f1 = txtFactor1.getText().toString();
                f2 = txtFactor2.getText().toString();
                id = identificador.getText().toString();

                if(!TextUtils.isEmpty(f1) && !TextUtils.isEmpty(f2)){
                    factor1 = Float.parseFloat(f1);
                    factor2 = Float.parseFloat(f2);
                    idOperacion = Integer.parseInt(id);

                    if(updateOperacion(idOperacion,factor1, factor2)){
                        Toast.makeText(getApplicationContext(), "Modificado correctamente", Toast.LENGTH_SHORT).show();
                        limpiar();
                    }//Fin if
                    else {
                        Toast.makeText(getApplicationContext(), "Error al modificar", Toast.LENGTH_SHORT).show();
                    }//Fin else

                }//Fin if
            }//Fin onClick
        });//Fin btnModificar

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idOperacion = Integer.parseInt(identificador.getText().toString());
                if(removeOperacion(idOperacion)){
                    Toast.makeText(getApplicationContext(), "Eliminada correctamente", Toast.LENGTH_SHORT).show();
                    limpiar();
                }//Fin if
                else {
                    Toast.makeText(getApplicationContext(), "Error al eliminar", Toast.LENGTH_SHORT).show();
                }//Fin else
            }//Fin onClick
        });//Fin btnEliminar

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista = getOperacion();
                adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, lista);
                listView.setAdapter(adapter);
            }//Fin onClick
        });//Fin btnActualizar

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Operacion operacion = lista.get(i);
                txtFactor1.setText(String.valueOf(operacion.getFactor1()));
                txtFactor2.setText(String.valueOf(operacion.getFactor2()));
                identificador.setText(String.valueOf(operacion.getId()));
            }//Fin onItemClick
        });//Fin listView

    }//Fin onCreate

    public void abrirDataBase(){
        try {
            db = openOrCreateDatabase(NOMBRE_DB, MODE_PRIVATE, null);
            db.execSQL(tbOperacion);
        }//Fin try
        catch (SQLiteOutOfMemoryException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Error al crear la base de datos", Toast.LENGTH_SHORT).show();
        }//Fin catch
    }//Fin metodo

    private boolean addOperacion(Float factor1, Float factor2){
        ContentValues content = new ContentValues();
        content.put("factor1", factor1);
        content.put("factor2", factor2);
        Float resultado = factor1*factor2;
        content.put("resultado", resultado);
        return db.insert(TABLA_OPERACION, null, content)>0;
    }//Fin metodo

    private ArrayList<Operacion> getOperacion(){
        Cursor cursor = db.query(TABLA_OPERACION, new String[]{"id", "factor1", "factor2", "resultado"},
                null, null, null, null, "id desc");
        cursor.moveToFirst();
        ArrayList<Operacion> listaOp = new ArrayList<>();

        while (!cursor.isAfterLast()){
            Operacion operacion = new Operacion();
            operacion.setId(cursor.getInt(0));
            operacion.setFactor1(cursor.getInt(1));
            operacion.setFactor2(cursor.getInt(2));
            operacion.setResultado(cursor.getInt(3));
            listaOp.add(operacion);
            cursor.moveToNext();
        }//Fin while

        cursor.close();
        return listaOp;

    }//Fin metodo

    private boolean removeOperacion(int id){
        return db.delete(TABLA_OPERACION, "id="+id, null)>0;
    }//Fin metodo

    private boolean updateOperacion(int id, float factor1, float factor2){
        ContentValues content = new ContentValues();
        content.put("factor1", factor1);
        content.put("factor2", factor2);
        Float resultado = factor1*factor2;
        content.put("resultado", resultado);
        return db.update(TABLA_OPERACION, content, "id="+id, null)>0;
    }//Fin metodo

    public void limpiar(){
        txtFactor1.setText("");
        txtFactor2.setText("");
        identificador.setText(String.valueOf(getOperacion().size()+1));
    }//Fin metodo

}//Fin clase