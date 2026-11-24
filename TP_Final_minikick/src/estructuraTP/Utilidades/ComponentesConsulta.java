package estructuraTP.Utilidades;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class ComponentesConsulta {

    private JPanel panel;

    private JLabel labelTitulo;
    private JLabel labelIndicacion;
    private JLabel labelNombre;

    private JTextField textField;
    private JTable table;
    private DefaultTableModel modelo;
    private JScrollPane scrollPane;

    private JButton botonBuscar;
    private JButton botonBorrar;
    private JButton botonModificar;
    private JButton botonMostrarTodo;
    private JButton botonVerDetalle;
    private JButton botonLimpiar;

    public ComponentesConsulta(String titulo, String indicacion,String nombreCampo, String[] columnas) {

        panel = new JPanel(null);
        panel.setBounds(0, 0, 800, 800);

        labelTitulo = new JLabel(titulo);
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 31));
        labelTitulo.setBounds(266, 28, 300, 47);
        panel.add(labelTitulo);

        labelIndicacion = new JLabel(indicacion);
        labelIndicacion.setHorizontalAlignment(SwingConstants.CENTER);
        labelIndicacion.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelIndicacion.setBounds(70, 76, 380, 26);
        panel.add(labelIndicacion);

        labelNombre = new JLabel(nombreCampo);
        labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
        labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelNombre.setBounds(57, 113, 86, 26);
        panel.add(labelNombre);

        textField = new JTextField();
        textField.setBounds(249, 117, 216, 26);
        panel.add(textField);

        botonBuscar = new JButton("Buscar");
        botonBuscar.setBounds(480, 113, 86, 29);
        panel.add(botonBuscar);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(57, 170, 493, 301);
        panel.add(scrollPane);

        modelo = new DefaultTableModel(columnas, 0);
        table = new JTable(modelo);
        table.setDefaultEditor(Object.class, null);
        scrollPane.setViewportView(table);


        botonModificar = new JButton("Modificar");
        botonModificar.setBounds(583, 241, 145, 38);
        panel.add(botonModificar);

        botonBorrar = new JButton("Borrar");
        botonBorrar.setBounds(583, 301, 145, 38);
        panel.add(botonBorrar);

        botonVerDetalle = new JButton("Ver detalle");
        botonVerDetalle.setBounds(583, 367, 145, 38);
        panel.add(botonVerDetalle);
    }

    // GETTERS

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JButton getBotonBuscar() {
        return botonBuscar;
    }

    public JButton getBotonBorrar() {
        return botonBorrar;
    }

    public JButton getBotonModificar() {
        return botonModificar;
    }

    public JButton getBotonMostrarTodo() {
        return botonMostrarTodo;
    }

    public JButton getBotonVerDetalle() {
        return botonVerDetalle;
    }

    public JButton getBotonLimpiar() {
        return botonLimpiar;
    }
}
