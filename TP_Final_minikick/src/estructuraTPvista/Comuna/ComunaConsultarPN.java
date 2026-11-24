package estructuraTPvista.Comuna;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraDAO.Conexion;
import estructuraDAO.Sentencias;
import estructuraTP.Utilidades.Dialogo;
import estructuraTPmodelo.Comuna;
import estructuraTPmodelo.Barrios.Barrio;
import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;
import estructuraTPvista.Persona.PersonaConsultarPN;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ComunaConsultarPN extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel labelTitulo;
	private JLabel labelIngreseNombre;
	private JLabel labelNombre;
	private JTextField textField;
	private JTable table;
	private JButton botonModificar;
	
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JButton botonBorrar;
	private JButton botonVerDetalle;
		
	public ComunaConsultarPN() {
		setLayout(null);
		setBounds(800, 400, 800, 800);
		crearElementos();
		crearTabla();
		addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentShown(ComponentEvent e) {
		    	actualizarTabla(Sentencias.comuna.consultar());
		    }
		});
		usosBotones();
		
	}
	
	private void crearElementos() {
		labelTitulo = new JLabel("Consultar Comuna");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 31));
		labelTitulo.setBounds(266, 28, 300, 47);
		add(labelTitulo);
		
		labelNombre = new JLabel("Nombre");
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombre.setBounds(57, 113, 86, 26);
		add(labelNombre);
		
		textField = new JTextField();
		textField.setBounds(249, 117, 216, 26);
		add(textField);
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 170, 493, 301);
		add(scrollPane);
		
		labelIngreseNombre = new JLabel("Ingrese el nombre de la sede comunal");
		labelIngreseNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelIngreseNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelIngreseNombre.setBounds(70, 76, 380, 26);
		add(labelIngreseNombre);
		
		botonBorrar = new JButton("Borrar");
		botonBorrar.setBounds(583, 243, 145, 38);
		add(botonBorrar);
		
		botonVerDetalle = new JButton("Ver detalle");
		botonVerDetalle.setBounds(583, 237, 145, 38);
		add(botonVerDetalle);
		
		botonModificar = new JButton("Modificar");
		botonModificar.setBounds(583, 170, 145, 38);
		botonBorrar.setBounds(583, 301, 145, 38);
		add(botonModificar);
		
		
	}
	private void usosBotones() {
		// -------------- BUSCAR UNO.
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {	
				actualizarTabla(Sentencias.comuna.consultaPorAproximacion(textField.getText()));
			}
		});
				
		//- -------------- VER DETALLE
		botonVerDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if(fila < 0) {
					Dialogo.mostrarDialogo("Seleccione una fila para ver detalle", ComunaConsultarPN.this);
				}else {
					Comuna c = new Comuna(table.getValueAt(fila, 0).toString(), (int) table.getValueAt(fila, 1));
					ArrayList<Barrio> b = Sentencias.comuna.consultarComunaDetalle(c.getNumeroComuna() + "");
					System.out.println(b.size());
					Dialogo.comuna.mostrarDialogoConTablaComuna(c, b, ComunaConsultarPN.this);
				}
			}
		});
		// ---------------------- BORRAR LO SELECCIONADO
		botonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				int numeroComuna = (int) table.getValueAt(fila, 1);
				if(fila <0 ) {
					Dialogo.mostrarDialogo("Seleccione una fila para borrar",ComunaConsultarPN.this);
				}
				else {
					if((int) table.getValueAt(fila, 2) == 0) {
						Dialogo.mostrarDialogo("Se elimino con éxito", ComunaConsultarPN.this);
						Sentencias.comuna.eliminar(table.getValueAt(fila, 1).toString());
					}else {
						String nombre = Dialogo.barrio.mostrarDialogoReAsignarBarrios(table.getValueAt(fila, 0)+"", ComunaConsultarPN.this);
						Sentencias.barrio.actualizarLaComunaAlBorrar(nombre, numeroComuna);
						
						Sentencias.comuna.eliminar(table.getValueAt(fila, 1).toString());
					}
					
					actualizarTabla(Sentencias.comuna.consultar());
				}
			}
		});
		// ------------------------- MODIFICAR
		botonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if(fila <0 ) {
					Dialogo.mostrarDialogo("Seleccione una fila para modificar", ComunaConsultarPN.this);
				}
				else {
					Comuna c = new Comuna(table.getValueAt(fila, 0).toString(), (int)table.getValueAt(fila, 1));
					Dialogo.comuna.mostrarDialogoConCargarComuna(c, ComunaConsultarPN.this);
					actualizarTabla(Sentencias.comuna.consultar());
					}
				
			}
		});
	}
	
	private void crearTabla() {
		table =  new JTable();
		table.setBounds(57, 122, 300, 112);
		
		Object[] columnas = new Object[] {"Nombre" , "Numero", "Cantidad Barrios"};
		
		modelo = new DefaultTableModel(columnas, 0);
		table.setDefaultEditor(Object.class, null);
		
		table.setModel(modelo);
		scrollPane.setViewportView(table);
	}

	private void actualizarTabla(ArrayList<Comuna> comunas) {
		modelo.setRowCount(0);
		for(Comuna c: comunas) {
				modelo.addRow(new Object[] {c.getNombre(), c.getNumeroComuna(), c.getcantidarBarrios()});
		}
	
	}
	
	
	
	
}
