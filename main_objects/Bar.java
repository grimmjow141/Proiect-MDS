package main_objects;

import java.util.Queue;

public class Bar {
	private Queue<Order> orders;
	private float juice_container[];
	
	public Bar() {
		juice_container = new float[3]; //cola, fanta, sprite for ex.. and the values are indicating how much juice is left
	}
	
	public void add_orders(Order...values) {
		for (Order i: values)
			orders.add(i);
	}
	
	public Queue<Order> get_orders() {
		return orders;
	}
}
