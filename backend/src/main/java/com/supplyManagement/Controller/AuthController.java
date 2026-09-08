package com.supplyManagement.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplyManagement.Dto.AuthRequest;
import com.supplyManagement.Dto.AuthResponse;
import com.supplyManagement.Entity.User;
import com.supplyManagement.Repository.UserRepository;
import com.supplyManagement.Service.UserService;
import com.supplyManagement.Utilities.JwUtil;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication related APIs")
@CrossOrigin(origins = "http://localhost:3000")

public class AuthController {
	@Autowired
	private UserRepository repository;
	@Autowired
	private UserService service;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwUtil jwtUtil;
	@PostMapping("/register")
	 public ResponseEntity<AuthResponse> registerUser(@RequestBody AuthRequest request) {
        if (repository.findByEmail(request.email()).isPresent()) {
            return new ResponseEntity<>(new AuthResponse("User already exists", null), HttpStatus.CONFLICT);
        }

        service.createuser(User.builder()
                .email(request.email())
                .password(request.password()) 
                .build());

        return new ResponseEntity<>(new AuthResponse("Registered successfully", null), HttpStatus.CREATED);
    }

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest request) {
		Optional<User> userOptional = repository.findByEmail(request.email());

		if (userOptional.isEmpty()) {
			return new ResponseEntity<>(new AuthResponse("User not registered", null), HttpStatus.UNAUTHORIZED);
		}

		User user = userOptional.get();

		if (!passwordEncoder.matches(request.password(), user.getPassword())) {
			return new ResponseEntity<>(new AuthResponse("Invalid password", null), HttpStatus.UNAUTHORIZED);
		}

		String token = jwtUtil.generateToken(request.email());

		return ResponseEntity.ok(new AuthResponse("Login successful", token));
	}
}