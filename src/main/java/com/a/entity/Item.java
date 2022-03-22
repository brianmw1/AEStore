package com.a.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
CREATE TABLE Item(
bid VARCHAR(20) NOT NULL PRIMARY KEY,
name VARCHAR(60) NOT NULL,
description VARCHAR(60) NOT NULL,
type VARCHAR(60) NOT NULL,
brand VARCHAR(60) NOT NULL,
quantity INT NOT NULL,
price INT NOT NULL
);
 */

@Entity
public class Item {

	private @Id String bid;
	private String name;
	private String description;
	private String type;
	private String brand;
	private int quantity;
	private int price;
	
	public Item() {}

	public Item(String bid, String name, String description, String type, String brand, int quantity, int price) {
		this.bid = bid;
		this.name = name;
		this.description = description;
		this.type = type;
		this.brand = brand;
		this.quantity = quantity;
		this.price = price;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bid, brand, description, name, price, quantity, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(bid, other.bid) && Objects.equals(brand, other.brand)
				&& Objects.equals(description, other.description) && Objects.equals(name, other.name)
				&& price == other.price && quantity == other.quantity && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Item [bid=" + bid + ", name=" + name + ", description=" + description + ", type=" + type + ", brand="
				+ brand + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
