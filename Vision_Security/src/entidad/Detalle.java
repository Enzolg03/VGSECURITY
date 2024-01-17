package entidad;

public class Detalle {
	private int nrocompro,codpro,stockpro,cantipro;
	private String nompro;
	private double preciopro,totalpro,totalcompro;
	public Detalle() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Detalle(int nrocompro, int codpro, int stockpro, int cantipro, String nompro, double preciopro,
			double totalpro, double totalcompro) {
		super();
		this.nrocompro = nrocompro;
		this.codpro = codpro;
		this.stockpro = stockpro;
		this.cantipro = cantipro;
		this.nompro = nompro;
		this.preciopro = preciopro;
		this.totalpro = totalpro;
		this.totalcompro = totalcompro;
	}
	public int getNrocompro() {
		return nrocompro;
	}
	public void setNrocompro(int nrocompro) {
		this.nrocompro = nrocompro;
	}
	public int getCodpro() {
		return codpro;
	}
	public void setCodpro(int codpro) {
		this.codpro = codpro;
	}
	public int getStockpro() {
		return stockpro;
	}
	public void setStockpro(int stockpro) {
		this.stockpro = stockpro;
	}
	public int getCantipro() {
		return cantipro;
	}
	public void setCantipro(int cantipro) {
		this.cantipro = cantipro;
	}
	public String getNompro() {
		return nompro;
	}
	public void setNompro(String nompro) {
		this.nompro = nompro;
	}
	public double getPreciopro() {
		return preciopro;
	}
	public void setPreciopro(double preciopro) {
		this.preciopro = preciopro;
	}
	public double getTotalpro() {
		return totalpro;
	}
	public void setTotalpro(double totalpro) {
		this.totalpro = totalpro;
	}
	public double getTotalcompro() {
		return totalcompro;
	}
	public void setTotalcompro(double totalcompro) {
		this.totalcompro = totalcompro;
	}
	
}
