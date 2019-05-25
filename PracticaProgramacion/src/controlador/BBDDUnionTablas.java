package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.CombinacionCarCor;
import modelo.Corredor;

public class BBDDUnionTablas {
	public static final String DB_NOMBRE = "db_running";
	public static final String TABLA_CORREDORES = "corredores";
	public static final String TABLA_CARRERAS = "carreras";
	private static final String TABLA_POBLACIONES = "poblaciones";
	private static final String TABLA_PROVINCIAS = "provincias";
	private static final String TABLA_INTERMEDIA = "corredoresycarreras";
	
	
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
	 * Función que carga un ArrayList de objetos carreras y corredores
	 * @return Devuelve un ArrayList de objetos carrerasYcorredores
	 */
	public ArrayList<CombinacionCarCor> mostrarTodo(){
		ArrayList<CombinacionCarCor> carCor = new ArrayList<>();
		
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CORREDORES + " cor JOIN " + TABLA_INTERMEDIA + " combi "
					+ "ON combi.idCorredor = cor.idCorredor JOIN " + TABLA_CARRERAS + " car ON combi.idCarrera = car.idCarrera;" );
			

			while(registro.next()) {
				CombinacionCarCor combinacion = new CombinacionCarCor();
				
				combinacion.setIdCorredor(registro.getInt(CORREDORES_ID));
				combinacion.setNombre(registro.getString(CORREDORES_NOMBRE));
				combinacion.setApellidos(registro.getString(CORREDORES_APELLIDOS));
				combinacion.setEdad(registro.getInt(CORREDORES_EDAD));
				combinacion.setProvincia(registro.getString(CORREDORES_PROVINCIA));
				combinacion.setPoblacion(registro.getString(CORREDORES_POBLACION));
				combinacion.setIdCarrera(registro.getInt(CARRERA_ID));
				combinacion.setNombreCarrera(registro.getString(10));
				combinacion.setDistancia(registro.getInt(CARRERA_DISTANCIA));
				combinacion.setProvinciaCarrera(registro.getString(12));
				combinacion.setPoblacionCarrera(registro.getString(13));
				
				carCor.add(combinacion);				
			}
			
			conexion.close();
			
			return carCor;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Función que consulta en la base de datos por nombre los objetos carreraYcorredor
	 * @param nombreCor Cadena de texto del nombre del corredor que se desea buscar.
	 * @return Devuelve un arraylist de objetos tipo carreraYcorredor.
	 */
	public ArrayList<CombinacionCarCor> consultaPorNombre(String nombreCor){
		ArrayList<CombinacionCarCor> combinacionArr = new ArrayList();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CORREDORES + " cor JOIN " + TABLA_INTERMEDIA + " combi "
					+ "ON combi.idCorredor = cor.idCorredor and cor.Nombre  LIKE '%" + nombreCor + "%' JOIN "  + TABLA_CARRERAS + " car ON combi.idCarrera = car.idCarrera;" );
			
			while(registro.next()) {
				CombinacionCarCor combinacion = new CombinacionCarCor();
				
				combinacion.setIdCorredor(registro.getInt(CORREDORES_ID));
				combinacion.setNombre(registro.getString(CORREDORES_NOMBRE));
				combinacion.setApellidos(registro.getString(CORREDORES_APELLIDOS));
				combinacion.setEdad(registro.getInt(CORREDORES_EDAD));
				combinacion.setProvincia(registro.getString(CORREDORES_PROVINCIA));
				combinacion.setPoblacion(registro.getString(CORREDORES_POBLACION));
				combinacion.setIdCarrera(registro.getInt(CARRERA_ID));
				combinacion.setNombreCarrera(registro.getString(10));
				combinacion.setDistancia(registro.getInt(CARRERA_DISTANCIA));
				combinacion.setProvinciaCarrera(registro.getString(12));
				combinacion.setPoblacionCarrera(registro.getString(13));
				
				combinacionArr.add(combinacion);		
			}
			
			conexion.close();
			
			return  combinacionArr;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Función que consulta los objetos tipo carreraYcorredor según el apellido del corredor
	 * @param apeCor Apellido 
	 * @return Devuelve un ArrayList de objetos tipo 
	 */
	public ArrayList<CombinacionCarCor> consultaPorApellido(String apeCor){
		ArrayList<CombinacionCarCor> combinacionArr = new ArrayList();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CORREDORES + " cor JOIN " + TABLA_INTERMEDIA + " combi "
					+ "ON combi.idCorredor = cor.idCorredor and cor.Apellidos  LIKE '%" + apeCor + "%' JOIN "  + TABLA_CARRERAS + " car ON combi.idCarrera = car.idCarrera;" );
			
