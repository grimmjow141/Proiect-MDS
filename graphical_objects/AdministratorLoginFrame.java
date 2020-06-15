package graphical_objects;


import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.*;


public class AdministratorLoginFrame extends JFrame{

	public AdministratorLoginFrame() {
		AdministratorLoginFrame root = this;
		
		setName("Login");
		setLocation(420, 340);
		setVisible(true);
		//setPreferredSize(new Dimension(700, 280));
		setResizable(false);
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(layout);
		
		JLabel label_1 = new JLabel("Username: ");
		JLabel label_2 = new JLabel("Password: ");
		
		JTextField user_field = new JTextField(15);
		JPasswordField pass_field = new JPasswordField(15);
		
		JButton submit = new JButton("Submit");
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("./images/login_icon.png"));
			Image scaled_image = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			
			ImageIcon icon = new ImageIcon(scaled_image);
			submit.setIcon(icon);
		} catch (IOException e) {
			System.out.println("Failed to load login icon image..");
			e.printStackTrace();
		}
		
		label_1.setLabelFor(user_field);
		label_2.setLabelFor(pass_field);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 2, 2);
		c.gridx = 0;
		c.gridy = 0;
		add(label_1, c);
		
		c.gridx = 1;
		c.gridy = 0;
		add(user_field, c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(label_2, c);
		
		c.gridx = 1;
		c.gridy = 1;
		add(pass_field, c);
		
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.EAST;
		
		add(submit, c);
		
		pack();
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
		        		String password = new String(decrypted);
		        		if ( (user_field.getText().equals("admin")) && (pass_field.getText().equals(password)) ) {
							root.dispose();
							new AdministratorPage();
						}
						
						else 
							JOptionPane.showMessageDialog(root, "Invalid username or password, try again..");
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
				
			}
		});
	}

}
