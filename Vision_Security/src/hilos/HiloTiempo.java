package hilos;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HiloTiempo extends Thread {
	
	private JLabel lblTiempo;
	private JFrame ventana;
	
	
	public HiloTiempo(JLabel lblTiempo, JFrame ventana) {
	
		this.lblTiempo = lblTiempo;
		this.ventana = ventana;
	}

	@Override
	public void run() {
		for (int i = 10; i >= 0; i--) {
			//mostrar el contador en la etiqueta lblTiempo
			lblTiempo.setText(i + "s");
			//System.out.println(i);
			//pausa
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("Error en el tiempo");
			}
		}
		//cerrar el formulario 
		ventana.dispose();
	}

}
