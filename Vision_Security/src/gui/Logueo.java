package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidad.Usuario;
import hilos.HiloTiempo;
import mantenimiento.GestionUsuarioDAO;
import utils.Alertas;

import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;


public class Logueo extends JFrame implements WindowListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	public static Logueo frame;
	private JButton btnAceptar;
	private JLabel lblMensaje;
	private JLabel lblTiempo;
	//
	public static Usuario usuario=new Usuario();
	GestionUsuarioDAO gUser = new GestionUsuarioDAO();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Logueo();
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
	public Logueo() {
		addWindowListener(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Logueo.class.getResource("/img/avatar.png")));
		setTitle("Logueo- Acceso al Sistema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(231, 36, 96, 20);
		contentPane.add(lblUsuario);
		
		JLabel lblClave = new JLabel("Contrase\u00F1a:");
		lblClave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClave.setBounds(231, 81, 96, 20);
		contentPane.add(lblClave);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(363, 37, 103, 22);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(363, 83, 103, 20);
		contentPane.add(txtClave);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(259, 199, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(396, 199, 89, 23);
		contentPane.add(btnSalir);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(Logueo.class.getResource("/img/sitio-web.png")));
		lblFondo.setBounds(42, 65, 74, 109);
		contentPane.add(lblFondo);
		
		lblMensaje = new JLabel("Esta ventana se cerrara en");
		lblMensaje.setBounds(97, 11, 153, 14);
		contentPane.add(lblMensaje);
		
		lblTiempo = new JLabel("10s");
		lblTiempo.setBounds(259, 11, 38, 14);
		contentPane.add(lblTiempo);
		//
		//iniciarConteo();
	}
	//m�todo para iniciar conteo
	private void iniciarConteo() {
		//llamar al proceso
		HiloTiempo h = new HiloTiempo(lblTiempo,this);
		//iniciar proceso
		h.start();
		
	}
	public void windowActivated(WindowEvent e) {
	}
	public void windowClosed(WindowEvent e) {
	}
	public void windowClosing(WindowEvent e) {
	}
	public void windowDeactivated(WindowEvent e) {
	}
	public void windowDeiconified(WindowEvent e) {
	}
	public void windowIconified(WindowEvent e) {
	}
	public void windowOpened(WindowEvent e) {
		if (e.getSource() == this) {
			windowOpenedThis(e);
		}
	}
	protected void windowOpenedThis(WindowEvent e) {
		iniciarConteo();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAceptar) {
			actionPerformedBtnAceptar(e);
		}
	}
	
	private void validarAcceso() {
		String user, clave;
		user = getUsuario();
		clave = getClave();
		if(user == null || clave == null){
			return; 
			}else {
				usuario = gUser.validarUsuario(user, clave);
				if(usuario == null) {
					Alertas.mensajeError("Usuario y/o password incorrecto");
	 }else {
		 cargarBarraProgreso();
	 }
	}		
	}
	
	@SuppressWarnings("deprecation")
	private String getClave() {
		String clav = null;
		if(txtClave.getText().trim().length() == 0){
			Alertas.mensajeError("Ingresar la Clave");
			txtClave.requestFocus();
		}else{
			clav = String.valueOf(txtClave.getPassword());
		}
			return clav;
	}

	private String getUsuario() {
		String user = null;
		if(txtUsuario.getText().trim().length() == 0){
			Alertas.mensajeError("Ingresar el usuario");
			txtUsuario.requestFocus();
		}else{
			user = txtUsuario.getText().trim();
		}
			return user;
	}
	protected void actionPerformedBtnAceptar(ActionEvent e) {
		validarAcceso();
	}
	
	private void cargarBarraProgreso() {
		FrmPreLoader v = new FrmPreLoader();
		v.setVisible(true);
		v.setLocationRelativeTo(this);
		this.dispose();
	}
}
