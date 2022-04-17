package com.a.model;

import com.a.entity.User;

public class Model {
	private User currentUser;
	private CartModel cart;
	public Model() {
		
	}
	
	public void logout() {
		this.currentUser = null;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	public CartModel getCart() {
		return cart;
	}
	public void setCart(CartModel cart) {
		this.cart = cart;
	}
	
}
