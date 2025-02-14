package com.electronicpayment.televentas.features.auth.application;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.electronicpayment.televentas.config.security.JwtTokenProvider;
import com.electronicpayment.televentas.features.auth.domain.dto.DocumentTypeDto;
import com.electronicpayment.televentas.features.auth.domain.dto.LoginDto;
import com.electronicpayment.televentas.features.auth.domain.dto.RegisterUserDto;
import com.electronicpayment.televentas.features.auth.domain.dto.TokenResponseDto;
import com.electronicpayment.televentas.features.auth.domain.services.IAuthService;
import com.electronicpayment.televentas.shared.entities.DocumentType;
import com.electronicpayment.televentas.shared.entities.User;
import com.electronicpayment.televentas.shared.repositories.IDocumentTypeRepository;
import com.electronicpayment.televentas.shared.repositories.IUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService {

    private final IUserRepository userRepository;
    private final IDocumentTypeRepository documentTypeRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public TokenResponseDto login(LoginDto request) {
        User user = this.userRepository.findByDocumentAndDocumentTypeAndStatus(request.getDocument(),
                new DocumentType(request.getDocumentTypeId()), Boolean.TRUE);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario o clave incorrecta");
        }

        if (!this.passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario o clave incorrecta");
        }

        String accessToken = this.jwtTokenProvider.generateToken(user);

        return new TokenResponseDto(accessToken);
    }

    @Override
    public List<DocumentTypeDto> listDocumentTypes() {
        List<DocumentType> documentTypes = this.documentTypeRepository.findAllByStatus(Boolean.TRUE);

        return documentTypes.stream().map(x -> new DocumentTypeDto(x.getId(), x.getName())).toList();
    }

    @Override
    public TokenResponseDto register(RegisterUserDto request) {
        User user = new User(
                request.getDocument(),
                this.passwordEncoder.encode(request.getPassword()),
                request.getDocumentTypeId());

        this.userRepository.save(user);

        String accessToken = this.jwtTokenProvider.generateToken(user);

        return new TokenResponseDto(accessToken);
    }

    @Override
    public void changePassword(String email, String newPassword) {
        User user = this.userRepository.findByEmail(email);

        user.setPassword(this.passwordEncoder.encode(newPassword));
        user.setPasswordChanged(Boolean.TRUE);

        this.userRepository.save(user);
    }

}
