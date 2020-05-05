package graphical_objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main_objects.Ticket;

public class TicketFrame extends JFrame {

	public TicketFrame(Ticket ticket) {
		
		setSize(400, 270);
		setLocation(470, 270);
		setVisible(true);
		setResizable(false);
		setLayout(null);
		String seat_aux_string = String.format("row: %d / column: %d", ticket.getOccupiedSeat().getRow(), ticket.getOccupiedSeat().getColumn());
		Background background = new Background(ticket.getKey_code(), ticket.getMovie().getName(), ticket.getClient().getFull_name(), 
								              (ticket.getClient().getExc() != null) ? (ticket.getClient().getExc().toString()) : "---", 
								               String.valueOf(ticket.getOccupiedSeat().getRoom()), seat_aux_string, String.valueOf(ticket.getPrice()) + "$");
		add(background);
		
	}
	
	static class Background extends JLabel {
		
		public static String key_code;
		public static String[] fields_values;
		public static int nr_of_fields;
		
		Background (String key_code, String... fields_values) {
			
			Background.fields_values = fields_values;
			Background.key_code = key_code;
			Background.nr_of_fields = fields_values.length;
			
			try {
				BufferedImage img = ImageIO.read(new File("images/empty-ticket.jpg"));
				Image scaled_img = img.getScaledInstance(380, 250, Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(scaled_img);
				setIcon(icon);
				setBounds(2, -5, 380, 250);
				
			} catch (IOException e) {
				System.out.println("Failed to load ticket background image..");
				e.printStackTrace();
			}
			//repaint();
		}
		@Override
		public void paintComponent(Graphics g) {			
			
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setFont(new Font("Arial", Font.BOLD, 25));
			g2.rotate(Math.toRadians(90.0), 60, 40);
			g2.setColor(Color.RED);
			g2.drawString(key_code, 60, 60);
			g2.rotate(Math.toRadians(-180.0), 125, -90);
			g2.drawString(key_code, 23, 60);
			g2.rotate(Math.toRadians(90.0), 50, 50); //we switch to normal orientation
			
			String[] fields_names = {"Movie: ", "Buyer: ", "Exc: ", "Room: ", "Seat: ", "Price: "};//, "Seat: ", "Ticket price: "};
			if (fields_names.length != fields_values.length)
				return;
			
			int init_x = -210;
			int init_y = -60;
			for (int i = 0; i < nr_of_fields; i ++) {
				
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("Cambria", Font.TRUETYPE_FONT, 18));
				g2.drawString(fields_names[i], init_x, init_y);
		
				g2.setColor(Color.DARK_GRAY);
				g2.setFont(new Font("Cambria", Font.TRUETYPE_FONT, 16));
				g2.drawString(fields_values[i], init_x + 55, init_y);
				
				init_x = -210;
				init_y += 30;
			}
		}
	}
}

