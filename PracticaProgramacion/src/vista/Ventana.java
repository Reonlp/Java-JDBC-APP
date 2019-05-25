package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.BBDDCarreras;
import vista.carreras.ModificarCar;
import vista.corredores.AgregarCor;
import vista.corredores.BorrarCor;
import vista.corredores.ConsultarCor;
import vista.corredores.ModifCor;
import vista.carreras.BorrarCar;
import vista.carreras.ConsultarCar;
import vista.carreras.AgregarCar;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Ventana extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BBDDCarreras baseDeDatos = new BBDDCarreras();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
					baseDeDatos.conexionDB();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		setTitle("Carreras y Corredores en Espa\u00F1a");


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 574);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Carreras");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmInsertar = new JMenuItem("1. Insertar");

		mnNewMenu.add(mntmInsertar);
		
		JMenuItem mntmConsultar = new JMenuItem("2. Consultar");

		mnNewMenu.add(mntmConsultar);
		
		JMenuItem mntmModificar = new JMenuItem("3. Modificar");

		mnNewMenu.add(mntmModificar);
		
		JMenuItem mntmBorrar = new JMenuItem("4. Borrar");

		mnNewMenu.add(mntmBorrar);
		
		JMenu mnCorredores = new JMenu("Corredores");
		menuBar.add(mnCorredores);
		
		JMenuItem corredorAgregar = new JMenuItem("1. Agregar");
		mnCorredores.add(corredorAgregar);
		
		JMenuItem corredorConsultar = new JMenuItem("2. Consultar");
		mnCorredores.add(corredorConsultar);
		
		JMenuItem corredorModificar = new JMenuItem("3. Modificar");
		mnCorredores.add(corredorModificar);
		
		JMenuItem corredorBorrar = new JMenuItem("4. Borrar");
		mnCorredores.add(corredorBorrar);
		
		JMenu mnExportar = new JMenu("Participación");
		menuBar.add(mnExportar);
		
		JMenuItem corredor_carreras = new JMenuItem("1. Mostrar participación");
		mnExportar.add(corredor_carreras);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem info = new JMenuItem("1. Info");
		mnAyuda.add(info);
		
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		CardLayout cl = new CardLayout();
		contentPane.setLayout(cl);
		
		JLabel lblNewLabel = new JLabel("Aplicaci\u00F3n para el mantenimiento de la base de datos de carreras y corredores en Espa\u00F1a");
		lblNewLabel.setIcon(null);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, "name_5053513042769");

		/*============ ACCIONES BOTONES MENU CARRERAS =================*/
		
		mntmInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.add(new AgregarCar(), "0");
				cl.show(contentPane, "0");
				
			}
		});
		
		mntmConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.add(new ConsultarCar(), "1");
				cl.show(contentPane, "1");
			}
		});
		
		mntmModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.add(new ModificarCar(), "2");
				cl.show(contentPane, "2");
			}
		});
		
		mntmBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.add(new BorrarCar(), "3");
				cl.show(contentPane, "3");
			}
		});
		
		/*========================================================*/
		
		/*============ ACCIONES BOTONES MENU CORREDORES =================*/
		corredorAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.add(new AgregarCor(), "0");
				cl.show(contentPane, "0");
				
			}
		});
		
		corredorConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.add(new ConsultarCor(), "1");
				cl.show(contentPane, "1");
				
			}
		});
		
		corredorModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.add(new ModifCor(), "2");
				cl.show(contentPane, "2");
				
			}
		});
		
		corredorBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.add(new BorrarCor(), "3");
				cl.show(contentPane, "3");
			}
		});
		
		corredor_carreras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.add(new CarrerasYcorredores(), "7");
				cl.show(contentPane, "7");
			}
		});
		
		info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.add(new Ayuda(), "0");
				cl.show(contentPane, "0");
			}
		});
		
		
		/*========================================================*/
	}
}
