package project.Classi;

public class Contatti {
    //ATTRIBUTI
    private String nome; //Il nome del contatto
    private String cognome; //Il cognome del contatto
    private String foto; //SI INSERISCONO METTENDO DIRETTAMENTE IL PERCORSO DELLA FOTO NEL DATABASE COSI VIENE CARICATA
    private String email; //L'indirizzo email del contatto
    private String indirizzo; //L'indirizzo di casa del contatto
    private String cellulare; //Il numero di celullare del contatto
    private String fisso; //Il numero di telefono fisso del contatto
    private String nomeGruppo;
    private int id;


    //COSTRUTTORE
    public Contatti(String nome, String cognome, String cellulare, String fisso, String indirizzo, String email, int id) {
        this.nome = nome;
        this.cognome = cognome;
        this.cellulare = cellulare;
        this.fisso = fisso;
        this.email = email;
        this.indirizzo = indirizzo;
        this.id = id;
    }

    public Contatti() {

    }


    //GETTER SETTER


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeGruppo() {
        return nomeGruppo;
    }

    public void setNomeGruppo(String nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public String getFisso() {
        return fisso;
    }

    public void setFisso(String fisso) {
        this.fisso = fisso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
