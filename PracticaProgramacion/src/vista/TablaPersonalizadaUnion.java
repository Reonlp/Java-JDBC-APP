package vista;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import modelo.CombinacionCarCor;
import modelo.Corredor;

public class TablaPersonalizadaUnion  extends AbstractTableModel{
public static final String[] COLUMN_NAMES = new String[] {"Id", "Nombre", "Apellidos", "Edad", "Provincia", "Población", "IdCarrera",
		"NombreCarrera", "Distancia", "ProvinciaCarrera", "PoblacionCarrera"};
	
	private ArrayList<CombinacionCarCor> unionCarCor;
	
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
	
	
	
	public TablaPersonalizadaUnion() {
		unionCarCor = new ArrayList<CombinacionCarCor>();
	}
	
	public void agregarCorredores(ArrayList<CombinacionCarCor> p) {

		unionCarCor = p;
	}
	
	public List<CombinacionCarCor> getCorredores() {
		return  unionCarCor;
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
		return  unionCarCor.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		CombinacionCarCor carreraObj =  unionCarCor.get(row);
		Object value = null;
		
		switch(column) {
			case 0: return value = carreraObj.getIdCorredor();
			case 1: return value = carreraObj.getNombre();
			case 2: return value = carreraObj.getApellidos();
			case 3: return value = carreraObj.getEdad();
			case 4: return value = carreraObj.getProvincia();
			case 5: return value = carreraObj.getPoblacion();
			case 6: return value = carreraObj.getIdCarrera();
			case 7: return value = carreraObj.getNombreCarrera();
			case 8:	
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
					
				
			case 9: return value = carreraObj.getProvinciaCarrera();
			case 10: return value = carreraObj.getPoblacionCarrera();
			
		}
		
		return value;
	}
	
	@Override
	public Class<?> getColumnClass(int col) {
	    if (col == 8) {
	        return ImageIcon.class;
	    } else {
	        return String.class;
	    }
	}
	
	public void removeRow(int row) {

		 unionCarCor.remove(row); 
		 fireTableRowsDeleted(row, row);
	}
}
