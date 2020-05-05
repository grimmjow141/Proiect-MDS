package main_objects;

import java.util.Date;
import java.util.ArrayList;

public class Order {
	private int id;
	private Date date;
	private int waiting_time; //in minutes
	private ArrayList <String> items;
	private String key_code;
	private static int nr_of_orders = 0;
	
	Order(String... items) {
		id = ++ nr_of_orders;
		date = new Date();
		this.items = new ArrayList <String>();
		for (String i: items)
			this.items.add(i);
		waiting_time = 2 * this.items.size(); //we suppose that each item takes 2 minutes to make
		key_code = "O#" + Utility.fill_zeros(id);
	}
	
	public void print_menu_items () {
		for (String i: items)
			System.out.println(i);
	}

	public int getId() {
		return id;
	}

	public String getDate() {
		return Utility.date_format.format(date);
	}

	public int getWaiting_time() {
		return waiting_time;
	}

	public ArrayList<String> getItems() {
		return items;
	}


	public String getKey_code() {
		return key_code;
	}


	public static int getNr_of_orders() {
		return nr_of_orders;
	}
	
	
}