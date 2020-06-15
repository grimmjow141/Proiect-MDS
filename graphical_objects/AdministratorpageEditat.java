package graphical_objects;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;

import main_objects.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AdministratorPage extends JFrame{

	AdministratorPage(){
        
		setVisible(true);
		setSize(500, 300);
		setLocation(350, 170);
		BoxLayout my_layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(my_layout);
		
		JButton bfilme = new JButton("Adauga Filme");
        JButton afilme = new JButton("Sterge Filme");
        JButton acamere = new JButton("Adauga Camere");
        JButton bcamere = new JButton("Sterge Camere");
        JButton abilete = new JButton("Locuri Ocupate");        
        
        add(bfilme);
        add(afilme);
        add(acamere);
        add(bcamere);
        add(abilete);        
        
        JFrame adaugafilme = new JFrame("Add movie");
        adaugafilme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adaugafilme.setLocation(400, 200);
        JFrame stergefilme = new JFrame("Delete movie");
        stergefilme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        stergefilme.setLocation(400, 200);
	JFrame adaugacamere = new JFrame("Add room");
	adaugacamere.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	adaugacamere.setLocation(400, 200);
	JFrame stergecamere = new JFrame("Delete room");
	stergecamere.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	stergecamere.setLocation(400, 200);
	JFrame modificabilete = new JFrame("Modify Tickets");
	modificabilete.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	modificabilete.setLocation(400, 200);

        
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

        		JTextField titlu = new JTextField(25);
        		JComboBox<Utility.Type> gen = new JComboBox<Utility.Type>();
        		JTextField oinceput = new JTextField(15);
        		JTextField ofinal = new JTextField(15);
     
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
        		
                stergefilme.setSize(500,500);  
                GridBagLayout layout = new GridBagLayout();
                stergefilme.getContentPane().setLayout(layout);  
                stergefilme.setVisible(true);
                 
                       
                JLabel label_1 = new JLabel("Title: ");                
                JTextField titlu = new JTextField(25);
                label_1.setLabelFor(titlu);                
                JButton sterge = new JButton("Delete");
                
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.VERTICAL;
                c.gridx = 0;
                c.gridy = 0;
                stergefilme.add(label_1, c);
                
                c.gridx = 1;
                c.gridy = 0;
                stergefilme.add(titlu, c);
                
                c.gridx = 0;
                c.gridy = 2;
                c.gridwidth = 2;
                stergefilme.add(sterge, c);
                
                
                sterge.addActionListener(new ActionListener() {  
                    public void actionPerformed(ActionEvent e) {       
                        ArrayList<Movie> filme = Utility.read_from_file("data/movies.csv", Movie.class);
                        String name = titlu.getText();
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
        acamere.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e){

			adaugacamere.setContentPane(new JPanel());
			adaugacamere.setSize(500,500);
			GridBagLayout layout = new GridBagLayout();
			adaugacamere.getContentPane().setLayout(layout);
			adaugacamere.setVisible(true);

            		JLabel label_1 = new JLabel("Randuri: ");
            		JLabel label_2 = new JLabel("Coloane: ");

            		JTextField randuri = new JTextField(25);
            		JTextField coloane = new JTextField(15);

            		label_1.setLabelFor(randuri);
            		label_2.setLabelFor(coloane);
            
                	GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.VERTICAL;
			c.gridx = 0;
			c.gridy = 0;
			modificabilete.add(label_1, c);

			c.gridx = 1;
			c.gridy = 0;
			modificabilete.add(randuri, c);

			c.gridx = 0;
			c.gridy = 1;
			modificabilete.add(label_2, c);

			c.gridx = 1;
			c.gridy = 1;
			modificabilete.add(coloane, c);
			
			c.gridx = 0;
			c.gridy = 2;
			c.gridwith = 2;
			modificabilete.add(adauga, c);
			adauga.addActionListener(new ActionListener() {  
        			public void actionPerformed(ActionEvent e) {
        				ArrayList<Room> list = new ArrayList<Room>();
					list.add(new Room(randuri.getText(),coloane.getText()));
					adaugacamere.dispose();
        				JOptionPane.showMessageDialog(adaugacamere, "The room has been added succesfully!");
			}
      		});

     	 } 

});
        bcamere.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e){

			stergecamere.setContentPane(new JPanel());
			stergecamere.setSize(500,500);
			GridBagLayout layout = new GridBagLayout();
			stergecamere.getContentPane().setLayout(layout);
			stergecamere.setVisible(true);

			JLabel label_1 = new JLabel("ID: ");

			JTextField id = new JTextField(15);
      
			label_1.setLabelFor(id);
            	
                	GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.VERTICAL;
			c.gridx = 0;
			c.gridy = 0;
			stergecamere.add(label_1, c);

			c.gridx = 1;
			c.gridy = 0;
			stergecamere.add(id, c);
			
			c.gridx = 0;
			c.gridy = 1;
			c.gridwith = 2;
			stergecamere.add(sterge, c);
			sterge.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
				ArrayList<Room> camere = Utility.read_from_file("data/room.csv", Room.class);
                       		String name = id.getText();
  		            	for (Room it: camere) {
  		            	
  		                 	if(it.getId().equals(name)) {
  		                        	camere.remove(it);
  		                        	try {
										FileWriter writer = new FileWriter("data/room.csv"); //deleting the content of the file
										Utility.write_to_file("data/room.csv", camere);
										JOptionPane.showMessageDialog(stergefilme, "The room was deleted from the list..");
	    		                        stergecamere.dispose();
	    		                        writer.close();
	    		                        return;
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
  		                       
    		                 }
  		            }
   		            stergecamere.dispose();
    		            JOptionPane.showMessageDialog(stergecamere, "The room was not found!");
                   }  
               });
      	}
          
      });
        abilete.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e){

			modificabilete.setContentPane(new JPanel());
			modificabilete.setSize(500,500);
			GridBagLayout layout = new GridBagLayout();
			modificabilete.getContentPane().setLayout(layout);
			modificabilete.setVisible(true);

			JLabel label_1 = new JLabel("ID: ");
            		JLabel label_2 = new JLabel("Randuri: ");
            		JLabel label_3 = new JLabel("Coloane: ");


			JTextField id = new JTextField(15);
            		JTextField randuri = new JTextField(25);
            		JTextField coloane = new JTextField(15);

			label_1.setLabelFor(id);
            		label_2.setLabelFor(randuri);
            		label_3.setLabelFor(coloane);
            
                	GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.VERTICAL;
			c.gridx = 0;
			c.gridy = 0;
			modificabilete.add(label_1, c);

			c.gridx = 1;
			c.gridy = 0;
			modificabilete.add(id, c);

			c.gridx = 0;
			c.gridy = 1;
			modificabilete.add(label_2, c);

			c.gridx = 1;
			c.gridy = 1;
			modificabilete.add(randuri, c);

			c.gridx = 0;
			c.gridy = 2;
			modificabilete.add(label_3, c);

			c.gridx = 1;
			c.gridy = 2;
			modificabilete.add(coloane, c);
			
			c.gridx = 0;
			c.gridy = 3;
			c.gridwith = 2;
			modificabilete.add(modifica, c);
			modifica.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ArrayList<OccupiedSeat> list = Utility.read_from_file("data/occupied_seats.csv", OccupiedSeat.class);
					String numar = id.getText();
					for(OccupiedSeat it: list)
						if(it.getRoom().equals(id))
					
                    new Room(randuri,coloane);
                }
            });
	  }
	});
    }
	
}
