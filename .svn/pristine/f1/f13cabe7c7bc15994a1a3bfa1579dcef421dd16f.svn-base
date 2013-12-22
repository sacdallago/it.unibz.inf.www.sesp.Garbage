package gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class CategoryPanel extends JPanel{
	
	JLabel lblCategoria = new JLabel("Categoria");
	JTextField textCategoria = new JTextField();
	JLabel lblColour = new JLabel("Colore");
	JTextField textColour = new JTextField();
	JLabel lblOggetti = new JLabel("Oggetti");
	JTextArea textOggetti = new JTextArea();
	/**
	 * Panel that lets the user insert the garbage categories
	 */
	public CategoryPanel(){
		
		//General info
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBackground(SystemColor.menu);
		setLayout(null);
		
		//components
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoria.setBounds(8, 11, 69, 14);
		add(lblCategoria);
		
		
		textCategoria.setBounds(84, 8, 140, 20);
		textCategoria.setColumns(10);
		add(textCategoria);
		
		
		lblColour.setHorizontalAlignment(SwingConstants.CENTER);
		lblColour.setBounds(230, 11, 59, 14);
		add(lblColour);
		
		
		textColour.setBounds(290, 8, 100, 20);
		textColour.setColumns(10);
		add(textColour);
		
		
		lblOggetti.setHorizontalAlignment(SwingConstants.CENTER);
		lblOggetti.setBounds(10, 36, 59, 14);
		add(lblOggetti);
		
		
		
		textOggetti.setFont(new Font("Arial", Font.PLAIN, 13));
		textOggetti.setText("i.e.  carne;frutta;verdura");
		textOggetti.setBounds(78, 39, 322, 50);
		textOggetti.setLayout(new FlowLayout());
		JScrollPane scroll = new JScrollPane(textOggetti);
		scroll.setBounds(78, 39, 322, 50);
		
		add(scroll);
	}
}
