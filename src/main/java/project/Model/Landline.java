package project.Model;

public class Landline extends Phone{
	private String redirect;
	
	public Landline(String number, String redirect) {
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
		return String.format("Numero fisso %s con redirect su mobile %s", this.number, this.redirect);
	}
	
	@Override
    public boolean equals(Object object)
    {
        boolean isSame = false;
        if (object != null && object instanceof Landline)
        {
        	isSame = (this.number.equalsIgnoreCase(((Phone) object).number));
        }
        return isSame;
    }
	
}
