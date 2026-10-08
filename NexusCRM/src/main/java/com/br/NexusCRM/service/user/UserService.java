package com.br.NexusCRM.service.user;


import com.br.NexusCRM.dto.requestDTO.user.UserRequestDTO;
import com.br.NexusCRM.dto.responseDTO.user.UserResponseDTO;
import com.br.NexusCRM.entity.user.UserEntity;
import com.br.NexusCRM.exceptions.user.UserException;
import com.br.NexusCRM.repository.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private UserResponseDTO transformResponse(UserEntity userEntity) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(userEntity.getId());
        userResponseDTO.setName(userEntity.getName());
        userResponseDTO.setEmail(userEntity.getEmail());
        userResponseDTO.setUserRole(userEntity.getUserRole());

        return userResponseDTO;
    }

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) throws Exception {
        boolean userFound = userRepository.existsByEmail(userRequestDTO.getEmail());

        if (userFound) {
            throw new UserException("Email already registered");
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setName(userRequestDTO.getName());
        userEntity.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        userEntity.setEmail(userRequestDTO.getEmail());
        userEntity.setUserRole(userRequestDTO.getUserRole());

        userRepository.save(userEntity);

        return transformResponse(userEntity);
    }
}
