package project.controller;
import project.GUI.SignInS;
import project.GUI.ValutazioneTest;
import project.database_connection.Database_connection;
import project.implementazioneDao.ImpCorsoDao;
import project.implementazioneDao.ImpInsegnanteDao;
import project.implementazioneDao.ImpQuiz_Risp_ApeDao;
import project.implementazioneDao.ImpQuiz_Risp_MulDao;
import project.implementazioneDao.ImpQuiz_svoltiDao;
import project.implementazioneDao.ImpStudenteDao;
import project.implementazioneDao.ImpTestDao;
import project.model.*;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

public class Controller {

	private Studente s;
	private Insegnante i;
	private Corso c;
	private ImpCorsoDao ic;
	private ImpInsegnanteDao ii;
	private ImpStudenteDao is;
	private ImpTestDao it;
	private ImpQuiz_Risp_ApeDao iqra;
	private ImpQuiz_Risp_MulDao iqrm;
	private ImpQuiz_svoltiDao iqs;
	
	public Controller() {
		ic=new ImpCorsoDao();
		ii=new ImpInsegnanteDao();
		is=new ImpStudenteDao();
		it=new ImpTestDao();
		iqra=new ImpQuiz_Risp_ApeDao();
		iqrm=new ImpQuiz_Risp_MulDao();
		iqs=new ImpQuiz_svoltiDao();
	}
	
	
	private Connection connessione;
	private Test t;
	private Quiz_risp_ape qra;

	


        
public String contrLog(String login, String pass) {
	   
	    try {
	        connessione = Database_connection.getInstance().getConnection();
	    } catch (SQLException e) {
	        System.out.println("Connessione fallita.");
	        e.printStackTrace();
	    }
    		
    		PreparedStatement select;
            try {
                select = connessione.prepareStatement("Select password from STUDENTE where login= ('"+login+"');");
                ResultSet rs = select.executeQuery();
                while ( rs.next() ) {
                    pass = rs.getString("password");
                }

            } 
            catch (SQLException e) {

                e.printStackTrace();

            }
			return pass;

   }
   
public String contrLogI(String login, String pass) {
		
	    try {
	        connessione = Database_connection.getInstance().getConnection();
	    } catch (SQLException e) {
	        System.out.println("Connessione fallita.");
	        e.printStackTrace();
	    }
		
		PreparedStatement select;
        try {
            select = connessione.prepareStatement("Select password from insegnante where login= ('"+login+"');");
            ResultSet rs = select.executeQuery();
            while ( rs.next() ) {
                pass = rs.getString("password");
            }

        } 
        catch (SQLException e) {

            e.printStackTrace();

        }
		return pass;

}
	
public List<String> riempiCbox(String login, List<String> cose) {
		
	    try {
	        connessione = Database_connection.getInstance().getConnection();
	    } catch (SQLException e) {
	        System.out.println("Connessione fallita.");
	        e.printStackTrace();
	    }
		
		PreparedStatement select;
        try {
            select = connessione.prepareStatement("SELECT NOME FROM CORSO WHERE ID_INS IN (SELECT ID_INS FROM INSEGNANTE WHERE LOGIN='"+login+"');");
            ResultSet rs = select.executeQuery();
            int i=1;
            while ( rs.next() ) {
            	cose.add(rs.getString("nome"));
            	i++;
            }

        } 
        catch (SQLException e) {

            e.printStackTrace();

        }
		return cose;

}

public List<String> riempiCboxS(String login, List<String> cose) {
	
    try {
        connessione = Database_connection.getInstance().getConnection();
    } catch (SQLException e) {
        System.out.println("Connessione fallita.");
        e.printStackTrace();
    }
		
		PreparedStatement select;
        try {
        	Controller contr= new Controller();
        	String matr=contr.getMatricolaS(login);
            select = connessione.prepareStatement("SELECT NOME FROM CORSO WHERE COD_CORSO IN (SELECT COD_CORSO FROM FREQUENTA WHERE ID_STUD= '"+matr+"');");
            ResultSet rs = select.executeQuery();
            int i=1;
            while ( rs.next() ) {
            	cose.add(rs.getString("nome"));
            }

        } 
        catch (SQLException e) {

            e.printStackTrace();

        }
		return cose;

}
	
public List<String> riempiCBoxTest(String nome, List<String> te) {
    try {
        connessione = Database_connection.getInstance().getConnection();
    } catch (SQLException e) {
        System.out.println("Connessione fallita.");
        e.printStackTrace();
    }
	
	PreparedStatement select;
    try {
        select = connessione.prepareStatement("SELECT nome_id FROM test WHERE cod_corso IN (SELECT cod_corso FROM Corso WHERE nome='"+nome+"');");
        ResultSet rs = select.executeQuery();
        while ( rs.next() ) {
        	String el=rs.getString("nome_id");
        	te.add(rs.getString("nome_id"));
        }
        

    } 
    catch (SQLException e) {

        e.printStackTrace();

    }
	return te;

}

public List<String> togliCBoxText(String login, List<String> tes){
	try {
        connessione = Database_connection.getInstance().getConnection();
    } catch (SQLException e) {
        System.out.println("Connessione fallita.");
        e.printStackTrace();
    }
	
	PreparedStatement select;
    try {
    	Controller contr= new Controller();
    	String matr=contr.getMatricolaS(login);
    	 select=connessione.prepareStatement("SELECT distinct nome_id FROM QUIZ_SVOLTI WHERE id_stud =('"+matr+"');");
         ResultSet rs = select.executeQuery();
        while ( rs.next() ) {
        	String el=rs.getString("nome_id");
        	
        	tes.add(rs.getString("nome_id"));
        }
        

    } 
    catch (SQLException e) {

        e.printStackTrace();

    }
	return tes;

}

public void registraStud (String nome, String cognome, String login, String password, String matricolaStud) {
	s=new Studente(nome, cognome, login, password, matricolaStud);
	is.InserisciStud(s);
}

public void registraInsegnante (String nome, String cognome, String login, String password, String matricolaIns ) {
	i=new Insegnante(nome, cognome, login, password, matricolaIns);
	
	ii.InserisciIns(i);
}

public void registraCorso (String cod_corso, String nome_corso, String matricolains, Date d_ini, Date d_fin ) {
	c=new Corso (cod_corso, nome_corso, matricolains, d_ini,  d_fin );
	
	ic.InserisciCorso(c);
}

public void registraTest (String nome_id, int n_quiz, Date data, Time ora_inizio ,Time ora_fine, String cod_corso, String id_ins) {
	t=new Test (nome_id,  n_quiz,  data,  ora_inizio , ora_fine,  cod_corso,  id_ins);
	
	it.InserisciTest(t);
}

public void registraQuiz_Risp_Ape (String id_quiz, String domanda, int max_lenght, float punt_max, float punt_min, String id_test) {
	qra=new Quiz_risp_ape (id_quiz, domanda, max_lenght, punt_max,  punt_min,  id_test);
	
	iqra.Quiz_Risp_Ape(qra);
}

public void registraQuiz_Risp_Mul (String id_quiz, String domanda, String A, String B, String C, String D, String risp_corr, int punt_c, int punt_e, String id_test) {
	Quiz_risp_mul qrm=new Quiz_risp_mul (id_quiz,  domanda,  A,  B,  C,  D,  risp_corr,  punt_c,  punt_e,  id_test);
	
	iqrm.Quiz_Risp_Multi(qrm);
}

public void riempiTableA (String codTest, DefaultTableModel model) {
	
	Object[] row= new Object [5];
	
    try {
        connessione = Database_connection.getInstance().getConnection();
    } catch (SQLException e) {
        System.out.println("Connessione fallita.");
        e.printStackTrace();
    }
	
	PreparedStatement select;
    try {
    	select = connessione.prepareStatement("SELECT Nome_id, Id_quizA, Id_stud, Risposta_data, Punteggio_quiz_dato FROM QUIZ_SVOLTI WHERE Id_quizM IS NULL AND Nome_id=('"+codTest+"') ORDER BY NOME_ID, ID_STUD;");
        ResultSet rs = select.executeQuery();
        int i=1;
        int a=0;
        while ( rs.next() ) {
        	String nome=rs.getString("Nome_id");
        	String quiz=rs.getString("Id_quizA");
        	String stud=rs.getString("Id_stud");
        	String risp=rs.getString("Risposta_data");
        	String punt=rs.getString("Punteggio_quiz_dato");
        	
        	row[0]=nome;
        	row[1]=quiz;
        	row[2]=stud;
        	row[3]=risp;
        	row[4]=punt;
        	model.addRow(row);
        	
        	i++;
        }

    } 
    catch (SQLException e) {

        e.printStackTrace();

    }

}

public void updateVoti (Object test, Object studente, Object quiz, Float voto) {
	
	
    try {
        connessione = Database_connection.getInstance().getConnection();
    } catch (SQLException e) {
        System.out.println("Connessione fallita.");
        e.printStackTrace();
    }
	
    try {
    	CallableStatement MyStmt = connessione.prepareCall("CALL correzione_risp_ape (?, ?, ?, ?)");
        MyStmt.setObject(1, test);
        MyStmt.setObject(2, studente);
        MyStmt.setObject(3, quiz);
        MyStmt.setFloat(4, voto);
        MyStmt.executeUpdate();
    	
    } 
    catch (SQLException e) {

        e.printStackTrace();

    }


}

public int[] SvolgiTestApe(String id_test, JPanel panel, CardLayout cl, Controller contr, String id_stud) {
    int i=1;
    int t=0;
		
	    try {
	        connessione = Database_connection.getInstance().getConnection();
	    } catch (SQLException e) {
	        System.out.println("Connessione fallita.");
	        e.printStackTrace();
	    }
		
		PreparedStatement select;
        try {
        	
            select = connessione.prepareStatement("Select id_quiz, domanda, lenght_risp, punt_max, punt_min, nome_id from quiz_risp_ape where nome_id= ('"+id_test+"');");
            ResultSet rs = select.executeQuery();
            int n=0;
            while ( rs.next()) {
            	n++;
            }
            
            ResultSet res = select.executeQuery();
            while ( res.next()) {
            	String id_quiz=res.getString("id_quiz");
            	String domanda=res.getString("Domanda");
            	int lunghezza_risp=res.getInt("lenght_risp");
            	float punt_max=res.getFloat("punt_max");
            	float punt_min=res.getFloat("punt_min");
            	String test=res.getString("nome_id");
            	int h=t-1;
            	String id_2= new String ("pan_"+h);
            	JPanel[] panelarr=new JPanel[n];
            	
            	panelarr[t]=new JPanel();
            	panelarr[t].setLayout(null);
            	panelarr[t].setBounds(130, 90, 300, 200);
            	String id= new String("pan_"+t);
            	String id_1= new String("pan_"+i);
            	panel.add(panelarr[t], id);

            	
        		JTextPane textPane = new JTextPane();
        		textPane.setText(domanda);
        		textPane.setEditable(false);
        		textPane.setBounds(22, 11, 245, 49);
        		panelarr[t].add(textPane);
        		
        		
        		JTextPane textPane_1 = new JTextPane();
        		textPane_1.setBounds(22, 99, 245, 31);
        		panelarr[t].add(textPane_1);
        				
        		
            
        		JButton btnNext = new JButton("->");
        		btnNext.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				cl.show(panel, id_1);
        				String entree = textPane_1.getText();
        				float nu= 0;
        				contr.registraQuiz_svoltiA(test, id_stud, id_quiz, entree, nu);
        			}
        		});
        		btnNext.setBounds(155, 150, 111, 23);
        		panelarr[t].add(btnNext);
        		
        		
        		JButton btnBack = new JButton("<-");
        		btnBack.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				cl.show(panel, id_2);
        			}
        		});
        		btnBack.setBounds(25, 150, 111, 23);
        		panelarr[t].add(btnBack);

            	
            	i++;
            	t++;
        } 
        }
        catch (SQLException e) {

            e.printStackTrace();

        }

        int[] arr= new int[2];
        arr[0]= t;
        arr[1]=i;
		return arr;
	
}

