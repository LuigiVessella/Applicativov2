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
import project.CustomException.DuplicatedAccountException;
import project.CustomException.EmailNotFoundException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Account extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private UltraPhoneBookController upbc;
	private JTextField txtApp;
	private JTextField txtNick;
	private JTextField txtEmail;
	private JTextField txtWelcome;
	private JFrame caller;
	
	private void deleteAccount(project.Model.Account accountToDelete) {
			String result = this.upbc.deleteAccount(accountToDelete);
			if(result != "OK") {
				JFrame frMsg = new JFrame();
				frMsg.setAlwaysOnTop(true);
				frMsg.setLocationRelativeTo(contentPanel);
				JOptionPane.showMessageDialog(frMsg, result, "Elimina Account",
				        JOptionPane.ERROR_MESSAGE);
			}else{
				dispose();
				this.caller.setVisible(true);
			}
	}
	
	 private void addAccount(String appName, String nickName, String email, String welcome) {
			String result = this.upbc.addAccount(appName, nickName, email, welcome);
			if(result != "OK") {
				JFrame frMsg = new JFrame();
				frMsg.setAlwaysOnTop(true);
				frMsg.setLocationRelativeTo(contentPanel);
				JOptionPane.showMessageDialog(frMsg, result, "Aggiungi Account",
				        JOptionPane.ERROR_MESSAGE);
			}else{
				dispose();
				this.caller.setVisible(true);
			}
	}
	 
	 private void updateAccount(String appName, String nickName, String email, String welcome, int selectedIndexAccount) {
			String result = this.upbc.updateAccount(appName, nickName, email, welcome, selectedIndexAccount);
			if(result != "OK") {
				JFrame frMsg = new JFrame();
				frMsg.setAlwaysOnTop(true);
				frMsg.setLocationRelativeTo(contentPanel);
				JOptionPane.showMessageDialog(frMsg, result, "Modifica Account",
				        JOptionPane.ERROR_MESSAGE);
			}else{
				dispose();
				this.caller.setVisible(true);
			}
	}
	
	/**
	 * Create the dialog.
	 */
	public Account(UltraPhoneBookController upbc, DialogType dt, JFrame caller, int ... selectedIndexAccount) {
		this.upbc = upbc;
		this.caller = caller;
		
		setBounds(100, 100, 450, 192);
		getContentPane().setLayout(new BorderLayout());
		FlowLayout fl_contentPanel = new FlowLayout();
		fl_contentPanel.setAlignment(FlowLayout.RIGHT);
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Applicazione");
			contentPanel.add(lblNewLabel);
		}
		{
			txtApp = new JTextField();
			contentPanel.add(txtApp);
			txtApp.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nickname");
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtNick = new JTextField();
			contentPanel.add(txtNick);
			txtNick.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Frase di benvenuto");
			contentPanel.add(lblNewLabel_2);
		}
		{
			txtWelcome = new JTextField();
			contentPanel.add(txtWelcome);
			txtWelcome.setColumns(24);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Email");
			contentPanel.add(lblNewLabel_3);
		}
		{
			txtEmail = new JTextField();
			contentPanel.add(txtEmail);
			txtEmail.setColumns(24);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(dt == DialogType.modificaAccount) {
							updateAccount(txtApp.getText(),txtNick.getText(),txtEmail.getText(),txtWelcome.getText(), selectedIndexAccount[0]);
						}else {
							addAccount(txtApp.getText(),txtNick.getText(),txtEmail.getText(),txtWelcome.getText());
						}
						 
					} });
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
		case nuovoAccount:
			setTitle("Nuovo Account");
			break;
		case modificaAccount:
			setTitle("Modifica Account");
			project.Model.Account selectedAccount = upbc.getContactAccount(selectedIndexAccount[0]);
			
			this.txtApp.setText(selectedAccount.getAppName());
			this.txtNick.setText(selectedAccount.getNickname());
			this.txtWelcome.setText(selectedAccount.getWelcomeSentence());
			this.txtEmail.setText(selectedAccount.getEmail());
			
			break;
		default:
			setTitle("Nuovo Account");
		}
	}

}
