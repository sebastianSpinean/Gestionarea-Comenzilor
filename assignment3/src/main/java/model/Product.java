package model;
/**
 * 
 * reprezinta clasa corespunzatoare tabelului product
 *
 */
public class Product {

	/**
	 * reprezinta id-ul unui produs
	 */
	private int idProduct; //id produs
	/**
	 * reprezinta numele unui produs
	 */
	private String nameProdus;// nume produs
	/**
	 * reprezinta stock-ul dintr-un produs
	 */
	private int stock;//stockul 
	/**
	 * reprezinta pretul produsului
	 */
	private float price;
	
	public Product() {
		
	}
	/**
	 * initializeaza un produs
	 * @param id reprezinta id-ul prousului
	 * @param nume reprezinta numele
	 * @param stock reprezinta stock-ul
	 * @param price reprezinta pretul
	 */
	public Product(int id,String nume, int stock, float price) {
		this.idProduct=id;
		this.nameProdus=nume;
		this.stock=stock;
		this.price=price;
	}

	/**
	 * obtine id-ul
	 * @return un int ce reprezinta id-ul
	 */
	public int getIdProduct() {
		return idProduct;
	}

	/**
	 * seteaza id-ul
	 * @param idProduct reprezinta id-ul
	 */
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	/**
	 * obtine numele
	 * @return un String ce reprezinta numele
	 */
	public String getNameProdus() {
		return nameProdus;
	}

	/**
	 * seteaza numele
	 * @param nameProdus reprezinta numele
	 */
	public void setNameProdus(String nameProdus) {
		this.nameProdus = nameProdus;
	}

	/**
	 * obtine stock-ul
	 * @return un int ce reprezinta stock-ul
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * seteaza stock-ul
	 * @param stock reprezinta stock-ul
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * obtine pretul
	 * @return un float care reprezinta pretul
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * seteaza pretul
	 * @param price reprezinta pretul
	 */
	public void setPrice(float price) {
		this.price = price;
	} 
	
	/**
	 * @return datele unui produs sub forma de String
	 */
	public String toString() {
		return idProduct+","+nameProdus+","+stock+","+price;
	}
	
}