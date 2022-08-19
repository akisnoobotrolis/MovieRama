package com.example.demo.service;

import com.example.demo.DtoEntity.MovieDto;
import com.example.demo.entity.Movie;
import com.example.demo.exceptionHandlers.MovieDoesNotExistException;

import java.util.List;
import java.util.Optional;



public interface MovieService {


    String addMovie(MovieDto movieDto);

    Movie updateMovie(Long movieId, MovieDto movieDto) throws Exception, MovieDoesNotExistException;

    Optional<Movie> findById(Long id);

    List<MovieDto> findAllMovies();

    List<Movie> sortMoviesByLikes();

    List<Movie> sortMoviesByDislikes();

    List<Movie> sortMoviesByDate();



    List<MovieDto> findAllMoviesByUsername(String user);

    List<MovieDto> findAllMoviesByLikes();

    List<MovieDto> findAllMoviesByDislikes();

    Movie findByTitle(String title);

    List<MovieDto> findAllMoviesByDate();
}
