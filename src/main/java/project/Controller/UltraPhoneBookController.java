package project.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import project.CustomException.*;
import project.Model.Account;
import project.Model.Address;
import project.Model.Contact;
import project.Model.Group;
import project.Model.Phone;
import project.Model.SearchType;

public class UltraPhoneBookController {
	private ArrayList<Contact> contacts;
	private ArrayList<Contact> filteredContacts;
	private ArrayList<Group> groups;
	private Contact selectedContact;
	private Group selectedGroup;
	private String textToSearch;
	private SearchType st;
	private String password;
	
	public UltraPhoneBookController() {
		this.contacts = new ArrayList<Contact>();
		this.filteredContacts = new ArrayList<Contact>();
		this.groups = new ArrayList<Group>();
		this.selectedContact = new Contact();
		this.password = "";
		new Contact();
	}
	
	public void addContact(Contact newContact) throws DuplicatedContactException, NoMobilePhoneException, NoLandlinePhoneException, NoMainAddressException{
		if (this.contacts.contains(newContact)) {
			throw new DuplicatedContactException(String.format("Non è possibile aggiungere il contatto %s %s perchè è già presente un contatto nella rubrica che utilizza una stessa mail.", newContact.getName(), newContact.getSurname()));
		}else{
			if(!newContact.getPhones().stream().anyMatch(c -> c instanceof project.Model.Mobile)) {
				throw new NoMobilePhoneException(String.format("Non è possibile aggiungere il contatto perchè ad esso non è associato nessun numenro di telefono mobile."));
			}else if(!newContact.getPhones().stream().anyMatch(c -> c instanceof project.Model.Landline)) {
				throw new NoLandlinePhoneException(String.format("Non è possibile aggiungere il contatto perchè ad esso non è associato nessun numenro di telefono fisso."));
			}else if(newContact.getAddresses().size() > 0 && !newContact.getAddresses().stream().anyMatch(c -> c.isMain() == true)){
				throw new NoMainAddressException(String.format("Non è possibile aggiungere il contatto perchè ad esso non è associato nessun indirizzo principale."));
			}else {
				this.contacts.add(newContact);
			}
		}
	}
	
	public void deleteContact(Contact contact) throws ContactNotFoundException {
		if (!this.contacts.contains(contact)) {
			throw new ContactNotFoundException(String.format("Non è possibile eliminare il contatto %s %s perchè non presente nella rubrica.", contact.getName(), contact.getSurname()));
		}else{
			this.contacts.remove(contact);
		}
	}
	
	public ArrayList<Contact> getOrderedContacts() {
		 Collections.sort(this.contacts);
		 return this.contacts;
	}
	
	public ArrayList<Group> getOrderedGroups() {
		 Collections.sort(this.groups);
		 return this.groups;
	}
	
	@Override
	public String toString() {
		String upbString = "Ecco la lista dei contatti:\n";
		for(Contact contact : this.contacts) {
			upbString += contact.toString() + "\n";
		}
		return upbString;
	}

	public String addAccount(String appName, String nickName, String email, String welcome){
		
		Account newAccount = new Account(appName, nickName,email, welcome);
		try {
			this.selectedContact.addAccount(newAccount);
		}catch(EmailNotFoundException ex) {
			return ex.getMessage();
		}catch(DuplicatedAccountException ex) {
			return ex.getMessage();
		}
		return "OK";
		
	}

	public void addNewContact() {
		this.selectedContact = new Contact();
		this.selectedContact.setProfileImagePath("/resources/profileDefault.png");
	}

	public String addEmail(String email) {
		try {
			this.selectedContact.addEmail(email);
		}catch(DuplicatedEmailException ex) {
			return ex.getMessage();
		}
		return "OK";
	}

	public String deleteEmail(String email) {
		try {
			this.selectedContact.deleteEmail(email);
		}catch(EmailNotFoundException ex) {
			return ex.getMessage();
		}
		return "OK";
	}

	public String deleteAccount(project.Model.Account accountToDelete) {
		try {
			this.selectedContact.deleteAccount(accountToDelete);
		}catch(AccountNotFoundException ex) {
			return ex.getMessage();
		}
		return "OK";
	}
	
	public ArrayList<String> getContactEmails(){
		return this.selectedContact.getEmails();
	}

	public ArrayList<project.Model.Account> getContactAccounts() {
		return this.selectedContact.getAccounts();
	}

