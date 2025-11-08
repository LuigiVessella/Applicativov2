package project.Gui;

import project.Classi.Contatti;
import project.Controller.Controller;
import project.DAO.RicercaContattoDAO;
import project.ImplementazioniDAO.RicercaContattoPostgreSQL;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ButtonGroup;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class RicercaContatti {
    /////////////////////////////////////////////////////       ATTRIBUTI       /////////////////////////////////////////////////////
    private JPanel ricercaContattiPane;
    private JPanel risultatoPane;
    private JScrollPane risultatoScroll;

    private JTextField txtRicerca;
    private JRadioButton nomeRadioButton;
    private JRadioButton nicknameRadioButton;
    private JRadioButton emailRadioButton;
    private JRadioButton cellulareRadioButton;


    private JButton btnTastoHome;

    /////////////////////////////////////////////////////       OGGETTI     /////////////////////////////////////////////////////
    Controller control;
    ArrayList<Contatti> contattiInfo = new ArrayList<>();
    RicercaContattoDAO ricerca = new RicercaContattoPostgreSQL();

    /////////////////////////////////////////////////////       COSTRUTTORE     /////////////////////////////////////////////////////
    public RicercaContatti(Controller controller) throws SQLException {
        control = controller;

        funzionalitaTasti();

    }

    /////////////////////////////////////////////////////       FUNZIONALITA' TASTI     /////////////////////////////////////////////////////
    private void funzionalitaTasti() {
        ButtonGroup gruppoRadio = new ButtonGroup( );
        gruppoRadio.add(nomeRadioButton);
        gruppoRadio.add(nicknameRadioButton);
        gruppoRadio.add(emailRadioButton);
        gruppoRadio.add(cellulareRadioButton);

        txtRicerca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                try {
                    if(gruppoRadio.isSelected(nomeRadioButton.getModel()) && !txtRicerca.getText().isEmpty()){
                        contattiInfo.clear();
                        contattiInfo = ricerca.ricercaNome(txtRicerca.getText());
                        ricercaCampi(contattiInfo);
                    }
                    if(gruppoRadio.isSelected(emailRadioButton.getModel()) && !txtRicerca.getText().isEmpty()){
                        contattiInfo.clear();
                        contattiInfo = ricerca.ricercaEmail(txtRicerca.getText());
                        ricercaCampi(contattiInfo);
                    }
                    if(gruppoRadio.isSelected(nicknameRadioButton.getModel()) && !txtRicerca.getText().isEmpty()){
                        contattiInfo.clear();
                        contattiInfo = ricerca.ricercaNickname(txtRicerca.getText());
                        ricercaCampi(contattiInfo);
                    }
                    if(gruppoRadio.isSelected(cellulareRadioButton.getModel()) && !txtRicerca.getText().isEmpty()){
                        contattiInfo.clear();
                        contattiInfo = ricerca.ricercaCellulare(txtRicerca.getText());
                        ricercaCampi(contattiInfo);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        //FUNZIONALITA' TASTO btnTastoHome
        ImageIcon imgTastoHome = new ImageIcon("Immagini/imgRitornoHomeFreccia24px.png");
        ImageIcon imgTastoHomeGrande = new ImageIcon("Immagini/imgRitornoHomeFreccia32px.png");

        btnTastoHome.setIcon(imgTastoHome);
        btnTastoHome.setMargin(new Insets(0,0,0,0));
        btnTastoHome.setContentAreaFilled(false);
        btnTastoHome.setBorderPainted(false);
        btnTastoHome.setBorder(null);
        btnTastoHome.setFocusPainted(false);
        btnTastoHome.setOpaque(true);

        btnTastoHome.setIcon(imgTastoHome);
        btnTastoHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    control.setJScrollPaneNorth();
                    control.getHomepage().getJScrollBarListaContatti().setBorder(BorderFactory.createTitledBorder("Lista dei Contatti"));
                    control.clickAudio();
                    control.switchJPanelInView(control.getHomepage().getPaneBase());
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            public void mouseEntered(MouseEvent e) {
                try {
                    control.rollOverAudio();
                    btnTastoHome.setIcon(imgTastoHomeGrande);
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            public void mouseExited(MouseEvent e) {
                btnTastoHome.setIcon(imgTastoHome);
            }
        });
    }


    /////////////////////////////////////////////////////       METODI LOGICI     /////////////////////////////////////////////////////
    public void ricercaCampi(ArrayList<Contatti> contattiInfo) throws SQLException {
        risultatoPane.removeAll();
        risultatoPane.setLayout(new GridLayout(0,1));
        int i = 0;
        risultatoPane.removeAll();

        for(i = 0; i < contattiInfo.size(); i++)
            for(int j = 0; j < contattiInfo.size() && i != j; j++){
                if((contattiInfo.get(i).getNome().equals(contattiInfo.get(j).getNome()) && (contattiInfo.get(i).getCognome().equals(contattiInfo.get(j).getCognome()))))
                    contattiInfo.remove(i);
            }




        i = 0;

        while(i < contattiInfo.size()){
            //JPanel paneLista = new JPanel();
            //paneLista.setLayout(new GridLayout(0, 1));
            JButton btnSchedaContatto = new JButton();
            btnSchedaContatto.setText(contattiInfo.get(i).getNome() +" "+ contattiInfo.get(i).getCognome());//Per il momento mi accontento solo di dargli queste informazioni al JButton
            btnSchedaContatto.setActionCommand(String.valueOf(contattiInfo.get(i).getId()));
            btnSchedaContatto.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        control.clickAudio();
                        control.newSchedaInfoContatto();    //Testing svuotamento dei campi nella scheda contatto
                        control.getSchedaInfoContatto().riempimentoInfoContatto(Integer.parseInt(e.getActionCommand()));
                        control.switchJPanelInView(control.getSchedaInfoContatto().getSchedaInfoContattoPane());
                        control.getHomepage().getJScrollBarListaContatti().setBorder(BorderFactory.createTitledBorder(""));
                        control.newRicercaContatti();
                    } catch (SQLException | UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            //paneLista.add(btnSchedaContatto);
            risultatoPane.add(btnSchedaContatto);
            //risultatoPane.setLayout(new GridLayout(0,1));
            //risultatoPane.add(paneLista);

            risultatoPane.validate();
            i++;
        }

        ricercaContattiPane.repaint();
        ricercaContattiPane.validate();
        risultatoScroll.revalidate();
        ricercaContattiPane.revalidate();
    }

    //GETTER SETTER
    public JTextField getTxtRicerca() {
        return txtRicerca;
    }

    public void setTxtRicerca(JTextField txtRicerca) {
        this.txtRicerca = txtRicerca;
    }

    public JRadioButton getNomeRadioButton() {
        return nomeRadioButton;
    }

    public void setNomeRadioButton(JRadioButton nomeRadioButton) {
        this.nomeRadioButton = nomeRadioButton;
    }

    public JRadioButton getNicknameRadioButton() {
        return nicknameRadioButton;
    }

    public void setNicknameRadioButton(JRadioButton nicknameRadioButton) {
        this.nicknameRadioButton = nicknameRadioButton;
    }

    public JRadioButton getEmailRadioButton() {
        return emailRadioButton;
    }

    public void setEmailRadioButton(JRadioButton emailRadioButton) {
        this.emailRadioButton = emailRadioButton;
    }

    public JRadioButton getCellulareRadioButton() {
        return cellulareRadioButton;
    }

    public void setCellulareRadioButton(JRadioButton cellulareRadioButton) {
        this.cellulareRadioButton = cellulareRadioButton;
    }

    public JPanel getRicercaContattiPane() {
        return ricercaContattiPane;
    }

    public void setRicercaContattiPane(JPanel ricercaContattiPane) {
        this.ricercaContattiPane = ricercaContattiPane;
    }
}
