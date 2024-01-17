package entidad;

public class TipoUsuario {
	//
	private int idtipo;
	private String des_tipo;
	//
	public TipoUsuario() {
		
	}
	//
	public TipoUsuario(int idtipo, String des_tipo) {
		super();
		this.idtipo = idtipo;
		this.des_tipo = des_tipo;
	}
	//
	public int getIdtipo() {
		return idtipo;
	}
	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}
	public String getDes_tipo() {
		return des_tipo;
	}
	public void setDes_tipo(String des_tipo) {
		this.des_tipo = des_tipo;
	}

}
