package businessLayer;

import java.util.ArrayList;
import java.util.List;

import dataAccessLayer.ClientDAO;
import dataAccessLayer.OrderDAO;
import dataAccessLayer.OrderItemDAO;
import dataAccessLayer.ProductDAO;
import model.Client;
import model.OrderItem;
import model.Order_table;
import model.Product;

/**
 * 
 * clasa care realizeaza logica de program corespunzatoare pentru clasa Order_table
 *
 */
public class OrderBLL {

	/**
	 * reprezinta linia care contine comanda
	 */
	private String line; //linia citita din fisier
	/**
	 * reprezinta obiectul care realizeaza legatura cu baza de date
	 */
	private OrderDAO orderDao;
	/**
	 * reprezinta obiectul care realizeaza legatura cu baza de date
	 */
	private ClientDAO clientDao;
	/**
	 * reprezinta obiectul care realizeaza legatura cu baza de date
	 */
	private ProductDAO productDao;
	/**
	 * reprezinta obiectul care realizeaza legatura cu baza de date
	 */
	private OrderItemDAO orderItemDao;
	/**
	 * reprezinta un validator
	 */
	private Validator validator;
	/**
	 * variabila statica reprezentand numarul de facturi
	 */
	private static int billNumber=1;//numarul de facturi generate
	/**
	 * variabila statica reprezentand numarul de pdf-uri pentru dituatia de under-stock
	 */
	private static int underStock=1;//numarul de pdf-uri pentru cazul de under-stock
	
	public OrderBLL() {
		orderDao = new OrderDAO();
		clientDao = new ClientDAO();
	    productDao = new ProductDAO();
	    orderItemDao = new OrderItemDAO();
	    validator = new Validator();
	}
	/**
	 * initializeaza un obiect
	 * @param line un String care contine comanda
	 */
	public OrderBLL(String line) {
		orderDao = new OrderDAO();
		clientDao = new ClientDAO();
	    productDao = new ProductDAO();
	    orderItemDao = new OrderItemDAO();
	    validator = new Validator();
		this.line = line;
	}
	
	
	
	/**
	 * realizeaza o comanda
	 */
	public void order() {// se creaza o noua comanda
		String[] words = line.split("[, ]");
		String name = words[1] + " " + words[2];//se formeaza numele clientului
		String productName = words[4];//numele produsului
	
		validator.setVariable(words[6]);  
		
		if(validator.isValid()) { //se verifica cantitatea sa fie valida
			int quantity = Integer.parseInt(words[6]);
			
			
			List<Client> client = clientDao.findBy("name", name); //se gaseste clientul cu acel nume
			List<Product> product = productDao.findBy("nameProdus",productName);//se gaseste produsul cu acel nume
			
			if(!client.isEmpty() && !product.isEmpty()) { //se verifica sa existe acel client si acel produs
				
				int idClient = client.get(0).getIdClient(); //id-ul clientului
				int idProduct = product.get(0).getIdProduct();// id-ul produsului
	            int idOrder = orderDao.maxId() + 1;//id-ul comenzii
				float total = product.get(0).getPrice()*quantity;//se calculeaza totalul
				Order_table order = new Order_table(idOrder,idClient,total);//se face o comanda
				
				List<Order_table> existentOrder = orderDao.findBy("idClient", idClient);//se verifica daca mai exista o comanda pentru acel cliebt
				OrderItem orderItem = new OrderItem(idOrder,idProduct,quantity);
				
				if(existentOrder.isEmpty() && product.get(0).getStock() - quantity >= 0) {//daca nu exista si stockul este suficient
					orderDao.insert(order);//se face o noua comanda
					orderItemDao.insert(orderItem);//se introduce in orderitem
					productDao.update("stock", "idProduct", product.get(0).getStock() - quantity, product.get(0).getIdProduct());//update la stock
					PdfGenerator pf = new PdfGenerator(name+" has ordered "+productName+" at a quantity of "+quantity,"Bill"+billNumber);//se genereaza factura
					billNumber++;//creste numarul facturilor
					
				}
				else if(!existentOrder.isEmpty() && product.get(0).getStock() - quantity >= 0) {//exista deja o comanda pentru acel client
					productDao.update("stock", "idProduct", product.get(0).getStock() - quantity, product.get(0).getIdProduct());//update la stock
					orderDao.update("total", "idClient", total+existentOrder.get(0).getTotal(), idClient);//se face update la comanda existenta
					orderItem.setIdOrder(existentOrder.get(0).getIdOrder());
					orderItemDao.insert(orderItem);//se introduce in orderitem
					PdfGenerator pf = new PdfGenerator(name+" has ordered "+productName+" and the total is "+total,"Bill"+billNumber);//se genereaza factura
					billNumber++;//creste numarul facturilor
						
				}
				else if(product.get(0).getStock() - quantity < 0) {//stockul este insuficient
					PdfGenerator pf = new PdfGenerator("There is an under-stock for product "+productName,"UnderStock"+underStock);//se genereaza mesaj de under-stock
					underStock++;//creste numarul mesajelor de under-stock
				}
				
			}
			
		}
		
	}
	/**
	 * genereaza un raport al comenzilor
	 * @param reportOrder reprezinta output-ul din tabela Order_table
	 */
	public void report(int reportOrder) {//se face raportul 
		List<Order_table> list = orderDao.listAll();//se returneaza continutul tabelei
		ArrayList<String> listOrders = new ArrayList<String>(); 
		for(Order_table i : list) {//se adauga intr-o lista datele tabelei
			listOrders.add(i.getIdOrder()+"");
			listOrders.add(i.getIdClient()+"");
			listOrders.add(i.getTotal()+"");
			
		}
		ArrayList<String> title = new ArrayList<String>();//se face o lista cu numele coloanelor
		title.add("idOrder");
		title.add("idClient");
		title.add("total");
		
		
		PdfGenerator p = new PdfGenerator(3,title,listOrders,"report order"+reportOrder);//se genereaza pdf
	}
}

