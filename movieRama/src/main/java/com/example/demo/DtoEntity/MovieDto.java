package com.example.demo.DtoEntity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {



    private String title;
    private String description;
    private String username;


    private LocalDate publicationDate;
    private Integer numberOfLikes;
    private Integer numberOfDislikes;



}
