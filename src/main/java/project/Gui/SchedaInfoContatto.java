package project.Gui;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import project.Classi.Contatti;
import project.Classi.Messaging;
import project.Controller.*;
import project.DAO.AggiornamentoContattoDAO;
import project.DAO.ContattoDAO;
import project.DAO.MessagingDAO;
import project.DAO.PartecipazioneDAO;
import project.ImplementazioniDAO.AggiornamentoContattoPostgreSQL;
import project.ImplementazioniDAO.ContattoPostgreSQL;
import project.ImplementazioniDAO.MessagingPostgreSQL;
import project.ImplementazioniDAO.PartecipazionePostgreSQL;

import org.postgresql.util.PSQLException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class SchedaInfoContatto {

    /////////////////////////////////////////////////////       ATTRIBUTI       /////////////////////////////////////////////////////
    private JPanel schedaInfoContattoPane;
    private JPanel jpPiuEmail;
    private JPanel jpPiuIndirizzo;

    private JTextField txtNome;
    private JTextField txtCognome;
    private JTextField txtCellulare;
    private JTextField txtFisso;
    private JTextField txtEmail;
    private JTextField txtIndirizzo;

    private JLabel lbNome;
    private JLabel lbCognome;
    private JLabel lbCellulare;
    private JLabel lbFisso;
    private JLabel lbEmail;
    private JLabel lbIndirizo;
    private JLabel lbGruppo;
    private JLabel lbWhatsApp;
    private JLabel lbRispostaWA;
    private JLabel lbTelegram;
    private JLabel lbRispostaTG;
    private JLabel lbTastoHome;
    private JLabel lbMessaggioErrore;


    private JButton btnAggiornaContatto;
    private JButton btnEliminaContatto;
    private JComboBox cbGruppo;
    private JButton btnImmagineCaricata;
    private JButton btnEntraGruppo;
    private JButton btnEsciGruppo;
    private JButton btnWhatsApp;
    private JButton btnTelegram;
    private JButton btnChiamataCellulare;
    private JButton btnChiamataFisso;

    /////////////////////////////////////////////////////       OGGETTI     /////////////////////////////////////////////////////
    Controller control;
    Messaging messaging = new Messaging();
    Contatti contatto = new Contatti();


    ContattoDAO contattoDAO = new ContattoPostgreSQL();
    PartecipazioneDAO partecipazioneDAO = new PartecipazionePostgreSQL();
    MessagingDAO messagingDAO = new MessagingPostgreSQL();

    ArrayList<String> indirizzoSecondario = new ArrayList<>();
    ArrayList<String> emailSecondario = new ArrayList<>();
    ArrayList<String> gruppi = new ArrayList<>();
    ArrayList<String> listaTxtEmail = new ArrayList<>();
    ArrayList<String> listaTxtIndirizzo = new ArrayList<>();
    JTextField[] arrayEmail;
    JTextField[] arrayIndirizzo;
    String nCellulare;

    /////////////////////////////////////////////////////       COSTRUTTORE     /////////////////////////////////////////////////////
    public SchedaInfoContatto(Controller controller) throws SQLException {
        control = controller;
        funzionalitaTasti();

    }

    /////////////////////////////////////////////////////       FUNZIONALITA' PULSANTI GUI      /////////////////////////////////////////////////////
    public void funzionalitaTasti() throws SQLException {


        //FUNZIONALITA' TASTO btnEliminaContatto
        btnEliminaContatto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    control.clickAudio();
                    contattoDAO.eliminaContatto(contatto.getId());
                    control.getHomepage().stampaContatti();
                } catch (SQLException | UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
                control.setJScrollPaneNorth();
                control.switchJPanelInView(control.getHomepage().getPaneBase());
            }
        });

        //FUNZIONALITA' TASTO btnAggiornaContatto
        btnAggiornaContatto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    control.clickAudio();
                    aggiornamentoContatto(arrayEmail, arrayIndirizzo);
                } catch (SQLException | UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //FUNZIONALITA' TASTO lbTastoHome
        ImageIcon imgTastoHome = new ImageIcon("Immagini/imgRitornoHomeFreccia24px.png");
        ImageIcon imgTastoHomeGrande = new ImageIcon("Immagini/imgRitornoHomeFreccia32px.png");
        lbTastoHome.setIcon(imgTastoHome);
        lbTastoHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    control.setJScrollPaneNorth();
                    control.clickAudio();
                    lbTastoHome.setIcon(imgTastoHome);
                    control.switchJPanelInView(control.getHomepage().getPaneBase());
                    control.getHomepage().stampaContatti();
                    control.getHomepage().getJScrollBarListaContatti().setBorder(BorderFactory.createTitledBorder("Lista dei Contatti"));
                } catch (SQLException | LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                try {
                    control.rollOverAudio();
                    lbTastoHome.setIcon(imgTastoHomeGrande);
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbTastoHome.setIcon(imgTastoHome);
            }
        });

        //FUNZIONALITA' TASTO btnImmagineCaricata
        btnImmagineCaricata.setMargin(new Insets(0, 0, 0, 0));
        btnImmagineCaricata.setContentAreaFilled(false);
        btnImmagineCaricata.setBorderPainted(false);
        btnImmagineCaricata.setBorder(null);
        btnImmagineCaricata.setFocusPainted(false);
        btnImmagineCaricata.setOpaque(true);

        btnImmagineCaricata.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser("D:\\GitHub\\Java\\Rubrica - ProgettoOO_BD\\Immagini\\ImmaginiContatto");
                int valoreRitorno = fileChooser.showOpenDialog(null);
                if (valoreRitorno == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String percorsoAssoluto = selectedFile.getAbsolutePath();
                    ImageIcon immagineProfilo = new ImageIcon(percorsoAssoluto);
                    try {
                        btnImmagineCaricata.setIcon(immagineProfilo);
                        btnImmagineCaricata.setActionCommand(percorsoAssoluto);
                    } catch (Exception b) {
                        lbMessaggioErrore.setText("impossibile caricare l'immagine dal disco");
                        control.chiudiNotifica(lbMessaggioErrore);
                    }
                }
            }
        });

        //btnEntraGruppo
        ImageIcon imgEntraGruppo = new ImageIcon("Immagini/imgEntrataGruppo16pxScuro.png");
        ImageIcon imgEntraGruppoGrande = new ImageIcon("Immagini/imgEntrataGruppo24pxScuro.png");

        btnEntraGruppo.setIcon(imgEntraGruppo);
        btnEntraGruppo.setMargin(new Insets(0, 0, 0, 0));
        btnEntraGruppo.setContentAreaFilled(false);
        btnEntraGruppo.setBorderPainted(false);
        btnEntraGruppo.setBorder(null);
        btnEntraGruppo.setFocusPainted(false);
        btnEntraGruppo.setOpaque(true);

        btnEntraGruppo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    control.clickAudio();
                    partecipazioneDAO.entraInGruppo(contatto.getId(), Objects.requireNonNull(cbGruppo.getSelectedItem()).toString());
                    aggiornaGruppi();

                } catch (Exception ex) {
                    lbMessaggioErrore.setText("La persona è gia presente nel gruppo");
                    control.chiudiNotifica(lbMessaggioErrore);
                }
            }

            public void mouseEntered(MouseEvent e) {
                btnEntraGruppo.setIcon(imgEntraGruppoGrande);
                try {
                    control.rollOverAudio();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            public void mouseExited(MouseEvent e) {
                btnEntraGruppo.setIcon(imgEntraGruppo);
            }
        });

        //btnEsciGruppo
        PartecipazioneDAO partecipazioneDAO = new PartecipazionePostgreSQL();
        ImageIcon imgEsciGruppo = new ImageIcon("Immagini/imgEsciGruppo16pxScuro.png");
        ImageIcon imgEsciGruppoGrande = new ImageIcon("Immagini/imgEsciGruppo24pxScuro.png");

        btnEsciGruppo.setIcon(imgEsciGruppo);
        btnEsciGruppo.setMargin(new Insets(0, 0, 0, 0));
        btnEsciGruppo.setContentAreaFilled(false);
        btnEsciGruppo.setBorderPainted(false);
        btnEsciGruppo.setBorder(null);
        btnEsciGruppo.setFocusPainted(false);
        btnEsciGruppo.setOpaque(true);

        btnEsciGruppo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    control.clickAudio();

                    partecipazioneDAO.esciDalGruppo(contatto.getId(), Objects.requireNonNull(cbGruppo.getSelectedItem()).toString());

                    aggiornaGruppi();

                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException | SQLException ex) {
                    ex.printStackTrace();
                }
            }

            public void mouseEntered(MouseEvent e) {
                btnEsciGruppo.setIcon(imgEsciGruppoGrande);
                try {
                    control.rollOverAudio();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            public void mouseExited(MouseEvent e) {
                btnEsciGruppo.setIcon(imgEsciGruppo);
            }
        });

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
                btnWhatsApp.setActionCommand("WhatsApp");
                try {
                    control.clickAudio();
                    messaging = messagingDAO.ricercaProvider(contatto.getId(), btnWhatsApp.getActionCommand());
                    control.PopupProviderOttenimentoInfo(messaging);
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

        btnTelegram.setContentAreaFilled(false);
        btnTelegram.setBorderPainted(false);
        btnTelegram.setBorder(null);
        btnTelegram.setFocusPainted(false);
        btnTelegram.setOpaque(true);
        btnTelegram.setIcon(imgTelegram);
        btnTelegram.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnTelegram.setActionCommand("Telegram");
                try {
                    control.clickAudio();
                    messaging = messagingDAO.ricercaProvider(contatto.getId(), btnTelegram.getActionCommand());
                    control.PopupProviderOttenimentoInfo(messaging);

                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException | SQLException ex) {
                    lbMessaggioErrore.setText("Il profilo telegram è vuoto");
                    control.chiudiNotifica(lbMessaggioErrore);
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

        //btnChiamataCellulare
        ImageIcon imgChiamataCellulare = new ImageIcon("Immagini/imgChiamataMobile16pxScuro.png");
        ImageIcon imgChiamataCellulareGrande = new ImageIcon("Immagini/imgChiamataMobile24pxScuro.png");

        btnChiamataCellulare.setContentAreaFilled(false);
        btnChiamataCellulare.setBorderPainted(false);
        btnChiamataCellulare.setBorder(null);
        btnChiamataCellulare.setFocusPainted(false);
        btnChiamataCellulare.setOpaque(true);
        btnChiamataCellulare.setIcon(imgChiamataCellulare);

        btnChiamataCellulare.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    control.clickAudio();
                    control.popupReindirizzamentoCellulare(contatto.getId());
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            public void mouseEntered(MouseEvent e) {
                try {
                    btnChiamataCellulare.setIcon(imgChiamataCellulareGrande);
                    control.rollOverAudio();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            public void mouseExited(MouseEvent e) {
                btnChiamataCellulare.setIcon(imgChiamataCellulare);
            }
        });

        //btnChiamataFisso
        ImageIcon imgChiamataFisso = new ImageIcon("Immagini/imgChiamataFisso16pxScuro.png");
        ImageIcon imgChiamataFissoGrande = new ImageIcon("Immagini/imgChiamataFisso24pxScuro.png");

        btnChiamataFisso.setContentAreaFilled(false);
        btnChiamataFisso.setBorderPainted(false);
        btnChiamataFisso.setBorder(null);
        btnChiamataFisso.setFocusPainted(false);
        btnChiamataFisso.setOpaque(true);
        btnChiamataFisso.setIcon(imgChiamataFisso);

        btnChiamataFisso.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    control.popupReindirizzamentoFisso(contatto.getId());
                    control.clickAudio();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            public void mouseEntered(MouseEvent e) {
                try {
                    btnChiamataFisso.setIcon(imgChiamataFissoGrande);
                    control.rollOverAudio();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            public void mouseExited(MouseEvent e) {

                btnChiamataFisso.setIcon(imgChiamataFisso);

            }
        });

    }

    /////////////////////////////////////////////////////       METODI LOGICI     /////////////////////////////////////////////////////
    public void riempimentoInfoContatto(int id) throws SQLException {
        int i = 0;
        arrayEmail = null;
        arrayIndirizzo = null;
        indirizzoSecondario.clear();
        emailSecondario.clear();
        gruppi.clear();
        cbGruppo.removeAllItems();
        contatto.setId(id);



        contatto = contattoDAO.cercaInfoContatti(id, indirizzoSecondario, emailSecondario);
        partecipazioneDAO.estraiGruppi(contatto.getId(), gruppi);
        getTxtNome().setText(contatto.getNome());
        getTxtCognome().setText(contatto.getCognome());
        getTxtCellulare().setText(contatto.getCellulare());
        getTxtFisso().setText(contatto.getFisso());
        getTxtIndirizzo().setText(contatto.getIndirizzo());

        while (i < gruppi.size()) {
            cbGruppo.addItem(gruppi.get(i));
            i++;
        }

        cbGruppo.addItem("Gruppi Nuovi");
        gruppi.clear();
        gruppi = partecipazioneDAO.gruppiPartecipabili(contatto.getId(), gruppi);

        i = 0;
        while (i < gruppi.size()) {
            cbGruppo.addItem(gruppi.get(i));
            i++;
        }

        if (contatto.getFoto() != null && !contatto.getFoto().isEmpty()) {
            ImageIcon immagine = new ImageIcon(contatto.getFoto());
            btnImmagineCaricata.setIcon(immagine);
        } else {
            ImageIcon immagine = new ImageIcon("Immagini/imgAggiungiFoto64pxScuro.png");
            btnImmagineCaricata.setIcon(immagine);
        }

        arrayEmail = aggiuntaEmailSecondarie();
        arrayIndirizzo = aggiuntaIndirizziSecondari();

        nCellulare = getTxtCellulare().getText();
    }

    public void aggiornaTxtEmail() {
        for (int i = 0; i < arrayEmail.length; i++)
            listaTxtEmail.add(arrayEmail[i].getText());

        for (int i = 0; i < arrayIndirizzo.length; i++)
            listaTxtIndirizzo.add(arrayIndirizzo[i].getText());
    }

    public void aggiornaGruppi() throws SQLException {
        gruppi.clear();
        cbGruppo.removeAllItems();
        partecipazioneDAO.estraiGruppi(contatto.getId(), gruppi);
        int i = 0;
        while (i < gruppi.size()) {
            cbGruppo.addItem(gruppi.get(i));
            i++;
        }

        cbGruppo.addItem("Gruppi Nuovi");
        gruppi.clear();
        gruppi = partecipazioneDAO.gruppiPartecipabili(contatto.getId(), gruppi);

        i = 0;
        while (i < gruppi.size()) {
            cbGruppo.addItem(gruppi.get(i));
            i++;
        }
    }

    //////////////////////////////////////////////////////// COMPLETARE IL METODO DI SOTTO ///////////////////////////////////////////////////////////////////////////////////
    public void aggiornamentoContatto(JTextField[] arrayTxtEmail, JTextField[] arrayTxtIndirizzo) throws SQLException, IOException {
        AggiornamentoContattoDAO aggiornaContatto = new AggiornamentoContattoPostgreSQL();
        Contatti contatto_new = new Contatti();
        try {
            contatto_new.setNome(getTxtNome().getText());
            contatto_new.setCognome(getTxtCognome().getText());
            contatto_new.setCellulare(getTxtCellulare().getText());
            contatto_new.setFisso(getTxtFisso().getText());
            contatto_new.setIndirizzo(getTxtIndirizzo().getText());
            if (!btnImmagineCaricata.getActionCommand().isEmpty())
                contatto_new.setFoto(btnImmagineCaricata.getActionCommand());
            else
                contatto_new.setFoto(contatto.getFoto());

            aggiornaTxtEmail();
            aggiornaContatto.aggiornaContatto(contatto, contatto_new, indirizzoSecondario, listaTxtIndirizzo, emailSecondario, listaTxtEmail);
        } catch (Exception e) {
            lbMessaggioErrore.setText("Non c'è nulla da aggiornare");
            control.chiudiNotifica(lbMessaggioErrore);
        }
    }

    public JTextField[] aggiuntaEmailSecondarie() {
        JTextField[] arrayEmail = new JTextField[emailSecondario.size()];
        for (int i = 0; i < arrayEmail.length; i++) {
            arrayEmail[i] = new JTextField();
            JPanel jpAppoggioPiuEmail = new JPanel();
            jpAppoggioPiuEmail.setLayout(new GridLayout(0, 1));
            arrayEmail[i].setText(emailSecondario.get(i));
            jpAppoggioPiuEmail.add(arrayEmail[i]);
            jpPiuEmail.setLayout(new GridLayout(0, 1));
            jpPiuEmail.add(jpAppoggioPiuEmail);

            jpPiuEmail.validate();
            jpPiuEmail.repaint();
        }

        return arrayEmail;

    }

    public JTextField[] aggiuntaIndirizziSecondari() {

        JTextField[] arrayIndirizzo = new JTextField[indirizzoSecondario.size()];
        for (int i = 0; i < arrayIndirizzo.length; i++) {
            arrayIndirizzo[i] = new JTextField();
            JPanel jpAppoggioPiuIndirizzo = new JPanel();
            jpAppoggioPiuIndirizzo.setLayout(new GridLayout(0, 1));
            jpAppoggioPiuIndirizzo.add(arrayIndirizzo[i]);
            jpPiuIndirizzo.setLayout(new GridLayout(0, 1));
            jpPiuIndirizzo.add(jpAppoggioPiuIndirizzo);
            arrayIndirizzo[i].setText(indirizzoSecondario.get(i));

            jpPiuIndirizzo.validate();
            jpPiuIndirizzo.repaint();

        }
        return arrayIndirizzo;
    }

    /////////////////////////////////////////////////////       GETTER SETTER       /////////////////////////////////////////////////////

    public JButton getBtnChiamataCellulare() {
        return btnChiamataCellulare;
    }

    public void setBtnChiamataCellulare(JButton btnChiamataCellulare) {
        this.btnChiamataCellulare = btnChiamataCellulare;
    }

    public JButton getBtnChiamataFisso() {
        return btnChiamataFisso;
    }

    public void setBtnChiamataFisso(JButton btnChiamataFisso) {
        this.btnChiamataFisso = btnChiamataFisso;
    }

    public JLabel getLbMessaggioErrore() {
        return lbMessaggioErrore;
    }

    public void setLbMessaggioErrore(JLabel lbMessaggioErrore) {
        this.lbMessaggioErrore = lbMessaggioErrore;
    }

    public JPanel getJpPiuEmail() {
        return jpPiuEmail;
    }

    public JButton getBtnImmagineCaricata() {
        return btnImmagineCaricata;
    }

    public void setBtnImmagineCaricata(JButton btnImmagineCaricata) {
        this.btnImmagineCaricata = btnImmagineCaricata;
    }

    public JButton getBtnEntraGruppo() {
        return btnEntraGruppo;
    }

    public void setBtnEntraGruppo(JButton btnEntraGruppo) {
        this.btnEntraGruppo = btnEntraGruppo;
    }

    public JButton getBtnEsciGruppo() {
        return btnEsciGruppo;
    }

    public void setBtnEsciGruppo(JButton btnEsciGruppo) {
        this.btnEsciGruppo = btnEsciGruppo;
    }

    public JButton getBtnWhatsApp() {
        return btnWhatsApp;
    }

    public void setBtnWhatsApp(JButton btnWhatsApp) {
        this.btnWhatsApp = btnWhatsApp;
    }

    public JButton getBtnTelegram() {
        return btnTelegram;
    }

    public void setBtnTelegram(JButton btnTelegram) {
        this.btnTelegram = btnTelegram;
    }


    public void setJpPiuEmail(JPanel jpPiuEmail) {
        this.jpPiuEmail = jpPiuEmail;
    }

    public JPanel getJpPiuIndirizzo() {
        return jpPiuIndirizzo;
    }

    public void setJpPiuIndirizzo(JPanel jpPiuIndirizzo) {
        this.jpPiuIndirizzo = jpPiuIndirizzo;
    }

    public JLabel getLbTastoHome() {
        return lbTastoHome;
    }

    public void setLbTastoHome(JLabel lbTastoHome) {
        this.lbTastoHome = lbTastoHome;
    }

    public JButton getBtnAggiornaContatto() {
        return btnAggiornaContatto;
    }

    public void setBtnAggiornaContatto(JButton btnAggiornaContatto) {
        this.btnAggiornaContatto = btnAggiornaContatto;
    }

    public JButton getBtnEliminaContatto() {
        return btnEliminaContatto;
    }

    public void setBtnEliminaContatto(JButton btnEliminaContatto) {
        this.btnEliminaContatto = btnEliminaContatto;
    }

    public JPanel getSchedaInfoContattoPane() {
        return schedaInfoContattoPane;
    }

    public void setSchedaInfoContattoPane(JPanel schedaInfoContattoPane) {
        this.schedaInfoContattoPane = schedaInfoContattoPane;
    }

    public JComboBox getCbGruppo() {
        return cbGruppo;
    }

    public void setCbGruppo(JComboBox cbGruppo) {
        this.cbGruppo = cbGruppo;
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

    public JLabel getLbNome() {
        return lbNome;
    }

    public void setLbNome(JLabel lbNome) {
        this.lbNome = lbNome;
    }

    public JLabel getLbCognome() {
        return lbCognome;
    }

    public void setLbCognome(JLabel lbCognome) {
        this.lbCognome = lbCognome;
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

    public JLabel getLbEmail() {
        return lbEmail;
    }

    public void setLbEmail(JLabel lbEmail) {
        this.lbEmail = lbEmail;
    }

    public JLabel getLbIndirizo() {
        return lbIndirizo;
    }

    public void setLbIndirizo(JLabel lbIndirizo) {
        this.lbIndirizo = lbIndirizo;
    }

    public JLabel getLbGruppo() {
        return lbGruppo;
    }

    public void setLbGruppo(JLabel lbGruppo) {
        this.lbGruppo = lbGruppo;
    }

    public JLabel getLbWhatsApp() {
        return lbWhatsApp;
    }

    public void setLbWhatsApp(JLabel lbWhatsApp) {
        this.lbWhatsApp = lbWhatsApp;
    }

    public JLabel getLbRispostaWA() {
        return lbRispostaWA;
    }

    public void setLbRispostaWA(JLabel lbRispostaWA) {
        this.lbRispostaWA = lbRispostaWA;
    }

    public JLabel getLbTelegram() {
        return lbTelegram;
    }

    public void setLbTelegram(JLabel lbTelegram) {
        this.lbTelegram = lbTelegram;
    }

    public JLabel getLbRispostaTG() {
        return lbRispostaTG;
    }

    public void setLbRispostaTG(JLabel lbRispostaTG) {
        this.lbRispostaTG = lbRispostaTG;
    }


}
