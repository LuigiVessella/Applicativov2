package project.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.awt.TextField;
import java.awt.Font;
import javax.swing.JLabel;
//import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.toedter.calendar.JCalendar;

import java.awt.Container;

import project.controller.Controller;
import project.dao.CorsoDAO;
import project.dao.TestDAO;

import javax.swing.JCheckBox;
import java.awt.Checkbox;
import java.awt.Choice;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.Panel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;

	
	public class SignInS extends JFrame implements ActionListener {
		private JPasswordField passwordField;
		private JPanel panel2 = new JPanel();
		private JPanel panel3= new JPanel();
		List<String> cose = new ArrayList<String>();
		List<String> te = new ArrayList<String>();
		List<String> tes = new ArrayList<String>();
		private JComboBox comboBox;	
		private JComboBox comboBox_1;
		private JTable table;

		
		public SignInS(Controller contr, String login) {
			getContentPane().setBackground(new Color(255, 153, 153));
		getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 153));
		panel.setBounds(0, 0, 870, 473);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 153, 153));
		panel_2.setBounds(266, 21, 594, 441);
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setVisible(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 567, 352);
		panel_2.add(scrollPane);
		
		DefaultTableModel model;
		
		table = new JTable();
		scrollPane.setViewportView(table);
        Object[] column = { "Corso", "Test", "Matricola", "Voto"};
		model=new DefaultTableModel ();
		model.setColumnIdentifiers(column);
		table.setModel(model);
		contr.TestCorretti(login, model);
		
		
		List<String> cose = new ArrayList<String>();
		
		cose=contr.riempiCboxS(login, cose);
		
		
		te = new ArrayList<String>();
			

			
		JComboBox comboBox;
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 153, 153));
		panel_1.setBounds(10, 56, 240, 255);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		comboBox= new JComboBox(cose.toArray());
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		comboBox.setBounds(5, 51, 132, 22);
		panel_1.add(comboBox);
		
		
		JLabel lblTest = new JLabel("Test");
		lblTest.setFont(new Font("Cambria Math", Font.PLAIN, 15));
		lblTest.setBounds(5, 104, 49, 14);
		lblTest.setVisible(false);
		panel_1.add(lblTest);
		
		
		
		
		JButton btnCambiaCorso = new JButton("Cambia Corso");
		btnCambiaCorso.setVisible(false);
		btnCambiaCorso.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		btnCambiaCorso.setBounds(5, 189, 142, 23);
		panel_1.add(btnCambiaCorso);
		btnCambiaCorso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_1.setVisible(false);
				btnCambiaCorso.setVisible(false);
				lblTest.setVisible(false);
			}
		});
		
		
		JLabel lblCorso = new JLabel("Corso");
		lblCorso.setFont(new Font("Cambria Math", Font.PLAIN, 15));
		lblCorso.setBounds(5, 26, 90, 16);
		panel_1.add(lblCorso);
		

		
		JLabel lblTitolo = new JLabel("Area Studenti");
		lblTitolo.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		lblTitolo.setBounds(5, 0, 216, 29);
		panel.add(lblTitolo);
		
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home ho=new Home(contr);
				ho.setBounds(100, 200, 500, 300);
				ho.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ho.show();
				dispose();
			}
		});
		btnHome.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		btnHome.setBounds(44, 410, 89, 23);
		panel.add(btnHome);
		

		
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
			}
		});
		btnBack.setBounds(488, 392, 89, 23);
		panel_2.add(btnBack);
		
		
		
		JButton btnTestCorretti = new JButton("Test Corretti");
		btnTestCorretti.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		btnTestCorretti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(true);
			}
		});
		btnTestCorretti.setBounds(5, 223, 141, 23);
		panel_1.add(btnTestCorretti);
		
		
		comboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	
		        Object selectedValue = comboBox.getSelectedItem();
		        
		        
		        if(comboBox.getSelectedItem()!=null) {
		        	btnCambiaCorso.setVisible(true);
		        	te.clear();
		        	te=contr.riempiCBoxTest(selectedValue.toString(), te);
		        	tes.clear();
		        	tes=contr.togliCBoxText(login, tes);
		        	te.removeAll(tes);
		        	
		       
		        
		    	comboBox_1= new JComboBox(te.toArray());
		    	comboBox_1.setBounds(5, 128, 132, 22);
				comboBox_1.setBackground(new Color(255, 255, 255));
				comboBox_1.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		    	comboBox_1.setVisible(true);
		    	lblTest.setVisible(true);
		    	panel_1.add(comboBox_1);
		    	
		    	comboBox_1.addActionListener(new ActionListener() {
		    	    public void actionPerformed(ActionEvent evt) {
		    	        Object selectedValue = comboBox_1.getSelectedItem();
		    	        
		    			SvolgimentoTest st= new SvolgimentoTest(contr,selectedValue.toString(), login);
		    			st.setBounds(100, 200, 560, 430);
		    			st.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    			st.show();
		    			dispose();
		    	    }

		    	});
		         	
		        	
		        	
		    }else {
		    	System.out.println("selected null");
		    }
		    }

		});
		
		
		
		}
		
		

		
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
