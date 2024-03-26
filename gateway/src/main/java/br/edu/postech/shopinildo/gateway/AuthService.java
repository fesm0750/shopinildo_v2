package br.edu.postech.shopinildo.gateway;

import org.springframework.stereotype.Service;

import br.edu.postech.shopinildo.gateway.config.JwtUtil;

@Service
public class AuthService {

    private final UserIntegration userIntegration;
    private final JwtUtil jwtUtil;

    public AuthService(UserIntegration userIntegration, JwtUtil jwtUtil) {
        this.userIntegration = userIntegration;
        this.jwtUtil = jwtUtil;
    }

    public String login(LoginRequest loginRequest) {
        UserResponse user = userIntegration.login(loginRequest);
        if (user == null) {
            throw new IllegalArgumentException("Usuário e/ou senha incorretos");
        }

        return jwtUtil.createToken(user.id(), user.role());
    }
}

