package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import entidad.CabeceraBoleta;
import entidad.DetalleBoleta;
import interfaces.VentasInterfacesDAO;
import utils.MySQLConexion8;

public class GestionVentasDAO implements VentasInterfacesDAO {

	@Override
	public String numBoleta() {
		String codigo = "B0001";
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			//1
			con = MySQLConexion8.getConexion();
			//2
			String sql = "SELECT COALESCE(SUBSTRING(MAX(num_bol), 2), 0) FROM tb_cab_boleta";
			//3
			pstm = con.prepareStatement(sql);
			//4.no
			//5
			res = pstm.executeQuery();
			//6
			if(res.next()) {
				DecimalFormat df = new DecimalFormat("0000");
				codigo = "B"+df.format(Integer.parseInt(res.getString(1))+1);//0001+1=0002
			}
			
		} catch (Exception e) {
			System.out.println("Error en la instrución SQL - generar nro boleta"+e.getMessage());
		}finally {
			try {
				if (pstm != null) pstm.close();
				if (res!= null) res.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la BD" + e2.getMessage());
			}
		}
		
		return codigo;
	}

	@Override
	public int realizarVenta(CabeceraBoleta cBol, ArrayList<DetalleBoleta> dBol) {
		int res = 0;
		Connection con = null;
		PreparedStatement pstm1 = null; // --> registrar los datos a la tb_cab_boleta
		PreparedStatement pstm2 = null; // --> registrar los datos a la tb_det_boleta
		PreparedStatement pstm3 = null; //---> actualizar la tabla productos(stock)

		try {
			//1.
			con = MySQLConexion8.getConexion();
			//2. Desactivar la confirmación automatico
			con.setAutoCommit(false);
			//3. Instruccion 1 --> registrar los datos a la tabla cab_boleta
			String sql1 = "insert into tb_cab_boleta values(?,?,?,?,?)";
			// comandos SQL
			pstm1 = con.prepareStatement(sql1);
			//parametros
			pstm1.setString(1,cBol.getNumBol());
			pstm1.setString(2,cBol.getFechaBol());
			pstm1.setInt(3,cBol.getCidCliente());
			pstm1.setInt(4,cBol.getCodVendedor());
			pstm1.setDouble(5,cBol.getTotalBoleta());
			//ejecutar
			res = pstm1.executeUpdate();

			//Instruccion 2 --> registrar lo datos a la tabla det_bol
			String sql2 = "insert into tb_det_boleta values(?,?,?,?,?)";
			for (DetalleBoleta detBoleta : dBol) {
				//comandos
				pstm2 = con.prepareStatement(sql2);
				//parametros
				pstm2.setString(1,cBol.getNumBol() );
				pstm2.setString(2, detBoleta.getIdProd());
				pstm2.setInt(3, detBoleta.getCant());
				pstm2.setDouble(4, detBoleta.getPrecio());
				pstm2.setDouble(5, detBoleta.getImporte());

				//ejecutar
				res = pstm2.executeUpdate();
			}
			//instruccion 3 : actualizar la tabla productos (stock)
			String sql3 ="update tb_producto set stock = stock - ? where Codigo = ?";
			for (DetalleBoleta detBoleta : dBol) {
				//
				pstm3 = con.prepareStatement(sql3);
				//
				pstm3.setInt(1, detBoleta.getCant());
				pstm3.setString(2,detBoleta.getIdProd());
				//
				res = pstm3.executeUpdate();
			}			
			//confirmacion de la transacción
			con.commit();

		} catch (Exception e) {
			System.out.println("Error al realizar la venta"+e.getMessage());
			res = 0;
			try {
				con.rollback(); // restablecer la base de datos un punto antes de la conexión
			} catch (Exception e2) {
				System.out.println("Error al restaurar la BD"+e2.getMessage());
			}
		}finally {
			try {
				if(pstm1 != null)pstm1.close();
				if(pstm2 != null)pstm2.close();
				if(pstm3 != null)pstm3.close();
				if(con != null)con.close();

			} catch (SQLException e2) {

				System.out.println("Error al cerrar la BD"+e2.getMessage());

			}

		}
		return res;
	}

	@Override
	public ArrayList<CabeceraBoleta> listarBoleta(String fecha) {
		ArrayList<CabeceraBoleta> listarBoleta = new ArrayList<CabeceraBoleta>();
		PreparedStatement pstm=null;
		Connection con=null;
		ResultSet res=null;
		CabeceraBoleta cBol = null;
		try {
			con=MySQLConexion8.getConexion();
			String sql="select * from tb_cab_boleta where fch_bol=?";
			pstm= con.prepareStatement(sql);
			pstm.setString(1, fecha);
			res=pstm.executeQuery();
			while (res.next()) {
				cBol= new CabeceraBoleta();
				cBol.setNumBol(res.getString(1));
				cBol.setFechaBol(res.getString(2));
				cBol.setCidCliente(res.getInt(3));
				cBol.setCodVendedor(res.getInt(4));
				cBol.setTotalBoleta(res.getDouble(5));
				listarBoleta.add(cBol);
			}
		} catch (Exception e) {
			System.out.println("Error >>> En la instruccion de consulta"+e.getMessage());
		}finally {
			try {
				if(pstm!=null)pstm.close();
				if(con!=null)con.close();
				if(res!=null)res.close();
			} catch (Exception e2) {
				System.out.println("Error >>> Al cerrar la base de datos"+e2.getMessage());
			}
		}
		return listarBoleta;
	}

	

}
