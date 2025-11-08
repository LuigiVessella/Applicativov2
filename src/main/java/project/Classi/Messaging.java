package project.Classi;


public class Messaging {
    //ATTRIBUTI
    private String providerMessaggi;
    private String nickname;
    private String email;
    private String messaggioBenvenuto;
    private int idcontatto;



    //COSTRUTTORE
    public Messaging(String providerMessaggi, String nickname, String email, String messaggioBenvenuto, int idcontatto){

        this.providerMessaggi = providerMessaggi;
        this.nickname = nickname;
        this.email = email;
        this.idcontatto = idcontatto;
        this.messaggioBenvenuto = messaggioBenvenuto;
    }

    public Messaging() {

    }


    //GETTER SETTER

    public String getMessaggioBenvenuto() {
        return messaggioBenvenuto;
    }

    public void setMessaggioBenvenuto(String messaggioBenvenuto) {
        this.messaggioBenvenuto = messaggioBenvenuto;
    }

    public int getIdcontatto() {
        return idcontatto;
    }

    public void setIdcontatto(int idcontatto) {
        this.idcontatto = idcontatto;
    }

    public String getProviderMessaggi() {
        return providerMessaggi;
    }

    public void setProviderMessaggi(String providerMessaggi) {
        this.providerMessaggi = providerMessaggi;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
