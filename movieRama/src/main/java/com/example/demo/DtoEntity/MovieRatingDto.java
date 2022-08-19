package com.example.demo.DtoEntity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class
MovieRatingDto {


    private Long userId;
    @NotNull
    private String movie;
    private boolean liked;
    private boolean disliked;
}
