package vista;


import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import modelo.Carrera;
import vista.corredores.BorrarCor;

public class TablaPersonalizadaCarreras extends AbstractTableModel {

	public static final String[] COLUMN_NAMES = new String[] {"Id", "Nombre", "Distancia", "Provincia", "Poblacion"};
	
	
	
	
	private ArrayList<Carrera> carrera;
	

	
	public TablaPersonalizadaCarreras() {
		carrera = new ArrayList<Carrera>();
	}


	
	public void agregarCarreras(ArrayList<Carrera> p) {

		carrera = p;
	}
	
	public List<Carrera> getCarrera() {
		return carrera;
	}

    @Override
    public String getColumnName(int numCol) {
        return COLUMN_NAMES[numCol];
    }

	@Override
	public int getColumnCount() {
		
		return COLUMN_NAMES.length;
				
	}


	@Override
	public int getRowCount() {

		return carrera.size();
	}



	ImageIcon imageIcon1 = new ImageIcon("distancias/1.jpg"); 
	Image image = imageIcon1.getImage(); 
	Image dist1 = image.getScaledInstance(160, 25, Image.SCALE_SMOOTH);
	ImageIcon dist1_1 = new ImageIcon(dist1);
	
	ImageIcon imageIcon2 = new ImageIcon("distancias/2.jpg"); 
	Image image2 = imageIcon2.getImage(); 
	Image dist2 = image2.getScaledInstance(160, 25, Image.SCALE_SMOOTH);
	ImageIcon dist2_2 = new ImageIcon(dist2);
	
	ImageIcon imageIcon3 = new ImageIcon("distancias/3.jpg"); 
	Image image3 = imageIcon3.getImage(); 
	Image dist3 = image3.getScaledInstance(160, 25, Image.SCALE_SMOOTH);
	ImageIcon dist3_3 = new ImageIcon(dist3);
	
	ImageIcon imageIcon4 = new ImageIcon("distancias/4.jpg"); 
	Image image4 = imageIcon4.getImage(); 
	Image dist4 = image4.getScaledInstance(160, 25, Image.SCALE_SMOOTH);
	ImageIcon dist4_4 = new ImageIcon(dist4);
	
	ImageIcon imageIcon5 = new ImageIcon("distancias/5.jpg"); 
	Image image5 = imageIcon5.getImage(); 
	Image dist5 = image5.getScaledInstance(160, 25, Image.SCALE_SMOOTH);
	ImageIcon dist5_5 = new ImageIcon(dist5);
	
	ImageIcon imageIcon6 = new ImageIcon("distancias/6.jpg"); 
	Image image6 = imageIcon6.getImage(); 
	Image dist6 = image6.getScaledInstance(160, 25, Image.SCALE_SMOOTH);
	ImageIcon dist6_6 = new ImageIcon(dist6);
	
	
	
	
	
	
	@Override
	public Object getValueAt(int row, int column) {
		Carrera carreraObj = carrera.get(row);
		Object value = null;
		
		switch(column) {
			case 0: return value = carreraObj.getIdCarrera();
			case 1: return value = carreraObj.getNombre();
			case 2: 
				value = carreraObj.getDistancia();
				switch((int)value) {
				case 1:
					return dist1_1;
				case 2:
					return dist2_2;
				case 3:
					return dist3_3;
				case 4:
					return dist4_4;
				case 5: 
					return dist5_5;
				case 6:
					return dist6_6;
				default:
					return "";
				}

			case 3: return value = carreraObj.getProvincia();
			case 4: return value=  carreraObj.getPoblacion();
			
		}
		
		return value;
	}
	
	@Override
	public Class<?> getColumnClass(int col) {
	    if (col == 2) {
	        return ImageIcon.class;
	    } else {
	        return String.class;
	    }
	}
	
	
	
	public void removeRow(int row) {

		 carrera.remove(row); 
		 fireTableRowsDeleted(row, row);
	}
	
	public void updateRow(Carrera value, int row){
        carrera.set(row, value);
        fireTableRowsUpdated(row, row);
    }


	

}
