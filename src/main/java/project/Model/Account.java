package project.Model;

public class Account implements Cloneable{
	private String appName;
	private String nickname;
	private String email;
	private String welcomeSentence;
	
	public Account() {
		this.appName = "";
		this.nickname = "";
		this.email = "";
		this.welcomeSentence = "";
	}
	
	public Account(String appName, String nickname, String email, String welcomeSentence) {
		this.appName = appName;
		this.nickname = nickname;
		this.email = email;
		this.welcomeSentence = welcomeSentence;
	}
	
	public String getAppName() {
		return this.appName;
	}
	
	public String getNickname() {
		return this.nickname;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getWelcomeSentence() {
		return this.welcomeSentence;
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setWelcomeSentence(String welcomeSentence) {
		this.welcomeSentence = welcomeSentence;
	}
	
	@Override
	public String toString() {
		return String.format("Account per l'applicazione %s",this.appName);
	}
	
	@Override
    public boolean equals(Object object)
    {
        boolean isSame = false;

        if (object != null && object instanceof Account)
        {
        	isSame = (this.appName.equalsIgnoreCase(((Account) object).appName)) 
        				&& (this.nickname.equalsIgnoreCase(((Account) object).nickname))
        				&& (this.email.equalsIgnoreCase(((Account) object).email));
        }

        return isSame;
    }
	
	@Override
	public Object clone() {
	    try {
	        return (Account) super.clone();
	    } catch (CloneNotSupportedException e) {
	        return null;
	    }
	}	
	
}
