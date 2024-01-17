package gui;


import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import entidad.CabeceraBoleta;
import mantenimiento.GestionVentasDAO;

public class Reporte extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblReporte;
	private JLabel lblAo;
	private JPanel panel_1;
	private JTable TReporte;
	private JScrollPane scrollPane;
	private JButton btnImprimir;
	private JButton btnExportar;
	private JDateChooser txtFechaFiltrar;
	
	DefaultTableModel model = new DefaultTableModel();
	GestionVentasDAO gVen = new GestionVentasDAO();
	String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reporte frame = new Reporte();
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
	public Reporte() {
		setFrameIcon(new ImageIcon(Reporte.class.getResource("/img/estadisticase.png")));
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 823, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 120, 787, 74);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblAo = new JLabel("Fecha");
		lblAo.setBounds(10, 24, 46, 14);
		panel.add(lblAo);
		
		txtFechaFiltrar = new JDateChooser();
		txtFechaFiltrar.setDateFormatString("dd-MM-yyyy");
		txtFechaFiltrar.setBounds(63, 24, 216, 20);
		panel.add(txtFechaFiltrar);
		
		btnExportar = new JButton("EXPORTAR");
		btnExportar.setBounds(404, 24, 104, 23);
		panel.add(btnExportar);
		
		btnImprimir = new JButton("IMPRIMIR");
		btnImprimir.addActionListener(this);
		btnImprimir.setBounds(535, 24, 104, 23);
		panel.add(btnImprimir);
		btnExportar.addActionListener(this);
		
		lblReporte = new JLabel("REPORTE");
		lblReporte.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporte.setBounds(10, 50, 787, 42);
		contentPane.add(lblReporte);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 239, 787, 240);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 767, 219);
		panel_1.add(scrollPane);
		
		TReporte = new JTable();
		TReporte.setFillsViewportHeight(true);
		scrollPane.setViewportView(TReporte);
		
		model.addColumn("Nro_Boleta");
		model.addColumn("Cod_Cliente");
		model.addColumn("Cod_Vendedor");
		model.addColumn("Fecha");
		model.addColumn("Total");
		TReporte.setModel(model);
	}
	
	private String getFechaFiltro() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(txtFechaFiltrar.getDate());
	}
	
	private void listadoBoleta() {
		model.setRowCount(0);
		String fecha=getFechaFiltro();
		ArrayList<CabeceraBoleta> lista=gVen.listarBoleta(fecha);
		for(CabeceraBoleta dBol : lista) {
			Object fila[] = {dBol.getNumBol(),
					         dBol.getCidCliente(),
					         dBol.getCodVendedor(),
			                 dBol.getFechaBol(),
			                 dBol.getTotalBoleta()};
			model.addRow(fila);
		}
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnImprimir) {
			actionPerformedBtnImprimir(e);
		}
		if (e.getSource() == btnExportar) {
			actionPerformedBtnExportar(e);
		}
	}
	protected void actionPerformedBtnExportar(ActionEvent e) {
		listadoBoleta();
	}
	protected void actionPerformedBtnImprimir(ActionEvent e) {
		String nomArchivo = "reportes/listaProductos.pdf";
		try {
			Document plantilla = new Document();
			FileOutputStream fos = new FileOutputStream(nomArchivo);
			@SuppressWarnings("unused")
			PdfWriter pdfWt = PdfWriter.getInstance(plantilla, fos);
			plantilla.open();
			
			Paragraph p = new Paragraph("Listado de Productos",FontFactory.getFont("Arial",20,Font.BOLD,BaseColor.BLACK));
			p.setAlignment(Chunk.ALIGN_CENTER);
			plantilla.add(p);
			p = new Paragraph(" ");
			plantilla.add(p);
			p = new Paragraph(fecha, FontFactory.getFont("arial", 14, Font.BOLD, BaseColor.BLACK));
			p.setAlignment(Chunk.ALIGN_RIGHT);
			plantilla.add(p);
			p = new Paragraph(" ");
			plantilla.add(p);
			
			model.setRowCount(0);
			ArrayList<CabeceraBoleta> lista = gVen.listarBoleta(getFechaFiltro());
			if(lista.size()==0) {
				p = new Paragraph("Lista vacia",FontFactory.getFont("arial",14,Font.BOLD,BaseColor.RED));
				p.setAlignment(Chunk.ALIGN_CENTER);
				plantilla.add(p);
			}else {
				PdfPTable tabla = new PdfPTable(5);
				
				tabla.addCell("Nro_Boleta");
				tabla.addCell("Cod_Cliente");
				tabla.addCell("Cod_Vendedor");
				tabla.addCell("Fecha");
				tabla.addCell("Total");
				for(CabeceraBoleta cBod : lista) {
					tabla.addCell(cBod.getNumBol());
				    tabla.addCell(cBod.getCidCliente()+"");
				    tabla.addCell(cBod.getCodVendedor()+"");
				    tabla.addCell(cBod.getFechaBol()+"");
				    tabla.addCell(cBod.getTotalBoleta()+"");
					}
				plantilla.add(tabla);
				
			}			
			
			plantilla.close();
			Desktop.getDesktop().open(new File(nomArchivo));
			
		} catch (Exception e2) {
			System.out.println("Error al generar el reporte: " + e2.getMessage());
		}
	}
}
