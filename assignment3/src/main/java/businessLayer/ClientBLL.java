package businessLayer;

import model.Client;

import java.util.ArrayList;
import java.util.List;

import dataAccessLayer.ClientDAO;

/**
 * clasa care realizeaza logica de program corespunzatoare pentru clasa Client 
 * 
 *
 */
public class ClientBLL {

	/**
	 * reprezinta linia care contine o comanda pentru tabela client
	 */
	private String line; //linia citita din fisier
	/**
	 * reprezinta obiectul care realizeaza legatura cu baza de date
	 */
	private ClientDAO clientDao;
	
	public ClientBLL() {
		clientDao = new ClientDAO();
	}
	
	/**
	 * initializeaza un obiect
	 * @param line un String ce reprezinta linia ce contine comanda
	 */
	public ClientBLL(String line) {
		this.line=line;
		clientDao = new ClientDAO();
	}
	/**
	 * apeleaza operatia de insert
	 */
	public void insert() {//insertul
		Client client = new Client();
		String[] words = line.split("[, ]");//se desparte linia citita din fisier in cuvinte
		String name = words[2]+" "+words[3]; //se formeaza numele clientului
		String[] words2 = line.split(" ");
		String address = words2[4]; //adresa clientului
		client.setAddress(address);
		client.setName(name);
		int id = clientDao.maxId();//se gaseste id-ul maxim
		client.setIdClient(id+1);//se incrementeaza id-ul maxim
		clientDao.insert(client); //se insereaza clientul
		
	}
	/**
	 * apeleaza operatia de delete
	 */
	public void delete() {//se executa stergerea
		Client client = new Client();
		String[] words = line.split("[, ]");
		String name = words[2]+" "+words[3];// se gaseste numele clientului
		clientDao.delete("name",name);//se sterge clientul cu acel nume
		
	}
	/**
	 * realizeaza operatia de generare de raport
	 * @param reportClient reprezinta output-ul obtinut din baza de date
	 */
	public void report(int reportClient) {//se genereaza raport cu clientii
		List<Client> list = clientDao.listAll();//se gasesc toti clientii din tabel
		ArrayList<String> listClients = new ArrayList<String>(); 
		for(Client i : list) {//se pun intr-o lista toate atributele lor
			listClients.add(i.getIdClient()+"");
			listClients.add(i.getName());
			listClients.add(i.getAddress());
		}
		ArrayList<String> title = new ArrayList<String>();//se face o lista cu numele coloanelor
		title.add("idClient");
		title.add("name");
		title.add("address");
		
		PdfGenerator p = new PdfGenerator(3,title,listClients,"report clients"+reportClient);//se genereaza pdf-ul
		
	}
	    
	
}

