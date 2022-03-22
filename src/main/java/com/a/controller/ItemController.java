package com.a.controller;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.a.entity.Item;
import com.a.exception.ItemNotFoundException;
import com.a.repository.ItemRepository;

@RestController
class ItemController {

  private final ItemRepository repository;

  ItemController(ItemRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/Items")
  List<Item> all() {
	System.out.println("pls");
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/Items")
  Item newItem(@RequestBody Item newItem) {
    return repository.save(newItem);
  }

  // Single item
  
  @GetMapping("/Items/{id}")
  EntityModel<Item> one(@PathVariable Long id) {

    Item Item = repository.findById(id) //
        .orElseThrow(() -> new ItemNotFoundException(id));

    return EntityModel.of(Item, //
        linkTo(methodOn(ItemController.class).one(id)).withSelfRel(),
        linkTo(methodOn(ItemController.class).all()).withRel("Items"));
  }

//  @PutMapping("/Items/{id}")
//  Item replaceItem(@RequestBody Item newItem, @PathVariable Long id) {
//    
//    return repository.findById(id)
//      .map(Item -> {
//        Item.setName(newItem.getName());
//        Item.setRole(newItem.getRole());
//        return repository.save(Item);
//      })
//      .orElseGet(() -> {
//        newItem.setId(id);
//        return repository.save(newItem);
//      });
//  }

  @DeleteMapping("/Items/{id}")
  void deleteItem(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
