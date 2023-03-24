package com.castgroup.backend.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castgroup.backend.dto.UserDto;
import com.castgroup.backend.service.UserService;

@Controller
@RestController
@RequestMapping("/castgroup")
public class LoginController {
   
	private final UserService userService;
	
    public LoginController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
		System.out.println("login");
       return userService.login(userDto);
    }

    @GetMapping("/logoff")
    public ResponseEntity<String> logoff() {
    	System.out.println("logoff");
       return userService.logoff();
    }
}
