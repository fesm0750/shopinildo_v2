package br.edu.postech.shopinildo.usuario.dto;

import br.edu.postech.shopinildo.usuario.User;

public record UserResponse(String id, String email) {

    public UserResponse(User user) {
        this(user.getId(), user.getEmail());
    }
    
}

