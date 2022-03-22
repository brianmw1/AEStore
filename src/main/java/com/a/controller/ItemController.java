package com.a.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

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
import com.a.exception.ItemNotFoundException;
import com.a.repository.ItemRepository;

@RestController
public class ItemController {

    private final ItemRepository repository;
    private ItemModelAssembler assembler;
    
    public ItemController(ItemRepository repository, ItemModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
        
    }

    // Aggregate root

    @GetMapping("/items")
    public CollectionModel<EntityModel<Item>> all() {

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