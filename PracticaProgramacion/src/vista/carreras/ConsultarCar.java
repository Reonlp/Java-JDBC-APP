package vista.carreras;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import vista.TablaPersonalizadaCarreras;

import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.BBDDCarreras;
import controlador.GestionaCarreras;
import modelo.Carrera;
import modelo.Corredor;
import modelo.Poblacion;
import modelo.Provincia;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;

public class ConsultarCar extends JPanel {
	private JTextField txtConsultar;
	private JTable table;
	private JTextField txtConsultarPob;

	/**
	 * Create the panel.
	 */
	public ConsultarCar() {
		GestionaCarreras gestion = new GestionaCarreras();
		BBDDCarreras data = new BBDDCarreras();
		TablaPersonalizadaCarreras modeloAbstracto = new TablaPersonalizadaCarreras();
		setBorder(new TitledBorder(null, "CONSULTAR CARRERAS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[318.00,grow][grow,left]", "[][grow]"));
		
		/*IMAGENES PARA JAR*/
		ConsultarCar.class.getResource("1.jpg");
		ConsultarCar.class.getResource("2.jpg");
		ConsultarCar.class.getResource("3.jpg");
		ConsultarCar.class.getResource("4.jpg");
		ConsultarCar.class.getResource("5.jpg");
		ConsultarCar.class.getResource("6.jpg");
		
		txtConsultar = new JTextField();
		add(txtConsultar, "cell 0 0,growx");
		txtConsultar.setColumns(10);
		
		JButton btnMostrarTodo = new JButton("Mostrar todo");
		add(btnMostrarTodo, "cell 1 0");
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 1,grow");
		panel.setLayout(new MigLayout("", "[grow][]", "[grow][][]"));
		
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
		
		JButton btnExportar = new JButton("Exportar");

		panel.add(btnExportar, "cell 0 1,alignx center");
		
		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 1 1,grow");
		panel_1.setLayout(new MigLayout("", "[grow]", "[][46.00][20.00][44.00][][][grow]"));
		
		JLabel lblDistancia = new JLabel("Distancia:");
		panel_1.add(lblDistancia, "cell 0 0");
		
		JComboBox cmbDist = new JComboBox();
		
		/*==============CONSULTAR POR DISTANCIA==============*/
		cmbDist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Carrera> carrera = new ArrayList();
				int comboBoxValue = cmbDist.getSelectedIndex() + 1;
				carrera = data.mostrarCarrerasPorDistancia(comboBoxValue);
				
				
				modeloAbstracto.agregarCarreras(carrera);

				gestion.modificarModeloAbstracto(modeloAbstracto);

				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
				
				
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
				ArrayList<Carrera> todasLasCarreras = data.mostrarCarreras();
				modeloAbstracto.agregarCarreras(todasLasCarreras);

				gestion.modificarModeloAbstracto(modeloAbstracto);

				table.revalidate();
				table.repaint();

				table.setModel(modeloAbstracto);
				
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
						
	
						modeloAbstracto.agregarCarreras(carreraProvincia);

						gestion.modificarModeloAbstracto(modeloAbstracto);

						table.revalidate();
						table.repaint();

						table.setModel(modeloAbstracto);
						
						if(carreraProvincia.isEmpty()) {
							Object[] options3 = {"OK"};
							int resultadoConsulta = JOptionPane.showOptionDialog(null,
					                   "No se ha encontrado ningún resultado","Resultado",
					                   JOptionPane.PLAIN_MESSAGE,
					                   JOptionPane.QUESTION_MESSAGE,
					                   null,
					                   options3,
					                   options3[0]);

						}
						
						
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
				});
		
		
		/*=======================Realiza una consulta a través del textfield de poblaciones ===============================*/
		
		txtConsultarPob.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
			String nombre = txtConsultarPob.getText();
			ArrayList<Carrera> carreraPorNombre = data.consultarCarrerasPorPoblacionTxt(nombre);

			modeloAbstracto.agregarCarreras(carreraPorNombre);

			gestion.modificarModeloAbstracto(modeloAbstracto);

			table.revalidate();
			table.repaint();

			table.setModel(modeloAbstracto);
			
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String nombre = txtConsultarPob.getText();

			ArrayList<Carrera> carreraPorNombre = data.consultarCarrerasPorPoblacionTxt(nombre);

			modeloAbstracto.agregarCarreras(carreraPorNombre);

			gestion.modificarModeloAbstracto(modeloAbstracto);

			table.revalidate();
			table.repaint();

			table.setModel(modeloAbstracto);
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			String nombre = txtConsultarPob.getText();

			ArrayList<Carrera> carreraPorNombre = data.consultarCarrerasPorPoblacionTxt(nombre);

			modeloAbstracto.agregarCarreras(carreraPorNombre);

			gestion.modificarModeloAbstracto(modeloAbstracto);

			table.revalidate();
			table.repaint();

			table.setModel(modeloAbstracto);		
			

		}
		});

		
				txtConsultar.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void changedUpdate(DocumentEvent arg0) {
						
						String nombre = txtConsultar.getText();

						ArrayList<Carrera> carreraPorNombre = data.consultaPorNombre(nombre);

						modeloAbstracto.agregarCarreras(carreraPorNombre);

						gestion.modificarModeloAbstracto(modeloAbstracto);

						table.revalidate();
						table.repaint();

						table.setModel(modeloAbstracto);
						
						
					}

					@Override
					public void insertUpdate(DocumentEvent arg0) {
						String nombre = txtConsultar.getText();

						ArrayList<Carrera> carreraPorNombre = data.consultaPorNombre(nombre);

						modeloAbstracto.agregarCarreras(carreraPorNombre);

						gestion.modificarModeloAbstracto(modeloAbstracto);

						table.revalidate();
						table.repaint();

						table.setModel(modeloAbstracto);
						
						
					}

					@Override
					public void removeUpdate(DocumentEvent arg0) {
						String nombre = txtConsultar.getText();

						ArrayList<Carrera> carreraPorNombre = data.consultaPorNombre(nombre);

						modeloAbstracto.agregarCarreras(carreraPorNombre);

						gestion.modificarModeloAbstracto(modeloAbstracto);

						table.revalidate();
						table.repaint();

						table.setModel(modeloAbstracto);
						
					}
				});
				
				
				/*======EXPORTAR=====*/
				
			JComboBox exportarCmb = new JComboBox();
			exportarCmb.addItem("Excel");
			exportarCmb.addItem("HTML");
			exportarCmb.addItem("SQL");
			exportarCmb.addItem("XML");	
			
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					gestion.exportarExcel(modeloAbstracto);

				}	else if(metodoExportar.equals("HTML")) {
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
					} catch (IOException e1) {
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
					} catch (IOException e1) {
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
					} catch (IOException e1) {
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
		
	}

}
