package br.edu.postech.shopinildo.usuario.dto;

import br.edu.postech.shopinildo.usuario.Role;
import br.edu.postech.shopinildo.usuario.User;

public record UserResponse(String id, Role role) {
    public UserResponse(User user) {
        this(user.getId(), user.getRole());
    }
}

