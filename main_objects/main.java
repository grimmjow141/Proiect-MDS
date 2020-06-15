package main_objects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import graphical_objects.AdministratorLoginFrame;
import graphical_objects.AdministratorPage;
import graphical_objects.StartMenu;
import graphical_objects.TicketFrame;
import main_objects.Utility.*;

public class main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame("main");
		window.setLocation(400, 200);
		window.setVisible(true);
		window.setResizable(false);
		window.setSize(600, 400);
		window.setContentPane(new StartMenu()); 
	}
}
