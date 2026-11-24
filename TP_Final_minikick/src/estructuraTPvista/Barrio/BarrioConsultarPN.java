package estructuraTPvista.Barrio;

import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import estructuraDAO.Sentencias;
import estructuraTP.Utilidades.Dialogo;
import estructuraTPmodelo.Comuna;
import estructuraTPmodelo.Barrios.Barrio;
import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;

public class BarrioConsultarPN extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel labelTitulo;
	private JLabel labelIngreseNombre;
	private JLabel labelNombre;
	private JTextField textField;
	private JTable table;
	private JButton botonModificar;
	
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JButton botonBuscar;
	private JButton botonBorrar;
	
	private boolean esUno = false; 
	private JButton botonVerMas;
	
	
	// Constructor para barrio
	public BarrioConsultarPN(String titulo) {
	    setLayout(null);
	    setBounds(800, 400, 800, 800);
	    crearLabels(titulo);
	    crearTabla();
	    actualizarPantalla(Sentencias.barrio.consultar());
	    usosBotonesBarrio();
	}
	
	private void crearLabels(String titulo) {

		labelTitulo = new JLabel(titulo);
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
		
		botonBuscar = new JButton("buscar");
		botonBuscar.setBounds(480, 113, 86, 29);
		add(botonBuscar);
		
		botonBorrar = new JButton("Borrar");
		botonBorrar.setBounds(583, 239, 145, 38);
		add(botonBorrar);
		
		labelIngreseNombre = new JLabel("Ingrese el nombre del Barrio");
		labelIngreseNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelIngreseNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelIngreseNombre.setBounds(70, 76, 380, 26);
		add(labelIngreseNombre);
		
		botonBorrar.setBounds(583, 301, 145, 38);
		add(botonBorrar);
		
		botonModificar = new JButton("Modificar");
		botonModificar.setBounds(583, 170, 145, 38);
		add(botonModificar);
		
		botonVerMas = new JButton("Ver detalle");
		botonVerMas.setBounds(583, 235, 145, 38);
		add(botonVerMas);
		
		
	}
	

	private void usosBotonesBarrio()
	{
		
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				actualizarPantalla(Sentencias.barrio.consultaBarrioPorAproximacion(textField.getText()));
			}
		});
		
		botonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if(fila <0 ) {
					Dialogo.mostrarDialogo("Seleccione una fila para borrar",BarrioConsultarPN.this);
				}
				else {
					Sentencias.barrio.eliminar(table.getValueAt(fila, 0).toString());
					Dialogo.mostrarDialogo("Se elimino con exito", BarrioConsultarPN.this);
					actualizarPantalla(Sentencias.barrio.consultar());
				}
			}
		});
		
		botonModificar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int fila = table.getSelectedRow();
		        if (fila < 0) {
		            Dialogo.mostrarDialogo("Seleccione una fila para modificar", BarrioConsultarPN.this);
		        } else {
		          
		        	String nombreViejo = table.getValueAt(fila, 0).toString();
                    ArrayList<Barrio> barrios = Sentencias.barrio.consultar();
		            Barrio b = null;
		            for (int i = 0; i < barrios.size(); i++) {
		                if (barrios.get(i).getNombre().equalsIgnoreCase(nombreViejo)) {
		                    b = barrios.get(i);
		                    break;
		                }
		            }
		            Dialogo.mostrarDialogoCargarBarrio(b, BarrioConsultarPN.this);

		            actualizarPantalla(Sentencias.barrio.consultar());
		        }
		    }
		});
		

		botonVerMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
		        if (fila < 0) {
		            Dialogo.mostrarDialogo("Seleccione una fila para modificar", BarrioConsultarPN.this);
		        } else {
		        	String nombreBarrio = table.getValueAt(fila, 0) + "";
		        	
		        	Barrio b = Sentencias.barrio.consultarPorNombre(nombreBarrio);
		        	
		        	Dialogo.mostrarDialogoConTablaBarrios(b, BarrioConsultarPN.this);
		        }
			}
		});
	}
	
	private void crearTabla() {
		table =  new JTable();
		table.setBounds(57, 122, 300, 112);
		
		Object[] columnas = new Object[] {"Nombre" , "Superficie", "Tipo Barrio" };
		
		modelo = new DefaultTableModel(columnas, 0);
		table.setDefaultEditor(Object.class, null);
		
		table.setModel(modelo);
		scrollPane.setViewportView(table);
	}
	
	private void actualizarPantalla(ArrayList<Barrio> barrios) {
		modelo.setRowCount(0);
		for(Barrio b: barrios) {
				if(b instanceof TrazadoFormal)
				{
					TrazadoFormal tf = (TrazadoFormal) b;
					modelo.addRow(new Object[] {tf.getNombre(), tf.getSuperficieEnHectarias() ,"Trazado Formal" });
				}
				else if(b instanceof ComplejoHabitacional)
				{
					ComplejoHabitacional ch = (ComplejoHabitacional) b;
					modelo.addRow(new Object[] {ch.getNombre(), ch.getSuperficie() , "Complejo Habitacional" });
				}
				else if(b instanceof UrbanizacionPlanificada)
				{
					UrbanizacionPlanificada up = (UrbanizacionPlanificada) b;
					modelo.addRow(new Object[] {up.getNombre(),up.getSuperficie() ,"Urbanizacion Planificada" });
				}

				
			}
		}
	}

