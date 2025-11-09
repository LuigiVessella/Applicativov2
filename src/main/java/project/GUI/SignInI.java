package project.GUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import com.toedter.calendar.JCalendar;

import project.controller.Controller;

import java.awt.Color;


public class SignInI extends JFrame{

	private JComboBox comboBox_1;
	private Controller contr= new Controller();
	private List<String> te;
	
	public SignInI(Controller contr, String login) {
		getContentPane().setBackground(new Color(153, 204, 255));
	getContentPane().setLayout(null);
	
	
	
	List<String> cose = new ArrayList<String>();
	
	cose=contr.riempiCbox(login, cose);
	
	
	te = new ArrayList<String>();
		
	
	JPanel panel = new JPanel();
	panel.setBackground(new Color(153, 204, 255));
	panel.setBounds(0, 0, 577, 473);
	getContentPane().add(panel);
	panel.setLayout(null);
		
	JComboBox comboBox;
	
	JPanel panel_1 = new JPanel();
	panel_1.setBackground(new Color(153, 204, 255));
	panel_1.setBounds(10, 56, 240, 237);
	panel.add(panel_1);
	panel_1.setLayout(null);
	
	comboBox= new JComboBox(cose.toArray());
	comboBox.setFont(new Font("Cambria Math", Font.PLAIN, 13));
	comboBox.setBounds(10, 51, 142, 22);
	panel_1.add(comboBox);
	
	
	JLabel lblTest = new JLabel("Test");
	lblTest.setFont(new Font("Cambria Math", Font.PLAIN, 16));
	lblTest.setBounds(10, 114, 49, 14);
	lblTest.setVisible(false);
	panel_1.add(lblTest);
	
	
	JButton btnAddTest = new JButton("Aggiungi test");
	
	JButton btnCambiaCorso = new JButton("Cambia Corso");
	btnCambiaCorso.setFont(new Font("Cambria Math", Font.PLAIN, 14));
	btnCambiaCorso.setBackground(new Color(255, 255, 255));
	btnCambiaCorso.setBounds(10, 203, 142, 23);
	btnCambiaCorso.setVisible(false);
	panel_1.add(btnCambiaCorso);
	btnCambiaCorso.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			comboBox_1.setVisible(false);
			btnCambiaCorso.setVisible(false);
			lblTest.setVisible(false);
			btnAddTest.setVisible(false);
		}
	});
	
	
	JLabel lblCorso = new JLabel("Corso");
	lblCorso.setFont(new Font("Cambria Math", Font.PLAIN, 16));
	lblCorso.setBounds(10, 26, 90, 16);
	panel_1.add(lblCorso);
	
	JPanel panel_2 = new JPanel();
	panel_2.setBackground(new Color(153, 204, 255));
	panel_2.setBounds(315, 11, 262, 436);
	panel.add(panel_2);
	panel_2.setLayout(null);
	panel_2.setVisible(false);
	
	JButton btnAddCorso = new JButton("Aggiungi Corso");
	btnAddCorso.setBackground(new Color(255, 255, 255));
	btnAddCorso.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			panel_2.setVisible(true);
		}
	});
	btnAddCorso.setFont(new Font("Cambria Math", Font.PLAIN, 14));
	btnAddCorso.setBounds(10, 80, 142, 23);
	panel_1.add(btnAddCorso);
	
	
	btnAddTest.setVisible(false);
	btnAddTest.setBackground(new Color(255, 255, 255));
	btnAddTest.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			CreazioneTest ct= new CreazioneTest(contr, login,comboBox.getSelectedItem().toString());
			ct.setBounds(100, 200, 650, 450);
			ct.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ct.show();
			dispose();
		}
	});
	btnAddTest.setFont(new Font("Cambria Math", Font.PLAIN, 14));
	btnAddTest.setBounds(10, 169, 142, 23);
	panel_1.add(btnAddTest);
	
	JLabel lblTitolo = new JLabel("Area Insegnanti");
	lblTitolo.setFont(new Font("Cambria Math", Font.PLAIN, 20));
	lblTitolo.setBounds(5, 0, 216, 29);
	panel.add(lblTitolo);
	

	
	JLabel lblCodiceCorso = new JLabel("Codice del corso");
	lblCodiceCorso.setFont(new Font("Cambria Math", Font.PLAIN, 17));
	lblCodiceCorso.setBounds(0, 34, 133, 21);
	panel_2.add(lblCodiceCorso);
	
	JTextField textFieldCodice = new JTextField();
	textFieldCodice.setFont(new Font("Cambria Math", Font.PLAIN, 17));
	textFieldCodice.setBounds(143, 35, 118, 20);
	textFieldCodice.setColumns(10);
	panel_2.add(textFieldCodice);
	
	JLabel lblNewLabelNomeCorso = new JLabel("Nome del corso");
	lblNewLabelNomeCorso.setFont(new Font("Cambria Math", Font.PLAIN, 17));
	lblNewLabelNomeCorso.setBounds(0, 78, 122, 21);
	panel_2.add(lblNewLabelNomeCorso);
	
	JTextField textField = new JTextField();
	textField.setFont(new Font("Cambria Math", Font.PLAIN, 11));
	textField.setBounds(142, 80, 119, 20);
	textField.setColumns(10);
	panel_2.add(textField);

	
	JLabel lblNewLabelDatai = new JLabel("Data inizio");
	lblNewLabelDatai.setFont(new Font("Cambria Math", Font.PLAIN, 17));
	lblNewLabelDatai.setBounds(0, 131, 85, 21);
	panel_2.add(lblNewLabelDatai);
	
	JLabel lblNewLabelDataFine = new JLabel("Data fine");
	lblNewLabelDataFine.setFont(new Font("Cambria Math", Font.PLAIN, 17));
	lblNewLabelDataFine.setBounds(0, 265, 85, 21);
	panel_2.add(lblNewLabelDataFine);
	
	JLabel lblTitolo_1 = new JLabel("Aggiunta corso");
	lblTitolo_1.setFont(new Font("Cambria Math", Font.PLAIN, 20));
	lblTitolo_1.setBounds(0, 0, 208, 25);
	panel_2.add(lblTitolo_1);
	
	JCalendar calendar = new JCalendar();
	calendar.setBounds(83, 135, 177, 108);
	panel_2.add(calendar);
	
	JCalendar calendar2 = new JCalendar();
	calendar2.setBounds(83, 270, 178, 108);
	panel_2.add(calendar2);
	
	
	JButton btnHome = new JButton("Home");
	btnHome.setBackground(new Color(255, 255, 255));
	btnHome.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Home ho=new Home(contr);
			ho.setBounds(100, 200, 450, 300);
			ho.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ho.show();
			dispose();
		}
	});
	btnHome.setFont(new Font("Cambria Math", Font.PLAIN, 17));
	btnHome.setBounds(50, 424, 89, 23);
	panel.add(btnHome);
	
	
	
	comboBox.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
	    	
	        Object selectedValue = comboBox.getSelectedItem();
	        
	        if(comboBox.getSelectedItem()!=null) {
	        	btnCambiaCorso.setVisible(true);
	        	btnAddTest.setVisible(true);
	        	te.clear();
	        	te=contr.riempiCBoxTest(selectedValue.toString(), te);
	        	
	       
	        
	    	comboBox_1= new JComboBox(te.toArray());
	    	comboBox_1.setBounds(10, 135, 142, 22);
	    	comboBox_1.setFont(new Font("Cambria Math", Font.PLAIN, 13));
	    	comboBox_1.setVisible(true);
	    	lblTest.setVisible(true);
	    	panel_1.add(comboBox_1);
	    	
	    	comboBox_1.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent evt) {
	    	        Object selectedValue = comboBox_1.getSelectedItem();
	    			ValutazioneTest vt=new ValutazioneTest(contr,selectedValue.toString(), login);
	    			vt.setBounds(100, 200, 650, 520);
	    			vt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    			vt.show();
	    			dispose();
	    	        
	    	    }

	    	});
	         	
	        	
	        	
	    }else {
	    	System.out.println("selected null");
	    }
	    }

	});
	
	
	JButton btnConferma = new JButton("Conferma");
	btnConferma.setBackground(new Color(255, 255, 255));
	btnConferma.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			java.sql.Date sqlDate1 = new java.sql.Date(calendar.getDate().getTime());
			java.sql.Date sqlDate2 = new java.sql.Date(calendar2.getDate().getTime());
			contr.registraCorso(textFieldCodice.getText(), textField.getText(), contr.getMatricolaI(login), sqlDate1, sqlDate2);
			comboBox.addItem(textField.getText());
		}
	});
	btnConferma.setFont(new Font("Cambria Math", Font.PLAIN, 17));
	btnConferma.setBounds(0, 413, 111, 23);
	panel_2.add(btnConferma);
	
	
	
	
	JButton btnAnnulla = new JButton("Indietro");
	btnAnnulla.setBackground(new Color(255, 255, 255));
	btnAnnulla.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			comboBox.setVisible(true);
			btnAddCorso.setVisible(true);
			panel_2.setVisible(false);
			lblCorso.setVisible(true);
		}
	});
	btnAnnulla.setFont(new Font("Cambria Math", Font.PLAIN, 17));
	btnAnnulla.setBounds(142, 413, 119, 23);
	panel_2.add(btnAnnulla);
	
	
	
	}
	
	

	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}




