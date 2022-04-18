package com.a.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class PurchaseOrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "po_id", referencedColumnName = "id")
	private PurchaseOrder po;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_bid", referencedColumnName = "bid")
	private Item item;
	
	public PurchaseOrder getPo() {
		return po;
	}

	public PurchaseOrderItem() {}
	public PurchaseOrderItem(PurchaseOrder po, Item item, int price) {
		super();
		this.po = po;
		this.item = item;
		this.price = price;
	}

	public void setPo(PurchaseOrder po) {
		this.po = po;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	private int price;
	
}
