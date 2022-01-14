package com.example.weatherapp.utils;

import com.example.weatherapp.models.User;
import com.example.weatherapp.repositories.UserRepository;
import com.example.weatherapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
public class Scheduler {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Scheduled(cron = "0 0 * * * *")
    @Transactional
    public void keepBasicUser() throws InterruptedException {
        User user = userRepository.findByUsername("username").orElseThrow(() -> new IllegalArgumentException("username does not exist."));
        String password = passwordEncoder.encode("password");
        String email = "username@email.com";
        user.update(password, email);
    }
}
