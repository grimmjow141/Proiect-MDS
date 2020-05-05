package main_objects;

import java.util.ArrayList;
import javax.swing.*;

import graphical_objects.StartMenu;
import graphical_objects.TicketFrame;
import main_objects.Utility.*;

public class main {

	public static void main(String[] args) {
//		
////		Client Gaga = new Client("Adrian", "Berceanu", Exc.STUDENT);
////		Movie movie = new Movie("Black panther 2", Type.ADVENTURE, "16:00", "17:00");
////		Ticket ticket = new Ticket(movie, Gaga, new OccupiedSeat(1, 4, 3));
//		ArrayList<Ticket> list = Utility.read_from_file("data/tickets.csv", Ticket.class);
////		list.add(ticket);
//		System.out.println(list);
		
//		new TicketFrame(ticket);
		
		JFrame window = new JFrame("main");
		window.setLocation(400, 200);
		window.setVisible(true);
		window.setResizable(false);
		window.setSize(600, 400);
		window.setContentPane(new StartMenu());
		
//		ArrayList<Ticket> list = new ArrayList<Ticket>();
//		list.add(ticket);
//		list.add(ticket_2);
//		Utility.write_to_file("test.csv", list);
//		ArrayList<OccupiedSeat> list = Utility.read_from_file("occupied_seats.csv", OccupiedSeat.class);
//		Utility.write_to_file("test.csv", list);
		
	
		
//		ArrayList<Client> list = Utility.read_from_file("clients.csv", Client.class);
//		Utility.write_to_file("test.csv", list);
		
//		System.out.println(ticket.getPrice());
//		System.out.println(ticket.getKey_code());
		
//		Cinema Patria = new Cinema();
//		Patria.add_n_new_rooms(5, 10, 20);
//		Patria.reserve_seat(3, 3, Patria.getHall().get(1), movie, Anda);
//		System.out.println(Patria.getHall().get(1).getSeats()[3][3]);
		
//		Order o1 = new Order("Cola", "Latte Coffe", "Popcorn");
//		Order o2 = new Order("Popcorn", "Fanta");
//		Order o3 = new Order();
//		Bar bar = new Bar();
//		bar.add_orders(o1, o2, o3);
//		Utility.write_to_file(Gaga, "clients.csv");
//		Utility.write_to_file(Anda, "clients.csv");
//		Utility.write_to_file(ticket, "tickets.csv"); //vip ticket
//		Utility.write_to_file(ticket_2, "tickets.csv");//normal ticket
//		Utility.write_to_file(o1, "orders.csv");
//		Utility.write_to_file(o2, "orders.csv");
//		Utility.write_to_file(o3, "orders.csv");
//		
//		String str;
//		Vector<Client> v = new Vector <Client>();
//		v.add(Anda);
//		v.add(Dorel);
//		v.add(Gaga);
//		v.add(Dorel_2);
//		System.out.println(v.contains(new Client("Anda")));
//		Arrays.sort(v);
//		for (Client t: v)
//			Patria.add_new_client(t);
		
	}
}
