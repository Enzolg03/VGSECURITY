package interfaces;

import java.util.ArrayList;

import entidad.ReporteUsuarioXTipo;
import entidad.Usuario;

public interface UsuarioInterfacesDAO {
	//registro
	public int registrar(Usuario u);
	//actualizar
	public int actualizar(Usuario u);
	//eliminar
	public int eliminar(int codigo);
	//buscar datos
	public Usuario buscarDatosUsuario(int codigo);
	//
	public ArrayList<ReporteUsuarioXTipo> reporteUsuariosXTipo(int tipo);
	//
	public ArrayList<Usuario> UsuariosXTipo(int tipo);
	//Validar Acceso
	public Usuario validarUsuario(String user,String contra);
}
