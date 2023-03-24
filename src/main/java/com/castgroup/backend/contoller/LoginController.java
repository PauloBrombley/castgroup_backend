package com.castgroup.backend.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castgroup.backend.dto.UserDto;
import com.castgroup.backend.service.UserService;

@Controller
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/castgroup")
public class LoginController {
   
	private final UserService userService;
	
    public LoginController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/login")
    public UserDto login(@RequestBody UserDto userDto) {
       return userService.login(userDto);
    }

    @GetMapping("/logoff")
    public ResponseEntity<String> logoff() {
       return userService.logoff();
    }
}
