package project.Controller;

import project.Classi.*;
import project.Gui.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


public class Controller{

    Contatti contatti;
    Messaging messaging;



    Homepage homepage;
    CreaContatto creaContatto;
    SchedaInfoContatto schedaInfoContatto;
    RicercaContatti ricercaContatti;
    MessagingGUI messagingGUI;
    Reindirizzamento reindirizzamento;
    //MAIN
    public static void main(String[] args) throws SQLException, IOException {
        Controller controller = new Controller();
    }


    //COSTRUTTORE
    public Controller() throws SQLException, IOException {

        homepage = new Homepage(this); //Serve a creare l'oggetto della GUI
        creaContatto = new CreaContatto(this);
        schedaInfoContatto = new SchedaInfoContatto(this);
        ricercaContatti = new RicercaContatti(this);

        homepage.setVisible(true);
        //startingAudio();


    }

    //METODI

    public void newCreaContatto() throws SQLException {
        creaContatto = null;
        creaContatto = new CreaContatto(this);
    }

    public void newSchedaInfoContatto() throws SQLException {
        schedaInfoContatto = null;
        schedaInfoContatto = new SchedaInfoContatto(this);
    }

    public void newRicercaContatti() throws SQLException {
        ricercaContatti = null;
        ricercaContatti = new RicercaContatti(this);
    }
    public void setJScrollPaneNorth(){
        homepage.getBorderLayoutBase().remove(homepage.getJScrollBarListaContatti());
        homepage.getBorderLayoutBase().add(homepage.getJScrollBarListaContatti(), BorderLayout.NORTH);
        homepage.getBorderLayoutBase().revalidate();
        homepage.getBorderLayoutBase().repaint();
    }
    public void setJScrollPaneCenter(){
        homepage.getBorderLayoutBase().remove(homepage.getJScrollBarListaContatti());
        homepage.getBorderLayoutBase().add(homepage.getJScrollBarListaContatti(), BorderLayout.CENTER);
        homepage.getBorderLayoutBase().revalidate();
        homepage.getBorderLayoutBase().repaint();
    }
    public void switchJPanelInView(JPanel accendi){
        homepage.getJScrollBarListaContatti().setViewportView(accendi);

    }

    public void clickAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        String audio = "Audio/click.wav";
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audio).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();

    }

    public void rollOverAudio() throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException {
        String audio = "Audio/rollover.wav";
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audio).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();

    }

    public Messaging popupProviderInserimentoInfo(Messaging messaging) throws SQLException, IOException {
        messagingGUI = new MessagingGUI(this);
        messaging = messagingGUI.inserimentoInfo(messaging);
        return messaging;
    }

    public void PopupProviderOttenimentoInfo(Messaging messaging) throws SQLException, IOException {
        messagingGUI = new MessagingGUI(this);

        messagingGUI.ottenimentoInfo(messaging);
    }

    public void popupReindirizzamentoCellulare(int id){
        reindirizzamento = new Reindirizzamento(this);
        reindirizzamento.chiamataCellulare(id);
    }
    public void popupReindirizzamentoFisso(int id){
        reindirizzamento = new Reindirizzamento(this);
        reindirizzamento.chiamataFisso(id);
    }

    public void startingAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        String audio = "Audio/avvioSoftware.wav";
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audio).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();



    }

    public void chiudiNotifica(JLabel lbErrore) {
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2500);
                    lbErrore.setVisible(false);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        };
        th.start();
    }


    //GETTER SETTER


    public RicercaContatti getRicercaContatti() {
        return ricercaContatti;
    }

    public void setRicercaContatti(RicercaContatti ricercaContatti) {
        this.ricercaContatti = ricercaContatti;
    }

    public MessagingGUI getMessagingGUI() {
        return messagingGUI;
    }

    public void setMessagingGUI(MessagingGUI messagingGUI) {
        this.messagingGUI = messagingGUI;
    }

    public SchedaInfoContatto getSchedaInfoContatto() {
        return schedaInfoContatto;
    }

    public void setSchedaInfoContatto(SchedaInfoContatto schedaInfoContatto) {
        this.schedaInfoContatto = schedaInfoContatto;
    }

    public CreaContatto getCreaContatto() {
        return creaContatto;
    }

    public void setCreaContatto(CreaContatto creaContatto) {
        this.creaContatto = creaContatto;
    }

    public Homepage getHomepage() {
        return homepage;
    }

    public void setHomepage(Homepage homepage) {
        this.homepage = homepage;
    }

    public Contatti getContatti() {
        return contatti;
    }

    public void setContatti(Contatti contatti) {
        this.contatti = contatti;
    }

    public Messaging getMessaging() {
        return messaging;
    }

    public void setMessaging(Messaging messaging) {
        this.messaging = messaging;
    }

    


}
