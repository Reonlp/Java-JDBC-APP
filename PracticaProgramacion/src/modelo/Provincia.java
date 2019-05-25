package modelo;

public class Provincia {
	private int cod;
	private String name;
	
	/**
	 * Constructor por parámetros del objeto provincia.
	 * @param cod Codigo provincia.
	 * @param name Nombre provincia
	 */
	public Provincia(int cod, String name) {
		this.cod = cod;
		this.name = name;
	}
	
	/**
	 * Constructor sin parámetros del objeto provincia.
	 */
	public Provincia() {
		cod = 0;
		name = "";
	}
	
	/**
	 * Getter del código de la provincia.
	 * @return
	 */
	public int getCod() {
		return cod;
	}
	
	/**
	 * Setter del código de la provincia.
	 * @param cod Parámetro del setter.
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
	 * @param name Parámetro del setter.
	 */
	public void setName(String name) {
		this.name = name;
	}	
}
