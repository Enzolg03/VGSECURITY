package interfaces;

import java.util.ArrayList;

import entidad.CabeceraBoleta;
import entidad.DetalleBoleta;

public interface VentasInterfacesDAO {
	//generar el nro de boleta
		public String numBoleta();
		
		//transaccion ---> realizar venta
		public int realizarVenta(CabeceraBoleta cBol,ArrayList<DetalleBoleta> dBol);
		//
		public ArrayList<CabeceraBoleta> listarBoleta(String fecha);
}
