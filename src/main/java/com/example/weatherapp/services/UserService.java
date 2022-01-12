package com.example.weatherapp.services;

import com.example.weatherapp.dto.EditRequestDto;
import com.example.weatherapp.dto.SignupRequestDto;
import com.example.weatherapp.exception.ApiRequestException;
import com.example.weatherapp.models.User;
import com.example.weatherapp.repositories.UserRepository;
import com.example.weatherapp.utils.UserInfoValidator;
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
        UserInfoValidator.validateUsername(username);
        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()) {
            throw new ApiRequestException("Username already exists.");
        }

        String email = requestDto.getEmail();
        UserInfoValidator.validateEmail(email);
        found = userRepository.findByEmail(email);
        if (found.isPresent()) {
            throw new ApiRequestException("Email already exists.");
        }
        UserInfoValidator.validatePassword(requestDto.getPassword());
        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password, email);
        userRepository.save(user);
    }

    @Transactional
    public void editUser(EditRequestDto requestDto) {
        Long userId = requestDto.getId();
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiRequestException("User does not exist."));
        String email = requestDto.getEmail();
        UserInfoValidator.validateEmail(email);
        UserInfoValidator.validatePassword(requestDto.getPassword());
        String password = passwordEncoder.encode(requestDto.getPassword());
        user.update(password, email);
    }
}
