package vista;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class Ayuda extends JPanel {

	/**
	 * Create the panel.
	 */
	public Ayuda() {
		setLayout(new MigLayout("", "[656.00]", "[][][][][][]"));
		
		JLabel lblAplicacinDesarrolladaPor = new JLabel("Aplicaci\u00F3n desarrollada por: Sergio Le\u00F3n");
		lblAplicacinDesarrolladaPor.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(lblAplicacinDesarrolladaPor, "cell 0 3,alignx center");
		
		JLabel lblAsignaturaProgramacindaw = new JLabel("Asignatura: Programaci\u00F3n/DAW/2019");
		lblAsignaturaProgramacindaw.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblAsignaturaProgramacindaw, "cell 0 5,alignx center");

	}

}
