package project.Gui;

import project.Classi.*;
import project.Controller.*;
import project.DAO.ContattoDAO;
import project.ImplementazioniDAO.ContattoPostgreSQL;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Homepage extends JFrame {

    /////////////////////////////////////////////////////       ATTRIBUTI       /////////////////////////////////////////////////////
    private JPanel cardHomepage;
    private JPanel homepage;
    private JPanel paneBase;
    private JPanel paneTendina;
    private JScrollPane JScrollBarListaContatti;

    private JButton btnCreaNuovoContatto;
    private JButton btnSwitchTema;
    private JButton btnRicercaContatto;
    private JPanel borderLayoutBase;
    private JLabel lbMessaggioErrore;

    private Timer timer;

    /////////////////////////////////////////////////////       OGGETTI     /////////////////////////////////////////////////////
    Controller control;

    static ArrayList<Contatti> contattiDB = new ArrayList<>();

    /////////////////////////////////////////////////////       COSTRUTTORE     /////////////////////////////////////////////////////
    public Homepage(Controller controller) throws SQLException, IOException {

        control = controller;
        impostazioniGeneraliHomepage();
        stampaContatti();
        funzionalitaTasti();

    }


    /////////////////////////////////////////////////////       FUNZIONALITA' TASTI     /////////////////////////////////////////////////////

    public void funzionalitaTasti(){
        //FUNZIONALITA TASTO btnCreaNuovoContatto
        ImageIcon imgCreaNuovoContatto = new ImageIcon("Immagini/imgCreaNuovoContatto24px.png");
        ImageIcon imgCreaNuovoContattoGrande = new ImageIcon("Immagini/imgCreaNuovoContatto32px.png");
        try {
            btnCreaNuovoContatto.setIcon(imgCreaNuovoContatto);
            btnCreaNuovoContatto.setMargin(new Insets(0,0,0,0));
            btnCreaNuovoContatto.setContentAreaFilled(false);
            btnCreaNuovoContatto.setBorderPainted(false);
            btnCreaNuovoContatto.setBorder(null);
            btnCreaNuovoContatto.setFocusPainted(false);
            btnCreaNuovoContatto.setOpaque(true);
        } catch (Exception e) {
            lbMessaggioErrore.setText("Icona btnCreaNuovoContattoSCURO non caricata");
            control.chiudiNotifica(lbMessaggioErrore);
        }
        btnCreaNuovoContatto.setFocusable(false);
        btnCreaNuovoContatto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    control.setJScrollPaneCenter();
                    control.clickAudio();
                    control.switchJPanelInView(control.getCreaContatto().getCreaContatto());
                    JScrollBarListaContatti.setBorder(BorderFactory.createTitledBorder(""));
                    control.newCreaContatto();
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCreaNuovoContatto.setIcon(imgCreaNuovoContattoGrande);
                try {
                    control.rollOverAudio();
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnCreaNuovoContatto.setIcon(imgCreaNuovoContatto);
            }
        });

        //FUNZIONALITA TASTO btnRicercaContatto
        ImageIcon imgRicercaContatto = new ImageIcon("Immagini/imgRicercaContatto24px.png");
        ImageIcon imgRicercaContattoGrande = new ImageIcon("Immagini/imgRicercaContatto32px.png");

        btnRicercaContatto.setIcon(imgRicercaContatto);
        btnRicercaContatto.setMargin(new Insets(0,0,0,0));
        btnRicercaContatto.setContentAreaFilled(false);
        btnRicercaContatto.setBorderPainted(false);
        btnRicercaContatto.setBorder(null);
        btnRicercaContatto.setFocusPainted(false);
        btnRicercaContatto.setOpaque(true);
        btnRicercaContatto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnRicercaContatto.setIcon(imgRicercaContattoGrande);
                try {
                    control.rollOverAudio();
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            @Override
            public void mouseClicked(MouseEvent e){
                try {
                    control.setJScrollPaneCenter();
                    control.clickAudio();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }

                control.switchJPanelInView(control.getRicercaContatti().getRicercaContattiPane());
                JScrollBarListaContatti.setBorder(BorderFactory.createTitledBorder(""));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnRicercaContatto.setIcon(imgRicercaContatto);
            }
        });

        //FUNZIONALITA TASTO btnSwitchTema
        btnSwitchTema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    timerSwitchTema();
                } catch (IOException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /////////////////////////////////////////////////////       METODI LOGICI     /////////////////////////////////////////////////////

    public void stampaContatti() throws SQLException {//Da spostare nel controller
        contattiDB.clear();
        ContattoDAO contattoDAO = new ContattoPostgreSQL();
        contattiDB = contattoDAO.stampaContatti();
        int i = 0;
        paneBase.removeAll();
        while(i < contattiDB.size()){
            JPanel paneLista = new JPanel();
            paneLista.setLayout(new GridLayout(0, 1));
            JButton btnSchedaContatto = new JButton();
            btnSchedaContatto.setText(contattiDB.get(i).getNome() +" "+ contattiDB.get(i).getCognome());//Per il momento mi accontento solo di dargli queste informazioni al JButton
            btnSchedaContatto.setActionCommand(String.valueOf(contattiDB.get(i).getId()));
            btnSchedaContatto.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        control.clickAudio();
                        control.newSchedaInfoContatto();    //Testing svuotamento dei campi nella scheda contatto
                        borderLayoutBase.remove(JScrollBarListaContatti);
                        borderLayoutBase.add(JScrollBarListaContatti, BorderLayout.CENTER);
                        control.setJScrollPaneCenter();
                        control.getSchedaInfoContatto().riempimentoInfoContatto(Integer.parseInt(e.getActionCommand()));
                        control.switchJPanelInView(control.getSchedaInfoContatto().getSchedaInfoContattoPane());
                        JScrollBarListaContatti.setBorder(BorderFactory.createTitledBorder(""));
                    } catch (SQLException | UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            paneLista.add(btnSchedaContatto);
            paneBase.setLayout(new GridLayout(0,1));
            paneBase.add(paneLista);

            validate();
            i++;
        }
        paneBase.repaint();
        paneBase.validate();

    }

    public void impostazioniGeneraliHomepage() throws IOException {
        cardHomepage = new JPanel();
        cardHomepage.setLayout(new CardLayout());
        cardHomepage.add(homepage);

        ImageIcon icon = new ImageIcon("Immagini/imgIcona.png");
        setIconImage(icon.getImage());
        setTitle("Homepage");
        setContentPane(cardHomepage);
        setSize(600, 600);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(300, 300);
        JScrollBarListaContatti.getVerticalScrollBar().setUnitIncrement(7);

        TemaScuro();

    }

    public void timerSwitchTema() throws IOException, SQLException {
        if (timer == null) {
            timer = new Timer(100, new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                }
            });
            timer.start();
            TemaChiaro();
            control.getCreaContatto().temaChiaro();
        } else if (timer.isRunning()) {
            timer.stop();
            TemaScuro();
            control.getCreaContatto().temaScuro();

        } else {
            timer.start();
            TemaChiaro();
            control.getCreaContatto().temaChiaro();

        }
    }

    //TEMA SCURO SET BY DEFAULT
    public void TemaScuro() throws IOException {
        FlatDarculaLaf.setup();
        SwingUtilities.updateComponentTreeUI(homepage);

        //GUI HOMEPAGE
        try {
            Image imgSwitchTema = ImageIO.read(new File("Immagini/imgTemaScuro.png"));
            btnSwitchTema.setIcon(new ImageIcon(imgSwitchTema));
        } catch (Exception e) {
           lbMessaggioErrore.setText("Icona btnTemaScuro non caricata");
            control.chiudiNotifica(lbMessaggioErrore);
        }

        try {
            Image imgCreaNuovoContatto = ImageIO.read(new File("Immagini/imgCreaNuovoContatto24px.png"));
            btnCreaNuovoContatto.setIcon(new ImageIcon(imgCreaNuovoContatto));
        } catch (Exception e) {
            lbMessaggioErrore.setText("Icona btnCreaNuovoContatto non caricata");
            control.chiudiNotifica(lbMessaggioErrore);
        }
        btnSwitchTema.setFocusable(false);


    }


    public void TemaChiaro() throws IOException {

        FlatLightLaf.setup();
        SwingUtilities.updateComponentTreeUI(homepage);     //Serve a fare il refresh dei componenti a runtime (UTILE PER DARK MODE TO LIGHT)


        try {
            Image imgSwitchTema = ImageIO.read(new File("Immagini/imgTemaChiaro.png")); //Da settare la dimensione (PRESI DI MISURA GIUSTA COSI DA NON
            btnSwitchTema.setIcon(new ImageIcon(imgSwitchTema));
        } catch (Exception e) {                                                                                //AVERE NECESSITA' PER IL MOMENTO)
            lbMessaggioErrore.setText("Icona btnTemaChiaroCHIARO non caricata");
            control.chiudiNotifica(lbMessaggioErrore);
        }
        btnSwitchTema.setFocusable(false);

        //Modifica del JButton ********* TESTING **********
        try {
            Image imgCreaNuovoContatto = ImageIO.read(new File("Immagini/imgCreaNuovoContatto24pxCHIARO.png")); //Da settare la dimensione (PRESI DI MISURA GIUSTA COSI DA NON
            btnCreaNuovoContatto.setIcon(new ImageIcon(imgCreaNuovoContatto));
        } catch (Exception e) {                                                                                //AVERE NECESSITA' PER IL MOMENTO)
            lbMessaggioErrore.setText("Icona btnCreaNuovoContattoCHIARO non caricata");
            control.chiudiNotifica(lbMessaggioErrore);
        }
        btnCreaNuovoContatto.setFocusable(false);


    }

    //GETTER SETTER


    public JButton getBtnRicercaContatto() {
        return btnRicercaContatto;
    }

    public void setBtnRicercaContatto(JButton btnRicercaContatto) {
        this.btnRicercaContatto = btnRicercaContatto;
    }

    public JPanel getBorderLayoutBase() {
        return borderLayoutBase;
    }

    public void setBorderLayoutBase(JPanel borderLayoutBase) {
        this.borderLayoutBase = borderLayoutBase;
    }

    public static ArrayList<Contatti> getContattiDB() {
        return contattiDB;
    }

    public static void setContattiDB(ArrayList<Contatti> contattiDB) {
        Homepage.contattiDB = contattiDB;
    }


    public JPanel getPaneTendina() {
        return paneTendina;
    }

    public void setPaneTendina(JPanel paneTendina) {
        this.paneTendina = paneTendina;
    }

    public JScrollPane getJScrollBarListaContatti() {
        return JScrollBarListaContatti;
    }

    public void setJScrollBarListaContatti(JScrollPane JScrollBarListaContatti) {
        this.JScrollBarListaContatti = JScrollBarListaContatti;
    }

    public JPanel getPaneBase() {
        return paneBase;
    }

    public void setPaneBase(JPanel paneBase) {
        this.paneBase = paneBase;
    }

    public JPanel getCardHomepage() {
        return cardHomepage;
    }

    public void setCardHomepage(JPanel cardHomepage) {
        this.cardHomepage = cardHomepage;
    }

    public JButton getBtnCreaNuovoContatto() {
        return btnCreaNuovoContatto;
    }

    public void setBtnCreaNuovoContatto(JButton btnCreaNuovoContatto) {
        this.btnCreaNuovoContatto = btnCreaNuovoContatto;
    }

    public JPanel getHomepage() {
        return homepage;
    }

    public void setHomepage(JPanel homepage) {
        this.homepage = homepage;
    }

    public JButton getBtnSwitchTema() {
        return btnSwitchTema;
    }

    public void setBtnSwitchTema(JButton btnSwitchTema) {
        this.btnSwitchTema = btnSwitchTema;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }


}


