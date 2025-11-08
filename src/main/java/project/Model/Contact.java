package project.Model;

import java.util.ArrayList;
import java.util.HashSet;

import project.CustomException.*;

public class Contact implements Comparable<Contact>, Cloneable{
	private String name;
	private String surname;
	private String profileImagePath;
	private Boolean reserved;
	private ArrayList<String> emails;
	private ArrayList<Account> accounts;
	private ArrayList<Address> addresses;
	private ArrayList<Phone> phones;
	
	public Contact() {
		this.name = "";
		this.surname = "";
		this.profileImagePath = "";
		this.reserved = false;
		this.emails = new ArrayList<String>();
		this.accounts = new ArrayList<Account>();
		this.addresses = new ArrayList<Address>();
		this.phones = new ArrayList<Phone>();
	}
	
	public Contact(String name, String surname, String profileImagePath, Boolean reserved) {
		this.name = name;
		this.surname = surname;
		this.profileImagePath = profileImagePath;
		this.reserved = reserved;
		this.emails = new ArrayList<String>();
		this.accounts = new ArrayList<Account>();
		this.addresses = new ArrayList<Address>();
		this.phones = new ArrayList<Phone>();
	}
	
	public Contact(String name, String surname, String profileImagePath, Boolean reserved, ArrayList<String> emails, ArrayList<Account> accounts, ArrayList<Address> addresses, ArrayList<Phone> phones) {
		this.name = name;
		this.surname = surname;
		this.profileImagePath = profileImagePath;
		this.reserved = reserved;
		this.emails = emails;
		this.accounts = accounts;
		this.addresses = addresses;
		this.phones = phones;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public String getProfileImagePath() {
		return this.profileImagePath;
	}
	
	public Boolean isReserved() {
		return this.reserved;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}
	
	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}
	
	@Override
	public String toString() {
		String emails = "\nQueste sono le email associate al contatto:";
		for (String email : this.emails) {
			emails += String.format("\n\t%s",email);
		}
		
		String addresses = "\nQuesti sono gli indirizzi associati al contatto:";
		for (Address address : this.addresses) {
			addresses += String.format("\n\t%s",address.toString());
		}
		
		String accounts = "\nQuesti sono gli accounts associati al contatto:";
		for (Account account : this.accounts) {
			accounts += String.format("\n\t%s",account.toString());
		}
		
		String phones = "\nQuesti sono i numeri di telefono associati al contatto:";
		for (Phone phone : this.phones) {
			phones += String.format("\n\t%s",phone.toString());
		}
		
		return String.format("%s %s %s %s %s %s", this.name, this.surname, emails, addresses, accounts, phones);
	}
	
	public void addEmail(String email) throws DuplicatedEmailException {
		if(!this.emails.contains(email)) {
			this.emails.add(email);
		}else {
			throw new DuplicatedEmailException(String.format("Non è stato possibile aggiungere la mail %s perchè già presente nell'elenco emails del contatto!", email)); 
		}
		
	}
	
	public void deleteEmail(String email) throws EmailNotFoundException {
		if(this.emails.contains(email)) {
			this.emails.remove(email);
		}else {
			throw new EmailNotFoundException(String.format("Non è stato possibile rimuovere la mail %s perchè non è presente nell'elenco emails del contatto!", email)); 
		}
		
	}
	
	public void addAddress(Address address) throws DuplicatedAddressException, DuplicatedMainAddressException {
		if(!this.addresses.contains(address)) {
			
			Address mainAddress = this.addresses.stream().filter(x -> x.isMain())
			  .findAny()
			  .orElse(null);
			
			if(mainAddress != null) {
				throw new DuplicatedMainAddressException(String.format("Non è stato possibile aggiungere l'indirizzo perchè è già presente nell'elenco indirizzi del contatto un indirizzo principale!"));  
			}else {
				this.addresses.add(address);
			}
		}else {
			throw new DuplicatedAddressException(String.format("Non è stato possibile aggiungere l'indirizzo perchè già presente nell'elenco indirizzi del contatto!"));  
		}
		
	}
	
