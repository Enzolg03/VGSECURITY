package utils;

import javax.swing.JOptionPane;

public class Alertas {
	
	//Error 
	public static void mensajeError(String msj) {
			JOptionPane.showMessageDialog(null, msj, "Error !!!", 0);
		}

	//exitoso
	public static void mensajeExitoso(String msj) {
		JOptionPane.showMessageDialog(null, msj, "Sistema", 1);
		}

}
