package com.br.NexusCRM.service.user;

import com.br.NexusCRM.entity.user.UserEntity;
import com.br.NexusCRM.exceptions.user.UserException;
import com.br.NexusCRM.repository.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceDetails {
    private final UserRepository userRepository;

    public UserServiceDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> userFound = userRepository.findByEmail(username);

        if (userFound.isEmpty()) {
            throw new UserException("User not found");
        }

        return userFound.get();
    }
}
