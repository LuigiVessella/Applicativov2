package project.Model;

import java.util.ArrayList;

import java.util.Collections;


public class Group implements Comparable<Group>, Cloneable{
	private String name;
	private ArrayList<Contact> contacts;
	
	public Group() {
		this.name = "";
		this.contacts = new ArrayList<Contact>();
	}
	
	public Group(String name) {
		this.name = name;
		this.contacts = new ArrayList<Contact>();
	}
	
	public Group(String name, ArrayList<Contact> contacts) {
		this.name = name;
		this.contacts = contacts;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	@Override
    public boolean equals(Object object)
    {
        boolean isSame = false;
        if (object != null && object instanceof Group)
        {
        	isSame = (this.name == ((Group) object).name);
        }
        return isSame;
    }
	
	@Override
	public int compareTo(Group o)
    {
        return this.name.compareToIgnoreCase(o.name);
    }
	
    public void addContact(Contact contact) {
    	this.contacts.add(contact);
		Collections.sort(this.contacts);
	}
    
    public void deleteContact(Contact contact) {
    	this.contacts.remove(contact);
    	Collections.sort(this.contacts);
	}
    
    public void deleteContactAtIndex(int index) {
    	this.contacts.remove(index);
    	Collections.sort(this.contacts);
	}

	public ArrayList<Contact> getContacts() {
		return this.contacts;
		
	}

	public boolean containsContact(Contact contact) {
		return this.contacts.contains(contact);
	}
	
	@Override
	public Object clone() {
		Group clone = new Group();
    	clone.setName(name);
    	clone.contacts = new ArrayList<Contact>(contacts);
    	return clone;
	}

	public String getNumberOfContacts() {
		return String.format("%d", this.contacts.size());
	}	
}
