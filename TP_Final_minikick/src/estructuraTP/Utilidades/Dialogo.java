package estructuraTP.Utilidades;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.awt.GridBagLayout;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import estructuraDAO.Sentencias;
import estructuraTPmodelo.Comuna;
import estructuraTPmodelo.Barrios.Barrio;
import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;
import estructuraTPvista.Barrio.BarrioAltaPN;
import estructuraTPvista.Comuna.ComunaAltaPN;

public abstract class Dialogo {
	private static String nombreComuna = null;	
	
	public static void mostrarDialogo(String mensaje, JPanel panel) {
        Window parentWindow = SwingUtilities.getWindowAncestor(panel);
        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parentWindow);
        JButton botonCerrar = new JButton("Cerrar");
        JLabel lblMensaje = new JLabel(mensaje, SwingConstants.CENTER);
        
        botonCerrar.addActionListener(e -> dialog.dispose());

        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(lblMensaje, BorderLayout.CENTER);
        dialog.getContentPane().add(botonCerrar, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
	
	public static void mostrarDialogoConTablaComuna(Comuna c,ArrayList<Barrio> b, JPanel panel) {
		
		Window parentWindow = SwingUtilities.getWindowAncestor(panel);

        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(590, 320);
        dialog.setLocationRelativeTo(parentWindow);
        JButton botonCerrar = new JButton("Cerrar");
        JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 170, 493, 261);
		
		Object[] columnas = new Object[] {"Nombre" , "Numero", "Barrio", "Tipo Barrio"};
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
		 
        JTable tabla =  new JTable();
		tabla.setDefaultEditor(Object.class, null);
		tabla.setBounds(57, 122, 300, 112);
		for(Barrio ba: b) {
			if(ba instanceof TrazadoFormal) {
				TrazadoFormal tz = (TrazadoFormal) ba;
				modelo.addRow(new Object[] {c.getNombre(), c.getNumeroComuna(), tz.getNombre(), "Trazado Formal"});
			}else if (ba instanceof UrbanizacionPlanificada) {
				UrbanizacionPlanificada up = (UrbanizacionPlanificada) ba;
				modelo.addRow(new Object[] {c.getNombre(), c.getNumeroComuna(), up.getNombre(), "Urbanizacion Planificada"});
			}else if(ba instanceof ComplejoHabitacional) {
				ComplejoHabitacional up = (ComplejoHabitacional) ba;
				modelo.addRow(new Object[] {c.getNombre(), c.getNumeroComuna(), up.getNombre(), "Complejo habitacional"});
			}
			
		}
		
		tabla.setModel(modelo);
		scrollPane.setViewportView(tabla);
        
        
        botonCerrar.addActionListener(e -> dialog.dispose());

        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);
        dialog.getContentPane().add(botonCerrar, BorderLayout.SOUTH);

        dialog.setVisible(true);
	}
	
	// TABLA DE LOS DISTINTOS BARRIOS
