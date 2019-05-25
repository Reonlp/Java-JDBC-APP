package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Carrera;
import modelo.Corredor;
import modelo.Poblacion;
import modelo.Provincia;

public class BBDDCorredores {
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
	 * Función que carga un ArrayList con todas las provincias de España.
	 * @return Devuelve un ArrayList objetos tipo provincia.
	 */
	public ArrayList<Provincia> mostrarTodasLasProvincias() {
		ArrayList<Provincia> todasLasProvincias = new ArrayList<>();

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");

			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_PROVINCIAS + ";");
			while (registro.next()) {
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
	 * Función que consulta las poblaciones de una determinada provincia.
	 * @param nombre Provincia cuyas poblaciones deseamos consultar.
	 * @return Devuelve un ArrayList de objetos tipo población.
	 */
	public ArrayList<Poblacion> consultarPoblacionesPorProvincia(String nombre) {
		ArrayList<Poblacion> poblacionArray = new ArrayList<>();

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");

			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery(
					"SELECT * FROM " + TABLA_POBLACIONES + " WHERE " + PROV_COL_ID + " IN (SELECT " + PROV_COL_ID
							+ " FROM " + TABLA_PROVINCIAS + " WHERE " + PROV_COL_NAME + " = '" + nombre + "');");
			while (registro.next()) {
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
	 * Función que carga un ArrayList con todos los corredores de la base de datos.
	 * @return Devuelve un ArrayList de objetos tipo corredor.
	 */
	public ArrayList<Corredor> mostrarTodosCorredores() {
		ArrayList<Corredor> corredores = new ArrayList<>();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");

			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CORREDORES + ";");
			while (registro.next()) {
				Corredor corredor = new Corredor();

				corredor.setIdCorredor(registro.getInt(CORREDORES_ID));
				corredor.setNombre(registro.getString(CORREDORES_NOMBRE));
				corredor.setApellidos(registro.getString(CORREDORES_APELLIDOS));
				corredor.setEdad(registro.getInt(CORREDORES_EDAD));
				corredor.setProvincia(registro.getString(CORREDORES_PROVINCIA));
				corredor.setPoblacion(registro.getString(CORREDORES_POBLACION));

				corredores.add(corredor);
			}

			conexion.close();

			return corredores;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Función que consulta los corredores de una determinada población.
	 * @param nombre Población cuyos corredores deseamos consultar.
	 * @return Devuelve un ArrayList de objetos tipo corredor
	 */
	public ArrayList<Corredor> consultarCarrerasPorPoblacionTxt(String nombre) {
		ArrayList<Corredor> corredoresPob = new ArrayList<>();

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");

			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CORREDORES + " WHERE "
					+ CORREDORES_POBLACION + " LIKE " + "'%" + nombre + "%'" + ";");
			while (registro.next()) {
				Corredor corredor = new Corredor();

				corredor.setIdCorredor(registro.getInt(CORREDORES_ID));
				corredor.setNombre(registro.getString(CORREDORES_NOMBRE));
				corredor.setApellidos(registro.getString(CORREDORES_APELLIDOS));
				corredor.setEdad(registro.getInt(CORREDORES_EDAD));
				corredor.setProvincia(registro.getString(CORREDORES_PROVINCIA));
				corredor.setPoblacion(registro.getString(CORREDORES_POBLACION));

				corredoresPob.add(corredor);
			}

			conexion.close();

			return corredoresPob;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Función que muestra los corredores de una determinada provincia.
	 * @param provincia Provincia cuyos corredores deseamos consultar
	 * @return Devuelve un ArrayList de objetos tipo corredor
	 */
	public ArrayList<Corredor> mostrarCarreraPorProvincia(String provincia) {
		ArrayList<Corredor> carrerasProvincias = new ArrayList<Corredor>();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");

			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery(
					"SELECT * FROM " + TABLA_CORREDORES + " WHERE " + CORREDORES_PROVINCIA + " = '" + provincia + "';");

			while (registro.next()) {
				Corredor corredor = new Corredor();

				corredor.setIdCorredor(registro.getInt(CORREDORES_ID));
				corredor.setNombre(registro.getString(CORREDORES_NOMBRE));
				corredor.setApellidos(registro.getString(CORREDORES_APELLIDOS));
				corredor.setEdad(registro.getInt(CORREDORES_EDAD));
				corredor.setProvincia(registro.getString(CORREDORES_PROVINCIA));
				corredor.setPoblacion(registro.getString(CORREDORES_POBLACION));

				carrerasProvincias.add(corredor);

			}

			conexion.close();

			return carrerasProvincias;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Función que consulta los corredores a partir de su nombre.
	 * @param nombre Nombre del corredor que deseamos consultar.
	 * @return Devuelve un ArrayList de objetos tipo corredor
	 */
	public ArrayList<Corredor> consultaPorNombre(String nombre) {
		ArrayList<Corredor> corredores = new ArrayList();

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");

			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("SELECT * FROM " + TABLA_CORREDORES + " WHERE "
					+ CORREDORES_NOMBRE + " LIKE " + "'%" + nombre + "%'" + ";");
			while (registro.next()) {
				Corredor corredor = new Corredor();

				corredor.setIdCorredor(registro.getInt(CORREDORES_ID));
				corredor.setNombre(registro.getString(CORREDORES_NOMBRE));
				corredor.setApellidos(registro.getString(CORREDORES_APELLIDOS));
				corredor.setEdad(registro.getInt(CORREDORES_EDAD));
				corredor.setProvincia(registro.getString(CORREDORES_PROVINCIA));
				corredor.setPoblacion(registro.getString(CORREDORES_POBLACION));

				corredores.add(corredor);
			}

			conexion.close();

			return corredores;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Función que consulta los corredores a partir de la edad.
	 * @param edad Edad del corredor.
	 * @return Devuelve un ArrayList de objetos tipo corredor.
	 */
	public ArrayList<Corredor> consultaPorEdad(int edad) {
		ArrayList<Corredor> corredores = new ArrayList();

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");

			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery(
					"SELECT * FROM " + TABLA_CORREDORES + " WHERE " + CORREDORES_EDAD + " <= " + edad + ";");
			while (registro.next()) {
				Corredor corredor = new Corredor();

				corredor.setIdCorredor(registro.getInt(CORREDORES_ID));
				corredor.setNombre(registro.getString(CORREDORES_NOMBRE));
				corredor.setApellidos(registro.getString(CORREDORES_APELLIDOS));
				corredor.setEdad(registro.getInt(CORREDORES_EDAD));
				corredor.setProvincia(registro.getString(CORREDORES_PROVINCIA));
				corredor.setPoblacion(registro.getString(CORREDORES_POBLACION));

				corredores.add(corredor);
			}

			conexion.close();

			return corredores;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}


	/**
	 * Función que inserta un nuevo corredor en la base de datos.
	 * @param nombre Nombre del corredor.
	 * @param apellidos Apellidos del corredor.
	 * @param edad Edad del corredor.
	 * @param provincia Provincia del corredor.
	 * @param poblacion Población del corredor.
	 */
	public void insertarCorredorSinCarrera(String nombre, String apellidos, int edad, String provincia,
			String poblacion) {

		Corredor corredor = new Corredor();

		corredor.setNombre(nombre);
		corredor.setApellidos(apellidos);
		corredor.setEdad(edad);
		corredor.setProvincia(provincia);
		corredor.setPoblacion(poblacion);

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");

			Statement consulta = conexion.createStatement();
			consulta.executeUpdate(
					"Insert into " + TABLA_CORREDORES + " (Nombre, Apellidos, Edad, Provincia, Poblacion) values ('"
							+ corredor.getNombre() + "',  '" + corredor.getApellidos() + "',  '" + corredor.getEdad()
							+ "', '" + corredor.getProvincia() + "',  '" + corredor.getPoblacion() + "')");

			Object[] options3 = { "OK" };
			int resultadoConsulta = JOptionPane.showOptionDialog(null, "Carreda insertada con éxito", "Resultado",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Función que inserta un corredor unido a una carrera.
	 * @param nombre Nombre del corredor.
	 * @param apellidos Apellidos del corredor.
	 * @param edad Edad del corredor.
	 * @param provincia Provincia del corredor.
	 * @param poblacion Población del corredor.
	 * @param idCarrera Id e la carrera.
	 */
	public void insertarDatos(String nombre, String apellidos, int edad, String provincia, String poblacion,
			int idCarrera) {

		Corredor corredor = new Corredor();

		corredor.setNombre(nombre);
		corredor.setApellidos(apellidos);
		corredor.setEdad(edad);
		corredor.setProvincia(provincia);
		corredor.setPoblacion(poblacion);

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");

			Statement consulta = conexion.createStatement();
			consulta.executeUpdate(
					"Insert into " + TABLA_CORREDORES + " (Nombre, Apellidos, Edad, Provincia, Poblacion) values ('"
							+ corredor.getNombre() + "',  '" + corredor.getApellidos() + "',  '" + corredor.getEdad()
							+ "', '" + corredor.getProvincia() + "',  '" + corredor.getPoblacion() + "')");

			Object[] options3 = { "OK" };
			int resultadoConsulta = JOptionPane.showOptionDialog(null, "Carreda insertada con éxito", "Resultado",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]);

			ResultSet registro = consulta.executeQuery("SELECT LAST_INSERT_ID()");
			int last_inserted_id = 0;
			if (registro.next()) {
				last_inserted_id = registro.getInt(1);

			}

			insertarIdTablaIntermedia(idCarrera, last_inserted_id);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Función que inserta una unión entre corredor y carrera en la base de datos.
	 * @param idCarrera Id de la carrera.
	 * @param idCorredor Id del corredor.
	 */
	public void insertarIdTablaIntermedia(int idCarrera, int idCorredor) {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			Statement consulta = conexion.createStatement();
			consulta.executeUpdate("Insert into " + TABLA_INTERMEDIA + " (idCorredor, idCarrera) values ('" + idCorredor
					+ "',  '" + idCarrera + "')");
			conexion.close();
			Object[] options3 = { "OK" };
			int resultadoConsulta = JOptionPane.showOptionDialog(null, "Operación realizada con éxito", "Resultado",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]);
		}  catch (SQLIntegrityConstraintViolationException e) {
			Object[] options3 = { "OK" };
			int resultadoConsulta = JOptionPane.showOptionDialog(null, "Ya existe esta participación", "Resultado",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]);
	    } catch (SQLException e) {
	
			Object[] options3 = { "OK" };
			int resultadoConsulta = JOptionPane.showOptionDialog(null, "Se ha producido un error", "Resultado",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]);
		} 

	}

	/**
	 * Función que consulta un corredor por su Id
	 * @param id Id del corredor
	 * @return Devuelve un objeto de tipo corredor.
	 */
	public Corredor consultaPorId(int id) {
		Corredor corredor = new Corredor();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");

			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta
					.executeQuery("SELECT * FROM " + TABLA_CORREDORES + " WHERE " + CORREDORES_ID + " = " + id + ";");
			if (registro.next()) {
				corredor.setIdCorredor(registro.getInt(CORREDORES_ID));
				corredor.setNombre(registro.getString(CORREDORES_NOMBRE));
				corredor.setApellidos(registro.getString(CORREDORES_APELLIDOS));
				corredor.setEdad(registro.getInt(CORREDORES_EDAD));
				corredor.setProvincia(registro.getString(CORREDORES_PROVINCIA));
				corredor.setPoblacion(registro.getString(CORREDORES_POBLACION));

			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return corredor;

	}
	
	/**
	 * Función que borra un corredor de la base de datos.
	 * @param id Id del corredor.
	 */
	public void borrarCarrera(int id) {

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			Statement consulta = conexion.createStatement();
			int valor = consulta
					.executeUpdate("delete from " + TABLA_CORREDORES + " where " + CORREDORES_ID + " = " + id);

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
	
	/**
	 * Función que actualiza los datos de un corredor.
	 * @param idCorredor Id del corredor.
	 * @param nombre Nombre del corredor.
	 * @param apellidos Apellidos del corredor.
	 * @param edad Edad del corredor.
	 * @param provincia Provincia del corredor.
	 * @param poblacion Población del corredor.
	 */
	public void actualizarDatos(int idCorredor, String nombre, String apellidos, int edad, String provincia,
			String poblacion) {

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NOMBRE, "curso2019", "curso2019");
			Statement consulta = conexion.createStatement();
			int valor = consulta.executeUpdate("update " + TABLA_CORREDORES + " set " + CORREDORES_NOMBRE + " = '"
					+ nombre + "', " + CORREDORES_APELLIDOS + " = '" + apellidos + "', " + CORREDORES_EDAD + " = '"
					+ edad + "', " + CORREDORES_PROVINCIA + " = '" + provincia + "', " + CORREDORES_POBLACION + " = '"
					+ poblacion + "' where " + CORREDORES_ID + " = " + idCorredor);

			if (valor == 1) {
				Object[] options00 = { "OK" };
				int resultadoConsulta = JOptionPane.showOptionDialog(null, "El corredor ha sido modificado con éxito ",
						"Resultado", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options00,
						options00[0]);
			} else {
				Object[] options11 = { "OK" };
				int resultadoConsulta2 = JOptionPane.showOptionDialog(null, "Se ha producido un error. ", "Resultado",
						JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options11, options11[0]);

			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
