package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import  java.lang.reflect.ParameterizedType;



 /**
  * 
  * Clasa generica care realizeaza operatiile asupra bazei de date
  *
  * @param <T> parametru generic care se poate mapa pe orice clasa care are toate variabilele instanta corespunzatoare coloanelor unei tanele din baza de date
  */
public class AbstractDAO<T> { 
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
	
	private final Class<T> type ;
	
	/**
	 * obtine clasa parametrului generic
	 */
	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		
		this.type=(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	
	/**
	 * construieste o interogare generica pentru insert
	 * @return un String corespunzator interogarii de insert
	 */
	
	public String queryInsert() {  // se genereaza query-ul pentru insert
		StringBuilder s = new StringBuilder();
		s.append("insert ");
		s.append(" into ");
		s.append(type.getSimpleName()); //numele tabelului se obtine prin reflexie
		Field[] fields = type.getDeclaredFields();
		int nr=0;
		s.append("( ");
		for(Field f : fields) {//se calculeaza numarul de coloane
			nr++;
		}
		for(int i=0;i<nr-1;i++) {//numele coloanelor sunt adaugete tot prin reflexie
			s.append(fields[i].getName());
			s.append(",");
		}
		s.append(fields[nr-1].getName());
		s.append(") ");
		s.append(" values ");
		s.append(" ( ");
		for(int i =0; i<nr-1; i++) {// se adauga atatea ? cate coloane are tabela
			s.append("?,");
		}
		s.append("?);");
		return s.toString();
	}
	/**
	 * construieste o interogare generica pentru stergere
	 * @param field reprezinta atributul dupa care se identifica tuplele care trebuie sterse
	 * @return un String corespunzator interogarii de delete
	 */
	public String queryDelete(String field) {//queryul pentru delete
		StringBuilder s = new StringBuilder();
		s.append("delete from ");
		s.append(type.getSimpleName()); //numele tabelei obtinut prin reflexie
		s.append(" where " +field+"=");
		s.append("?;");
		return s.toString();
	}
	
	/**
	 * construieste o interogare generica pentru update
	 * @param field1 reprezinta atributul care trebuie modificat
	 * @param field2 reprezinta atribultul dupa care se face identificarea tuplei care trebuie modificate
	 * @return un String corespunzator interogarii de update
	 */
	public String queryUpdate(String field1, String field2) {//queryul pentru update
		StringBuilder s = new StringBuilder();
		s.append("update ");
		s.append(type.getSimpleName());//numele tabelului se obtine prin reflexie
		s.append(" set ");
		s.append(field1+"=? ");
		s.append(" where ");
		s.append(field2+"=?;");
		return s.toString();
		
	}
	/**
	 * construieste o interogare generica pentru gasirea unei tuple
	 * @param field reprezinta atributul dupa care se face identificarea
	 * @return un String corespunzator interogarii
	 */
	public String queryFindBy(String field) {// queryul pentru gasirea unei tuple dupa o conditie
		StringBuilder s = new StringBuilder();
		s.append("select * from ");
		s.append(type.getSimpleName());//numele tabelului se obtine prin reflexie
		s.append(" where ");
		s.append(field+"=?;");
		return s.toString();
	}
	/**
	 * construieste o interogare pentru afisarea continutului intregii tabele
	 * @return un String corespunzator interogarii
	 */
	public String queryFindAll() {//queryul pentru afisarea tuturor tuplelor
		StringBuilder s = new StringBuilder();
		s.append("select * from ");
		s.append(type.getSimpleName()); //numele tabelului se obtine prin reflexie
		return s.toString();
	} 
	/**
	 * construieste o interogare pentru a afla valoarea maxima a unui atribut
	 * @param field specifica atributul 
	 * @return un String corespunzator interogarii
	 */
	public String queryMaxValue(String field) {// queryul folosit pentru gasirea valorii maxime de pe o coloana
		StringBuilder s = new StringBuilder();
		s.append("select max("+field+")"+" from "+type.getSimpleName()+";"); //numele tabelului se obtine prin reflexie
		return s.toString();
		
	}
	/**
	 * afiseaza continutul intregii tabele
	 * @return o lista de obicecte corespunzatoare unui tabel
	 */
	public List<T> listAll() {// afisarea tuturor tuplelor
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		String query = queryFindAll(); 
		try {
			connection = ConnectionClass.getConnection();//se face conexiunea la baza de date
			statement = connection.prepareStatement(query); //executarea queryului
			rs=statement.executeQuery();
			
		}catch(SQLException e) {
			
		}
		
		return createObjects(rs); //se returnaza valorile sub forma unei liste de obiecte T
	}
	
	
	
	/**
	 * creaza o lista de obiecte de tip T
	 * @param resultSet reprezinta rezultatul obtinut dintr-o tabela 
	 *  in urma unei interogari 
	 * @return lista de obiecte de tip T
	 */
	private List<T> createObjects(ResultSet resultSet) {// creaza o lista de obiecte T din tuplele returnate dintr-un tabel
		List<T> list = new ArrayList<T>();

		try {
			while (resultSet.next()) {
				
				T instance = type.getDeclaredConstructor().newInstance();
				for (Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch(Exception e) {
			
		}
		return list;
	}
	
	/**
	 * efectueaza operatia de inserare asupra bazei de date
	 * @param t parametru generic care se insereaza 
	 */
	
	public void insert(T t) {// inserarea intr-un tabel a unui obiect de tip T
		Connection connection = null;
		PreparedStatement statement = null;
		String query = queryInsert(); 
		try {
			connection = ConnectionClass.getConnection(); //crearea conexiunii
			statement = connection.prepareStatement(query);
			Field[] fields = type.getDeclaredFields(); //se obtin numele variabilelor instanta ale clasei 
			for(int i=0;i<fields.length;i++) {
				 Field field=fields[i];
				    field.setAccessible(true); //se face din private accesibil
				    Object value=field.get(t);// se obtine valoarea variabilei
				    statement.setObject((i+1), value);
			}
			statement.execute();
			
		}catch(Exception e) {
			
		}
		
	}
	/**
	 * realizeaza operatia de stergere asupra bazei de date
	 * @param field1 reprezinta atributul dupa care se face identficarea tuplei ce urmeaza a fi stearsa
	 * @param field2 reprezinta valoarea atributului dupa care se face stergerea
	 */
	public void delete(String field1, Object field2) { //se executa stergerea
		Connection connection = null;
		PreparedStatement statement = null;
		String query = queryDelete(field1); 
		try {
			connection = ConnectionClass.getConnection();//se face conexiunea
			statement = connection.prepareStatement(query);
		    statement.setObject(1, field2);
			statement.execute();
		}catch(Exception e) {
			
		}
		
		
	}
	/**
	 * returneaza o lista de obiecte de tip T care corespund unui anumit criteriu de identificare
	 * @param field reprezinta atributul dupa care se face identificarea 
	 * @param value reprezinta valoarea atributului dupa care se face identificarea 
	 * @return o lista de atribute de tip T
	 */
	public List<T> findBy(String field, Object value) {//se executa operatia de gasire dupa un criteriu
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		String query = queryFindBy(field);
		try {
			connection = ConnectionClass.getConnection();//se face conexiunea
			statement = connection.prepareStatement(query);
		    statement.setObject(1, value);
			rs=statement.executeQuery(); 
		}catch(Exception e) {
			
		}
		
		
		return createObjects(rs);//se returnaza valorile sub forma unei liste de obiecte T
		
	}
	/**
	 * efectueaza operatie de update asupra bazei de date
	 * @param field1 reprezinta atributul care se modifica
	 * @param field2 reprezinta atributul dupa care se face identificarea tuplei
	 * @param value1 reprezinta noua valoare care se introduce in tabel
	 * @param value2 reprezinta valoarea dupa care se face identificarea tuplei
	 */
	public void update(String field1, String field2, Object value1, Object value2) {//se executa operatia de update
		Connection connection = null;
		PreparedStatement statement = null;
		String query = queryUpdate(field1,field2); 
		try {
			connection = ConnectionClass.getConnection();//se face conexiunea
			statement = connection.prepareStatement(query);
		    statement.setObject(1, value1);
		    statement.setObject(2, value2);
			statement.execute();//se executa
		}catch(Exception e) {
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
