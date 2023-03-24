package com.castgroup.backend.service;

import org.springframework.http.ResponseEntity;

import com.castgroup.backend.dto.UserDto;

public interface UserService {
	
	ResponseEntity<String> login(UserDto userDto);
	
	ResponseEntity<String> logoff();

}
