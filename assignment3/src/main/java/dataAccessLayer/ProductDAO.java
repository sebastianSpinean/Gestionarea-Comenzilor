package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;
/**
 * 
 * * clasa concreta pentru tabela product 
 * foloseste metodele abstracte mostenite din clasa parinte
 * defineste o metoda pentru identificarea id-ului maxim din tabel
 *
 */
public class ProductDAO extends AbstractDAO<Product> {

	/*public void insertProduct(Product product) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = queryInsert(); 
		try {
			connection = ConnectionClass.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1,product.getIdProduct());
			statement.setString(2, product.getNameProdus());
			statement.setInt(3, product.getStock());
			statement.setFloat(4, product.getPrice());
	
			statement.execute();
			
		}catch(SQLException e) {
			
		}
		
	}
	
	public void deleteProduct(String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = queryDelete("nameProdus"); 
		try {
			connection = ConnectionClass.getConnection();
			statement = connection.prepareStatement(query);
			//statement.setInt(1,client.getIdClient());
			statement.setString(1, name);
			//statement.setString(3, client.getAddress());
			statement.execute();
			
		}catch(SQLException e) {
			
		}
	}*/
	/**
	 obtine id-ul maxim din tabela product
	 * @return un int corespunzator id-ului maxim din tabel
	 */
	public int maxId() {//id-ul maxim din tabela
		int id=0;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		String query = queryMaxValue("idProduct");  
		try {
			connection = ConnectionClass.getConnection();//conexiunea
			statement = connection.prepareStatement(query);
			rs=statement.executeQuery();
			while(rs.next()) {
				
				id=rs.getInt("max(idProduct)");//se returneaza valoarea id-ului
				return id;
			}
		}catch(SQLException e) {
			
		}
		
		
		return id;//returneaza 0 daca nu exista niciun element 
	}
	
	
}

