package main_objects;

public class VIP_Ticket extends Ticket{
	VIP_Ticket(Movie movie, Client client, OccupiedSeat occupied_seat) {
		super(movie, client, occupied_seat);
		price += 0.25 * price; //the price of a VIP ticket is 25% higher
		key_code += "^VP";     //i also modify the ticket code just for fun!
	}
}
