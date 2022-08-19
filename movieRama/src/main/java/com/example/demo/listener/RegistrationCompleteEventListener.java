package com.example.demo.listener;


import com.example.demo.entity.User;
import com.example.demo.event.RegistrationCompleteEvent;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VerificationTokenRepository;
import com.example.demo.service.UserService;
import com.example.demo.tokens.VerificationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.UUID;



@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private UserService userService;
    @Autowired UserRepository userRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;



    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Create VERIFICATION for the user with link
        User user =event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token,user);




        //end mail to user
        String url= event.getApplicationUrl()+ "/verifyRegistration?token="+token;


        log.info("Click the link to verify your account :{}",url);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    VerificationToken verificationToken=verificationTokenRepository.findByToken(token);
                    @Override
                    public void run() {
                        Calendar cal=Calendar.getInstance();
                        if (verificationToken.getExpirationTime().getTime()-cal.getTime().getTime()<=0){
                            verificationTokenRepository.delete(verificationToken);
                            userRepository.delete(user);
                        }
                    }
                },
                660000
        );

    }
}