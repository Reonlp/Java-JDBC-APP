package vista.corredores;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.BBDDCorredores;
import controlador.GestionaCorredores;
import modelo.Carrera;
import modelo.Corredor;
import modelo.Poblacion;
import modelo.Provincia;
import net.miginfocom.swing.MigLayout;
import vista.TablaPersonalizadaCorredores;

public class BorrarCor extends JPanel {
	private JTextField txtConsultar;
	private JTable table;
	private JTextField txtConsultarPob;

	/**
	 * Create the panel.
	 */
	public BorrarCor() {
		BBDDCorredores data = new BBDDCorredores();
		GestionaCorredores gestion = new GestionaCorredores();
		
		/*IMAGENES PARA JAR*/
		BorrarCor.class.getResource("1.jpg");
		BorrarCor.class.getResource("2.jpg");
		BorrarCor.class.getResource("3.jpg");
		BorrarCor.class.getResource("4.jpg");
		BorrarCor.class.getResource("5.jpg");
		BorrarCor.class.getResource("6.jpg");
		
		setBorder(new TitledBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consultar Corredores",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
				"Consultar Corredores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[418.00,grow][grow]", "[][][229.00,grow]"));

		txtConsultar = new JTextField();
		add(txtConsultar, "cell 0 0,growx");
		txtConsultar.setColumns(10);
		
		JLabel lblHazClickSobre = new JLabel("Haz click sobre el corredor que desee borrar");
		add(lblHazClickSobre, "cell 0 1");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 2,grow");

		table = new JTable();

		scrollPane.setViewportView(table);

