package modelo;

public class Provincia {
	private int cod;
	private String name;
	
	/**
	 * Constructor por par�metros del objeto provincia.
	 * @param cod Codigo provincia.
	 * @param name Nombre provincia
	 */
	public Provincia(int cod, String name) {
		this.cod = cod;
		this.name = name;
	}
	
	/**
	 * Constructor sin par�metros del objeto provincia.
	 */
	public Provincia() {
		cod = 0;
		name = "";
	}
	
	/**
	 * Getter del c�digo de la provincia.
	 * @return
	 */
	public int getCod() {
		return cod;
	}
	
	/**
	 * Setter del c�digo de la provincia.
	 * @param cod Par�metro del setter.
	 */
	public void setCod(int cod) {
		this.cod = cod;
	}

	/**
	 * Getter del nombre de provincia.
	 * @return Devuelve el nombre de la provincia. 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter del nombre de la provincia. 
	 * @param name Par�metro del setter.
	 */
	public void setName(String name) {
		this.name = name;
	}	
}
