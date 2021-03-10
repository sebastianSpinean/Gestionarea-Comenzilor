package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Order_table;
/**
 * 
 * clasa concreta pentru tabela order_tabel 
 * foloseste metodele abstracte mostenite din clasa parinte
 * defineste o metoda pentru identificarea id-ului maxim din tabel
 *
 */
public class OrderDAO extends AbstractDAO<Order_table> {

	
	/**
	  obtine id-ul maxim din tabela order_table
	 * @return un int corespunzator id-ului maxim din tabel
	 */
	public int maxId() {//id-ul mxim din tabela
		int id=0;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		String query = queryMaxValue("idOrder");  
		try {
			connection = ConnectionClass.getConnection();//conexiunea
			statement = connection.prepareStatement(query);
			rs=statement.executeQuery();
			while(rs.next()) {
				
				id=rs.getInt("max(idOrder)"); //se returneaza valoarea de pe coloana idOrder
				return id;
			}
		}catch(SQLException e) {
			
		}
		
		
		return id;// se returneaza 0 daca nu exista nimic in tabel
	}
}

