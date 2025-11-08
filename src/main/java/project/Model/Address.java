package project.Model;

public class Address implements Cloneable{
	private String address;
	private String city;
	private String zipCode;
	private String nation;
	private Boolean main;
	
	public Address() {
		this.address = "";
		this.city = "";
		this.zipCode = "";
		this.nation = "";
		this.main = false;
	}
	
	public Address(String address, String city,  String nation, String zipCode, Boolean main) {
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.nation = nation;
		this.main = main;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getZipCode() {
		return this.zipCode;
	}
	
	public String getNation() {
		return this.nation;
	}
	
	public Boolean isMain() {
		return this.main;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public void setNation(String nation) {
		this.nation = nation;
	}
	
	public void setMain(Boolean main) {
		this.main = main;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s %s",this.address, this.city, this.zipCode, this.nation);
	}
	
	@Override
    public boolean equals(Object object)
    {
        boolean isSame = false;

        if (object != null && object instanceof Address)
        {
        	isSame = (this.address.equalsIgnoreCase(((Address) object).address))
        				&& (this.city.equalsIgnoreCase(((Address) object).city))
        				&& (this.zipCode.equalsIgnoreCase(((Address) object).zipCode))
        				&& (this.nation.equalsIgnoreCase(((Address) object).nation))
        				&& (this.main == ((Address) object).isMain());
        }

        return isSame;
    }
	
	@Override
	public Object clone() {
	    try {
	        return (Address) super.clone();
	    } catch (CloneNotSupportedException e) {
	        return null;
	    }
	}
}
