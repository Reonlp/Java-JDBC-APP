package controlador;

import java.awt.Component;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import modelo.Carrera;
import modelo.Poblacion;
import vista.TablaPersonalizadaCarreras;
import vista.TablaPersonalizadaCorredores;
import vista.TablaPersonalizadaUnion;

public class GestionaCarreras {
	BBDDCarreras data = new BBDDCarreras();
	
	/**
	 * Función que recoge el valor del index del comboBox que muestra las imágenes de distancia y devuelve el valor adecuado establecido.
	 * @param cmbDist
	 * @return Devuelve un entero del valor distancia.
	 */
	public int valorDistancia(int cmbDist) {
		int valor = -1;
		switch(cmbDist) {
			case 0:
				valor = 1;
				break;
			case 1:
				valor = 2;
				break;
			case 2:
				valor = 3;
				break;
			case 3:
				valor = 4;
				break;
			case 4:
				valor = 5;
				break;
			case 5:
				valor = 6;
				break;
			default:
				valor = -1;
				break;
		}
		
		return valor;
	}
	
	/**
	 * Funcion que gestiona los errores a la hora de introducir una carrera. Dependiendo del error que se produzca devolverá un valor distinto
	 * que será traducido a un mensaje personalizado de error por la función mostrarAvisoError.
	 * @param nombre
	 * @param dist
	 * @param prov
	 * @param pob
	 * @return
	 */
	public int valoresParaInsertar(String nombre, int dist, String prov, String pob) {
		int resultado = 1;
		if(nombre.equals("")) {
			resultado = -1;
			return resultado;
		} else if(dist == -1) {
			resultado = -2;
			return resultado;
		} else if(prov.equals("")) {
			resultado = -3;
			return resultado;
		} else if(pob.equals("")) {
			resultado = -4;
			return resultado;
		}
		
		
		return resultado;
	}
	
	/**
	 * Función que recoge por parámetro el resultado de la función valoresParaInsertar y muestra un mensaje por pantalla indicado el error que 
	 * se ha producido
	 * @param resultado
	 */
	public void mostrarAvisoError(int resultado) {
		switch(resultado) {
			case -1:
				Object[] options = {"OK"};
				int resultadoConsulta = JOptionPane.showOptionDialog(null,
		                   "Es necesario indicar el nombre de la carrera","Resultado",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   options,
		                   options[0]);
				break;
						   
			case -2:
				Object[] options2 = {"OK"};
				int resultadoConsulta2 = JOptionPane.showOptionDialog(null,
		                   "Es necesario indicar la distancia de la carrera","Resultado",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   options2,
		                   options2[0]);
				break;
				
			case -3:
				Object[] options3 = {"OK"};
				int resultadoConsulta3 = JOptionPane.showOptionDialog(null,
		                   "Es necesario indicar la provincia de la carrera","Resultado",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   options3,
		                   options3[0]);
				break;
			
			case -4:
				Object[] options4 = {"OK"};
				int resultadoConsulta4 = JOptionPane.showOptionDialog(null,
		                   "Es necesario indicar la población","Resultado",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   options4,
		                   options4[0]);
				break;
		}
	}
	
