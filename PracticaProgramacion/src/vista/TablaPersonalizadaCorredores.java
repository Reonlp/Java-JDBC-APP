package vista;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelo.Carrera;
import modelo.Corredor;

public class TablaPersonalizadaCorredores extends AbstractTableModel{
	public static final String[] COLUMN_NAMES = new String[] {"Id", "Nombre", "Apellidos", "Edad", "Provincia", "Población"};
	
	private ArrayList<Corredor> corredores;
	
	public TablaPersonalizadaCorredores() {
		corredores = new ArrayList<Corredor>();
	}
	
	public void agregarCorredores(ArrayList<Corredor> p) {

		corredores = p;
	}
	
	public List<Corredor> getCorredores() {
		return corredores;
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
		return corredores.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Corredor carreraObj = corredores.get(row);
		Object value = null;
		
		switch(column) {
			case 0: return value = carreraObj.getIdCorredor();
			case 1: return value = carreraObj.getNombre();
			case 2: return value = carreraObj.getApellidos();
			case 3: return value = carreraObj.getEdad();
			case 4: return value = carreraObj.getProvincia();
			case 5: return value = carreraObj.getPoblacion();
			
		}
		
		return value;
	}
	
	public void removeRow(int row) {

		 corredores.remove(row); 
		 fireTableRowsDeleted(row, row);
	}

}
