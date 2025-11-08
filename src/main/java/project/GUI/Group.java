package project.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import project.Controller.UltraPhoneBookController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class Group extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JFrame caller;
	private UltraPhoneBookController upbc;
	private JList lstContacts;
	private JList lstGroupContacts;
	protected int contactSeletectedIndex;
	
	private JButton btnRemoveContactFromGroup;
	private JButton btnAddContactToGoup;
	protected int contactGroupSeletectedIndex;
	
	private ArrayList<Integer> selectedContacts;
	private int selectedGroupToModify;
	
	private void reloadContacts(){
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		ArrayList<project.Model.Contact> contacts = this.upbc.getContacts();
		
		for(int index = 0; index < contacts.size() ; index++)
		{
			listModel.addElement(String.format("%s %s",contacts.get(index).getSurname(), contacts.get(index).getName()));
		}
		this.lstContacts.setModel(listModel);
	}
	
	private void reloadGroupContacts(){
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		ArrayList<project.Model.Contact> contacts = this.upbc.getSelectedGroupContacts();
		
		for(int index = 0; index < contacts.size() ; index++)
		{
			listModel.addElement(String.format("%s %s",contacts.get(index).getSurname(), contacts.get(index).getName()));
		}
		this.lstGroupContacts.setModel(listModel);
	}
	
	private void addGroup(String name) {
		String result = this.upbc.addGroup(name);
		if(result != "OK") {
			JFrame frMsg = new JFrame();
			frMsg.setAlwaysOnTop(true);
			frMsg.setLocationRelativeTo(contentPanel);
			JOptionPane.showMessageDialog(frMsg, result, "Aggiungi Gruppo",
			        JOptionPane.ERROR_MESSAGE);
		}else{
			dispose();
			this.caller.setVisible(true);
		}
	}
	
	private void updateGroup(String name) {
		String result = this.upbc.updateGroup(name, selectedGroupToModify);
		if(result != "OK") {
			JFrame frMsg = new JFrame();
			frMsg.setAlwaysOnTop(true);
			frMsg.setLocationRelativeTo(contentPanel);
			JOptionPane.showMessageDialog(frMsg, result, "Aggiungi Gruppo",
			        JOptionPane.ERROR_MESSAGE);
		}else{
			dispose();
			this.caller.setVisible(true);
		}
	}
	/**
	 * Create the dialog.
	 */
	public Group(UltraPhoneBookController upbc, DialogType dt, JFrame caller, int ... selectedGroupToModify) {
		
		this.upbc = upbc;
		this.caller = caller;
		
		selectedContacts = new ArrayList<Integer>();
		
		setTitle("Nuovo Gruppo");
		setBounds(100, 100, 495, 382);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("Nome gruppo");
				panel.add(lblNewLabel);
			}
			{
				txtName = new JTextField();
				panel.add(txtName);
				txtName.setColumns(15);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.WEST);
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setMaximumSize(new Dimension(200, 200));
					scrollPane.setPreferredSize(new Dimension(200, 200));
					panel_1.add(scrollPane);
					lstContacts = new JList();
					lstContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					lstContacts.addListSelectionListener(new ListSelectionListener() {
			            @Override
			            public void valueChanged(ListSelectionEvent arg0) {
			                if (!arg0.getValueIsAdjusting()) {
			                   contactSeletectedIndex = lstContacts.getSelectedIndex();
			                   if(contactSeletectedIndex >= 0 && !upbc.groupContainsContact(contactSeletectedIndex)) {
			                	   btnAddContactToGoup.setEnabled(true);
				           		   btnRemoveContactFromGroup.setEnabled(false);
				           		   lstGroupContacts.clearSelection();
			                   }else {
			                	   btnAddContactToGoup.setEnabled(false);
			                   }
			                }
			            }
			        });
					scrollPane.setViewportView(lstContacts);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.EAST);
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setMaximumSize(new Dimension(200, 200));
					scrollPane.setPreferredSize(new Dimension(200, 200));
					panel_1.add(scrollPane);
					lstGroupContacts = new JList();
					lstGroupContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					lstGroupContacts.addListSelectionListener(new ListSelectionListener() {
			            @Override
			            public void valueChanged(ListSelectionEvent arg0) {
			                if (!arg0.getValueIsAdjusting()) {
			                	contactGroupSeletectedIndex = lstGroupContacts.getSelectedIndex();
			                   if(contactGroupSeletectedIndex >= 0) {
			                	   btnAddContactToGoup.setEnabled(false);
				           		   btnRemoveContactFromGroup.setEnabled(true);
				           		   lstContacts.clearSelection();
			                   }
			                }
			            }
			        });
					scrollPane.setViewportView(lstGroupContacts);
				}
			}
			{
				btnRemoveContactFromGroup = new JButton("Rimuovi dal gruppo");
				btnRemoveContactFromGroup.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						upbc.removeContactToSelectedGroup(contactGroupSeletectedIndex);
						reloadContacts();
						reloadGroupContacts();
						btnRemoveContactFromGroup.setEnabled(false);
					} });
				panel.add(btnRemoveContactFromGroup, BorderLayout.SOUTH);
			}
			{
				btnAddContactToGoup = new JButton("Aggiungi al gruppo ->");
				btnAddContactToGoup.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						upbc.addContactToSelectedGroup(contactSeletectedIndex);
						reloadContacts();
						reloadGroupContacts();
						btnAddContactToGoup.setEnabled(false);
					} });
				panel.add(btnAddContactToGoup, BorderLayout.NORTH);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("SALVA");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(dt == DialogType.nuovoGruppo) {
							addGroup(txtName.getText());
						}else {
							updateGroup(txtName.getText());
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
						dispose();
					} });
				buttonPane.add(cancelButton);
			}
		}
		
		switch(dt) {
		case nuovoGruppo:
			setTitle("Nuovo Gruppo");
			break;
		case modificaGruppo:
			setTitle("Modifica Gruppo");
			this.selectedGroupToModify = selectedGroupToModify[0];
			txtName.setText(upbc.getNameOfGroupToModify());
			break;
		default:
			setTitle("Nuovo Gruppo");
		}
		this.reloadContacts();
		this.reloadGroupContacts();
		this.btnAddContactToGoup.setEnabled(false);
		this.btnRemoveContactFromGroup.setEnabled(false);
	}

}
