package businessLayer;

import java.util.ArrayList;
import java.util.List;

import dataAccessLayer.ProductDAO;

import model.Product;
/**
 * 
 * clasa care realizeaza logica de program corespunzatoare pentru clasa Product
 *
 */
public class ProductBLL {
	/**
	 * contine comanda
	 */
	private String line;//linia citita din fisier
	/**
	 * reprezinta obiectul care realizeaza legatura cu baza de date
	 */
	private ProductDAO productDao;
	/**
	 * reprezinta un validator
	 */
	private Validator validator;
	
	public ProductBLL() {
		productDao = new ProductDAO();
		validator = new Validator();
	}
	/**
	 *initializeaza un obiect
	 * @param line un String care contine comanda
	 */
	public ProductBLL(String line) {
		this.line=line;
		productDao = new ProductDAO();
		validator = new Validator();
	}
	/**
	 * apeleaza operatia de insert
	 */
	public void insert() {//operatia de insert
		Product product = new Product();
		String[] words = line.split("[, ]");
		String name = words[2];//numele produslui
		validator.setVariable(words[4]);
	
		if(validator.isValid()) {//se valideaza stockul
			
			validator.setVariable(words[6]);
			
			
			if(validator.isFloatValid()) {//se valideaza pretul
				
				int stock = Integer.parseInt(words[4]);
				float price = Float.parseFloat(words[6]);
				int id = productDao.maxId();
				product.setIdProduct(id+1);//se gaseste id-ul maxim
				product.setNameProdus(name);
				product.setStock(stock);
				product.setPrice(price); 
				List<Product> pr = productDao.findBy("nameProdus", product.getNameProdus());//se verifica daca mai exista un produs cu acel nume
				if(pr.isEmpty()) {
					productDao.insert(product);//daca nu se introduce
				}
				else {//daca da
					productDao.update("stock", "nameProdus", pr.get(0).getStock()+product.getStock(), product.getNameProdus());//se face update la stock
					productDao.update("price", "nameProdus", product.getPrice(), product.getNameProdus());//se face update la pret
				}
			}
		}
					
	}
	/**
	 * apelaza operatia de delete
	 */
	public void delete() {//stergerea
		String[] words = line.split(" ");
		String name = words[2];//numele
		productDao.delete("nameProdus",name);//se sterge
	}
	/**
	 * genereaza un raport
	 * @param reportProduct reprezinta output-ul obtinut din baza de date
	 */
	public void report(int reportProduct) {//se genereaza raportul
		List<Product> list = productDao.listAll();//tuplele din tabela
		ArrayList<String> listProducts = new ArrayList<String>(); 
		for(Product i : list) {//se adauga valorile lor intr-o lista
			listProducts.add(i.getIdProduct()+"");
			listProducts.add(i.getNameProdus());
			listProducts.add(i.getStock()+"");
			listProducts.add(i.getPrice()+"");
		}
		ArrayList<String> title = new ArrayList<String>();//se introduc intr-o lista titlurile coloanelor
		title.add("idProduct");
		title.add("nameProdus");
		title.add("stock");
		title.add("price");
		
		PdfGenerator p = new PdfGenerator(4,title,listProducts,"report products"+reportProduct);//se genereaza pdf-uri
	}

}
