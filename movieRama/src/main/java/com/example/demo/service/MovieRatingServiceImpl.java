package com.example.demo.service;


import com.example.demo.DtoEntity.MovieDto;
import com.example.demo.DtoEntity.MovieRatingDto;
import com.example.demo.entity.Movie;
import com.example.demo.entity.MovieRating;
import com.example.demo.exceptionHandlers.MovieDoesNotExistException;
import com.example.demo.exceptionHandlers.SameVoteException;
import com.example.demo.exceptionHandlers.SameVoterAndCreatorException;
import com.example.demo.repository.MovieRatingRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class MovieRatingServiceImpl implements MovieRatingService {


    @Autowired
    MovieService movieService;



    @Autowired
    MovieRatingRepository movieRatingRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    MovieRepository movieRepository;



    @Override
    @Transactional
    public void  rateMovie(MovieRatingDto movieRatingdto) throws Exception, SameVoterAndCreatorException, SameVoteException, MovieDoesNotExistException {

        MovieRating movieRating = new MovieRating();


        Movie optionalMovie =  movieService.findByTitle(movieRatingdto.getMovie());

        if(optionalMovie!=null){
            if(movieRatingdto.getUserId() == optionalMovie.getUser().getId()){
                throw new SameVoterAndCreatorException("The user cannot vote for this movie");
            }
            else{

                MovieRating tempMovieRating = movieRatingRepository.findByUserAndMovie(optionalMovie.getUser(), optionalMovie);
                MovieDto movieDto =new MovieDto();
                movieDto.setUsername(optionalMovie.getUser().getUsername());
                movieDto.setPublicationDate(optionalMovie.getPublicationDate());
                movieDto.setTitle(optionalMovie.getTitle());
                movieDto.setNumberOfDislikes(optionalMovie.getNumberOfDislikes());
                movieDto.setNumberOfLikes(optionalMovie.getNumberOfLikes());

                if(tempMovieRating == null){
                    movieRating.setUser(userRepository.findByUsername(movieDto.getUsername()));
                    movieRating.setMovie(movieRepository.findByTitle(movieDto.getTitle()));
                    movieRating.setLiked(movieRatingdto.isLiked());
                    movieRating.setDisliked(movieRatingdto.isDisliked());

                    if(movieRatingdto.isLiked()){
                        movieDto.setNumberOfLikes(movieDto.getNumberOfLikes()+1);
                    }
                    else if (movieRatingdto.isDisliked()){
                        movieDto.setNumberOfDislikes(movieDto.getNumberOfDislikes()+1);
                    }

                    try{
                        movieService.updateMovie(optionalMovie.getId(),movieDto);
                        movieRatingRepository.save(movieRating);
                    }catch (MovieDoesNotExistException e){
                        throw new Exception("Something went wrong");
                    }
                }
                else{
                    if (movieRatingdto.isLiked()== tempMovieRating.isLiked() &&  movieRatingdto.isLiked()){
                        throw new SameVoteException("You can not like a movie twice");
                    } else if (movieRatingdto.isDisliked()== tempMovieRating.isDisliked() &&  movieRatingdto.isDisliked()) {
                        throw new SameVoteException("You can not like a movie twice");
                    } else{
                        movieRating.setUser(userRepository.findById(movieRatingdto.getUserId()).get());
                        movieRating.setMovie(movieRepository.findByTitle(movieRatingdto.getMovie()));
                        movieRating.setDisliked(movieRatingdto.isDisliked());
                        movieRating.setLiked(movieRatingdto.isLiked());

                        if(movieRatingdto.isLiked()){
                            movieDto.setNumberOfLikes(movieDto.getNumberOfLikes()+1);
                            movieDto.setNumberOfDislikes(movieDto.getNumberOfDislikes()-1);
                        }
                        else if (movieRatingdto.isDisliked()){
                            movieDto.setNumberOfLikes(movieDto.getNumberOfLikes()-1);
                            movieDto.setNumberOfDislikes(movieDto.getNumberOfDislikes()+1);
                        }

                        try{
                            movieService.updateMovie(optionalMovie.getId(), movieDto);
                            updateMovieRating(tempMovieRating.getId(),movieRating);

                        }catch (PersistenceException e){
                            throw new Exception("Something went wrong");
                        } catch (MovieDoesNotExistException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }
        }
        else {
            throw new MovieDoesNotExistException("There is no such movie");
        }

    }




    @Override
    public MovieRating updateMovieRating(Long movieRatingId, MovieRating movieRatingDto) throws MovieDoesNotExistException {
        Optional<MovieRating> optionalMovieRating = movieRatingRepository.findById(movieRatingId);
        MovieRating movieRating;

        if(optionalMovieRating.isPresent()){
            movieRating = optionalMovieRating.get();
            movieRating.setDisliked(movieRatingDto.isDisliked());
            movieRating.setLiked(movieRatingDto.isLiked());
        }
        else{
            throw new MovieDoesNotExistException("MovieRating does not exists");
        }
        return movieRatingRepository.save(movieRating);

    }
}












