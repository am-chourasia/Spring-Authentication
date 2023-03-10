package com.tekion.UserAuthentication.auth;

import com.tekion.UserAuthentication.auth.jwt.JwtService;
import com.tekion.UserAuthentication.dto.AuthenticationRequestDto;
import com.tekion.UserAuthentication.response.AuthenticationResponseDto;
import com.tekion.UserAuthentication.response.RegisterRequestDto;
import com.tekion.UserAuthentication.datasource.model.Role;
import com.tekion.UserAuthentication.datasource.model.User;
import com.tekion.UserAuthentication.datasource.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto register(RegisterRequestDto request) throws AuthenticationException {
        User user = User.builder().firstName(request.firstName()).lastName(request.lastName())
                        .email(request.email()).password(passwordEncoder.encode(request.password()))
                        .role(Role.USER).build();

        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder().token(jwt).build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                ));
        User user = userRepository.findByEmail(request.email()).orElseThrow();
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder().token(jwt).build();
    }
}