	public void deleteAddress(Address address) throws AddressNotFoundException {
		if(this.addresses.contains(address)) {
			this.addresses.remove(address);
		}else {
			throw new AddressNotFoundException(String.format("Non è stato possibile rimuovere l'indirizzo perchè non presente nell'elenco indirizzi del contatto.")); 
		}
		
	}
	
	public void addAccount(Account account) throws EmailNotFoundException, DuplicatedAccountException  {
		
		if(!this.emails.contains(account.getEmail())) {
			throw new EmailNotFoundException(String.format("Non è stato possibile aggiungere l'account perchè la mail %s associata non è presente nell'elenco emails del contatto!", account.getEmail())); 
		}else {
			if(!this.accounts.contains(account)) {
				this.accounts.add(account);
			}else {
				throw new DuplicatedAccountException(String.format("Non è stato possibile aggiungere l'account perchè già presente! Dettagli account: %s", account.toString())); 
			}
		}
	}
	
    public void updateAccount(Account account, int selectedIndex) throws EmailNotFoundException, DuplicatedAccountException  {
		
		if(!this.emails.contains(account.getEmail())) {
			throw new EmailNotFoundException(String.format("Non è stato possibile modificare l'account perchè la mail %s associata non è presente nell'elenco emails del contatto!", account.getEmail())); 
		}else {
			if(!this.accounts.contains(account)) {
				
				project.Model.Account selectedAccount = this.accounts.get(selectedIndex);
				selectedAccount.setAppName(account.getAppName());
				selectedAccount.setNickname(account.getNickname());
				selectedAccount.setWelcomeSentence(account.getWelcomeSentence());
				selectedAccount.setEmail(account.getEmail());
			}else {
				throw new DuplicatedAccountException(String.format("Non è stato possibile modificare l'account perchè un account con gli stessi dati è già presente! Dettagli account: %s", account.toString())); 
			}
		}
	}
    
    public void updateEmail(String email, int selectedIndexEmail) throws DuplicatedEmailException {
    	if(!this.emails.contains(email)) {
    	  this.emails.set(selectedIndexEmail, email);
		}else {
			throw new DuplicatedEmailException(String.format("Non è stato possibile modificare la mail in %s perchè già presente nell'elenco emails del contatto!", email)); 
		}
		
	}
    
	public void updatePhone(Phone updatePhone, int selectedIndexPhone) throws DuplicatedPhoneException {
		project.Model.Phone selectedPhone = this.phones.get(selectedIndexPhone);
		
		if(!this.phones.contains(selectedPhone)) {
			
			selectedPhone = updatePhone;
			
		}else {
			throw new DuplicatedPhoneException(String.format("Non è stato possibile inserire il numero telefonico (%s) perchè già presente nella lista del contatto!", updatePhone.toString())); 
		}
		
	}

    
    public void updateAddress(Address updateAddress, int selectedIndexAddress) throws DuplicatedMainAddressException, DuplicatedAddressException{
    	project.Model.Address selectedAddress = this.addresses.get(selectedIndexAddress);
    	if(!this.addresses.contains(updateAddress)) {
			
			Address mainAddress = this.addresses.stream().filter(isMain -> updateAddress.isMain() == true)
			  .findAny()
			  .orElse(null);
			
			if(mainAddress != null) {
				throw new DuplicatedMainAddressException(String.format("Non è stato possibile modificare l'indirizzo perchè è già presente nell'elenco indirizzi del contatto un indirizzo principale!"));  
			}else {
				selectedAddress.setAddress(updateAddress.getAddress());
				selectedAddress.setCity(updateAddress.getCity());
				selectedAddress.setNation(updateAddress.getNation());
				selectedAddress.setZipCode(updateAddress.getZipCode());
				selectedAddress.setMain(updateAddress.isMain());
			}
		} else {
			throw new DuplicatedAddressException(String.format("Non è stato possibile modificare l'indirizzo perchè già presente nell'elenco indirizzi del contatto!"));  
		}
		
	}
	
