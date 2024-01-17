package interfaces;
import java.util.ArrayList;
import entidad.Detalle;

public interface DetalleInterfacesDAO {
	public int registrar(Detalle c);
	public int actualizar(Detalle c);
	public int eliminar(int codigo);
	public ArrayList<Detalle> listarComprobante();
}