	public ArrayList<project.Model.Address> getContactAddresses() {
		return this.selectedContact.getAddresses();
	}
	
	public ArrayList<project.Model.Phone> getContactPhones() {
		return this.selectedContact.getPhones();
	}

	public String deleteAddress(project.Model.Address addressToDelete) {
		try {
			this.selectedContact.deleteAddress(addressToDelete);
		}catch(AddressNotFoundException ex) {
			return ex.getMessage();
		}
		return "OK";
		
	}

	public String addAddress(String address, String city, String nation, String cap, Boolean isMain) {
		project.Model.Address newAddress = new project.Model.Address(address, city, nation, cap, isMain);
		try {
			this.selectedContact.addAddress(newAddress);
		}catch(DuplicatedMainAddressException ex) {
			return ex.getMessage();
		}catch(DuplicatedAddressException ex) {
			return ex.getMessage();
		}
		return "OK";
	}

	public String deletePhone(Phone phoneToDelete) {
		try {
			this.selectedContact.deletePhone(phoneToDelete);
		}catch(PhoneNotFoundException ex) {
			return ex.getMessage();
		}
		return "OK";
	}
	
	public String addPhone(String number, String redirect, Boolean isMobile) {		
		project.Model.Phone newPhone = isMobile ? new project.Model.Mobile(number, redirect) : new project.Model.Landline(number, redirect);
		try {
			this.selectedContact.addPhone(newPhone);
		}catch(DuplicatedPhoneException ex) {
			return ex.getMessage();
		}
		return "OK";
	}

	public project.Model.Account getContactAccount(int selectedIndexAccount) {
		return this.selectedContact.getAccount(selectedIndexAccount);
	}
	
	public project.Model.Address getContactAddress(int selectedIndexAddress) {
		return this.selectedContact.getAddress(selectedIndexAddress);
	}
	
	public String getContactEmail(int selectedIndexEmail) {
		return this.selectedContact.getEmail(selectedIndexEmail);
	}
	

	public Phone getContactPhone(int selectedIndexPhone) {
		return this.selectedContact.getPhone(selectedIndexPhone);
	}


	public void deleteAccountAtIndex(int accountsSeletectedIndex) {
		this.selectedContact.deleteAccountAtIndex(accountsSeletectedIndex);
		
	}
	
	public void deleteAddressAtIndex(int addressesSeletectedIndex) {
		this.selectedContact.deleteAddressAtIndex(addressesSeletectedIndex);
		
	}
	
	public void deleteEmailAtIndex(int aemailsSeletectedIndex) {
		this.selectedContact.deleteEmailAtIndex(aemailsSeletectedIndex);
		
	}
	
	public void deletePhoneAtIndex(int phonesSeletectedIndex) {
		this.selectedContact.deletePhoneAtIndex(phonesSeletectedIndex);
		
	}
	
	
	

	public String updateAccount(String appName, String nickName, String email, String welcome,
		int selectedIndexAccount) {
		
		Account updateAccount = new Account(appName, nickName,email, welcome);
		try {
			this.selectedContact.updateAccount(updateAccount, selectedIndexAccount);
		}catch(EmailNotFoundException ex) {
			return ex.getMessage();
		}catch(DuplicatedAccountException ex) {
			return ex.getMessage();
		}
		return "OK";
	}

	public String updateEmail(String email, int selectedIndexEmail) {
		try {
			this.selectedContact.updateEmail(email, selectedIndexEmail);
		}catch(DuplicatedEmailException ex) {
			return ex.getMessage();
		}
		return "OK";
	}

	public String updateAddress(String address, String city, String nation, String cap, Boolean isMain,
			int selectedIndexAddress) {
		
		project.Model.Address updateAddress = new project.Model.Address(address, city, nation, cap, isMain);
		try {
			this.selectedContact.updateAddress(updateAddress, selectedIndexAddress);
		}catch(DuplicatedMainAddressException ex) {
			return ex.getMessage();
		}catch(DuplicatedAddressException ex) {
			return ex.getMessage();
		}
		return "OK";
		
	}

	public String updatePhone(String number, String redirect, Boolean isMobile, int selectedIndexPhone) {
		project.Model.Phone updatePhone = isMobile ? new project.Model.Mobile(number, redirect) : new project.Model.Landline(number, redirect);
		try {
			this.selectedContact.updatePhone(updatePhone, selectedIndexPhone);
		}catch(DuplicatedPhoneException ex) {
			return ex.getMessage();
		}
		return "OK";
	}

