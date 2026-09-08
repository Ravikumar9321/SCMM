package com.supplyManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.supplyManagement.Dao.UserDAO;
import com.supplyManagement.Dto.ResponseStructure;
import com.supplyManagement.Entity.User;
import com.supplyManagement.Exception.NoRecordFoundException;

@Service
public class UserService {

	@Autowired
	private UserDAO userdao;

	public ResponseEntity<ResponseStructure<List<User>>> getAllUser() {
		ResponseStructure<List<User>> response = new ResponseStructure<List<User>>();
		List<User> list = userdao.getAllUser();
		if (list.size() >= 1) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Retrieved ");
			response.setData(list);
		} else
			throw new NoRecordFoundException("User details not found");

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	public ResponseEntity<ResponseStructure<User>> createuser(User user) {
		ResponseStructure<User> response = new ResponseStructure<>();
		User user1 = userdao.createuser(user);

		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Created Successfully ");
		response.setData(user1);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

}
