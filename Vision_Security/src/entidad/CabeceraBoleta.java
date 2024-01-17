package entidad;

public class CabeceraBoleta {
		//atributos privados
		private String numBol;
		private String fechaBol;
		private int cidCliente;
		private int codVendedor;
		private double totalBoleta;
		//
		public CabeceraBoleta() {
	
		}
		//
		public CabeceraBoleta(String numBol, String fechaBol, int cidCliente, int codVendedor, double totalBoleta) {
			super();
			this.numBol = numBol;
			this.fechaBol = fechaBol;
			this.cidCliente = cidCliente;
			this.codVendedor = codVendedor;
			this.totalBoleta = totalBoleta;
		}
		//
		public String getNumBol() {
			return numBol;
		}
		public void setNumBol(String numBol) {
			this.numBol = numBol;
		}
		public String getFechaBol() {
			return fechaBol;
		}
		public void setFechaBol(String fechaBol) {
			this.fechaBol = fechaBol;
		}
		public int getCidCliente() {
			return cidCliente;
		}
		public void setCidCliente(int cidCliente) {
			this.cidCliente = cidCliente;
		}
		public int getCodVendedor() {
			return codVendedor;
		}
		public void setCodVendedor(int codVendedor) {
			this.codVendedor = codVendedor;
		}
		public double getTotalBoleta() {
			return totalBoleta;
		}
		public void setTotalBoleta(double totalBoleta) {
			this.totalBoleta = totalBoleta;
		}
				
}