public void SvolgiTestMulti(String id_test, JPanel panel, CardLayout cl, int t, int i, JPanel panel_1, Controller contr, String id_stud, String login, JButton btnSend) {
    try {
        connessione = Database_connection.getInstance().getConnection();
    } catch (SQLException e) {
        System.out.println("Connessione fallita.");
        e.printStackTrace();
    }
	
	PreparedStatement select;
    try {
    	int n=0;
        select = connessione.prepareStatement("Select id_quiz, domanda, a, b, c, d, risposta_c, punt_c, punt_e, nome_id from quiz_risp_mul where nome_id= ('"+id_test+"');");
        ResultSet rs = select.executeQuery();

        while ( rs.next()) {
        	n++;
        }
        
        ResultSet res = select.executeQuery();
        while ( res.next()) {
        	int k=0;
        	String id_quiz=res.getString("id_quiz");
        	String domanda=res.getString("Domanda");
        	String A=res.getString("A");
        	String B=res.getString("B");
        	String C=res.getString("C");
        	String D=res.getString("D");
        	String risposta_c=res.getString("risposta_c");
        	int punt_c=res.getInt("punt_c");
        	int punt_e=res.getInt("punt_e");
        	String test=res.getString("nome_id");
        	
        	JPanel[] panelarr=new JPanel[n];
        	int h=t-1;
        	panelarr[k]=new JPanel();
        	panelarr[k].setLayout(null);
        	panelarr[k].setBounds(130, 90, 300, 200);
        	String id_2= new String ("pan_"+h);
        	String id= new String("pan_"+t);
        	String id_1= new String("pan_"+i);
        	
        	panel.add(panelarr[k], id);

        	
    		JTextPane textPane = new JTextPane();
    		textPane.setText(domanda);
    		textPane.setEditable(false);
    		textPane.setBounds(22, 11, 245, 49);
    		panelarr[k].add(textPane);
    		
    		
    		JRadioButton a = new JRadioButton(A);
    		a.setName("A");
    		a.setBounds(32, 72, 111, 23);
    		a.setActionCommand(a.getName() );
    		panelarr[k].add(a);
    		
    		JRadioButton b = new JRadioButton(B);
    		b.setName("B");
    		b.setBounds(32, 98, 111, 23);
    		b.setActionCommand(b.getName() );
    		panelarr[k].add(b);
    		
    		JRadioButton c = new JRadioButton(C);
    		c.setName("C");
    		c.setBounds(32, 124, 111, 23);
    		c.setActionCommand(c.getName() );
    		panelarr[k].add(c);
    		
    		JRadioButton d = new JRadioButton(D);
    		d.setName("D");
    		d.setBounds(32, 150, 111, 23);
    		d.setActionCommand(d.getName() );
    		panelarr[k].add(d);
    		

    		
    		ButtonGroup bg= new ButtonGroup();
    		bg.add(a);
    		bg.add(b);
    		bg.add(c);
    		bg.add(d);
    		
    				
        int nu=n;
        int num=i;
    		JButton btnNext = new JButton("->");
    		btnNext.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				cl.show(panel, id_1);
    				String entree = bg.getSelection().getActionCommand();
    				float nu=0;
    				contr.registraQuiz_svoltiM(test, id_stud, id_quiz, entree, nu);
    				if(num>nu) {
    					btnSend.setVisible(true);
    				}
    			}
    		});
    		btnNext.setBounds(155, 175, 111, 23);
    		panelarr[k].add(btnNext);
    		
    		JButton btnBack = new JButton("<-");
    		btnBack.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				cl.show(panel, id_2);
    			}
    		});
    		btnBack.setBounds(25, 175, 111, 23);
    		panelarr[k].add(btnBack);

        	t++;
        	i++;
        	k++;
        	
    } 
    }
    catch (SQLException e) {

        e.printStackTrace();

    }


}

