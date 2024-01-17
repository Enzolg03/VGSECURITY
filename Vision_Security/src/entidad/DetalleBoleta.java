package entidad;

public class DetalleBoleta {
		//atributos
		private String numBol;
		private String idProd;
		private int cant;
		private double precio;
		private double importe;
		//
		public DetalleBoleta() {
		
		}
		//
		public DetalleBoleta(String numBol, String idProd, int cant, double precio, double importe) {
			super();
			this.numBol = numBol;
			this.idProd = idProd;
			this.cant = cant;
			this.precio = precio;
			this.importe = importe;
		}
		//
		public String getNumBol() {
			return numBol;
		}
		public void setNumBol(String numBol) {
			this.numBol = numBol;
		}
		public String getIdProd() {
			return idProd;
		}
		public void setIdProd(String idProd) {
			this.idProd = idProd;
		}
		public int getCant() {
			return cant;
		}
		public void setCant(int cant) {
			this.cant = cant;
		}
		public double getPrecio() {
			return precio;
		}
		public void setPrecio(double precio) {
			this.precio = precio;
		}
		public double getImporte() {
			return importe;
		}
		public void setImporte(double importe) {
			this.importe = importe;
		}	

}
