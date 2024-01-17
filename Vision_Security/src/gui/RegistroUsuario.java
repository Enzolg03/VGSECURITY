package gui;


import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import entidad.TipoUsuario;
import entidad.Usuario;
import mantenimiento.GestionTipoUsuarioDAO;
import mantenimiento.GestionUsuarioDAO;
import utils.Validaciones;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import com.toedter.calendar.JDateChooser;

public class RegistroUsuario extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTable tblUSuario;
	
	DefaultTableModel model = new DefaultTableModel();
	GestionUsuarioDAO gUser = new GestionUsuarioDAO();
	GestionTipoUsuarioDAO gTip = new GestionTipoUsuarioDAO();
	
	private JPasswordField txtContraseña;
	private JDateChooser txtFecha;
	private JLabel lblNewLabel_7;
	private JTextField txtCodigo;
	private JButton btnBuscar;
	private JComboBox<String> cboTipo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroUsuario frame = new RegistroUsuario();
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
	public RegistroUsuario() {
		setClosable(true);
		setTitle("Mantenimiento-Usuario");
		setBounds(100, 100, 812, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(10, 39, 57, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 64, 57, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido:");
		lblNewLabel_2.setBounds(10, 89, 57, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("DNI:");
		lblNewLabel_3.setBounds(10, 114, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_4.setBounds(10, 139, 82, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Fecha:");
		lblNewLabel_5.setBounds(10, 164, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Tipo:");
		lblNewLabel_6.setBounds(10, 189, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnNuevo(e);
			}
		});
		btnNuevo.setBounds(3, 214, 89, 23);
		contentPane.add(btnNuevo);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnRegistrar(e);
			}
		});
		btnRegistrar.setBounds(3, 248, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnActualizar(e);
			}
		});
		btnActualizar.setBounds(3, 279, 89, 23);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnEliminar(e);
			}
		});
		btnEliminar.setBounds(3, 313, 89, 23);
		contentPane.add(btnEliminar);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(83, 36, 100, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(83, 61, 100, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(83, 86, 100, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(83, 111, 100, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		cboTipo = new JComboBox<String>();
		cboTipo.setBounds(83, 188, 100, 22);
		contentPane.add(cboTipo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(196, 26, 577, 312);
		contentPane.add(scrollPane);
		
		tblUSuario = new JTable();
		tblUSuario.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblUSuario);
		
		//columnas
		model.addColumn("Usuario");
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("DNI");
		model.addColumn("Contraseña");
		model.addColumn("Fecha");
		//
		tblUSuario.setModel(model);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(83, 136, 100, 20);
		contentPane.add(txtContraseña);
		
		txtFecha = new JDateChooser();
		txtFecha.setBounds(83, 164, 100, 20);
		contentPane.add(txtFecha);
		
		lblNewLabel_7 = new JLabel("Codigo:");
		lblNewLabel_7.setBounds(10, 14, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(50, 8, 62, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		btnBuscar = new JButton("Busca");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBuscar(e);
			}
		});
		btnBuscar.setBounds(122, 7, 61, 23);
		contentPane.add(btnBuscar);
		
		//
		cargarComboBox();
		
	}
	private void cargarComboBox() {
		//1.obtener el resultado del proceso listarTipoUsuario
		ArrayList<TipoUsuario> listaTipUser = gTip.listarTipoUsuario();
		//2.validar del comboBox desde la bd
		if(listaTipUser.size() ==0) {
			mensageError("Lista vacia");
		}else {
			cboTipo.addItem("selecciones");
			for(TipoUsuario tipUsuario : listaTipUser) {
				cboTipo.addItem(tipUsuario.getIdtipo()+"-"+tipUsuario.getDes_tipo());
			}
		}	
	}

	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		registrarDatos();	
	}

	private void registrarDatos() {

				String usuario,nomb,ape,dni,contra,fecReg;
				int tipo;
				usuario = getUsuario();
				nomb = getNombre();
				ape = getApellido();
				dni = getDni();
				contra = getContraseña();
				fecReg = getFechaRegistro();
				tipo= cboTipo.getSelectedIndex();

				
				if(usuario == null||nomb == null||ape == null||dni == null||contra == null ||fecReg ==null || tipo==0 ) {
					return;
				}else {

					Usuario u = new Usuario();
					u.setUsuario(usuario);
					u.setNombre(nomb);
					u.setApellido(ape);
					u.setDni(dni);
					u.setContra(contra);
					u.setFecReg(fecReg);
					u.setTipo(tipo);

					int res = gUser.registrar(u);
					if (res == 0) {
						mensageError("Error en el registro");
					} else {
						mensajeExitoso("Registro exitoso");
					}
				}
				Object fila []= {usuario,nomb,ape,dni,contra,fecReg};
				model.addRow(fila);
	}




	private void mensajeExitoso(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Sistema", 1);
		
	}

	private String getFechaRegistro() {
		String fecha = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		fecha = sdf.format(txtFecha.getDate());
		return fecha;
	}

	private String getContraseña() {
		String clave = null;

		clave = encriptado(String.valueOf(txtContraseña.getPassword()));
		return clave;
	}
	
	
	private String encriptado(String clave) {
				StringBuilder encriptado = new StringBuilder();
				encriptado.append(clave);
				for (int i = 0; i < encriptado.length(); i++) {

					switch (encriptado.charAt(i)) {
					case 'a':
						encriptado.setCharAt(i, 'e');
						break;
					case 'e':
						encriptado.setCharAt(i, 'i');
						break;
					case 'i':
						encriptado.setCharAt(i, 'o');
						break;
					case 'o':
						encriptado.setCharAt(i, 'u');
						break;
					case 'u':
						encriptado.setCharAt(i, 'a');
						break;
					}
				}
				return encriptado.reverse().toString();
	}

	private String getDni() {
		String dni = null;
		if (txtDni.getText().trim().length() == 0) {
			mensageError("Ingresar Nr DNI");
			txtDni.setText("");
			txtDni.requestFocus();
		} else if (txtDni.getText().trim().matches(Validaciones.DNI)) {
			dni = txtDni.getText().trim();
		} else {
			mensageError("formato incorrecto del usuario.Ej 12345678");
			txtDni.setText("");
			txtDni.requestFocus();
		}

		return dni;
	}

	private String getApellido() {
		String ape = null;
		if (txtApellido.getText().trim().length() == 0) {
			mensageError("Ingresar el apellido del usuario");
			txtApellido.setText("");
			txtApellido.requestFocus();
		} else if (txtApellido.getText().trim().matches(Validaciones.APELLIDO)) {
			ape = txtApellido.getText().trim();
		} else {
			mensageError("formato incorrecto, ingresar  de 4 a 25 letras");
			txtApellido.setText("");
			txtApellido.requestFocus();
		}
		return ape;
	}

	private String getNombre() {
		String nom = null;
		if (txtNombre.getText().trim().length() == 0) {
			mensageError("Ingresar el nombre del usuario");
			txtNombre.setText("");
			txtNombre.requestFocus();
		} else if (txtNombre.getText().trim().matches(Validaciones.NOMBRE)) {
			nom = txtNombre.getText().trim();
		} else {
			mensageError("formato incorrecto, ingresar  de 3 a 15 letras");
			txtNombre.setText("");
			txtNombre.requestFocus();
		}
		return nom;
	}

	private String getUsuario() {
		String user = null;
		if (txtUsuario.getText().trim().length() == 0) {
			mensageError("Ingresar usuario");
			txtUsuario.setText("");
			txtUsuario.requestFocus();
		} else if (txtUsuario.getText().trim().matches(Validaciones.USUARIO)) {
			user = txtUsuario.getText().trim();
		} else {
			mensageError("formato incorrecto del usuario.Ej U000");
			txtUsuario.setText("");
			txtUsuario.requestFocus();
		}

		return user;
	}
	
	private void mensageError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error !!!", 0);
	}
	
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		txtUsuario.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtDni.setText("");
		txtContraseña.setText("");
		txtFecha.setDate(null);
		
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualizarUsuario();
	}

	private void actualizarUsuario() {
		String usuario,nomb,ape,contra,fecReg,dni;
		int codigo;
		usuario = getUsuario();
		nomb = getNombre();
		ape = getApellido();
		dni = getDni();
		contra = getContraseña();
		fecReg = getFechaRegistro();
		codigo = getCodigo();
		if(codigo == -1||usuario == null||nomb == null||ape == null||dni == null||contra == null ||fecReg ==null ) {
			return;
		}else {
			Usuario u = new Usuario();
			u.setUsuario(usuario);
			u.setNombre(nomb);
			u.setApellido(ape);
			u.setDni(dni);
			u.setContra(contra);
			u.setFecReg(fecReg);
			u.setCodigo(codigo);

			int res = gUser.actualizar(u);

			if (res == 0) {
				mensageError("Error en la actualización");
			} else {
				mensajeExitoso("Usuario actulizado");
			}
		}
		Object fila []= {usuario,nomb,ape,dni,contra,fecReg};
		model.addRow(fila);
	}
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		buscarUsuario();
	}

	private void buscarUsuario() {
		int codigo;
		codigo = getCodigo();

		if (codigo == -1) {
			return;
		} else {

			Usuario user = gUser.buscarDatosUsuario(codigo);

			if (user == null) {
				mensageError("C�digo no existe");
			} else {
				txtNombre.setText(user.getNombre());
				txtApellido.setText(user.getApellido());
				txtUsuario.setText(user.getUsuario());
				txtContraseña.setText(user.getContra());
				txtDni.setText(user.getDni());
				try {
					txtFecha.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(user.getFecReg()));
				} catch (ParseException e) {
					System.out.println("Error en la fecha" + e.getMessage());
				}
			}

		}
		
	}

	private int getCodigo() {
		int cod = -1;
		if (txtCodigo.getText().trim().length() == 0) {
			mensageError("Ingresar el codigo del usuario");
			txtCodigo.setText("");
			txtCodigo.requestFocus();
		} else {
			try {
				cod = Integer.parseInt(txtCodigo.getText());
			} catch (NumberFormatException e) {
				mensageError("formato incorrecto, ingresar  solo numeros");
				txtCodigo.setText("");
				txtCodigo.requestFocus();
			}
		}
		return cod;
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {

		int codigo, opcion;

		codigo = getCodigo();

		if (codigo == -1) {
			return;
		} else {

			opcion = JOptionPane.showConfirmDialog(this, "Seguro de eliminar", "Sistema", JOptionPane.YES_NO_OPTION);
			if (opcion == 0) {

				int res = gUser.eliminar(codigo);

				if (res == 0) {
					mensageError("C�digo no existe");
				} else {
					mensajeExitoso("Usuario Eliminado");
					//cargarDataUsuarios();
				}
			}
		}
	}
}
