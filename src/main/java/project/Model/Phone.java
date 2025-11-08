package project.Model;

public class Phone implements Cloneable{
	protected String number; 

	public Phone(String number) {
		this.number = number;
	}

	public String getNumber() {
		return this.number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return String.format("%s", this.number);
	}
	
	@Override
    public boolean equals(Object object)
    {
        boolean isSame = false;
        if (object != null && object instanceof Phone)
        {
        	isSame = (this.number.equalsIgnoreCase(((Phone) object).number));
        }
        return isSame;
    }
	
	@Override
	public Object clone() {
	    try {
	        return (Phone) super.clone();
	    } catch (CloneNotSupportedException e) {
	        return null;
	    }
	}
}
