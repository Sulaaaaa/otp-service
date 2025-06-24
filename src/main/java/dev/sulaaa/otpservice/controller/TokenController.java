package dev.sulaaa.otpservice.controller;

import dev.sulaaa.otpservice.request.OtpValidationRequest;
import dev.sulaaa.otpservice.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private TokenService tokenService;

//    private static final Logger LOG =

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String email){

        System.err.println("Controller looking good");
        String output = tokenService.otpSender(email);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateOtp(@RequestBody OtpValidationRequest request){
        System.err.println("Incoming validation "+ request);
        String output = tokenService.otpValidation(request.getEmail(), request.getToken());
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
