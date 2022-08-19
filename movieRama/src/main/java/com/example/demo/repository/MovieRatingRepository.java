package com.example.demo.repository;

import com.example.demo.entity.Movie;
import com.example.demo.entity.MovieRating;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRatingRepository extends JpaRepository<MovieRating,Long> {

    MovieRating findByUserAndMovie(User user, Movie movie);

}
