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
import project.Model.SearchType;

import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class Search extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTextToSearch;
	private JRadioButton rbPhone;
	private JRadioButton rbEmail;
	private JRadioButton rbName;
	private JRadioButton rbAccount;
	private JFrame caller;

	/**
	 * Create the dialog.
	 */
	public Search(UltraPhoneBookController upbc, JFrame caller) {
		this.caller = caller;
		setTitle("Cerca contatto");
		setBounds(100, 100, 333, 161);
		getContentPane().setLayout(new BorderLayout());
		FlowLayout fl_contentPanel = new FlowLayout();
		fl_contentPanel.setAlignment(FlowLayout.RIGHT);
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			txtTextToSearch = new JTextField();
			contentPanel.add(txtTextToSearch);
			txtTextToSearch.setColumns(25);
		}
		{
			rbPhone = new JRadioButton("Numero");
			contentPanel.add(rbPhone);
		}
		{
			rbEmail = new JRadioButton("Email");
			contentPanel.add(rbEmail);
		}
		{
			rbName = new JRadioButton("Nome");
			contentPanel.add(rbName);
		}
		{
			rbAccount = new JRadioButton("Account");
			contentPanel.add(rbAccount);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("CERCA");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						SearchType st = rbEmail.isSelected() ? SearchType.Email : 
										(rbName.isSelected() ? SearchType.Nome : 
										(rbPhone.isSelected() ? SearchType.Numero : SearchType.Account));
						
						upbc.setSearchParameters(txtTextToSearch.getText(), st);
						
						dispose();		
						caller.setVisible(true);
					} });
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CHIUDI");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					} });
				buttonPane.add(cancelButton);
			}
		}
		
		 ButtonGroup group = new ButtonGroup();
		 group.add(rbPhone);
		 group.add(rbEmail);
		 group.add(rbName);
		 group.add(rbAccount);
		 
		 rbPhone.setSelected(true);
	}

}
