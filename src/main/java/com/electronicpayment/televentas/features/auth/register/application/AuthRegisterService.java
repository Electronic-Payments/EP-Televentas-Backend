package com.electronicpayment.televentas.features.auth.register.application;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.electronicpayment.televentas.features.auth.register.domain.dto.RegisterUserDto;
import com.electronicpayment.televentas.features.auth.register.domain.services.IAuthRegisterService;
import com.electronicpayment.televentas.shared.dto.TokenResponseDto;
import com.electronicpayment.televentas.shared.entities.User;
import com.electronicpayment.televentas.shared.repositories.IUserRepository;
import com.electronicpayment.televentas.shared.token.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthRegisterService implements IAuthRegisterService {

    private final IUserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenResponseDto register(RegisterUserDto request) {
        User user = new User(
            request.getDocument(),
            new BCryptPasswordEncoder().encode(request.getPassword()),
            request.getDocumentTypeId()
        );

        this.userRepository.save(user);

        String accessToken = this.jwtTokenProvider.generateToken(user);
        
        return new TokenResponseDto(accessToken);
    }
}
