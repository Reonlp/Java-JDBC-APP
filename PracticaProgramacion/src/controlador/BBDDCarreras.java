package controlador;


import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import modelo.Carrera;
import modelo.Poblacion;
import modelo.Provincia;

public class BBDDCarreras {
	public static final String DB_NOMBRE = "db_running";
	public static final String TABLA_CORREDORES = "corredores";
	public static final String TABLA_CARRERAS = "carreras";
	private static final String TABLA_POBLACIONES = "poblaciones";
	private static final String TABLA_PROVINCIAS = "provincias";
	
	public static final String CORREDORES_ID = "idCorredor";
	public static final String CORREDORES_NOMBRE = "Nombre";
	public static final String CORREDORES_APELLIDOS = "Apellidos";
	public static final String CORREDORES_EDAD = "Edad";
	public static final String CORREDORES_PROVINCIA = "Provincia";
	public static final String CORREDORES_POBLACION = "Poblacion";
	public static final String CORREDORES_ID_CARRERA = "idCarrera";
	
	public static final String CARRERA_ID = "idCarrera";
	public static final String CARRERA_NOMBRE = "Nombre";
	public static final String CARRERA_DISTANCIA = "Distancia";
	public static final String CARRERA_PROVINCIA = "Provincia";
	public static final String CARRERA_POBLACION = "Poblacion";
	

	private static final String PROV_COL_ID = "CodProvincia";
	private static final String PROV_COL_NAME = "Provincia";
	
