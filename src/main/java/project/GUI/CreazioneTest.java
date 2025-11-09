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
import java.security.Timestamp;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTextPane;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;



public class CreazioneTest extends JFrame{
	private JTextField txtFliedIdTest;
	private JTextField textFieldNQuiz;
	private JTextField textFieldOInizio;
	private JTextField textFieldOFine;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	
	public CreazioneTest(Controller contr, String login, String corso) {
		getContentPane().setBackground(new Color(153, 204, 255));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(10, 0, 626, 689);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTitolo = new JLabel("Area Creazione Test");
		lblTitolo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTitolo.setBounds(0, 0, 266, 43);
		panel.add(lblTitolo);
		
		JLabel lblNewLabel = new JLabel("Id del test");
		lblNewLabel.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 72, 98, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNumeroDiQuiz = new JLabel("Numero di quiz");
		lblNumeroDiQuiz.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblNumeroDiQuiz.setBounds(10, 117, 98, 14);
		panel.add(lblNumeroDiQuiz);
		
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblData.setBounds(10, 150, 51, 14);
		panel.add(lblData);
		
		JLabel lblOrarioDiInizio = new JLabel("Orario di inizio");
		lblOrarioDiInizio.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblOrarioDiInizio.setBounds(11, 294, 108, 14);
		panel.add(lblOrarioDiInizio);
		
		JLabel lblOrarioDiFine = new JLabel("Orario di fine");
		lblOrarioDiFine.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblOrarioDiFine.setBounds(11, 328, 98, 14);
		panel.add(lblOrarioDiFine);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(59, 150, 194, 126);
		panel.add(calendar);
		
		txtFliedIdTest = new JTextField();
		txtFliedIdTest.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		txtFliedIdTest.setBounds(118, 69, 132, 20);
		panel.add(txtFliedIdTest);
		txtFliedIdTest.setColumns(10);
		
		textFieldNQuiz = new JTextField();
		textFieldNQuiz.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		textFieldNQuiz.setBounds(118, 115, 135, 20);
		panel.add(textFieldNQuiz);
		textFieldNQuiz.setColumns(10);
		
		textFieldOInizio = new JTextField();
		textFieldOInizio.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		textFieldOInizio.setBounds(118, 291, 135, 20);
		panel.add(textFieldOInizio);
		textFieldOInizio.setColumns(10);
		
		textFieldOFine = new JTextField();
		textFieldOFine.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		textFieldOFine.setBounds(119, 325, 134, 20);
		panel.add(textFieldOFine);
		textFieldOFine.setColumns(10);
		
		JButton btnIndietro = new JButton("Torna ad area insegnanti");
		btnIndietro.setBackground(new Color(255, 255, 255));
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignInI si= new SignInI(contr, login);
				si.setBounds(100, 100, 650, 550);
				si.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				si.show();
				dispose();
			}
		});
		btnIndietro.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		btnIndietro.setBounds(0, 382, 184, 23);
		panel.add(btnIndietro);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.setFont(new Font("Cambria Math", Font.PLAIN, 12));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setVisible(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 204, 255));
		panel_1.setBounds(331, 72, 280, 274);
		panel_1.setVisible(false);
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		
		JButton btnQuizRispApe = new JButton("Quiz a risposta aperta");
		btnQuizRispApe.setFont(new Font("Cambria Math", Font.PLAIN, 13));

		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(331, 72, 280, 300);
		panel.add(panel_2);
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setVisible(false);
		panel_2.setVisible(false);
		panel_2.setLayout(null);
		
		
		JButton btnSave = new JButton("Salva");
		JButton btnQuizRispMulti = new JButton("Quiz a risposta multipla");
		btnQuizRispMulti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setVisible(false);
				btnIndietro.setVisible(false);
				btnQuizRispMulti.setVisible(false);
				btnQuizRispApe.setVisible(false);
				panel_2.setVisible(true);
			}
		});
		btnQuizRispMulti.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		btnQuizRispMulti.setBounds(373, 41, 194, 23);
		btnQuizRispMulti.setVisible(false);
		panel.add(btnQuizRispMulti);
		btnQuizRispMulti.setVisible(false);
		

		
		btnQuizRispApe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setVisible(false);
				btnIndietro.setVisible(false);
				btnQuizRispApe.setVisible(false);
				btnQuizRispMulti.setVisible(false);
				panel_1.setVisible(true);
			}
		});
		btnQuizRispApe.setBounds(373, 12, 194, 23);
		btnQuizRispApe.setVisible(false);
		panel.add(btnQuizRispApe);
		btnQuizRispApe.setVisible(false);
		
		

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnQuizRispApe.setVisible(true);
				btnQuizRispMulti.setVisible(true);
			}
		});
		btnNewButton.setBounds(263, 113, 53, 23);
		panel.add(btnNewButton);
		btnNewButton.setVisible(false);
		
		
		JLabel lblId_quiz = new JLabel("Id quiz");
		lblId_quiz.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblId_quiz.setBounds(10, 31, 63, 14);
		panel_1.add(lblId_quiz);
		
		JLabel lblDomanda = new JLabel("Domanda");
		lblDomanda.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblDomanda.setBounds(10, 63, 90, 14);
		panel_1.add(lblDomanda);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(255, 255, 255));
		textPane.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		textPane.setBounds(133, 62, 147, 51);
		panel_1.add(textPane);
		
		JLabel lblNewLabel_1 = new JLabel("Lunghezza risposta");
		lblNewLabel_1.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 134, 121, 14);
		panel_1.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		textField.setBounds(133, 131, 75, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		textField_1.setBounds(133, 28, 75, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Punteggio max");
		lblNewLabel_2.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 173, 115, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Punteggio min");
		lblNewLabel_3.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(10, 202, 115, 14);
		panel_1.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		textField_2.setBounds(133, 170, 75, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		textField_3.setBounds(133, 199, 75, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Creazione Quiz a risposta aperta");
		lblNewLabel_4.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(10, 0, 270, 20);
		panel_1.add(lblNewLabel_4);
		
	
		
		JLabel lblNewLabel_5 = new JLabel("Vuoi creare un altro quiz?");
		lblNewLabel_5.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(0, 260, 151, 14);
		lblNewLabel_5.setVisible(false);
		panel_1.add(lblNewLabel_5);
		lblNewLabel_5.setVisible(false);
		
		JButton btnNewButton_3_1 = new JButton("No");
		btnNewButton_3_1.setBackground(new Color(255, 255, 255));
		btnNewButton_3_1.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		
		JButton btnNewButton_3 = new JButton("Si");
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnQuizRispApe.setVisible(true);
				btnQuizRispMulti.setVisible(true);
				btnIndietro.setVisible(true);
				lblNewLabel_5.setVisible(false);
				btnNewButton_3.setVisible(false);
				btnNewButton_3_1.setVisible(false);
				panel_1.setVisible(false);
			}
		});
		btnNewButton_3.setBounds(151, 260, 63, 14);
		btnNewButton_3.setVisible(false);
		panel_1.add(btnNewButton_3);
		btnNewButton_3.setVisible(false);
		

		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnIndietro.setVisible(true);
				lblNewLabel_5.setVisible(false);
				btnNewButton_3.setVisible(false);
				btnNewButton_3_1.setVisible(false);
				panel_1.setVisible(false);
			}
		});
		btnNewButton_3_1.setBounds(221, 261, 59, 14);
		btnNewButton_3_1.setVisible(false);
		panel_1.add(btnNewButton_3_1);
		btnNewButton_3_1.setVisible(false);

		JButton btnNewButton_1 = new JButton("Indietro");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnIndietro.setVisible(true);
				lblNewLabel_5.setVisible(false);
				btnNewButton_3.setVisible(false);
				btnNewButton_3_1.setVisible(false);
				panel_1.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(10, 227, 99, 23);
		panel_1.add(btnNewButton_1);
	
		JButton btnNewButton_2 = new JButton("Conferma");
		btnNewButton_2.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_5.setVisible(true);
				btnNewButton_3.setVisible(true);
				btnNewButton_3_1.setVisible(true);
				int i= Integer.parseInt(textField.getText());
				float f= Float.parseFloat(textField_2.getText());
				float f_1=Float.parseFloat(textField_3.getText());
				contr.registraQuiz_Risp_Ape(textField_1.getText(),textPane.getText(),i,f,f_1,txtFliedIdTest.getText());
			}
		});
		btnNewButton_2.setBounds(181, 226, 99, 23);
		panel_1.add(btnNewButton_2);
		


		
		
		JLabel lblNewLabel_6 = new JLabel("Creazione Quiz a risposta multipla");
		lblNewLabel_6.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		lblNewLabel_6.setBounds(10, 0, 260, 21);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblId_quiz_1 = new JLabel("Id quiz");
		lblId_quiz_1.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblId_quiz_1.setBounds(10, 32, 63, 14);
		panel_2.add(lblId_quiz_1);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		textField_4.setColumns(10);
		textField_4.setBounds(108, 29, 51, 17);
		panel_2.add(textField_4);
		
		JLabel lblDomanda_1 = new JLabel("Domanda");
		lblDomanda_1.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblDomanda_1.setBounds(10, 57, 90, 14);
		panel_2.add(lblDomanda_1);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		textPane_1.setBounds(108, 57, 172, 33);
		panel_2.add(textPane_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Risposta A");
		lblNewLabel_1_1.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 95, 78, 14);
		panel_2.add(lblNewLabel_1_1);
		
		JTextPane textPane_1_1 = new JTextPane();
		textPane_1_1.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		textPane_1_1.setBounds(108, 95, 172, 21);
		panel_2.add(textPane_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Risposta B");
		lblNewLabel_1_1_1.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(10, 120, 78, 14);
		panel_2.add(lblNewLabel_1_1_1);
		
		JTextPane textPane_1_1_1 = new JTextPane();
		textPane_1_1_1.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		textPane_1_1_1.setBounds(108, 120, 172, 21);
		panel_2.add(textPane_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Risposta C");
		lblNewLabel_1_1_1_1.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1.setBounds(10, 145, 78, 14);
		panel_2.add(lblNewLabel_1_1_1_1);
		
		JTextPane textPane_1_1_1_1 = new JTextPane();
		textPane_1_1_1_1.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		textPane_1_1_1_1.setBounds(108, 145, 172, 21);
		panel_2.add(textPane_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Risposta D");
		lblNewLabel_1_1_1_2.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblNewLabel_1_1_1_2.setBounds(10, 170, 78, 14);
		panel_2.add(lblNewLabel_1_1_1_2);
		
		JTextPane textPane_1_1_1_2 = new JTextPane();
		textPane_1_1_1_2.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		textPane_1_1_1_2.setBounds(108, 170, 172, 21);
		panel_2.add(textPane_1_1_1_2);
		
		JLabel lblId_quiz_1_1 = new JLabel("Corretta");
		lblId_quiz_1_1.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblId_quiz_1_1.setBounds(10, 198, 63, 14);
		panel_2.add(lblId_quiz_1_1);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		textField_5.setColumns(10);
		textField_5.setBounds(108, 195, 51, 17);
		panel_2.add(textField_5);
		
		JLabel lblId_quiz_1_1_1 = new JLabel("Punti corretta");
		lblId_quiz_1_1_1.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblId_quiz_1_1_1.setBounds(10, 218, 95, 14);
		panel_2.add(lblId_quiz_1_1_1);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		textField_6.setColumns(10);
		textField_6.setBounds(108, 216, 51, 17);
		panel_2.add(textField_6);
		
		JLabel lblId_quiz_1_1_2 = new JLabel("Punti errata");
		lblId_quiz_1_1_2.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblId_quiz_1_1_2.setBounds(10, 238, 90, 14);
		panel_2.add(lblId_quiz_1_1_2);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		textField_7.setColumns(10);
		textField_7.setBounds(108, 236, 51, 17);
		panel_2.add(textField_7);
		
		JLabel lblNewLabel_7 = new JLabel("Vuoi creare un altro quiz?");
		lblNewLabel_7.setFont(new Font("Cambria Math", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(0, 282, 134, 14);
		lblNewLabel_7.setVisible(false);
		panel_2.add(lblNewLabel_7);
		lblNewLabel_7.setVisible(false);
		
		JButton btnYes = new JButton("Si");
		btnYes.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		btnYes.setBackground(new Color(255, 255, 255));
		
		JButton btnNope = new JButton("No");
		btnNope.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		btnNope.setBackground(new Color(255, 255, 255));
		btnNope.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnIndietro.setVisible(true);
				lblNewLabel_7.setVisible(false);
				btnYes.setVisible(false);
				btnNope.setVisible(false);
				panel_2.setVisible(false);
				
			}
		});
		
		
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnQuizRispApe.setVisible(true);
				btnQuizRispMulti.setVisible(true);
				btnIndietro.setVisible(true);
				lblNewLabel_7.setVisible(false);
				btnYes.setVisible(false);
				btnNope.setVisible(false);
				panel_2.setVisible(false);
				
			}
		});
		btnYes.setBounds(144, 283, 64, 14);
		btnYes.setVisible(false);
		panel_2.add(btnYes);
		btnYes.setVisible(false);
		
		
		btnNope.setBounds(216, 283, 64, 14);
		btnNope.setVisible(false);
		panel_2.add(btnNope);
		btnNope.setVisible(false);
		
		JButton btnConf = new JButton("Conferma");
		btnConf.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		btnConf.setBackground(new Color(255, 255, 255));
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_7.setVisible(true);
				btnYes.setVisible(true);
				btnNope.setVisible(true);
				int a=Integer.parseInt(textField_6.getText());
				int b=Integer.parseInt(textField_7.getText());
				contr.registraQuiz_Risp_Mul(textField_4.getText(), textPane_1.getText(), textPane_1_1.getText(), textPane_1_1_1.getText(), textPane_1_1_1_1.getText(), textPane_1_1_1_2.getText(), textField_5.getText(), a, b, txtFliedIdTest.getText());
			}
		});
		btnConf.setBounds(186, 252, 94, 20);
		panel_2.add(btnConf);
		
		JButton btnIndi = new JButton("Indietro");
		btnIndi.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		btnIndi.setBackground(new Color(255, 255, 255));
		btnIndi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnIndietro.setVisible(true);
				lblNewLabel_7.setVisible(false);
				btnYes.setVisible(false);
				btnNope.setVisible(false);
				panel_2.setVisible(false);
			}
		});
		btnIndi.setBounds(10, 252, 88, 20);
		panel_2.add(btnIndi);
		
		btnSave.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		btnSave.setBackground(new Color(255, 255, 255));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Time o_ini=java.sql.Time.valueOf(textFieldOInizio.getText());
				Time o_fin=java.sql.Time.valueOf(textFieldOFine.getText());
				int n= Integer.parseInt(textFieldNQuiz.getText());
			
				java.sql.Date sqlDate = new java.sql.Date(calendar.getDate().getTime());
				contr.registraTest(txtFliedIdTest.getText(),n, sqlDate ,o_ini, o_fin, contr.getCod_corso(corso), contr.getMatricolaI(login));
				btnNewButton.setVisible(true);
				
			}
		});
		btnSave.setBounds(203, 382, 89, 23);
		panel.add(btnSave);
		

		

		

		
	}	
}
