package com.a.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.a.assembler.ItemModelAssembler;
import com.a.entity.Address;
import com.a.entity.Item;
import com.a.entity.PurchaseOrder;
import com.a.entity.PurchaseOrderItem;
import com.a.entity.User;
import com.a.exception.ItemNotFoundException;
import com.a.exception.UserNotFoundException;
import com.a.model.CartModel;
import com.a.repository.ItemRepository;
import com.a.repository.PurchaseOrderItemRepository;
import com.a.repository.PurchaseOrderRepository;
import com.a.repository.UserRepository;
import com.a.repository.VisitEventRepository;
import com.a.service.CartService;

@RestController
public class CartController {
	
	private final PurchaseOrderRepository poRepository;
	private final PurchaseOrderItemRepository poItemRepository;
	private final CartService cartService;
	private final ItemRepository itemRepository;
	private final UserRepository userRepository;
	private static int paymentCount = 0;
	private VisitEventRepository visitEventRepository;
    
    public CartController(UserRepository userRepository, PurchaseOrderRepository poRepository, PurchaseOrderItemRepository poItemRepository, CartService cartService, ItemRepository itemRepository, VisitEventRepository visitEventRepository) {
        this.poRepository = poRepository;
        this.poItemRepository = poItemRepository;
        this.cartService = cartService;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.visitEventRepository = visitEventRepository;
    }
    
    @GetMapping("/cart") 
    public ResponseEntity<Map<String, Integer>> getCart() {
    	return new ResponseEntity<Map<String, Integer>>(cartService.getCartString(), HttpStatus.OK);
    	
    }
    
    @PostMapping("/cart/addItem/{bid}")
    public ResponseEntity<Map<String,Integer>> addItem(@PathVariable("bid") String bid, HttpServletRequest request) {
    	Item item = itemRepository.findById(bid).orElseThrow(() -> new ItemNotFoundException(bid));
    	cartService.addItem(item);
    	return new ResponseEntity<Map<String, Integer>>(cartService.getCartString(), HttpStatus.OK);
    }
    
    @PostMapping("/cart/removeItem/{bid}")
    public ResponseEntity<Map<String,Integer>> removeItem(@PathVariable("bid") String bid) {
    	Item item = itemRepository.findById(bid).orElseThrow(() -> new ItemNotFoundException(bid));
    	cartService.removeItem(item);
    	return new ResponseEntity<Map<String, Integer>>(cartService.getCartString(), HttpStatus.OK);
    }
    
    @GetMapping("/cart/getTotal")
    public ResponseEntity<Double> getTotal() {
    	return new ResponseEntity<Double>(cartService.getTotal(), HttpStatus.OK);
    }
    
    @GetMapping("/cart/checkout")
    public ResponseEntity<PurchaseOrder> checkout(@RequestBody Map<String, String> json, HttpServletRequest request) {
    	User user = userRepository.findById(json.get("username")).orElseThrow(() -> new UserNotFoundException(json.get("username")));
    	Address address = new Address();
    	address.setCountry(json.get("country"));
    	address.setPhone(json.get("phone"));
    	address.setProvince(json.get("province"));
    	address.setStreet(json.get("street"));
    	address.setZip(json.get("zip"));
    	PurchaseOrder po = new PurchaseOrder();
    	po.setAddress(address);
    	po.setUser(user);
    	if(paymentCount == 2) {
    		po.setStatus("DENIED");
    		paymentCount = 0;
    	} else {
    		po.setStatus("PROCESSED");
    		paymentCount += 1;
    		Map<Item, Integer> items = cartService.getCart();
    		for (Map.Entry<Item, Integer> entry : items.entrySet()) {
    			int quantity = entry.getValue();
    			Item item = entry.getKey();
    			item.setQuantity(item.getQuantity() - quantity);
    			item = itemRepository.save(item);
    			
    			PurchaseOrderItem poi = new PurchaseOrderItem();
    			poi.setItem(item);
    			poi.setPo(po);
    			poi.setPrice(item.getPrice() * quantity);
    			poi = poItemRepository.save(poi);
    		}
    	}
    	po = poRepository.save(po);
    	cartService.clearCart();
    	return new ResponseEntity<PurchaseOrder>(po, HttpStatus.OK);
    	
    }
    
    
}
