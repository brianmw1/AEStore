package com.a.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.a.entity.Item;
import com.a.controller.ItemController;

@Component
public class ItemModelAssembler implements RepresentationModelAssembler<Item, EntityModel<Item>> {

    @Override
    public EntityModel<Item> toModel(Item item) {

        return EntityModel.of(item, //
                linkTo(methodOn(ItemController.class).one(item.getBid())).withSelfRel(), linkTo(methodOn(ItemController.class).all()).withRel("items"));
    }
}
