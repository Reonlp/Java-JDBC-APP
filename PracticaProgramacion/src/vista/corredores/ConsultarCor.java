package vista.corredores;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import controlador.BBDDCorredores;
import controlador.GestionaCorredores;
import modelo.Carrera;
import modelo.Corredor;
import modelo.Poblacion;
import modelo.Provincia;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;

import net.miginfocom.swing.MigLayout;
import vista.TablaPersonalizadaCorredores;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class ConsultarCor extends JPanel {
	private JTextField txtConsultar;
	private JTable table;
	private JTextField txtConsultarPob;

	/**
	 * Create the panel.
	 */
	public ConsultarCor() {
		BBDDCorredores data = new BBDDCorredores();
		GestionaCorredores gestion = new GestionaCorredores();
		


		setBorder(new TitledBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consultar Corredores",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
				"Consultar Corredores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[418.00,grow][grow]", "[][229.00,grow][]"));

		txtConsultar = new JTextField();
		add(txtConsultar, "cell 0 0,growx");
		txtConsultar.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1,grow");

		table = new JTable();

		scrollPane.setViewportView(table);
		
		/*IMAGENES PARA JAR*/
		ConsultarCor.class.getResource("1.jpg");
		ConsultarCor.class.getResource("2.jpg");
		ConsultarCor.class.getResource("3.jpg");
		ConsultarCor.class.getResource("4.jpg");
		ConsultarCor.class.getResource("5.jpg");
		ConsultarCor.class.getResource("6.jpg");

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
		add(panel, "cell 1 1,grow");
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

				if (!modeloAbstracto.getCorredores().isEmpty()) {
					ArrayList<Corredor> arrayModAbstracto = (ArrayList<Corredor>) modeloAbstracto.getCorredores();
					ArrayList<Corredor> nuevoSubConsulta = new ArrayList<Corredor>();
					for (int i = 0; i < arrayModAbstracto.size(); i++) {
						if (arrayModAbstracto.get(i).getProvincia().equals(provincia)) {
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
						int resultadoConsulta = JOptionPane.showOptionDialog(null,
								"No se ha encontrado ningún resultado", "Resultado", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]);

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

		/*
		 * =======================Realiza una consulta a través del textfield de
		 * poblaciones ===============================
		 */

		txtConsultarPob.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				String poblacion = txtConsultarPob.getText();

				if (!poblacion.equals("")) {

					if (!modeloAbstracto.getCorredores().isEmpty()) {
						ArrayList<Corredor> arrayModAbstracto = (ArrayList<Corredor>) modeloAbstracto.getCorredores();
						ArrayList<Corredor> nuevoSubConsulta = new ArrayList<Corredor>();
						for (int i = 0; i < arrayModAbstracto.size(); i++) {
							if (arrayModAbstracto.get(i).getPoblacion().equals(poblacion)) {
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

					if (!modeloAbstracto.getCorredores().isEmpty()) {
						ArrayList<Corredor> arrayModAbstracto = (ArrayList<Corredor>) modeloAbstracto.getCorredores();
						ArrayList<Corredor> nuevoSubConsulta = new ArrayList<Corredor>();
						for (int i = 0; i < arrayModAbstracto.size(); i++) {
							if (arrayModAbstracto.get(i).getPoblacion().equals(poblacion)) {
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

					if (!modeloAbstracto.getCorredores().isEmpty()) {
						ArrayList<Corredor> arrayModAbstracto = (ArrayList<Corredor>) modeloAbstracto.getCorredores();
						ArrayList<Corredor> nuevoSubConsulta = new ArrayList<Corredor>();
						for (int i = 0; i < arrayModAbstracto.size(); i++) {
							if (arrayModAbstracto.get(i).getPoblacion().equals(poblacion)) {
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

				if (!modeloAbstracto.getCorredores().isEmpty()) {
					ArrayList<Corredor> arrayModAbstracto = (ArrayList<Corredor>) modeloAbstracto.getCorredores();
					ArrayList<Corredor> nuevoSubConsulta = new ArrayList<Corredor>();
					for (int i = 0; i < arrayModAbstracto.size(); i++) {
						if (arrayModAbstracto.get(i).getEdad() <= value) {
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

		JComboBox exportarCmb = new JComboBox();
		exportarCmb.addItem("Excel");
		exportarCmb.addItem("HTML");
		exportarCmb.addItem("XML");
		exportarCmb.addItem("SQL");

		/* ==============EXPORTA ARCHIVOS EXCEL, XML, HTML ====================== */

		JButton btnExportar = new JButton("EXPORTAR");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int seleccion = JOptionPane.showOptionDialog(null, exportarCmb, "¿Desea exportar la tabla actual?",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Sí", "No" }, 
						"No");
				String metodoExportar = "";
				if (seleccion == JOptionPane.YES_OPTION) {
					metodoExportar = exportarCmb.getSelectedItem().toString();
				}

				if (metodoExportar.equals("Excel")) {
					gestion.exportarExcel(modeloAbstracto);
				} else if(metodoExportar.equals("HTML")) {
					try {
						gestion.exportarHTML(modeloAbstracto);
						Object[] option = {"OK"};
						int resultadoConsulta = JOptionPane.showOptionDialog(null,
				                   "Archivo guardado con éxito","Resultado",
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
				} else if(metodoExportar.equals("SQL")) {
					try {
						gestion.exportarSQL(modeloAbstracto);
						Object[] option = {"OK"};
						int resultadoConsulta = JOptionPane.showOptionDialog(null,
				                   "Archivo guardado con éxito","Resultado",
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
				} else if(metodoExportar.equals("XML")) {
					try {
						gestion.exportarXML(modeloAbstracto);
						Object[] option = {"OK"};
						int resultadoConsulta = JOptionPane.showOptionDialog(null,
				                   "Archivo guardado con éxito","Resultado",
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
			}
		});
		add(btnExportar, "cell 0 2,alignx center");

	}

}
