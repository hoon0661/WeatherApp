package com.example.weatherapp.services;

import com.example.weatherapp.dto.EditRequestDto;
import com.example.weatherapp.dto.SignupRequestDto;
import com.example.weatherapp.models.User;
import com.example.weatherapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();

        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()) {
            throw new IllegalArgumentException("Username already exists.");
        }

        String email = requestDto.getEmail();
        found = userRepository.findByEmail(email);
        if (found.isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }

        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password, email);
        userRepository.save(user);
    }

    @Transactional
    public void editUser(EditRequestDto requestDto) {
        Long userId = requestDto.getId();
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User does not exist."));
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();
        user.update(password, email);
    }
}
