package estructuraTPvista;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.Color;

public class ComunaAlta extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel labelTitulo;
	private JTextField textField;
	private JLabel labelNombre;
	private JLabel labelNumero;
	
	private JSpinner spinner;
	private JButton botonAgregar;
	
	public ComunaAlta() {
		setLayout(null);
		crearLabels();
	}
	private void crearLabels() {
		labelTitulo = new JLabel("Agregar Comuna");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 31));
		labelTitulo.setBounds(47, 27, 300, 47);
		add(labelTitulo);
		
		textField = new JTextField();
		textField.setBounds(179, 100, 186, 26);
		add(textField);
		textField.setColumns(10);
		
		labelNombre = new JLabel("Nombre");
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombre.setBounds(47, 100, 86, 26);
		add(labelNombre);
		
		labelNumero = new JLabel("Numero");
		labelNumero.setHorizontalAlignment(SwingConstants.CENTER);
		labelNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNumero.setBounds(47, 169, 89, 26);
		add(labelNumero);
		
		spinner = new JSpinner();
		spinner.setBounds(179, 169, 48, 26);
		add(spinner);
		
		botonAgregar = new JButton("AGREGAR");
		botonAgregar.setBackground(new Color(0, 128, 64));
		botonAgregar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botonAgregar.setBounds(138, 217, 118, 40);
		add(botonAgregar);
	}
}
