package com.java.martinn.dao;



import java.util.List;

import com.java.martinn.model.Cart;
import com.java.martinn.model.MenuItem;

public interface CartDao {
	
	public void addCartItem(long userId, long menuItemId);
	public Cart getAllCartItems(long userId) throws CartEmptyException;
	public void removeCartItem(long userId, long menuItemId);

}
