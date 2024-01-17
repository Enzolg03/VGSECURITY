package hilos;

import javax.swing.JProgressBar;

import gui.FrmPreLoader;

public class HiloBarraProgreso extends Thread {
	
	private JProgressBar prbCarga;
	
	public HiloBarraProgreso(JProgressBar prbCarga, FrmPreLoader frmPreLoader) {
		this.prbCarga = prbCarga;
	}

	@Override
	public void run() {
		
		for (int i = 0; i <= 100; i++) {
			//mostrar el valor del contador en la barra de progreso
			prbCarga.setValue(i);
			//pausa
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Error en la pausa de la barra" + e.getMessage());
			}
			}
	}


}
