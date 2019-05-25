package vista.carreras;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controlador.BBDDCarreras;
import controlador.GestionaCarreras;
import modelo.Poblacion;
import modelo.Provincia;
import net.miginfocom.swing.MigLayout;
import vista.CarrerasYcorredores;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class AgregarCar extends JPanel {
	private JTextField txtNombre;

	/**
	 * Create the panel.
	 */
	public AgregarCar() {
		GestionaCarreras gestion = new GestionaCarreras();
		BBDDCarreras data = new BBDDCarreras();
		setBorder(new TitledBorder(null, "AGREGAR CARRERAS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[][171.00,grow][grow,left]", "[][][][210.00,grow][grow]"));
		
		JLabel lblNombre = new JLabel("Nombre:");
		add(lblNombre, "cell 0 0,alignx trailing");
		
		txtNombre = new JTextField();
		add(txtNombre, "cell 1 0,growx");
		txtNombre.setColumns(10);
		
		JLabel lblDistancia = new JLabel("Distancia: ");
		add(lblDistancia, "cell 0 1,alignx trailing");
		
		JComboBox cmbDist = new JComboBox();
		
		
		cmbDist.setMaximumSize(new Dimension(200, 45));
		
		ImageIcon imageIcon1 = new ImageIcon("distancias/1.jpg"); 
		Image image = imageIcon1.getImage(); 
		Image dist1 = image.getScaledInstance(200, 45, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon2 = new ImageIcon("distancias/2.jpg"); 
		Image image2 = imageIcon2.getImage(); 
		Image dist2 = image2.getScaledInstance(200, 45, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon3 = new ImageIcon("distancias/3.jpg"); 
		Image image3 = imageIcon3.getImage(); 
		Image dist3 = image3.getScaledInstance(200, 45, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon4 = new ImageIcon("distancias/4.jpg"); 
		Image image4 = imageIcon4.getImage(); 
		Image dist4 = image4.getScaledInstance(200, 45, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon5 = new ImageIcon("distancias/5.jpg"); 
		Image image5 = imageIcon5.getImage(); 
		Image dist5 = image5.getScaledInstance(200, 45, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon6 = new ImageIcon("distancias/6.jpg"); 
		Image image6 = imageIcon6.getImage(); 
		Image dist6 = image6.getScaledInstance(200, 45, Image.SCALE_SMOOTH);
		
		DefaultComboBoxModel<Icon> cm = new DefaultComboBoxModel<Icon>();
		cm.addElement(new ImageIcon(dist1));
		cm.addElement(new ImageIcon(dist2));
		cm.addElement(new ImageIcon(dist3));
		cm.addElement(new ImageIcon(dist4));
		cm.addElement(new ImageIcon(dist5));
		cm.addElement(new ImageIcon(dist6));
		
		
		
		cmbDist.setModel(cm);

		add(cmbDist, "cell 1 1,growx");
		
		JLabel lblProvincia = new JLabel("Provincia:");
		add(lblProvincia, "cell 0 2,alignx trailing");
		
		JComboBox cmbProv = new JComboBox();
	
		add(cmbProv, "cell 1 2,growx");
		
		/*==============AGREGA LAS PROVINCIAS AL COMBO BOX=========================*/
		ArrayList<Provincia> prov = data.mostrarTodasLasProvincias();
		cmbProv.addItem("");
		for(int i = 0; i < prov.size(); i++) {
			cmbProv.addItem(prov.get(i).getName());

		}
		
		
		JLabel lblPoblacin = new JLabel("Poblaci\u00F3n:");
		add(lblPoblacin, "cell 0 3");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 3,grow");
		
		JButton btnInsertarDatos = new JButton("Insertar datos");

		add(btnInsertarDatos, "cell 1 4,alignx center,aligny center");
		
		JList lstPob = new JList();
		
		
		cmbProv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				DefaultListModel model = new DefaultListModel();
				String nombre = cmbProv.getSelectedItem().toString();
				ArrayList<Poblacion> poblaciones = data.consultarPoblacionesPorProvincia(nombre);
				for(int i = 0; i < poblaciones.size(); i++) {
					model.addElement(poblaciones.get(i).getNombre());
				}
						
				lstPob.setModel(model);	
				scrollPane.setViewportView(lstPob);
			}
		});
		

		btnInsertarDatos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String prov = "";
				String pob = "";
				String nombre = txtNombre.getText();
				int distancia = gestion.valorDistancia(cmbDist.getSelectedIndex());
				
				if(cmbProv.getSelectedItem() == null) {
					prov = "";
				} else {
					prov = cmbProv.getSelectedItem().toString();
				}
				if(lstPob.isSelectionEmpty()) {
					pob = "";
				} else {
					pob = lstPob.getSelectedValue().toString();
				}
				
				int resultado = gestion.valoresParaInsertar(nombre, distancia, prov, pob);
				if(resultado == 1) {
					data.insertarDatos(nombre, distancia, prov, pob);
				} else {
					gestion.mostrarAvisoError(resultado);
				}
			}
		});

	}

}
