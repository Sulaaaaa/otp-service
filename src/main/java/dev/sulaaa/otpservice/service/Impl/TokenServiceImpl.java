package dev.sulaaa.otpservice.service.Impl;

import dev.sulaaa.otpservice.model.Token;
import dev.sulaaa.otpservice.repository.TokenRepository;
import dev.sulaaa.otpservice.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public String otpGenerator() {
        System.err.println("start here");
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int digit = (int)(Math.random() * 10);
            number.append(digit);
        }
        return number.toString();
    }

    @Override
    public String otpSender(String email) {
        try {
            Optional<Token> optionalToken = tokenRepository.findByEmailIgnoreCase(email);

            String token1 = otpGenerator();

            Token token = new Token();

            if(optionalToken.isEmpty()){
                token.setOtp(token1);
                token.setEmail(email);
            }else {
                token = optionalToken.get();
                token.setOtp(token1);
            }
            token.setCreatedTime(LocalDateTime.now());

            tokenRepository.save(token);

            System.err.println("Before sending OTP");

            return mailSender(email, token1);

        } catch (Exception e) {
            System.err.println("Couldn't send OTP" + e.getMessage());
            return "Failed to send OTP";
        }
    }
    public String mailSender(String email, String token1){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tise.abiodun.alt@gmail.com");
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your one-time password (OTP) is: " + token1);

        mailSender.send(message);
        System.err.println("After sending OTP");
        return "OTP sent to " + email;
    }

    @Override
    public String otpValidation(String email, String token) {
        Optional<Token> optionalToken = tokenRepository.findByEmailIgnoreCase(email);

        if (email.isEmpty()){
            return "Email was not provided...please provide a email";
        }
        if (token.isEmpty()){
            return "Please provide a token";
        }
        if(token.length()<6){
            return "Token isn't up to 6 digits";
        }

        Token tokenValue = optionalToken.get();

        String otpEmail = tokenValue.getEmail();
        String otpToken = tokenValue.getOtp();
        LocalDateTime otpTime = tokenValue.getCreatedTime();

        if(Objects.equals(otpEmail, email) && Objects.equals(otpToken, token)){
            if(otpTime.plusMinutes(2).isAfter(LocalDateTime.now())) {
                return "Token has been validated";
            }
            else{
                return "Token has expired";
            }
        }
        else{
            return "Invalid Token";
        }


    }


}



