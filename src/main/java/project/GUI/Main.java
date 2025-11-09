package project.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.controller.*;

import java.awt.FlowLayout;
import java.awt.Toolkit;

;

public class Main {
private JFrame frame;
private JTextField textField;
Controller contr=new Controller();

String loginn;

public static void main(String[] args) {
	
	
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Main window = new Main();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}


public Main() {
	initialize();
}


private void initialize() {
	frame = new Home(contr);
	frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 17));
	frame.setBounds(100, 100, 450, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

}


