package graphical_objects;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main_objects.Cinema;

public class StartMenu extends JPanel {

	public StartMenu() {
		//BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		FlowLayout layout = new FlowLayout();
		layout.setVgap(40);
		layout.preferredLayoutSize(this);
		
		BookingFrame.setCinema(new Cinema());
		BookingFrame.getCinema().add_new_room(10, 5);
		BookingFrame.getCinema().add_new_room(10, 9);
		BookingFrame.getCinema().add_new_room(5, 3);
		BookingFrame.getCinema().add_new_room(5, 6);
		
		
		setLayout(layout);
		setBorder(new EmptyBorder(150, 50, 20, 50));
		JButton b1 = new JButton("Book a ticket..");
		JButton b2 = new JButton("Admin settings..");
		
		b1.setSize(25, 10);
		b2.setSize(25, 10);
		
		add(b1);
		add(b2);
		
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new BookingFrame();
			}
		});
		
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdministratorLoginFrame();
			}
		});
		
	}

	@Override
	public void paintComponent(Graphics g) { //set the background of the panel
		
		try {
			g.drawImage(ImageIO.read(new File("./images/gradient.jpg")), 0, 0, null);
		} catch (IOException e) {
			System.out.println("Failed to load the background image..");
			e.printStackTrace();
		}
	}

}
