package com.example.themoviedb001;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themoviedb001.json_mapper.Movie;
import com.example.themoviedb001.json_mapper.MovieResponse;
import com.example.themoviedb001.movies_api.MoviesAPI;
import com.example.themoviedb001.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String API_KEY = "TU_API_KEY"; // Reemplaza con tu API Key
    private MoviesAPI api; // Cambiado de Movie a MoviesAPI
    private TextView textViewResults; // TextView para mostrar resultados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = RetrofitClient.getInstance(); // Inicializa correctamente la interfaz

        textViewResults = findViewById(R.id.textViewResults); // Inicializa el TextView

        Button buttonPopularMovies = findViewById(R.id.buttonPopularMovies);
        Button buttonSearchMovie = findViewById(R.id.buttonSearchMovie);
        Button buttonMovieDetails = findViewById(R.id.buttonMovieDetails);

        buttonPopularMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchPopularMovies();
            }
        });

        buttonSearchMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovie("titanic"); // Puedes cambiar esto a cualquier película
            }
        });

        buttonMovieDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMovieDetails(550); // ID de "Fight Club"
            }
        });
    }

    private void fetchPopularMovies() {
        Call<MovieResponse> call = api.getPopularMovies(API_KEY, "es-ES", 1);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    StringBuilder moviesList = new StringBuilder(); // Para almacenar títulos
                    for (Movie movie : response.body().getResults()) {
                        moviesList.append(movie.getTitle()).append("\n"); // Agregar títulos a la lista
                        Log.d(TAG, "Película Popular: " + movie.getTitle());
                    }
                    textViewResults.setText(moviesList.toString()); // Mostrar títulos en el TextView
                    Toast.makeText(MainActivity.this, "Películas populares cargadas", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "Fallo en la llamada: " + t.getMessage());
            }
        });
    }

    private void searchMovie(String query) {
        Call<MovieResponse> call = api.searchMovie(API_KEY, "es-ES", query, 1);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    StringBuilder moviesList = new StringBuilder(); // Para almacenar títulos
                    for (Movie movie : response.body().getResults()) {
                        moviesList.append(movie.getTitle()).append("\n"); // Agregar títulos a la lista
                        Log.d(TAG, "Resultado de búsqueda: " + movie.getTitle());
                    }
                    textViewResults.setText(moviesList.toString()); // Mostrar títulos en el TextView
                    Toast.makeText(MainActivity.this, "Búsqueda completada", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "Fallo en la llamada: " + t.getMessage());
            }
        });
    }

    private void getMovieDetails(int movieId) {
        Call<Movie> call = api.getMovieDetails(movieId, API_KEY, "es-ES");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Movie movie = response.body();
                    Log.d(TAG, "Detalles de la película: " + movie.getTitle() + " - " + movie.getOverview());
                    Toast.makeText(MainActivity.this, "Detalles de película cargados", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(TAG, "Fallo en la llamada: " + t.getMessage());
            }
        });
    }
}
