package project.GUI;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.w3c.dom.css.RGBColor;

import project.Controller.UltraPhoneBookController;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JColorChooser;

public class MainUltraPhoneBook {

	private JFrame frmUltraPhoneBook;
	private JFrame frame;
	private UltraPhoneBookController upbc;
	private JTable tblContacts;
	private JPanel pnlContactsList;
	private JPanel pnlGroupList;
	
	private JButton btnModifyContact;
	private JButton btnDeleteContact;
	private JButton btnClosePnlContactsList;
	
	JButton btnCloseGroups;
	JButton btnDeleteGroup;
	JButton btnModifyGroup;
	
	private int contactSeletectedRow;
	private int groupSeletectedRow;
	
	private MainModality modality;
	private JTable tblGroups;
	/**
	 * Create the application.
	 */
	public MainUltraPhoneBook(UltraPhoneBookController upbc) {
		this.upbc = upbc;
		initialize();
		frmUltraPhoneBook.setVisible(true);
	}
	
	private void loadContacts() {
		String[] columnNames = { "Cognome", "Nome" };
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
	    ArrayList<project.Model.Contact> contacts = upbc.getContacts();
	  
	    for(int index = 0; index < contacts.size(); index++) {
			Object[] data = {contacts.get(index).getSurname(), contacts.get(index).getName()};
			tableModel.addRow(data);
		}
		tblContacts.setModel(tableModel);
		tblContacts.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		modality = MainModality.Visualizza;
		this.frame.setVisible(false);
	
	}
	