	public void deleteAccount(Account account) throws AccountNotFoundException {
		if(this.accounts.contains(account)) {
			this.accounts.remove(account);
		}else {
			throw new AccountNotFoundException(String.format("Non è stato possibile rimuovere l'account perchè non presente! Dettagli account: %s", account.toString())); 
		}
		
	}
	
	public void addPhone(Phone phone) throws DuplicatedPhoneException {
		if(!this.phones.contains(phone)) {
			this.phones.add(phone);
		}else {
			throw new DuplicatedPhoneException(String.format("Non è stato possibile inserire il numero telefonico (%s) perchè già presente nella lista del contatto!", phone.toString())); 
		}
		
	}
	
	public void deletePhone(Phone phone) throws PhoneNotFoundException {
		if(this.phones.contains(phone)) {
			this.phones.remove(phone);
		}else {
			throw new PhoneNotFoundException(String.format("Non è stato possibile eliminare il numero telefonico (%s) perchè non presente nella lista del contatto!", phone.toString())); 
		}
		
	}
	
	public ArrayList<String> getEmails(){
		return this.emails;
	}
	
	@Override
    public boolean equals(Object object)
    {
        boolean isSame = false;
        if (object != null && object instanceof Contact)
        { 	
        	if(this.emails.size() > 0 || ((Contact)object).emails.size() > 0) {
        		HashSet<String> set = new HashSet<>(); 
           	    set.addAll(this.emails);
                set.retainAll(((Contact)object).emails);
                
                String[] intersection = {};
                intersection = set.toArray(intersection);
                isSame = intersection.length > 0 ? true : false;
        	}else{
        		isSame = (this.name.equalsIgnoreCase(((Contact)object).name) == true) &&
        				 (this.surname .equalsIgnoreCase(surname) == true) &&
        				 (this.accounts.equals(((Contact)object).accounts) == true) &&
        				 (this.addresses.equals(((Contact)object).addresses) == true) &&
        				 (this.phones.equals(((Contact)object).phones) == true);
        	}
        	 
        }
        return isSame;
    }
	
	@Override
	public int compareTo(Contact o)
    {
        return this.surname.compareToIgnoreCase(o.surname);
    }

	public ArrayList<Account> getAccounts() {
		return this.accounts; 
	}

	public ArrayList<Address> getAddresses() {
		return this.addresses; 
	}

	public ArrayList<Phone> getPhones() {
		return this.phones; 
	}

	public Account getAccount(int selectedIndexAccount) {
		return this.accounts.get(selectedIndexAccount);
	}
	
	public String getEmail(int selectedIndexEmail) {
		return this.emails.get(selectedIndexEmail);
	}
	
	public Address getAddress(int selectedIndexAddress) {
		return this.addresses.get(selectedIndexAddress);
	}

	public Phone getPhone(int selectedIndexPhone) {
		return this.phones.get(selectedIndexPhone);
	}
	
	public void deleteAccountAtIndex(int accountsSeletectedIndex) {
		this.accounts.remove(accountsSeletectedIndex);
	}
	
	public void deleteEmailAtIndex(int emailsSeletectedIndex) {
		this.emails.remove(emailsSeletectedIndex);
	}
	
	public void deleteAddressAtIndex(int addressesSeletectedIndex) {
		this.addresses.remove(addressesSeletectedIndex);
	}
	
	public void deletePhoneAtIndex(int phonesSeletectedIndex) {
		this.phones.remove(phonesSeletectedIndex);
	}

	@Override
	public Object clone() {
		Contact clone = new Contact();
    	clone.setName(name);
    	clone.setSurname(surname);
    	clone.setProfileImagePath(profileImagePath);
    	clone.setReserved(reserved);
    	clone.accounts = new ArrayList<Account>(accounts);
    	clone.emails = new ArrayList<String>(emails);
    	clone.addresses = new ArrayList<Address>(addresses);
    	clone.phones = new ArrayList<Phone>(phones);
    	return clone;
	}	
}
