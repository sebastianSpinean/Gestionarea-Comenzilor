package model;

/**
 * 
 * reprezinta clasa corespunzatoare tabelului Order_table
 *
 */

public class Order_table {

	/**
	 * reprezinta id-ul comenzii
	 */
	private int idOrder; //id-ul comenzii
	/**
	 * reprezinta id-ul clientului
	 */
	private int idClient;//id clientului care o facut comanda
	/**
	 * reprezinta totalul comenzii
	 */
	private float total;// toatl
	
	public Order_table() {
		
	}
	
	/**
	 * initializeaza o comanda
	 * @param ido reprezinta id-ul comenzii
	 * @param idc reprezinta id-ul clientului
	 * @param total reprezinta totalul comenzii
	 */
	public Order_table(int ido, int idc, float total) {
		this.idOrder=ido;
		this.idClient=idc;
		this.total=total;
	}

	/**
	 * obtine id-ul comenzii
	 * @return un int ce reprezinta id-ul comenzii
	 */
	public int getIdOrder() {
		return idOrder;
	}

	/**
	 * seteaza id-ul comenzii
	 * @param idOrder reprezinta id-ul comenzii
	 */
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	/**
	 * obtine id-ul clientului
	 * @return un int ce reprezinta id-ul clientului
	 */
	public int getIdClient() {
		return idClient;
	}

	/**
	 * seteaza id-ul clientului
	 * @param idClient reprezinta id-ul clientului
	 */
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	/**
	 * obtine totalul
	 * @return un float ce reprezinta totalul
	 */
	public float getTotal() {
		return total;
	}

	/**
	 * seteaza totalul
	 * @param total reprezinta totalul
	 */
	public void setTotal(float total) {
		this.total = total;
	}
	/**
	 * @return datele unei comenzi sub forma unui String
	 */
	public String toString() {
		return idOrder+","+idClient+","+total;
	}
	

}