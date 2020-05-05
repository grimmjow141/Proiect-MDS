package main_objects;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Bar {
	private float juice_container[];
	private Queue <Order> orders;   
	
	Bar() {
		juice_container = new float[3]; //we suppose that a bar has 3 juice containers
		orders = new LinkedBlockingQueue <Order>();
	}
	
	public void add_orders (Order... order) {
		for (Order i: order)
			orders.add(i);
	}
	
	public Queue <Order> getOrders () {
		return orders;
	}
}
