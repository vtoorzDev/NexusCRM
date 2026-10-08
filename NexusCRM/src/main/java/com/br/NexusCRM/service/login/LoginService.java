package com.br.NexusCRM.service.login;

import com.br.NexusCRM.dto.login.LoginRequestDTO;
import com.br.NexusCRM.dto.responseDTO.login.LoginResponseDTO;
import com.br.NexusCRM.entity.user.UserEntity;
import com.br.NexusCRM.exceptions.user.UserException;
import com.br.NexusCRM.repository.user.UserRepository;
import com.br.NexusCRM.service.jwt.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginService(UserRepository userRepository,
                        PasswordEncoder passwordEncoder,
                        JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Optional<UserEntity> user = userRepository.findByEmail(loginRequestDTO.getEmail());

        if (user.isEmpty()) {
            throw new UserException("User not found");
        }

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.get().getPassword())) {
            throw new RuntimeException("incorrect password");
        }

        String token = jwtService.generateToken(user.get().getEmail());

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken(token);

        return loginResponseDTO;
    }
}