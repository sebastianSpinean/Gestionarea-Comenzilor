package model;

/**
 * 
 * Clasa corespunzatoare tabelului Client
 * 
 * 
 */


public class Client {
	
	/** 
	 * reprezinta id-ul unui client din baza de date
	 */
	private int idClient;   //id client
	/**
	 * reprezinta numele clientului
	 */
	private String name; // nume client
	/**
	 * reprezinta adresa unui client
	 */
	private String address; //adresa client
	
	public Client() {
		
	}
	/**
	 * se initializeaza un client
	 * @param id id-il clientului
	 * @param name numele clientului
	 * @param address adresa clientului
	 */
	public Client(int id, String name, String address) {
		this.idClient=id;
		this.name=name;
		this.address=address;
	}

	/**
	 * seteaza id-ul
	 * @return un int care reprezint id-ul
	 */
	public int getIdClient() {
		return idClient;
	}

	/**
	 * seteaza id-ul
	 * @param idClient reprezinta id-ul unui client
	 */
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	/**
	 * obtine numele
	 * @return un String corespunzator numelui
	 */
	public String getName() {
		return name;
	}

	/**
	 * seteaza numele
	 * @param name reprezinta numele clientului
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * obtine adresa
	 * @return un String corespunzator adresei
	 */
	public String getAddress() {
		return address;
	}

	/**
     * seteaza adresa
	 * @param address reprezinta adresa clientului
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @return returneaza datele unui client sub forma de string
	 */
	public String toString() {
		return idClient+","+name+","+address;
	}

}