public String getMatricolaS(String login) {

try {
    connessione = Database_connection.getInstance().getConnection();
} catch (SQLException e) {
    System.out.println("Connessione fallita.");
    e.printStackTrace();
}

PreparedStatement select;
try {
	int n=0;
    select = connessione.prepareStatement("Select id_stud FROM STUDENTE where login= ('"+login+"');");
    ResultSet rs = select.executeQuery();
    while(rs.next()) {
    	 login= rs.getString("id_stud");
    }
   
}catch (SQLException e) {

    e.printStackTrace();

}
return login;


}

public String getCod_corso(String login) {

try {
    connessione = Database_connection.getInstance().getConnection();
} catch (SQLException e) {
    System.out.println("Connessione fallita.");
    e.printStackTrace();
}

PreparedStatement select;
try {
	int n=0;
    select = connessione.prepareStatement("Select cod_corso FROM CORSO where nome= ('"+login+"');");
    ResultSet rs = select.executeQuery();
    while(rs.next()) {
    	 login= rs.getString("cod_corso");
    }
   
}catch (SQLException e) {

    e.printStackTrace();

}
return login;


}

public String getMatricolaI(String login) {

try {
    connessione = Database_connection.getInstance().getConnection();
} catch (SQLException e) {
    System.out.println("Connessione fallita.");
    e.printStackTrace();
}

PreparedStatement select;
try {
	int n=0;
    select = connessione.prepareStatement("Select id_ins FROM INSEGNANTE where login= ('"+login+"');");
    ResultSet rs = select.executeQuery();
    while(rs.next()) {
    	 login= rs.getString("id_ins");
    }
   
}catch (SQLException e) {

    e.printStackTrace();

}
return login;


}

