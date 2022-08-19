package com.example.demo.DtoEntity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
        @NotNull

        private String username;

        @NotNull
        private String firstName;

        @NotNull
        private String lastName;

        @NotNull
        private String email;

        private String password;


    }

