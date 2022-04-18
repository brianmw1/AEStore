package com.a.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class VisitEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_bid", referencedColumnName = "bid")
	private Item item;
	
	private String ipaddress;
	private String day;
	private String eventtype;
	public VisitEvent() {}
	public VisitEvent(int id, Item item, String ipaddress, String day, String eventtype) {
		super();
		this.id = id;
		this.item = item;
		this.ipaddress = ipaddress;
		this.day = day;
		this.eventtype = eventtype;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getEventtype() {
		return eventtype;
	}
	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}
	
}
