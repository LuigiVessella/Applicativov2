package project.Gui;

import project.Classi.Messaging;
import project.Controller.Controller;
import project.DAO.MessagingDAO;
import project.ImplementazioniDAO.MessagingPostgreSQL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class MessagingGUI extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    private JTextArea taFraseBenvenuto;
    private JTextField txtNickname;
    private JComboBox cbEmail;
    private JLabel lbEmail;
    private JLabel lbBenvenuto;
    private JLabel lbNickname;

    Controller control;
    Messaging messaging = new Messaging();
    MessagingDAO messagingDAO = new MessagingPostgreSQL();

    public MessagingGUI(Controller controller) throws SQLException {
        control = controller;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        textureJDialog();
    }

    private void onOK() {
        // add your code here

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void textureJDialog() {


        setSize(400,200);
        setLocationRelativeTo(control.getHomepage());

    }

    public void ottenimentoInfo(Messaging messaging) throws  IOException {

        txtNickname.setText(messaging.getNickname());
        cbEmail.addItem(messaging.getEmail());
        taFraseBenvenuto.setText(messaging.getMessaggioBenvenuto());
        try {
            if (messaging.getProviderMessaggi().equals("WhatsApp")) {
                BufferedImage whatsapp = ImageIO.read(new File("Immagini/imgWA16px.png"));
                Border border = BorderFactory.createTitledBorder("WhatsApp");
                contentPane.setBorder(border);
                setIconImage(whatsapp);
            } else {
                BufferedImage imgTelegram = ImageIO.read(new File("Immagini/imgTelegram16px.png"));
                Border border = BorderFactory.createTitledBorder("Telegram");
                contentPane.setBorder(border);
                setIconImage(imgTelegram);
            }
            taFraseBenvenuto.setEditable(false);
            txtNickname.setEditable(false);
            setVisible(true);
        }
        catch(Exception e){
            control.getSchedaInfoContatto().getLbMessaggioErrore().setText("Non esistono account messaging per l'utente");
            control.chiudiNotifica(control.getSchedaInfoContatto().getLbMessaggioErrore());
        }

    }

    public Messaging inserimentoInfo(Messaging messaging) throws IOException {
        lbEmail.setVisible(false);
        cbEmail.setVisible(false);

        if(messaging.getProviderMessaggi().equals("WhatsApp")){
            BufferedImage whatsapp = ImageIO.read(new File("Immagini/imgWA16px.png"));
            Border border = BorderFactory.createTitledBorder("WhatsApp");
            contentPane.setBorder(border);
            setIconImage(whatsapp);
        }
        else
        {
            BufferedImage imgTelegram = ImageIO.read(new File("Immagini/imgTelegram16px.png"));
            Border border = BorderFactory.createTitledBorder("Telegram");
            contentPane.setBorder(border);
            setIconImage(imgTelegram);
        }

        setVisible(true);

        messaging.setNickname(txtNickname.getText());
        messaging.setMessaggioBenvenuto(taFraseBenvenuto.getText());

        return messaging;
    }


}
