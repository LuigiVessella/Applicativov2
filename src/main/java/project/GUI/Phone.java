package project.GUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.Controller.UltraPhoneBookController;
import project.Model.Landline;
import project.Model.Mobile;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class Phone extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private UltraPhoneBookController upbc;
	private JTextField txtNumber;
	private JTextField txtRedirect;
	private JRadioButton rbMobile;
	private JRadioButton rbLandline ;
	private JFrame caller;
	
	private void deletePhone(project.Model.Phone phoneToDelete) {
			String result = this.upbc.deletePhone(phoneToDelete);
			if(result != "OK") {
				JFrame frMsg = new JFrame();
				frMsg.setAlwaysOnTop(true);
				frMsg.setLocationRelativeTo(contentPanel);
				JOptionPane.showMessageDialog(frMsg, result, "Elimina telefono",
				        JOptionPane.ERROR_MESSAGE);
			}else{
				dispose();
				this.caller.setVisible(true);
			}
	}

	private void addPhone(String number, String redirect, Boolean isMobile) {
			String result = this.upbc.addPhone(number, redirect, isMobile);
			if(result != "OK") {
				JFrame frMsg = new JFrame();
				frMsg.setAlwaysOnTop(true);
				frMsg.setLocationRelativeTo(contentPanel);
				JOptionPane.showMessageDialog(frMsg, result, "Aggiungi Telefono",
				        JOptionPane.ERROR_MESSAGE);
			}else{
				dispose();
				this.caller.setVisible(true);
			}
	}
	
	private void updatePhone(String number, String redirect, Boolean isMobile, int selectedIndexPhone) {
		String result = this.upbc.updatePhone(number, redirect, isMobile, selectedIndexPhone);
		if(result != "OK") {
			JFrame frMsg = new JFrame();
			frMsg.setAlwaysOnTop(true);
			frMsg.setLocationRelativeTo(contentPanel);
			JOptionPane.showMessageDialog(frMsg, result, "Modifica Telefono",
			        JOptionPane.ERROR_MESSAGE);
		}else{
			dispose();
			this.caller.setVisible(true);
		}
}
	/**
	 * Create the dialog.
	 */
	public Phone(UltraPhoneBookController upbc, DialogType dt, JFrame caller, int ... selectedIndexPhone) {
		this.upbc = upbc;
		this.caller = caller;
	
		setBounds(100, 100, 245, 173);
		getContentPane().setLayout(new BorderLayout());
		FlowLayout fl_contentPanel = new FlowLayout();
		fl_contentPanel.setAlignment(FlowLayout.RIGHT);
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Numero");
			contentPanel.add(lblNewLabel);
		}
		{
			txtNumber = new JTextField();
			contentPanel.add(txtNumber);
			txtNumber.setColumns(13);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Inoltra a");
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtRedirect = new JTextField();
			contentPanel.add(txtRedirect);
			txtRedirect.setColumns(13);
		}
		{
			rbMobile = new JRadioButton("Mobile");
			contentPanel.add(rbMobile);
		}
		{
			rbLandline = new JRadioButton("Fisso");
			contentPanel.add(rbLandline);
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
						
						
						if(dt == DialogType.modificaNumero) {
							boolean isMobile = rbMobile.isSelected() ? true : false;
							updatePhone(txtNumber.getText(), txtRedirect.getText(), isMobile, selectedIndexPhone[0]);
						}else {
							boolean isMobile = rbMobile.isSelected() ? true : false;
							addPhone(txtNumber.getText(), txtRedirect.getText(), isMobile);
						}
						
						
					} });
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
		case nuovoNumero:
			setTitle("Nuovo Numero");
			rbMobile.setSelected(true);
			break;
		case modificaNumero:
			setTitle("Modifica Numero");
			
			project.Model.Phone selectedPhone = upbc.getContactPhone(selectedIndexPhone[0]);
			
			 if(selectedPhone instanceof Mobile) {
				 txtNumber.setText(((Mobile)selectedPhone).getNumber());
				 txtRedirect.setText(((Mobile)selectedPhone).getRedirect());
				 rbMobile.setSelected(true);
			 }else{
				 txtNumber.setText(((Landline)selectedPhone).getNumber());
				 txtRedirect.setText(((Landline)selectedPhone).getRedirect());
				 rbLandline.setSelected(true);
			 }
			
			break;
		default:
			setTitle("Nuovo Numero");
			rbMobile.setSelected(true);
		}
		
		 ButtonGroup group = new ButtonGroup();
		 group.add(rbMobile);
		 group.add(rbLandline);
		 
		 
	}

}
