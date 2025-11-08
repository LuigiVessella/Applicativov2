package project.Model;

public class Mobile extends Phone{
	private String redirect;
	
	public Mobile(String number, String redirect) {
		super(number);
		this.redirect = redirect;
	}
	
	public void setRedirect (String number) {
		this.redirect = number;
	}
	
	public String getRedirect() {
		return this.redirect;
	}
	
	@Override
	public String toString() {
		return String.format("Numero mobile %s con redirect su fisso %s", this.number, this.redirect);
	}
	
	@Override
    public boolean equals(Object object)
    {
        boolean isSame = false;
        if (object != null && object instanceof Mobile)
        {
        	isSame = (this.number.equalsIgnoreCase(((Phone) object).number));
        }
        return isSame;
    }

}