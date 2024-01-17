package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.TipoUsuario;
import interfaces.TipoUsuarioInterfacesDAO;
import utils.MySQLConexion8;

public class GestionTipoUsuarioDAO implements TipoUsuarioInterfacesDAO {

	@Override
	public ArrayList<TipoUsuario> listarTipoUsuario() {
		ArrayList<TipoUsuario> lista = new ArrayList<TipoUsuario>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		TipoUsuario tip;
		try {
			//1.conexion a la BD
			con = MySQLConexion8.getConexion();
			//2.intruccion Sql -- consulta
			String Sql = " SELECT * FROM tb_tipo";
			//3.prepara instruccion
			pstm = con.prepareStatement(Sql);
			//4.no hay
			//5.ejecucion de la instruccion
			res = pstm.executeQuery();
			//6.bucle para recorrido
			while(res.next()) {
				tip = new TipoUsuario();
				//setear
				tip.setIdtipo(res.getInt(1));
				tip.setDes_tipo(res.getString(2));
				//enviar a la lista
				lista.add(tip);
			}
			
		} catch (Exception e) {
			System.out.println(">>> Error en al instruccion SQL - listar tipo de usuario" +e.getMessage());
		}finally {
			try {
				if(pstm !=null)pstm.close();
				if(res != null)res.close();
				if(con != null)con.close();
				
			} catch (SQLException e2) {
				System.out.println(">>> Error al cerrar la base de datos" +e2.getMessage());
			}
		}
		
		return lista;
	}

}