	private static final String POB_COL_ID = "CodPoblacion";
	private static final String POB_COL_NAME = "NombPoblacion";
	
	
	/**
	 * Función que comprueba el estado de la conexión a la base de datos
	 */
	public void conexionDB() {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			Object[] option = {"OK"};
			int resultadoConsulta = JOptionPane.showOptionDialog(null,
	                   "Conexión a la base de datos realizada con éxito ","Conexión",
	                   JOptionPane.PLAIN_MESSAGE,
	                   JOptionPane.QUESTION_MESSAGE,
	                   null,
	                   option,
	                   option[0]);

		} catch(SQLException e) {
			Object[] option = {"OK"};
			int resultadoConsulta = JOptionPane.showOptionDialog(null,
	                   "Error en la conexión a la base de datos","Conexión",
	                   JOptionPane.PLAIN_MESSAGE,
	                   JOptionPane.QUESTION_MESSAGE,
	                   null,
	                   option,
	                   option[0]);
		}
	}
	
	/**
	 * Función que devuelve un arrayList con todas las carreras que se encuentran en la base de datos.
	 * @return ArrayList de objetos tipo carrera.
	 */
	public ArrayList<Carrera> mostrarCarreras(){
		ArrayList<Carrera> carreras = new ArrayList<>();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CARRERAS + ";");
			while(registro.next()) {
				Carrera carrera = new Carrera();
				
				carrera.setIdCarrera(registro.getInt(CARRERA_ID));
				carrera.setNombre(registro.getString(CARRERA_NOMBRE));
				carrera.setDistancia(registro.getInt(CARRERA_DISTANCIA));
				carrera.setProvincia(registro.getString(CARRERA_PROVINCIA));
				carrera.setPoblacion(registro.getString(CARRERA_POBLACION));
				
				carreras.add(carrera);				
			}
			
			conexion.close();
			
			return carreras;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Función que carga un ArrayList con todas las provincias de España
	 * @return Devuelve un ArrayList de objetos tipo provincia.
	 */
	public ArrayList<Provincia> mostrarTodasLasProvincias(){
		ArrayList<Provincia> todasLasProvincias = new ArrayList<>();

		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_PROVINCIAS + ";");
			while(registro.next()) {
				Provincia provincia = new Provincia();
				

				provincia.setCod(registro.getInt(PROV_COL_ID));
				provincia.setName(registro.getString(PROV_COL_NAME));
				
				todasLasProvincias.add(provincia);	
			}
			
			conexion.close();
			
			return todasLasProvincias;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Función que carga un ArrayList con todas las poblaciones de la provincia indicada por parámetro.
	 * @param nombre Nombre de la provincia cuyas poblaciones queremos obtener
	 * @return Devuelve un ArrayList de objetos población.
	 */
	public ArrayList<Poblacion> consultarPoblacionesPorProvincia(String nombre) {
		ArrayList<Poblacion> poblacionArray = new ArrayList<>();

		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_POBLACIONES + " WHERE " +
														PROV_COL_ID + " IN (SELECT " + PROV_COL_ID + " FROM " + TABLA_PROVINCIAS 
														+ " WHERE " +  PROV_COL_NAME + " = '" + nombre + "');");
			while(registro.next()) {
				Poblacion poblacion = new Poblacion();
				
				poblacion.setCodPob(registro.getInt(POB_COL_ID));
				poblacion.setCodProv(registro.getInt(PROV_COL_ID));
				poblacion.setNombre(registro.getString(POB_COL_NAME));
				
				poblacionArray.add(poblacion);	
			}
			
			conexion.close();
			
			return poblacionArray;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Función que carga un ArrayList con todas las carreras de la base de datos.
	 * @return Devuelve un ArrayList de objetos tipo Carrera
	 */
	public ArrayList<Carrera> consultarTodasLasCarreras() {
		ArrayList<Carrera> carreraArray = new ArrayList<>();

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CARRERAS + ";");
			while(registro.next()) {
				Carrera carrera = new Carrera();
				
				carrera.setIdCarrera(registro.getInt(CARRERA_ID));
				carrera.setNombre(registro.getString(CARRERA_NOMBRE));
				carrera.setDistancia(registro.getInt(CARRERA_DISTANCIA));
				carrera.setProvincia(registro.getString(CARRERA_PROVINCIA));
				carrera.setPoblacion(registro.getString(CARRERA_POBLACION));
				
				carreraArray.add(carrera);	
			}
			
			conexion.close();
			
			return carreraArray;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Función que se conecta a la base de datos y carga un ArrayList con las carreras cuyo nombre coincida con la cadena que se le pase por parámetros
	 * @param nombre Nombre de la carrera que queremos consultar
	 * @return  Devuelve un ArrayList de objetos tipo Carrera
	 */
	public ArrayList<Carrera> consultaPorNombre(String nombre){
		ArrayList<Carrera> carreras = new ArrayList();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CARRERAS + " WHERE " + CARRERA_NOMBRE + " LIKE " + 
					"'%" + nombre + "%'" + ";");
			while(registro.next()) {
				Carrera carrera = new Carrera();
				
				carrera.setIdCarrera(registro.getInt(CARRERA_ID));
				carrera.setNombre(registro.getString(CARRERA_NOMBRE));
				carrera.setDistancia(registro.getInt(CARRERA_DISTANCIA));
				carrera.setProvincia(registro.getString(CARRERA_PROVINCIA));
				carrera.setPoblacion(registro.getString(CARRERA_POBLACION));
				
				carreras.add(carrera);	
			}
			
			conexion.close();
			
			return carreras;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Función que se conecta a la base de datos y carga un ArrayList de carreras que tengan lugar en la provincia marcada por parámetros
	 * @param provincia Cadena de texto de la provincia cuyas carreras queremos consultar.
	 * @return Devuelve un ArrayList de objetos tipo carrera
	 */
	public ArrayList<Carrera> mostrarCarreraPorProvincia(String provincia){
		ArrayList<Carrera> carrerasProvincias = new ArrayList<Carrera>();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CARRERAS + " WHERE " + CARRERA_PROVINCIA + " = '" + provincia + "';");
			
			while(registro.next()){
				Carrera carrera =  new Carrera();
					carrera.setIdCarrera(registro.getInt(CARRERA_ID));
					carrera.setNombre(registro.getString(CARRERA_NOMBRE));
					carrera.setDistancia(registro.getInt(CARRERA_DISTANCIA));
					carrera.setProvincia(registro.getString(CARRERA_PROVINCIA));
					carrera.setPoblacion(registro.getString(CARRERA_POBLACION));
				
				carrerasProvincias.add(carrera);
				
			}
			
			conexion.close();
			
			return carrerasProvincias;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Función que se conecta a la base de datos y carga un ArrayList basándose en la distancia de la carrera que hemos pasado por parámetros.
	 * @param distancia Distancia de la carrera que queremos consultar
	 * @return Devuelve un arraylist de objetos tipo carrera
	 */
	public ArrayList<Carrera> mostrarCarrerasPorDistancia(int distancia){
		ArrayList<Carrera> carrerasDistancia = new ArrayList<Carrera>();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CARRERAS + " WHERE " + CARRERA_DISTANCIA + " = '" + distancia + "';");
			
			while(registro.next()){
				Carrera carrera =  new Carrera();
					carrera.setIdCarrera(registro.getInt(CARRERA_ID));
					carrera.setNombre(registro.getString(CARRERA_NOMBRE));
					carrera.setDistancia(registro.getInt(CARRERA_DISTANCIA));
					carrera.setProvincia(registro.getString(CARRERA_PROVINCIA));
					carrera.setPoblacion(registro.getString(CARRERA_POBLACION));
				
				carrerasDistancia.add(carrera);
				
			}
			
			conexion.close();
			
			return carrerasDistancia;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	
	/**
	 * Función que se conecta a la base de datos y consulta las carreras en función de la población que le pasamos por parámetro.
	 * @param nombre Cadena de texto de la población cuyas carreras deseamos consultar.
	 * @return Devuelve un arraylist de objetos tipo carrera.
	 */
	public ArrayList<Carrera> consultarCarrerasPorPoblacionTxt(String nombre){
		ArrayList<Carrera> carreras = new ArrayList();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CARRERAS + " WHERE " + CARRERA_POBLACION + " LIKE " + 
					"'%" + nombre + "%'" + ";");
			while(registro.next()) {
				Carrera carrera = new Carrera();
				
				carrera.setIdCarrera(registro.getInt(CARRERA_ID));
				carrera.setNombre(registro.getString(CARRERA_NOMBRE));
				carrera.setDistancia(registro.getInt(CARRERA_DISTANCIA));
				carrera.setProvincia(registro.getString(CARRERA_PROVINCIA));
				carrera.setPoblacion(registro.getString(CARRERA_POBLACION));
				
				carreras.add(carrera);	
			}
			
			conexion.close();
			
			return carreras;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * Función que inserta una carrera en la base de datos.
	 * @param nombre Nombre de la carrera que deseamos insertar.
	 * @param distancia Distancia de la carrera que deseamos insertar.
	 * @param provincia Provincia de la carrera que deseamos insertar.
	 * @param poblacion Población de la carrera que deseamos insertar.
	 */
	public void insertarDatos(String nombre, int distancia, String provincia, String poblacion) {
			
			Carrera carrera = new Carrera();
	
			carrera.setNombre(nombre);
			carrera.setDistancia(distancia);
			carrera.setProvincia(provincia);
			carrera.setPoblacion(poblacion);
		
			
			try {
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
				Statement consulta = conexion.createStatement();
				consulta.executeUpdate("Insert into " + TABLA_CARRERAS + " (Nombre, Distancia, Provincia, Poblacion) values ('" + carrera.getNombre() + "',  '" + carrera.getDistancia()  + "',  '" + carrera.getProvincia() + "', '" + carrera.getPoblacion() +"')");
				conexion.close();
				Object[] options3 = {"OK"};
				int resultadoConsulta = JOptionPane.showOptionDialog(null,
		                   "Carreda insertada con éxito","Resultado",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   options3,
		                   options3[0]);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	/**
	 * Función que consulta una carrera por su id.
	 * @param id Id de la carrera que deseamos consultar.
	 * @return Devuelve un objeto de tipo carrera.
	 */
	public Carrera consultaPorId(int id) {
		Carrera carrera = new Carrera();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CARRERAS + " WHERE " + CARRERA_ID + " = " + id + ";");
			if(registro.next()) {
				carrera.setIdCarrera(registro.getInt(CARRERA_ID));
				carrera.setNombre(registro.getString(CARRERA_NOMBRE));
				carrera.setDistancia(registro.getInt(CARRERA_DISTANCIA));
				carrera.setProvincia(registro.getString(CARRERA_PROVINCIA));
				carrera.setPoblacion(registro.getString(CARRERA_POBLACION));
			}	
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return carrera;
		
	}
	
	/**
	 * Función que actualiza una carrera previamente insertada.
	 * @param id Id de la carrerra.
	 * @param nombre Nombre de la carrera.
	 * @param distancia Distancia de la carrera.
	 * @param provincia Provincia de la carrera.
	 * @param poblacion Población de la carrera.
	 */
	public void actualizarDatos(int id, String nombre, int distancia, String provincia, String poblacion) {

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			Statement consulta = conexion.createStatement();
			int valor = consulta.executeUpdate("update " + TABLA_CARRERAS + " set " + CARRERA_NOMBRE + " = '" + nombre + "', " + CARRERA_DISTANCIA + " = '" + distancia + 
					"', " + CARRERA_PROVINCIA  + " = '" + provincia + "', " + CARRERA_POBLACION + " = '" + poblacion + "' where " + CARRERA_ID + " = " + id );
			
			if(valor==1) {
				Object[] options00 = {"OK"};
				int resultadoConsulta = JOptionPane.showOptionDialog(null,
		                   "La carrera ha sido modificada con éxito " ,"Resultado",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   options00,
		                   options00[0]);
			} else {
				Object[] options11 = {"OK"};
				int resultadoConsulta2 = JOptionPane.showOptionDialog(null,
		                   "Se ha producido un error. " ,"Resultado",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   options11,
		                   options11[0]);
				
			}
			conexion.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Función que borra una carrera de la base de datos
	 * @param id Id de la carrera que deseamos borrar
	 */
	public void borrarCarrera(int id) {
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			Statement consulta = conexion.createStatement();
			int valor = consulta.executeUpdate("delete from " + TABLA_CARRERAS + " where " + CARRERA_ID + " = " + id);
			
			if(valor==1) {
				 Object[] options = {"OK"};
				int resultadoBorrado = JOptionPane.showOptionDialog(null,
		                   "Artículo borrado correctamente ","Operación realizada con éxito",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   options,
		                   options[0]);
				
			} else {
				Object[] options2 = {"OK"};
				int resultadoBorrado2 = JOptionPane.showOptionDialog(null,
		                   "Se ha producido un error. Inténtelo de nuevo","Resultado",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   options2,
		                   options2[0]);
			}
			
			conexion.close();
		} catch(SQLException e ) {
			e.printStackTrace();
		}
		
		
	}
}

