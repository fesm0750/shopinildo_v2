package br.edu.postech.shopinildo.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.postech.shopinildo.usuario.dto.LoginRequest;
import br.edu.postech.shopinildo.usuario.dto.UserRequest;
import br.edu.postech.shopinildo.usuario.dto.UserResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/customer")
    public ResponseEntity<UserResponse> registerNewCustomer(@RequestBody UserRequest userRequestDTO) {
        var response = new UserResponse(userService.registerNewCustomer(userRequestDTO));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<UserResponse> registerNewAdmin(@RequestBody UserRequest userRequestDTO) {
        var response = new UserResponse(userService.registerNewAdmin(userRequestDTO));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/login")
    public UserResponse login(@RequestBody LoginRequest loginRequest) {
        return new UserResponse(userService.login(loginRequest));
    }

    @PatchMapping("/update/password/{userId}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long userId, @RequestBody String newPassword) {
        userService.updatePassword(userId, newPassword);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update/email/{userId}")
    public ResponseEntity<Void> updateEmail(@PathVariable Long userId, @RequestBody String newEmail) {
        userService.updateEmail(userId, newEmail);
        return ResponseEntity.noContent().build();
    }
}

