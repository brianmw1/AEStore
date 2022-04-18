package com.a.model;

import java.util.Map;

public class MonthSales {
	private int month;
	private int year;
	private Map<String, Integer> itemSales;
	
	public MonthSales(int month, int year, Map<String,Integer> itemSales) {
		super();
		this.month = month;
		this.year = year;
		this.itemSales = itemSales;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public void incQuantity(String bid) {
		if(itemSales.containsKey(bid)) {
			itemSales.put(bid, itemSales.get(bid) + 1);
		} else {
			itemSales.put(bid, 1);
		}
	}

	public Map<String, Integer> getItemSales() {
		return itemSales;
	}

	public void setItemSales(Map<String, Integer> itemSales) {
		this.itemSales = itemSales;
	}
}
