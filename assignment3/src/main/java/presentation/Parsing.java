package presentation;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import businessLayer.ClientBLL;
import businessLayer.OrderBLL;
import businessLayer.ProductBLL;

/**
 * 
 * realizeaza citirea din fisier
 *
 */

public class Parsing {

	/**
	 * reprezinta numele fisierului din care se citeste
	 */
	private String file; //fiserul din care se citeste
	/**
	 * initializeaza un obiect al acestei clase
	 * @param file reprezinta numele fisierului
	 */
	public Parsing(String file) {
		this.file=file;
	}
	
	/**
	 * apeleaza operatia de inserare a unui client
	 * @param line linia citita din fisier care contine comanda de inserare a unui client
	 */
	public void insertClient(String line) {   //se da comanda de inserare client
		ClientBLL clientbll = new ClientBLL(line); 
		clientbll.insert();
	}
	/**
	 * apeleaza stergerea unui client
	 * @param line linia citita din fisier care contine comanda de stergere 
	 */
	public void deleteClient(String line) {   //se da comanda de stergere client
		ClientBLL clientbll = new ClientBLL(line);
		clientbll.delete();
	}
	/**
	 * apeleaza generarea unui raport despre clienti
	 * @param reportClient reprezinta numarul raportului
	 */
	public void reportClient(int reportClient) { //se da comanda de generare raport client
		ClientBLL clientbll = new ClientBLL();
		clientbll.report(reportClient);
	}
	/**
	 * apeleaza operatia de inserare a unui produs
	 * @param line linia citita din fisier care contine comanda de inserare a unui produs
	 */
	public void insertProduct(String line) { //se da comanda de inserare produs
		ProductBLL productbll = new ProductBLL(line);
		productbll.insert();
		
	}
	/**
	 * apeleaza stergerea unui produs
	 * @param line  linia citita din fisier care contine comanda de stergere
	 */
	public void deleteProduct(String line) {//se da comanda de stergere produs
		ProductBLL productbll = new ProductBLL(line);
		productbll.delete();
	}
	/**
	 *  apeleaza generarea unui raport despre produse
	 * @param reportProduct reprezinta numarul raportului
	 */
	public void reportProduct(int reportProduct) {//se da comanda de generare raport produs
		ProductBLL productbll = new ProductBLL();
		productbll.report(reportProduct);
	}
	/**
	 * apeleaza operatia de creare a unei comenzi
	 * @param line linia din fisier care contine comanda
	 */
	public void order(String line) {// se face o noua comanda
		OrderBLL orderbll = new OrderBLL(line);
		orderbll.order();
		
	}
	/**
	 * apeleaza generarea unui raport despre comenzi
	 * @param reportOrder reprezinta numarul raportului
	 */
	public void reportOrder(int reportOrder) {//se da comanda de generare raport comanda
		OrderBLL orderbll = new OrderBLL();
		orderbll.report(reportOrder);
	}
    /**
     * realizeaza citirea din fisier si executarea comenzilor
     */
	public void parse() {
		try {
	  
			
			File pf = new File(file);
			Scanner sc = new Scanner(pf);
			int reportClient=0,reportProduct=0,reportOrder=0;// se initializeaza numarul de rapoarte create cu 0
			
			while(sc.hasNextLine()) {
				
				String line = sc.nextLine(); //se citeste o linie din fisier
				String[] instr = line.split(" ");//se desparte in cuvinte
			  
				
			
				if(instr[0].equals("Report")) {//primul cuvant este report
			  
					if(instr[1].equals("client")) {//al doilea este client
						reportClient++;//creste numarul de rapoarte generate
						reportClient(reportClient);
					} 
					else if(instr[1].equals("product")) {//al doilea este produs
						reportProduct++;//creste numarul de rapoarte generate
						reportProduct(reportProduct);
					}
					else if(instr[1].equals("order")) {//al doilea este order
						reportOrder++;//creste numarul de rapoarte generate
						reportOrder(reportOrder);
					
					}
					
				}
				else if(instr[0].equals("Insert")) {//primul este insert
			 
					if(instr[1].equals("client:")) {//al doilea este client
						insertClient(line);
					} 
					else if(instr[1].equals("product:")) {//al doilea este produs
						insertProduct(line);
					} 
				}
				else if(instr[0].equals("Order:")) {//primul este order
					order(line);
				}
				else if(instr[0].equals("Delete")) {//primul este delete
	 
					if(instr[1].equals("product:")) {//al doilea este produs
					     deleteProduct(line);
					}
					else if(instr[1].equals("client:")) {//al doilea este client
						deleteClient(line);
					} 
					
				}
				
			} sc.close();
				
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	
	}
	
	public static void main(String[] args) {
		Parsing p = new Parsing("in.txt"); 
		p.parse();
		
	}
}
