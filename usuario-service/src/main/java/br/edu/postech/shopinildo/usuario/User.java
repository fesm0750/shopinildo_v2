package br.edu.postech.shopinildo.usuario;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import br.edu.postech.shopinildo.usuario.config.Regexes;
import jakarta.validation.constraints.Email;

@Document(collection = "users")
public class User {

    @Id
	private String id;
	
	@Indexed(unique = true)
	@Email(regexp = Regexes.EMAIL_VALIDATION, message = "Invalid email address")
	private String email;

	private String password;

    private Role role;

	public User() {
	}

	public User(String id, String email, String password, Role role) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String username) {
		this.email = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}

