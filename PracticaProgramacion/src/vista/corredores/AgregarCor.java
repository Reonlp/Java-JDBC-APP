package vista.corredores;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.BBDDCarreras;
import controlador.BBDDCorredores;
import controlador.GestionaCorredores;
import modelo.Carrera;
import modelo.Corredor;
import modelo.Poblacion;
import modelo.Provincia;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import net.miginfocom.swing.MigLayout;
import vista.carreras.ConsultarCar;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class AgregarCor extends JPanel {
	private JTextField txtNombre;
	private JTextField txtApellidos;

	/**
	 * Create the panel.
	 */
	public AgregarCor() {
		BBDDCorredores data = new BBDDCorredores();
		BBDDCarreras dataCar = new BBDDCarreras();
		GestionaCorredores gestion = new GestionaCorredores();
		
		/*IMAGENES PARA JAR*/
		AgregarCor.class.getResource("1.jpg");
		AgregarCor.class.getResource("2.jpg");
		AgregarCor.class.getResource("3.jpg");
		AgregarCor.class.getResource("4.jpg");
		AgregarCor.class.getResource("5.jpg");
		AgregarCor.class.getResource("6.jpg");
		
		setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Agregar Corredor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[][214.00,left][43.00][165.00,grow]", "[][35.00,grow][][][][grow][][30.00]"));
		
		JLabel lblAgregarUnNuevo = new JLabel("Agregar un nuevo corredor:");
		add(lblAgregarUnNuevo, "cell 1 0");
		
		JLabel lblAgregarUnaCarrera = new JLabel("Agregar una carrera a un corredor ya existente: ");
		add(lblAgregarUnaCarrera, "cell 3 0");
		
		JLabel lblNombre = new JLabel("Nombre:");
		add(lblNombre, "cell 0 1,alignx left");
		
		txtNombre = new JTextField();
		add(txtNombre, "cell 1 1,alignx left");
		txtNombre.setColumns(10);
		
		JPanel panel = new JPanel();
		add(panel, "cell 3 1 1 3,grow");
		panel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, "cell 0 0,grow");
		
		JList<Corredor> lstCorredores = new JList<Corredor>();
		
		scrollPane_1.setViewportView(lstCorredores);
		
		DefaultListModel<Corredor> modelCorredores = new DefaultListModel<Corredor>();
		

		ArrayList<Corredor> todosLosCorredores = data.mostrarTodosCorredores();
		for(int i = 0; i < todosLosCorredores.size(); i++) {
			modelCorredores.addElement(todosLosCorredores.get(i));

		}
		
		lstCorredores.setModel(modelCorredores);


		
		JLabel lblApellidos = new JLabel("Apellidos: ");
		add(lblApellidos, "cell 0 2,alignx trailing");
		
		txtApellidos = new JTextField();
		add(txtApellidos, "cell 1 2,growx");
		txtApellidos.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Edad:");
		add(lblNewLabel, "cell 0 3,alignx left");
		
		JSpinner spinnerEdad = new JSpinner();
		add(spinnerEdad, "cell 1 3,growx");
		
		JLabel lblProvincia = new JLabel("Provincia:");
		add(lblProvincia, "cell 0 4,alignx trailing");
		
		JComboBox cmbProv = new JComboBox();
		add(cmbProv, "cell 1 4,growx");
		
		JLabel lblCarreras = new JLabel("Carreras:");
		add(lblCarreras, "cell 3 4");
		
		JLabel lblPoblacin = new JLabel("Poblaci\u00F3n:");
		add(lblPoblacin, "cell 0 5");
		
		JScrollPane spinnerCarrera = new JScrollPane();
		add(spinnerCarrera, "cell 1 5,grow");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 3 5,grow");
		
		JList<Carrera> lstCarreras = new JList<Carrera>();
		JList<Carrera> lstCarrerasAviso = new JList<Carrera>();
		
		DefaultListModel<Carrera> modelCarreras = new DefaultListModel<Carrera>();
		DefaultListModel<Carrera> modelCarreras2 = new DefaultListModel<Carrera>();
		
		ArrayList<Carrera> todasLasCarreras = dataCar.consultarTodasLasCarreras();
		for(int i = 0; i < todasLasCarreras.size(); i++) {
			modelCarreras.addElement(todasLasCarreras.get(i));
			modelCarreras2.addElement(todasLasCarreras.get(i));
		}
		
		lstCarreras.setModel(modelCarreras);
		scrollPane.setViewportView(lstCarreras);
		


		JScrollPane scrollPane_3 = new JScrollPane();
		
		scrollPane_3.setViewportView(lstCarrerasAviso);

		lstCarrerasAviso.setModel(modelCarreras2);
		

		
		JButton btnAgr = new JButton("Insertar");

		add(btnAgr, "cell 1 7,alignx center");
		
		JButton btnAgregarCarreraA = new JButton("Agregar carrera a corredor");

		add(btnAgregarCarreraA, "cell 3 7,alignx center");
		
		ArrayList<Provincia> agregarProvincias = data.mostrarTodasLasProvincias();
		
		for(int i = 0; i < agregarProvincias.size(); i++) {
			cmbProv.addItem(agregarProvincias.get(i).getName());
		}
		
		JList lstPobAgrCar = new JList();
		
		cmbProv.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
			
						
				/*=======================LISTA DE POBLACIONES QUE SON AGREGADAS DINAMICAMENTE AL ELEGIR LA PROVINCIA EN EL ANTERIOR COMBO BOX======*/
				
			
				
				
				DefaultListModel model = new DefaultListModel();
				String nombre = cmbProv.getSelectedItem().toString();
				ArrayList<Poblacion> poblaciones = data.consultarPoblacionesPorProvincia(nombre);
				for(int i = 0; i < poblaciones.size(); i++) {
					model.addElement(poblaciones.get(i).getNombre());
				}
						
				lstPobAgrCar.setModel(model);	
				
				spinnerCarrera.setViewportView(lstPobAgrCar);
				
				
				lstPobAgrCar.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						if (!arg0.getValueIsAdjusting()) {
							
							int pob =  lstPobAgrCar.getSelectedIndex();
							String poblacion = poblaciones.get(pob).getNombre();
							
						}
						
					}
					
				});
				
				
			}
		});
		
		

		
		btnAgr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				String apellidos = txtApellidos.getText();
				int edad = (int) spinnerEdad.getValue();
				String provincia = cmbProv.getSelectedItem().toString();
				String pob = "";
				if(lstPobAgrCar.isSelectionEmpty()) {
					pob = "";
				} else {
					pob = lstPobAgrCar.getSelectedValue().toString();
				}
				
			
				

				
				int resultado = gestion.valoresParaInsertar(nombre, apellidos, edad, provincia, pob);
				if(resultado == 1) {
					int seleccion = JOptionPane.showOptionDialog(null, 
							"¿Desea agregar en este momento una carrera a este corredor?", "Seleccione una opción",
							JOptionPane.PLAIN_MESSAGE,
							JOptionPane.YES_NO_CANCEL_OPTION, 
							null, 
							new Object[] { "Sí", "No" },
							"No");
					
					if (seleccion == JOptionPane.YES_OPTION) {
				        int reply = JOptionPane.showOptionDialog(null, 
				        		scrollPane_3, 
				        		"Seleccione la carrera que desea añadir al corredor",
				        		JOptionPane.YES_NO_CANCEL_OPTION,
				        		JOptionPane.PLAIN_MESSAGE, null,    // null para icono por defecto.
								new Object[] { "Sí", "No"},   // null para YES, NO y CANCEL
								"No");
				        
				        if(reply == JOptionPane.YES_OPTION) {
				            try {
				            	int index = lstCarrerasAviso.getSelectedIndex();							
								int idCarrera = modelCarreras.get(index).getIdCarrera();
								data.insertarDatos(nombre, apellidos, edad, provincia, pob, idCarrera);
								
				            } catch (IndexOutOfBoundsException ep) {
								int seleccion3 = JOptionPane.showOptionDialog(null, 
										"Selecciona una carrera, por favor", "Resultado",
										JOptionPane.PLAIN_MESSAGE,
										JOptionPane.YES_NO_CANCEL_OPTION, 
										null, 
										new Object[] { "Ok" },
										"No");
				            }
							



				        } else {
							int seleccion2 = JOptionPane.showOptionDialog(null, 
									"No se ha agregado ningún corredor", "Resultado",
									JOptionPane.PLAIN_MESSAGE,
									JOptionPane.YES_NO_CANCEL_OPTION, 
									null, 
									new Object[] { "Ok" },
									"No");
				        }
				        

				        
					} else {
						//En este caso se agregará un corredor sin carrera
						System.out.println("hola");
						data.insertarCorredorSinCarrera(nombre, apellidos, edad, provincia, pob);
					}
					

					

									
				} else {
					
					gestion.mostrarAvisoError(resultado);
					
				}
				
			}
		});
		
		btnAgregarCarreraA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				int indexCorredores = lstCorredores.getSelectedIndex();
				int idCorredor = modelCorredores.get(indexCorredores).getIdCorredor();
				int indexCarreras = lstCarreras.getSelectedIndex();
				int idCarrera = modelCarreras.get(indexCarreras).getIdCarrera();
				data.insertarIdTablaIntermedia(idCarrera, idCorredor);
			
			}
		});		
	}
}
