package estructuraTPvista.Persona;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
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
import estructuraTPmodelo.Persona;
import estructuraTPmodelo.TipoSituacionLaboral;

public class PersonaConsultarPN extends JPanel {

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
	
	private ArrayList<Persona> personas = Sentencias.persona.consultar();
	
	public PersonaConsultarPN() {
		setLayout(null);
		setBounds(800, 400, 800, 800);
		crearElementos();
		usosBotones();
		
		addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentShown(ComponentEvent e) {
		    	personas = Sentencias.persona.consultar();
		        actualizarTabla(personas);
		    }
		});
		crearTabla();
	}
	
	private void crearElementos() {
		labelTitulo = new JLabel("Consultar persona");
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
		botonBorrar.setBounds(583, 237, 145, 38);
		add(botonBorrar);
		
		botonModificar = new JButton("Modificar");
		botonModificar.setBounds(583, 229, 145, 38);
		botonBorrar.setBounds(583, 301, 145, 38);
		add(botonModificar);
		
		
	}

	
	private void usosBotones() {
		// -------------- BusquedaAproximada
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {	
				String nombre = textField.getText();
				if(!nombre.isEmpty()) {
					actualizarTabla(busquedaPorAproximacion(nombre));
				}else {
					actualizarTabla(personas);
				}
			}
		});
		// ---------------------- BORRAR LO SELECCIONADO
		botonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if(fila <0 ) {
					Dialogo.mostrarDialogo("Seleccione una fila para borrar",PersonaConsultarPN.this);
				}
				else {
					String dni =  table.getValueAt(fila, 1) + "";
					Dialogo.mostrarDialogo("Borrado con exito", PersonaConsultarPN.this);
					Sentencias.persona.eliminar(dni);
					actualizarTabla(Sentencias.persona.consultar());
				}
			}
		});
		// ------------------------- MODIFICAR
		botonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if(fila <0 ) {
					Dialogo.mostrarDialogo("Seleccione una fila para modificar", PersonaConsultarPN.this);
				}
				else {
					String nombre = table.getValueAt(fila, 0) + "";
					String dni = table.getValueAt(fila, 1) + "";
					LocalDate fechaNacimiento = (LocalDate) table.getValueAt(fila, 2);
					TipoSituacionLaboral tipo = TipoSituacionLaboral.valueOf(table.getValueAt(fila, 3)+"");
					Persona p = new Persona(nombre, fechaNacimiento, dni,tipo);
					Dialogo.persona.mostrarDialogoConCargarPersona(p, PersonaConsultarPN.this);
					actualizarTabla(Sentencias.persona.consultar());
				}
			}
		});
	}
	
	private void crearTabla() {
		table =  new JTable();
		table.setBounds(57, 122, 300, 112);
		
		Object[] columnas = new Object[] {"Nombre" , "DNI", "fecha de nacimiento", "Situacion laboral"};
		
		modelo = new DefaultTableModel(columnas, 0);
		table.setDefaultEditor(Object.class, null);
		
		table.setModel(modelo);
		scrollPane.setViewportView(table);
	}

	private void actualizarTabla(ArrayList<Persona> personas) {
		modelo.setRowCount(0);
		for(Persona p : personas) {
				modelo.addRow(new Object[] {p.getNombre(), p.getDNI(), p.getFechaNacimiento(), p.getTipo().toString()});
		}
	}
	

	private ArrayList<Persona> busquedaPorAproximacion(String nombre){
		String nombreMinuscula = nombre.toLowerCase();
		char primeraLetraBuscado = nombreMinuscula.charAt(0);
		ArrayList<Persona> personasOrdenadas = ordenarNombres();
		ArrayList<Persona> personasBuscadas = new ArrayList<>();
		boolean salir = false;
		int indice = 0;
		while(salir == false) {
			String nombrePersona = personasOrdenadas.get(indice).getNombre().toLowerCase();
			char primeraLetra = nombrePersona.charAt(0);
			
			if(primeraLetraBuscado == primeraLetra) {
				personasBuscadas.add(personasOrdenadas.get(indice));
			}else if(primeraLetraBuscado < primeraLetra){
				salir = true;
			}
			indice++;

			if(indice == personasOrdenadas.size()) {
				salir = true;
			}
		}
		if(nombreMinuscula.length() > 1) {
			int contadorIguales= 0;
			
			for(int i = personasBuscadas.size() - 1; i >= 0; i--) {
				String nombreBuscado = personasBuscadas.get(i).getNombre().toLowerCase();
				contadorIguales = 0;
				if(nombreBuscado.length() >= nombreMinuscula.length()) {
					for(int j = 1; j < nombreMinuscula.length(); j++) {
						char letraBuscado = nombreBuscado.charAt(j);
						char letraIngresada = nombreMinuscula.charAt(j);
						
						if(letraBuscado == letraIngresada) {
							contadorIguales++;
						}
					}
				}
				 if(contadorIguales != nombreMinuscula.length() - 1) {
	                    personasBuscadas.remove(i);
	            }
			}	
			
		}
		return personasBuscadas;
	}
	
	private ArrayList<Persona> ordenarNombres() {
		ArrayList<Persona> personasOrdenadas = cargarArrayList();
		int maximo = personasOrdenadas.size();
		for(int i = 0; i < maximo; i++) {
			for(int j = 0; j < maximo - 1 - i; j++) {
				String nombre1 = personasOrdenadas.get(j).getNombre().toLowerCase();
				String nombre2 = personasOrdenadas.get(j+1).getNombre().toLowerCase();
				
				int codigoAscii1 = nombre1.charAt(0);
				int codigoAscii2 = nombre2.charAt(0);
				
				if(codigoAscii1 > codigoAscii2) {
					Persona aux = personasOrdenadas.get(j);
					personasOrdenadas.set(j, personasOrdenadas.get(j+1));
					personasOrdenadas.set(j+1, aux);
				}
			}
		}
		return personasOrdenadas;
	}
	
	private ArrayList<Persona> cargarArrayList(){
		ArrayList<Persona> personasOrdenadas = new ArrayList<>();
		for(int i = 0; i < personas.size(); i++) {
			personasOrdenadas.add(personas.get(i));
		}
		return personasOrdenadas;
	}
}
