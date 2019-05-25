package controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import modelo.Carrera;
import modelo.Corredor;
import modelo.Poblacion;
import vista.TablaPersonalizadaCorredores;
import vista.TablaPersonalizadaUnion;

public class GestionaCorredores {
	
	BBDDCorredores data = new BBDDCorredores();
	
	/**
	 * Función que controla los valores que insertamos y devuelve un número en función del error que se produzca.
	 * @param nombre Nombre del corredor
	 * @param apellidos Apellidos del corredor
	 * @param edad Edad del corredor
	 * @param provincia Provincia del corredor
	 * @param poblacion Población del corredor
	 * @return
	 */
	public int valoresParaInsertar(String nombre, String apellidos, int edad, String provincia, String poblacion) {
		int resultado = 1;
		if(nombre.equals("")) {
			resultado = -1;
			return resultado;
		} else if(apellidos.equals("")) {
			resultado = -2;
			return resultado;
		} else if(edad == 0) {
			resultado = -3;
			return resultado;
		} else if(provincia.equals("")) {
			resultado = -4;
			return resultado;
		} else if(poblacion.equals("")) {
			resultado = -5;
			return resultado;
		}
				
		return resultado;
	}
	
	/**
	 * Función que muestra un mensaje de error individualizado en función del resultado de la función valoresParaInsertar
	 * @param resultado Parámetro que le pasamos y que muestra un error. 
	 */
	public void mostrarAvisoError(int resultado) {
		switch(resultado) {
			case -1:
				Object[] options = {"OK"};
				int resultadoConsulta = JOptionPane.showOptionDialog(null,
		                   "Es necesario indicar el nombre del corredor","Resultado",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   options,
		                   options[0]);
				break;
						   
			case -2:
				Object[] options2 = {"OK"};
				int resultadoConsulta2 = JOptionPane.showOptionDialog(null,
		                   "Es necesario indicar los apellidos del corredor","Resultado",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   options2,
		                   options2[0]);
				break;
				
			case -3:
				Object[] options3 = {"OK"};
				int resultadoConsulta3 = JOptionPane.showOptionDialog(null,
		                   "Es necesario indicar la edad del corredor","Resultado",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   options3,
		                   options3[0]);
				break;
			
			case -4:
				Object[] options4 = {"OK"};
				int resultadoConsulta4 = JOptionPane.showOptionDialog(null,
		                   "Es necesario indicar la provincia del corredor","Resultado",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   options4,
		                   options4[0]);
				break;
			case -5:
				Object[] options5 = {"OK"};
				int resultadoConsulta5 = JOptionPane.showOptionDialog(null,
		                   "Es necesario indicar la poblacion del corredor","Resultado",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   options5,
		                   options5[0]);
				break;
		}
	}
	
