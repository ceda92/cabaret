package com.cabaret;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@SpringBootApplication

public class CabaretApplication {

	public static void main(String[] args) {

		SpringApplication.run(CabaretApplication.class, args);
	}


}
