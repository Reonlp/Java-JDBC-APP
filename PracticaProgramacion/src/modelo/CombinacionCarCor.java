package modelo;

public class CombinacionCarCor {
	private int idCorredor;
	private String nombre;
	private String apellidos;
	private int edad;
	private String provincia;
	private String poblacion;
	private int idCarrera;
	private String nombreCarrera;
	private int distancia;
	private String provinciaCarrera;
	private String poblacionCarrera;
	
	
	/**
	 * Constructor del objeto combinación carreras y corredores.
	 * @param idCorredor
	 * @param nombre
	 * @param apellidos
	 * @param edad
	 * @param provincia
	 * @param poblacion
	 * @param idCarrera
	 * @param nombreCarrera
	 * @param distancia
	 * @param provinciaCarrera
	 * @param poblacionCarrera
	 */
	public CombinacionCarCor(int idCorredor, String nombre, String apellidos, int edad, String provincia,
			String poblacion, int idCarrera, String nombreCarrera, int distancia, String provinciaCarrera,
			String poblacionCarrera) {

		this.idCorredor = idCorredor;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.provincia = provincia;
		this.poblacion = poblacion;
		this.idCarrera = idCarrera;
		this.nombreCarrera = nombreCarrera;
		this.distancia = distancia;
		this.provinciaCarrera = provinciaCarrera;
		this.poblacionCarrera = poblacionCarrera;
	}
	
	/**
	 * Segundo constructor del objeto carrera y constructores
	 */
	public CombinacionCarCor() {
		idCorredor = 0;
		nombre = "";
		apellidos = "";
		edad = 0;
		provincia = "";
		poblacion = "";
		idCarrera = 0;
		nombreCarrera = "";
		distancia = 0;
		provinciaCarrera = "";
		poblacionCarrera = "";
	}
	
	/**
	 * Getter de id corredor
	 * @return
	 */
	public int getIdCorredor() {
		return idCorredor;
	}
	
	/**
	 * Setter del id corredor
	 * @param idCorredor
	 */
	public void setIdCorredor(int idCorredor) {
		this.idCorredor = idCorredor;
	}
	
	/**
	 * Getter del nombre del corredor
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Setter del nombre de la distancia.
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Getter de apellido del corredor
	 * @return
	 */
	public String getApellidos() {
		return apellidos;
	}
	
	/**
	 * Setter de apellidos
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
	 * Getter de población
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
	 * Getter del id carrera
	 * @return
	 */
	public int getIdCarrera() {
		return idCarrera;
	}
	
	/**
	 * Setter del id carrera
	 * @param idCarrera
	 */
	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}
	
	/**
	 * Getter del nombre carrera
	 * @return
	 */
	public String getNombreCarrera() {
		return nombreCarrera;
	}
	
	/**
	 * Setter del nombre de carrera
	 * @param nombreCarrera
	 */
	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}
	
	/**
	 * Getter distancia
	 * @return
	 */
	public int getDistancia() {
		return distancia;
	}
	
	/**
	 * Setter de  distancia 
	 * @param distancia
	 */
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
	/**
	 * Getter de la provincia de la carrera
	 * @return
	 */
	public String getProvinciaCarrera() {
		return provinciaCarrera;
	}
	
	/**
	 * Setter de provincia carrera.
	 * @param provinciaCarrera
	 */
	public void setProvinciaCarrera(String provinciaCarrera) {
		this.provinciaCarrera = provinciaCarrera;
	}
	
	/**
	 * Getter de población de la carrera.
	 * @return
	 */
	public String getPoblacionCarrera() {
		return poblacionCarrera;
	}
	
	/**
	 * Setter de población carrera
	 * @param poblacionCarrera
	 */
	public void setPoblacionCarrera(String poblacionCarrera) {
		this.poblacionCarrera = poblacionCarrera;
	}
	
	
	
	
	
	
}
