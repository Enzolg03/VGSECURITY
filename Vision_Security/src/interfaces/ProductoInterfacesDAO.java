package interfaces;
import java.util.ArrayList;

import entidad.Producto;

public interface ProductoInterfacesDAO {
	public int registrar(Producto p);
	public int actualizar(Producto p);
	public int eliminar(int codigo);
	public ArrayList<Producto> listarProducto();
	//buscar por nombre producto
	public ArrayList<Producto> buscarProducto(String nombre);
}
