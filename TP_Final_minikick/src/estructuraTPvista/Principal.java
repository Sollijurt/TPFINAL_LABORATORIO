package estructuraTPvista;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;



public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JPanel panel = new JPanel();
	
	//MENUS-----------
	MenuInicio mInicio = new MenuInicio();
	MenuComuna mComuna = new MenuComuna();
	
	//COMUNAS---------
	ComunaAlta cAlta = new ComunaAlta();
	ComunaConsultar cConsulta = new ComunaConsultar();
	
	// BOTONES BARRA DE TAREAS--------------------
	private JButton botonBarraComuna;
	private JButton botonBarraBarrio;
	private JButton botonBarraPersona;
	private JButton botonBarraMenu;
	
	// BOTONES DEL MENU-----------------
	private JButton botonComuna; 
	private JButton botonBarrio;
	private JButton botonPersona;
	// BOTONES DEL MENU COMUNA ------------------
	private JButton botonAltaComuna;
	private JButton botonConsultaComuna;
	
	private JToolBar toolBar = new JToolBar();
	
	private CardLayout cl = new CardLayout();
	
	public Principal() {

		
		//--------------
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 400, 400, 400);
		contentPane = new JPanel();
		
		contentPane.setLayout(null);
		
		cargarBarraTareas();
		cargarBotonesMenuPrincipal();
		usosBotones();
		cargarBotonesMenuComuna(); //temporal para pruebas
		usosBotonesComuna(); //temporal
		
		panel.setBounds(0, 38, 384, 323);
		contentPane.add(panel);
		panel.setLayout(cl);
		
		// AGREGAR PANELES AL LAYOUT-----------------
		panel.add(mInicio, "MenuPrincipal");
		panel.add(mComuna, "MenuComuna");
		panel.add(cAlta, "AltaComuna");
		
		cl.show(panel, "MenuPrincipal");
		
		setContentPane(contentPane);
		// Acciones
		
	}
	
	private void cargarBotonesMenuPrincipal() {
		// BOTONES MENU PRINCIPAL- ----
		botonComuna = new JButton("Comuna");
		botonComuna.setBounds(135, 115, 106, 28);
		mInicio.add(botonComuna);
		
		botonBarrio = new JButton("Barrio");
		botonBarrio.setBounds(135, 165, 106, 28);
		mInicio.add(botonBarrio);
		
		botonPersona = new JButton("Persona");
		botonPersona.setBounds(135, 213, 106, 28);
		mInicio.add(botonPersona);
		
		// TOOLBAR------------------------
		botonBarraMenu = new JButton("Menu");
		toolBar.add(botonBarraMenu);
		
		botonBarraComuna = new JButton("Comuna");
		toolBar.add(botonBarraComuna);
		
		botonBarraBarrio = new JButton("Barrio");
		toolBar.add(botonBarraBarrio);
		
		botonBarraPersona = new JButton("Persona");
		toolBar.add(botonBarraPersona);
	}
	
	private void cargarBotonesMenuComuna() {
		botonAltaComuna = new JButton("Cargar");
		botonAltaComuna.setBounds(140, 115, 106, 28);
		mComuna.add(botonAltaComuna);
		
		botonConsultaComuna = new JButton("Mostrar");
		botonConsultaComuna.setBounds(140, 170, 106, 28);
		mComuna.add(botonConsultaComuna);
	}
	private void cargarBarraTareas() {
		toolBar.setBounds(0, 0, 384, 41);
		contentPane.add(toolBar);
	}
	private void usosBotonesComuna() {
		botonAltaComuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "AltaComuna");
			}
		});

		botonConsultaComuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	private void usosBotones() {
		// MENU-----------
		botonBarraMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "MenuPrincipal");
			}
		});
		// COMUNA------------
		botonBarraComuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarBotonesMenuComuna();
				cl.show(panel, "MenuComuna");
			}
		});
		botonComuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarBotonesMenuComuna();
				cl.show(panel, "MenuComuna");
			}
		});
		
		// BARRIO--------------------
		botonBarraBarrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "Mundo");
			}
		});
		botonBarrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		// PERSONA----------------
		botonBarraPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "PersonajeVisual");
			}
		});
		
		botonPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}