public void registraQuiz_svoltiM (String nome_id, String id_stud, String id_quiz, String entree, float punteggio_quiz) {
	Quiz_svolti qs=new Quiz_svolti(nome_id,  id_stud,  id_quiz,  entree,  punteggio_quiz);
	iqs.salvaRispMulti(qs);
}

public void registraQuiz_svoltiA (String nome_id, String id_stud, String id_quiz, String entree, float punteggio_quiz) {
	Quiz_svolti qs=new Quiz_svolti(nome_id,  id_stud,  id_quiz,  entree,  punteggio_quiz);
	iqs.salvaRispostaApe(qs);
}

public void controllo(String login, List<String> te) {
	Controller c= new Controller();
	String matr=c.getMatricolaS(login);
	
	try {
        connessione = Database_connection.getInstance().getConnection();
    } catch (SQLException e) {
        System.out.println("Connessione fallita.");
        e.printStackTrace();
    }
	
	PreparedStatement select;
    try {
    	
        select = connessione.prepareStatement("Select distinct nome_id from quiz_svolti where id_stud=('"+matr+"');");
        ResultSet rs = select.executeQuery();
        
        ResultSet res = select.executeQuery();
        while ( res.next()) {
        	String ris=res.getString("nome_id");
        	
       for(int i = 0; i < te.size(); i++ ) {
        	 String el=te.get(i);
        		if(el.equals(ris)) {
        			te.remove(el);
        		}
        	}
        	
        }
	
}catch (SQLException e) {

    e.printStackTrace();

}

}

