package com.example.themoviedb001.retrofit;

import com.example.themoviedb001.movies_api.MoviesAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static MoviesAPI instance; // Cambiar a MoviesAPI

    public static MoviesAPI getInstance() {
        if (instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            instance = retrofit.create(MoviesAPI.class);
        }

        return instance;
    }
}
