package com.example.primersaludo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class act_principal extends AppCompatActivity {

    //declarar componentes
    EditText txtNombre;
    Button btnAceptar;
    String nombre="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_principal);

        //asignar valor de la interfaz
        txtNombre = findViewById(R.id.txtNombre);
        btnAceptar = findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = txtNombre.getText().toString();
                Toast.makeText(getApplicationContext(),"Hola " + nombre,Toast.LENGTH_SHORT).show();

                //Referencia a la otra activity que se quiere mostrar
                Intent intent = new Intent(act_principal.this, act_Saludo.class);

                //de esta forma se asigna el valor de una variable de este activity para usar en el otro
                Bundle bundle = new Bundle();
                bundle.putString("NOMBRE", nombre);

                //aqui se envia al activity
                intent.putExtras(bundle);

                startActivity(intent);

            }//fin onClick
        });

    }//fin onCreate
}//fin main