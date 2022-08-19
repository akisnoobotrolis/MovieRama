package com.example.demo.service;

import com.example.demo.DtoEntity.MovieDto;
import com.example.demo.entity.Movie;
import com.example.demo.entity.User;
import com.example.demo.exceptionHandlers.MovieDoesNotExistException;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    AuthenticationManager authenticationManager;




    @Autowired
    private MovieRepository movieRepository;





    @Autowired
    private UserRepository userRepository;




    @Transactional
    @Override
    public String addMovie(MovieDto movieDto) {


        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setPublicationDate(LocalDate.now());
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        User user=userRepository.findByUsername(username);
        if (user.isEnabled()){
            movie.setUser(user);
            movie.setNumberOfLikes(0);
            movie.setNumberOfDislikes(0);
            movieRepository.save(movie);
            return "Movie added";


        }
        return "movie didnt added cause user is not registered";
    }

    @Override
    public Movie updateMovie(Long movieId, MovieDto movieDto) throws MovieDoesNotExistException {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Movie movie;

        if(optionalMovie.isPresent()){
            movie = optionalMovie.get();

            movie.setTitle(movieDto.getTitle());
            movie.setDescription(movieDto.getDescription());
            movie.setNumberOfLikes(movieDto.getNumberOfLikes());
            movie.setNumberOfDislikes(movieDto.getNumberOfDislikes());
        }
        else{
            throw new MovieDoesNotExistException("Movie does not exists");
        }
        return movieRepository.save(movie);

    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<MovieDto> findAllMovies() {
        List<Movie> movieList=movieRepository.findAll();
        ArrayList<MovieDto> movieDtoList = new ArrayList<MovieDto>();
        for(Movie movie: movieList){
            MovieDto movieDto=new MovieDto();
            movieDto.setTitle(movie.getTitle());
            movieDto.setDescription(movie.getDescription());
            movieDto.setNumberOfLikes(movie.getNumberOfLikes());
            movieDto.setNumberOfDislikes(movie.getNumberOfDislikes());
            movieDto.setUsername(movie.getUser().getUsername());
            movieDto.setPublicationDate(movie.getPublicationDate());
            movieDtoList.add(movieDto);
        }
        return movieDtoList;
    }

    @Override
    public List<Movie> sortMoviesByLikes() {
        List<Movie> movieList =  movieRepository.findAll();
        movieList.sort(Comparator.comparing(Movie::getNumberOfLikes));
        return movieList;

    }

    @Override
    public List<Movie> sortMoviesByDislikes() {
        List<Movie> movieList =  movieRepository.findAll();
        movieList.sort(Comparator.comparing(Movie::getNumberOfDislikes));
        return movieList;
    }

    @Override
    public List<Movie> sortMoviesByDate() {

        List<Movie> movieList =  movieRepository.findAll();
        movieList.sort(Comparator.comparing(Movie::getPublicationDate));
        return movieList;
    }



    @Override
    public List<MovieDto> findAllMoviesByUsername(String username) {
        List<Movie> movieList=movieRepository.findAllByUser(userRepository.findByUsername(username));
        List<MovieDto> movieDtoList= movieToMovie(movieList);
        return movieDtoList;
    }

    @Override
    public List<MovieDto> findAllMoviesByLikes() {
        List<Movie> movieList = movieRepository.findAllByOrderByNumberOfLikesDesc();
        List<MovieDto> movieDtoList= movieToMovie(movieList);
        return movieDtoList;
    }

    @Override
    public List<MovieDto> findAllMoviesByDislikes() {
            List<Movie> movieList=movieRepository.findAllByOrderByNumberOfDislikesDesc();
            List<MovieDto> movieDtoList= movieToMovie(movieList);
            return movieDtoList;
    }

    @Override
    public Movie findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<MovieDto> findAllMoviesByDate() {
        List<Movie> movieList = movieRepository.findAllByOrderByPublicationDateDesc();
        List<MovieDto> movieDtoList= movieToMovie(movieList);
        return movieDtoList;
    }

    public List<MovieDto> movieToMovie(List<Movie> movieList) {
        List<MovieDto> movieDtoList = new ArrayList<MovieDto>();
        for (Movie movie : movieList) {
            MovieDto movieDto = new MovieDto();
            movieDto.setTitle(movie.getTitle());
            movieDto.setDescription(movie.getDescription());
            movieDto.setNumberOfLikes(movie.getNumberOfLikes());
            movieDto.setUsername(movie.getUser().getUsername());
            movieDto.setNumberOfDislikes(movie.getNumberOfDislikes());
            movieDto.setPublicationDate(movie.getPublicationDate());
            movieDtoList.add(movieDto);
        }
        return movieDtoList;
    }

}
