package modelo;

public class Carrera {
	private int idCarrera;
	private String nombre;
	private int distancia;
	private String provincia;
	private String poblacion;
	
	
	public Carrera(int idCarrera, String nombre, int distancia, String provincia, String poblacion) {
		this.idCarrera = idCarrera;
		this.nombre = nombre;
		this.distancia = distancia;
		this.provincia = provincia;
		this.poblacion = poblacion;
	}
	

	public Carrera() {
		idCarrera = 0;
		nombre = "";
		distancia = 0;
		provincia = "";
		poblacion = "";
	}

	/**
	 * Getter del id de carrera
	 * @return Devuelve el id.
	 */
	public int getIdCarrera() {
		return idCarrera;
	}

	/**
	 * Setter del id de carrera.
	 * @param idCarrera Devuelve el id de carrera
	 */
	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	/**
	 * Getter del nombre de carrera
	 * @return Devuelve el nombre de la carrera
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter del nombre de la carrera
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter de la distancia de carrera.
	 * @return Devuelve la distancia de la carrera
	 */
	public int getDistancia() {
		return distancia;
	}

	/**
	 * Setter de distancia
	 * @param distancia
	 */
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	/**
	 * Getter de provincia
	 * @return Devuelve provincia
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
	 * Getter de población
	 * @return Devuelve la población de la carrera
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
	 * Función que devuelve un objeto carrera en un string
	 */
	@Override
	public String toString() {
	    return "Id: " + idCarrera + "      " + "Nombre: " + nombre;
	}
	
	
	
}
