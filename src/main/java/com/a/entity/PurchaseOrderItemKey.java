package com.a.entity;

import java.io.Serializable;

public class PurchaseOrderItemKey implements Serializable {
	private PurchaseOrder po;
	private Item item;
	
	public PurchaseOrderItemKey(PurchaseOrder po, Item item) {
		super();
		this.po = po;
		this.item = item;
	}
	
	public PurchaseOrder getPo() {
		return po;
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
}