	/**
	 * Función que recoge por parámetros la carrera que queremos modificar y devuelve un objeto con los  nuevos valores que serás introducidos
	 * posteriormente en la base de datos.
	 * @param p
	 * @param distancia
	 * @param provincia
	 * @return
	 */
	public Carrera valoresModificados(Carrera p, JComboBox distancia, JComboBox provincia) {
		Carrera carrera = new Carrera();
		String nombre = JOptionPane.showInputDialog(
				   null,
				   "Introduce el nuevo nombre de la carrera",
				   p.getNombre());
		


		//DISTANCIA

		 	int kilometros = JOptionPane.showOptionDialog(null, 
	        		distancia, 
	        		"Seleccione la distancia",
	        		JOptionPane.YES_NO_CANCEL_OPTION,
	        		JOptionPane.PLAIN_MESSAGE, null,    // null para icono por defecto.
					new Object[] { "Sí", "No"},   // null para YES, NO y CANCEL
					"No");
		 	
		 	int valorDist = -2;
		 	int valorElegido = 0;
		 	if(kilometros == JOptionPane.YES_OPTION) {
		 		valorDist = distancia.getSelectedIndex();
		 		valorElegido = valorDistancia(valorDist);
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
	        	 System.out.println(provinciaSeleccionada = provincia.getSelectedItem().toString());
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

		        carrera.setNombre(nombre);
		        carrera.setDistancia(valorElegido);
		        carrera.setProvincia(provinciaSeleccionada);
		        carrera.setPoblacion(poblacion);
		       
	        
		return carrera;
	}
	
	/**
	 * Función que nos sirve para modificar dinámicamente el modelo abstrácto de la tabla. 
	 * @param modeloAbstracto Modelo de la tabla que le pasamos como parámetro.
	 */
	public void modificarModeloAbstracto(TablaPersonalizadaCarreras modeloAbstracto) {
		for (int i = 0; i < modeloAbstracto.getRowCount(); i++) {
			modeloAbstracto.setValueAt(modeloAbstracto.getCarrera().get(i).getIdCarrera(), i, 0);
			modeloAbstracto.fireTableCellUpdated(i, 0);
			modeloAbstracto.setValueAt(modeloAbstracto.getCarrera().get(i).getNombre(), i, 1);
			modeloAbstracto.fireTableCellUpdated(i, 1);
			modeloAbstracto.setValueAt(modeloAbstracto.getCarrera().get(i).getDistancia(), i, 2);
			modeloAbstracto.fireTableCellUpdated(i, 2);
			modeloAbstracto.setValueAt(modeloAbstracto.getCarrera().get(i).getProvincia(), i, 3);
			modeloAbstracto.fireTableCellUpdated(i, 3);
			modeloAbstracto.setValueAt(modeloAbstracto.getCarrera().get(i).getPoblacion(), i, 4);
			modeloAbstracto.fireTableCellUpdated(i, 4);



		}
	}
	
	
	/**
	 * Función que exporta el modelo de la tabla en un fichero excel.
	 * @param modeloAbstracto Modelo de la tabla abstracta.
	 */
	public void exportarExcel(TablaPersonalizadaCarreras modeloAbstracto) {
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
    		row.createCell(0).setCellValue(modeloAbstracto.getCarrera().get(fila).getIdCarrera());
    		row.createCell(1).setCellValue(modeloAbstracto.getCarrera().get(fila).getNombre());
    		row.createCell(2).setCellValue(modeloAbstracto.getCarrera().get(fila).getDistancia());
    		row.createCell(3).setCellValue(modeloAbstracto.getCarrera().get(fila).getProvincia());
    		row.createCell(4).setCellValue(modeloAbstracto.getCarrera().get(fila).getPoblacion());
    		
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
	                   option[0]);
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
	 * Función que exporta un archivo html con la información de la tabla
	 * @param modeloAbstracto Modelo abstracto de la tabla
	 * @throws IOException Excepción en caso de error
	 */
	public void exportarHTML(TablaPersonalizadaCarreras modeloAbstracto) throws IOException {
		
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
        		+ "charset=UTF-8'/><title>Carreras</title>   <style>\r\n" + 
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
        		"      <th>Id Carrera</th>\r\n" + 
        		"      <th>Nombre</th> \r\n" + 
        		"      <th>Distancia</th>\r\n" + 
        		"      <th>Provincia</th> \r\n" + 
        		"      <th>Población</th>\r\n" + 
        		"    </tr>";
        
        
        
        String infoTags = "";
        
    	for(int fila = 0; fila < modeloAbstracto.getRowCount(); fila++) {

    		infoTags += "<tr> <td> " + modeloAbstracto.getCarrera().get(fila).getIdCarrera() + " </td>";
    		infoTags += "<td> " + modeloAbstracto.getCarrera().get(fila).getNombre() + " </td> ";
    		infoTags += "<td> " + modeloAbstracto.getCarrera().get(fila).getDistancia() + " </td> ";
    		infoTags += "<td> " + modeloAbstracto.getCarrera().get(fila).getProvincia() + " </td> ";
    		infoTags += "<td> " + modeloAbstracto.getCarrera().get(fila).getPoblacion() + " </td> </tr> ";
    		
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
	 * Función que exporta un archivo xml de la tabla.
	 * @param modeloAbstracto Modelo abstracto de la tabla
	 * @throws IOException Excepción en caso de error
	 */
	public void exportarXML(TablaPersonalizadaCarreras modeloAbstracto) throws IOException {
		
	    String fileDictName = "";
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Open the file"); //name for chooser
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Xml", ".xml"); //filter to show only that
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
	    
	    String firstTags = "<Carreras> ";
        String infoTags = "";
        
    	for(int fila = 0; fila < modeloAbstracto.getRowCount(); fila++) {

    		
    		infoTags += "\n   <Carrera>\n     <idCarrera> " + modeloAbstracto.getCarrera().get(fila).getIdCarrera() + " </idCarrera>  ";
    		infoTags += "\n     <nombreCarrera> " + modeloAbstracto.getCarrera().get(fila).getNombre() + " </nombreCarrera> ";
    		infoTags += "\n     <distancia> " + modeloAbstracto.getCarrera().get(fila).getDistancia() + " </distancia> ";
    		infoTags += "\n     <provinciaCarrera> " + modeloAbstracto.getCarrera().get(fila).getProvincia() + "</provinciaCarrera>  ";
    		infoTags += "\n     <poblacionCarrera> " + modeloAbstracto.getCarrera().get(fila).getPoblacion() + " </poblacionCarrera> \n   </Carrera> ";
    		
    	}
    	
    	String finalTags = "\n</Carreras>";


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
	 *Función que exporta un archivo sql de la tabla. 
	 * @param modeloAbstracto Modelo de la tabla.
	 * @throws IOException Excepción en caso de error.
	 */
	public void exportarSQL(TablaPersonalizadaCarreras modeloAbstracto) throws IOException {
		
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
			infoTags += "INSERT INTO `carreras`(`idCarrera`, `Nombre`, `Distancia`, `Provincia`, `Poblacion`) VALUES (" + "`"+  modeloAbstracto.getCarrera().get(fila).getIdCarrera() + "`" + ", `"+  modeloAbstracto.getCarrera().get(fila).getNombre() + "`, `" + modeloAbstracto.getCarrera().get(fila).getDistancia() + ", `" + modeloAbstracto.getCarrera().get(fila).getProvincia() + "`, `" + modeloAbstracto.getCarrera().get(fila).getPoblacion() + "`);\n";                                                  ;	
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


