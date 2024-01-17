package entidad;

public class Usuario {
	private int codigo;
	private String usuario;
	private String nombre;
	private String apellido;
	private String dni;
	private String contra;
	private String fecReg;
	private int tipo;
	//
	public Usuario() {

	}
	//
	public Usuario(int codigo, String usuario, String nombre, String apellido, String dni, String contra, String fecReg,
			int tipo) {
		super();
		this.codigo = codigo;
		this.usuario = usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.contra = contra;
		this.fecReg = fecReg;
		this.tipo = tipo;
	}
	//
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getContra() {
		return contra;
	}
	public void setContra(String contra) {
		this.contra = contra;
	}
	public String getFecReg() {
		return fecReg;
	}
	public void setFecReg(String fecReg) {
		this.fecReg = fecReg;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}	

}
