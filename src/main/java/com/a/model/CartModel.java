package com.a.model;

import java.util.Map;
import java.util.Map.Entry;

import com.a.entity.Item;

public class CartModel {
	Map<Item, Integer> cart;
	
	public Map<Item, Integer> getCart() {
		return cart;
	}

	public void setCart(Map<Item, Integer> cart) {
		this.cart = cart;
	}

	public CartModel(Map<Item,Integer> cart) {
		this.cart = cart;
	}
	
	public double calculateTotal() {
		double total = 0;
		for (Entry<Item, Integer> entry : cart.entrySet()) {
		    Item item = entry.getKey();
		    double price = item.getPrice();
		    int value = entry.getValue();
		    total += price * value;
		}
		return total;
	}
}
