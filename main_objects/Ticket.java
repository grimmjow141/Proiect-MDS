package main_objects;

import java.util.Date;

public class Ticket {
	
	private int id;
	private Movie movie;
	private Client client;
	private OccupiedSeat occupied_seat;
	private final Date sell_date;
	private static int nr_of_tickets = 0;
	
	protected String key_code;
	protected float price;
	
	public Ticket() {
		sell_date = null;
	}
	
	public Ticket(Movie movie, Client client, OccupiedSeat occupied_seat) {
		
		id = ++ nr_of_tickets;
		this.movie = movie;
		this.client = client;
		this.occupied_seat = occupied_seat;
		
		if (client.getExc() != null)
			switch(client.getExc()) {
				case STUDENT:
					price = 9.3f;
					break;
				case RETIRED:
					price = 9.2f;
					break;
				case DISABLED:
					price = 8.5f;
			}
		else
			price = 10.0f;
		
		sell_date = new Date();                  //we initialize sell_date with the current date
		String type = (movie.getType() != null) ? movie.getType().toString() : "";//auxiliary variable, it stores the type but in string
		key_code = "#" + Utility.fill_zeros(id) + "|";
		key_code += (movie.getType() != null) ? type.toUpperCase().substring(0, 3) : "---";
		key_code += "|";
		key_code += String.valueOf(occupied_seat.getRoom()) + "/" + String.valueOf(occupied_seat.getRow()) + String.valueOf(occupied_seat.getColumn());
	
	}
	
	public Movie getMovie () {
		return movie;
	}
	
	public Client getClient () {
		return client;
	}
	
	public OccupiedSeat getOccupiedSeat () {
		return occupied_seat;
	}
	
	public String getSell_date () {
		return Utility.date_format.format(sell_date);
	}
	
	public float getPrice () {
		return price;
	}
	
	public String getKey_code() {
		return key_code;
	}
	
	public static void setNr_of_tickets (int nr_of_tickets) {
		Ticket.nr_of_tickets = nr_of_tickets;
	}

	public static int getNr_of_tickets() {
		return nr_of_tickets;
	}
	
}
