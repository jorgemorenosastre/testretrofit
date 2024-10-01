package com.example.themoviedb001.json_mapper;

import java.util.List;

public class MovieResponse {
    //URL https://api.themoviedb.org/3/movie/popular?api_key=13c85b2534d44565c1345efef9cc436c
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
