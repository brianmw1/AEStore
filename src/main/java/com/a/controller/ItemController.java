package com.a.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.a.assembler.ItemModelAssembler;
import com.a.entity.Item;
import com.a.entity.VisitEvent;
import com.a.exception.ItemNotFoundException;
import com.a.repository.ItemRepository;
import com.a.repository.VisitEventRepository;
import com.a.util.HttpReqRespUtil;

@RestController
public class ItemController {

    private final ItemRepository repository;
    private ItemModelAssembler assembler;
    private VisitEventRepository visitEventRepository;
    
    public ItemController(ItemRepository repository, ItemModelAssembler assembler, VisitEventRepository visitEventRepository) {
        this.repository = repository;
        this.assembler = assembler;
        this.visitEventRepository = visitEventRepository;
    }

    // Aggregate root

    @GetMapping("/items")
    public CollectionModel<EntityModel<Item>> all() {
    	List<Item> vItems = repository.findAll();
    	for(Item item:vItems) {
    		VisitEvent visitEvent = new VisitEvent();
        	String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
        	visitEvent.setDay(date);
        	visitEvent.setEventtype("VIEW");
        	visitEvent.setItem(item);
        	String ip = HttpReqRespUtil.getClientIpAddressIfServletRequestExist();
        	System.out.println(ip);
        	visitEvent.setIpaddress(ip);
        	visitEventRepository.save(visitEvent);
    	}
    	
        List<EntityModel<Item>> items = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(items, linkTo(methodOn(ItemController.class).all()).withSelfRel());
    }

    @PostMapping("/items")
    public ResponseEntity<?> newItem(@RequestBody Item newItem) {

        EntityModel<Item> entityModel = assembler.toModel(repository.save(newItem));

        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }

    // Single item
    @GetMapping("/items/{id}")
    public EntityModel<Item> one(@PathVariable String bid) {

        Item Item = repository.findById(bid) //
                .orElseThrow(() -> new ItemNotFoundException(bid));

        return assembler.toModel(Item);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<?> replaceItem(@RequestBody Item newItem, @PathVariable String bid) {

        Item updatedItem = repository.findById(bid)
                .map(Item -> {
                    Item.setName(newItem.getName());
                    return repository.save(Item);
                })
                .orElseGet(() -> {
                    newItem.setBid(bid);
                    return repository.save(newItem);
                });
        
        EntityModel<Item> entityModel = assembler.toModel(updatedItem);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable String bid) {
        repository.deleteById(bid);
        return ResponseEntity.noContent().build();
    }
}