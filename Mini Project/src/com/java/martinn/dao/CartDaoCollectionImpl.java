package com.java.martinn.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.java.martinn.model.Cart;
import com.java.martinn.model.MenuItem;

public class CartDaoCollectionImpl implements CartDao {

	private HashMap<Long,Cart> userCarts;
	
	
	
	public CartDaoCollectionImpl() {
		if(userCarts == null) {
			userCarts = new HashMap<Long, Cart>();
		}
		//check if userCarts is null
		//if true > new hashmap
		//long is userid
	}

	@Override
	public void addCartItem(long userId, long menuItemId) {
		MenuItemDaoCollectionImpl itemCollection = new MenuItemDaoCollectionImpl();
		MenuItem item = itemCollection.getMenuItem(menuItemId);
		if(userCarts.containsKey(userId)) {
			//System.out.println(userId);
			Cart cart = userCarts.get(userId);
			List<MenuItem> itemList = cart.getMenuItemList();
			itemList.add(item);
			cart.setMenuItemList(itemList);
		}else {
			Cart cart = new Cart(new ArrayList<MenuItem>() , Double.valueOf(0));
			List<MenuItem> itemList = cart.getMenuItemList();
			itemList.add(item);
			cart.setMenuItemList(itemList);
			userCarts.put(userId, cart);
			//System.out.println(userId);
		}
		// inst MenuItemDaoCollectionImpl
		// getMenuItem
		// if user exist > get menuitemlist frm userCarst add the item to list
		// if false > create new cart add item to menuitemlist put userid and list into usercarsts
		
	}

	@Override
	public Cart getAllCartItems(long userId) throws CartEmptyException {
		if(userCarts.containsKey(userId)) {
			Cart cart = userCarts.get(userId);
			List<MenuItem> menuItemList = cart.getMenuItemList(); 
			if(menuItemList.isEmpty() == true) {
				throw new CartEmptyException("Cart is Empty");
			}else {
				Float total = 0f;
				for (MenuItem menuItem : menuItemList) {
					total += menuItem.getPrice();
				}
				cart.setTotal(Double.valueOf(total));
			}
			return cart;
		}else {
			throw new CartEmptyException("Cart is Empty");
		}
		// if list empty > throw exception
		// false >iterate throuth list and add up prices
		// set total
		
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		// get the list based on userid
		// if item matches remove it
		if(userCarts.containsKey(userId)){
			Cart cart = userCarts.get(userId);
			List<MenuItem> menuItemList = cart.getMenuItemList();
			for (MenuItem menuItem : menuItemList) {
				if(menuItem.getId() == menuItemId) {
					menuItemList.remove(menuItem);
				}
			}
		}
	}

}
