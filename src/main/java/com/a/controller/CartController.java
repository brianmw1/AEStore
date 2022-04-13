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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.a.assembler.ItemModelAssembler;
import com.a.entity.Item;
import com.a.model.CartModel;
import com.a.repository.ItemRepository;

@RestController
public class CartController {
	
	private final ItemRepository repository;
    
    public CartController(ItemRepository repository) {
        this.repository = repository;
    }
        

	@GetMapping("/cart/getTotal")
    public double getTotal(@RequestBody Map<String, Integer> items) {
		Map<Item, Integer> cart = new HashMap<Item, Integer>();
		int total = 0;
		for (Entry<String, Integer> entry : items.entrySet()) {
		    Item item = repository.getById(entry.getKey());
		    cart.put(item, entry.getValue());
		}
		CartModel cartModel = new CartModel(cart);
		cartModel.calculateTotal();
        return total;
    }
}
