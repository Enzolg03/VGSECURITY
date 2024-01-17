package gui;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.Producto;
import mantenimiento.*;
import utils.Validaciones;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Inventario extends JInternalFrame implements ActionListener, MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblInventario;
	private JPanel panel;
	private JPanel panel_1;
	private JTable TInventario;
	private JScrollPane scrollPane;
	DefaultTableModel model= new DefaultTableModel();
	GestionProductoDAO gProduct = new GestionProductoDAO();
	private JLabel lblCodigo;
	private JLabel lblNombre;
	private JLabel lblMarca;
	private JLabel lblDescripcion;
	private JLabel lblPrecio;
	private JLabel lblStock;
	private JButton btnLimpiar;
	private JButton btnExportar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnAgregar;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTextField txtDescripcion;
	private JTextField txtMarca;
	private JTextField txtNombre;
	private JTextField txtCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventario frame = new Inventario();
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
	public Inventario() {
		setClosable(true);
		setRootPaneCheckingEnabled(false);
		setFrameIcon(new ImageIcon(Inventario.class.getResource("/img/documento.png")));
		setBounds(100, 100, 823, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblInventario = new JLabel("INVENTARIO");
		lblInventario.setHorizontalAlignment(SwingConstants.CENTER);
		lblInventario.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblInventario.setBounds(21, 18, 776, 22);
		contentPane.add(lblInventario);
		
		panel = new JPanel();
		panel.setBounds(523, 51, 274, 416);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(20, 50, 46, 14);
		panel.add(lblCodigo);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(20, 83, 46, 14);
		panel.add(lblNombre);
		
		lblMarca = new JLabel("Marca");
		lblMarca.setBounds(20, 114, 46, 14);
		panel.add(lblMarca);
		
		lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(20, 145, 69, 14);
		panel.add(lblDescripcion);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(20, 172, 69, 14);
		panel.add(lblPrecio);
		
		lblStock = new JLabel("Stock");
		lblStock.setBounds(20, 199, 46, 14);
		panel.add(lblStock);
		
		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(29, 230, 104, 23);
		panel.add(btnLimpiar);
		
		btnExportar = new JButton("EXPORTAR");
		btnExportar.setBounds(30, 264, 103, 23);
		panel.add(btnExportar);
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(96, 297, 97, 23);
		panel.add(btnEliminar);
		
		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(136, 264, 117, 23);
		panel.add(btnActualizar);
		
		btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(136, 230, 117, 23);
		panel.add(btnAgregar);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(96, 199, 157, 20);
		panel.add(txtStock);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(96, 172, 157, 20);
		panel.add(txtPrecio);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(96, 145, 157, 20);
		panel.add(txtDescripcion);
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(96, 114, 157, 20);
		panel.add(txtMarca);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(96, 83, 157, 20);
		panel.add(txtNombre);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(96, 50, 157, 20);
		panel.add(txtCodigo);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 51, 503, 416);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 483, 394);
		panel_1.add(scrollPane);
		
		TInventario = new JTable();
		TInventario.addKeyListener(this);
		TInventario.addMouseListener(this);
		TInventario.setFillsViewportHeight(true);
		scrollPane.setViewportView(TInventario);
		model.addColumn("Codigo");
		model.addColumn("Marca");
		model.addColumn("Precio");
		model.addColumn("Nombre");
		model.addColumn("Descripcion");
		model.addColumn("Stock");	
		TInventario.setModel(model);
		cargarDatos();
		mostrarData(0);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLimpiar) {
			actionPerformedBtnLimpiar(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(e);
		}
	}
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		registrarDatos();
	}
	
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualizarProducto();
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		eliminarProducto();
	}
	protected void actionPerformedBtnLimpiar(ActionEvent e) {
		limpiar();
	}
	
	
	void limpiar() {
		txtCodigo.setText("");
		txtNombre.setText("");
		txtMarca.setText("");
		txtDescripcion.setText("");
		txtPrecio.setText("");
		txtStock.setText("");
		txtCodigo.requestFocus();
	}
	void registrarDatos(){
		String nomb,marca,descri;
		double precio;
		int stock;
		nomb=getNombre();
		marca=getMarca();
		descri=getDescripcion();
		precio=getprecio();
		stock=getStock();
		if(nomb==null||marca==null||descri==null||precio==-1||stock==-1)
		{
			return;
		}else{
			Producto p= new Producto();
			p.setNombre(nomb);
			p.setMarca(marca);
			p.setDescripcion(descri);
			p.setPrecio(precio);
			p.setStock(stock);
			int res=gProduct.registrar(p);
			if(res==0){
				mensajeError("Error en el registro");
			}else {
				mensajeExitoso("Registro exitoso");
				cargarDatos();
			}
			
		}
	}

		private void cargarDatos() {
			ArrayList<Producto> lista = gProduct.listarProducto();
			int posFila = 0;
			if (model.getRowCount() > 0)
				posFila = TInventario.getSelectedRow();
			if (model.getRowCount() == lista.size() - 1)
				posFila = lista.size() - 1;
			if (posFila == lista.size())
				posFila--;
			model.setRowCount(0);
			for (Producto p : lista) {
				Object fila[] = { p.getCodigo(), p.getNombre(), p.getMarca(), p.getDescripcion(), p.getPrecio(),
						p.getStock() };
				model.addRow(fila);
			}
			if (lista.size() > 0)
				TInventario.getSelectionModel().setSelectionInterval(posFila, posFila);
	}

		void actualizarProducto(){
		String nomb,marca,descri;
		double precio;
		int stock,codigo;
		codigo=getCodigo();
		nomb=getNombre();
		marca=getMarca();
		descri=getDescripcion();
		precio=getprecio();
		stock=getStock();
		if(codigo==-1||nomb==null||marca==null||descri==null||precio==-1||stock==-1)
		{
			return;
		}else{
			Producto p= new Producto();
			p.setNombre(nomb);
			p.setMarca(marca);
			p.setDescripcion(descri);
			p.setPrecio(precio);
			p.setStock(stock);
			p.setCodigo(codigo);
			int res=gProduct.actualizar(p);
			if(res==0){
				mensajeError("Error en la actualizacion");
			}else {
				mensajeExitoso("Actulizacion exitosa");
				cargarDatos();
			}
			
		}
	}
	
		void eliminarProducto(){
		int codigo,opcion;
		codigo=getCodigo();
		if(codigo==-1){
			return;
		}else{
			opcion= JOptionPane.showConfirmDialog(this, "Seguro de eliminar","Sistema",JOptionPane.YES_NO_OPTION);
			if(opcion==0){
				int res=gProduct.eliminar(codigo);
				if(res==0){
					mensajeError("Codigo no existe");
				}else{
					mensajeExitoso("Usuario eliminado");
					cargarDatos();
				}
					
			}
		}
	}
	
		private void mostrarData(int Fila) {
			String cod, nom, marca, desc, pre, stock;

			cod = TInventario.getValueAt(Fila, 0).toString();
			nom = TInventario.getValueAt(Fila, 1).toString();
			marca = TInventario.getValueAt(Fila, 2).toString();
			desc = TInventario.getValueAt(Fila, 3).toString();
			pre = TInventario.getValueAt(Fila, 4).toString();
			stock = TInventario.getValueAt(Fila, 5).toString();

			txtCodigo.setText(cod);
			txtNombre.setText(nom);
			txtMarca.setText(marca);
			txtDescripcion.setText(desc);	
			txtPrecio.setText(pre);	
			txtStock.setText(stock);
		}

	private int getCodigo() {
		int cod=-1;
		if(txtCodigo.getText().trim().length()==0)
		{
			mensajeError("Ingrese el codigo de usuario");
			txtCodigo.setText("");
			txtCodigo.requestFocus();
		
		}else{
			try {
				cod=Integer.parseInt(txtCodigo.getText());
			} catch (NumberFormatException e) {
				mensajeError("Formato incorrecto, ingresar solo numeros");
				txtCodigo.setText("");
				txtCodigo.requestFocus();
			}
		}
		
		return cod;
	}
	
	private String getNombre() {
		String nom=null;
		if(txtNombre.getText().trim().length()==0)
		{
			mensajeError("Ingrese el nombre del producto");
			txtNombre.setText("");
			txtNombre.requestFocus();
		}else if(txtNombre.getText().trim().matches(Validaciones.NOMBRE)){
			nom=txtNombre.getText().trim();
		}else{
			mensajeError("Formato incorrecto, ingresar de 3 a 15 letras");
			txtNombre.setText("");
			txtNombre.requestFocus();
		}
		
		return nom;
	}

	private String getMarca() {
		String marca=null;
		if(txtNombre.getText().trim().length()==0)
		{
			mensajeError("Ingrese la marca del producto");
			txtMarca.setText("");
			txtMarca.requestFocus();
		}else if(txtMarca.getText().trim().matches(Validaciones.MARCA)){
			marca=txtMarca.getText().trim();
		}else{
			mensajeError("Formato incorrecto, ingresar de 4 a 25 letras");
			txtMarca.setText("");
			txtMarca.requestFocus();
		}
		
		return marca;
	}

	private String getDescripcion() {
		String descri=null;
		if(txtDescripcion.getText().trim().length()==0)
		{
			mensajeError("Ingrese la descripcion del producto");
			txtDescripcion.setText("");
			txtDescripcion.requestFocus();
		}else if(txtDescripcion.getText().trim().matches(Validaciones.DESCRIPCION)){
			descri=txtDescripcion.getText().trim();
		}else{
			mensajeError("Formato incorrecto, ingresar de 9 a 45 letras");
			txtDescripcion.setText("");
			txtDescripcion.requestFocus();
		}
		
		return descri;
	}

	private double getprecio() {
		double precio=-1;
		if(txtPrecio.getText().trim().length()==0)
		{
			mensajeError("Ingrese el stock del producto");
			txtPrecio.setText("");
			txtPrecio.requestFocus();
		
		}else{
			try {
				precio=Double.parseDouble(txtPrecio.getText());
			} catch (NumberFormatException e) {
				mensajeError("Formato incorrecto, ingresar solo numeros");
				txtPrecio.setText("");
				txtPrecio.requestFocus();
			}
		}
		
		return precio;
	}

	private int getStock() {
		int stock=-1;
		if(txtStock.getText().trim().length()==0)
		{
			mensajeError("Ingrese el stock del producto");
			txtStock.setText("");
			txtStock.requestFocus();
		
		}else{
			try {
				stock=Integer.parseInt(txtStock.getText());
			} catch (NumberFormatException e) {
				mensajeError("Formato incorrecto, ingresar solo numeros");
				txtStock.setText("");
				txtStock.requestFocus();
			}
		}
		
		return stock;
	}

	private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this, msj,"Error !!!",0);
	}

	private void mensajeExitoso(String msj) {
		JOptionPane.showMessageDialog(this, msj,"Sistema",1);
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == TInventario) {
			mouseReleasedTInventario(e);
		}
	}
	protected void mouseReleasedTInventario(MouseEvent e) {
		int fila;
		fila = TInventario.getSelectedRow();
		mostrarData(fila);
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == TInventario) {
			keyReleasedTInventario(e);
		}
	}
	public void keyTyped(KeyEvent e) {
	}
	protected void keyReleasedTInventario(KeyEvent e) {
		int fila;
		fila = TInventario.getSelectedRow();
		mostrarData(fila);
	}
}
