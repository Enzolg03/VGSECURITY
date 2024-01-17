package mantenimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidad.Producto;
import interfaces.*;
import utils.MySQLConexion8;

public class GestionProductoDAO implements ProductoInterfacesDAO{

	@Override
	public int registrar(Producto p) {
		int res=0;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con=MySQLConexion8.getConexion();
			String sql="insert into tb_producto values (null,?,?,?,?,?)";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, p.getNombre());
			pstm.setString(2, p.getMarca());
			pstm.setString(3, p.getDescripcion());
			pstm.setDouble(4, p.getPrecio());
			pstm.setInt(5, p.getStock());
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
	public int actualizar(Producto p) {
		int res=0;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con=MySQLConexion8.getConexion();
			String sql="update tb_producto set Nombre=?,Marca=?,Descripcion=?,Precio=?,Stock=? where Codigo=?";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, p.getNombre());
			pstm.setString(2, p.getMarca());
			pstm.setString(3, p.getDescripcion());
			pstm.setDouble(4, p.getPrecio());
			pstm.setInt(5, p.getStock());
			pstm.setInt(6, p.getCodigo());
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
			String sql="delete from tb_producto where Codigo=?";
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
	public ArrayList<Producto> listarProducto() {
		ArrayList<Producto> listarProducto = new ArrayList<Producto>();
		PreparedStatement pstm = null;
		Connection con=null;
		ResultSet res = null;
		Producto pro = null;
		try {
			con=MySQLConexion8.getConexion();
			String sql = "select * from tb_producto";
			pstm = con.prepareStatement(sql);
			res = pstm.executeQuery();
			while(res.next()) {
			pro=new Producto();
			pro.setCodigo(res.getInt(1));
			pro.setNombre(res.getString(2));
			pro.setMarca(res.getString(3));
			pro.setDescripcion(res.getString(4));
			pro.setPrecio(res.getDouble(5));
			pro.setStock(res.getInt(6));
			listarProducto.add(pro);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstm !=null)pstm.close();
				if(con !=null)con.close();
				if(res !=null)res.close();
			} catch (Exception e2) {
				System.out.println("Error <<<<< en la instrucci�n consultar producto" + e2.getMessage());
			}
		}
		
		return listarProducto;
	}

	@Override
	public ArrayList<Producto> buscarProducto(String nombre) {
		ArrayList<Producto> lista=new ArrayList<Producto>();
		ResultSet res = null;
		PreparedStatement pstm = null;
		Producto prod = null;
		Connection con = null;
		try {
			//1. establecer  conexion con la BD
			con = MySQLConexion8.getConexion();
			//2. estableces la instruccion SQl -- consultar
			String sql = "{call usp_buscarProducto(?)}";
			//3
			pstm = con.prepareStatement(sql);
			//4
			pstm.setString(1, nombre);
			//5
			res = pstm.executeQuery();
			//6
			while(res.next()) {
				prod = new Producto();
				
				prod.setCodigo(res.getInt(1));
				prod.setNombre(res.getString(2));
				prod.setMarca(res.getString(3));
				prod.setDescripcion(res.getString(4));
				prod.setPrecio(res.getDouble(5));
				prod.setStock(res.getInt(6));
				
				lista.add(prod);
				
			}
			
		} catch (Exception e) {
			System.out.println("Error en el procedimiento almacenado de producto"+e.getMessage());
		}finally {
			try {
				if(pstm !=null);
				
			} catch (Exception e2) {
				System.out.println("Error al cerrar la BD"+e2.getMessage());
			}
		}
		
		return lista;
	}

}
