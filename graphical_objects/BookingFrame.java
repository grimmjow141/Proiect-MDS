package graphical_objects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import main_objects.Cinema;
import main_objects.Client;
import main_objects.Movie;
import main_objects.OccupiedSeat;
import main_objects.Room;
import main_objects.Ticket;
import main_objects.Utility;

public class BookingFrame extends JFrame{
	
	private static Cinema cinema;
	private MatrixOfButtons matrix;
	private static int selected_row;
	private static int selected_column;
	private static boolean seat_is_selected;
	
	private static JPanel movie_info;
	private static JPanel client_info;

	class GridButton extends JButton { //auxiliary class for storing the coordinates
									   //of a button in a GridLayout
		public final int row;		   
		public final int column;
		
		GridButton () {
			row = -1;
			column = -1;
		}
		
		GridButton(int row, int column) {
			this.row = row;
			this.column = column;
		}

	}
	
	class MatrixOfButtons extends JPanel {
		
		public int rows;
		public int columns;
		public boolean show_image;
		public GridButton[][] buttons;
		
		MatrixOfButtons() {
			setPreferredSize(new Dimension(400, 170));
			setBorder(new EmptyBorder(20, 50, 10, 320));
			show_image = true;
		}
		
		MatrixOfButtons(int rows, int columns) {

			setLayout(new GridLayout(rows, columns));
			setBorder(new EmptyBorder(20, 50, 10, 40));
			setPreferredSize(new Dimension(400, 170));
			this.rows = rows;
			this.columns = columns;
			show_image = false;
			buttons = new GridButton[rows][columns];
			for (int i = 0; i < rows; i ++)
				for (int j = 0; j < columns; j ++)
					buttons[i][j] = new GridButton(i, j);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			if (show_image)
				try {
					g.drawImage(ImageIO.read(new File("images/cortain.png")), 0, 0, null);
				} catch (IOException e) {
					System.out.println("Failed to load matrix background image..");
					e.printStackTrace();
				}
		
		}

	}
	
