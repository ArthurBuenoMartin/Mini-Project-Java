package com.java.martinn.dao;

import java.util.List;

import com.java.martinn.model.MenuItem;

public class MenuItemDaoSqlImpl implements MenuItemDao {
	
	
	public MenuItemDaoSqlImpl() {
		ConnectionHandler.getConnection();
	}
	
	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		// TODO Auto-generated method stub
		return null;
	}

}