	public String addContact(String name, String surname, Boolean isReserved, String imagePath)  throws DuplicatedContactException, NoMobilePhoneException, NoLandlinePhoneException, NoMainAddressException {
		
		this.selectedContact.setName(name);
		this.selectedContact.setSurname(surname);
		this.selectedContact.setReserved(isReserved);
		this.selectedContact.setProfileImagePath(imagePath);
		
		if (this.contacts.contains((Contact)selectedContact.clone())) {
			throw new DuplicatedContactException(String.format("Non è possibile aggiungere il contatto perchè è già presente un contatto nella rubrica che utilizza una stessa mail."));
		}else{
			if(!((Contact)selectedContact.clone()).getPhones().stream().anyMatch(c -> c instanceof project.Model.Mobile)) {
				throw new NoMobilePhoneException(String.format("Non è possibile aggiungere il contatto perchè ad esso non è associato nessun numenro di telefono mobile."));
			}else if(!((Contact)selectedContact.clone()).getPhones().stream().anyMatch(c -> c instanceof project.Model.Landline)) {
				throw new NoLandlinePhoneException(String.format("Non è possibile aggiungere il contatto perchè ad esso non è associato nessun numenro di telefono fisso."));
			}else if(((Contact)selectedContact.clone()).getAddresses().size() > 0 && !((Contact)selectedContact.clone()).getAddresses().stream().anyMatch(c -> c.isMain() == true)){
				throw new NoMainAddressException(String.format("Non è possibile aggiungere il contatto perchè ad esso non è associato nessun indirizzo principale."));
			}else {
				this.contacts.add(((Contact)selectedContact.clone()));
				Collections.sort(this.contacts);
			}
		}
		return "OK";
		
	}
	
   public ArrayList<Contact> getContacts(){
	   return this.contacts;
   }

	public void selectContactToModify(int contactSeletectedRow, boolean fromFiltered) {
		Contact contactToModify;
		if(fromFiltered) {
			contactToModify = (Contact)this.filteredContacts.get(contactSeletectedRow).clone();
			this.selectedContact = contactToModify;
		}else {
			contactToModify = (Contact)this.contacts.get(contactSeletectedRow).clone();
			this.selectedContact = contactToModify;
		}
		
	}
	
	public String getNameOfContactToModify() {
		return this.selectedContact.getName();
	}
	
	public String getSurnameOfContactToModify() {
		return this.selectedContact.getSurname();
	}
	
	public String getProfileImagePathOfContactToModify() {
		return this.selectedContact.getProfileImagePath();
	}

	public boolean contactToModifyIsReserved() {
		return this.selectedContact.isReserved();
	}
	
	public void deletContactAtIndex(int contactSeletectedRow,  boolean fromFiltered) {
		if(fromFiltered) {
			this.filteredContacts.remove(contactSeletectedRow);
		}else {
			this.contacts.remove(contactSeletectedRow);
		}
		
	}

	public String updateContact(String name, String surname, Boolean isReserved, String imagePath,
			int contactsSelectedIndex, boolean fromFiltered) throws DuplicatedContactException,NoMobilePhoneException,NoLandlinePhoneException,NoMainAddressException {
		this.selectedContact.setName(name);
		this.selectedContact.setSurname(surname);
		this.selectedContact.setReserved(isReserved);
		this.selectedContact.setProfileImagePath(imagePath);
		
		if (this.contacts.contains((Contact)selectedContact.clone())) {
			throw new DuplicatedContactException(String.format("Non è possibile aggiungere il contatto perchè è già presente un contatto nella rubrica che utilizza una stessa mail."));
		}else{
			if(!((Contact)selectedContact.clone()).getPhones().stream().anyMatch(c -> c instanceof project.Model.Mobile)) {
				throw new NoMobilePhoneException(String.format("Non è possibile aggiungere il contatto perchè ad esso non è associato nessun numenro di telefono mobile."));
			}else if(!((Contact)selectedContact.clone()).getPhones().stream().anyMatch(c -> c instanceof project.Model.Landline)) {
				throw new NoLandlinePhoneException(String.format("Non è possibile aggiungere il contatto perchè ad esso non è associato nessun numenro di telefono fisso."));
			}else if(((Contact)selectedContact.clone()).getAddresses().size() > 0 && !((Contact)selectedContact.clone()).getAddresses().stream().anyMatch(c -> c.isMain() == true)){
				throw new NoMainAddressException(String.format("Non è possibile aggiungere il contatto perchè ad esso non è associato nessun indirizzo principale."));
			}else {
				if(fromFiltered) {
					this.filteredContacts.set(contactsSelectedIndex, (Contact)selectedContact.clone());
					Collections.sort(this.filteredContacts);
				}else {
					this.contacts.set(contactsSelectedIndex, (Contact)selectedContact.clone());
					Collections.sort(this.contacts);
				}
			}
		}
		return "OK";
	}

