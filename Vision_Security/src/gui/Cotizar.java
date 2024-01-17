package gui;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.CabeceraBoleta;

import entidad.DetalleBoleta;
import mantenimiento.GestionDetalleDAO;
import mantenimiento.GestionVentasDAO;
import utils.Alertas;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Cotizar extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblOrdenDeCompra;
	private JLabel lblCliente;
	private JLabel lblRuc;
	private JLabel lblNewLabel;
	private JLabel lblApellido;
	private JLabel lblCantidad;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel_1;
	private JTextField txtNOrden;
	private JTextField txtFecha;
	private JTextField txtAño;
	private JLabel lblNombre;
	private JLabel lblPrecio;
	private JLabel lblStock;
	private JLabel lblCantidad_1;
	public static JTextField txtCodigoPro;
	public static JTextField txtNombreCli;
	public static JTextField txtApelliCli;
	private JLabel lblTipoDeDocumento;
	private JComboBox<String> cboTipoDoc;
	private JLabel lblNDocumento;
	public static JTextField txtNDoc;
	public static JTextField txtNombrePro;
	public static JTextField txtPrecioPro;
	public static JTextField txtStockPro;
	private JTextField txtCantida;
	private JPanel panel_3;
	private JButton btnAgregar;
	private JTable TOrden;
	private JPanel panel_4;
	private JButton btnNuevo;
	private JButton btnFinalizar;
	private JPanel panel_5;
	private JLabel lblTotal_1;
	private JTextField txtTotalOrden;
	private JScrollPane scrollPane;
	GestionDetalleDAO gDetalle = new GestionDetalleDAO();
	private JButton btnLimpiar;
	private JButton btnBuscaCliente;
	private JButton btnConsultaProducto;
	//
	double impTotal;
	DefaultTableModel model= new DefaultTableModel();
	GestionVentasDAO gVent = new GestionVentasDAO();
	ArrayList<DetalleBoleta> carrito = new ArrayList<DetalleBoleta>();
	public static JTextField txtCodigoCli;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cotizar frame = new Cotizar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cotizar() {
		setMaximizable(true);
		setFrameIcon(new ImageIcon(Cotizar.class.getResource("/img/carrito-de-compras.png")));
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 823, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblOrdenDeCompra = new JLabel("Orden de Compra");
		lblOrdenDeCompra.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOrdenDeCompra.setBounds(142, 30, 159, 28);
		contentPane.add(lblOrdenDeCompra);
		
		panel = new JPanel();
		panel.setBounds(423, 11, 374, 70);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("A\u00F1o");
		lblNewLabel.setBounds(267, 11, 86, 14);
		panel.add(lblNewLabel);
		
		lblRuc = new JLabel("Fecha");
		lblRuc.setBounds(171, 11, 79, 14);
		panel.add(lblRuc);
		
		lblCliente = new JLabel("Numero Documento");
		lblCliente.setBounds(53, 11, 118, 14);
		panel.add(lblCliente);
		
		txtNOrden = new JTextField();
		txtNOrden.setEditable(false);
		txtNOrden.setBounds(53, 36, 108, 20);
		panel.add(txtNOrden);
		txtNOrden.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setBounds(171, 36, 86, 20);
		panel.add(txtFecha);
		txtFecha.setColumns(10);
		
		txtAño = new JTextField();
		txtAño.setEditable(false);
		txtAño.setBounds(267, 36, 86, 20);
		panel.add(txtAño);
		txtAño.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setToolTipText("");
		panel_1.setBounds(10, 104, 787, 79);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 50, 58, 14);
		panel_1.add(lblApellido);
		
		lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(10, 25, 58, 14);
		panel_1.add(lblNewLabel_1);
		
		txtNombreCli = new JTextField();
		txtNombreCli.setBounds(66, 22, 128, 20);
		panel_1.add(txtNombreCli);
		txtNombreCli.setColumns(10);
		
		txtApelliCli = new JTextField();
		txtApelliCli.setBounds(66, 47, 128, 20);
		panel_1.add(txtApelliCli);
		txtApelliCli.setColumns(10);
		
		lblTipoDeDocumento = new JLabel("Tipo de Documento");
		lblTipoDeDocumento.setBounds(236, 25, 111, 14);
		panel_1.add(lblTipoDeDocumento);
		
		cboTipoDoc = new JComboBox<String>();
		cboTipoDoc.setModel(new DefaultComboBoxModel<String>(new String[] {"DNI"}));
		cboTipoDoc.setBounds(357, 22, 128, 20);
		panel_1.add(cboTipoDoc);
		
		lblNDocumento = new JLabel("N\u00AA Documento");
		lblNDocumento.setBounds(236, 50, 97, 14);
		panel_1.add(lblNDocumento);
		
		txtNDoc = new JTextField();
		txtNDoc.setBounds(357, 47, 128, 20);
		panel_1.add(txtNDoc);
		txtNDoc.setColumns(10);
		
		btnBuscaCliente = new JButton("");
		btnBuscaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBuscaCliente(e);
			}
		});
		btnBuscaCliente.setIcon(new ImageIcon(Cotizar.class.getResource("/img/busca.png")));
		btnBuscaCliente.setBounds(522, 25, 46, 43);
		panel_1.add(btnBuscaCliente);
		
		txtCodigoCli = new JTextField();
		txtCodigoCli.setColumns(10);
		txtCodigoCli.setBounds(601, 23, 128, 20);
		panel_1.add(txtCodigoCli);
		
		panel_2 = new JPanel();
		panel_2.setBounds(10, 199, 543, 70);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		lblCantidad = new JLabel("Codigo");
		lblCantidad.setBounds(10, 11, 43, 14);
		panel_2.add(lblCantidad);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(82, 11, 46, 14);
		panel_2.add(lblNombre);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(248, 11, 46, 14);
		panel_2.add(lblPrecio);
		
		lblStock = new JLabel("Stock");
		lblStock.setBounds(310, 11, 46, 14);
		panel_2.add(lblStock);
		
		lblCantidad_1 = new JLabel("Cantidad");
		lblCantidad_1.setBounds(370, 11, 62, 14);
		panel_2.add(lblCantidad_1);
		
		txtCodigoPro = new JTextField();
		txtCodigoPro.setEditable(false);
		txtCodigoPro.setBounds(10, 36, 62, 20);
		panel_2.add(txtCodigoPro);
		txtCodigoPro.setColumns(10);
		
		txtNombrePro = new JTextField();
		txtNombrePro.setEditable(false);
		txtNombrePro.setBounds(82, 36, 156, 20);
		panel_2.add(txtNombrePro);
		txtNombrePro.setColumns(10);
		
		txtPrecioPro = new JTextField();
		txtPrecioPro.setEditable(false);
		txtPrecioPro.setBounds(248, 36, 52, 20);
		panel_2.add(txtPrecioPro);
		txtPrecioPro.setColumns(10);
		
		txtStockPro = new JTextField();
		txtStockPro.setEditable(false);
		txtStockPro.setBounds(310, 36, 50, 20);
		panel_2.add(txtStockPro);
		txtStockPro.setColumns(10);
		
		txtCantida = new JTextField();
		txtCantida.setBounds(370, 36, 57, 20);
		panel_2.add(txtCantida);
		txtCantida.setColumns(10);
		
		btnConsultaProducto = new JButton("");
		btnConsultaProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnConsultaProducto(e);
			}
		});
		btnConsultaProducto.setIcon(new ImageIcon(Cotizar.class.getResource("/img/busca.png")));
		btnConsultaProducto.setBounds(471, 11, 46, 47);
		panel_2.add(btnConsultaProducto);
		
		panel_3 = new JPanel();
		panel_3.setBounds(563, 199, 234, 70);
		contentPane.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(this);
		panel_3.add(btnAgregar);
		
		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.addActionListener(this);
		panel_3.add(btnLimpiar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 280, 787, 131);
		contentPane.add(scrollPane);
		
		TOrden = new JTable();
		TOrden.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				keyReleasedTOrden(e);
			}
		});
		TOrden.setFillsViewportHeight(true);
		scrollPane.setViewportView(TOrden);
		
		panel_4 = new JPanel();
		panel_4.setBounds(10, 453, 355, 39);
		contentPane.add(panel_4);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		panel_4.add(btnNuevo);
		
		btnFinalizar = new JButton("Finalizar Compra");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnFinalizar(e);
			}
		});
		panel_4.add(btnFinalizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnEliminar(e);
			}
		});
		panel_4.add(btnEliminar);
		
		panel_5 = new JPanel();
		panel_5.setBounds(533, 422, 264, 70);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		lblTotal_1 = new JLabel("TOTAL");
		lblTotal_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal_1.setBounds(10, 5, 244, 14);
		panel_5.add(lblTotal_1);
		
		txtTotalOrden = new JTextField();
		txtTotalOrden.setEditable(false);
		txtTotalOrden.setBounds(10, 30, 244, 25);
		panel_5.add(txtTotalOrden);
		txtTotalOrden.setColumns(10);
		
		model.addColumn("Codigo");
		model.addColumn("Nombre");
		model.addColumn("Precio");
		model.addColumn("Cantidad");
		model.addColumn("Stock");	
		TOrden.setModel(model);
		
		//mostrar nro de boleta
		txtNOrden.setText(obtenerNumBoleta());
		txtFecha.setText(obtenerFecha());
		txtAño.setText(obtenerAño());
	
	
	}
	private String obtenerAño() {
		return new SimpleDateFormat("yyyy").format(new Date());
		
	}

	private String obtenerFecha() {
		return new SimpleDateFormat("MM/dd").format(new Date());
	}

	private String obtenerNumBoleta() {
		return gVent.numBoleta();
	}
	
	private String obtenerFechaCompleta() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
		if (e.getSource() == btnLimpiar) {
			actionPerformedBtnLimpiar(e);
		}
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(e);
		}
	}
	
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		//variables
		String codigo,nombre;
		double precio,importe;
		int cantidad,stock;
		//obtener los datos de la GUI
		codigo = leerCodigo();
		nombre = leerNombre();
		precio = leerPrecio();
		cantidad = leerCantidad();
		stock = leerStock();
		//calcular el importe -- proceso
		importe = calcImporte(cantidad,precio);
		impTotal += importe;
		
		//mostrar el total a la tabla
		Object fila[]= {codigo,nombre,precio,cantidad,stock};
		model.addRow(fila);
		//agrgar el total de los importes
		txtTotalOrden.setText("S/. "+impTotal);
		
		DetalleBoleta dbol = new DetalleBoleta(null, codigo, cantidad, precio,importe);
		carrito.add(dbol);
		limpiar();
		
	}
	
	
	private double calcImporte(int cantidad, double precio) {
		return cantidad * precio;
	}

	private int leerStock() {
		return Integer.parseInt(txtStockPro.getText());
	}

	private int leerCantidad() {	
		return Integer.parseInt(txtCantida.getText());
	}

	private double leerPrecio() {
		return Double.parseDouble(txtPrecioPro.getText());
	}

	private String leerNombre() {
		return txtNombrePro.getText();
	}

	private String leerCodigo() {
		return txtCodigoPro.getText();
	}

	protected void actionPerformedBtnLimpiar(ActionEvent e) {
		limpiar();
	}
	
	void limpiar() {
		txtCodigoPro.setText("");
		txtNombrePro.setText("");
		txtPrecioPro.setText("");
		txtStockPro.setText("");
		txtCantida.setText("");
		//txtTotalPro.setText("");
		txtCodigoPro.requestFocus();
	}

	@SuppressWarnings("unused")
	private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this, msj,"Error !!!",0);
	}

	@SuppressWarnings("unused")
	private void mensajeExitoso(String msj) {
		JOptionPane.showMessageDialog(this, msj,"Sistema",1);
	}
	protected void actionPerformedBtnBuscaCliente(ActionEvent e) {
		DlgCliente d = new DlgCliente();
		d.setVisible(true);
	}
	protected void actionPerformedBtnConsultaProducto(ActionEvent e) {
		DlgProducto d = new DlgProducto();
		d.setVisible(true);
	}
	protected void actionPerformedBtnFinalizar(ActionEvent e) {
		finalizarCompra();
		limpiar();
		LimpiarTabla();
		txtNombreCli.setText("");
		txtApelliCli.setText("");
		txtNDoc.setText("");
		txtCodigoCli.setText("");
	}
	
	private void finalizarCompra() {
		//declarar las variables --> obtener lsod atos para la clase cabeceraBoleta
		String numBol,fecha;
		int codCliente,codVendedor;
		double totalBol;
		//obtener los datos (GUI/sistm)
		numBol = obtenerNumBoleta();
		fecha = obtenerFechaCompleta();
		codCliente = leerCodCliente();
		codVendedor = obtenerCodVendedor();
		totalBol = impTotal;		
		//instanciar un objeto de la clase "CabeceraBoleta"
				CabeceraBoleta cBol = new CabeceraBoleta(numBol, fecha, codCliente, codVendedor, totalBol);				
				//llamar al proceso de transaccion
				int ok = gVent.realizarVenta(cBol, carrito);
				//validar el resultado de la transacción
				if(ok == 0) {
					Alertas.mensajeError("Error en la venta");
				}else {
					Alertas.mensajeExitoso("Venta OK");
				}		
		
	}

	private int obtenerCodVendedor() {
		return  Logueo.usuario.getCodigo();
	}

	private int leerCodCliente() {
		return Integer.parseInt(txtCodigoCli.getText());
	}
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		txtCodigoPro.setText("");
		txtNombrePro.setText("");
		txtPrecioPro.setText("");
		txtStockPro.setText("");
		txtCantida.setText("");
		//txtTotalPro.setText("");
		txtCodigoPro.requestFocus();
		
		txtCodigoCli.setText("");
		txtNombreCli.setText("");
		txtApelliCli.setText("");
		txtNDoc.setText("");
		txtTotalOrden.setText("");
		txtNOrden.setText(obtenerNumBoleta());
		txtFecha.setText(obtenerFecha());
		txtAño.setText(obtenerAño());
		LimpiarTabla();
	}
	private void mostrarData(int Fila) {
		String cod, nom, precio, stock, cantida;

		cod = TOrden.getValueAt(Fila, 0).toString();
		nom = TOrden.getValueAt(Fila, 1).toString();
		precio = TOrden.getValueAt(Fila, 2).toString();
		stock = TOrden.getValueAt(Fila, 3).toString();
		cantida = TOrden.getValueAt(Fila, 4).toString();
		

		txtCodigoPro.setText(cod);
		txtNombrePro.setText(nom);
		txtPrecioPro.setText(precio);
		txtStockPro.setText(stock);	
		txtCantida.setText(cantida);	
		
	}

	
	private void LimpiarTabla(){
		for (int i = 0; i < TOrden.getRowCount(); i++) {
			model.removeRow(i);
			i-=1;}		
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		model.removeRow(TOrden.getSelectedRow());
	}
	protected void keyReleasedTOrden(KeyEvent e) {
		int fila;
		fila = TOrden.getSelectedRow();
		mostrarData(fila); 
	}
}
