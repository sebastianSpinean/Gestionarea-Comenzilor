package businessLayer;

/**
 * 
 * Clasa care verifica validitatea anumitor variabile
 *
 */
public class Validator {

	/**
	 * variabila care se verifica
	 */
	private String variable;//variabila ce se valideaza
	
	public Validator() {
		
	}
	
	/**
	 * initializeaza un validator
	 * @param variable variabila care se verifica
	 */
	public Validator(String variable) {
		this.variable=variable;
		
	}
	/**
	 * operatie care verifica daca o variabila este de tip int
	 * @return returneaza true daca variabila este de tip int si false in caz contrar
	 */
	public boolean isValid() {//se verifica daca are forma unui numar intreg pozitiv
		if(variable.matches("([1-9][0-9]*)|0")) {
				return true;
		}
		return false;
	}
	/**
	 *  operatie care verifica daca o variabila este de tip float
	 * @return true daca variabila este de tip float si false in caz contrar
	 */
	public boolean isFloatValid() {//se verifica daca are forma unui numar real pozitiv
		if(variable.matches("([1-9][0-9]*[.][0-9][0-9]*)|([0][.][0-9][0-9]*)|([1-9][0-9]*)|0")) {
			return true;
		}
		return false;
	}


	/**
	 * obtine variabila
	 * @return un String care reprezinta variabila
	 */
	public String getVariable() {
		return variable;
	}


	/**
	 * seteaza variabila
	 * @param variable un String care reprezinta variabila
	 */
	public void setVariable(String variable) {
		this.variable = variable;
	}
	
	
	
	
}
