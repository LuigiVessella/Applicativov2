package project.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.util.Properties;
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
import project.implementazioneDao.ImpTestDao;

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
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.util.Date;
import java.util.Calendar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JPopupMenu;

public class ValutazioneTest extends JFrame {
	private JTable table;
	private List<String> te;
	
	private ImpTestDao it=new ImpTestDao();
	private String voto= new String();
	private JTextField textFieldVoto;
	private int i;

	public ValutazioneTest (Controller c, String codTest, String login) {
		getContentPane().setBackground(new Color(153, 204, 255));
		getContentPane().setLayout(null);

		
		DefaultTableModel model;
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(10, 38, 628, 444);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 608, 352);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
        Object[] column = { "Test", "Quiz", "Matricola", "RispostaData", "Voto"};
		Object[] row = new Object [0];
		model=new DefaultTableModel ();
		model.setColumnIdentifiers(column);
		table.setModel(model);
		c.riempiTableA(codTest, model);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBackground(new Color(255, 255, 255));
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignInI si= new SignInI(c, login);
				si.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				si.setBounds(100, 100, 600, 500);
				si.show();
				dispose();
			}
		});
		btnIndietro.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		btnIndietro.setBounds(349, 374, 96, 23);
		panel.add(btnIndietro);
		
	
		
		textFieldVoto = new JTextField();
		textFieldVoto.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		textFieldVoto.setBounds(349, 408, 96, 20);
		textFieldVoto.setVisible(false);
		panel.add(textFieldVoto);
		textFieldVoto.setVisible(false);
		textFieldVoto.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Inserisci voto");
		lblNewLabel.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblNewLabel.setBounds(249, 410, 96, 14);
		lblNewLabel.setVisible(false);
		panel.add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		btnOk.setBackground(new Color(255, 255, 255));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i=table.getSelectedRow();
				String test= model.getValueAt(i, 0).toString();
			
				String quiz= model.getValueAt(i, 1).toString();
			
				String matricola= model.getValueAt(i, 2).toString();
				String risp= model.getValueAt(i, 3).toString();
			
				String voto= textFieldVoto.getText();
				float d= Float.parseFloat(voto);
				c.updateVoti(test, matricola, quiz, d);
				model.removeRow(i);
				Object[] row= new Object [5];
				row[0]=test;
	        	row[1]=quiz;
	        	row[2]=matricola;
	        	row[3]=risp;
	        	row[4]=d;
	        	model.addRow(row);
				//c.riempiTableA(codTest, model);

			}
		});
		btnOk.setBounds(455, 407, 70, 23);
		btnOk.setVisible(false);
		panel.add(btnOk);
		btnOk.setVisible(false);
		
		JButton btnDietro = new JButton("Indietro");
		btnDietro.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		btnDietro.setBackground(new Color(255, 255, 255));
		btnDietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldVoto.setVisible(false);
				lblNewLabel.setVisible(false);
				btnOk.setVisible(false);
				btnDietro.setVisible(false);
			}
		});
		btnDietro.setBounds(535, 407, 83, 23);
		btnDietro.setVisible(false);
		panel.add(btnDietro);
		btnDietro.setVisible(false);
		
		JLabel lblTitolo = new JLabel("Area Valutazione");
		lblTitolo.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		lblTitolo.setBounds(10, 3, 148, 24);
		getContentPane().add(lblTitolo);
		

		JButton btnEdit = new JButton("Modifica voto");
		btnEdit.setBackground(new Color(255, 255, 255));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i= table.getSelectedRow();
				textFieldVoto.setVisible(true);
				lblNewLabel.setVisible(true);
				btnOk.setVisible(true);
				btnDietro.setVisible(true);
				
			}
		});
		btnEdit.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		btnEdit.setBounds(455, 374, 163, 23);
		panel.add(btnEdit);
		
		
		
	}

}
	

