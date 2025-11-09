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
import java.awt.TextField;
import java.awt.Font;
import javax.swing.JLabel;
//import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.toedter.calendar.JCalendar;

import java.awt.Container;
import java.awt.Dimension;

import project.controller.Controller;
import project.dao.CorsoDAO;
import project.dao.TestDAO;

import javax.swing.JCheckBox;
import java.awt.Checkbox;
import java.awt.Choice;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;


import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
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
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JRadioButton;
import java.awt.GridLayout;

	
public class SvolgimentoTest extends JFrame {
	public SvolgimentoTest(Controller contr, String test, String login) {
		getContentPane().setBackground(new Color(255, 153, 153));
		getContentPane().setLayout(null);
		CardLayout cl=new CardLayout();
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 153));
		panel.setBounds(0, 0, 591, 437);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTitolo = new JLabel("Svolgimento Test");
		lblTitolo.setBounds(5, 0, 156, 36);
		lblTitolo.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		panel.add(lblTitolo);
		
		JPanel panelinizio = new JPanel();
		panelinizio.setBackground(new Color(255, 153, 153));
		panelinizio.setBounds(130, 90, 300, 200);
		panel.add(panelinizio);
		panelinizio.setLayout(cl);
		
		JButton btnSend = new JButton("Invia e chiudi");   		
		btnSend.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		btnSend.setBackground(new Color(255, 255, 255));
		btnSend.setBounds(411, 357, 124, 23);
		btnSend.setVisible(false);
		panel.add(btnSend);
		btnSend.setVisible(false);
		
		int [] arr= new int [2];
		String matr=contr.getMatricolaS(login);
		arr=contr.SvolgiTestApe(test, panelinizio, cl, contr, matr);
		contr.SvolgiTestMulti(test, panelinizio, cl, arr[0], arr[1], panel, contr, matr, login, btnSend);
		
		
   		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignInS sis= new SignInS(contr, login);
				sis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sis.setBounds(100, 100, 900, 500);
				sis.show();
				dispose();
				}
		});
		

	}
}
