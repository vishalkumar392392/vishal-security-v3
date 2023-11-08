package com.vishal.security.vishalsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	@GetMapping("/message")
	public String getMessage() {
		return "Hello world";
	}

}
