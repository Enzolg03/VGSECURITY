package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Dimension;

public class Inicio extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnSistema;
	private JMenu mnCotizar;
	private JMenu mnReporte;
	private JMenuItem mntmSalir;
	private JDesktopPane escritorio;
	private JMenu mnMantenimiento;
	private JMenuItem mntmInventario;
	private JMenuItem mntmUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	public Inicio() {
		setTitle(Logueo.usuario.getNombre()+" "+Logueo.usuario.getApellido());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnSistema = new JMenu("Sistema");
		mnSistema.setMnemonic('S');
		mnSistema.setIcon(new ImageIcon(Inicio.class.getResource("/img/monitor.png")));
		menuBar.add(mnSistema);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(this);
		mntmSalir.setIcon(new ImageIcon(Inicio.class.getResource("/img/cancelar.png")));
		mnSistema.add(mntmSalir);
		
		mnCotizar = new JMenu("Cotizar");
		mnCotizar.addMouseListener(this);
		mnCotizar.addActionListener(this);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		mnMantenimiento.setIcon(new ImageIcon(Inicio.class.getResource("/imagenes/documento.png")));
		menuBar.add(mnMantenimiento);
		
		mntmInventario = new JMenuItem("Inventario");
		mntmInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmInventario(e);
			}
		});
		mntmInventario.setIcon(new ImageIcon(Inicio.class.getResource("/imagenes/monitor.png")));
		mnMantenimiento.add(mntmInventario);
		
		mntmUsuario = new JMenuItem("Usuario");
		mntmUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmUsuario(e);
			}
		});
		mntmUsuario.setIcon(new ImageIcon(Inicio.class.getResource("/imagenes/monitor.png")));
		mnMantenimiento.add(mntmUsuario);
		mnCotizar.setMnemonic('C');
		mnCotizar.setIcon(new ImageIcon(Inicio.class.getResource("/img/carrito-de-compras.png")));
		menuBar.add(mnCotizar);
		
		mnReporte = new JMenu("Reporte");
		mnReporte.addMouseListener(this);
		mnReporte.setMnemonic('R');
		mnReporte.setIcon(new ImageIcon(Inicio.class.getResource("/img/estadisticase.png")));
		menuBar.add(mnReporte);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		escritorio = new JDesktopPane();
		escritorio.setBackground(Color.LIGHT_GRAY);
		contentPane.add(escritorio, BorderLayout.CENTER);
		restringirPermisos();
	}
	
	private void restringirPermisos() {
		switch (Logueo.usuario.getTipo()) {
		case 2:		
			mnMantenimiento.removeActionListener(this);
			mnMantenimiento.setVisible(false);
			break;
		case 3:
			mnMantenimiento.removeMouseListener(this);
			mnMantenimiento.setVisible(false);
			mnCotizar.removeMouseListener(this);
			mnCotizar.setVisible(false);
		default:
			break;
		}
		
	}

	public void actionPerformed(ActionEvent arg0) {
		
		if (arg0.getSource() == mntmSalir) {
			actionPerformedMntmSalir(arg0);
		}
	}
	protected void actionPerformedMntmSalir(ActionEvent arg0) {
		System.exit(0);
	}
	
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == mnReporte) {
			mouseClickedMnReporte(arg0);
		}
		if (arg0.getSource() == mnCotizar) {
			mouseClickedMnCotizar(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedMnCotizar(MouseEvent arg0) {
		Cotizar c=new Cotizar();
		if(JInternalFrames_Abiertos(c)==false) {
			Dimension desktopSize= escritorio.getSize(); 
			Dimension FrameSize= c.getSize();
			c.setLocation((desktopSize.width-FrameSize.width)/2,(desktopSize.height-FrameSize.height)/2);
			escritorio.add(c);
			c.setVisible(true);
		}
		
	}
	protected void mouseClickedMnReporte(MouseEvent arg0) {
		Reporte r=new Reporte();
		if(JInternalFrames_Abiertos(r)==false) {
			Dimension desktopSize= escritorio.getSize(); 
			Dimension FrameSize= r.getSize();
			r.setLocation((desktopSize.width-FrameSize.width)/2,(desktopSize.height-FrameSize.height)/2);
			escritorio.add(r);
			r.setVisible(true);
		}
		
		
	}
	
	protected void actionPerformedMntmInventario(ActionEvent e) {
		Inventario i=new Inventario();
		if(JInternalFrames_Abiertos(i)==false) {
				Dimension desktopSize= escritorio.getSize(); 
				Dimension FrameSize= i.getSize();
				i.setLocation((desktopSize.width-FrameSize.width)/2,(desktopSize.height-FrameSize.height)/2);
				escritorio.add(i);
				i.setVisible(true);
		}
	
	}
	protected void actionPerformedMntmUsuario(ActionEvent e) {
		RegistroUsuario ri= new RegistroUsuario();
		if(JInternalFrames_Abiertos(ri)==false) {
			Dimension desktopSize= escritorio.getSize(); 
			Dimension FrameSize= ri.getSize();
			ri.setLocation((desktopSize.width-FrameSize.width)/2,(desktopSize.height-FrameSize.height)/2);
			escritorio.add(ri);
			ri.setVisible(true);
		}
		
	}
	
	public boolean JInternalFrames_Abiertos(JInternalFrame jif){ 
	     JInternalFrame [] jif_Activos = escritorio.getAllFrames(); 	 
	     for (int i = 0; i < jif_Activos.length; i++) { 
	          
	          if (jif.getClass().isInstance(jif_Activos[i])) {
	                JOptionPane.showMessageDialog(null, "La ventana que esta intentando abrir ya esta abierta.", "Información", JOptionPane.INFORMATION_MESSAGE);
	                return true;
	          }
	     }
	     return false;
	}
	
}