			while(registro.next()) {
				CombinacionCarCor combinacion = new CombinacionCarCor();
				
				combinacion.setIdCorredor(registro.getInt(CORREDORES_ID));
				combinacion.setNombre(registro.getString(CORREDORES_NOMBRE));
				combinacion.setApellidos(registro.getString(CORREDORES_APELLIDOS));
				combinacion.setEdad(registro.getInt(CORREDORES_EDAD));
				combinacion.setProvincia(registro.getString(CORREDORES_PROVINCIA));
				combinacion.setPoblacion(registro.getString(CORREDORES_POBLACION));
				combinacion.setIdCarrera(registro.getInt(CARRERA_ID));
				combinacion.setNombreCarrera(registro.getString(10));
				combinacion.setDistancia(registro.getInt(CARRERA_DISTANCIA));
				combinacion.setProvinciaCarrera(registro.getString(12));
				combinacion.setPoblacionCarrera(registro.getString(13));
				
				combinacionArr.add(combinacion);		
			}
			
			conexion.close();
			
			return  combinacionArr;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * Función que realiza una consulta en la base de datos utilizando el parámetro edad del corredor.
	 * @param edad
	 * @return Devuelve un arraylist  de objetos carreraCorredor
	 */
	public ArrayList<CombinacionCarCor> consultaPorEdad(int edad){
		ArrayList<CombinacionCarCor> combinacionArr = new ArrayList();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CORREDORES + " cor JOIN " + TABLA_INTERMEDIA + " combi "
					+ "ON combi.idCorredor = cor.idCorredor and cor.Edad <= " + edad + " JOIN " +  TABLA_CARRERAS + " car ON combi.idCarrera = car.idCarrera;" );
			
			while(registro.next()) {
				CombinacionCarCor combinacion = new CombinacionCarCor();
				
				combinacion.setIdCorredor(registro.getInt(CORREDORES_ID));
				combinacion.setNombre(registro.getString(CORREDORES_NOMBRE));
				combinacion.setApellidos(registro.getString(CORREDORES_APELLIDOS));
				combinacion.setEdad(registro.getInt(CORREDORES_EDAD));
				combinacion.setProvincia(registro.getString(CORREDORES_PROVINCIA));
				combinacion.setPoblacion(registro.getString(CORREDORES_POBLACION));
				combinacion.setIdCarrera(registro.getInt(CARRERA_ID));
				combinacion.setNombreCarrera(registro.getString(10));
				combinacion.setDistancia(registro.getInt(CARRERA_DISTANCIA));
				combinacion.setProvinciaCarrera(registro.getString(12));
				combinacion.setPoblacionCarrera(registro.getString(13));
				
				combinacionArr.add(combinacion);		
			}
			
			conexion.close();
			
			return  combinacionArr;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Función que consulta en la base de datos la unión de carrera y corredores utilizando provincia como parámetro. 
	 * @param provincia String provincia.
	 * @return Devuelve un arraylist de objetos tipo carrera y corredor.
	 */
	public ArrayList<CombinacionCarCor> consultaPorProvincia(String provincia){
		ArrayList<CombinacionCarCor> combinacionArr = new ArrayList();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CORREDORES + " cor JOIN " + TABLA_INTERMEDIA + " combi "
					+ "ON combi.idCorredor = cor.idCorredor and cor.Provincia = '" + provincia + "' JOIN " +  TABLA_CARRERAS + " car ON combi.idCarrera = car.idCarrera;" );
			
			while(registro.next()) {
				CombinacionCarCor combinacion = new CombinacionCarCor();
				
				combinacion.setIdCorredor(registro.getInt(CORREDORES_ID));
				combinacion.setNombre(registro.getString(CORREDORES_NOMBRE));
				combinacion.setApellidos(registro.getString(CORREDORES_APELLIDOS));
				combinacion.setEdad(registro.getInt(CORREDORES_EDAD));
				combinacion.setProvincia(registro.getString(CORREDORES_PROVINCIA));
				combinacion.setPoblacion(registro.getString(CORREDORES_POBLACION));
				combinacion.setIdCarrera(registro.getInt(CARRERA_ID));
				combinacion.setNombreCarrera(registro.getString(10));
				combinacion.setDistancia(registro.getInt(CARRERA_DISTANCIA));
				combinacion.setProvinciaCarrera(registro.getString(12));
				combinacion.setPoblacionCarrera(registro.getString(13));
				
				combinacionArr.add(combinacion);		
			}
			
			conexion.close();
			
