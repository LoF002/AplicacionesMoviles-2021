package com.example.registrousuariosfotografias.Modelo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOutOfMemoryException;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GestorDB  extends AppCompatActivity {

    private static SQLiteDatabase db;

    private static final String NOMBRE_BD = "MultiSQLite";
    private final String TABLA_USUARIO = "usuario";

    public static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "nombreUsuario STRING NOT NULL, " + "nombre STRING NOT NULL, " + "apellido STRING NOT NULL, " + "contrasenha STRING NOT NULL " +")";

    public GestorDB(){
        /*abrirDataBase();*/
    }

    public void abrirDataBase(){
        try {
            db = openOrCreateDatabase(NOMBRE_BD, MODE_PRIVATE, null);
            db.execSQL(CREATE_USER_TABLE);
        }catch (SQLiteOutOfMemoryException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error al cargar la base de datos", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean searchUsuario(String nombreUsuario){
        return false;
    }

    public boolean addUsuario(Usuario usuario){
        ContentValues content = new ContentValues();
        content.put("nombreUsuario", String.valueOf(usuario.getNombreUsuario()));
        content.put("nombre", String.valueOf(usuario.getNombre()));
        content.put("apellido", String.valueOf(usuario.getApellido()));
        content.put("contrasenha", String.valueOf(usuario.getContrasenha()));

        return db.insert(TABLA_USUARIO, null, content) > 0;
    }

    public ArrayList<Usuario> getUsuarioDB(){
        Cursor cursor = db.query(TABLA_USUARIO,
                new String []{"id", "nombreUsuario", "nombre", "apellido", "contrasenha"},
                null, null, null, null, "id desc");

        cursor.moveToFirst();

        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        while(!cursor.isAfterLast()){
            Usuario usuario = new Usuario();

            //usuario.setId(cursor.getInt(0));
            usuario.setNombreUsuario(String.valueOf(cursor.getInt(1)));
            usuario.setNombre(String.valueOf(cursor.getInt(2)));
            usuario.setApellido(String.valueOf(cursor.getInt(3)));
            usuario.setContrasenha(String.valueOf(cursor.getInt(4)));

            listaUsuarios.add(usuario);

            cursor.moveToNext();
        }

        cursor.close();
        return listaUsuarios;
    }//Fin getUsuarioDB

    public boolean removeUsuario(int id){
        return db.delete(TABLA_USUARIO, "id=" + id, null) > 0;
    }//Fin removeUsuario

    public boolean updateUsuario(int id, Usuario usuario){

        ContentValues content = new ContentValues();
        content.put("nombreUsuario", String.valueOf(usuario.getNombreUsuario()));
        content.put("nombre", String.valueOf(usuario.getNombre()));
        content.put("apellido", String.valueOf(usuario.getApellido()));
        content.put("contrasenha", String.valueOf(usuario.getContrasenha()));

        return db.update(TABLA_USUARIO, content, "id=" + id, null) > 0;
    }//Fin updateUsuario

}
