package entidad;

public class Trabajador {
	private int codtra,dnitra,telftra;
	private double sueldo;
	private String nombtra,apelltra,domitra,cargotra;
	public Trabajador() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Trabajador(int codtra, int dnitra, int telftra, double sueldo, String nombtra, String apelltra,
			String domitra, String cargotra) {
		super();
		this.codtra = codtra;
		this.dnitra = dnitra;
		this.telftra = telftra;
		this.sueldo = sueldo;
		this.nombtra = nombtra;
		this.apelltra = apelltra;
		this.domitra = domitra;
		this.cargotra = cargotra;
	}
	public int getCodtra() {
		return codtra;
	}
	public void setCodtra(int codtra) {
		this.codtra = codtra;
	}
	public int getDnitra() {
		return dnitra;
	}
	public void setDnitra(int dnitra) {
		this.dnitra = dnitra;
	}
	public int getTelftra() {
		return telftra;
	}
	public void setTelftra(int telftra) {
		this.telftra = telftra;
	}
	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	public String getNombtra() {
		return nombtra;
	}
	public void setNombtra(String nombtra) {
		this.nombtra = nombtra;
	}
	public String getApelltra() {
		return apelltra;
	}
	public void setApelltra(String apelltra) {
		this.apelltra = apelltra;
	}
	public String getDomitra() {
		return domitra;
	}
	public void setDomitra(String domitra) {
		this.domitra = domitra;
	}
	public String getCargotra() {
		return cargotra;
	}
	public void setCargotra(String cargotra) {
		this.cargotra = cargotra;
	}
	
}
