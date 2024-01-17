package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.Producto;
import mantenimiento.GestionProductoDAO;
import utils.Alertas;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DlgProducto extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JTextField txtNombreProducto;
	private JTable tblProductos;
	private JScrollPane scrollPane;
	//instancia
	DefaultTableModel model= new DefaultTableModel();
	GestionProductoDAO gProd = new GestionProductoDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgProducto dialog = new DlgProducto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgProducto() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblNewLabel = new JLabel("Producto:");
		lblNewLabel.setBounds(10, 11, 62, 14);
		contentPanel.add(lblNewLabel);
		
		txtNombreProducto = new JTextField();
		txtNombreProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				keyReleasedTxtNombreProducto(e);
			}
		});
		txtNombreProducto.setBounds(76, 8, 348, 20);
		contentPanel.add(txtNombreProducto);
		txtNombreProducto.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 414, 182);
		contentPanel.add(scrollPane);
		
		tblProductos = new JTable();
		tblProductos.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblProductos);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						actionPerformedOkButton(e);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		model.addColumn("Codigo");
		model.addColumn("Nombre");
		//model.addColumn("Descripcion");
		model.addColumn("Stock");
		model.addColumn("precio");
		//
		tblProductos.setModel(model);
		
	}
	
	private void listarProductos(String nombre) {
		//limpiar tabla
				model.setRowCount(0);
				//obtener los resultados para la estructura de la tabla
				ArrayList<Producto> lista= gProd.buscarProducto(nombre);
				//validar si el resultado del porceso y mostrar en la tabla
				if(lista.size()== 0) {
					Alertas.mensajeError("Lista vacia");
				}else {
					for(Producto pd : lista) {
						Object fila[] = {pd.getCodigo(),
										 pd.getNombre(),
										 //pd.getDescripcion(),
										 pd.getStock(),
										 pd.getPrecio()
						};
						model.addRow(fila);
					}
				}
			}
	
	protected void actionPerformedOkButton(ActionEvent e) {
		String codigo,nombre,stock,precio;
		int fila;
		//obtener el valor de la fila seleccionada
		fila = tblProductos.getSelectedRow();
		//obtener los datos de la fila seleccioanda
		codigo = tblProductos.getValueAt(fila,0).toString();
		nombre = tblProductos.getValueAt(fila,1).toString();
		stock = tblProductos.getValueAt(fila,2).toString();
		precio = tblProductos.getValueAt(fila,3).toString();
		//enviar los datos de las cajas de texto cotizar
		Cotizar.txtCodigoPro.setText(codigo);
		Cotizar.txtNombrePro.setText(nombre);
		Cotizar.txtStockPro.setText(stock);
		Cotizar.txtPrecioPro.setText(precio);
		//cerrar ventana actual
		this.dispose();
		
	}
	protected void keyReleasedTxtNombreProducto(KeyEvent e) {
		String nombre;
		//obtener el nombre ingresado
		nombre = getNombreProducto();
		//llamar al proceso
		listarProductos(nombre);
	}

	private String getNombreProducto() {
		String prod = null;
		prod = txtNombreProducto.getText().trim();
		return prod;
	}
}
