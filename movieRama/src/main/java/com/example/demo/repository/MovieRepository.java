package com.example.demo.repository;


import com.example.demo.entity.Movie;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {


    Movie findByTitle(String title);
    List<Movie> findAllByUser(User user);
    List<Movie> findAllByOrderByNumberOfLikesDesc();
    List<Movie> findAllByOrderByNumberOfDislikesDesc();

    List<Movie> findAllByOrderByPublicationDateDesc();



}
