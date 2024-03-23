package br.edu.postech.shopinildo.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestParam String email, @RequestParam String password) {
        var response = new UserResponse(userService.login(email, password));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/password/{userId}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long userId, @RequestParam String newPassword) {
        userService.updatePassword(userId, newPassword);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update/email/{userId}")
    public ResponseEntity<Void> updateEmail(@PathVariable Long userId, @RequestParam String newEmail) {
        userService.updateEmail(userId, newEmail);
        return ResponseEntity.noContent().build();
    }
}

