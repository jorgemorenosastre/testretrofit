package com.example.themoviedb001.movies_api;

import com.example.themoviedb001.json_mapper.Movie;
import com.example.themoviedb001.json_mapper.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesAPI {
    @GET("movie/popular?api_key=13c85b2534d44565c1345efef9cc436c")
    Call<MovieResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("search/movie?api_key=13c85b2534d44565c1345efef9cc436c")
    Call<MovieResponse> searchMovie(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String query,
            @Query("page") int page
    );

    @GET("movie/{movie_id}?api_key=13c85b2534d44565c1345efef9cc436c")
    Call<Movie> getMovieDetails(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