			return  combinacionArr;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Función que realiza una consulta de la unión de objetos carrera y corredor 
	 * @param poblacion Parámetro de población.
	 * @return Devuelve un arraylist de objetos carreraYcorredor.
	 */
	public ArrayList<CombinacionCarCor> consultaPorPoblacion(String poblacion){
		ArrayList<CombinacionCarCor> combinacionArr = new ArrayList();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CORREDORES + " cor JOIN " + TABLA_INTERMEDIA + " combi "
					+ "ON combi.idCorredor = cor.idCorredor and cor.Poblacion = '" + poblacion + "' JOIN " +  TABLA_CARRERAS + " car ON combi.idCarrera = car.idCarrera;" );
			
			while(registro.next()) {
				CombinacionCarCor combinacion = new CombinacionCarCor();
				
				combinacion.setIdCorredor(registro.getInt(CORREDORES_ID));
				combinacion.setNombre(registro.getString(CORREDORES_NOMBRE));
				combinacion.setApellidos(registro.getString(CORREDORES_APELLIDOS));
				combinacion.setEdad(registro.getInt(CORREDORES_EDAD));
				combinacion.setProvincia(registro.getString(CORREDORES_PROVINCIA));
				combinacion.setPoblacion(registro.getString(CORREDORES_POBLACION));
				combinacion.setIdCarrera(registro.getInt(CARRERA_ID));
				combinacion.setNombreCarrera(registro.getString(10));
				combinacion.setDistancia(registro.getInt(CARRERA_DISTANCIA));
				combinacion.setProvinciaCarrera(registro.getString(12));
				combinacion.setPoblacionCarrera(registro.getString(13));
				
				combinacionArr.add(combinacion);		
			}
			
			conexion.close();
			
			return  combinacionArr;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Función que realiza una consulta en la tabla unión carreras y corredores en función del nombre de la carrera. 
	 * @param nombreCar String del nombre de la carrera que deseamos consultar.
	 * @return Devuelve un arraylist de objetos de tipo carreraYcorredor
	 */
	public ArrayList<CombinacionCarCor> consultaCarreraPorNombre(String nombreCar){
		ArrayList<CombinacionCarCor> combinacionArr = new ArrayList();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CORREDORES + " cor JOIN " + TABLA_INTERMEDIA + " combi "
					+ "ON combi.idCorredor = cor.idCorredor  JOIN "  + TABLA_CARRERAS + " car ON combi.idCarrera = car.idCarrera and car.Nombre  LIKE '%" + nombreCar + "%';" );
			
			while(registro.next()) {
				CombinacionCarCor combinacion = new CombinacionCarCor();
				
				combinacion.setIdCorredor(registro.getInt(CORREDORES_ID));
				combinacion.setNombre(registro.getString(CORREDORES_NOMBRE));
				combinacion.setApellidos(registro.getString(CORREDORES_APELLIDOS));
				combinacion.setEdad(registro.getInt(CORREDORES_EDAD));
				combinacion.setProvincia(registro.getString(CORREDORES_PROVINCIA));
				combinacion.setPoblacion(registro.getString(CORREDORES_POBLACION));
				combinacion.setIdCarrera(registro.getInt(CARRERA_ID));
				combinacion.setNombreCarrera(registro.getString(10));
				combinacion.setDistancia(registro.getInt(CARRERA_DISTANCIA));
				combinacion.setProvinciaCarrera(registro.getString(12));
				combinacion.setPoblacionCarrera(registro.getString(13));
				
				combinacionArr.add(combinacion);		
			}
			
			conexion.close();
			
			return  combinacionArr;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Función que consulta en la tabla de unión de carrera y corredores utilizando el parámetro distancia. 
	 * @param dist Entero distancia.
	 * @return Devuelve un arraylist de objetos carreraYcorredor.
	 */
	public ArrayList<CombinacionCarCor> consultaPorDistancia(int dist){
		ArrayList<CombinacionCarCor> combinacionArr = new ArrayList();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CORREDORES + " cor JOIN " + TABLA_INTERMEDIA + " combi "
					+ "ON combi.idCorredor = cor.idCorredor  JOIN "  + TABLA_CARRERAS + " car ON combi.idCarrera = car.idCarrera and car.Distancia = " + dist + ";" );
			
			while(registro.next()) {
				CombinacionCarCor combinacion = new CombinacionCarCor();
				
				combinacion.setIdCorredor(registro.getInt(CORREDORES_ID));
				combinacion.setNombre(registro.getString(CORREDORES_NOMBRE));
				combinacion.setApellidos(registro.getString(CORREDORES_APELLIDOS));
				combinacion.setEdad(registro.getInt(CORREDORES_EDAD));
				combinacion.setProvincia(registro.getString(CORREDORES_PROVINCIA));
				combinacion.setPoblacion(registro.getString(CORREDORES_POBLACION));
				combinacion.setIdCarrera(registro.getInt(CARRERA_ID));
				combinacion.setNombreCarrera(registro.getString(10));
				combinacion.setDistancia(registro.getInt(CARRERA_DISTANCIA));
				combinacion.setProvinciaCarrera(registro.getString(12));
				combinacion.setPoblacionCarrera(registro.getString(13));
				
				combinacionArr.add(combinacion);		
			}
			
