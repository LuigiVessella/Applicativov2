package project.dao;
import project.database_connection.*;
import project.model.*;

import java.sql.*;

public interface Quiz_svoltiDAO {

	public void salvaRispMulti(Quiz_svolti qs);
	public void salvaRispostaApe(Quiz_svolti qs);
	
}
