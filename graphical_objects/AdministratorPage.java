package graphical_objects;

import java.awt.Dimension;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import main_objects.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AdministratorPage extends JFrame{

	private static String password;
	
	public AdministratorPage(){
        try {
        	Scanner scanner = new Scanner(new File("data/pass.txt"));
        	if (scanner.hasNextLine()) {
        		String encr_pass = scanner.nextLine();
        		//DECRYPTION
        		String key = "cepoi";
        		SecretKeySpec skeyspec = new SecretKeySpec(key.getBytes(),"Blowfish");
        		Cipher cipher=Cipher.getInstance("Blowfish");
        		cipher.init(Cipher.DECRYPT_MODE, skeyspec);
        		byte[] decrypted=cipher.doFinal(encr_pass.getBytes());
        		password = new String(decrypted);
        	}
			scanner.close();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalBlockSizeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BadPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
		setVisible(true);
		setSize(250, 150);
		setLocation(350, 170);
		BoxLayout my_layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		//FlowLayout my_layout = new FlowLayout();
		//my_layout.setVgap(40);
		getContentPane().setLayout(my_layout);
		//getContentPane().setBounds(MAXIMIZED_HORIZ, 75, 100, 150);
		
		JButton bfilme = new JButton("Add movies");
        JButton afilme = new JButton("Delete movies");   
        JButton change_password = new JButton("Change password");
        
        bfilme.setSize(new Dimension(70, 25));
        afilme.setSize(new Dimension(70, 25));
        change_password.setSize(new Dimension(70, 25));
        
        add(bfilme);
        add(afilme);      
        add(change_password);
        
        JFrame adaugafilme = new JFrame("Add movie");
        adaugafilme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adaugafilme.setLocation(400, 200);
        JFrame stergefilme = new JFrame("Delete movie");
        stergefilme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        stergefilme.setLocation(400, 200);
        JFrame change_pass = new JFrame("Change password");
        change_pass.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        change_pass.setLocation(400, 200);
        
        bfilme.addActionListener(new ActionListener() { //add movies

        	public void actionPerformed(ActionEvent e) {      

        		adaugafilme.setContentPane(new JPanel());
        		adaugafilme.setSize(500,500);  
        		GridBagLayout layout = new GridBagLayout();
        		adaugafilme.getContentPane().setLayout(layout);  
        		adaugafilme.setVisible(true);

        		JLabel label_1 = new JLabel("Title: ");
        		JLabel label_2 = new JLabel("Type: ");
        		JLabel label_3 = new JLabel("Begin hour: ");
        		JLabel label_4 = new JLabel("End hour: ");

        		JTextField titlu = new JTextField(20);
        		JComboBox<Utility.Type> gen = new JComboBox<Utility.Type>();
        		JTextField oinceput = new JTextField(10);
        		JTextField ofinal = new JTextField(10);
     
        		JButton adauga = new JButton("Add");
        		
        		gen.addItem(Utility.Type.ACTION);
        		gen.addItem(Utility.Type.ADVENTURE);
        		gen.addItem(Utility.Type.COMEDY);
        		gen.addItem(Utility.Type.DRAMA);
        		gen.addItem(Utility.Type.THRILLER);
        		
        		label_1.setLabelFor(titlu);
        		label_2.setLabelFor(gen);
        		label_3.setLabelFor(oinceput);
        		label_4.setLabelFor(ofinal);
        		
        		GridBagConstraints c = new GridBagConstraints();
        		c.fill = GridBagConstraints.VERTICAL;
        		c.insets = new Insets(30, 0, 30, 0);
        		c.gridx = 0;
        		c.gridy = 0;
        		adaugafilme.add(label_1, c);
        		
        		c.gridx = 1;
        		c.gridy = 0;
        		adaugafilme.add(titlu, c);
        		
        		c.gridx = 0;
        		c.gridy = 1;
        		adaugafilme.add(label_2, c);
        		
        		c.gridx = 1;
        		c.gridy = 1;
        		adaugafilme.add(gen, c);
        		
        		c.gridx = 0;
        		c.gridy = 2;
        		adaugafilme.add(label_3, c);
        		
        		c.gridx = 1;
        		c.gridy = 2;
        		adaugafilme.add(oinceput, c);
        		
        		c.gridx = 0;
        		c.gridy = 3;
        		adaugafilme.add(label_4, c);
        		
        		c.gridx = 1;
        		c.gridy = 3;
        		adaugafilme.add(ofinal, c);

        		c.gridx = 0;
        		c.gridy = 4;
        		c.gridwidth = 2;
        		adaugafilme.add(adauga, c);
        		adauga.addActionListener(new ActionListener() {  
        			public void actionPerformed(ActionEvent e) {
        				try {
							if (Utility.hour_format.parse(oinceput.getText()).compareTo(Utility.hour_format.parse(ofinal.getText())) >= 0)
								JOptionPane.showMessageDialog(adaugafilme, "Invalid hours..");
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        				ArrayList<Movie> list = new ArrayList<Movie>();
        				list.add(new Movie(titlu.getText(),(Utility.Type)gen.getSelectedItem(),oinceput.getText(),ofinal.getText()));
        				Utility.write_to_file("data/movies.csv", list);
        				adaugafilme.dispose();
        				JOptionPane.showMessageDialog(adaugafilme, "The movie has been added succesfully!");
        			}  
        		});

        	} 

        });
           
        afilme.addActionListener(new ActionListener(){ //delete movie from the list
        	
        	public void actionPerformed(ActionEvent e) {
        		
                stergefilme.setSize(350,150);  
                GridBagLayout layout = new GridBagLayout();
                stergefilme.getContentPane().setLayout(layout);  
                stergefilme.setVisible(true);
                       
                JLabel label_1 = new JLabel("Title: ");                
                ArrayList<Movie> movie_list = Utility.read_from_file("data/movies.csv", Movie.class);
                JComboBox<Movie> movie_field = new JComboBox<Movie>();
        		for (Movie it: movie_list)
        			movie_field.addItem(it);
                label_1.setLabelFor(movie_field);                
                JButton sterge = new JButton("Delete");
                
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.VERTICAL;
                c.gridx = 0;
                c.gridy = 0;
                stergefilme.add(label_1, c);
                
                c.gridx = 1;
                c.gridy = 0;
                stergefilme.add(movie_field, c);
                
                c.gridx = 0;
                c.gridy = 1;
                c.gridwidth = 2;
                stergefilme.add(sterge, c);
                
                
                sterge.addActionListener(new ActionListener() {  
                    public void actionPerformed(ActionEvent e) {       
                        ArrayList<Movie> filme = Utility.read_from_file("data/movies.csv", Movie.class);
                        Movie movie = (Movie) movie_field.getSelectedItem();
                        String name = movie.getName();
    		            for (Movie it: filme) {
    		            	
    		                 if(it.getName().equals(name)) {
    		                        filme.remove(it);
    		                        try {
										FileWriter writer = new FileWriter("data/movies.csv"); //deleting the content of the file
										Utility.write_to_file("data/movies.csv", filme);
										JOptionPane.showMessageDialog(stergefilme, "The movie was deleted from the list..");
	    		                        stergefilme.dispose();
	    		                        writer.close();
	    		                        return;
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
    		                       
    		                 }
    		            }
    		            stergefilme.dispose();
    		            JOptionPane.showMessageDialog(stergefilme, "The movie was not found!");
                    }  
                });
                
        	}
            
        });	
    
        change_password.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				change_pass.setSize(350,170);  
                GridBagLayout layout = new GridBagLayout();
                change_pass.getContentPane().setLayout(layout);  
                change_pass.setVisible(true);
                
                JLabel label_1 = new JLabel("Old password: ");
                JLabel label_2 = new JLabel("New password: ");
                JLabel label_3 = new JLabel("Repeat password: ");
                
                JTextField field_1 = new JTextField(10);
                JTextField field_2 = new JTextField(10);
                JTextField field_3 = new JTextField(10);
                
                
                JButton change_button = new JButton("Enter");
                
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.VERTICAL;
                //c.insets = new Insets(10, 5, 10, 5);
                c.gridx = 0;
                c.gridy = 0;
                change_pass.add(label_1, c);
                
                c.gridx = 1;
                c.gridy = 0;
                change_pass.add(field_1, c);
                
                c.gridx = 0;
                c.gridy = 1;
                change_pass.add(label_2, c);
                
                c.gridx = 1;
                c.gridy = 1;
                change_pass.add(field_2, c);
                
                c.gridx = 0;
                c.gridy = 2;
                change_pass.add(label_3, c);
                
                c.gridx = 1;
                c.gridy = 2;
                change_pass.add(field_3, c);
                
                c.gridx = 0;
                c.gridy = 3;
                c.gridwidth = 2;
                change_pass.add(change_button, c);
                
                change_button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (field_1.getText().compareTo(password) != 0)
							JOptionPane.showMessageDialog(change_pass, "You entered the wrong old password, try again..");
						else if (field_2.getText().compareTo(field_3.getText()) != 0)
							JOptionPane.showMessageDialog(change_pass, "The passwords aren't matching, try again..");
						else
							try {
								FileWriter writer = new FileWriter("data/pass.txt");
								//ENCRYPTION
								String key = "cepoi";
				        		SecretKeySpec skeyspec = new SecretKeySpec(key.getBytes(),"Blowfish");
				        		Cipher cipher = Cipher.getInstance("Blowfish");
				        		cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
				        		byte[] encrypted=cipher.doFinal(field_2.getText().getBytes());
				        		writer.write(new String(encrypted));
				        		writer.close();
				        		JOptionPane.showMessageDialog(change_pass, "Password changed succesfully!");
				        		change_pass.dispose();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (NoSuchAlgorithmException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (NoSuchPaddingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (InvalidKeyException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IllegalBlockSizeException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (BadPaddingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
					}
				});
				
			}
		});
	}	
}
