package com.example.demo.entity;


import lombok.*;


import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor /* Constructor with all arguments*/
@NoArgsConstructor /* Constructor with no arguments*/
@Builder
public class MovieRating {
    @Id
    @SequenceGenerator(
            name = "rating_sequence",
            sequenceName = "rating_sequence"
            ,allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rating_sequence")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id",referencedColumnName = "id")
    private Movie movie;
    private boolean liked;
    private boolean disliked;



}
