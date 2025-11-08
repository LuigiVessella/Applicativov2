package project.Gui;

import project.Classi.Contatti;
import project.Controller.Controller;
import project.DAO.ContattoDAO;
import project.ImplementazioniDAO.ContattoPostgreSQL;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Reindirizzamento extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lbArrow;
    private JLabel lbNumeroCellulare;
    private JLabel lbNumeroFisso;
    private JLabel nFisso;
    private JLabel nCellulare;

    Controller control;

    public Reindirizzamento(Controller controller) {
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

        funzionalitaGUI();
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void funzionalitaGUI(){
        setSize(400,200);
        setLocationRelativeTo(control.getHomepage());
    }

    public void chiamataCellulare(int id){
        try
        {
            Icon reindirizza = new ImageIcon("Immagini/imgReindirizza.png");
            lbArrow.setIcon(reindirizza);
            ContattoDAO contattoDAO = new ContattoPostgreSQL();
            Contatti contatto = new Contatti();
            contatto = contattoDAO.estraiCellulareFisso(id);


            lbNumeroCellulare.setText(contatto.getCellulare());
            lbNumeroFisso.setText(contatto.getFisso());
        }
        catch(SQLException e){
            e.printStackTrace();
        }


        setVisible(true);
    }

    public void chiamataFisso(int id){
        try
        {
            Icon reindirizza = new ImageIcon("Immagini/imgReindirizza.png");
            lbArrow.setIcon(reindirizza);
            ContattoDAO contattoDAO = new ContattoPostgreSQL();
            Contatti contatto = new Contatti();
            contatto = contattoDAO.estraiCellulareFisso(id);

            nFisso.setText("Numero Cellulare");
            nCellulare.setText("Numero Fisso");
            lbNumeroCellulare.setText(" "+contatto.getFisso());
            lbNumeroFisso.setText(" "+contatto.getCellulare());
        }
        catch(SQLException e){
            e.printStackTrace();
        }


        setVisible(true);
    }
}
