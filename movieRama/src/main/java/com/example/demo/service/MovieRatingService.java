package com.example.demo.service;


import com.example.demo.DtoEntity.MovieRatingDto;
import com.example.demo.entity.MovieRating;
import com.example.demo.exceptionHandlers.*;

import javax.transaction.Transactional;


public interface MovieRatingService {




    MovieRating updateMovieRating(Long movieRatingId, MovieRating movieRatingDto)throws MovieDoesNotExistException;
    @Transactional
    void   rateMovie(MovieRatingDto movieRatingdto) throws Exception, SameVoterAndCreatorException, SameVoteException, MovieDoesNotExistException;
}
