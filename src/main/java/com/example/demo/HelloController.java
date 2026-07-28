package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/")
	public String home()
	{
		return "Hello from Docker & Kubernetes";
	}
	
	@GetMapping("/time")
	public String getTime()
	{
		return "Current Time : " + LocalDateTime.now();
	}

}
