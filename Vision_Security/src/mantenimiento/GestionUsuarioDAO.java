package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.ReporteUsuarioXTipo;
import entidad.Usuario;
import interfaces.UsuarioInterfacesDAO;
import utils.MySQLConexion8;

public class GestionUsuarioDAO implements UsuarioInterfacesDAO {

	@Override
	public int registrar(Usuario u) {

				int res = 0;
				Connection con = null;
				PreparedStatement pstm = null;
				try {

					con = MySQLConexion8.getConexion();
					String sql = "insert into tb_usuario values (null,?,?,?,?,?,?,?,default);";
					pstm = con.prepareStatement(sql);
					pstm.setString(1, u.getUsuario());
					pstm.setString(2, u.getNombre());
					pstm.setString(3, u.getApellido());
					pstm.setString(4, u.getDni());
					pstm.setString(5, u.getContra());
					pstm.setString(6, u.getFecReg());
					pstm.setInt(7, u.getTipo());
					res = pstm.executeUpdate();
					
				} catch (Exception e) {
					System.out.println("Error >>> en la intrucci�n SQL de registrar "+e.getMessage());
				}finally {
					try {
						if(pstm !=null)pstm.close();
						if(con !=null)con.close();
					} catch (SQLException e2) {
						System.out.println("Error al cerrar la BD"+e2.getMessage());
					}
				}
				return res ;
	}

