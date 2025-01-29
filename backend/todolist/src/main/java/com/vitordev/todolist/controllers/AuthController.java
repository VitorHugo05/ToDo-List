package com.vitordev.todolist.controllers;

import com.vitordev.todolist.domain.user.User;
import com.vitordev.todolist.domain.user.dto.AuthenticationDTO;
import com.vitordev.todolist.domain.user.dto.LoginResponseDTO;
import com.vitordev.todolist.domain.user.dto.RegisterDTO;
import com.vitordev.todolist.infra.security.TokenService;
import com.vitordev.todolist.repositories.UserRepository;
import com.vitordev.todolist.services.exception.AccessDeniedException;
import com.vitordev.todolist.services.exception.DataAlreadyExistException;
import com.vitordev.todolist.services.exception.MissingFieldException;
import com.vitordev.todolist.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data){
        try {
            if(data.getEmail() == null || data.getPassword() == null){
                throw new MissingFieldException("Missing email or password");
            }

            var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (BadCredentialsException e) {
            throw new AccessDeniedException("Invalid credentials");
        } catch (UsernameNotFoundException e) {
            throw new ObjectNotFoundException("User not found");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDTO data){
        if(userRepository.findByEmail(data.getEmail()) != null){
            throw new DataAlreadyExistException("Email already registered");
        }

        if (data.getPassword() == null) {
            throw new MissingFieldException("The field 'password' it is mandatory.");
        }

        if (data.getUsername() == null) {
            throw new MissingFieldException("The field 'username' it is mandatory.");
        }

        if (data.getEmail() == null) {
            throw new MissingFieldException("The field 'email' it is mandatory.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(null, data.getEmail(), data.getUsername(), encryptedPassword);

        userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}