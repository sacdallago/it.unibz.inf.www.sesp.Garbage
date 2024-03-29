package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
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
import textmanager.FTPBridge;
import controller.City;
import controller.GarbageType;

public class OperationFrame extends JFrame {
	private Definitions definitions = new Definitions();
	private int eventualCAP;

	JPanel middlePanel;
	JFormattedTextField capField;
	JTextField cityField;

	private JTextField textCAP;
	private JTextField textCitta;
	private JLabel lblCAP;
	private JLabel lblCitta;
	private LinkedList<CategoryPanel> categories = new LinkedList<CategoryPanel>();
	private JScrollPane scroll;

	private int Selected = 0;

	private int layoutCounter = 3;

	public OperationFrame(int selected, int CAP) {
		this.Selected = selected;
		this.eventualCAP = CAP;
		setBounds(200, 200, 470, 460);

		// -----------------MENU---------------------
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

		setLayout(null);

		// -----------------Zona Superiore---------------------
		textCAP = new JTextField(5);
		textCAP.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(textCAP.getText().length()>5){
					textCAP.setText(textCAP.getText().substring(0, 4));
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		textCAP.setBounds(20, 30, 86, 20);

		lblCAP = new JLabel("CAP");
		lblCAP.setBounds(20, 15, 43, 14);

		textCitta = new JTextField();
		textCitta.setBounds(116, 30, 125, 20);

		lblCitta = new JLabel("Citt\u00E0");
		lblCitta.setBounds(116, 15, 64, 14);

		// -----------------Zona Media---------------------
		middlePanel = new JPanel();
		middlePanel.setBackground(Color.WHITE);
		middlePanel.setLayout(new GridLayout(layoutCounter, 0, 0, 0));
		middlePanel.setPreferredSize(new Dimension(410, 100));
		
		if (Selected == 1) {//If adding, display one category slot
			CategoryPanel firstcategory = new CategoryPanel();
			categories.push(firstcategory);
			middlePanel.add(firstcategory);
		}
		
		scroll = new JScrollPane(middlePanel);
		scroll.setBounds(20, 61, 420, 300);

		// -----------------Zona Inferiore---------------------
		
		//pulsante Nuova Categoria
		JButton btnNuovaCategoria = new JButton("Nuova Categoria");
		btnNuovaCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a new CategoryPanel
				CategoryPanel category = new CategoryPanel();
				//put it in the linked list (to be able to adress it)
				categories.push(category);
				//and put it in the panel
				middlePanel.add(category);
				//Resize things out
				Rectangle r = middlePanel.getBounds();
				layoutCounter++;
				middlePanel.setLayout(new GridLayout(layoutCounter, 0, 0, 0));
				middlePanel.setPreferredSize(new Dimension(r.width,
						r.height + 100));
				middlePanel.revalidate();
			}
		});
		btnNuovaCategoria.setBounds(20, 370, 150, 23);

		
		//pulsante GO!
		JButton go = new JButton();
		//In base all'operazione, cambia il suo testo
		if (Selected == 1) {
			go.setText("Aggiungi questa Citta");
		} else if (Selected == 2) {
			go.setText("Modifica questa Citta");
		} else if (Selected == 3) {
			go.setText("Elimina questa Citta");
		}
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//variabile che controlla se si vuole(1) o no(0) eventualmente sovrascrivere
				int control = 1;
				// Se si aggiunge o si modifica
				if (Selected == 1 || Selected == 2) {
					//Se esiste gia, chiediamo se vuole sovrascrivere
					if (definitions.contains(Integer.parseInt(textCAP.getText()))) {
						int reply = JOptionPane.showConfirmDialog(null,
								"La citt\u00E0 esiste gia, sovrascriverla?", "",
								JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {} 
						else {//Se non vuole sovrascrivere 
							control = 0;//Settiamo la variabile a 0
							JOptionPane.showMessageDialog(null,
									"Modifiche NON salvate");
						}
					}
					if (control == 1) {//Se la variabile � a 1, cio� se le operazioni devono avere luogo
						try {
							//Creiamo un nuovo oggetto citta
							City newCity = new City(Integer.parseInt(textCAP
									.getText()), textCitta.getText());
							//per ogn'uno dei pannelli CategoryPanel instanziati
							for (CategoryPanel x : categories) {
								//Se non ci sono campi vuoti
								if (!x.textCategoria.getText().equals("")
										&& !x.textColour.getText().equals("")
										&& !x.textOggetti.getText().equals("")) {
									//Creiamo un nuovo tipo
									newCity.createType(
											x.textCategoria.getText(),
											x.textColour.getText());
									String items[] = x.textOggetti.getText()
											.split(";");
									//e inseriamo gli oggetti
									for (int i = 0; i < items.length; i++) {
										newCity.insertItem(
												x.textCategoria.getText(),
												items[i]);
									}
								} else {
									//altrimenti fa vedere un messaggio di errore
									JOptionPane.showMessageDialog(null,
											"Perfavore riempire tutti i campi",
											"Error", 2);
								}
							}
							//Se esiste gia la citta
							if (definitions.contains(newCity.getCAP())) {
								//La elimina e poi la aggiunge
								definitions.removeCity(newCity.getCAP());
								definitions.addCity(newCity);
								//poi creiamo un file xml
								File newXML = definitions.writeXML();
								//e facciamo il collegamento con il server per inviare il file
								String srv = JOptionPane.showInputDialog("FTP connection server:");
								String usr = JOptionPane.showInputDialog("FTP connection username:");
								String pss = JOptionPane.showInputDialog("FTP connection password:");
								FTPBridge ftpUploader = new FTPBridge(
										srv, usr,
										pss);
								String x = newXML.getAbsolutePath().replaceAll(
										"[\\\\]", "\\\\\\\\");
								//lo inviamo
								ftpUploader
										.uploadFile(x, "Garbage.xml", "XML/");
								ftpUploader.disconnect();
								//rassicuriamo l'utente dell'avvenuto salvataggio
								JOptionPane.showMessageDialog(null,
										"Modifiche salvate", "Salvataggio", 1);
							} else {
								//Se � una citta nuova, fa le stesse cose ._.
								definitions.addCity(newCity);
								File newXML = definitions.writeXML();
								String srv = JOptionPane.showInputDialog("FTP connection server:");
								String usr = JOptionPane.showInputDialog("FTP connection username:");
								String pss = JOptionPane.showInputDialog("FTP connection password:");
								FTPBridge ftpUploader = new FTPBridge(
										srv, usr,
										pss);
								String x = newXML.getAbsolutePath().replaceAll(
										"[\\\\]", "\\\\\\\\");
								System.out.println(x);
								ftpUploader
										.uploadFile(x, "Garbage.xml", "XML/");
								ftpUploader.disconnect();
								JOptionPane.showMessageDialog(null,
										"Modifiche salvate", "Salvataggio", 1);
							}
						} catch (Exception voidFields) {
							//Se qualcosa fallisce, probabilmente manca riempire qualche campo
							JOptionPane.showMessageDialog(null,
									"Perfavore inserire CAP e Citta", "Error",
									2);
						}
					}
					//Se si elimina
				} else if (Selected == 3) {
					//Chiediamo conferma
					int reply = JOptionPane.showConfirmDialog(null,
							"Eliminare questa citt\u00E0 definitivamente?", "",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						//Eliminiamo la citta
						definitions.removeCity(eventualCAP);
						//Riscriviamo il file xml
						File newXML = definitions.writeXML();
						//Facciamo la connessione e trasferiamo il file
						String srv = JOptionPane.showInputDialog("FTP connection server:");
						String usr = JOptionPane.showInputDialog("FTP connection username:");
						String pss = JOptionPane.showInputDialog("FTP connection password:");
						FTPBridge ftpUploader = new FTPBridge(
								srv, usr,
								pss);
						String x = newXML.getAbsolutePath().replaceAll(
								"[\\\\]", "\\\\\\\\");
						System.out.println(x);
						ftpUploader.uploadFile(x, "Garbage.xml", "XML/");
						ftpUploader.disconnect();
						//confermiamo l'avvenuta eliminazione
						JOptionPane.showMessageDialog(null, "Citta eliminata");
					} else {
						//rassicura l'utente della non eliminazione della citta
						JOptionPane.showMessageDialog(null,
								"Citta NON eliminata");
					}

				}
			}
		});
		go.setBounds(264, 370, 175, 23);

		// -----------Modify/Eliminate fields fulfill----------

		//Se si vuole modificare o eliminare una citta
		if (Selected == 2 || Selected == 3) {
			//Creiamo un nuovo oggetto di tipo City partendo dal cap
			City modifyCity = definitions.getCity(eventualCAP);
			//inseriamo il cap e il nome nei campi
			this.textCAP.setText(modifyCity.getCAP() + "");
			this.textCitta.setText(modifyCity.getCityName());
			//creiamo e riempiamo i diversi pannelli di categorie
			for (GarbageType type : modifyCity.getListOfGarbage()) {
				CategoryPanel category = new CategoryPanel();
				categories.push(category);
				middlePanel.add(category);
				Rectangle r = middlePanel.getBounds();
				layoutCounter++;
				middlePanel.setLayout(new GridLayout(layoutCounter, 0, 0, 0));
				middlePanel.setPreferredSize(new Dimension(r.width,
						r.height + 100));

				category.textCategoria.setText(type.getType());
				category.textColour.setText(type.getBinColor());
				String items = "";
				for (String item : type.getItems()) {
					items += item + ";";
				}
				category.textOggetti.setText(items);
				//Se si vuole eliminare, non si possono modificare le informazioni
				if (Selected == 3) {
					category.textCategoria.setEnabled(false);
					category.textColour.setEnabled(false);
					category.textOggetti.setEnabled(false);
				}
			}
			//Se si vuole eliminare, non si possono modificare le informazioni
			if (Selected == 3) {
				this.textCAP.setEnabled(false);
				this.textCitta.setEnabled(false);
				btnNuovaCategoria.setVisible(false);
			}
		}

		// -----------Adding things to the frame
		add(go);
		add(btnNuovaCategoria);
		add(textCAP);
		add(lblCAP);
		add(textCitta);
		add(lblCitta);
		add(scroll, BorderLayout.CENTER);
	}

}
