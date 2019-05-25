package controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import vista.TablaPersonalizadaCorredores;
import vista.TablaPersonalizadaUnion;

public class GestionaUnionTablas {
	
	
	
	/**
	 * Función que exporta un archivo html con la información de la tabla.
	 * @param modeloAbstracto
	 * @throws IOException
	 */
	public void exportarHTML(TablaPersonalizadaUnion modeloAbstracto) throws IOException {
		
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
        		" <th> Id Carrera </th> \r\n" +
         		"      <th>Nombre Carrera </th>\r\n" + 
        		"      <th>Distancia </th> \r\n" + 
        		"      <th>Provincia Carrera</th>\r\n" + 
        		" <th> Población carrera</th> \r\n" 

        		+"    </tr>";
        
        
        
        String infoTags = "";
        
    	for(int fila = 0; fila < modeloAbstracto.getRowCount(); fila++) {

    		infoTags += "<tr> <td> " + modeloAbstracto.getCorredores().get(fila).getIdCorredor() + " </td>";
    		infoTags += "<td> " + modeloAbstracto.getCorredores().get(fila).getNombre() + " </td> ";
    		infoTags += "<td> " + modeloAbstracto.getCorredores().get(fila).getApellidos() + " </td> ";
    		infoTags += "<td> " + modeloAbstracto.getCorredores().get(fila).getEdad() + " </td> ";
    		infoTags += "<td> " + modeloAbstracto.getCorredores().get(fila).getProvincia() + " </td> ";
    		infoTags += "<td> " + modeloAbstracto.getCorredores().get(fila).getPoblacion() + " </td> ";
    		infoTags += "<td> " + modeloAbstracto.getCorredores().get(fila).getIdCarrera() + " </td>  ";
    		infoTags += "<td> " + modeloAbstracto.getCorredores().get(fila).getNombreCarrera() + " </td> ";
    		infoTags += "<td> " + modeloAbstracto.getCorredores().get(fila).getDistancia() + " </td> ";
    		infoTags += "<td> " + modeloAbstracto.getCorredores().get(fila).getProvinciaCarrera() + "</td>  ";
    		infoTags += "<td> " + modeloAbstracto.getCorredores().get(fila).getPoblacionCarrera() + " </td> </tr> ";
    		
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
	 * Función que exporta un archivo excel con lo que se muestra por pantalla.
	 * @param modeloAbstracto El modelo de la tabla.
	 */
	public void exportarExcel(TablaPersonalizadaUnion modeloAbstracto) {
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
    		row.createCell(6).setCellValue(modeloAbstracto.getCorredores().get(fila).getIdCarrera());
    		row.createCell(7).setCellValue(modeloAbstracto.getCorredores().get(fila).getNombreCarrera());
    		row.createCell(8).setCellValue(modeloAbstracto.getCorredores().get(fila).getDistancia());
    		row.createCell(9).setCellValue(modeloAbstracto.getCorredores().get(fila).getProvinciaCarrera());
    		row.createCell(10).setCellValue(modeloAbstracto.getCorredores().get(fila).getPoblacionCarrera());
    		
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
	 * Función que exporta un archivo xml de la tabla.
	 * @param modeloAbstracto Modelo abstracto de la tabla.
	 * @throws IOException Excepción en caso de error.
	 */
	public void exportarXML(TablaPersonalizadaUnion modeloAbstracto) throws IOException {
		
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
	    
	    String firstTags = "<Participaciones> ";
        String infoTags = "";
        
    	for(int fila = 0; fila < modeloAbstracto.getRowCount(); fila++) {

    		infoTags += "\n   <Participacion>\n     <idCorredor> " + modeloAbstracto.getCorredores().get(fila).getIdCorredor() + " </idCorredor>";
    		infoTags += "\n     <nombreCorredor> " + modeloAbstracto.getCorredores().get(fila).getNombre() + " </nombreCorredor> ";
    		infoTags += "\n     <apellidosCorredor> " + modeloAbstracto.getCorredores().get(fila).getApellidos() + " </apellidosCorredor> ";
    		infoTags += "\n     <edad> " + modeloAbstracto.getCorredores().get(fila).getEdad() + " </edad> ";
    		infoTags += "\n     <provinciaCorredor> " + modeloAbstracto.getCorredores().get(fila).getProvincia() + " </provinciaCorredor> ";
    		infoTags += "\n     <poblacionCorredor> " + modeloAbstracto.getCorredores().get(fila).getPoblacion() + " </poblacionCorredor> ";
    		infoTags += "\n     <idCarrera> " + modeloAbstracto.getCorredores().get(fila).getIdCarrera() + " </idCarrera>  ";
    		infoTags += "\n     <nombreCarrera> " + modeloAbstracto.getCorredores().get(fila).getNombreCarrera() + " </nombreCarrera> ";
    		infoTags += "\n     <distancia> " + modeloAbstracto.getCorredores().get(fila).getDistancia() + " </distancia> ";
    		infoTags += "\n     <provinciaCarrera> " + modeloAbstracto.getCorredores().get(fila).getProvinciaCarrera() + "</provinciaCarrera>  ";
    		infoTags += "\n     <poblacionCarrera> " + modeloAbstracto.getCorredores().get(fila).getPoblacionCarrera() + " </poblacionCarrera> \n   </Participacion> ";
    		
    	}
    	
    	String finalTags = "\n</Participaciones>";


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
	public void exportarSQL(TablaPersonalizadaUnion modeloAbstracto) throws IOException {
		
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
    		infoTags += "INSERT INTO `corredoresycarreras`(`idCorredor`, `idCarrera`) VALUES (" + modeloAbstracto.getCorredores().get(fila).getIdCorredor() + ", " +  modeloAbstracto.getCorredores().get(fila).getIdCarrera() + ");\n";
    		
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
