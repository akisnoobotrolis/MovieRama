package com.example.demo.controller;


import com.example.demo.DtoEntity.MovieRatingDto;
import com.example.demo.entity.MovieRating;
import com.example.demo.exceptionHandlers.*;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.MovieRatingService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class MovieRatingController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MovieRatingService movieRatingService;


    @PostMapping("/vote")
    public void voteForMovie(@RequestBody MovieRatingDto movieRatingDto) throws SameActionByUserException, MovieDoesNotExistException, SameActionException, Exception, SameVoteException, SameVoterAndCreatorException {
        log.info(String.valueOf(movieRatingDto));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        log.info(username);
        movieRatingDto.setUserId(userRepository.findByUsername(username).getId());
        log.info(String.valueOf(movieRatingDto));

        movieRatingService.rateMovie(movieRatingDto);
    }
}
