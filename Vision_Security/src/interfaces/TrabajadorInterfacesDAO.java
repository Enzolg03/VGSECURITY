package interfaces;

import java.util.ArrayList;

import entidad.Trabajador;

public interface TrabajadorInterfacesDAO {
	public int registrar(Trabajador t);
	public int actualizar(Trabajador t);
	public int eliminar(int codigo);
	public ArrayList<Trabajador> listarTrabajador();
}
