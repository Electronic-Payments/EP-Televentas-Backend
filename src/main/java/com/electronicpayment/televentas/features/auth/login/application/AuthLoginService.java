package com.electronicpayment.televentas.features.auth.login.application;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.electronicpayment.televentas.features.auth.login.domain.dto.LoginDto;
import com.electronicpayment.televentas.features.auth.login.domain.services.IAuthLoginService;
import com.electronicpayment.televentas.features.auth.register.domain.dto.DocumentTypeDto;
import com.electronicpayment.televentas.shared.dto.TokenResponseDto;
import com.electronicpayment.televentas.shared.entities.DocumentType;
import com.electronicpayment.televentas.shared.entities.User;
import com.electronicpayment.televentas.shared.repositories.IDocumentTypeRepository;
import com.electronicpayment.televentas.shared.repositories.IUserRepository;
import com.electronicpayment.televentas.shared.token.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthLoginService implements IAuthLoginService {

    private final IUserRepository userRepository;
    private final IDocumentTypeRepository documentTypeRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenResponseDto login(LoginDto request) {
        User user = this.userRepository.findByDocumentAndDocumentTypeAndStatus(request.getDocument(), new DocumentType(request.getDocumentTypeId()), Boolean.TRUE);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario o clave incorrecta");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
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

}
