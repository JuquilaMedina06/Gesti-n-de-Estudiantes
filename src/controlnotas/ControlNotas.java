/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controlnotas;

/**
 *
 * @author juqui
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ControlNotas extends JFrame {
    private JTabbedPane tabbedPane;
    private JTable estudiantesTable, materiasTable, gruposTable, notasTable;
    private DefaultTableModel estudiantesModel, materiasModel, gruposModel, notasModel;
    private JTextField estudianteField, grupoField, materiaField, notaField;
    private JButton addEstudianteButton, addMateriaButton, addGrupoButton, addNotaButton;
    private JComboBox<String> estudianteCombo, grupoCombo, materiaCombo;

    public ControlNotas() {
        setTitle("Sistema de Control de Notas");
        setSize(1000, 600); // Tamaño más extenso para la interfaz
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Estudiantes", createEstudiantesPanel());
        tabbedPane.addTab("Materias", createMateriasPanel());
        tabbedPane.addTab("Grupos", createGruposPanel());
        tabbedPane.addTab("Control de Notas", createNotasPanel());

        add(tabbedPane);
    }

    private JPanel createEstudiantesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        estudianteField = new JTextField();
        addEstudianteButton = new JButton("Agregar Estudiante");
        JButton deleteButton = new JButton("Eliminar Estudiante");

        inputPanel.add(new JLabel("Nombre del Estudiante:"));
        inputPanel.add(estudianteField);
        inputPanel.add(addEstudianteButton);
        inputPanel.add(deleteButton);

        estudiantesModel = new DefaultTableModel(new String[]{"Nombre"}, 0);
        estudiantesTable = new JTable(estudiantesModel);

        addEstudianteButton.addActionListener(e -> agregarElemento(estudianteField, estudiantesModel, estudianteCombo));
        deleteButton.addActionListener(e -> eliminarElemento(estudiantesTable, estudiantesModel));

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(estudiantesTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createMateriasPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        materiaField = new JTextField();
        addMateriaButton = new JButton("Agregar Materia");
        JButton deleteButton = new JButton("Eliminar Materia");

        inputPanel.add(new JLabel("Nombre de la Materia:"));
        inputPanel.add(materiaField);
        inputPanel.add(addMateriaButton);
        inputPanel.add(deleteButton);

        materiasModel = new DefaultTableModel(new String[]{"Materia"}, 0);
        materiasTable = new JTable(materiasModel);

        addMateriaButton.addActionListener(e -> agregarElemento(materiaField, materiasModel, materiaCombo));
        deleteButton.addActionListener(e -> eliminarElemento(materiasTable, materiasModel));

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(materiasTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createGruposPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        grupoField = new JTextField();
        addGrupoButton = new JButton("Agregar Grupo");
        JButton deleteButton = new JButton("Eliminar Grupo");

        inputPanel.add(new JLabel("Nombre del Grupo:"));
        inputPanel.add(grupoField);
        inputPanel.add(addGrupoButton);
        inputPanel.add(deleteButton);

        gruposModel = new DefaultTableModel(new String[]{"Grupo"}, 0);
        gruposTable = new JTable(gruposModel);

        addGrupoButton.addActionListener(e -> agregarElemento(grupoField, gruposModel, grupoCombo));
        deleteButton.addActionListener(e -> eliminarElemento(gruposTable, gruposModel));

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(gruposTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createNotasPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(3, 4, 10, 10)); // Diseño más extenso
        estudianteCombo = new JComboBox<>();
        grupoCombo = new JComboBox<>();
        materiaCombo = new JComboBox<>();
        notaField = new JTextField();
        addNotaButton = new JButton("Agregar Nota");
        JButton deleteButton = new JButton("Eliminar Nota");

        inputPanel.add(new JLabel("Estudiante:"));
        inputPanel.add(estudianteCombo);
        inputPanel.add(new JLabel("Materia:"));
        inputPanel.add(materiaCombo);
        inputPanel.add(new JLabel("Grupo:"));
        inputPanel.add(grupoCombo);
        inputPanel.add(new JLabel("Nota:"));
        inputPanel.add(notaField);
        inputPanel.add(addNotaButton);
        inputPanel.add(deleteButton);

        notasModel = new DefaultTableModel(new String[]{"Estudiante", "Materia", "Grupo", "Nota"}, 0);
        notasTable = new JTable(notasModel);

        addNotaButton.addActionListener(e -> agregarNota(estudianteCombo, grupoCombo, materiaCombo, notaField));
        deleteButton.addActionListener(e -> eliminarElemento(notasTable, notasModel));

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(notasTable), BorderLayout.CENTER);
        return panel;
    }

    private void agregarElemento(JTextField field, DefaultTableModel model, JComboBox<String> comboBox) {
        String text = field.getText().trim();
        if (!text.isEmpty()) {
            model.addRow(new Object[]{text});
            field.setText("");
            actualizarComboBox(model, comboBox);
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar un valor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarComboBox(DefaultTableModel model, JComboBox<String> comboBox) {
        comboBox.removeAllItems();
        for (int i = 0; i < model.getRowCount(); i++) {
            comboBox.addItem((String) model.getValueAt(i, 0));
        }
    }

    private void agregarNota(JComboBox<String> estudianteCombo, JComboBox<String> grupoCombo, JComboBox<String> materiaCombo, JTextField notaField) {
        String estudiante = (String) estudianteCombo.getSelectedItem();
        String grupo = (String) grupoCombo.getSelectedItem();
        String materia = (String) materiaCombo.getSelectedItem();
        String nota = notaField.getText().trim();
        if (estudiante != null && grupo != null && materia != null && !nota.isEmpty()) {
            notasModel.addRow(new Object[]{estudiante, materia, grupo, nota});
            notaField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione estudiante, grupo, materia y asigne una nota.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarElemento(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ControlNotas().setVisible(true));
    }
}