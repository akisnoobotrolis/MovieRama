package com.example.demo.controller;


import com.example.demo.DtoEntity.PasswordDto;
import com.example.demo.DtoEntity.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VerificationTokenRepository;
import com.example.demo.tokens.VerificationToken;
import com.example.demo.event.RegistrationCompleteEvent;
import com.example.demo.exceptionHandlers.UserAlreadyExistsException;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class RegistrationController {


    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    UserRepository userRepository;




    @Autowired
    private ApplicationEventPublisher publisher;


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDto userDto, final HttpServletRequest request) throws UserAlreadyExistsException {
        User user= userService.NewUserAccount(userDto);

        publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request)));

        return "Success";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordDto passwordDto,HttpServletRequest request){
        User user=userService.findUserByEmail(passwordDto.getEmail());
        String url="";
        if (user!=null){
            String token= UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user,token);
            url= passwordResetTokenMail(user,applicationUrl(request),token);
        }
        return  url;
    }

    private String passwordResetTokenMail(User user, String applicationUrl, String token) {
        String url= applicationUrl+ "/savePassword?token="+token;


        log.info("Click the link to reset your password :{}",url);
        return url;
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token")String token,@RequestBody PasswordDto passwordDto){
        String result=userService.validatePasswordResetToken(token);
        if (!result.equalsIgnoreCase("valid")){
            return "Invalid Token";
        }
        Optional<User> user=userService.getUserByPasswordResetToken(token);
        if (user.isPresent()){
            userService.changePassword(user.get(),passwordDto.getNewPassword());
            return "Password reset Successfully";
        }
        else {
            return "invalid token";
        }


    }



    @GetMapping("/resendVerifyToken")
    public  String resendVerificationToken(@RequestParam("token" )String oldToken, HttpServletRequest request){
        VerificationToken verificationToken=userService.generateNewVerificationToken(oldToken);
        User user= verificationToken.getUser();
        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return  "verification link resent";
    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken)
    {
        String url= applicationUrl+ "/verifyRegistration?token="+verificationToken.getToken();




        log.info("Click the link to verify your account :{}",url);
    }


    @GetMapping("/verifyRegistration")
        public String verifyRegistration(@RequestParam("token") String token){
        String result=userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")){
            return "User verified";
        }
        else {

            return "User did not verify";
        }
        }
}
