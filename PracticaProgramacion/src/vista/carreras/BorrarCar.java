package vista.carreras;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.BBDDCarreras;
import controlador.GestionaCarreras;
import modelo.Carrera;
import modelo.Poblacion;
import modelo.Provincia;
import net.miginfocom.swing.MigLayout;
import vista.TablaPersonalizadaCarreras;

public class BorrarCar extends JPanel {
	private JTextField txtConsultar;
	private JTable table;
	private JTextField txtConsultarPob;

	/**
	 * Create the panel.
	 */
	public BorrarCar() {
		GestionaCarreras gestion = new GestionaCarreras();
		BBDDCarreras data = new BBDDCarreras();
		setBorder(new TitledBorder(null, "CONSULTAR CARRERAS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[318.00,grow][grow,left]", "[][][grow]"));
		
		/*IMAGENES PARA JAR*/
		BorrarCar.class.getResource("1.jpg");
		BorrarCar.class.getResource("2.jpg");
		BorrarCar.class.getResource("3.jpg");
		BorrarCar.class.getResource("4.jpg");
		BorrarCar.class.getResource("5.jpg");
		BorrarCar.class.getResource("6.jpg");
		
		txtConsultar = new JTextField();
		add(txtConsultar, "cell 0 0,growx");
		txtConsultar.setColumns(10);
		
		JButton btnMostrarTodo = new JButton("Mostrar todo");
		add(btnMostrarTodo, "cell 1 0");
		
		JLabel lblHazClickSobre = new JLabel("Haz click sobre la carrera que desee borrar");
		add(lblHazClickSobre, "cell 0 1");
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 2,grow");
		panel.setLayout(new MigLayout("", "[grow][]", "[grow][]"));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 0,grow");
			
		table = new JTable();

		
		//Función que pone las filas pares en un color y las impares en otro
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        c.setBackground(row % 2 == 0 ? Color.getHSBColor(18, 2, 200) : Color.WHITE);
		        return this;
		    }
		});
		

		
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 1 2,grow");
		panel_1.setLayout(new MigLayout("", "[grow]", "[][46.00][20.00][44.00][][][grow]"));
		
		JLabel lblDistancia = new JLabel("Distancia:");
		panel_1.add(lblDistancia, "cell 0 0");
		
		
		
		
		TablaPersonalizadaCarreras abstracto = new TablaPersonalizadaCarreras();
		
		JComboBox cmbDist = new JComboBox();
		
		/*==============CONSULTAR POR DISTANCIA==============*/
		cmbDist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Carrera> carrera = new ArrayList();
				int comboBoxValue = cmbDist.getSelectedIndex() + 1;
				carrera = data.mostrarCarrerasPorDistancia(comboBoxValue);
				
				
				abstracto.agregarCarreras(carrera);

				gestion.modificarModeloAbstracto(abstracto);

				table.revalidate();
				table.repaint();

				table.setModel(abstracto);
				
				
				
			}
		});
		
		
		cmbDist.setMaximumSize(new Dimension(270, 60));
		
		
		ImageIcon imageIcon1 = new ImageIcon("distancias/1.jpg"); 
		Image image = imageIcon1.getImage(); 
		Image dist1 = image.getScaledInstance(270, 60, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon2 = new ImageIcon("distancias/2.jpg"); 
		Image image2 = imageIcon2.getImage(); 
		Image dist2 = image2.getScaledInstance(270, 60, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon3 = new ImageIcon("distancias/3.jpg"); 
		Image image3 = imageIcon3.getImage(); 
		Image dist3 = image3.getScaledInstance(270, 60, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon4 = new ImageIcon("distancias/4.jpg"); 
		Image image4 = imageIcon4.getImage(); 
		Image dist4 = image4.getScaledInstance(270, 60, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon5 = new ImageIcon("distancias/5.jpg"); 
		Image image5 = imageIcon5.getImage(); 
		Image dist5 = image5.getScaledInstance(270, 60, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon6 = new ImageIcon("distancias/6.jpg"); 
		Image image6 = imageIcon6.getImage(); 
		Image dist6 = image6.getScaledInstance(270, 60, Image.SCALE_SMOOTH);
		
		DefaultComboBoxModel<Icon> cm = new DefaultComboBoxModel<Icon>();

		cm.addElement(new ImageIcon(dist1));
		cm.addElement(new ImageIcon(dist2));
		cm.addElement(new ImageIcon(dist3));
		cm.addElement(new ImageIcon(dist4));
		cm.addElement(new ImageIcon(dist5));
		cm.addElement(new ImageIcon(dist6));
		
		
		
		cmbDist.setModel(cm);

		
		panel_1.add(cmbDist, "cell 0 1,grow");
		
		JLabel lblProvincia = new JLabel("Provincia:");
		panel_1.add(lblProvincia, "cell 0 2");
		
		JComboBox cmbProvAgrCar = new JComboBox();
		panel_1.add(cmbProvAgrCar, "cell 0 3,growx,aligny center");
		
		/*=================ACCION QUE MUESTRA TODAS LAS CARRERAS AL PRESIONAR UN BOTÓN ===========================*/

	
		btnMostrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ArrayList<Carrera> todasLasCarreras = data.consultarTodasLasCarreras();
				abstracto.agregarCarreras(todasLasCarreras);
				
				
				for(int i = 0; i < abstracto.getRowCount(); i++) {
					abstracto.setValueAt(abstracto.getCarrera().get(i).getIdCarrera(), i, 0);
					abstracto.fireTableCellUpdated(i, 0);
					abstracto.setValueAt(abstracto.getCarrera().get(i).getNombre(), i, 1);
					abstracto.fireTableCellUpdated(i, 1);
					abstracto.setValueAt(abstracto.getCarrera().get(i).getDistancia(), i, 2);
					abstracto.fireTableCellUpdated(i, 2);
					abstracto.setValueAt(abstracto.getCarrera().get(i).getProvincia(), i, 3);
					abstracto.fireTableCellUpdated(i, 3);
					abstracto.setValueAt(abstracto.getCarrera().get(i).getPoblacion(), i, 4);
					abstracto.fireTableCellUpdated(i, 4);
				}
				
				
				table.revalidate();
				table.repaint();
				
				table.setModel(abstracto);
		
			}
		});
		
		/*==============AGREGA LAS PROVINCIAS AL COMBO BOX=========================*/
		ArrayList<Provincia> prov = data.mostrarTodasLasProvincias();
		for(int i = 0; i < prov.size(); i++) {
			cmbProvAgrCar.addItem(prov.get(i).getName());

		}
		
		JLabel lblPoblacin = new JLabel("Poblaci\u00F3n");
		panel_1.add(lblPoblacin, "cell 0 4");
		
		txtConsultarPob = new JTextField();
		panel_1.add(txtConsultarPob, "cell 0 5,growx");
		txtConsultarPob.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, "cell 0 6,grow");
		
		
		

		
		/*============REALIZA UNA CONSULTA A TRAVÉS DE LAS PROVINCIAS =================*/
		cmbProvAgrCar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						/*--------AÑADE LA BUSQUEDA DE LA BASE DE DATOS A LA TABLA-----*/
						String provincia = cmbProvAgrCar.getSelectedItem().toString();
						ArrayList<Carrera> carreraProvincia = data.mostrarCarreraPorProvincia(provincia);
						abstracto.agregarCarreras(carreraProvincia);
						
						if(carreraProvincia.isEmpty()) {
							Object[] options3 = {"OK"};
							int resultadoConsulta = JOptionPane.showOptionDialog(null,
					                   "No se ha encontrado ningún resultado","Resultado",
					                   JOptionPane.PLAIN_MESSAGE,
					                   JOptionPane.QUESTION_MESSAGE,
					                   null,
					                   options3,
					                   options3[0]);
							//int contador = table.getRowCount();
							/*for(int i = contador - 1 ; i >= 0; i--) {
								model.removeRow(i);
							} */
							
						
						} else {
						
							for(int i = 0; i < abstracto.getRowCount(); i++) {
								abstracto.setValueAt(abstracto.getCarrera().get(i).getIdCarrera(), i, 0);
								abstracto.fireTableCellUpdated(i, 0);
								abstracto.setValueAt(abstracto.getCarrera().get(i).getNombre(), i, 1);
								abstracto.fireTableCellUpdated(i, 1);
								abstracto.setValueAt(abstracto.getCarrera().get(i).getDistancia(), i, 2);
								abstracto.fireTableCellUpdated(i, 2);
								abstracto.setValueAt(abstracto.getCarrera().get(i).getProvincia(), i, 3);
								abstracto.fireTableCellUpdated(i, 3);
								abstracto.setValueAt(abstracto.getCarrera().get(i).getPoblacion(), i, 4);
								abstracto.fireTableCellUpdated(i, 4);
							}
							
							table.revalidate();
							table.repaint();
							table.setModel(abstracto);
							//abstracto.fireTableDataChanged();
							
							/*=======================LISTA DE POBLACIONES QUE SON AGREGADAS DINAMICAMENTE AL ELEGIR LA PROVINCIA EN EL ANTERIOR COMBO BOX======*/
							
		
								JList lstPobAgrCar = new JList<>();
								DefaultListModel model = new DefaultListModel();
								String nombre = cmbProvAgrCar.getSelectedItem().toString();
								ArrayList<Poblacion> poblaciones = data.consultarPoblacionesPorProvincia(nombre);

								
								
									for(int i = 0; i < poblaciones.size(); i++) {
										model.addElement(poblaciones.get(i).getNombre());
										
									}
									
									lstPobAgrCar.setModel(model);	
									
									
										
										
									scrollPane_1.setViewportView(lstPobAgrCar);
						

								
								lstPobAgrCar.addListSelectionListener(new ListSelectionListener() {
									
									@Override
									public void valueChanged(ListSelectionEvent arg0) {
								
										if (!arg0.getValueIsAdjusting()) {

											int pob =  lstPobAgrCar.getSelectedIndex();
											String poblacion = poblaciones.get(pob).getNombre();
											txtConsultarPob.setText(poblacion);
										}
										
									}
									
								});
						}
						

						
						
						

						
					}
				});
		
		
		/*=======================Realiza una consulta a través del textfield de poblaciones ===============================*/
		
		txtConsultarPob.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
			
			String nombre = txtConsultarPob.getText();
			//DefaultTableModel model = (DefaultTableModel) table.getModel();

			ArrayList<Carrera> carreraPorNombre = data.consultarCarrerasPorPoblacionTxt(nombre);
			abstracto.agregarCarreras(carreraPorNombre);
			for(int i = 0; i < abstracto.getRowCount(); i++) {
				abstracto.setValueAt(abstracto.getCarrera().get(i).getIdCarrera(), i, 0);
				abstracto.fireTableCellUpdated(i, 0);
				abstracto.setValueAt(abstracto.getCarrera().get(i).getNombre(), i, 1);
				abstracto.fireTableCellUpdated(i, 1);
				abstracto.setValueAt(abstracto.getCarrera().get(i).getDistancia(), i, 2);
				abstracto.fireTableCellUpdated(i, 2);
				abstracto.setValueAt(abstracto.getCarrera().get(i).getProvincia(), i, 3);
				abstracto.fireTableCellUpdated(i, 3);
				abstracto.setValueAt(abstracto.getCarrera().get(i).getPoblacion(), i, 4);
				abstracto.fireTableCellUpdated(i, 4);
			}
			
			table.revalidate();
			table.repaint();
			table.setModel(abstracto);
			//abstracto.fireTableDataChanged();
			/*if(!nombre.equals("")) {
	
				
				
				while(table.getRowCount() < carreraPorNombre.size()) {
					model.addRow(new Object[]{"Column 1", "Column 2", "Column 3", "Column 4", "Column 5"});
				}
				
				while(table.getRowCount() > carreraPorNombre.size()) {
					model.removeRow(table.getRowCount() - 1);
				}
				

				for(int i = 0; i < carreraPorNombre.size(); i++) {
					table.setValueAt(carreraPorNombre.get(i).getIdCarrera(), i, 0);
					table.setValueAt(carreraPorNombre.get(i).getNombre(), i, 1);
					table.setValueAt(carreraPorNombre.get(i).getDistancia(), i, 2);
					table.setValueAt(carreraPorNombre.get(i).getProvincia(), i, 3);
					table.setValueAt(carreraPorNombre.get(i).getPoblacion(), i, 4);				
				}
			} */
			
			/*txtConsultarPob.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
	
					if(txtConsultarPob.getText().equals("")) {
						int contador = table.getRowCount();
						for(int i = contador - 1 ; i >= 0; i--) {
							model.removeRow(i);
						}
					}
				}
			});*/
			
		
			
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
	
			String nombre = txtConsultarPob.getText();
			//DefaultTableModel model = (DefaultTableModel) table.getModel();

			ArrayList<Carrera> carreraPorNombre = data.consultarCarrerasPorPoblacionTxt(nombre);
			abstracto.agregarCarreras(carreraPorNombre);
			for(int i = 0; i < abstracto.getRowCount(); i++) {
				abstracto.setValueAt(abstracto.getCarrera().get(i).getIdCarrera(), i, 0);
				abstracto.fireTableCellUpdated(i, 0);
				abstracto.setValueAt(abstracto.getCarrera().get(i).getNombre(), i, 1);
				abstracto.fireTableCellUpdated(i, 1);
				abstracto.setValueAt(abstracto.getCarrera().get(i).getDistancia(), i, 2);
				abstracto.fireTableCellUpdated(i, 2);
				abstracto.setValueAt(abstracto.getCarrera().get(i).getProvincia(), i, 3);
				abstracto.fireTableCellUpdated(i, 3);
				abstracto.setValueAt(abstracto.getCarrera().get(i).getPoblacion(), i, 4);
				abstracto.fireTableCellUpdated(i, 4);
			}
			
			table.revalidate();
			table.repaint();
			table.setModel(abstracto);
			//abstracto.fireTableDataChanged();
			/*if(!nombre.equals("")) {
	
				
				
				while(table.getRowCount() < carreraPorNombre.size()) {
					model.addRow(new Object[]{"Column 1", "Column 2", "Column 3", "Column 4", "Column 5"});
				}
				
				while(table.getRowCount() > carreraPorNombre.size()) {
					model.removeRow(table.getRowCount() - 1);
				}
				

				for(int i = 0; i < carreraPorNombre.size(); i++) {
					table.setValueAt(carreraPorNombre.get(i).getIdCarrera(), i, 0);
					table.setValueAt(carreraPorNombre.get(i).getNombre(), i, 1);
					table.setValueAt(carreraPorNombre.get(i).getDistancia(), i, 2);
					table.setValueAt(carreraPorNombre.get(i).getProvincia(), i, 3);
					table.setValueAt(carreraPorNombre.get(i).getPoblacion(), i, 4);				
				}
			} */
			
			/*txtConsultarPob.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
	
					if(txtConsultarPob.getText().equals("")) {
						int contador = table.getRowCount();
						for(int i = contador - 1 ; i >= 0; i--) {
							model.removeRow(i);
						}
					}
				}
			});*/
			
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
	
			String nombre = txtConsultarPob.getText();
			//DefaultTableModel model = (DefaultTableModel) table.getModel();

			ArrayList<Carrera> carreraPorNombre = data.consultarCarrerasPorPoblacionTxt(nombre);
			abstracto.agregarCarreras(carreraPorNombre);
			for(int i = 0; i < abstracto.getRowCount(); i++) {
				abstracto.setValueAt(abstracto.getCarrera().get(i).getIdCarrera(), i, 0);
				abstracto.fireTableCellUpdated(i, 0);
				abstracto.setValueAt(abstracto.getCarrera().get(i).getNombre(), i, 1);
				abstracto.fireTableCellUpdated(i, 1);
				abstracto.setValueAt(abstracto.getCarrera().get(i).getDistancia(), i, 2);
				abstracto.fireTableCellUpdated(i, 2);
				abstracto.setValueAt(abstracto.getCarrera().get(i).getProvincia(), i, 3);
				abstracto.fireTableCellUpdated(i, 3);
				abstracto.setValueAt(abstracto.getCarrera().get(i).getPoblacion(), i, 4);
				abstracto.fireTableCellUpdated(i, 4);
			}
			
			table.revalidate();
			table.repaint();
			table.setModel(abstracto);

			/*if(!nombre.equals("")) {
	
				
				
				while(table.getRowCount() < carreraPorNombre.size()) {
					model.addRow(new Object[]{"Column 1", "Column 2", "Column 3", "Column 4", "Column 5"});
				}
				
				while(table.getRowCount() > carreraPorNombre.size()) {
					model.removeRow(table.getRowCount() - 1);
				}
				

				for(int i = 0; i < carreraPorNombre.size(); i++) {
					table.setValueAt(carreraPorNombre.get(i).getIdCarrera(), i, 0);
					table.setValueAt(carreraPorNombre.get(i).getNombre(), i, 1);
					table.setValueAt(carreraPorNombre.get(i).getDistancia(), i, 2);
					table.setValueAt(carreraPorNombre.get(i).getProvincia(), i, 3);
					table.setValueAt(carreraPorNombre.get(i).getPoblacion(), i, 4);				
				}
			} */
			
			/*txtConsultarPob.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
	
					if(txtConsultarPob.getText().equals("")) {
						int contador = table.getRowCount();
						for(int i = contador - 1 ; i >= 0; i--) {
							model.removeRow(i);
						}
					}
				}
			});*/
		}
		});
		
		



		
		
		
		//Evento que busca a través de un textField de forma dinámica las CARRERAS. 
		//Hay mucho código repetido, busca una manera de meter funciones
				txtConsultar.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void changedUpdate(DocumentEvent arg0) {

						String nombre = txtConsultar.getText();
						//DefaultTableModel model = (DefaultTableModel) table.getModel();
						ArrayList<Carrera> carreraPorNombre = data.consultaPorNombre(nombre);
						abstracto.agregarCarreras(carreraPorNombre);
						for(int i = 0; i < abstracto.getRowCount(); i++) {
							abstracto.setValueAt(abstracto.getCarrera().get(i).getIdCarrera(), i, 0);
							abstracto.fireTableCellUpdated(i, 0);
							abstracto.setValueAt(abstracto.getCarrera().get(i).getNombre(), i, 1);
							abstracto.fireTableCellUpdated(i, 1);
							abstracto.setValueAt(abstracto.getCarrera().get(i).getDistancia(), i, 2);
							abstracto.fireTableCellUpdated(i, 2);
							abstracto.setValueAt(abstracto.getCarrera().get(i).getProvincia(), i, 3);
							abstracto.fireTableCellUpdated(i, 3);
							abstracto.setValueAt(abstracto.getCarrera().get(i).getPoblacion(), i, 4);
							abstracto.fireTableCellUpdated(i, 4);
						}
						
						table.revalidate();
						table.repaint();
						table.setModel(abstracto);
				
						/*if(!nombre.equals("")) {
							
							
							
							while(table.getRowCount() < carreraPorNombre.size()) {
								model.addRow(new Object[]{"Column 1", "Column 2", "Column 3", "Column 4", "Column 5"});
							}
							
							while(table.getRowCount() > carreraPorNombre.size()) {
								model.removeRow(table.getRowCount() - 1);
							}
							

							for(int i = 0; i < carreraPorNombre.size(); i++) {
								table.setValueAt(carreraPorNombre.get(i).getIdCarrera(), i, 0);
								table.setValueAt(carreraPorNombre.get(i).getNombre(), i, 1);
								table.setValueAt(carreraPorNombre.get(i).getDistancia(), i, 2);
								table.setValueAt(carreraPorNombre.get(i).getProvincia(), i, 3);
								table.setValueAt(carreraPorNombre.get(i).getPoblacion(), i, 4);				
							}
						} */
						
						/*txtConsultar.addKeyListener(new KeyAdapter() {
							@Override
							public void keyPressed(KeyEvent arg0) {
	
								if(txtConsultar.getText().equals("")) {
									int contador = table.getRowCount();
									for(int i = contador - 1 ; i >= 0; i--) {
										model.removeRow(i);
									}
								}
							}
						});*/
						
					}

					@Override
					public void insertUpdate(DocumentEvent arg0) {
	
						String nombre = txtConsultar.getText();
						//DefaultTableModel model = (DefaultTableModel) table.getModel();
						ArrayList<Carrera> carreraPorNombre = data.consultaPorNombre(nombre);
						abstracto.agregarCarreras(carreraPorNombre);
						for(int i = 0; i < abstracto.getRowCount(); i++) {
							abstracto.setValueAt(abstracto.getCarrera().get(i).getIdCarrera(), i, 0);
							abstracto.fireTableCellUpdated(i, 0);
							abstracto.setValueAt(abstracto.getCarrera().get(i).getNombre(), i, 1);
							abstracto.fireTableCellUpdated(i, 1);
							abstracto.setValueAt(abstracto.getCarrera().get(i).getDistancia(), i, 2);
							abstracto.fireTableCellUpdated(i, 2);
							abstracto.setValueAt(abstracto.getCarrera().get(i).getProvincia(), i, 3);
							abstracto.fireTableCellUpdated(i, 3);
							abstracto.setValueAt(abstracto.getCarrera().get(i).getPoblacion(), i, 4);
							abstracto.fireTableCellUpdated(i, 4);
						}
						
						table.revalidate();
						table.repaint();
						table.setModel(abstracto);
					
						/*if(!nombre.equals("")) {


							
							while(table.getRowCount() < carreraPorNombre.size()) {
								model.addRow(new Object[]{"Column 1", "Column 2", "Column 3", "Column 4", "Column 5"});
							}
							
							while(table.getRowCount() > carreraPorNombre.size()) {
								model.removeRow(table.getRowCount() - 1);
							}
							

							for(int i = 0; i < carreraPorNombre.size(); i++) {
								table.setValueAt(carreraPorNombre.get(i).getIdCarrera(), i, 0);
								table.setValueAt(carreraPorNombre.get(i).getNombre(), i, 1);
								table.setValueAt(carreraPorNombre.get(i).getDistancia(), i, 2);
								table.setValueAt(carreraPorNombre.get(i).getProvincia(), i, 3);
								table.setValueAt(carreraPorNombre.get(i).getPoblacion(), i, 4);				
							}
						}*/
						
						/*txtConsultar.addKeyListener(new KeyAdapter() {
							@Override
							public void keyPressed(KeyEvent arg0) {

								if(txtConsultar.getText().equals("")) {
									int contador = table.getRowCount();
									for(int i = contador - 1 ; i >= 0; i--) {
										model.removeRow(i);
									}
								}
							}
						});*/
						
					}

					@Override
					public void removeUpdate(DocumentEvent arg0) {					
						String nombre = txtConsultar.getText();
						//DefaultTableModel model = (DefaultTableModel) table.getModel();
						ArrayList<Carrera> carreraPorNombre = data.consultaPorNombre(nombre);
						abstracto.agregarCarreras(carreraPorNombre);
						for(int i = 0; i < abstracto.getRowCount(); i++) {
							abstracto.setValueAt(abstracto.getCarrera().get(i).getIdCarrera(), i, 0);
							abstracto.fireTableCellUpdated(i, 0);
							abstracto.setValueAt(abstracto.getCarrera().get(i).getNombre(), i, 1);
							abstracto.fireTableCellUpdated(i, 1);
							abstracto.setValueAt(abstracto.getCarrera().get(i).getDistancia(), i, 2);
							abstracto.fireTableCellUpdated(i, 2);
							abstracto.setValueAt(abstracto.getCarrera().get(i).getProvincia(), i, 3);
							abstracto.fireTableCellUpdated(i, 3);
							abstracto.setValueAt(abstracto.getCarrera().get(i).getPoblacion(), i, 4);
							abstracto.fireTableCellUpdated(i, 4);
						}
						
						table.revalidate();
						table.repaint();
						table.setModel(abstracto);
						/*if(!nombre.equals("")) {
							
	
							
							
							while(table.getRowCount() < carreraPorNombre.size()) {
								model.addRow(new Object[]{"Column 1", "Column 2", "Column 3", "Column 4", "Column 5"});
							}
							
							while(table.getRowCount() > carreraPorNombre.size()) {
								model.removeRow(table.getRowCount() - 1);
							}
							

							for(int i = 0; i < carreraPorNombre.size(); i++) {
								table.setValueAt(carreraPorNombre.get(i).getIdCarrera(), i, 0);
								table.setValueAt(carreraPorNombre.get(i).getNombre(), i, 1);
								table.setValueAt(carreraPorNombre.get(i).getDistancia(), i, 2);
								table.setValueAt(carreraPorNombre.get(i).getProvincia(), i, 3);
								table.setValueAt(carreraPorNombre.get(i).getPoblacion(), i, 4);				
							}
						} 			*/
						
						/*txtConsultar.addKeyListener(new KeyAdapter() {
							@Override
							public void keyPressed(KeyEvent arg0) {
								if(txtConsultar.getText().equals("")) {
									int contador = table.getRowCount();
									for(int i = contador - 1 ; i >= 0; i--) {
										model.removeRow(i);
									}
								}
							}
						});*/
					}
				});
				
				
				
				table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
					
				
						int rowNumber = table.getSelectedRow();
						
						//int id = (int) table.getValueAt(rowNumber, 0);
						
						int id = (int) abstracto.getValueAt(rowNumber, 0);

						
						//Carrera carreraB = data.consultaPorId(id);
						if (event.getValueIsAdjusting() == false) {
							int seleccion = JOptionPane.showOptionDialog(null,
									"¿Desea borrar la carrera seleccionada?"/* + "\n     Id: " + carreraB.getIdCarrera()
											+ "\n     Nombre:" + carreraB.getNombre() + "" + "\n     Distancia: "
											+ carreraB.getDistancia() + "\n     Provincia: " + carreraB.getProvincia() + ""
											+ "\n     Poblacion: " + carreraB.getPoblacion()*/,
									"Elija una opción", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null
																																// para
																																// icono
																																// por
																																// defecto.
									new Object[] { "Sí", "No" }, // null para YES, NO y CANCEL
									"No");

							if (seleccion == JOptionPane.YES_OPTION) {
								
								ArrayList<Carrera> p = (ArrayList<Carrera>) abstracto.getCarrera();
								System.out.println(p.size());
								p.remove(rowNumber);
								System.out.println(p.size());
								
								data.borrarCarrera(id);
						
								abstracto.agregarCarreras(p);
				
								for(int i = 0; i < abstracto.getRowCount(); i++) {
									abstracto.setValueAt(abstracto.getCarrera().get(i).getIdCarrera(), i, 0);
									abstracto.fireTableCellUpdated(i, 0);
									abstracto.setValueAt(abstracto.getCarrera().get(i).getNombre(), i, 1);
									abstracto.fireTableCellUpdated(i, 1);
									abstracto.setValueAt(abstracto.getCarrera().get(i).getDistancia(), i, 2);
									abstracto.fireTableCellUpdated(i, 2);
									abstracto.setValueAt(abstracto.getCarrera().get(i).getProvincia(), i, 3);
									abstracto.fireTableCellUpdated(i, 3);
									abstracto.setValueAt(abstracto.getCarrera().get(i).getPoblacion(), i, 4);
									abstracto.fireTableCellUpdated(i, 4);
									
								}
								
								
								table.revalidate();
								table.repaint();
			
								table.setModel(abstracto);
								
								
							}

						}
						
					

					}
				});
				
		
				
				
	}

}
