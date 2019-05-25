package modelo;

public class Poblacion {
	private int codProv;
	private int codPob;
	private String nombre;
	
	
	/**
	 * Constructor del objeto población.
	 * @param codProv Código provincia.
	 * @param codPob Código población.
	 * @param nombre Nombre de la población.
	 */
	public Poblacion(int codProv, int codPob, String nombre) {
		this.codProv = codProv;
		this.codPob = codPob;
		this.nombre = nombre;
	}
	
	/**
	 * Constructor sin parámetros del objeto población.
	 */
	public Poblacion() {
		codProv = 0;
		codPob = 0;
		nombre = "";
	}

	/**
	 * Getter del código provincia
	 * @return Devuelve el código provincia.
	 */
	public int getCodProv() {
		return codProv;
	}

	/**
	 * Setter del código provincia.
	 * @param codProv Parámetro que establece el código provincia
	 */
	public void setCodProv(int codProv) {
		this.codProv = codProv;
	}
	
	/**
	 * Getter del código población
	 * @return
	 */
	public int getCodPob() {
		return codPob;
	}
	
	/**
	 * Setter del código población.
	 * @param codPob Parámetro del código población.
	 */
	public void setCodPob(int codPob) {
		this.codPob = codPob;
	}

	/**
	 * Getter del nombre del objeto población.
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Setter del nombre de la población.
	 * @param nombre Parámetro que le pasamos.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

