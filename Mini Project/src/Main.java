import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.java.martinn.dao.CartDaoCollectionImpl;
import com.java.martinn.dao.CartEmptyException;
import com.java.martinn.dao.MenuItemDaoCollectionImpl;
import com.java.martinn.model.Cart;
import com.java.martinn.model.MenuItem;
import com.java.martinn.util.DateUtil;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	private static MenuItemDaoCollectionImpl menuItemCollec = new MenuItemDaoCollectionImpl();
	private static CartDaoCollectionImpl cartCollect = new CartDaoCollectionImpl();
	private static List<MenuItem> menuItemList = new ArrayList<MenuItem>();
	private static Boolean isAdmin = false;
	private static long userId = 0;

	public static void main(String[] args) throws CartEmptyException {
		// TODO Auto-generated method stub
		scanner = new Scanner(System.in);
		String s = ""; 

		while(!s.equalsIgnoreCase("exit")) {
			System.out.println("Type 1 for Admin, 2 for User or EXIT to exit:");

			s =scanner.next();
			scanner.nextLine();
			if(s.equals("1")) {
				isAdmin = true;
				adminMenu();

			}else if(s.equals("2")) {
				isAdmin =false;
				customerMenu();

			}else if(!s.equalsIgnoreCase("exit")) {
				System.out.println("Please type a valid options");
			}



		}

	}

	public static void adminMenu() {
		int opt = 0;

		while (opt != 3) {
			System.out.println("-------- Admin Menu --------");
			System.out.println("1. Display all Items");
			System.out.println("2. Modify Items");
			System.out.println("3.EXIT");

			opt = scanner.nextInt();

			switch (opt) {
			case 1:
				displayItems();
				break;
			case 2:
				modifyItems();
				break;
			default:
				opt = 3;
				break;
			}
		}

	}

	public static void customerMenu() throws CartEmptyException {
		int opt = 0;
		System.out.println("Type your user id:");
		try {
			userId = scanner.nextLong();
			scanner.nextLine();
			while (opt != 5) {
				System.out.println("-------- Customer Menu --------");
				System.out.println("1. Display all Items");
				System.out.println("2. Add item to cart");
				System.out.println("3. Show all the items on the cart");
				System.out.println("4. Remove item from cart");
				System.out.println("5.EXIT");

				opt = scanner.nextInt();

				switch (opt) {
				case 1:
					displayItems();
					break;
				case 2:
					System.out.println("Type the item id you want to add to your cart:");
					Long itemId = scanner.nextLong();
					cartCollect.addCartItem(userId, itemId);
					break;
				case 3:
					Cart cart = cartCollect.getAllCartItems(userId);
					System.out.println("\nDisplaying all items on your cart:");
					System.out.println(String.format("|%-10s |%-10s |%-10s |%-10s |%-10s |%-10s |%-10s", "Id","Name","Price","Active","Date of launch","Category","Free Delivery"));	
					List<MenuItem> menuItems= cart.getMenuItemList();
					for (MenuItem menuItem : menuItems) {
						System.out.println(menuItem.toString());
					}
					System.out.println("Total:");
					System.out.println("R$ "+cart.getTotal());
					break;
				case 4:
					System.out.println("Type the item id you wish to remove:");
					Long rmItemId = scanner.nextLong();
					cartCollect.removeCartItem(userId, rmItemId);
					break;
				default:
					opt = 5;
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("invalid user id");
		}finally {
			userId =0;
		}

	}

	private static void displayItems() {
		// TODO Auto-generated method stub
		System.out.println("\nDisplaying all items:");
		System.out.println(String.format("|%-10s |%-10s |%-10s |%-10s |%-10s |%-10s |%-10s", "Id","Name","Price","Active","Date of launch","Category","Free Delivery"));	
		if(isAdmin == true) {
			menuItemList = menuItemCollec.getMenuItemListAdmin();
		}else {
			menuItemList = menuItemCollec.getMenuItemListCustomer();
		}
		for (MenuItem menuItem : menuItemList) {
			System.out.println(menuItem.toString());
		}
	}

	private static void modifyItems() {
		String modifyOpt = "y";
		Long id = Long.valueOf("-1");
		while(modifyOpt.equalsIgnoreCase("y")) {
			displayItems();
			System.out.println("Type the id of the item you want ot modify:");
			try {
				id = scanner.nextLong();
				if(menuItemCollec.getMenuItem(id) != null) {
					MenuItem item = menuItemCollec.getMenuItem(id);
					System.out.println("Type the field you want to modify:");
					String field = scanner.next();
					scanner.nextLine();
					if(field.equalsIgnoreCase("name")) {
						System.out.println("Type the new value:");
						String name = scanner.next();
						item.setName(name);
						menuItemCollec.modifyMenuItem(item);
					}else if(field.equalsIgnoreCase("price")) {
						System.out.println("Type the new value:");
						try {
							Float price = scanner.nextFloat();
							item.setPrice(price);
							menuItemCollec.modifyMenuItem(item);
						} catch (Exception e) {
							System.out.println("invalid input");
						}

					}else if(field.equalsIgnoreCase("active")) {
						System.out.println("Type the new value:");
						try {
							Boolean active = scanner.nextBoolean();
							item.setActive(active);
							menuItemCollec.modifyMenuItem(item);
						} catch (Exception e) {
							System.out.println("invalid input");
						}
					}else if(field.equalsIgnoreCase("date of launch")) {
						System.out.println("Type the new value:");
						try {
							String dateString = scanner.nextLine();
							Date date = DateUtil.convertToDate(dateString);
							item.setDateOfLaunch(date);
							menuItemCollec.modifyMenuItem(item);
						} catch (Exception e) {
							System.out.println("invalid input");
						}
					}else if(field.equalsIgnoreCase("category")) {
						System.out.println("Type the new value:");
						try {
							String category = scanner.next();
							scanner.nextLine();
							item.setCategory(category);
							menuItemCollec.modifyMenuItem(item);
						} catch (Exception e) {
							System.out.println("invalid input");
						}
					}else if(field.equalsIgnoreCase("free delivery")) {
						System.out.println("Type the new value:");
						try {
							Boolean delivery = scanner.nextBoolean();
							item.setFreeDelivery(delivery);
							menuItemCollec.modifyMenuItem(item);
						} catch (Exception e) {
							System.out.println("invalid input");
						}
					}else {
						System.out.println("invalid field");
					}
				}else {
					System.out.println("Item not found");
				}
			} catch (Exception e) {
				System.out.println("Type a valid input");
			}finally {
				id = Long.valueOf("-1");
			}
			System.out.println("Type Y to modify another item or type ANY KEY to continue:");
			scanner.nextLine();
			modifyOpt = scanner.next();
		}
	}



}
