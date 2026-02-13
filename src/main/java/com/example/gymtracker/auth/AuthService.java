package com.example.gymtracker.auth;

import com.example.gymtracker.config.JwtService;
import com.example.gymtracker.model.User;
import com.example.gymtracker.model.role;
import com.example.gymtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public AuthenticationResponse register(RegisterRequest request) {

        if(userRepository.existsByUsername(request.getUsername())){
            throw new IllegalArgumentException("User Found with Username");
        }
            var user= User.builder()
                    .email(request.getEmail())
                    .role(role.ROLE_CUSTOMER)
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            userRepository.save(user);
            return AuthenticationResponse.builder()
                    .token(jwtService.generateToken(user))
                    .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user =userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found :("));
        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .build();

    }


}

