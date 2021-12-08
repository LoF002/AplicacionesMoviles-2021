package com.example.pruebaapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.pruebaapi.modelo.Fecha;
import com.example.pruebaapi.modelo.FechaRespuesta;
import com.example.pruebaapi.service.RetrofitApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "FECHAS";

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.mockachino.com/8e7694cd-0daa-4c/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iniciarValores();

    }//Fin onCreate

    public void iniciarValores(){

        RetrofitApiService service = retrofit.create(RetrofitApiService.class);
        Call<FechaRespuesta> fechaRespuestaCall = service.obtenerListaFecha();

        fechaRespuestaCall.enqueue(new Callback<FechaRespuesta>() {
            @Override
            public void onResponse(Call<FechaRespuesta> call, Response<FechaRespuesta> response) {
                if (response.isSuccessful()){
                    FechaRespuesta fechaRespuesta = response.body();
                    ArrayList<Fecha> listaFecha = new ArrayList<>();
                    listaFecha = fechaRespuesta.getResultados();

                    for (int i = 0; i < listaFecha.size(); i++) {
                        Fecha f = listaFecha.get(i);
                        Log.i(TAG, "Fecha: " + f.getActividad());
                    }//Fin for

                }//Fin if
                else{
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }//Fin else
            }//Fin onResponse

            @Override
            public void onFailure(Call<FechaRespuesta> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }//Fin onFailure
        });//Fin enqueue
    }//Fin iniciarValores

}//Fin MainActivity