public static void mostrarDialogoConTablaB(Barrio b, JPanel panel) {
		
		Window parentWindow = SwingUtilities.getWindowAncestor(panel);

        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(590, 320);
        dialog.setLocationRelativeTo(parentWindow);
        JButton botonCerrar = new JButton("Cerrar");
        JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 170, 493, 261);
		
		Object[] columnas = new Object[] {"Nombre" , "Superficie", "Barrio", "Tipo Barrio"};
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
		 
        JTable tabla =  new JTable();
		tabla.setDefaultEditor(Object.class, null);
		tabla.setBounds(57, 122, 300, 112);
		
			if(b instanceof TrazadoFormal) {
				TrazadoFormal tz = (TrazadoFormal) b;
				modelo.addRow(new Object[] {tz.getNombre(), tz.getSuperFicieEnHectarias(), tz.getNombre(), "Trazado Formal"});
			}else if (b instanceof UrbanizacionPlanificada) {
				UrbanizacionPlanificada up = (UrbanizacionPlanificada) b;
				modelo.addRow(new Object[] {up.getNombre(), up.getSuperFicieEnHectarias(), up.getNombre(), "Urbanizacion Planificada"});
			}else if(b instanceof ComplejoHabitacional) {
				ComplejoHabitacional ch = (ComplejoHabitacional) b;
				modelo.addRow(new Object[] {ch.getNombre(), ch.getSuperFicieEnHectarias(), ch.getNombre(), "Complejo habitacional"});
			}
		
		tabla.setModel(modelo);
		scrollPane.setViewportView(tabla);
        
        
        botonCerrar.addActionListener(e -> dialog.dispose());

        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);
        dialog.getContentPane().add(botonCerrar, BorderLayout.SOUTH);

        dialog.setVisible(true);
	}
	
	
	
	public static void mostrarDialogoConCargarComuna(Comuna c, JPanel panel) {
		Window parentWindow = SwingUtilities.getWindowAncestor(panel);

        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        ComunaAltaPN alta = new ComunaAltaPN(c.getNombre(), c.getNumeroComuna());
        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(alta, BorderLayout.CENTER);
        dialog.setSize(650, 650);
        dialog.setLocationRelativeTo(parentWindow);
        
        dialog.setVisible(true);
	}
	
	
	
	public static String mostrarDialogoReAsignarBarrios(String nombreComunaEliminada,JPanel panel) {
		Window parentWindow = SwingUtilities.getWindowAncestor(panel);
        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        JPanel panelDialgo = new JPanel();
        panelDialgo.setLayout(null);
        JButton botonCerrar = new JButton("Cerrar");
        int i = 0;
        boolean salir = true;
        ArrayList<String> nombres = Sentencias.comuna.consultarNombresComuna();
        while(salir || i < nombres.size()) {
        	if(nombres.get(i).equals(nombreComunaEliminada)) {
        		salir = false;
        		nombres.remove(i);
        	}
        	i++;
        }
        
        
		JLabel labelMigrarBarrios = new JLabel("Tienes que migar los barrios a un comuna");
		labelMigrarBarrios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelMigrarBarrios.setHorizontalAlignment(SwingConstants.CENTER);
		labelMigrarBarrios.setBounds(10, 11, 280, 22);
		panelDialgo.add(labelMigrarBarrios);
		
		JComboBox comboBoxComunas = new JComboBox();
		comboBoxComunas.setBounds(74, 73, 160, 31);
		comboBoxComunas.setModel(new DefaultComboBoxModel(nombres.toArray()));
		panelDialgo.add(comboBoxComunas);
		
		JLabel lblEligeUno = new JLabel("Elige uno");
		lblEligeUno.setHorizontalAlignment(SwingConstants.CENTER);
		lblEligeUno.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEligeUno.setBounds(10, 40, 280, 22);
		panelDialgo.add(lblEligeUno);
		
		
		botonCerrar.setBounds(201, 121, 89, 23);
		panelDialgo.add(botonCerrar);
		
		JButton botonCambiar = new JButton("Cambiar");
		botonCambiar.setBounds(74, 121, 89, 23);
		panelDialgo.add(botonCambiar);
        dialog.setSize(350, 300);
        
        
        
        botonCerrar.addActionListener(e -> dialog.dispose());
        
        botonCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombreComuna = comboBoxComunas.getSelectedItem() + "";
				dialog.dispose();
			}
		});
        
        dialog.setLocationRelativeTo(parentWindow);
        dialog.setContentPane(panelDialgo);
        dialog.setVisible(true);
        
        return nombreComuna;
	}
	
	public static void mostrarDialogoCargarBarrio(Barrio b, Component invoker) {
		Window parentWindow = SwingUtilities.getWindowAncestor(invoker);

        JDialog dialog = new JDialog(parentWindow, "Diálogo desde JPanel", Dialog.ModalityType.APPLICATION_MODAL);
        BarrioAltaPN alta = new BarrioAltaPN();
       
        if(b instanceof TrazadoFormal) {
        	TrazadoFormal tf = (TrazadoFormal)b;
        	alta = new BarrioAltaPN(tf.getNombre(),tf.getSuperFicieEnHectarias(), tf.getCantidadEdificios(),tf.getPorcentajeAsfalto());	
        }else if (b instanceof UrbanizacionPlanificada) {
        	UrbanizacionPlanificada up = (UrbanizacionPlanificada)b;
            alta = new BarrioAltaPN(up.getNombre(),up.getSuperficie(),up.getNumeroLey(),up.getTieneConexionAgua());
        }else if (b instanceof ComplejoHabitacional) {
        	ComplejoHabitacional ch = (ComplejoHabitacional) b;
        	alta  = new BarrioAltaPN(ch.getNombre(), ch.getSuperficie(),ch.getCantidadMonoblock(),ch.getTieneConsorcioAdministracion());
        }
        
        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(alta, BorderLayout.CENTER);
        dialog.setSize(650, 650);
        dialog.setLocationRelativeTo(parentWindow);
        
        dialog.setVisible(true);
		
	}
	
	
}
