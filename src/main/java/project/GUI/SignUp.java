package project.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.TextField;
import java.awt.Font;
import javax.swing.JLabel;
//import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JCheckBox;
import java.awt.Checkbox;
import java.awt.Choice;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import project.controller.Controller;

public class SignUp extends JFrame {
	private JPasswordField passwordField;
	private JTextField textFieldMatricola;
	public SignUp(Controller contr) {
		getContentPane().setBackground(new Color(204, 102, 102));
		Controller c=contr;
		getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 11));
		getContentPane().setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		passwordField.setBounds(160, 114, 201, 20);
		getContentPane().add(passwordField);
		
		TextField textField_cognome = new TextField();
		textField_cognome.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		textField_cognome.setBounds(160, 60, 201, 21);
		getContentPane().add(textField_cognome);
		
		TextField textField_nome = new TextField();
		textField_nome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textField_nome.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		textField_nome.setBounds(160, 33, 201, 21);
		getContentPane().add(textField_nome);
		
		TextField textField_login = new TextField();
		textField_login.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		textField_login.setBounds(160, 87, 201, 21);
		getContentPane().add(textField_login);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		lblNome.setBounds(26, 33, 97, 21);
		getContentPane().add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		lblCognome.setBounds(26, 60, 97, 21);
		getContentPane().add(lblCognome);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		lblLogin.setBounds(26, 87, 97, 21);
		getContentPane().add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		lblPassword.setBounds(26, 112, 97, 20);
		getContentPane().add(lblPassword);
		
		
		JComboBox comboBoxStudIns = new JComboBox();
		comboBoxStudIns.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		comboBoxStudIns.setModel(new DefaultComboBoxModel(new String[] {"Studente", "Insegnante"}));
		comboBoxStudIns.setBounds(160, 170, 201, 22);
		getContentPane().add(comboBoxStudIns);
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.setBackground(new Color(255, 255, 255));
		btnConferma.setToolTipText("Attenzione, verrai ricondotto alla home per poter fare il login con le tue credenziali");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studins= new String (comboBoxStudIns.getSelectedItem().toString());
				if(studins.equals("Studente")) {
				
				c.registraStud(textField_nome.getText(), textField_cognome.getText(), textField_login.getText(), passwordField.getText(), textFieldMatricola.getText());
				Home ho=new Home(c);
				ho.setBounds(100, 100, 450, 300);
				ho.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ho.show();
				dispose();
				} else if (studins.equals("Insegnante")) {
					c.registraInsegnante (textField_nome.getText(), textField_cognome.getText(), textField_login.getText(), passwordField.getText(), textFieldMatricola.getText());
					Home ho=new Home(c);
					ho.setBounds(100, 100, 450, 300);
					ho.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					ho.show();
					dispose();
				}
			}
		});
		btnConferma.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		btnConferma.setBounds(173, 217, 124, 23);
		getContentPane().add(btnConferma);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBackground(new Color(255, 255, 255));
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home ho=new Home(c);
				ho.setBounds(100, 100, 450, 300);
				ho.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ho.show();
				dispose();
			}
		});
		btnAnnulla.setToolTipText("Ritorni alla home");
		btnAnnulla.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		btnAnnulla.setBounds(318, 217, 108, 23);
		getContentPane().add(btnAnnulla);
		
		
		JLabel lblSeleziona = new JLabel("Sono un/uno");
		lblSeleziona.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		lblSeleziona.setBounds(26, 172, 124, 18);
		getContentPane().add(lblSeleziona);
		
		JLabel lblMatricola = new JLabel("Matricola");
		lblMatricola.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		lblMatricola.setBounds(26, 143, 97, 18);
		getContentPane().add(lblMatricola);
		
		textFieldMatricola = new JTextField();
		textFieldMatricola.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		textFieldMatricola.setBounds(160, 139, 201, 20);
		getContentPane().add(textFieldMatricola);
		textFieldMatricola.setColumns(10);
	}
}
