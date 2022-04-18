package com.a.controller;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.text.ParseException;
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
import com.a.entity.VisitEvent;
import com.a.exception.ItemNotFoundException;
import com.a.exception.UserNotFoundException;
import com.a.model.MonthSales;
import com.a.repository.ItemRepository;
import com.a.repository.UserRepository;
import com.a.repository.VisitEventRepository;
import com.a.service.AdminService;

@RestController
public class AdminController {
	
	private final AdminService adminService;
	private final VisitEventRepository visitEventRepository;
    
    public AdminController(AdminService adminService, VisitEventRepository visitEventRepository) {
        this.adminService = adminService;
        this.visitEventRepository = visitEventRepository;
    }
	
	@GetMapping("/admin/visits")
    public ResponseEntity<?> visits() {
		return new ResponseEntity<List<VisitEvent>>(visitEventRepository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/admin/stats/year/{year}/month/{month}")
    public ResponseEntity<?> getStats(@PathVariable("year") int year, @PathVariable("month") int month) throws ParseException {
		Map<String, Integer> monthSales = adminService.monthlySales(month, year).getItemSales();
		return new ResponseEntity<Map<String, Integer>>(monthSales, HttpStatus.OK);
    }
	
	
}
