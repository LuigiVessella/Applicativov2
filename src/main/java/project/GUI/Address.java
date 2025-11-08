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
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;

public class Address extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private UltraPhoneBookController upbc;
	private JTextField txtAddress;
	private JTextField txtCity;
	private JTextField txtCap;
	private JTextField txtNation;
	private JCheckBox cbIsMain;
	private JFrame caller;

	private void deleteAddress(project.Model.Address addressToDelete) {
			String result = this.upbc.deleteAddress(addressToDelete);
			if(result != "OK") {
				JFrame frMsg = new JFrame();
				frMsg.setAlwaysOnTop(true);
				frMsg.setLocationRelativeTo(contentPanel);
				JOptionPane.showMessageDialog(frMsg, result, "Elimina Indirizzo",
				        JOptionPane.ERROR_MESSAGE);
			}else{
				dispose();
				this.caller.setVisible(true);
			}
	}

	private void addAAddress(String address, String city, String nation, String cap, Boolean isMain) {
			String result = this.upbc.addAddress(address, city, nation, cap, isMain);
			if(result != "OK") {
				JFrame frMsg = new JFrame();
				frMsg.setAlwaysOnTop(true);
				frMsg.setLocationRelativeTo(contentPanel);
				JOptionPane.showMessageDialog(frMsg, result, "Aggiungi Indirizzo",
				        JOptionPane.ERROR_MESSAGE);
			}else{
				dispose();
				this.caller.setVisible(true);
			}
	}
	
	private void updateAddress(String address, String city, String nation, String cap, Boolean isMain, int selectedIndexAddress) {
		String result = this.upbc.updateAddress(address, city, nation, cap, isMain, selectedIndexAddress);
		if(result != "OK") {
			JFrame frMsg = new JFrame();
			frMsg.setAlwaysOnTop(true);
			frMsg.setLocationRelativeTo(contentPanel);
			JOptionPane.showMessageDialog(frMsg, result, "Modifica Indirizzo",
			        JOptionPane.ERROR_MESSAGE);
		}else{
			dispose();
			this.caller.setVisible(true);
		}
}

	/**
	 * Create the dialog.
	 */
	public Address(UltraPhoneBookController upbc, DialogType dt, JFrame caller, int ... selectedIndexAddress) {
		this.upbc = upbc;
		this.caller = caller;
		
		setBounds(100, 100, 450, 176);
		getContentPane().setLayout(new BorderLayout());
		FlowLayout fl_contentPanel = new FlowLayout();
		fl_contentPanel.setAlignment(FlowLayout.RIGHT);
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Indirizzo, n° civico");
			contentPanel.add(lblNewLabel);
		}
		{
			txtAddress = new JTextField();
			contentPanel.add(txtAddress);
			txtAddress.setColumns(24);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Città");
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtCity = new JTextField();
			contentPanel.add(txtCity);
			txtCity.setColumns(13);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Nazione");
			contentPanel.add(lblNewLabel_3);
		}
		{
			txtNation = new JTextField();
			contentPanel.add(txtNation);
			txtNation.setColumns(12);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("CAP");
			contentPanel.add(lblNewLabel_2);
		}
		{
			txtCap = new JTextField();
			contentPanel.add(txtCap);
			txtCap.setColumns(5);
		}
		{
			cbIsMain = new JCheckBox("Indirizzo principale");
			contentPanel.add(cbIsMain);
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
						
						if(dt == DialogType.modificaIndirizzo) {
							updateAddress(txtAddress.getText(),txtCity.getText(),txtNation.getText(),txtCap.getText(), cbIsMain.isSelected(), selectedIndexAddress[0]);
						}else {
							addAAddress(txtAddress.getText(),txtCity.getText(),txtNation.getText(),txtCap.getText(), cbIsMain.isSelected());
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
						
					} });
				buttonPane.add(cancelButton);
			}
		}
		
		switch(dt) {
		case nuovoIndirizzo:
			setTitle("Nuovo Indirizzo");
			break;
		case modificaIndirizzo:
			setTitle("Modifica Indirizzo");
			project.Model.Address selectedAddress = upbc.getContactAddress(selectedIndexAddress[0]);
			
			this.txtAddress.setText(selectedAddress.getAddress());
			this.txtCity.setText(selectedAddress.getCity());
			this.txtNation.setText(selectedAddress.getNation());
			this.txtCap.setText(selectedAddress.getZipCode());
			this.cbIsMain.setSelected(selectedAddress.isMain());
			break;
		default:
			setTitle("Nuovo Indirizzo");
		}
	}

}
