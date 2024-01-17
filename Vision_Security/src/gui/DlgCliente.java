package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.Usuario;
import mantenimiento.GestionUsuarioDAO;
import utils.Alertas;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgCliente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblClientes;
	//
	DefaultTableModel model= new DefaultTableModel();
	GestionUsuarioDAO gUser = new GestionUsuarioDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCliente dialog = new DlgCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCliente() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 414, 206);
			contentPanel.add(scrollPane);
			{
				tblClientes = new JTable();
				tblClientes.setFillsViewportHeight(true);
				scrollPane.setViewportView(tblClientes);
			}
		}
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
		model.addColumn("Apellido");
		model.addColumn("Nro.Documento");
		
		//model.addColumn("Descripcion");
		//
		tblClientes.setModel(model);
		//
		listarUsuarios();
	}

	private void listarUsuarios() {
		//limpiar tabla
		model.setRowCount(0);
		//obtener los resultados para la estructura de la tabla
		ArrayList<Usuario> listUser= gUser.UsuariosXTipo(3);
		//validar si el resultado del porceso y mostrar en la tabla
		if(listUser.size()== 0) {
			Alertas.mensajeError("Lista vacia");
		}else {
			for(Usuario us : listUser) {
				Object fila[] = {
						        us.getCodigo(),
						        us.getNombre(),
								us.getApellido(),
								us.getDni(),										
				};
				model.addRow(fila);
			}
		}
		
	}

	protected void actionPerformedOkButton(ActionEvent e) {
		enviarDatos();
	}

	private void enviarDatos() {
		String nomb,ape,dni,cod;
		int fila;
		
		//obtener el valor de la fila seleccionada
		fila = tblClientes.getSelectedRow();
		//obtener los datos de la fila seleccioanda
		cod = tblClientes.getValueAt(fila, 0).toString();
		nomb = tblClientes.getValueAt(fila,1).toString();
		ape = tblClientes.getValueAt(fila,2).toString();
		dni = tblClientes.getValueAt(fila, 3).toString();		
		//enviar los datos de las cajas de texto FrmBoleta
		Cotizar.txtCodigoCli.setText(cod);
		Cotizar.txtNombreCli.setText(nomb);	
		Cotizar.txtApelliCli.setText(ape);
		Cotizar.txtNDoc.setText(dni);
		
		//cerrar ventana actual
		this.dispose();
		
	}
}
