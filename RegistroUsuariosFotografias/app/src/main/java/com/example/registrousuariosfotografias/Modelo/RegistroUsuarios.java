package com.example.registrousuariosfotografias.Modelo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOutOfMemoryException;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class RegistroUsuarios extends AppCompatActivity implements Serializable, Parcelable{

    private static SQLiteDatabase db;
    private static final String NOMBRE_BD = "MultiSQLite";
    private final String TABLA_USUARIO = "usuario";

    public static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "nombreUsuario STRING NOT NULL, " + "nombre STRING NOT NULL, " + "apellido STRING NOT NULL, " + "contrasenha STRING NOT NULL " +")";

    ArrayList<Usuario> listaUsuarios;

    Usuario usuarioLog;
    Fotografia foto;

    public RegistroUsuarios() {
        listaUsuarios = new ArrayList<>();

        abrirDataBase();

        /*Usuario usuario1 = new Usuario("rongon", "Ronald", "Chaves", "123");
        Usuario usuario2 = new Usuario("Sval14", "Sharon", "Valverde", "456");
        listaUsuarios.add(usuario1);
        listaUsuarios.add(usuario2);*/
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


    protected RegistroUsuarios(Parcel in) {
        listaUsuarios = in.createTypedArrayList(Usuario.CREATOR);
        usuarioLog = in.readParcelable(Usuario.class.getClassLoader());
    }
    public static final Creator<RegistroUsuarios> CREATOR = new Creator<RegistroUsuarios>() {
        @Override
        public RegistroUsuarios createFromParcel(Parcel in) {
            return new RegistroUsuarios(in);
        }

        @Override
        public RegistroUsuarios[] newArray(int size) {
            return new RegistroUsuarios[size];
        }
    };


    public int buscarPosicionUsuario(String userName) {

        int idUser = -1;

        String[] projection = {"id"};

        String selection = "nombreUsuario = ?";

        String[] selectionArgs = {userName};

        String sortOrder = "id DESC";

        Cursor cursor = db.query(
                TABLA_USUARIO,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        cursor.moveToNext();

        if(cursor != null){
            idUser = cursor.getInt(0);
        }

        /*if(listaUsuarios != null){
            if (userName != null) {
                for (int i = 0; i < listaUsuarios.size(); i++) {
                    if (listaUsuarios.get(i).getNombreUsuario().equalsIgnoreCase(userName)) {
                        return i;
                    }
                }
            }
        }else{
            return -2;
        }*/

        return idUser;
    }

    public Usuario getUsuario(int id) {

        Usuario usuario = null;

        String[] projection = {"id", "nombreUsuario", "nombre", "apellido", "contrasenha"};

        String selection = "id = ?";

        String[] selectionArgs = {id+""};

        String sortOrder = "id DESC";

        Cursor cursor = db.query(
                TABLA_USUARIO,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        cursor.moveToNext();

        if(cursor != null){
            usuario = new Usuario(String.valueOf(cursor.getInt(1)), String.valueOf(cursor.getInt(2)),
                    String.valueOf(cursor.getInt(3)), String.valueOf(cursor.getInt(4)));
        }

        return usuario;
    }

    public String agregarUsuario(Usuario usuario) {

        ContentValues content = new ContentValues();

        content.put("nombreUsuario", String.valueOf(usuario.getNombreUsuario()));
        content.put("nombre", String.valueOf(usuario.getNombre()));
        content.put("apellido", String.valueOf(usuario.getApellido()));
        content.put("contrasenha", String.valueOf(usuario.getContrasenha()));

        if(db.insert(TABLA_USUARIO, null, content) > 0){
            return "Usuario registrado correctamente";
        }

        /*if (usuario != null) {
            if (buscarPosicionUsuario(usuario.getNombreUsuario()) == -1) {
                if(db.addUsuario(usuario)){
                    listaUsuarios = db.getUsuarioDB();
                    return "Usuario registrado";
                }else{
                    return "Error al registrar el usuario";
                }
            }
        }*/

        return "El usuario ya se encuentra registrado";

    }

    public String actualizarUsuario(Usuario usuario, int id) {

        ContentValues content = new ContentValues();

        content.put("nombreUsuario", String.valueOf(usuario.getNombreUsuario()));
        content.put("nombre", String.valueOf(usuario.getNombre()));
        content.put("apellido", String.valueOf(usuario.getApellido()));
        content.put("contrasenha", String.valueOf(usuario.getContrasenha()));

        if(db.update(TABLA_USUARIO, content, "id = " + id, null) > 0){
            return "Actualizado correctamente";
        }

        /*if (usuario != null  && posicion != -1 && posicion != -2) {
            if(db.updateUsuario(posicion, usuario)){
                listaUsuarios = db.getUsuarioDB();
                return "Usuario actualizado";
            }else{
                return "Error al actualizar el usuario";
            }
            listaUsuarios.get(posicion).setNombreUsuario(usuario.getNombreUsuario());
            listaUsuarios.get(posicion).setNombre(usuario.getNombre());
            listaUsuarios.get(posicion).setApellido(usuario.getApellido());
            listaUsuarios.get(posicion).setContrasenha(usuario.getContrasenha());
        }*/

        return "Error al actualizar";
    }

    public ArrayList getListaUsuarios() {
        return listaUsuarios;
    }

    public Usuario getUsuarioLog() {
        return usuarioLog;
    }

    public void setUsuarioLog(Usuario usuarioLog) {
        this.usuarioLog = usuarioLog;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(listaUsuarios);
        parcel.writeParcelable(usuarioLog, i);
    }

}//Fin clase RegistroUsuarios