	public BookingFrame(){
		
		setVisible(true);
		setSize(648, 318);
		setResizable(false);
		setLocation(370, 270);
		setLayout(new BorderLayout(20, 0));
		//paint(getGraphics());
		
//		cinema = new Cinema();
//		cinema.add_new_room(10, 5);
//		cinema.add_new_room(10, 9);
//		cinema.add_new_room(5, 3);
//		cinema.add_new_room(5, 6);
		
		movie_info = new JPanel(new GridBagLayout());
		movie_info.setBorder(new EmptyBorder(5, 15, 5, 100));
		client_info = new JPanel(new GridBagLayout());
		client_info.setBorder(new EmptyBorder(0, 0, 0, 40));
		
		getData();
		
		JLabel movie = new JLabel("Movie: ");
		JLabel type = new JLabel("Type: ");
		JLabel f_n = new JLabel("First name: ");
		JLabel l_n = new JLabel("Last name: ");
		JLabel exc = new JLabel("Exception: ");
		JLabel vip = new JLabel("VIP ticket: ");
		JButton filter_button = new JButton("Filter");
		
		JComboBox<Movie> movie_field = new JComboBox<Movie>();
		movie_field.insertItemAt(null, 0);
		ArrayList<Movie> movie_list = Utility.read_from_file("data/movies.csv", Movie.class);
		for (Movie it: movie_list)
			movie_field.addItem(it);
	
		Map<Movie, Room> map = new HashMap<Movie, Room>();
		for (int i = 0; i < movie_list.size(); i ++)
			map.put(movie_list.get(i), cinema.getHall().get(new Random().nextInt(cinema.getNr_of_rooms()))); //it links every movie with a random room in our static cinema field
		
		JComboBox<Utility.Type> type_field = new JComboBox<Utility.Type>();
		JTextField first_name_field = new JTextField(8);
		JTextField last_name_field = new JTextField(8);
		JComboBox<Utility.Exc> exc_field = new JComboBox<Utility.Exc>();
		JCheckBox vip_box = new JCheckBox();
		
		type_field.insertItemAt(null, 0);
		type_field.addItem(Utility.Type.ACTION);
		type_field.addItem(Utility.Type.ADVENTURE);
		type_field.addItem(Utility.Type.COMEDY);
		type_field.addItem(Utility.Type.DRAMA);
		type_field.addItem(Utility.Type.THRILLER);
		
		exc_field.insertItemAt(null, 0); //adds a blank entry
		exc_field.addItem(Utility.Exc.STUDENT);
		exc_field.addItem(Utility.Exc.DISABLED);
		exc_field.addItem(Utility.Exc.RETIRED);
	
		JPanel info_panel = new JPanel();
		GridBagLayout my_layout = new GridBagLayout();
		info_panel.setLayout(my_layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 2, 2);
		
		c.gridy = 0;
		c.gridx = 0;
		movie_info.add(movie, c);
		client_info.add(f_n, c);
		
		c.gridx = 1;
		movie_info.add(movie_field, c);
		client_info.add(first_name_field, c);
		
		c.gridy = 1;
		c.gridx = 0;
		movie_info.add(type, c);
		client_info.add(l_n, c);
		
		c.gridx = 1;
		movie_info.add(type_field, c);
		client_info.add(last_name_field, c);
		
		
		c.gridy = 2;
		c.gridx = 0;
		client_info.add(exc, c);
		
		c.gridwidth = 2;
		c.insets = new Insets(5, 70, 5, 70);
		movie_info.add(filter_button, c);
		
		c.gridx = 1;
		c.insets = new Insets(5, 5, 2, 2);
		client_info.add(exc_field, c);
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(new EmptyBorder(5, 15, 5, 10));
		JButton reserve_button = new JButton("Reserve the ticket..");
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.insets = new Insets(5, 3, 5, 5);
		
		c2.gridy = 0;
		c2.gridx = 0;
		panel.add(vip, c2);
		
		c2.gridx = 1;
		panel.add(vip_box, c2);
		
		c2.gridy = 1;
		c2.gridx = 0;
		c2.gridwidth = 2;
		c2.gridheight = 2;
		c2.insets = new Insets(5, 15, 5, 16);
		panel.add(reserve_button, c2);
		
		JPanel north_panel = new JPanel();
		BoxLayout box_layout = new BoxLayout(north_panel, BoxLayout.X_AXIS);
		north_panel.setLayout(box_layout);
		north_panel.add(movie_info);
		north_panel.add(panel);
		
		add(north_panel, BorderLayout.NORTH);
		matrix = new MatrixOfButtons();
		
		add(matrix, BorderLayout.CENTER);
		add(client_info, BorderLayout.LINE_END);		
		
		movie_field.addItemListener(new ItemListener() {
			public int rows;
			public int columns;
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				BookingFrame root_frame = (BookingFrame) SwingUtilities.getWindowAncestor(getRootPane());
				
				if(e.SELECTED == 1 ) {    //if the user selected an item
					JComboBox<Movie> source = (JComboBox<Movie>) e.getSource();
					root_frame.repaint();
					if (source.getSelectedItem() == null) {  //if the field is empty
		
						matrix.removeAll();
						matrix.show_image = true;
						matrix.repaint();
						return;
						
					}
					Movie selected_movie = (Movie) e.getItem();
					Room room = (Room) map.get(selected_movie);
					rows = room.getRows();
					columns = room.getColumns();
					seat_is_selected = false;
					root_frame.remove(matrix); 
					matrix.removeAll();
					matrix = new MatrixOfButtons(rows, columns);
					matrix.repaint();
					matrix.setVisible(true);
					type_field.setSelectedItem(selected_movie.getType());
					
					System.out.println(room.getId());

					for (int i = 0; i < rows; i ++)
						for (int j = 0; j < columns; j ++) {
							GridButton button = new GridButton(i, j);
							button.setBackground(room.isOccupied(i, j) ? Color.RED : Color.GREEN);
							button.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) { //when a grid button is pressed
									GridButton source = (GridButton) e.getSource();
									if (source.getBackground() == Color.GREEN) {
										seat_is_selected = true;
										delete_last_button();
										source.setBackground(Color.DARK_GRAY);
										selected_row = source.row;
										selected_column = source.column;
										return;
									} 
									
									if (source.getBackground() == Color.RED){
										JOptionPane.showMessageDialog(root_frame, "The seat is occupied..");
										return;
									}
									
									if (source.getBackground() == Color.DARK_GRAY) {
										seat_is_selected = false;
										source.setBackground(Color.GREEN);
										return;
									}
									
								}
							});
							matrix.add(button);
							matrix.buttons[i][j] = button;
						}
					
					root_frame.add(matrix, BorderLayout.CENTER);
					root_frame.pack();
				}
				if (e.DESELECTED == 2)
					root_frame.repaint();
			}

			void delete_last_button () {
				for (int i = 0; i < rows; i ++)
					for (int j = 0; j < columns; j ++)
						if (matrix.buttons[i][j].getBackground() == Color.DARK_GRAY) 
							matrix.buttons[i][j].setBackground(Color.GREEN);    
			}
		});
		
		type_field.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.SELECTED == 1) {
					repaint();
					
				}
				
			}
		});
	
		filter_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Utility.Type type = (Utility.Type) type_field.getSelectedItem();			
				movie_field.removeAllItems();
				if (type == null)
					movie_field.insertItemAt(null, 0);
				if (type != null){
					for (Movie movie: movie_list)
						if (movie.getType() == type)
							movie_field.addItem(movie);
				} else {
					for (Movie movie: movie_list)
						movie_field.addItem(movie);
				}
			}
		});
		
		reserve_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				BookingFrame root_frame = (BookingFrame) SwingUtilities.getWindowAncestor(getRootPane()); //getting the root frame
				Movie selected_movie = (Movie) movie_field.getSelectedItem();
				Room selected_room = map.get(selected_movie); 
				
				if (movie_field.getSelectedItem() == null || !movie_field.isEnabled()) {
					JOptionPane.showMessageDialog(root_frame, "You need to select a movie from the list..");
					return;
				}
				
				if (first_name_field.getText().length() == 0 && last_name_field.getText().length() == 0) {
					JOptionPane.showMessageDialog(root_frame, "You need to specify at least one name..");
					return;
				}
				
				if (!seat_is_selected) {
					JOptionPane.showMessageDialog(root_frame, "You need to select a seat..");
					return;
				}
				
				Client selected_client = new Client(first_name_field.getText(), last_name_field.getText(), (Utility.Exc) exc_field.getSelectedItem());
				Ticket ticket = cinema.reserve_seat(selected_row, selected_column, selected_room, selected_movie, selected_client);
				if (ticket != null) { //if the reservation has been successfully
					OccupiedSeat selected_seat = new OccupiedSeat(selected_room.getId(), selected_row, selected_column);
					
					ArrayList<OccupiedSeat> singleton_seat_list = new ArrayList<OccupiedSeat>();
					ArrayList<Client> singleton_client_list = new ArrayList<Client>();
					ArrayList<Ticket> singleton_ticket_list = new ArrayList<Ticket>();
					
					singleton_seat_list.add(selected_seat);
					singleton_client_list.add(selected_client);
					singleton_ticket_list.add(ticket);
					//we are writing the data in files 
					Utility.write_to_file("data/clients.csv", singleton_client_list); 
					Utility.write_to_file("data/occupied_seats.csv", singleton_seat_list);
					Utility.write_to_file("data/tickets.csv", singleton_ticket_list);
					
					matrix.buttons[selected_row][selected_column].setBackground(Color.RED);  //we set the red color of the selected seat in matrix 
					Ticket.setNr_of_tickets(Ticket.getNr_of_tickets());
					root_frame.dispose(); 	//we close the booking frame
					new TicketFrame(ticket);//visual representation of the reserved ticket
				}
			}
		});
		
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				repaint();
			}
		});
		
	}

	public static Cinema getCinema () {
		return cinema;
	}
	
	public static void setCinema(Cinema cinema) {
		BookingFrame.cinema = cinema;
	}
	
	public void getData() {
		ArrayList<OccupiedSeat> occupied_seats = Utility.read_from_file("data/occupied_seats.csv", OccupiedSeat.class);
		for (OccupiedSeat seat: occupied_seats) {
			cinema.getHall().get(seat.getRoom()).getSeats()[seat.getRow()][seat.getColumn()] = true;
		}
		try {
			int nr = Files.readAllLines(Paths.get("data/tickets.csv")).size();
			Ticket.setNr_of_tickets(nr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(0, 140, 648, 140);
		g.drawLine(408, 0, 408, 318);
	}
	
}
