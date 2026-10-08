package com.br.NexusCRM.controller.user;

import com.br.NexusCRM.dto.requestDTO.user.UserRequestDTO;
import com.br.NexusCRM.dto.responseDTO.user.UserResponseDTO;
import com.br.NexusCRM.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDTO registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) throws Exception {
        return userService.registerUser(userRequestDTO);
    }
}
