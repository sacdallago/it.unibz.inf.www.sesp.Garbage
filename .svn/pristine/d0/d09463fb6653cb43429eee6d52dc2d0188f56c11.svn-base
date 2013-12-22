package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import textmanager.Definitions;


public class SelectionFrame extends JFrame {
	Definitions definitions = new Definitions();
	public SelectionFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 430, 400);
		this.setLayout(null);
		
		
		//----------Menu-----------
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Options");
		menuBar.add(menu);
		
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"Authors :    Stanko Franz Ovkaric & Christian Dallago\n"
								,"About", 1);
			}
		});
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JMenuItem cities = new JMenuItem("List of Cities");
		cities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s ="";
				for(String city : definitions.listOfCities()){
					s+=city+"\n";//adding the cities in one variable
				}
				//Lista-delle-citta Frame creation
				JFrame f = new JFrame("Lista delle citt\u00E0");
				f.setPreferredSize(new Dimension(300, 300));
				f.setBounds(500, 200, 300, 300);
				JPanel p = new JPanel();
				p.setBackground(Color.WHITE);
				JTextArea ta = new JTextArea();
				ta.setBounds(0, 0, 300, 300);
				ta.setText(s);
				ta.setEditable(false);
				p.add(ta);
				JScrollPane scroll = new JScrollPane(p);
				scroll.setBounds(0, 0, 300, 300);
				f.add(scroll);
				f.setLayout(null);
				f.pack();
				f.setVisible(true);
							}
		});
		menu.add(cities);
		menu.addSeparator();
		menu.add(about);
		menu.addSeparator();
		menu.add(exit);
		setJMenuBar(menuBar);
		
		//-------------immagini-----------
		BufferedImage myPicture;
		BufferedImage myPicture2;
		BufferedImage myPicture3;
		try {
			//----------Aggiungi-----------
			//Img
			myPicture = ImageIO .read(new File("src/imgs/3.png"));
			JButton picLabel = new JButton(new ImageIcon(myPicture));
			picLabel.setBounds(110, 70, 188, 64);
			//comportamento
			picLabel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OperationFrame frame2 = new OperationFrame(1,0);
					frame2.setTitle("Inserimento di una nuova citt\u00E0");
					frame2.setVisible(true);
				}});
			
			//----------Modifica-----------
			myPicture2 = ImageIO .read(new File("src/imgs/2.png"));
			JButton picLabel2 = new JButton(new ImageIcon(myPicture2));
			picLabel2.setBounds(110, 140, 188, 64);
			picLabel2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object[] options1 = {"OK"};
					JPanel panel = new JPanel();
					panel.add(new JLabel("Inserisci il CAP della citt\u00E0 desiderata"));
					JTextField textField = new JTextField(5);
					panel.add(textField);
					int CAP=0;
					int result = JOptionPane.showOptionDialog(null, panel,
							"Inserisci un CAP", JOptionPane.OK_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options1, null);
					if (result == JOptionPane.OK_OPTION) {
						try{
							CAP = Integer.parseInt(textField.getText());
							//Se l'input � giusto
							if (CAP>10000 && CAP<=99999){
								Definitions f = new Definitions();
								//Crea il frame
								if(f.contains(CAP)){OperationFrame frame2 = new OperationFrame(2,CAP);
								frame2.setTitle("Modifica di una citt\u00E0");
								frame2.setVisible(true);
								}//Se non � trovato
								else{JOptionPane.showMessageDialog(null,
										"CAP non trovato!"
										,"Error", 2);}
							}else{//Se non � valido 
								JOptionPane.showMessageDialog(null,
									"CAP non valido: valore numerico non corrispondente ad un CAP"
									,"Error", 2);}
						}catch(Exception notNumber){
							JOptionPane.showMessageDialog(null,
									"CAP non valido: valore inserito non numerico"
									,"Error", 2);
						}
					}	
				}});
			
			//----------Elimina-----------
			myPicture3 = ImageIO .read(new File("src/imgs/1.png"));
			JButton picLabel3 = new JButton(new ImageIcon(myPicture3));
			picLabel3.setBounds(110, 210, 188, 64);
			picLabel3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object[] options1 = {"OK"};
					JPanel panel = new JPanel();
					panel.add(new JLabel("Inserisci il CAP della citt\u00E0 desiderata"));
					JTextField textField = new JTextField(5);
					panel.add(textField);
					int CAP=0;
					int result = JOptionPane.showOptionDialog(null, panel,
							"Inserisci un CAP", JOptionPane.OK_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options1, null);
					if (result == JOptionPane.OK_OPTION) {
						try{
							CAP = Integer.parseInt(textField.getText());
							//Se l'input � giusto
							if (CAP>10000 && CAP<=99999){
								Definitions f = new Definitions();
								//Se esiste il cap, apri il Frame
								if(f.contains(CAP)){OperationFrame frame2 = new OperationFrame(3,CAP);
								frame2.setTitle("Eliminazione di una citt\u00E0");
								frame2.setVisible(true);}
								//Se non trovato 
								else{JOptionPane.showMessageDialog(null,
										"CAP non trovato!"
										,"Error", 2);}
								//Se non valido
							}else{JOptionPane.showMessageDialog(null,
									"CAP non valido: valore numerico non corrispondente ad un CAP"
									,"Error", 2);}
						}catch(Exception notNumber){
							JOptionPane.showMessageDialog(null,
									"CAP non valido: valore inserito non numerico"
									,"Error", 2);
						}
						
					}	
				}});
			add(picLabel);
			add(picLabel2);
			add(picLabel3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