			conexion.close();
			
			return  combinacionArr;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Función que consulta en la tabla de unión corredoresycarreras en función de la provincia de la carrera
	 * @param provincia String de la provincia de la carrera
	 * @return Devuelve un arraylist de objetos carreraYcorredor.
	 */
	public ArrayList<CombinacionCarCor> consultaCarreraPorProvincia(String provincia){
		ArrayList<CombinacionCarCor> combinacionArr = new ArrayList();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CORREDORES + " cor JOIN " + TABLA_INTERMEDIA + " combi "
					+ "ON combi.idCorredor = cor.idCorredor  JOIN " +  TABLA_CARRERAS + " car ON combi.idCarrera = car.idCarrera and car.Provincia = '" + provincia + "';" );
			
			while(registro.next()) {
				CombinacionCarCor combinacion = new CombinacionCarCor();
				
				combinacion.setIdCorredor(registro.getInt(CORREDORES_ID));
				combinacion.setNombre(registro.getString(CORREDORES_NOMBRE));
				combinacion.setApellidos(registro.getString(CORREDORES_APELLIDOS));
				combinacion.setEdad(registro.getInt(CORREDORES_EDAD));
				combinacion.setProvincia(registro.getString(CORREDORES_PROVINCIA));
				combinacion.setPoblacion(registro.getString(CORREDORES_POBLACION));
				combinacion.setIdCarrera(registro.getInt(CARRERA_ID));
				combinacion.setNombreCarrera(registro.getString(10));
				combinacion.setDistancia(registro.getInt(CARRERA_DISTANCIA));
				combinacion.setProvinciaCarrera(registro.getString(12));
				combinacion.setPoblacionCarrera(registro.getString(13));
				
				combinacionArr.add(combinacion);		
			}
			
			conexion.close();
			
			return  combinacionArr;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Función que consulta en la tabla carreraYcorredores en función de la población de la carrera
	 * @param poblacion Población de la carrera
	 * @return Devuelve un arraylist de objetos tipo carreraCorredor. 
	 */
	public ArrayList<CombinacionCarCor> consultaCarreraPorPoblacion(String poblacion){
		ArrayList<CombinacionCarCor> combinacionArr = new ArrayList();
		
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CORREDORES + " cor JOIN " + TABLA_INTERMEDIA + " combi "
					+ "ON combi.idCorredor = cor.idCorredor JOIN " +  TABLA_CARRERAS + " car ON combi.idCarrera = car.idCarrera  and car.Poblacion = '" + poblacion + "';" );
			
			while(registro.next()) {
				CombinacionCarCor combinacion = new CombinacionCarCor();
				
				combinacion.setIdCorredor(registro.getInt(CORREDORES_ID));
				combinacion.setNombre(registro.getString(CORREDORES_NOMBRE));
				combinacion.setApellidos(registro.getString(CORREDORES_APELLIDOS));
				combinacion.setEdad(registro.getInt(CORREDORES_EDAD));
				combinacion.setProvincia(registro.getString(CORREDORES_PROVINCIA));
				combinacion.setPoblacion(registro.getString(CORREDORES_POBLACION));
				combinacion.setIdCarrera(registro.getInt(CARRERA_ID));
				combinacion.setNombreCarrera(registro.getString(10));
				combinacion.setDistancia(registro.getInt(CARRERA_DISTANCIA));
				combinacion.setProvinciaCarrera(registro.getString(12));
				combinacion.setPoblacionCarrera(registro.getString(13));
				
				combinacionArr.add(combinacion);		
			}
			
			conexion.close();
			
			return  combinacionArr;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Función que borra una participación de la base de datos
	 * @param idCorredor Id del corredor
	 * @param idCarrera Id de la carrera
	 */
	public void borrarParticipacion(int idCorredor, int idCarrera) {

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			Statement consulta = conexion.createStatement();
			int valor = consulta
					.executeUpdate("delete from corredoresycarreras  where idCorredor  = " + idCorredor + " and idCarrera = " + idCarrera );

			if (valor == 1) {
				Object[] options = { "OK" };
				int resultadoBorrado = JOptionPane.showOptionDialog(null, "Corredor borrado correctamente ",
						"Operación realizada con éxito", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null,
						options, options[0]);

			} else {
				Object[] options2 = { "OK" };
				int resultadoBorrado2 = JOptionPane.showOptionDialog(null,
						"Se ha producido un error. Inténtelo de nuevo", "Resultado", JOptionPane.PLAIN_MESSAGE,
						JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
			}

			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

}