	public ArrayList<Contact> getFilteredContacts() {
		this.filteredContacts = new ArrayList<Contact>();
		
		switch(this.st) {
		case Nome:
			
			this.filteredContacts = (ArrayList<Contact>) contacts.stream().filter(contact -> contact.getName().toLowerCase().contains(this.textToSearch.toLowerCase())).collect(Collectors.toList());
			
			break;
		case Numero:
			
			for(Contact contact:this.contacts) {
				ArrayList<Phone> phones = (ArrayList<Phone>) contact.getPhones().stream().filter(phone -> phone.getNumber().contains(this.textToSearch)).collect(Collectors.toList());
				if(phones != null & phones.size() > 0) {
					this.filteredContacts.add(contact);
				}
			}
			
			break;
		case Account:
			
			for(Contact contact:this.contacts) {
				ArrayList<Account> accounts = (ArrayList<Account>) contact.getAccounts().stream().filter(account -> account.getAppName().toLowerCase().contains(this.textToSearch.toLowerCase())).collect(Collectors.toList());
				if(accounts != null & accounts.size() > 0) {
					this.filteredContacts.add(contact);
				}
			}
			
			break;
		case Email:
			
			for(Contact contact:this.contacts) {
				ArrayList<String> emails = (ArrayList<String>) contact.getEmails().stream().filter(email -> email.toLowerCase().contains(this.textToSearch.toLowerCase())).collect(Collectors.toList());
				if(emails != null & emails.size() > 0) {
					this.filteredContacts.add(contact);
				}
			}
			
			break;
		default:
			break;
		}
		
		Collections.sort(this.filteredContacts);
		return this.filteredContacts;
	}


	public void setSearchParameters(String textToSearch, SearchType st) {
		this.textToSearch = textToSearch;
		this.st = st;
	}

	public String getProfileImagePath() {
		return this.selectedContact.getProfileImagePath();
	}

	public void setImageProfilePath(String absolutePath) {
		this.selectedContact.setProfileImagePath(absolutePath);
		
	}

	public void addNewGroup() {
		this.selectedGroup = new Group();
	}

	public ArrayList<Contact> getSelectedGroupContacts() {
		return this.selectedGroup.getContacts();
	}

	public void removeContactToSelectedGroup(int contactGroupSeletectedIndex) {
		this.selectedGroup.deleteContactAtIndex(contactGroupSeletectedIndex);
	}

	public void addContactToSelectedGroup(int contactSeletectedIndex) {
		Contact contact = this.contacts.get(contactSeletectedIndex);
		this.selectedGroup.addContact(contact);
	}

	public boolean groupContainsContact(int contactSeletectedIndex) {
		Contact contact = this.contacts.get(contactSeletectedIndex);
		return this.selectedGroup.containsContact(contact);
	}

	public String addGroup(String name) {
		this.selectedGroup.setName(name);
		this.groups.add((Group)selectedGroup.clone());
		Collections.sort(this.groups);
		return "OK";
	}

	public ArrayList<Group> getGroups() {
		return this.groups;
	}

	public void deleteGroupAtIndex(int groupSeletectedRow) {
		this.groups.remove(groupSeletectedRow);
	}

	public void selectGroupToModify(int groupSeletectedRow) {
		Group groupToModify = (Group)this.groups.get(groupSeletectedRow).clone();
		this.selectedGroup = groupToModify;
	}

	public String getNameOfGroupToModify() {
		return this.selectedGroup.getName();
	}

	public String updateGroup(String name, int contactGroupSeletectedIndex) {
		this.selectedGroup.setName(name);
		this.groups.set(contactGroupSeletectedIndex, (Group)selectedGroup.clone());
		Collections.sort(this.groups);
		
		return "OK";
	}

	
}
