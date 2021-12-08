package com.example.registroestudiantes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.registroestudiantes.Modelo.Adapter;
import com.example.registroestudiantes.Modelo.Estudiante;

import java.util.ArrayList;

public class act_lista extends AppCompatActivity {

    ListView listaEst;
    ArrayList<Estudiante> lista;
    Adapter adapterP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_lista);

        listaEst = findViewById(R.id.listaEst);
        Bundle bundle = getIntent().getExtras();
        lista = bundle.getParcelableArrayList("miLista");
        lista = getIntent().getParcelableArrayListExtra("miLista");
        adapterP = new Adapter(getApplicationContext(),lista);
        listaEst.setAdapter(adapterP);

    }//Fin metodo onCreate

}//Fin clase