	@Override
	public int actualizar(Usuario u) {
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			//paso 1:establecer la conexxi�n con la BD
			con = MySQLConexion8.getConexion();
			//paso 2:establecer la instruccion SQL --->registrar
			String sql = "update tb_usuario set usuario =?,nombre =?,apellido =?,dni =?,contraseña =?,fnaReg =? where codigo =?";
			//paso 3:obtener 
			pstm = con.prepareStatement(sql);
			//paso 4:parametros
			pstm.setString(1, u.getUsuario());
			pstm.setString(2, u.getNombre());
			pstm.setString(3, u.getApellido());
			pstm.setString(4, u.getDni());
			pstm.setString(5, u.getContra());
			pstm.setString(6, u.getFecReg());
			pstm.setInt(7, u.getCodigo());
			//paso 5:
			res = pstm.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Error >>> en la intruccion SQL de actualizar "+e.getMessage());
		}finally {
			try {
				if(pstm !=null)pstm.close();
				if(con !=null)con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar la BD"+e2.getMessage());
			}
		}
		return res ;
	}

	@Override
	public Usuario buscarDatosUsuario(int codigo) {
		Usuario user = null;
		PreparedStatement pstm = null;
		Connection con = null;
		ResultSet res = null;
		try {
			//1. conectar al BD
			con = MySQLConexion8.getConexion();
			//2. establecer la instruccion SQL - consultar
			String sql = "select * from tb_usuario where codigo = ? ";
			//3. enviar la instruccion SQL al objeto pstm para obtener los comandos
			pstm = con.prepareStatement(sql);
			//4. paramentros
			pstm.setInt(1, codigo);
			//5. ejecutar la instruccion
			res = pstm.executeQuery();
			//bucle
			if(res.next()) {
				user = new Usuario(res.getInt(1),//codigo
						           res.getString(2),//nombre
						           res.getString(3),//apellido
						           res.getString(4), //usuario
						           res.getString(5), //clave
						           res.getString(6),//fecha
						           res.getString(7),//fecha
						           res.getInt(8));					         
			}
			
		} catch (Exception e) {
			System.out.println("Error <<<<< en la instruccion consultar usuario" + e.getMessage());
		}finally {
			try {
				if(pstm != null) pstm.close();
				if(res != null)res.close();
				if(con != null)con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar la bd" + e2.getMessage());
			}
		}
		
		return user;
	}

	@Override
	public int eliminar(int codigo) {
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			//paso 1:establecer la conexxi�n con la BD
			con = MySQLConexion8.getConexion();
			//paso 2:establecer la instruccion SQL --->actualizar
			String sql = "delete from tb_usuario where codigo =?";
			//paso 3:obtener 
			pstm = con.prepareStatement(sql);
			//paso 4:parametros
			pstm.setInt(1, codigo);
			//paso 5:
			res = pstm.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Error >>> en la intrucci�n SQL de eliminar "+e.getMessage());
		}finally {
			try {
				if(pstm !=null)pstm.close();
				if(con !=null)con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar la BD"+e2.getMessage());
			}
		}
		return res ;
	}

	@Override
	public ArrayList<ReporteUsuarioXTipo> reporteUsuariosXTipo(int tipo) {		
		ArrayList<ReporteUsuarioXTipo> listaUsuarios = new ArrayList<ReporteUsuarioXTipo>(); //null
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		ReporteUsuarioXTipo repUser = null;
		try {
			//1. establecer  conexion con la BD
			con = MySQLConexion8.getConexion();
			//2. estableces la instruccion SQl -- consultar
			String sql = "{call usp_listarUsuarioTipo(?)}";
			//3. enviar la instruccion sql al objetivo "pstm" para obtener los comandos SQL
			pstm = con.prepareStatement(sql);
			//4.parametros --- no hay
			pstm.setInt(1, tipo);
			//5.ejecutar la instruccion
			res = pstm.executeQuery();
			//6. bucle para realizar el recorrido en el objetos "res"
			while(res.next()) {
				//crear un objeto "usuario"
				repUser = new ReporteUsuarioXTipo();
				//setear(asignar los valores obtenidos de la consulta de la BD hacia los atributos privados
				repUser.setCodigo(res.getInt(1));
				repUser.setNombCompleto(res.getString(2));
				repUser.setDescripTipo(res.getString(3));
				
				//a�adir objeto a la lista
				listaUsuarios.add(repUser);
			}
				
		} catch (Exception e) {
			System.out.println("Error>> en la instruccion de consulta por tipo" + e.getMessage());
		}finally {
			try {
				if (pstm != null) pstm.close();
				if (res!= null) res.close();
				if (con != null) con.close();
				
			} catch (Exception e2) {
				System.out.println("Error al cerrar la BD" + e2.getMessage());
			}
		}
		
		
		return listaUsuarios;
	}

	@Override
	public ArrayList<Usuario> UsuariosXTipo(int tipo) {
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>(); //null
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		Usuario repUser = null;
		try {
			//1. establecer  conexion con la BD
			con = MySQLConexion8.getConexion();
			//2. estableces la instruccion SQl -- consultar
			String sql = "{call usp_tipoUsuario(?)}";
			//3. enviar la instruccion sql al objetivo "pstm" para obtener los comandos SQL
			pstm = con.prepareStatement(sql);
			//4.parametros --- no hay
			pstm.setInt(1, tipo);
			//5.ejecutar la instruccion
			res = pstm.executeQuery();
			//6. bucle para realizar el recorrido en el objetos "res"
			while(res.next()) {
				//crear un objeto "usuario"
				repUser = new Usuario();
				//setear(asignar los valores obtenidos de la consulta de la BD hacia los atributos privados
				repUser.setCodigo(res.getInt(1));
				repUser.setNombre(res.getString(2));
				repUser.setApellido(res.getString(3));
				repUser.setDni(res.getString(4));		
				
				//a�adir objeto a la lista
				listaUsuarios.add(repUser);
			}
				
		} catch (Exception e) {
			System.out.println("Error>> en la instruccion de consulta por tipo" + e.getMessage());
		}finally {
			try {
				if (pstm != null) pstm.close();
				if (res!= null) res.close();
				if (con != null) con.close();
				
			} catch (Exception e2) {
				System.out.println("Error al cerrar la BD" + e2.getMessage());
			}
		}
		
		
		return listaUsuarios;
	}

	@Override
	public Usuario validarUsuario(String user, String contra) {
		Usuario usuario = null;;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = MySQLConexion8.getConexion();
			String sql = "{call usp_validarAcesso(?,?)}";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user);
			pstm.setString(2, contra);
			res = pstm.executeQuery();
			if(res.next()) {
				usuario = new Usuario(res.getInt(1),
						              res.getString(2),
						              res.getString(3),
						              res.getString(4),
						              res.getString(5),
						              res.getString(6),
						              res.getString(7),
						              res.getInt(8));						              
			}
				
		} catch (Exception e) {
			System.out.println("Error en el procedimiento almacenado"+e.getMessage());
		}finally {
			try {
				if(pstm != null)pstm.close();
				if(res != null)res.close();
				if(con != null)con.close();
				
			} catch (SQLException e2) {
				System.out.println("Error al cerrar la BD"+e2.getMessage());
			}
		}
		return usuario;
	}

}
