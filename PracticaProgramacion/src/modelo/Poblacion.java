package modelo;

public class Poblacion {
	private int codProv;
	private int codPob;
	private String nombre;
	
	
	/**
	 * Constructor del objeto poblaci�n.
	 * @param codProv C�digo provincia.
	 * @param codPob C�digo poblaci�n.
	 * @param nombre Nombre de la poblaci�n.
	 */
	public Poblacion(int codProv, int codPob, String nombre) {
		this.codProv = codProv;
		this.codPob = codPob;
		this.nombre = nombre;
	}
	
	/**
	 * Constructor sin par�metros del objeto poblaci�n.
	 */
	public Poblacion() {
		codProv = 0;
		codPob = 0;
		nombre = "";
	}

	/**
	 * Getter del c�digo provincia
	 * @return Devuelve el c�digo provincia.
	 */
	public int getCodProv() {
		return codProv;
	}

	/**
	 * Setter del c�digo provincia.
	 * @param codProv Par�metro que establece el c�digo provincia
	 */
	public void setCodProv(int codProv) {
		this.codProv = codProv;
	}
	
	/**
	 * Getter del c�digo poblaci�n
	 * @return
	 */
	public int getCodPob() {
		return codPob;
	}
	
	/**
	 * Setter del c�digo poblaci�n.
	 * @param codPob Par�metro del c�digo poblaci�n.
	 */
	public void setCodPob(int codPob) {
		this.codPob = codPob;
	}

	/**
	 * Getter del nombre del objeto poblaci�n.
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Setter del nombre de la poblaci�n.
	 * @param nombre Par�metro que le pasamos.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

