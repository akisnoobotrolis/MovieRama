package com.example.demo.service;

import com.example.demo.DtoEntity.UserDto;
import com.example.demo.entity.Movie;
import com.example.demo.tokens.PasswordResetToken;
import com.example.demo.entity.User;
import com.example.demo.tokens.VerificationToken;
import com.example.demo.exceptionHandlers.UserAlreadyExistsException;
import com.example.demo.repository.PasswordResetTokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
public class UserServiceImpl implements UserService {




    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;




    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);

    }

    @Override
    public void saveVerificationTokenForUser(String token, User user){
        VerificationToken verificationToken=new VerificationToken(user,token);
        verificationTokenRepository.save(verificationToken);



    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken=verificationTokenRepository.findByToken(token);
        if (verificationToken==null){
            return "invalid";
        }
        User user=verificationToken.getUser();
        Calendar cal=Calendar.getInstance();
        if (verificationToken.getExpirationTime().getTime()-cal.getTime().getTime()<=0){
            verificationTokenRepository.delete(verificationToken);
            userRepository.delete(user);
            return "expired";

        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken=verificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken= new PasswordResetToken(user,token);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken=passwordResetTokenRepository.findByToken(token);
        if (passwordResetToken==null){
            return "invalid";
        }
        User user=passwordResetToken.getUser();
        Calendar cal=Calendar.getInstance();
        if (passwordResetToken.getExpirationTime().getTime()-cal.getTime().getTime()<=0){
            passwordResetTokenRepository.delete(passwordResetToken);
            return "expired";

        }

        return "valid";
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

    }

    @Override
    public List<User> findAllMovies() {
        return userRepository.findAll();
    }


    @Transactional
    @Override
    public User NewUserAccount(UserDto userDto) throws UserAlreadyExistsException {

        if (userRepository.findUserByEmail(userDto.getEmail()) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setRole("USER");
        userRepository.save(user);
        return  user;
    }
}