	/**
	 * Función que actualiza el modelo abstracto de la tabla.
	 * @param modeloAbstracto Modelo abstracto de la tabla.
	 */
	public void modificarModeloAbstracto(TablaPersonalizadaCorredores modeloAbstracto) {
		for (int i = 0; i < modeloAbstracto.getRowCount(); i++) {
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getIdCorredor(), i, 0);
			modeloAbstracto.fireTableCellUpdated(i, 0);
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getNombre(), i, 1);
			modeloAbstracto.fireTableCellUpdated(i, 1);
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getApellidos(), i, 2);
			modeloAbstracto.fireTableCellUpdated(i, 2);
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getEdad(), i, 3);
			modeloAbstracto.fireTableCellUpdated(i, 3);
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getProvincia(), i, 4);
			modeloAbstracto.fireTableCellUpdated(i, 4);
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getPoblacion(), i, 5);
			modeloAbstracto.fireTableCellUpdated(i, 5);

		}
	}
	
	/**
	 * Función que actualiza el modelo abstracto de la tabla unión corredores y carreras.
	 * @param modeloAbstracto Parámetro del modelo abstracto.
	 */
	public void modificarModeloAbstractoUnion(TablaPersonalizadaUnion modeloAbstracto) {
		for (int i = 0; i < modeloAbstracto.getRowCount(); i++) {
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getIdCorredor(), i, 0);
			modeloAbstracto.fireTableCellUpdated(i, 0);
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getNombre(), i, 1);
			modeloAbstracto.fireTableCellUpdated(i, 1);
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getApellidos(), i, 2);
			modeloAbstracto.fireTableCellUpdated(i, 2);
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getEdad(), i, 3);
			modeloAbstracto.fireTableCellUpdated(i, 3);
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getProvincia(), i, 4);
			modeloAbstracto.fireTableCellUpdated(i, 4);
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getPoblacion(), i, 5);
			modeloAbstracto.fireTableCellUpdated(i, 5);
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getIdCarrera(), i, 6);
			modeloAbstracto.fireTableCellUpdated(i, 6);
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getNombreCarrera(), i, 7);
			modeloAbstracto.fireTableCellUpdated(i, 7);
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getDistancia(), i, 8);
			modeloAbstracto.fireTableCellUpdated(i, 8);
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getProvinciaCarrera(), i, 9);
			modeloAbstracto.fireTableCellUpdated(i, 9);
			modeloAbstracto.setValueAt(modeloAbstracto.getCorredores().get(i).getPoblacionCarrera(), i, 10);
			modeloAbstracto.fireTableCellUpdated(i, 10);

		}
	}
	
	/**
	 * Función que modifica la información de un corredor mediante paneles.
	 * @param p Objeto corredor.
	 * @param spinner Spinner para la edad.
 	 * @param provincia Combobox dela provincia.
	 * @return Devuelve un objeto tipo carrera 
	 */
	public Corredor valoresModificados(Corredor p, JSpinner spinner, JComboBox provincia) {
		Corredor corredor = new Corredor();
		int id = p.getIdCorredor();
		String nombre = JOptionPane.showInputDialog(
				   null,
				   "Introduce el nuevo nombre del corredor",
				   p.getNombre());
		
		String apellidos = JOptionPane.showInputDialog(
				   null,
				   "Introduce el nuevo nombre del corredor",
				   p.getApellidos());
		


		//DISTANCIA

		 	int edad = JOptionPane.showOptionDialog(null, 
	        		spinner, 
	        		"Seleccione la edad",
	        		JOptionPane.YES_NO_CANCEL_OPTION,
	        		JOptionPane.PLAIN_MESSAGE, null,    // null para icono por defecto.
					new Object[] { "Sí", "No"},   // null para YES, NO y CANCEL
					"No");
		 	
		
		 	int valorEdad = 0;
		 	if(edad == JOptionPane.YES_OPTION) {
		 		valorEdad = (int) spinner.getValue();
		 	}

	     
		/********************************/

	       
	        int reply = JOptionPane.showOptionDialog(null, 
	        		provincia, 
	        		"Seleccione la provincia",
	        		JOptionPane.YES_NO_CANCEL_OPTION,
	        		JOptionPane.PLAIN_MESSAGE, null,    // null para icono por defecto.
					new Object[] { "Sí", "No"},   // null para YES, NO y CANCEL
					"No");


	        String provinciaSeleccionada = "";
	        if(reply == JOptionPane.YES_OPTION) {
	        	provinciaSeleccionada = provincia.getSelectedItem().toString();
	        }
	        
	        
	   
	     
	    /*============JLIST CON POBLACIONES============*/ 
	     JList lstPobAgrCar = new JList<>();

			DefaultListModel model = new DefaultListModel();
			ArrayList<Poblacion> poblaciones = data.consultarPoblacionesPorProvincia(provinciaSeleccionada);
			for(int i = 0; i < poblaciones.size(); i++) {
				model.addElement(poblaciones.get(i).getNombre());
			}
					
			lstPobAgrCar.setModel(model);	
								
			JScrollPane scrollPane_1 = new JScrollPane();	
			scrollPane_1.setViewportView(lstPobAgrCar);
			
		    String poblacionSeleccionada = "";
	        int pueblos = JOptionPane.showOptionDialog(null, 
	        		scrollPane_1, 
	        		"Seleccione la provincia",
	        		JOptionPane.YES_NO_CANCEL_OPTION,
	        		JOptionPane.PLAIN_MESSAGE, null,    // null para icono por defecto.
					new Object[] { "Sí", "No"},   // null para YES, NO y CANCEL
					"No");
	       
	        String poblacion = "";
	        if(pueblos == JOptionPane.YES_OPTION) {
	        	poblacion = lstPobAgrCar.getSelectedValue().toString();
	        }
	        
		 		corredor.setIdCorredor(id);
		        corredor.setNombre(nombre);
		        corredor.setApellidos(apellidos);
		        corredor.setEdad(valorEdad);
		        corredor.setProvincia(provinciaSeleccionada);
		        corredor.setPoblacion(poblacion);
		       
	        
		return corredor;
	}
	
	/**
	 * Función que exporta el archivo html con la información de la tabla.
	 * @param modeloAbstracto Parámetro del modelo abstracto de la tabla.
	 * @throws IOException Excepción que se produce en caso de que se produzca un fallo.
	 */
	public void exportarHTML(TablaPersonalizadaCorredores modeloAbstracto) throws IOException {
		
	    String fileDictName = "";
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Open the file"); //name for chooser
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Files", ".html"); //filter to show only that
	    fileChooser.setAcceptAllFileFilterUsed(false); //to show or not all other files
	    fileChooser.addChoosableFileFilter(filter);
	    fileChooser.setSelectedFile(new File(fileDictName)); //when you want to show the name of file into the chooser
	    fileChooser.setVisible(true);
	    int result = fileChooser.showSaveDialog(fileChooser);
	    if (result == JFileChooser.APPROVE_OPTION) {
	           fileDictName = fileChooser.getSelectedFile().getAbsolutePath()  + ".html";
	    } else {
	         return;
	    }
	    BufferedWriter bw = new BufferedWriter(new FileWriter(fileDictName));
	    	
		 
        String firstTags = "<!DOCTYPE html><html lang='es'> <head><meta http-equiv='Content-Type' content='text/html; "
        		+ "charset=UTF-8'/><title>New Page</title>   <style>\r\n" + 
        		"    table, th, td {\r\n" + 
        		"  border: 1px solid black;\r\n" + 
        		"}\r\n" + 
        		"\r\n" + 
        		"td {\r\n" + 
        		"  text-align: center;\r\n" + 
        		"}\r\n" + 
        		"\r\n" + 
        		"tr:hover {\r\n" + 
        		"  background-color: black;\r\n" + 
        		"  color: white;\r\n" + 
        		"}\r\n" + 
        		"  </style></head> <body> "
        		+ "<table style=\"width:100%\">\r\n" + 
        		"    <tr>\r\n" + 
        		"      <th>Id Corredor</th>\r\n" + 
        		"      <th>Nombre</th> \r\n" + 
        		"      <th>Apellidos</th>\r\n" + 
        		"      <th>Edad</th>\r\n" + 
        		"      <th>Provincia</th> \r\n" + 
        		"      <th>Población</th>\r\n" + 
        		"    </tr>";
        
        
        
        String infoTags = "";
        
    	for(int fila = 0; fila < modeloAbstracto.getRowCount(); fila++) {

    		infoTags += "<tr> <td> " + modeloAbstracto.getCorredores().get(fila).getIdCorredor() + " </td>";
    		infoTags += "<td> " + modeloAbstracto.getCorredores().get(fila).getNombre() + " </td> ";
    		infoTags += "<td> " + modeloAbstracto.getCorredores().get(fila).getApellidos() + " </td> ";
    		infoTags += "<td> " + modeloAbstracto.getCorredores().get(fila).getEdad() + " </td> ";
    		infoTags += "<td> " + modeloAbstracto.getCorredores().get(fila).getProvincia() + " </td> ";
    		infoTags += "<td> " + modeloAbstracto.getCorredores().get(fila).getPoblacion() + " </td> </tr> ";
    		
    	}
    	
    	String finalTags = "</table> </body> </html>";


         try {
			bw.write(firstTags + infoTags + finalTags);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
     
	}
	
	
	/**
	 * Función que exporta un archivo excel con la información de la table.
	 * @param modeloAbstracto Parámetro del modelo abstracto de la tabla.
	 */
	public void exportarExcel(TablaPersonalizadaCorredores modeloAbstracto) {
    	HSSFWorkbook wb = new HSSFWorkbook();
    	HSSFSheet sheet = wb.createSheet("Excel Sheet");
    	HSSFRow rowhead = sheet.createRow(0);
    	String fileDictName = "";
    	 JFileChooser fileChooser = new JFileChooser();
         fileChooser.setDialogTitle("Open the file"); //name for chooser
         FileNameExtensionFilter filter = new FileNameExtensionFilter("Files", ".xlsx"); //filter to show only that
         fileChooser.setAcceptAllFileFilterUsed(false); //to show or not all other files
         fileChooser.addChoosableFileFilter(filter);
         fileChooser.setSelectedFile(new File(fileDictName)); //when you want to show the name of file into the chooser
         fileChooser.setVisible(true);
         int result = fileChooser.showSaveDialog(fileChooser);
         if (result == JFileChooser.APPROVE_OPTION) {
             fileDictName = fileChooser.getSelectedFile().getAbsolutePath();
         } else {
             return;
         }
    	
         File file = new File(fileDictName + ".xls");
    	
         
    	//Titulos columnas
    	for (int col = 0; col < modeloAbstracto.getColumnCount(); col++) {
    	    String columnName = modeloAbstracto.getColumnName(col);
    	    rowhead.createCell(col).setCellValue(columnName);
    	}
    	
    	//Contenido tabla
    	int fila = 0;
    	
    	for(fila = 0; fila < modeloAbstracto.getRowCount(); fila++) {
    		HSSFRow row = sheet.createRow(fila + 1);
    		row.createCell(0).setCellValue(modeloAbstracto.getCorredores().get(fila).getIdCorredor());
    		row.createCell(1).setCellValue(modeloAbstracto.getCorredores().get(fila).getNombre());
    		row.createCell(2).setCellValue(modeloAbstracto.getCorredores().get(fila).getApellidos());
    		row.createCell(3).setCellValue(modeloAbstracto.getCorredores().get(fila).getEdad());
    		row.createCell(4).setCellValue(modeloAbstracto.getCorredores().get(fila).getProvincia());
    		row.createCell(5).setCellValue(modeloAbstracto.getCorredores().get(fila).getPoblacion());
    		
    	}
    	
    	
    	try (
    			
    		FileOutputStream fileOut = new FileOutputStream(file)) {

    		wb.write(fileOut);
			Object[] option = {"OK"};
			int resultadoConsulta = JOptionPane.showOptionDialog(null,
	                   "Archivo guardado con éxito","Resultado",
	                   JOptionPane.PLAIN_MESSAGE,
	                   JOptionPane.QUESTION_MESSAGE,
	                   null,
	                   option,
	                   option[0]);

    	} catch (FileNotFoundException e) {
    		Object[] option = {"OK"};
			int resultadoConsulta = JOptionPane.showOptionDialog(null,
	                   "Se ha producido un error","Resultado",
	                   JOptionPane.PLAIN_MESSAGE,
	                   JOptionPane.QUESTION_MESSAGE,
	                   null,
	                   option,
	                   option[0]);;
		} catch (IOException e) {
			Object[] option = {"OK"};
			int resultadoConsulta = JOptionPane.showOptionDialog(null,
	                   "Se ha producido un error","Resultado",
	                   JOptionPane.PLAIN_MESSAGE,
	                   JOptionPane.QUESTION_MESSAGE,
	                   null,
	                   option,
	                   option[0]);
		}
	}
	
	/**
	 * Función que exporta un archivo xml de la tabla.
	 * @param modeloAbstracto Modelo de la tabla.
	 * @throws IOException Excepción en caso de error.
	 */
	public void exportarXML(TablaPersonalizadaCorredores modeloAbstracto) throws IOException {
		
	    String fileDictName = "";
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Open the file"); //name for chooser
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("xml", ".xml"); //filter to show only that
	    fileChooser.setAcceptAllFileFilterUsed(false); //to show or not all other files
	    fileChooser.addChoosableFileFilter(filter);
	    fileChooser.setSelectedFile(new File(fileDictName)); //when you want to show the name of file into the chooser
	    fileChooser.setVisible(true);
	    int result = fileChooser.showSaveDialog(fileChooser);
	    if (result == JFileChooser.APPROVE_OPTION) {
	           fileDictName = fileChooser.getSelectedFile().getAbsolutePath()  + ".xml";
	    } else {
	         return;
	    }
	    BufferedWriter bw = new BufferedWriter(new FileWriter(fileDictName));
	    	
		 
	    String firstTags = "<Corredores> ";
        String infoTags = "";
    	for(int fila = 0; fila < modeloAbstracto.getRowCount(); fila++) {

    		infoTags += "\n   <Corredor>\n     <idCorredor> " + modeloAbstracto.getCorredores().get(fila).getIdCorredor() + " </idCorredor>";
    		infoTags += "\n     <nombreCorredor> " + modeloAbstracto.getCorredores().get(fila).getNombre() + " </nombreCorredor> ";
    		infoTags += "\n     <apellidosCorredor> " + modeloAbstracto.getCorredores().get(fila).getApellidos() + " </apellidosCorredor> ";
    		infoTags += "\n     <edad> " + modeloAbstracto.getCorredores().get(fila).getEdad() + " </edad> ";
    		infoTags += "\n     <provinciaCorredor> " + modeloAbstracto.getCorredores().get(fila).getProvincia() + " </provinciaCorredor> ";
    		infoTags += "\n     <poblacionCorredor> " + modeloAbstracto.getCorredores().get(fila).getPoblacion() + " </poblacionCorredor>  \n   </Corredor> ";

    		
    	}
    	
    	String finalTags = "\n</Corredores>";


         try {
			bw.write(firstTags + infoTags + finalTags);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
     
	}

	/**
	 * Función que exporta un archivo sql de la tabla.
	 * @param modeloAbstracto Modelo de la tabla.
	 * @throws IOException Excepción en caso de error.
	 */
	public void exportarSQL(TablaPersonalizadaCorredores modeloAbstracto) throws IOException {
	
	    String fileDictName = "";
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Open the file"); //name for chooser
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("sql", ".sql"); //filter to show only that
	    fileChooser.setAcceptAllFileFilterUsed(false); //to show or not all other files
	    fileChooser.addChoosableFileFilter(filter);
	    fileChooser.setSelectedFile(new File(fileDictName)); //when you want to show the name of file into the chooser
	    fileChooser.setVisible(true);
	    int result = fileChooser.showSaveDialog(fileChooser);
	    if (result == JFileChooser.APPROVE_OPTION) {
	           fileDictName = fileChooser.getSelectedFile().getAbsolutePath()  + ".sql";
	    } else {
	         return;
	    }
	    BufferedWriter bw = new BufferedWriter(new FileWriter(fileDictName));
	    	
	
	    String infoTags = "";
		for(int fila = 0; fila < modeloAbstracto.getRowCount(); fila++) {
			infoTags += "INSERT INTO `corredores`(`idCorredor`, `Nombre`, `Apellidos`, `Edad`, `Provincia`, `Poblacion`) VALUES (" + "`"+  modeloAbstracto.getCorredores().get(fila).getIdCorredor() + "`" + ", `"+  modeloAbstracto.getCorredores().get(fila).getNombre() + "`, `" + modeloAbstracto.getCorredores().get(fila).getApellidos() + "`, " + modeloAbstracto.getCorredores().get(fila).getEdad() + ", `" + modeloAbstracto.getCorredores().get(fila).getProvincia() + "`, `" + modeloAbstracto.getCorredores().get(fila).getPoblacion() + "`);\n";                                                  ;	
		}
	
	
	     try {
			bw.write(infoTags);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
 
	}
	
}
