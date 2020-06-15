package main_objects;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	private final int id;
	private final Date date;
	private int waiting_time = 0;
	private ArrayList<String> items;
	private String key_code;
	private static int nr_of_orders = 0;
	
	public Order (String...values) {
		id = nr_of_orders ++;
		date = new Date();
		for (String i: values) {
			waiting_time += 2;
			items.add(i);
		}
	}
	public ArrayList<String> getItems() {
		return items;
	}
	public Date get_date() {
		return date;
	}
	
	public int get_waiting_time() {
		return waiting_time;
	}
	
	public int get_id() {
		return id;
	}
	public int getNr_of_orders() {
		return nr_of_orders;
	}
	
	public String getKey_code() {
		return key_code;
	}
	
	public void print_menu_items() {
		for(String i: items)
			System.out.println(i);
	}
}
