package project.GUI;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import project.Controller.UltraPhoneBookController;
import project.CustomException.DuplicatedContactException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

public class Contact extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private UltraPhoneBookController upbc;
	private JTextField txtName;
	private JTextField txtSurname;
    private JFrame frame;
    private JFrame caller;
    private JList lstEmail;
    private JList lstAccount;
    private JList lstAddress;
    private JList lstPhones;
    private JPanel pnlReserved;
    
    private JLabel lblImageProfile;
    
    private JButton btnModifyEmail;
    private JButton btnDeleteEmail;
    
    private JButton btnModifyAccount;
    private JButton btnDeleteAccount;
    
    private JButton btnModifyAddress;
    private JButton btnDeleteAddress;
    
    private JButton btnModifyPhone;
    private JButton btnDeletePhone;
    
    private JCheckBox cbIsReserved;
    
    private int accountsSeletectedIndex;
    private int phonesSeletectedIndex;
    private int addressesSeletectedIndex;
    private int emailsSeletectedIndex;
    private int contactsSelectedIndex;
    
    
    private String imagePath = "";
    private MainModality mm = MainModality.Visualizza;
    
	void update() {
		
		this.reloadEmails();
		this.reloadAccounts();
		this.reloadAddresses();
		this.reloadPhones();
		this.frame.setVisible(false);
	}
	
	private void reloadEmails(){
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		ArrayList<String> emails = this.upbc.getContactEmails();
		
		for(int index = 0; index < emails.size() ; index++)
		{
			listModel.addElement(emails.get(index));
		}
		this.lstEmail.setModel(listModel);
	}
	
	private void reloadAccounts(){
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		ArrayList<project.Model.Account> accounts = this.upbc.getContactAccounts();
		for(int index = 0; index < accounts.size() ; index++)
		{
			listModel.addElement(accounts.get(index).toString());
		}
		this.lstAccount.setModel(listModel);
	}
	
	private void reloadAddresses(){
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		ArrayList<project.Model.Address> addresses = this.upbc.getContactAddresses();
		for(int index = 0; index < addresses.size() ; index++)
		{
			listModel.addElement(addresses.get(index).toString());
		}
		this.lstAddress.setModel(listModel);
	}
	
	private void reloadPhones(){
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		ArrayList<project.Model.Phone> phones = this.upbc.getContactPhones();
		for(int index = 0; index < phones.size() ; index++)
		{
			listModel.addElement(phones.get(index).toString());
		}
		this.lstPhones.setModel(listModel);
	}
	
	 private void addContact(String name, String surname, Boolean isReserved, String imagePath) {
		 try {
			 String result = this.upbc.addContact(name, surname, isReserved, imagePath);
			 dispose();
			 this.caller.setVisible(true);
		 }catch(Exception ex) {
			JFrame frMsg = new JFrame();
			frMsg.setAlwaysOnTop(true);
			frMsg.setLocationRelativeTo(contentPanel);
			JOptionPane.showMessageDialog(frMsg, ex.getMessage(), "Aggiungi Contatto", JOptionPane.ERROR_MESSAGE);
		 }
	}
	 
	 private void updateContact(String name, String surname, Boolean isReserved, String imagePath) {
			try {
				 String result = this.upbc.updateContact(name, surname, isReserved, imagePath, contactsSelectedIndex, (mm == MainModality.Cerca ? true : false));
				 dispose();
				 this.caller.setVisible(true);
			 }catch(Exception ex) {
				JFrame frMsg = new JFrame();
				frMsg.setAlwaysOnTop(true);
				frMsg.setLocationRelativeTo(contentPanel);
				JOptionPane.showMessageDialog(frMsg, ex.getMessage(), "Modifica Contatto", JOptionPane.ERROR_MESSAGE);
			 }
	}
	
	/**
	 * Create the dialog.
	 */
	public Contact(UltraPhoneBookController upbc, DialogType dt, MainModality mm,JFrame caller, int ... selectedContactToModify) {
			
		this.frame = new JFrame();
		this.caller = caller;
		
		this.imagePath = upbc.getProfileImagePath();
		 
		this.frame.addComponentListener( new ComponentListener() {
			 @Override
            public void componentShown( ComponentEvent e ) {
           	 update();
            }
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentMoved(ComponentEvent e) {}
			@Override
			public void componentHidden(ComponentEvent e) {}
        } );
		this.mm = mm;
		this.upbc = upbc;
		
		setBounds(100, 100, 450, 368);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("Nome");
				panel.add(lblNewLabel);
			}
			{
				txtName = new JTextField();
				panel.add(txtName);
				txtName.setColumns(12);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Cognome");
				panel.add(lblNewLabel_1);
			}
			{
				txtSurname = new JTextField();
				panel.add(txtSurname);
				txtSurname.setColumns(12);
			}
		}
		{
			{
				
			}
		}
		{
			pnlReserved = new JPanel();
			contentPanel.add(pnlReserved, BorderLayout.SOUTH);
			{
				cbIsReserved = new JCheckBox("Contatto riservato");
				pnlReserved.add(cbIsReserved);
			}
		}
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPanel.add(tabbedPane, BorderLayout.CENTER);
			{
				JPanel pnlProfilo = new JPanel();
				tabbedPane.addTab("Profilo", null, pnlProfilo, null);
				{
					lblImageProfile = new JLabel("");
					lblImageProfile.setHorizontalAlignment(SwingConstants.CENTER);
					
					// Se l'immagine profilo è quella di default la carico dalle resource, altrimenti da percorso
					ImageIcon imageIcon;
					if(imagePath == "/resources/profileDefault.png") {
						imageIcon = new ImageIcon(getClass().getResource(imagePath));
					}else {
						imageIcon = new ImageIcon(imagePath);
					}
					
					Image image = imageIcon.getImage();
					Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); 
					ImageIcon newImageIcon = new ImageIcon(newimg);
					pnlProfilo.setLayout(new BorderLayout(0, 0));
					lblImageProfile.setIcon(newImageIcon);
					pnlProfilo.add(lblImageProfile);
				}
				{
					JButton btnSelectImage = new JButton("Scegli");
					btnSelectImage.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							final JFileChooser fc = new JFileChooser();
							FileFilter filter = new FileNameExtensionFilter("Image file", "jpg", "jpeg", "png", "bmp");
							fc.setFileFilter(filter);
							int returnVal = fc.showOpenDialog(caller);
							if (returnVal == JFileChooser.APPROVE_OPTION) {
								File file = fc.getSelectedFile();
								
					          	ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
							  	Image image = imageIcon.getImage();
								Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); 
								ImageIcon newImageIcon = new ImageIcon(newimg);
								pnlProfilo.setLayout(new BorderLayout(0, 0));
								lblImageProfile.setIcon(newImageIcon);
								imagePath = file.getPath();
					          
					        } else {
					           
					        }
							
						} });
					
					pnlProfilo.add(btnSelectImage, BorderLayout.SOUTH);
				}
			}
			{
				JPanel pnlEmails = new JPanel();
				tabbedPane.addTab("Emails", null, pnlEmails, null);
				pnlEmails.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					pnlEmails.add(scrollPane);
					{
						this.lstEmail = new JList();
						lstEmail.addListSelectionListener(new ListSelectionListener() {
					            @Override
					            public void valueChanged(ListSelectionEvent arg0) {
					                if (!arg0.getValueIsAdjusting()) {
					                   emailsSeletectedIndex = lstEmail.getSelectedIndex();
					                   if(emailsSeletectedIndex >= 0) {
					                	   btnModifyEmail.setEnabled(true);
						           		   btnDeleteEmail.setEnabled(true);
					                   }else{
					                	   btnModifyEmail.setEnabled(false);
						           		   btnDeleteEmail.setEnabled(false);
					                   }
					                }
					            }
					        });
						scrollPane.setViewportView(lstEmail);
					}
				}
				{
					JPanel panel = new JPanel();
					pnlEmails.add(panel, BorderLayout.SOUTH);
					{
						JButton btnAddEmail = new JButton("Aggiungi");
						btnAddEmail.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								Email dialogEmail = new Email(upbc,DialogType.nuovaEmail, frame);
								dialogEmail.setLocationRelativeTo(contentPanel);
								dialogEmail.setAlwaysOnTop(true);
								dialogEmail.setModal(true);
								dialogEmail.setVisible(true);
								
							} });
						panel.add(btnAddEmail);
					}
					{
					    btnModifyEmail = new JButton("Modifica");
						btnModifyEmail.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								Email dialogEmail = new Email(upbc,DialogType.modificaEmail, frame, emailsSeletectedIndex);
								dialogEmail.setLocationRelativeTo(contentPanel);
								dialogEmail.setAlwaysOnTop(true);
								dialogEmail.setModal(true);
								dialogEmail.setVisible(true);
								
							} });
						panel.add(btnModifyEmail);
					}
					{
						btnDeleteEmail = new JButton("Elimina");
						btnDeleteEmail.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								JFrame jf=new JFrame();
								jf.setAlwaysOnTop(true);
								int des = JOptionPane.showConfirmDialog(jf, "Sei sicuro di voler eliminare la mail selezionata?" ,"Richiesta conferma" , JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
								if(des == 0) {
									upbc.deleteEmailAtIndex(emailsSeletectedIndex);
									reloadEmails();
								}
							} });
						
						
						panel.add(btnDeleteEmail);
					}
				}
			}
			{
				JPanel pnlAccount = new JPanel();
				tabbedPane.addTab("Accounts", null, pnlAccount, null);
				pnlAccount.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					pnlAccount.add(scrollPane);
					{
						this.lstAccount = new JList();
						lstAccount.addListSelectionListener(new ListSelectionListener() {

					            @Override
					            public void valueChanged(ListSelectionEvent arg0) {
					                if (!arg0.getValueIsAdjusting()) {
					                   accountsSeletectedIndex = lstAccount.getSelectedIndex();
					                   if(accountsSeletectedIndex >= 0) {
					                	   btnModifyAccount.setEnabled(true);
						           		   btnDeleteAccount.setEnabled(true);
					                   }else{
					                	   btnModifyAccount.setEnabled(false);
						           		   btnDeleteAccount.setEnabled(false);
					                   }
					                }
					            }
					        });
						scrollPane.setViewportView(lstAccount);
					}
				}
				{
					JPanel panel = new JPanel();
					pnlAccount.add(panel, BorderLayout.SOUTH);
					{
						JButton btnAddAccount = new JButton("Aggiungi");
						btnAddAccount.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								Account dialogAccount = new Account(upbc,DialogType.nuovoAccount, frame);
								dialogAccount.setLocationRelativeTo(contentPanel);
								dialogAccount.setAlwaysOnTop(true);
								dialogAccount.setModal(true);
								dialogAccount.setVisible(true);
								
							} });
						panel.add(btnAddAccount);
					}
					{
						btnModifyAccount = new JButton("Modifica");
						btnModifyAccount.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								Account dialogAccount = new Account(upbc,DialogType.modificaAccount, frame, accountsSeletectedIndex);
								dialogAccount.setLocationRelativeTo(contentPanel);
								dialogAccount.setAlwaysOnTop(true);
								dialogAccount.setModal(true);
								dialogAccount.setVisible(true);
								
							} });
						panel.add(btnModifyAccount);
					}
					{
						btnDeleteAccount = new JButton("Elimina");
						btnDeleteAccount.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								JFrame jf=new JFrame();
								jf.setAlwaysOnTop(true);
								int des = JOptionPane.showConfirmDialog(jf, "Sei sicuro di voler eliminare l'account selezionato?" ,"Richiesta conferma" , JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
								if(des == 0) {
									upbc.deleteAccountAtIndex(accountsSeletectedIndex);
									reloadAccounts();
								}
							} });
						panel.add(btnDeleteAccount);
					}
				}
			}
			{
				JPanel pnlAddresses = new JPanel();
				tabbedPane.addTab("Indirizzi", null, pnlAddresses, null);
				pnlAddresses.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					pnlAddresses.add(scrollPane, BorderLayout.CENTER);
					{
						lstAddress = new JList();
						lstAddress.addListSelectionListener(new ListSelectionListener() {
				            @Override
				            public void valueChanged(ListSelectionEvent arg0) {
				                if (!arg0.getValueIsAdjusting()) {
				                   addressesSeletectedIndex = lstAddress.getSelectedIndex();
				                   if(addressesSeletectedIndex >= 0) {
				                	   btnModifyAddress.setEnabled(true);
					           		   btnDeleteAddress.setEnabled(true);
				                   }else{
				                	   btnModifyAddress.setEnabled(false);
					           		   btnDeleteAddress.setEnabled(false);
				                   }
				                }
				            }
				        });
						scrollPane.setViewportView(lstAddress);
					}
				}
				{
					JPanel panel = new JPanel();
					pnlAddresses.add(panel, BorderLayout.SOUTH);
					{
						JButton btnAddAddress = new JButton("Aggiungi");
						btnAddAddress.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								Address dialogAddress = new Address(upbc,DialogType.nuovoIndirizzo, frame);
								dialogAddress.setLocationRelativeTo(contentPanel);
								dialogAddress.setAlwaysOnTop(true);
								dialogAddress.setModal(true);
								dialogAddress.setVisible(true);
								
							} });
						panel.add(btnAddAddress);
					}
					{
						this.btnModifyAddress = new JButton("Modifica");
						btnModifyAddress.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								Address dialogAddress = new Address(upbc,DialogType.modificaIndirizzo, frame, addressesSeletectedIndex);
								dialogAddress.setLocationRelativeTo(contentPanel);
								dialogAddress.setAlwaysOnTop(true);
								dialogAddress.setModal(true);
								dialogAddress.setVisible(true);
								
							} });
						panel.add(btnModifyAddress);
					}
					{
						this.btnDeleteAddress = new JButton("Elimina");
						btnDeleteAddress.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								JFrame jf=new JFrame();
								jf.setAlwaysOnTop(true);
								int des = JOptionPane.showConfirmDialog(jf, "Sei sicuro di voler eliminare l'indirizzo selezionato?" ,"Richiesta conferma" , JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
								if(des == 0) {
									upbc.deleteAddressAtIndex(addressesSeletectedIndex);
									reloadAddresses();
								}
							} });
						panel.add(btnDeleteAddress);
					}
				}
			}
			{
				JPanel pnlPhones = new JPanel();
				tabbedPane.addTab("Telefoni", null, pnlPhones, null);
				pnlPhones.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					pnlPhones.add(scrollPane, BorderLayout.CENTER);
					{
						lstPhones = new JList();
						lstPhones.addListSelectionListener(new ListSelectionListener() {
				            @Override
				            public void valueChanged(ListSelectionEvent arg0) {
				                if (!arg0.getValueIsAdjusting()) {
				                   phonesSeletectedIndex = lstPhones.getSelectedIndex();
				                   if(phonesSeletectedIndex >= 0) {
				                	   btnModifyPhone.setEnabled(true);
					           		   btnDeletePhone.setEnabled(true);
				                   }else{
				                	   btnModifyPhone.setEnabled(false);
				                	   btnDeletePhone.setEnabled(false);
				                   }
				                }
				            }
				        });
						scrollPane.setViewportView(lstPhones);
					}
				}
				{
					JPanel panel = new JPanel();
					pnlPhones.add(panel, BorderLayout.SOUTH);
					{
						JButton btnAddPhone = new JButton("Aggiungi");
						btnAddPhone.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								Phone dialogPhone = new Phone(upbc, DialogType.nuovoNumero, frame);
								dialogPhone.setLocationRelativeTo(contentPanel);
								dialogPhone.setAlwaysOnTop(true);
								dialogPhone.setModal(true);
								dialogPhone.setVisible(true);
							} });
						panel.add(btnAddPhone);
					}
					{
						btnModifyPhone = new JButton("Modifica");
						btnModifyPhone.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								Phone dialogPhone = new Phone(upbc, DialogType.modificaNumero, frame, phonesSeletectedIndex);
								dialogPhone.setLocationRelativeTo(contentPanel);
								dialogPhone.setAlwaysOnTop(true);
								dialogPhone.setModal(true);
								dialogPhone.setVisible(true);
							} });
						panel.add(btnModifyPhone);
					}
					{
						btnDeletePhone = new JButton("Elimina");
						btnDeletePhone.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								JFrame jf=new JFrame();
								jf.setAlwaysOnTop(true);
								int des = JOptionPane.showConfirmDialog(jf, "Sei sicuro di voler eliminare il telefono selezionato?" ,"Richiesta conferma" , JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
								if(des == 0) {
									upbc.deletePhoneAtIndex(phonesSeletectedIndex);
									reloadPhones();
								}
							} });
						panel.add(btnDeletePhone);
					}
				}
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
						if(dt == DialogType.nuovoContatto) {
							addContact(txtName.getText(), txtSurname.getText(), cbIsReserved.isSelected(), imagePath);
						}else {
							updateContact(txtName.getText(), txtSurname.getText(), cbIsReserved.isSelected(), imagePath);
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
		case nuovoContatto:
			setTitle("Nuovo Contatto");
			break;
		case modificaContatto:
			setTitle("Modifica Contatto");
			this.contactsSelectedIndex = selectedContactToModify[0];
			txtName.setText(upbc.getNameOfContactToModify());
			txtSurname.setText(upbc.getSurnameOfContactToModify());
			imagePath = upbc.getProfileImagePathOfContactToModify();
			cbIsReserved.setSelected(upbc.contactToModifyIsReserved());
			update();
			break;
		default:
			setTitle("Nuovo Contatto");
		}
		
		// disable Modifiy & Delete btns
		this.btnModifyEmail.setEnabled(false);
		this.btnDeleteEmail.setEnabled(false);
		this.btnModifyAccount.setEnabled(false);
		this.btnDeleteAccount.setEnabled(false);
		this.btnModifyAddress.setEnabled(false);
		this.btnDeleteAddress.setEnabled(false);
		this.btnModifyPhone.setEnabled(false);
		this.btnDeletePhone.setEnabled(false);
		this.pnlReserved.setVisible(false);
	}

}
