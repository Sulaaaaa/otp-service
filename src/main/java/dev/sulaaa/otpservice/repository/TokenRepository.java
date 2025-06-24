package dev.sulaaa.otpservice.repository;

import dev.sulaaa.otpservice.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByEmailIgnoreCase(String email);


}
