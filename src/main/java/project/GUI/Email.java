package project.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.Controller.UltraPhoneBookController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Email extends JDialog {

	private UltraPhoneBookController upbc;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmail;
	private JFrame caller;
	
	private void deleteEmail(String email) {
			String result = this.upbc.deleteEmail(email);
			if(result != "OK") {
				JFrame frMsg = new JFrame();
				frMsg.setAlwaysOnTop(true);
				frMsg.setLocationRelativeTo(contentPanel);
				JOptionPane.showMessageDialog(frMsg, result, "Elimina email",
				        JOptionPane.ERROR_MESSAGE);
			}else {
				dispose();
				this.caller.setVisible(true);
			}
	}

	 private void addEmail(String email) {
			String result = this.upbc.addEmail(email);
			if(result != "OK") {
				JFrame frMsg = new JFrame();
				frMsg.setAlwaysOnTop(true);
				frMsg.setLocationRelativeTo(contentPanel);
				JOptionPane.showMessageDialog(frMsg, result, "Aggiungi email",
				        JOptionPane.ERROR_MESSAGE);
			}else {
				dispose();
				this.caller.setVisible(true);
			}
	}
	 
	 private void updateEmail(String email, int selectedIndexEmail) {
			String result = this.upbc.updateEmail(email, selectedIndexEmail);
			if(result != "OK") {
				JFrame frMsg = new JFrame();
				frMsg.setAlwaysOnTop(true);
				frMsg.setLocationRelativeTo(contentPanel);
				JOptionPane.showMessageDialog(frMsg, result, "Modifica email",
				        JOptionPane.ERROR_MESSAGE);
			}else {
				dispose();
				this.caller.setVisible(true);
			}
	}
	 
	
	/**
	 * Create the dialog.
	 */
	public Email(UltraPhoneBookController upbc, DialogType dt, JFrame caller, int ... selectedIndexEmail) {
		this.upbc = upbc;
		this.caller = caller;
		
		
		setBounds(100, 100, 399, 120);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblEmail = new JLabel("Email");
			contentPanel.add(lblEmail);
		}
		{
			txtEmail = new JTextField();
			contentPanel.add(txtEmail);
			txtEmail.setColumns(25);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(dt == DialogType.modificaEmail) {
							updateEmail(txtEmail.getText(), selectedIndexEmail[0]);
						}else {
							addEmail(txtEmail.getText());
						}
						
					} });
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("ANNULLA");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						 // Chiudo la finestra di dialogo senza salvare i dati
						 
						 dispose();
						 caller.setVisible(true);
						 
					} });
				buttonPane.add(cancelButton);
			}
		}
		
		switch(dt) {
		case nuovaEmail:
			setTitle("Nuova Email");
			break;
		case modificaEmail:
			setTitle("Modifica Email");
			String selectedEmail = upbc.getContactEmail(selectedIndexEmail[0]);
			this.txtEmail.setText(selectedEmail);
			break;
		default:
			setTitle("Nuova Email");
		}
	}

}