public void TestCorretti(String login, DefaultTableModel model) {
	Controller c= new Controller();
	String matr=c.getMatricolaS(login);
	
	try {
        connessione = Database_connection.getInstance().getConnection();
    } catch (SQLException e) {
        System.out.println("Connessione fallita.");
        e.printStackTrace();
    }
	
	PreparedStatement select;
    try {
    	
        select = connessione.prepareStatement("SELECT qv.NOME_ID, COUNT(RISPOSTA_DATA) FROM QUIZ_SVOLTI qv WHERE ID_STUD=('"+matr+"') GROUP BY NOME_ID HAVING COUNT(RISPOSTA_DATA)= (SELECT N_QUIZ FROM TEST  WHERE NOME_ID=qv.NOME_ID);");
        
        ResultSet res = select.executeQuery();
        while ( res.next()) {
        	String ris=res.getString(1);
        	String nome_corso=c.getCorso(ris);
        	
        	c.riempiTableV(ris, model, nome_corso, login);
        	
        }
	
}catch (SQLException e) {

    e.printStackTrace();

}
}

public String getCorso(String nome_id) {
	
	try {
        connessione = Database_connection.getInstance().getConnection();
    } catch (SQLException e) {
        System.out.println("Connessione fallita.");
        e.printStackTrace();
    }
	
	PreparedStatement select;
    try {
    	
        select = connessione.prepareStatement("SELECT NOME FROM CORSO WHERE COD_CORSO IN (SELECT COD_CORSO FROM TEST WHERE NOME_ID=('"+nome_id+"'))");
        
        ResultSet res = select.executeQuery();
        while ( res.next()) {
        	nome_id=res.getString("nome");
        	

        	
        }
	
}catch (SQLException e) {

    e.printStackTrace();

}
	return nome_id;
	
}

public void riempiTableV (String codTest, DefaultTableModel model, String nome_corso, String login) {
	Controller contr= new Controller();
	String matr=contr.getMatricolaS(login);
	
	try {
        connessione = Database_connection.getInstance().getConnection();
    } catch (SQLException e) {
        System.out.println("Connessione fallita.");
        e.printStackTrace();
    }
	
	
	Object[] row= new Object [4];
	
    
	
    try {    	PreparedStatement MyStmt = connessione.prepareStatement("SELECT SUM(Punteggio_quiz_dato) FROM QUIZ_SVOLTI WHERE Nome_id=('"+codTest+"') AND id_stud=('"+matr+"') ;");
    			ResultSet rs = MyStmt.executeQuery();
    
    	while(rs.next()) {
    	

        	
        	row[0]=nome_corso;
        	row[1]=codTest;
        	row[2]=matr;
        	row[3]=rs.getString(1);
        	model.addRow(row);

    	}
        }catch (SQLException e) {

        e.printStackTrace();

    }

}

}