	private void loadGroups() {
		String[] columnNames = {"Nome gruppo", "Numero contatti"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
	    ArrayList<project.Model.Group> groups = upbc.getGroups();
	  
	    for(int index = 0; index < groups.size(); index++) {
			Object[] data = {groups.get(index).getName(), groups.get(index).getNumberOfContacts()};
			tableModel.addRow(data);
		}
		tblGroups.setModel(tableModel);
		tblGroups.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		modality = MainModality.Visualizza;
		this.frame.setVisible(false);
		
	}
	
	private void loadFilteredContacts() {
		String[] columnNames = { "Cognome", "Nome" };
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
	    ArrayList<project.Model.Contact> contacts = upbc.getFilteredContacts();
	  
	    for(int index = 0; index < contacts.size(); index++) {
			Object[] data = {contacts.get(index).getSurname(), contacts.get(index).getName()};
			tableModel.addRow(data);
		}
		
		tblContacts.setModel(tableModel);
		tblContacts.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.frame.setVisible(false);
	
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		this.modality = MainModality.Visualizza;
		
		
		
		frmUltraPhoneBook = new JFrame();
		frmUltraPhoneBook.setTitle("ULTRA PHONE BOOK 2022");
		frmUltraPhoneBook.setBounds(100, 100, 700, 535);
		frmUltraPhoneBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.frame.addComponentListener( new ComponentListener() {
			 @Override
           public void componentShown( ComponentEvent e ) {
				 if(modality == MainModality.Visualizza) {
					 loadContacts();
					 loadGroups();
				 }
				 if(modality == MainModality.Cerca) {
					 loadFilteredContacts();
				 }
				
           }
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentMoved(ComponentEvent e) {}
			@Override
			public void componentHidden(ComponentEvent e) {}
       } );
		
		JPanel panel = new JPanel();
		frmUltraPhoneBook.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Powered by FR78");
		panel_1.add(lblNewLabel);
		
		pnlContactsList = new JPanel();
		panel.add(pnlContactsList, BorderLayout.NORTH);
		pnlContactsList.setLayout(new BorderLayout(0, 0));
		pnlContactsList.setVisible(false);
		
		JPanel pnlBtns = new JPanel();
		pnlBtns.setBackground(Color.BLUE);
		pnlContactsList.add(pnlBtns, BorderLayout.SOUTH);
		
		/*
		 * Button modifica contatto
		 * */
		
		btnModifyContact = new JButton("Modifica");
		btnModifyContact.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				upbc.selectContactToModify(contactSeletectedRow, (modality == MainModality.Cerca ? true : false));
				Contact dialogContact = new Contact(upbc, DialogType.modificaContatto, modality, frame, contactSeletectedRow);
				dialogContact.setLocationRelativeTo(frmUltraPhoneBook);
				dialogContact.setModal(true);
				dialogContact.setVisible(true);
				
			} });
		pnlBtns.add(btnModifyContact);
		
		/*
		 * Button elimina contatto
		 * */
		
		btnDeleteContact = new JButton("Elimina");
		btnDeleteContact.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf=new JFrame();
				jf.setAlwaysOnTop(true);
				int des = JOptionPane.showConfirmDialog(jf, "Sei sicuro di voler eliminare il contatto selezionato?" ,"Richiesta conferma" , JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(des == 0) {
					upbc.deletContactAtIndex(contactSeletectedRow, modality == MainModality.Cerca ? true : false);
					if(modality == MainModality.Cerca) {
						 loadFilteredContacts();
					}else {
						loadContacts();
					}
					
				}
			} });
		pnlBtns.add(btnDeleteContact);
		
		/*
		 * Button chiudi visualizzazione lista contatti
		 * */
		
		btnClosePnlContactsList = new JButton("Chiudi");
		btnClosePnlContactsList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlContactsList.setVisible(false); 
			} });
		pnlBtns.add(btnClosePnlContactsList);
		
		/*
		 * tabella contatti
		 */
		
		tblContacts = new JTable();
		tblContacts.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				contactSeletectedRow = tblContacts.getSelectedRow();
				if(contactSeletectedRow >= 0) {
             	   btnModifyContact.setEnabled(true);
	           	   btnDeleteContact.setEnabled(true);
                }else{
                   btnModifyContact.setEnabled(false);
 	           	   btnDeleteContact.setEnabled(false);
                }
			}
	    });
		tblContacts.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(tblContacts);
		JTableHeader header = tblContacts.getTableHeader();
	    header.setBackground(new Color(64,126,211));
	    
		pnlContactsList.add(scrollPane, BorderLayout.CENTER);
	
		
		JMenuBar menuBar = new JMenuBar();
		frmUltraPhoneBook .getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnUltraPhoneBook = new JMenu("UltraPhoneBook");
		menuBar.add(mnUltraPhoneBook);
		
		JMenuItem mnItemInfo = new JMenuItem("Info");
		mnItemInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Info dialogInfo = new Info();
				dialogInfo.setLocationRelativeTo(frmUltraPhoneBook);
				dialogInfo.setModal(true);
				dialogInfo.setVisible(true);
			}
		});
		mnUltraPhoneBook.add(mnItemInfo);
		
		JMenuItem mnItemExit = new JMenuItem("Esci");
		mnItemExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmUltraPhoneBook.dispose();
			}
		});
		mnUltraPhoneBook.add(mnItemExit);
		
		JMenu mnCotacts = new JMenu("Contatti");
		menuBar.add(mnCotacts);
		
		JMenuItem mnItemListContacts = new JMenuItem("Lista completa");
		mnItemListContacts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modality = MainModality.Visualizza;
				loadContacts();
				pnlContactsList.setVisible(true);
				pnlGroupList.setVisible(false);
			}
		});
		mnCotacts.add(mnItemListContacts);
		
		JMenuItem mnItemNewContact = new JMenuItem("Nuovo");
		mnItemNewContact.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modality = MainModality.Visualizza;
				upbc.addNewContact();
				Contact dialogContact = new Contact(upbc,DialogType.nuovoContatto,  modality, frame);
				dialogContact.setLocationRelativeTo(frmUltraPhoneBook);
				dialogContact.setModal(true);
				dialogContact.setVisible(true);
			}
		});
		mnCotacts.add(mnItemNewContact);
		
		JMenuItem mnItemSearchContact = new JMenuItem("Cerca");
		mnItemSearchContact.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modality = MainModality.Cerca;
				Search dialogSearch = new Search(upbc, frame);
				dialogSearch.setLocationRelativeTo(frmUltraPhoneBook);
				dialogSearch.setModal(true);
				dialogSearch.setVisible(true);
				
				
			}
		});
		mnCotacts.add(mnItemSearchContact);
		
		
		
		JMenu mnGroups = new JMenu("Gruppi");
		menuBar.add(mnGroups);
		
		JMenuItem mnItemListGroups = new JMenuItem("Lista");
		mnItemListGroups.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadGroups();
				pnlContactsList.setVisible(false);
				pnlGroupList.setVisible(true);
			}
		});
		mnGroups.add(mnItemListGroups);
		
		JMenuItem mnItemNewGroup = new JMenuItem("Nuovo");
		mnItemNewGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				upbc.addNewGroup();
				Group dialogGroup = new Group(upbc,DialogType.nuovoGruppo, frame);
				dialogGroup.setLocationRelativeTo(frmUltraPhoneBook);
				dialogGroup.setModal(true);
				dialogGroup.setVisible(true);
			}
		});
		mnGroups.add(mnItemNewGroup);
		
		btnModifyContact.setEnabled(false);
     	btnDeleteContact.setEnabled(false);
     	
     	pnlGroupList = new JPanel();
     	pnlGroupList.setBackground(Color.ORANGE);
     	panel.add(pnlGroupList, BorderLayout.CENTER);
     	pnlGroupList.setLayout(new BorderLayout(0, 0));
     	
     	JPanel pnlGroupBtns = new JPanel();
     	pnlGroupBtns.setBackground(Color.ORANGE);
     	pnlGroupList.add(pnlGroupBtns, BorderLayout.SOUTH);
     	
     	btnModifyGroup = new JButton("Modifica");
     	btnModifyGroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				upbc.selectGroupToModify(groupSeletectedRow);
				Group dialogGroup = new Group(upbc, DialogType.modificaGruppo, frame, groupSeletectedRow);
				dialogGroup.setLocationRelativeTo(frmUltraPhoneBook);
				dialogGroup.setModal(true);
				dialogGroup.setVisible(true);
			} });
     	pnlGroupBtns.add(btnModifyGroup);
     	
     	btnDeleteGroup = new JButton("Elimina");
     	btnDeleteGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFrame jf=new JFrame();
				jf.setAlwaysOnTop(true);
				int des = JOptionPane.showConfirmDialog(jf, "Sei sicuro di voler eliminare il gruppo selezionato?" ,"Richiesta conferma" , JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(des == 0) {
					upbc.deleteGroupAtIndex(groupSeletectedRow);
					loadGroups();
					
				}
				
			} });
     	pnlGroupBtns.add(btnDeleteGroup);
     	
     	btnCloseGroups = new JButton("Chiudi");
     	btnCloseGroups.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlGroupList.setVisible(false); 
			} });
     	pnlGroupBtns.add(btnCloseGroups);
     	
     	tblGroups = new JTable();
     	tblGroups.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				groupSeletectedRow = tblGroups.getSelectedRow();
				if(groupSeletectedRow >= 0) {
             	   btnModifyGroup.setEnabled(true);
	           	   btnDeleteGroup.setEnabled(true);
                }else{
                   btnModifyGroup.setEnabled(false);
 	           	   btnDeleteGroup.setEnabled(false);
                }
			}
	    });
     	tblGroups.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane_1 = new JScrollPane(tblGroups);
		JTableHeader headerGroup = tblGroups.getTableHeader();
		headerGroup.setBackground(new Color(247,148,13));
		pnlGroupList.add(scrollPane_1, BorderLayout.CENTER);
		
		
		btnModifyGroup.setEnabled(false);
		btnDeleteGroup.setEnabled(false);
		pnlContactsList.setVisible(false);
		pnlGroupList.setVisible(false);
	}

}
