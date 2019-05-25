package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.BBDDCorredores;
import controlador.BBDDUnionTablas;
import controlador.GestionaCorredores;
import controlador.GestionaUnionTablas;
import modelo.Carrera;
import modelo.CombinacionCarCor;
import modelo.Corredor;
import modelo.Poblacion;
import modelo.Provincia;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;

import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class CarrerasYcorredores extends JPanel {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtConsCorr;
	private JTextField txtConsApe;
	private JTextField txtConsCar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarrerasYcorredores frame = new CarrerasYcorredores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CarrerasYcorredores() {
		BBDDUnionTablas data = new BBDDUnionTablas();
		BBDDCorredores dataCor = new BBDDCorredores();
		GestionaCorredores gestion = new GestionaCorredores();
		GestionaUnionTablas gestionUnion = new GestionaUnionTablas();
		
		
		
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consulta conjunta de corredores y las carreras en las que han participado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[grow]", "[-5.00][][grow][grow][][][][][][][][grow][]"));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Consultar por corredor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, "cell 0 2,grow");
		panel.setLayout(new MigLayout("", "[grow]", "[][][]"));
		
		JLabel lblConsultarPorNombre = new JLabel("Consultar por nombre:");
		panel.add(lblConsultarPorNombre, "flowx,cell 0 0");
		
		txtConsCorr = new JTextField();
		panel.add(txtConsCorr, "flowx,cell 0 1,alignx left");
		txtConsCorr.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("                         ");
		panel.add(lblNewLabel, "cell 0 0");
		
		JLabel lblNewLabel_1 = new JLabel("                                ");
		panel.add(lblNewLabel_1, "cell 0 1");
		
		JLabel lblConsultarPorApellido = new JLabel("Consultar por apellido:");
		panel.add(lblConsultarPorApellido, "cell 0 0");
		
		txtConsApe = new JTextField();
		panel.add(txtConsApe, "cell 0 1");
		txtConsApe.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("               ");
		panel.add(lblNewLabel_2, "cell 0 0");
		
		JLabel lblNewLabel_3 = new JLabel("                                      ");
		panel.add(lblNewLabel_3, "cell 0 1");
		
		JLabel lblConsutarPorEdad = new JLabel("Consutar por edad:");
		panel.add(lblConsutarPorEdad, "cell 0 0");
		
		JSpinner spiEdad = new JSpinner();
		spiEdad.setModel(new SpinnerNumberModel(0, null, 130, 1));
		panel.add(spiEdad, "cell 0 1,alignx center");
		
		JLabel lblNewLabel_4 = new JLabel("         ");
		panel.add(lblNewLabel_4, "cell 0 0");
		
		JLabel lblConsultarPorProvincia = new JLabel("Consultar por provincia:");
		panel.add(lblConsultarPorProvincia, "cell 0 0");
		
		JLabel label_1 = new JLabel("                        ");
		panel.add(label_1, "cell 0 0");
		
		JLabel lblConsultarPorPoblacin = new JLabel("Consultar por poblaci\u00F3n:");
		panel.add(lblConsultarPorPoblacin, "cell 0 0");
		
		JLabel label = new JLabel("");
		panel.add(label, "cell 0 0");
		
		JLabel lblNewLabel_5 = new JLabel("         ");
		panel.add(lblNewLabel_5, "cell 0 1");
		
		JComboBox cmbProv = new JComboBox();
		// Agregar Provincias al comboBox
		ArrayList<Provincia> agregarProvincias = dataCor.mostrarTodasLasProvincias();
		for (int i = 0; i < agregarProvincias.size(); i++) {
			cmbProv.addItem(agregarProvincias.get(i).getName());
		}
		panel.add(cmbProv, "cell 0 1");
		
		JLabel lblNewLabel_6 = new JLabel("         ");
		panel.add(lblNewLabel_6, "cell 0 1");
		
		JLabel label_2 = new JLabel("         ");
		panel.add(label_2, "cell 0 1");
		
		JComboBox cmbPob = new JComboBox();

		cmbPob.addItem("Seleccione una población");
		panel.add(cmbPob, "cell 0 1");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Consulta por carrera", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_1, "cell 0 3,grow");
		panel_1.setLayout(new MigLayout("", "[grow]", "[][][]"));
		
		JLabel lblConsultaPorNombre = new JLabel("Consulta por nombre:");
		panel_1.add(lblConsultaPorNombre, "flowx,cell 0 0");
		
		txtConsCar = new JTextField();
		panel_1.add(txtConsCar, "flowx,cell 0 1,alignx left");
		txtConsCar.setColumns(10);
		
		JLabel label_3 = new JLabel("                            ");
		panel_1.add(label_3, "cell 0 0");
		
		JLabel label_4 = new JLabel("                                  ");
		panel_1.add(label_4, "cell 0 1");
		
		JLabel lblConsultaPorDistancia = new JLabel("Consulta por distancia:");
		panel_1.add(lblConsultaPorDistancia, "cell 0 0");
		
		JLabel lblHazClickSobre = new JLabel("Haz click sobre una participac\u00F3n para borrarla.");
		add(lblHazClickSobre, "cell 0 4");
		
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 11,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		

		
		
		TablaPersonalizadaUnion modeloAbstracto = new TablaPersonalizadaUnion();
		
		
		JComboBox cmbDist = new JComboBox();
		cmbDist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<CombinacionCarCor> carrera = new ArrayList();
				int comboBoxValue = cmbDist.getSelectedIndex() + 1;
				carrera = data.consultaPorDistancia(comboBoxValue);
				
				
				modeloAbstracto.agregarCorredores(carrera);

				gestion.modificarModeloAbstractoUnion(modeloAbstracto);

				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
			}
		});
		cmbDist.setMaximumSize(new Dimension(140, 25));
		
		CarrerasYcorredores.class.getResource("1.jpg");
		CarrerasYcorredores.class.getResource("2.jpg");
		CarrerasYcorredores.class.getResource("3.jpg");
		CarrerasYcorredores.class.getResource("4.jpg");
		CarrerasYcorredores.class.getResource("5.jpg");
		CarrerasYcorredores.class.getResource("6.jpg");
		
		
		ImageIcon imageIcon1 = new ImageIcon("distancias/1.jpg"); 
		Image image = imageIcon1.getImage(); 
		Image dist1 = image.getScaledInstance(140, 25, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon2 = new ImageIcon("distancias/2.jpg"); 
		Image image2 = imageIcon2.getImage(); 
		Image dist2 = image2.getScaledInstance(140, 25, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon3 = new ImageIcon("distancias/3.jpg"); 
		Image image3 = imageIcon3.getImage(); 
		Image dist3 = image3.getScaledInstance(140, 25, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon4 = new ImageIcon("distancias/4.jpg"); 
		Image image4 = imageIcon4.getImage(); 
		Image dist4 = image4.getScaledInstance(140, 25, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon5 = new ImageIcon("distancias/5.jpg"); 
		Image image5 = imageIcon5.getImage(); 
		Image dist5 = image5.getScaledInstance(140, 25, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon6 = new ImageIcon("distancias/6.jpg"); 
		Image image6 = imageIcon6.getImage(); 
		Image dist6 = image6.getScaledInstance(140, 25, Image.SCALE_SMOOTH);
		
		DefaultComboBoxModel<Icon> cm = new DefaultComboBoxModel<Icon>();

		cm.addElement(new ImageIcon(dist1));
		cm.addElement(new ImageIcon(dist2));
		cm.addElement(new ImageIcon(dist3));
		cm.addElement(new ImageIcon(dist4));
		cm.addElement(new ImageIcon(dist5));
		cm.addElement(new ImageIcon(dist6));
		
		
		
		cmbDist.setModel(cm);
		panel_1.add(cmbDist, "cell 0 1");
		
		JLabel lblNewLabel_7 = new JLabel("                       ");
		panel_1.add(lblNewLabel_7, "cell 0 0");
		
		JLabel lblNewLabel_8 = new JLabel("                        ");
		panel_1.add(lblNewLabel_8, "cell 0 1");
		
		JLabel lblConsultarPorProvincia_1 = new JLabel("Consultar por provincia:");
		panel_1.add(lblConsultarPorProvincia_1, "cell 0 0");
		
		JComboBox cmbProvCar = new JComboBox();

		cmbProvCar.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una provincia"}));
		for (int i = 0; i < agregarProvincias.size(); i++) {
			cmbProvCar.addItem(agregarProvincias.get(i).getName());
		}
		panel_1.add(cmbProvCar, "cell 0 1");
		
		JLabel label_5 = new JLabel("                               ");
		panel_1.add(label_5, "cell 0 0");
		
		JLabel label_6 = new JLabel("                       ");
		panel_1.add(label_6, "cell 0 1");
		
		JLabel lblConsultarPorPoblacin_1 = new JLabel("Consultar por poblaci\u00F3n:");
		panel_1.add(lblConsultarPorPoblacin_1, "cell 0 0");
		
		JComboBox cmbPobCar = new JComboBox();
		cmbPobCar.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una poblaci\u00F3n"}));
		panel_1.add(cmbPobCar, "cell 0 1");
		
		JButton btnMostrarTodo = new JButton("Mostrar Todo");

		add(btnMostrarTodo, "cell 0 7");
		

		
		btnMostrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ArrayList<CombinacionCarCor> todosLosCorredores = data.mostrarTodo();
				modeloAbstracto.agregarCorredores(todosLosCorredores);

				gestion.modificarModeloAbstractoUnion(modeloAbstracto);
				
				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
			}
		});
		
		JComboBox exportarCmb = new JComboBox();
		exportarCmb.addItem("Excel");
		exportarCmb.addItem("HTML");
		exportarCmb.addItem("XML");
		exportarCmb.addItem("SQL");
		
		JButton btnExportar = new JButton("EXPORTAR");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int seleccion = JOptionPane.showOptionDialog(null, exportarCmb, "¿Desea exportar la tabla actual?",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Sí", "No" }, // null
																														// para
																														// YES,
																														// NO
																														// y
																														// CANCEL
						"No");
				String metodoExportar = "";
				if (seleccion == JOptionPane.YES_OPTION) {
					metodoExportar = exportarCmb.getSelectedItem().toString();
				}

				if (metodoExportar.equals("Excel")) {
					gestionUnion.exportarExcel(modeloAbstracto);

				} else if(metodoExportar.equals("HTML")) {
					try {
						gestionUnion.exportarHTML(modeloAbstracto);
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
						gestionUnion.exportarXML(modeloAbstracto);
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
						gestionUnion.exportarSQL(modeloAbstracto);
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
		add(btnExportar, "cell 0 12,alignx center");
		
		
		txtConsCorr.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				String nombre = txtConsCorr.getText();
				ArrayList<CombinacionCarCor> combinacion = data.consultaPorNombre(nombre);
				
				modeloAbstracto.agregarCorredores(combinacion);
				gestion.modificarModeloAbstractoUnion(modeloAbstracto);
				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
				
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String nombre = txtConsCorr.getText();
				ArrayList<CombinacionCarCor> combinacion = data.consultaPorNombre(nombre);
				
				modeloAbstracto.agregarCorredores(combinacion);
				gestion.modificarModeloAbstractoUnion(modeloAbstracto);
				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				String nombre = txtConsCorr.getText();
				ArrayList<CombinacionCarCor> combinacion = data.consultaPorNombre(nombre);
				
				modeloAbstracto.agregarCorredores(combinacion);
				gestion.modificarModeloAbstractoUnion(modeloAbstracto);
				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
				
			}
			
		});
		
		
		txtConsApe.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				String nombre = txtConsApe.getText();
				ArrayList<CombinacionCarCor> combinacion = data.consultaPorApellido(nombre);
				
				modeloAbstracto.agregarCorredores(combinacion);
				gestion.modificarModeloAbstractoUnion(modeloAbstracto);
				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
				
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String nombre = txtConsApe.getText();
				ArrayList<CombinacionCarCor> combinacion = data.consultaPorApellido(nombre);
				
				modeloAbstracto.agregarCorredores(combinacion);
				gestion.modificarModeloAbstractoUnion(modeloAbstracto);
				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				String nombre = txtConsApe.getText();
				ArrayList<CombinacionCarCor> combinacion = data.consultaPorApellido(nombre);
				
				modeloAbstracto.agregarCorredores(combinacion);
				gestion.modificarModeloAbstractoUnion(modeloAbstracto);
				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
				
			}
			
		});
		
		spiEdad.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				int edad = (int) spiEdad.getValue();
				ArrayList<CombinacionCarCor> combinacion = data.consultaPorEdad(edad);
				
				modeloAbstracto.agregarCorredores(combinacion);
				gestion.modificarModeloAbstractoUnion(modeloAbstracto);
				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
				
			}
			
		});
		
		
		cmbProv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String provincia = cmbProv.getSelectedItem().toString();
				ArrayList<CombinacionCarCor> combinacion = data.consultaPorProvincia(provincia);
				
				modeloAbstracto.agregarCorredores(combinacion);
				gestion.modificarModeloAbstractoUnion(modeloAbstracto);
				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
				
				
				
				ArrayList<Poblacion> poblaciones = dataCor.consultarPoblacionesPorProvincia(provincia);
				DefaultComboBoxModel model = new DefaultComboBoxModel();
				for (int i = 0; i < poblaciones.size(); i++) {
					
					model.addElement(poblaciones.get(i).getNombre());
				}
				
				cmbPob.setModel(model);
				
				cmbPob.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String poblacion = cmbPob.getSelectedItem().toString();
						ArrayList<CombinacionCarCor> combinacionPob = data.consultaPorPoblacion(poblacion);
						
						modeloAbstracto.agregarCorredores(combinacionPob);
						gestion.modificarModeloAbstractoUnion(modeloAbstracto);
						table.revalidate();
						table.repaint();

						table.setModel(modeloAbstracto);
					}
				});

			}
			
		});
		
		txtConsCar.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				String nombre = txtConsCar.getText();
				ArrayList<CombinacionCarCor> combinacion = data.consultaCarreraPorNombre(nombre);
				
				modeloAbstracto.agregarCorredores(combinacion);
				gestion.modificarModeloAbstractoUnion(modeloAbstracto);
				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
				
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String nombre = txtConsCar.getText();
				ArrayList<CombinacionCarCor> combinacion = data.consultaCarreraPorNombre(nombre);
				
				modeloAbstracto.agregarCorredores(combinacion);
				gestion.modificarModeloAbstractoUnion(modeloAbstracto);
				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				String nombre = txtConsCar.getText();
				ArrayList<CombinacionCarCor> combinacion = data.consultaCarreraPorNombre(nombre);
				
				modeloAbstracto.agregarCorredores(combinacion);
				gestion.modificarModeloAbstractoUnion(modeloAbstracto);
				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
				
			}
			
		});
		
		cmbProvCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String provincia = cmbProvCar.getSelectedItem().toString();
				ArrayList<CombinacionCarCor> combinacion = data.consultaCarreraPorProvincia(provincia);
				
				modeloAbstracto.agregarCorredores(combinacion);
				gestion.modificarModeloAbstractoUnion(modeloAbstracto);
				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
				
				
				
				ArrayList<Poblacion> poblaciones = dataCor.consultarPoblacionesPorProvincia(provincia);
				DefaultComboBoxModel model = new DefaultComboBoxModel();
				for (int i = 0; i < poblaciones.size(); i++) {
					
					model.addElement(poblaciones.get(i).getNombre());
				}
				
				cmbPobCar.setModel(model);
				
				cmbPobCar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String poblacion = cmbPobCar.getSelectedItem().toString();
						ArrayList<CombinacionCarCor> combinacionPob = data.consultaCarreraPorPoblacion(poblacion);
						
						modeloAbstracto.agregarCorredores(combinacionPob);
						gestion.modificarModeloAbstractoUnion(modeloAbstracto);
						table.revalidate();
						table.repaint();

						table.setModel(modeloAbstracto);
					}
				});

			}

		});
		
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {

				int rowNumber = table.getSelectedRow();

				int idCorredor = (int) modeloAbstracto.getValueAt(rowNumber, 0);
				int idCarrera = (int) modeloAbstracto.getValueAt(rowNumber, 6);

				if (event.getValueIsAdjusting() == false) {
					int seleccion = JOptionPane.showOptionDialog(null,
							"¿Desea borrar el carrera seleccionada?", 
							"Elija una opción",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
							new Object[] { "Sí", "No" }, 
							"No");

					if (seleccion == JOptionPane.YES_OPTION) {
						ArrayList<CombinacionCarCor> corredores = (ArrayList<CombinacionCarCor>) modeloAbstracto.getCorredores();
						
						corredores.remove(rowNumber);

						data.borrarParticipacion(idCorredor, idCarrera);

						modeloAbstracto.agregarCorredores(corredores);

						gestion.modificarModeloAbstractoUnion(modeloAbstracto);

						table.revalidate();
						
						table.repaint();

						table.setModel(modeloAbstracto);

					}

				}

			}
		});
		

	}

}
