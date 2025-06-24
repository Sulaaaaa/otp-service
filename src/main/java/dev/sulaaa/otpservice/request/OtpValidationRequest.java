package dev.sulaaa.otpservice.request;

public class OtpValidationRequest {
    private String email;
    private String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenRequest{" +
                "email='" + email + '\'' +
                ", otp='" + token + '\'' +
                '}';
    }
}
