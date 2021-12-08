package com.example.primersaludo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class act_Saludo extends AppCompatActivity {

    TextView lbl_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_saludo);

        lbl_nombre = findViewById(R.id.lbl_nombe);

        //recibir info del otro activity
        Bundle bundle = this.getIntent().getExtras();

        //se asigna el valor recibido a un lbl de aca
        lbl_nombre.setText("Hola " + bundle.getString("NOMBRE"));

    }//Fin onCreate
}//Fin class