package model;

/**
 * 
 * reprezinta clasa corespunzatoare tabelului OrderItem
 *
 */
public class OrderItem {

	/**
	 * reprezinta id-ul comenzii
	 */
	private int idOrder; //id comanda
	/**
	 * reprezinta id-ul produsului
	 */
	private int idProduct; //id produs
	/**
	 * reprezinta cantitatea 
	 */
	private int quantity;// cantitatea
	
	public OrderItem() {
		
	}
	/**
	 * initializeaza un obiect dintr-o comanda 
	 * @param ido reprezinta id-ul unei comenzi
	 * @param idp reprezinta id-ul unui produs
	 * @param quantity reprezinta cantitatea
	 */
	public OrderItem(int ido, int idp, int quantity) {
		this.idOrder=ido;
		this.idProduct=idp;
		this.quantity=quantity;
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
	 * obtine id-ul produsului
	 * @return un int ce reprezinta id-ul produsului
	 */
	public int getIdProduct() {
		return idProduct;
	}

	/**
	 * seteaza  id-ul produsului
	 * @param idProduct reprezinta id-ul produsului
	 */
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	/**
	 * obtine cantitatea
	 * @return un int ce reprezinta cantitatea
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * seteaza cantitatea
	 * @param quantity reprezinta cantitatea
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}