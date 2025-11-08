package project.GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;

import project.Controller.UltraPhoneBookController;


public class Main {

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UltraPhoneBookController upbc = new UltraPhoneBookController();
					MainUltraPhoneBook window = new MainUltraPhoneBook(upbc);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
