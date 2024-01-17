package entidad;

public class ReporteUsuarioXTipo {
	//atributos
	private int codigo;
	private String nombCompleto;
	private String descripTipo;
	//
	public ReporteUsuarioXTipo() {
		
	}
	//
	public ReporteUsuarioXTipo(int codigo, String nombCompleto, String descripTipo) {
		super();
		this.codigo = codigo;
		this.nombCompleto = nombCompleto;
		this.descripTipo = descripTipo;
	}
	//
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombCompleto() {
		return nombCompleto;
	}
	public void setNombCompleto(String nombCompleto) {
		this.nombCompleto = nombCompleto;
	}
	public String getDescripTipo() {
		return descripTipo;
	}
	public void setDescripTipo(String descripTipo) {
		this.descripTipo = descripTipo;
	}
	
}
