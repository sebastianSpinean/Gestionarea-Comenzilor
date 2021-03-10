package dataAccessLayer;
import model.Client;

import java.sql.*;

/**
 * 
 * clasa concreta pentru tabela client 
 * foloseste metodele abstracte mostenite din clasa parinte
 * defineste o metoda pentru identificarea id-ului maxim din tabel
 *
 */

public class ClientDAO extends AbstractDAO<Client>{
	
	
	
	
	/**
	 * obtine id-ul maxim din tabela client
	 * @return un int corespunzator id-ului maxim din tabel
	 */
	public int maxId() {//obtine id-ul maxim din tabela
		int id=0;
		Connection connection = null;
		PreparedStatement statement = null;    
		ResultSet rs = null;
		String query = queryMaxValue("idClient");  
		try {
			connection = ConnectionClass.getConnection();//se face conexiunea
			statement = connection.prepareStatement(query);
			rs=statement.executeQuery();//se executa
			while(rs.next()) {
				id=rs.getInt("max(idClient)");//se returneaza valoarea de pe coloana idClient
				return id;
			}
		}catch(SQLException e) {
			
		}
		
		
		return id;//returneaza 0 daca nu exista niciun element in tabel
		
	}

	

}
