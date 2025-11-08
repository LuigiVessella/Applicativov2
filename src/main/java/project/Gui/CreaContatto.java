package project.Gui;

import project.Classi.Contatti;
import project.Classi.Messaging;
import project.Controller.Controller;
import project.DAO.*;
import project.ImplementazioniDAO.*;

import com.formdev.flatlaf.FlatLightLaf;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class CreaContatto extends JPanel {
    /////////////////////////////////////////////////////       ATTRIBUTI       /////////////////////////////////////////////////////
    private JPanel creaContatto;
    private JPanel jpTxtEmail;
    private JPanel jpTxtIndirizzo;

    private JTextField txtNome;
    private JTextField txtCognome;
    private JTextField txtEmail;
    private JTextField txtIndirizzo;
    private JTextField txtCellulare;
    private JTextField txtFisso;

    private JLabel lbNome;
    private JLabel lbIndirizzo;
    private JLabel lbCognome;
    private JLabel lbEmail;
    private JLabel lbCellulare;
    private JLabel lbFisso;
    private JLabel lbMessaggioErrore;
    private JLabel imgErroreNome;
    private JLabel imgErroreCognome;
    private JLabel imgErroreFisso;
    private JLabel imgErroreCellulare;
    private JLabel imgErroreEmail;
    private JLabel imgErroreIndirizzo;

    private JButton btnSvuotaCampi;
    private JButton btnConferma;
    private JButton btnTastoHome;
    private JButton btnPiuEmail;
    private JButton btnPiuIndirizzi;
    private JButton btnCaricaImmagine;
    private JButton btnWhatsApp;
    private JButton btnTelegram;

    private JFileChooser fileChooser;
    private JComboBox cbWhatsapp;
    private JComboBox cbTelegram;
    private JComboBox cbGruppi;


    /////////////////////////////////////////////////////       OGGETTI     /////////////////////////////////////////////////////
    Controller control;
    Contatti contatto;
    Messaging whatsApp = new Messaging();
    Messaging telegram = new Messaging();

    //Arraylist per stampa, DEBUG, dovrà essere sostituito con inserimento da DATABASE
    static ArrayList<Contatti> insContatti = new ArrayList<>();
    static ArrayList<Contatti> insContattiCopia = new ArrayList<>();
    ArrayList<JTextField> listaTxtEmail = new ArrayList<>();
    ArrayList<JTextField> listaTxtIndirizzo = new ArrayList<>();

    /////////////////////////////////////////////////////       COSTRUTTORE     /////////////////////////////////////////////////////
    public CreaContatto(Controller controller) throws SQLException {
        control = controller;

        temaScuro();
        messaggiErroreFALSE();
        funzionalitaTasti();

    }

    /////////////////////////////////////////////////////       FUNZIONALITA' PULSANTI GUI      /////////////////////////////////////////////////////
    public void funzionalitaTasti() throws SQLException {

        //FUNZIONI TASTO "CONFERMA"
        String errNomeLungo = "Hai inserito un nome non valido";
        String errCognomeLungo = "Hai inserito un cognome non valido";
        String errCellulareNonValido = "Hai inserito un numero cellulare non valido";
        String errFissoNonValido = "Hai inserito un fisso non valido";
        String errEmailNonValido = "Hai inserito un indirizzo email non valido";
        String errIndirizzoNonValido = "Hai inserito un indirizzo non valido";

        btnConferma.setEnabled(false);
        getBtnConferma().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messaggiErroreFALSE();
                creaContatto.repaint();
                if (txtNome.getText().length() > 20 || txtNome.getText().length() == 0) {
                    lbErroreInserimento(errNomeLungo);
                    imgErroreNome.setVisible(true);
                } else if (txtCognome.getText().length() > 20 || txtCognome.getText().length() == 0) {
                    lbErroreInserimento(errCognomeLungo);
                    imgErroreCognome.setVisible(true);
                } else if (txtCellulare.getText().length() != 10) {
                    lbErroreInserimento(errCellulareNonValido);
                    imgErroreCellulare.setVisible(true);
                } else if ((txtFisso.getText().length() < 8 || txtFisso.getText().length() > 11) || txtFisso.getText().length() == 0) {
                    lbErroreInserimento(errFissoNonValido);
                    imgErroreFisso.setVisible(true);
                } else if (txtEmail.getText().length() > 30 || txtEmail.getText().length() == 0) {
                    lbErroreInserimento(errEmailNonValido);
                    imgErroreEmail.setVisible(true);
                } else if (!controlliIndirizzo()) {
                    lbErroreInserimento(errIndirizzoNonValido);
                    imgErroreIndirizzo.setVisible(true);
                } else {
                    try {
                        control.clickAudio();
                        control.setJScrollPaneNorth();
                        inserimentoContattoDatabase();

                    } catch (SQLException | UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        //FUNZIONI TASTO "SVUOTA CAMPI"
        getBtnSvuotaCampi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                svuotaCampi();
            }
        });

        //FUNZIONI TASTO "CARICA IMMAGINI"
        ImageIcon imgCaricaImmaginiGrande = new ImageIcon("Immagini/imgAggiungiFoto64pxScuro.png");
        btnCaricaImmagine.setIcon(imgCaricaImmaginiGrande);

        btnCaricaImmagine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser("D:\\GitHub\\Java\\Rubrica - ProgettoOO_BD\\Immagini\\ImmaginiContatto");
                int valoreRitorno = fileChooser.showOpenDialog(null);
                if (valoreRitorno == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String percorsoAssoluto = selectedFile.getAbsolutePath();
                    ImageIcon immagineProfilo = new ImageIcon(percorsoAssoluto);
                    try {
                        btnCaricaImmagine.setIcon(immagineProfilo);
                        btnCaricaImmagine.setActionCommand(percorsoAssoluto);
                    } catch (Exception b) {
                        lbMessaggioErrore.setText("impossibile caricare l'immagine dal disco");
                        control.chiudiNotifica(lbMessaggioErrore);
                    }
                }
            }
        });

        //FUNZIONALITA' cbGruppi    //INSERIRE FUNZIONE PER LA CREAZIONE DI UN NUOVO GRUPPO DA GUI E FARE LA RELATIVA FUNZIONE SQL PER INSERIRLA NEL DB. //AGGIUSTARE
        ArrayList<String> infoGruppi = new ArrayList<>();
        GruppoDAO gruppoDAO = new GruppoPostgreSQL();
        infoGruppi = gruppoDAO.cercaGruppi();
        cbGruppi.addItem("");
        for(int i = 0; i < infoGruppi.size(); i++)
            cbGruppi.addItem(infoGruppi.get(i));
        cbGruppi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

    }

    public void lbErroreInserimento(String codiceErrore){

        lbMessaggioErrore.setText(codiceErrore);
        lbMessaggioErrore.setVisible(true);
    }

    public void svuotaCampi() {
        txtNome.setText("");
        txtCognome.setText("");
        txtCellulare.setText("");
        txtFisso.setText("");
        txtEmail.setText("");
        txtIndirizzo.setText("");

        messaggiErroreFALSE();
    }

    public void textureTasti(){

        //btnTastoHome
        ImageIcon imgTastoHome = new ImageIcon("Immagini/imgRitornoHomeFreccia24px.png");
        ImageIcon imgTastoHomeGrande = new ImageIcon("Immagini/imgRitornoHomeFreccia32px.png");

        btnTastoHome.setIcon(imgTastoHome);
        btnTastoHome.setMargin(new Insets(0,0,0,0));
        btnTastoHome.setContentAreaFilled(false);
        btnTastoHome.setBorderPainted(false);
        btnTastoHome.setBorder(null);
        btnTastoHome.setFocusPainted(false);
        btnTastoHome.setOpaque(true);


        btnTastoHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    control.setJScrollPaneNorth();
                    control.switchJPanelInView(control.getHomepage().getPaneBase());
                    control.clickAudio();
                    control.getHomepage().stampaContatti();
                    control.getHomepage().getJScrollBarListaContatti().setBorder(BorderFactory.createTitledBorder("Lista dei Contatti"));//TESTING
                } catch (SQLException | UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                try {
                    btnTastoHome.setIcon(imgTastoHomeGrande);
                    control.rollOverAudio();
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnTastoHome.setIcon(imgTastoHome);
            }
        });

        //btnPiuIndirizzi
        ImageIcon piuIndirizzi = new ImageIcon("Immagini/imgIconaAggiungiIndirizzo-Email16px.png");
        ImageIcon piuIndirizziGrande = new ImageIcon("Immagini/imgIconaAggiungiIndirizzo-Email24px.png");

        btnPiuIndirizzi.setIcon(piuIndirizzi);
        btnPiuIndirizzi.setMargin(new Insets(0,0,0,0));
        btnPiuIndirizzi.setContentAreaFilled(false);
        btnPiuIndirizzi.setBorderPainted(false);
        btnPiuIndirizzi.setBorder(null);
        btnPiuIndirizzi.setFocusPainted(false);
        btnPiuIndirizzi.setOpaque(true);


        btnPiuIndirizzi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    control.clickAudio();

                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }

                int i = listaTxtIndirizzo.size() - 1;
                if (!txtIndirizzo.getText().isEmpty()) {
                    if (listaTxtIndirizzo.size() == 0)
                        aggiuntaTxtFieldIndirizzo();

                    else if ((listaTxtIndirizzo.size() - i) == 1 && !listaTxtIndirizzo.get(i).getText().isEmpty())
                        aggiuntaTxtFieldIndirizzo();
                    else {
                        lbErroreInserimento("Inserire l'indirizzo secondario precedente");
                        control.chiudiNotifica(lbMessaggioErrore);
                    }
                } else {
                    lbErroreInserimento("Inserire l'indirizzo principale");
                    control.chiudiNotifica(lbMessaggioErrore);
                }
            }


            @Override
            public void mouseEntered(MouseEvent e) {
                btnPiuIndirizzi.setIcon(piuIndirizziGrande);
                try {
                    control.rollOverAudio();
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            public void mouseExited(MouseEvent e) {
                btnPiuIndirizzi.setIcon(piuIndirizzi);
            }
        });

        //btnPiuEmail
        ImageIcon piuEmail = new ImageIcon("Immagini/imgIconaAggiungiIndirizzo-Email16px.png");
        ImageIcon piuEmailGrande = new ImageIcon("Immagini/imgIconaAggiungiIndirizzo-Email24px.png");

        btnPiuEmail.setIcon(piuIndirizzi);
        btnPiuEmail.setMargin(new Insets(0,0,0,0));
        btnPiuEmail.setContentAreaFilled(false);
        btnPiuEmail.setBorderPainted(false);
        btnPiuEmail.setBorder(null);
        btnPiuEmail.setFocusPainted(false);
        btnPiuEmail.setOpaque(true);

        btnPiuEmail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    control.clickAudio();
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }

                int i = listaTxtEmail.size()-1;

                if(!txtEmail.getText().isEmpty()) {
                    if (listaTxtEmail.size() == 0)
                        aggiuntaTxtFieldEmail();
                    else if ((listaTxtEmail.size() - i) == 1 && !listaTxtEmail.get(i).getText().isEmpty())
                        aggiuntaTxtFieldEmail();
                    else {
                        lbErroreInserimento("Inserire email secondario precedente");
                        control.chiudiNotifica(lbMessaggioErrore);
                    }
                }
                else {
                    lbErroreInserimento("Inserire l'email principale");
                    control.chiudiNotifica(lbMessaggioErrore);
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnPiuEmail.setIcon(piuEmailGrande);
                try {
                    control.rollOverAudio();
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            public void mouseExited(MouseEvent e) {
                btnPiuEmail.setIcon(piuEmail);
            }
        });

        //btnCaricaImmagini
        btnCaricaImmagine.setMargin(new Insets(0,0,0,0));
        btnCaricaImmagine.setContentAreaFilled(false);
        btnCaricaImmagine.setBorderPainted(false);
        btnCaricaImmagine.setBorder(null);
        btnCaricaImmagine.setFocusPainted(false);
        btnCaricaImmagine.setOpaque(true);

        //btnWhatsApp
        ImageIcon imgWhatsApp = new ImageIcon("Immagini/imgWA16px.png");
        ImageIcon imgWhatsAppGrande = new ImageIcon("Immagini/imgWA24px.png");
        btnWhatsApp.setContentAreaFilled(false);
        btnWhatsApp.setBorderPainted(false);
        btnWhatsApp.setBorder(null);
        btnWhatsApp.setFocusPainted(false);
        btnWhatsApp.setOpaque(true);
        btnWhatsApp.setIcon(imgWhatsApp);
        btnWhatsApp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                whatsApp.setProviderMessaggi("WhatsApp");
                try {
                    control.clickAudio();
                    whatsApp = control.popupProviderInserimentoInfo(whatsApp);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
            public void mouseEntered(MouseEvent e) {
                try {
                    btnWhatsApp.setIcon(imgWhatsAppGrande);
                    control.rollOverAudio();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            public void mouseExited(MouseEvent e) {
                btnWhatsApp.setIcon(imgWhatsApp);
            }
        });

        //btnTelegram
        ImageIcon imgTelegram = new ImageIcon("Immagini/imgTelegram16px.png");
        ImageIcon imgTelegramGrande = new ImageIcon("Immagini/imgTelegram24px.png");
        btnTelegram.setIcon(imgTelegram);
        btnTelegram.setContentAreaFilled(false);
        btnTelegram.setBorderPainted(false);
        btnTelegram.setBorder(null);
        btnTelegram.setFocusPainted(false);
        btnTelegram.setOpaque(true);

        btnTelegram.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                telegram.setProviderMessaggi("Telegram");
                try {
                    control.clickAudio();
                    telegram = control.popupProviderInserimentoInfo(telegram);
                    btnConferma.setEnabled(true);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
            public void mouseEntered(MouseEvent e) {
                try {
                    btnTelegram.setIcon(imgTelegramGrande);
                    control.rollOverAudio();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            public void mouseExited(MouseEvent e) {
                btnTelegram.setIcon(imgTelegram);
            }
        });

    }
    public void aggiuntaTxtFieldEmail(){

        JTextField txtEmail = new JTextField();
        JPanel appoggioEmail = new JPanel();
        appoggioEmail.setLayout(new GridLayout(0,1));
        appoggioEmail.add(txtEmail);
        jpTxtEmail.setLayout(new GridLayout(0,1));
        jpTxtEmail.add(appoggioEmail);

        listaTxtEmail.add(txtEmail);

        jpTxtEmail.repaint();
        jpTxtEmail.validate();
        creaContatto.repaint();
        creaContatto.validate();
    }

    public void aggiuntaTxtFieldIndirizzo(){ //RISOLVERE PIUINDIRIZZI JTXT SPORCO DOPO CONFERMA

        JTextField txtPiuIndirizzi = new JTextField();
        JPanel appoggioIndirizzo = new JPanel();
        appoggioIndirizzo.setLayout(new GridLayout(0,1));
        appoggioIndirizzo.add(txtPiuIndirizzi);
        jpTxtIndirizzo.setLayout(new GridLayout(0,1));
        jpTxtIndirizzo.add(appoggioIndirizzo);

        listaTxtIndirizzo.add(txtPiuIndirizzi);


        creaContatto.repaint();
        creaContatto.validate();
    }


    /////////////////////////////////////////////////////       METODI LOGICI       /////////////////////////////////////////////////////
    public void controlloField(){
        for(int i = 0; i < listaTxtIndirizzo.size(); i++){
            if(listaTxtIndirizzo.get(i).getText().isEmpty())
                listaTxtIndirizzo.remove(i);
        }
    }
    public boolean controlliIndirizzo() {
        String via, civico, cap, citta, nazione;
        int lunghezza = txtIndirizzo.getText().split("\\s*,\\s*").length;
        String[] divisione;
        if (lunghezza == 5) {
            divisione = txtIndirizzo.getText().split("\\s*,\\s*");
            via = divisione[0];
            civico = divisione[1];
            cap = divisione[2];
            citta = divisione[3];
            nazione = divisione[4];
            if ((via.length() < 30 && via.length() > 0) && (civico.length() <= 3 && civico.length() > 0) && (cap.length() == 5) && (citta.length() < 20 && citta.length() > 0) && (nazione.length() < 20 && nazione.length() > 0)) {
                return true;
            }
            else {
                return false;
            }
        }

        return false;
    }


    public void messaggiErroreFALSE(){

        imgErroreFisso.setVisible(false);
        imgErroreCognome.setVisible(false);
        imgErroreNome.setVisible(false);
        imgErroreCellulare.setVisible(false);
        imgErroreIndirizzo.setVisible(false);
        imgErroreEmail.setVisible(false);
        lbMessaggioErrore.setVisible(false);
    }
    public void inserimentoContattoDatabase() throws SQLException {
        //CreaContattoDAO creaContattoDAO = new CreaContattoPostgreSQL();
        ContattoDAO contattoDAO = new ContattoPostgreSQL();
        NumeroCellulareDAO numeroCellulareDAO = new NumeroCellularePostgreSQL();
        NumeroFissoDAO numeroFissoDAO = new NumeroFissoPostgreSQL();
        EmailDAO emailDAO = new EmailPostgreSQL();
        IndirizzoPrincipaleDAO indirizzoPrincipaleDAO = new IndirizzoPrincipalePostgreSQL();
        EmailSecondarioDAO emailSecondarioDAO = new EmailSecondarioPostgreSQL();
        IndirizzoSecondarioDAO indirizzoSecondarioDAO = new IndirizzoSecondarioPostgreSQL();
        PartecipazioneDAO partecipazioneDAO = new PartecipazionePostgreSQL();
        MessagingDAO messagingDAO = new MessagingPostgreSQL();
        contatto = new Contatti();
        contatto.setNome(txtNome.getText());
        contatto.setCognome(txtCognome.getText());
        contatto.setCellulare(txtCellulare.getText());
        contatto.setFisso(txtFisso.getText());
        contatto.setEmail(txtEmail.getText());
        contatto.setIndirizzo(txtIndirizzo.getText());
        contatto.setFoto(btnCaricaImmagine.getActionCommand());
        contatto.setNomeGruppo(Objects.requireNonNull(cbGruppi.getSelectedItem()).toString());

        controlloField();
        if(emailDAO.controlloDuplicatoEmail(contatto.getEmail())){
            lbMessaggioErrore.setVisible(true);
            lbErroreInserimento("Hai inserito un'email duplicata");
        }
        else{
            try {
                int id = contattoDAO.inserimentoContatto(contatto.getNome(), contatto.getCognome(), contatto.getFoto());
                numeroCellulareDAO.inserisciCellulare(id, contatto.getCellulare());
                numeroFissoDAO.inserisciFisso(id, contatto.getFisso());
                int idEmail = emailDAO.inserimentoEmail(id, contatto.getEmail());
                indirizzoPrincipaleDAO.inserisciIndirizzoPrincipale(id, contatto.getIndirizzo());
                emailSecondarioDAO.inserisciEmailSecondarie(id, listaTxtEmail);
                indirizzoSecondarioDAO.inserisciIndirizzoSecondario(id, listaTxtIndirizzo);
                partecipazioneDAO.entraInGruppo(id, contatto.getNomeGruppo());

                messagingDAO.inserimentoAccountMessaging(id, idEmail, whatsApp);
                messagingDAO.inserimentoAccountMessaging(id, idEmail, telegram);
                svuotaCampi();
                lbMessaggioErrore.setVisible(false);
                control.getHomepage().stampaContatti();
                control.switchJPanelInView(control.getHomepage().getPaneBase());
            }
            catch(Exception e){
                lbMessaggioErrore.setText("Hai inserito i dati in maniera errata");
                control.chiudiNotifica(lbMessaggioErrore);
            }
        }
    }

    public void temaScuro() {

        ImageIcon imgErrore = new ImageIcon("Immagini/imgErroreCampo.png");
        Image image = imgErrore.getImage();
        Image imgScalata = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        imgErrore = new ImageIcon(imgScalata);

        try{
            imgErroreNome.setIcon(imgErrore);
            imgErroreCognome.setIcon(imgErrore);
            imgErroreFisso.setIcon(imgErrore);
            imgErroreCellulare.setIcon(imgErrore);
            imgErroreEmail.setIcon(imgErrore);
            imgErroreIndirizzo.setIcon(imgErrore);
        }catch(Exception e){
            lbMessaggioErrore.setText("Una delle immagini imgErrore in CreaContatto non funziona");
            control.chiudiNotifica(lbMessaggioErrore);
        }

        textureTasti();
    }

    public void temaChiaro(){   //TESTING
        FlatLightLaf.setup();
        SwingUtilities.updateComponentTreeUI(creaContatto);
    }

    //GETTER SETTER

    public JLabel getLbErroreInserimento() {
        return lbMessaggioErrore;
    }

    public void setLbErroreInserimento(JLabel lbErroreInserimento) {
        this.lbMessaggioErrore = lbErroreInserimento;
    }

    public static ArrayList<Contatti> getInsContattiCopia() {
        return insContattiCopia;
    }

    public static void setInsContattiCopia(ArrayList<Contatti> insContattiCopia) {
        CreaContatto.insContattiCopia = insContattiCopia;
    }

    public JButton getBtnSvuotaCampi() {
        return btnSvuotaCampi;
    }

    public void setBtnSvuotaCampi(JButton btnSvuotaCampi) {
        svuotaCampi();
    }

    public JButton getBtnConferma() {
        return btnConferma;
    }

    public void setBtnConferma(JButton btnConferma) throws SQLException {
        inserimentoContattoDatabase();
    }


    public JComboBox getCbWhatsapp() {
        return cbWhatsapp;
    }

    public void setCbWhatsapp(JComboBox cbWhatsapp) {
        this.cbWhatsapp = cbWhatsapp;
    }

    public JComboBox getCbTelegram() {
        return cbTelegram;
    }

    public void setCbTelegram(JComboBox cbTelegram) {
        this.cbTelegram = cbTelegram;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(JTextField txtNome) {
        this.txtNome = txtNome;
    }

    public JTextField getTxtCognome() {
        return txtCognome;
    }

    public void setTxtCognome(JTextField txtCognome) {
        this.txtCognome = txtCognome;
    }

    public JLabel getLbNome() {
        return lbNome;
    }

    public void setLbNome(JLabel lbNome) {
        this.lbNome = lbNome;
    }

    public JLabel getLbIndirizzo() {
        return lbIndirizzo;
    }

    public void setLbIndirizzo(JLabel lbIndirizzo) {
        this.lbIndirizzo = lbIndirizzo;
    }

    public JLabel getLbCognome() {
        return lbCognome;
    }

    public void setLbCognome(JLabel lbCognome) {
        this.lbCognome = lbCognome;
    }

    public JLabel getLbEmail() {
        return lbEmail;
    }

    public void setLbEmail(JLabel lbEmail) {
        this.lbEmail = lbEmail;
    }

    public JLabel getLbCellulare() {
        return lbCellulare;
    }

    public void setLbCellulare(JLabel lbCellulare) {
        this.lbCellulare = lbCellulare;
    }

    public JLabel getLbFisso() {
        return lbFisso;
    }

    public void setLbFisso(JLabel lbFisso) {
        this.lbFisso = lbFisso;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(JTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public JTextField getTxtIndirizzo() {
        return txtIndirizzo;
    }

    public void setTxtIndirizzo(JTextField txtIndirizzo) {
        this.txtIndirizzo = txtIndirizzo;
    }

    public JTextField getTxtCellulare() {
        return txtCellulare;
    }

    public void setTxtCellulare(JTextField txtCellulare) {
        this.txtCellulare = txtCellulare;
    }

    public JTextField getTxtFisso() {
        return txtFisso;
    }

    public void setTxtFisso(JTextField txtFisso) {
        this.txtFisso = txtFisso;
    }

    public JPanel getCreaContatto() {
        return creaContatto;
    }

    public void setCreaContatto(JPanel creaContatto) {
        this.creaContatto = creaContatto;
    }

    public static ArrayList<Contatti> getInsContatti() {
        return insContatti;
    }

    public static void setInsContatti(ArrayList<Contatti> insContatti) {
        CreaContatto.insContatti = insContatti;
    }
    
}
