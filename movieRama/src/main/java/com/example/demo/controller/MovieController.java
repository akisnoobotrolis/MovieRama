package com.example.demo.controller;


import com.example.demo.DtoEntity.MovieDto;


import com.example.demo.repository.UserRepository;
import com.example.demo.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {




    @Autowired
    private MovieService movieService;

    @PostMapping("/movie/addMovie")
    public String addMovie(@RequestBody MovieDto movieDto)
    {
        return movieService.addMovie(movieDto);
    }

    @GetMapping(value = "/movie/findAllMovies")
    public List<MovieDto> findAllMovies(){
        return movieService.findAllMovies();
    }

    @GetMapping(value = "/movie/findAllMoviesByUser")
    public List<MovieDto> findAllMoviesByUser(@RequestBody String username){
        return movieService.findAllMoviesByUsername(username);

    }
    @GetMapping(value = "/movie/findAllMoviesByLikes")
    public List<MovieDto> findAllMoviesByLikes(){
        return movieService.findAllMoviesByLikes();

    }
    @GetMapping(value = "/movie/findAllMoviesByDislikes")
    public List<MovieDto> findAllMoviesByDislikes(){
        return movieService.findAllMoviesByDislikes();

    }
    @GetMapping(value = "/movie/findAllMoviesByDate")
    public List<MovieDto> findAllMoviesByDate(){
        return movieService.findAllMoviesByDate();

    }

}


