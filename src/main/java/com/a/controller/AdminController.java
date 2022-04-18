package com.a.controller;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.a.assembler.ItemModelAssembler;
import com.a.assembler.UserModelAssembler;
import com.a.entity.Item;
import com.a.entity.User;
import com.a.exception.ItemNotFoundException;
import com.a.exception.UserNotFoundException;
import com.a.repository.ItemRepository;
import com.a.repository.UserRepository;

@RestController
public class AdminController {
	
	private final UserRepository repository;
	private UserModelAssembler assembler;
    
    public AdminController(UserRepository repository, UserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }
	
    /*
     * User object is id, fname, lname, username, password
     */
	@PostMapping("/admin/visits")
    public ResponseEntity<?> newUser(@RequestBody User newUser) {
		EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));
		
        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }
	
	/*
     * User object is id, fname, lname, username, password
     */
	@PostMapping("/admin/stats")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> json) {
		User user = repository.findById(json.get("username")).orElseThrow(() -> new UserNotFoundException(json.get("username"))) ;
		if(user.getPassword().equals(json.get("password"))) {
			
			EntityModel<?> entityModel = assembler.toModel(user);
			return ResponseEntity 
	                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
	                .body(entityModel);
		} else {
			return new ResponseEntity<Exception>(new UserNotFoundException(user.getUsername()), HttpStatus.UNAUTHORIZED);
		}
    }
}
