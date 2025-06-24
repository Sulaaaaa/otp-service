package dev.sulaaa.otpservice.service;

public interface TokenService {
    String otpGenerator();
    String otpSender(String email);
    String otpValidation(String email, String token);

}
