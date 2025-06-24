package dev.sulaaa.otpservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="otp_data")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String otp;
    private LocalDateTime createdTime;

    public Token() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "token{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", otp='" + otp + '\'' +
                ", duration=" + createdTime +
                '}';
    }
}
