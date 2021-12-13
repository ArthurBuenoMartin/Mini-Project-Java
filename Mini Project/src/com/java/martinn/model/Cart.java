package com.java.martinn.model;

import java.util.List;

public class Cart {
	
	private List<MenuItem> menuItemList;
	private Double total;
	
	public Cart(List<MenuItem> menuItemList, Double total) {
		super();
		this.menuItemList = menuItemList;
		this.total = total;
	}

	public List<MenuItem> getMenuItemList() {
		return menuItemList;
	}

	public void setMenuItemList(List<MenuItem> menuItemList) {
		this.menuItemList = menuItemList;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Cart [menuItemList=" + menuItemList + ", total=" + total + "]";
	}

	
	
}
