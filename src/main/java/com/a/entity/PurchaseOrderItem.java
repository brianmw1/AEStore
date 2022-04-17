package com.a.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@IdClass(PurchaseOrderItemKey.class)
public class PurchaseOrderItem {
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "po_id", referencedColumnName = "id")
	private @Id PurchaseOrder po;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_bid", referencedColumnName = "bid")
	private @Id Item item;
	
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
