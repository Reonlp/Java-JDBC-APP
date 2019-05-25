package modelo;

public class Corredor {
	private int idCorredor;
	private String nombre;
	private String apellidos;
	private int edad;
	private String provincia;
	private String poblacion;

	/**
	 * Constructor del objeto corredor
	 * @param idCorredor
	 * @param nombre
	 * @param apellidos
	 * @param edad
	 * @param provincia
	 * @param poblacion
	 */
	public Corredor(int idCorredor, String nombre, String apellidos, int edad, String provincia, String poblacion) {
		this.idCorredor = idCorredor;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.provincia = provincia;
		this.poblacion = poblacion;

	}
	
	/**
	 * Segundo constructor de corredor 
	 */
	public Corredor() {
		idCorredor = 0;
		nombre = "";
		apellidos = "";
		edad = 0;
		provincia = "";
		poblacion = "";

	}
	
	/**
	 * Getter del id de corredor
	 * @return
	 */
	public int getIdCorredor() {
		return idCorredor;
	}
	
	/**
	 * Setter del id corredor.
	 * @param idCorredor
	 */
	public void setIdCorredor(int idCorredor) {
		this.idCorredor = idCorredor;
	}
	
	/**
	 * Getter del nombre de corredor
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Setter del nombre del corredor
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Getter del apellido
	 * @return
	 */
	public String getApellidos() {
		return apellidos;
	}
	
	/**
	 * Setter de Apellidos.
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	/**
	 * Getter de edad
	 * @return
	 */
	public int getEdad() {
		return edad;
	}
	
	/**
	 * Setter de edad
	 * @param edad
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	/**
	 * Getter de provincia
	 * @return
	 */
	public String getProvincia() {
		return provincia;
	}
	
	/**
	 * Setter de provincia
	 * @param provincia
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	/**
	 * Getter de poblacion
	 * @return
	 */
	public String getPoblacion() {
		return poblacion;
	}
	
	/**
	 * Setter de población
	 * @param poblacion
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	
	/**
	 * Metodo que devuelve un string con los dats del objeto.
	 */
	@Override
	public String toString() {
	    return "Id: " + idCorredor + "      " + "Nombre: " + nombre;
	}
	


	
}
