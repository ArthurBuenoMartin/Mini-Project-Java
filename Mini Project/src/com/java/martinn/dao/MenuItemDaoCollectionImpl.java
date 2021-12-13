package com.java.martinn.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.martinn.model.MenuItem;
import com.java.martinn.util.DateUtil;

public class MenuItemDaoCollectionImpl implements MenuItemDao {

	private List<MenuItem> menuItemList;
	
	
	
	public MenuItemDaoCollectionImpl() {
		if(menuItemList == null) {
			menuItemList = new ArrayList<MenuItem>();
			menuItemList.add(new MenuItem(Long.valueOf(0), "Sandwich", Float.valueOf(7.34f), true, DateUtil.convertToDate("15/03/2017"), "Main Course", true));
			menuItemList.add(new MenuItem(Long.valueOf(1), "Burguer", Float.valueOf(9.57f), true, DateUtil.convertToDate("23/12/2017"), "Main Course", false));
			menuItemList.add(new MenuItem(Long.valueOf(2), "Pizza", Float.valueOf(11.05f), true, DateUtil.convertToDate("21/08/2018"), "Main Course", false));
			menuItemList.add(new MenuItem(Long.valueOf(3), "French Fries", Float.valueOf(4.23f), false, DateUtil.convertToDate("02/07/2017"), "Starters", true));
			menuItemList.add(new MenuItem(Long.valueOf(4), "Choccolate Brownie", Float.valueOf(2.37f), true, DateUtil.convertToDate("02/11/2022"), "Dessert", true));
		}
	}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		// return items that will be displayed
		
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		List<MenuItem> customerItems = new ArrayList<MenuItem>();
		Date currentDate = new Date();
		for (MenuItem menuItem : menuItemList) {
			if(!menuItem.getDateOfLaunch().after(currentDate)) {
				if(menuItem.isActive()) {
					customerItems.add(menuItem);
				}
			}
		}
		// init an arraylist fo type menuitem get items registred
		// if launch date is today or befor today &&
		// && menu item is active 
		// then add to arraylist
		return customerItems;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		// update the item data
		MenuItem item = getMenuItem(menuItem.getId());
		menuItemList.remove(item);
		menuItemList.add(menuItem);
		
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		MenuItem item = null;
		for (MenuItem menuItem : menuItemList) {
			if(menuItem.getId() == menuItemId) {
				item = menuItem;
			}
		}
		return item;
	}

}
