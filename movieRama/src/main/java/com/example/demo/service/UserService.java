package com.example.demo.service;

import com.example.demo.DtoEntity.UserDto;
import com.example.demo.entity.Movie;
import com.example.demo.entity.User;
import com.example.demo.tokens.VerificationToken;
import com.example.demo.exceptionHandlers.UserAlreadyExistsException;


import java.util.List;
import java.util.Optional;


public interface UserService {
    public User findUserByEmail(String username);



    User NewUserAccount(UserDto userDto) throws  UserAlreadyExistsException;

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);


    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);


    List<User> findAllMovies();
}
