package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data/* This is for getters, setters */
@AllArgsConstructor /* Constructor with all arguments*/
@NoArgsConstructor /* Constructor with no arguments*/
@Builder
@Table(name = "tbl_movie",
        uniqueConstraints=@UniqueConstraint(
                name="title_unique",
                columnNames = "title")
)

public class Movie {

    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence"
            ,allocationSize = 1

    )
    @GeneratedValue(
            strategy =GenerationType.SEQUENCE,
            generator = "movie_sequence")
    private Long id;
    private String title;

    private String description;


    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)

    private LocalDate publicationDate;
    private Integer numberOfLikes;
    private Integer numberOfDislikes;

}

