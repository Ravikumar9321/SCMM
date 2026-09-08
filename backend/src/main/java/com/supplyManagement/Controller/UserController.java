package com.supplyManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplyManagement.Dto.ResponseStructure;
import com.supplyManagement.Entity.User;
import com.supplyManagement.Service.UserService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "User related APIs")
@Hidden
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<User>>> getAllUser() {
		return service.getAllUser();
	}

}
