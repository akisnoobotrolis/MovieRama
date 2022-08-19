package com.example.demo.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data/* This is for getters, setters */
@AllArgsConstructor /* Constructor with all arguments*/
@NoArgsConstructor /* Constructor with no arguments*/
@Builder
@Table(
        uniqueConstraints={@UniqueConstraint(
                name="email_unique",
                columnNames = "email"),@UniqueConstraint(
                name="username_unique",
                columnNames = "username")}

)
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence"
            ,allocationSize = 1

    )
    @GeneratedValue(
            strategy =GenerationType.SEQUENCE,
            generator = "user_sequence")
    private Long id;

    private String username;

    private String firstName;


    private String lastName;

    private String email;

    private String password;





    private boolean enabled=false;

    private String role;

}