		// Función que pone las filas pares en un color y las impares en otro
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);
				c.setBackground(row % 2 == 0 ? Color.getHSBColor(18, 2, 200) : Color.WHITE);
				return this;
			}
		});

		/* MOSTRAMOS TODO DE FORMA DINÁMICA */
		TablaPersonalizadaCorredores modeloAbstracto = new TablaPersonalizadaCorredores();

		JButton btnMostrarTodo = new JButton("Mostrar todo");
		btnMostrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ArrayList<Corredor> todosLosCorredores = data.mostrarTodosCorredores();
				modeloAbstracto.agregarCorredores(todosLosCorredores);

				gestion.modificarModeloAbstracto(modeloAbstracto);
				
				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
			}
		});
		add(btnMostrarTodo, "cell 1 0,growx");

		JPanel panel = new JPanel();
		add(panel, "cell 1 2,grow");
		panel.setLayout(new MigLayout("", "[162.00,grow]", "[][][][][18.00][84.00][-2.00,top][][grow]"));

		JLabel lblEdad = new JLabel("Edad:");
		panel.add(lblEdad, "cell 0 2");

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 130, 1));
		panel.add(spinner, "cell 0 3,growx");

		JLabel lblProvincia = new JLabel("Provincia:");
		panel.add(lblProvincia, "cell 0 4,aligny bottom");

		JComboBox cmbProv = new JComboBox();

		txtConsultarPob = new JTextField();
		panel.add(txtConsultarPob, "cell 0 7,growx");
		txtConsultarPob.setColumns(10);

		JScrollPane scrollPane_2 = new JScrollPane();
		panel.add(scrollPane_2, "cell 0 8,grow");

		// Agregar Provincias al comboBox
		ArrayList<Provincia> agregarProvincias = data.mostrarTodasLasProvincias();
		for (int i = 0; i < agregarProvincias.size(); i++) {
			cmbProv.addItem(agregarProvincias.get(i).getName());
		}

		panel.add(cmbProv, "cell 0 5,growx,aligny top");

		cmbProv.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				
				
				/*--------AÑADE LA BUSQUEDA DE LA BASE DE DATOS A LA TABLA-----*/
				String provincia = cmbProv.getSelectedItem().toString();
				
				if(!modeloAbstracto.getCorredores().isEmpty()) {
					ArrayList<Corredor> arrayModAbstracto = (ArrayList<Corredor>) modeloAbstracto.getCorredores();
					ArrayList<Corredor> nuevoSubConsulta = new ArrayList<Corredor>();
					for(int i = 0; i < arrayModAbstracto.size(); i++) {
						if(arrayModAbstracto.get(i).getProvincia().equals(provincia)) {
							nuevoSubConsulta.add(arrayModAbstracto.get(i));
						}
					}
					
					modeloAbstracto.agregarCorredores(nuevoSubConsulta);
					
					gestion.modificarModeloAbstracto(modeloAbstracto);

					table.revalidate();
					table.repaint();
					
					table.setModel(modeloAbstracto);
					
				} else {
					
				
				ArrayList<Corredor> carreraProvincia = data.mostrarCarreraPorProvincia(provincia);

				modeloAbstracto.agregarCorredores(carreraProvincia);

				if (carreraProvincia.isEmpty()) {
					Object[] options3 = { "OK" };
					int resultadoConsulta = JOptionPane.showOptionDialog(null, "No se ha encontrado ningún resultado",
							"Resultado", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options3,
							options3[0]);
					
					gestion.modificarModeloAbstracto(modeloAbstracto);

					table.revalidate();
					table.repaint();

					table.setModel(modeloAbstracto);

				} else {

					gestion.modificarModeloAbstracto(modeloAbstracto);

					table.revalidate();
					table.repaint();

					table.setModel(modeloAbstracto);
					
					}

				}

				/*
				 * =======================LISTA DE POBLACIONES QUE SON AGREGADAS DINAMICAMENTE
				 * AL ELEGIR LA PROVINCIA EN EL ANTERIOR COMBO BOX======
				 */

				JList lstPobAgrCar = new JList<>();

				DefaultListModel model = new DefaultListModel();
				String nombre = cmbProv.getSelectedItem().toString();
				ArrayList<Poblacion> poblaciones = data.consultarPoblacionesPorProvincia(nombre);
				for (int i = 0; i < poblaciones.size(); i++) {
					model.addElement(poblaciones.get(i).getNombre());
				}

				lstPobAgrCar.setModel(model);

				scrollPane_2.setViewportView(lstPobAgrCar);

				lstPobAgrCar.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						if (!arg0.getValueIsAdjusting()) {

							int pob = lstPobAgrCar.getSelectedIndex();
							String poblacion = poblaciones.get(pob).getNombre();
							txtConsultarPob.setText(poblacion);
						}

					}

				});

			}
		});


		txtConsultarPob.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				String poblacion = txtConsultarPob.getText();
				
				if (!poblacion.equals("")) {
					
					if(!modeloAbstracto.getCorredores().isEmpty()) {
						ArrayList<Corredor> arrayModAbstracto = (ArrayList<Corredor>) modeloAbstracto.getCorredores();
						ArrayList<Corredor> nuevoSubConsulta = new ArrayList<Corredor>();
						for(int i = 0; i < arrayModAbstracto.size(); i++) {
							if(arrayModAbstracto.get(i).getPoblacion().equals(poblacion)) {
								nuevoSubConsulta.add(arrayModAbstracto.get(i));
							}
						}
						
						modeloAbstracto.agregarCorredores(nuevoSubConsulta);
						
						gestion.modificarModeloAbstracto(modeloAbstracto);

						table.revalidate();
						table.repaint();
						
						table.setModel(modeloAbstracto);
						
					} else {
						
						ArrayList<Corredor> carreraPorPoblacion = data.consultarCarrerasPorPoblacionTxt(poblacion);
						
						modeloAbstracto.agregarCorredores(carreraPorPoblacion);
						
						gestion.modificarModeloAbstracto(modeloAbstracto);

						table.revalidate();
						table.repaint();

						table.setModel(modeloAbstracto);
					}
					
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				String poblacion = txtConsultarPob.getText();
				
				if (!poblacion.equals("")) {
					
					if(!modeloAbstracto.getCorredores().isEmpty()) {
						ArrayList<Corredor> arrayModAbstracto = (ArrayList<Corredor>) modeloAbstracto.getCorredores();
						ArrayList<Corredor> nuevoSubConsulta = new ArrayList<Corredor>();
						for(int i = 0; i < arrayModAbstracto.size(); i++) {
							if(arrayModAbstracto.get(i).getPoblacion().equals(poblacion)) {
								nuevoSubConsulta.add(arrayModAbstracto.get(i));
							}
						}
						
						modeloAbstracto.agregarCorredores(nuevoSubConsulta);
						
						gestion.modificarModeloAbstracto(modeloAbstracto);

						table.revalidate();
						table.repaint();
						
						table.setModel(modeloAbstracto);
						
					} else {
						
						ArrayList<Corredor> carreraPorPoblacion = data.consultarCarrerasPorPoblacionTxt(poblacion);
						
						modeloAbstracto.agregarCorredores(carreraPorPoblacion);
						
						gestion.modificarModeloAbstracto(modeloAbstracto);

						table.revalidate();
						table.repaint();

						table.setModel(modeloAbstracto);
					}
					
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				String poblacion = txtConsultarPob.getText();
				
				if (!poblacion.equals("")) {
					
					if(!modeloAbstracto.getCorredores().isEmpty()) {
						ArrayList<Corredor> arrayModAbstracto = (ArrayList<Corredor>) modeloAbstracto.getCorredores();
						ArrayList<Corredor> nuevoSubConsulta = new ArrayList<Corredor>();
						for(int i = 0; i < arrayModAbstracto.size(); i++) {
							if(arrayModAbstracto.get(i).getPoblacion().equals(poblacion)) {
								nuevoSubConsulta.add(arrayModAbstracto.get(i));
							}
						}
						
						modeloAbstracto.agregarCorredores(nuevoSubConsulta);
						
						gestion.modificarModeloAbstracto(modeloAbstracto);

						table.revalidate();
						table.repaint();
						
						table.setModel(modeloAbstracto);
						
					} else {
						
						ArrayList<Corredor> carreraPorPoblacion = data.consultarCarrerasPorPoblacionTxt(poblacion);
						
						modeloAbstracto.agregarCorredores(carreraPorPoblacion);
						
						gestion.modificarModeloAbstracto(modeloAbstracto);

						table.revalidate();
						table.repaint();

						table.setModel(modeloAbstracto);
					}
					
				}

			}
		});

		JLabel lblPoblacin = new JLabel("Poblaci\u00F3n:");
		panel.add(lblPoblacin, "cell 0 6");

		// Evento que busca a través de un textField de forma dinámica las CARRERAS.
		// Hay mucho código repetido, busca una manera de meter funciones
		txtConsultar.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {

				String nombre = txtConsultar.getText();

				ArrayList<Corredor> carreraPorNombre = data.consultaPorNombre(nombre);
				if (!nombre.equals("")) {
					modeloAbstracto.agregarCorredores(carreraPorNombre);
					gestion.modificarModeloAbstracto(modeloAbstracto);

					table.revalidate();
					table.repaint();

					table.setModel(modeloAbstracto);
				}


			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {

				String nombre = txtConsultar.getText();

				ArrayList<Corredor> carreraPorNombre = data.consultaPorNombre(nombre);
				if (!nombre.equals("")) {
					modeloAbstracto.agregarCorredores(carreraPorNombre);
					gestion.modificarModeloAbstracto(modeloAbstracto);
					table.revalidate();
					table.repaint();

					table.setModel(modeloAbstracto);
				}

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {

				String nombre = txtConsultar.getText();

				ArrayList<Corredor> carreraPorNombre = data.consultaPorNombre(nombre);
				if (!nombre.equals("")) {
					modeloAbstracto.agregarCorredores(carreraPorNombre);
					gestion.modificarModeloAbstracto(modeloAbstracto);

					table.revalidate();
					table.repaint();

					table.setModel(modeloAbstracto);
				}
			}
		});

		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner) e.getSource();
				int value = (int) spinner.getValue();
				
				if(!modeloAbstracto.getCorredores().isEmpty()) {
					ArrayList<Corredor> arrayModAbstracto = (ArrayList<Corredor>) modeloAbstracto.getCorredores();
					ArrayList<Corredor> nuevoSubConsulta = new ArrayList<Corredor>();
					for(int i = 0; i < arrayModAbstracto.size(); i++) {
						if(arrayModAbstracto.get(i).getEdad() <= value) {
							nuevoSubConsulta.add(arrayModAbstracto.get(i));
						}
					}
					
					modeloAbstracto.agregarCorredores(nuevoSubConsulta);
					
					gestion.modificarModeloAbstracto(modeloAbstracto);

					table.revalidate();
					table.repaint();
					
					table.setModel(modeloAbstracto);
				} else {
				
				
				ArrayList<Corredor> consultaEdad = data.consultaPorEdad(value);

				modeloAbstracto.agregarCorredores(consultaEdad);

				gestion.modificarModeloAbstracto(modeloAbstracto);


				table.revalidate();
				table.repaint();
				
				table.setModel(modeloAbstracto);
				
				}
			}
		});

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {

				int rowNumber = table.getSelectedRow();

				int id = (int) modeloAbstracto.getValueAt(rowNumber, 0);

				if (event.getValueIsAdjusting() == false) {
					int seleccion = JOptionPane.showOptionDialog(null,
							"¿Desea borrar el carrera seleccionada?"/*
																	 * + "\n     Id: " + carreraB.getIdCarrera() +
																	 * "\n     Nombre:" + carreraB.getNombre() + "" +
																	 * "\n     Distancia: " + carreraB.getDistancia() +
																	 * "\n     Provincia: " + carreraB.getProvincia() +
																	 * "" + "\n     Poblacion: " +
																	 * carreraB.getPoblacion()
																	 */, "Elija una opción",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null
																									// para
																									// icono
																									// por
																									// defecto.
							new Object[] { "Sí", "No" }, // null para YES, NO y CANCEL
							"No");

					if (seleccion == JOptionPane.YES_OPTION) {
						ArrayList<Corredor> corredores = (ArrayList<Corredor>) modeloAbstracto.getCorredores();
						
						corredores.remove(rowNumber);

						data.borrarCarrera(id);

						modeloAbstracto.agregarCorredores(corredores);

						gestion.modificarModeloAbstracto(modeloAbstracto);

						table.revalidate();
						
						table.repaint();

						table.setModel(modeloAbstracto);

					}

				}

			}
		});

	}

}



