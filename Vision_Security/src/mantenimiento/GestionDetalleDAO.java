package mantenimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import entidad.Detalle;
import interfaces.*;
import utils.MySQLConexion8;
public class GestionDetalleDAO implements DetalleInterfacesDAO{

	@Override
	public int registrar(Detalle d) {
		int res=0;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con=MySQLConexion8.getConexion();
			String sql="insert into tb_detalle values (null,?,?,?,?,?,?,?)";
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, d.getCodpro());
			pstm.setString(2, d.getNompro());
			pstm.setDouble(3, d.getPreciopro());
			pstm.setInt(4, d.getStockpro());
			pstm.setInt(5,d.getCantipro());
			pstm.setDouble(6,d.getTotalpro());
			pstm.setDouble(7,d.getTotalcompro());
			res=pstm.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("---ERROR >> En la instruccion sql de registrar"+e.getMessage());
		}finally {
			try {
				if(pstm !=null)pstm.close();
				if(con !=null)con.close();
			} catch (SQLException e2) {
				System.out.println("ERROR AL CERRAR LA BASE DE DATOS"+e2.getMessage());
			}
		}
		return res;
	}

	@Override
	public int actualizar(Detalle d) {
		int res=0;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con=MySQLConexion8.getConexion();
			String sql="update tb_detalle set cod_pro=?,precio_pro=?,stock_pro=?,canti_pro=?,total_pro=?,total_compro=? where nro_doc=?";
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, d.getCodpro());
			pstm.setString(2, d.getNompro());
			pstm.setDouble(3, d.getPreciopro());
			pstm.setInt(4, d.getStockpro());
			pstm.setInt(5,d.getCantipro());
			pstm.setDouble(6,d.getTotalpro());
			pstm.setDouble(7,d.getTotalcompro());
			pstm.setDouble(8,d.getNrocompro());
			
			res=pstm.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("---ERROR >> En la instruccion sql de actualizar"+e.getMessage());
		}finally {
			try {
				if(pstm !=null)pstm.close();
				if(con !=null)con.close();
			} catch (SQLException e2) {
				System.out.println("ERROR AL CERRAR LA BASE DE DATOS"+e2.getMessage());
			}
		}
		return res;
	}

	@Override
	public int eliminar(int codigo) {
		int res=0;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con=MySQLConexion8.getConexion();
			String sql="delete from tb_detalle where nro_doc=?";
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, codigo);
			res=pstm.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("---ERROR >> En la instruccion sql de eliminar"+e.getMessage());
		}finally {
			try {
				if(pstm !=null)pstm.close();
				if(con !=null)con.close();
			} catch (SQLException e2) {
				System.out.println("ERROR AL CERRAR LA BASE DE DATOS"+e2.getMessage());
			}
		}
		return res;
	}

	@Override
	public ArrayList<Detalle> listarComprobante() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
