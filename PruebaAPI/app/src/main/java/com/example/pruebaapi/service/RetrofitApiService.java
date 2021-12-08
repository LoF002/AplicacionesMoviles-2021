package com.example.pruebaapi.service;

import com.example.pruebaapi.modelo.FechaRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApiService {

    @GET("fecha")
    Call<FechaRespuesta> obtenerListaFecha();

}//Fin RetrofitApiService
