package ui;

import model.Pedido;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean clicked;
    private JTable table;
    private List<Pedido> pedidos; // Lista de pedidos para actualizar el estado
    private DefaultTableModel model; // Modelo de la tabla para reflejar cambios

    public ButtonEditor(JCheckBox checkBox, List<Pedido> pedidos, DefaultTableModel model) {
        super(checkBox);
        this.pedidos = pedidos;
        this.model = model;

        button = new JButton();
        button.setOpaque(true);

        // Acción al hacer clic en el botón
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    this.table = table;
    this.label = (value != null ? value.toString() : "Acción");
    button.setText(label); // El texto del botón
    clicked = true;
    return button;
}


    @Override
public Object getCellEditorValue() {
    if (clicked) {
        int row = table.getSelectedRow();

        // Obtener datos de la tabla en lugar de la lista
        String medicamento = (String) table.getValueAt(row, 0);
        String tipo = (String) table.getValueAt(row, 1);
        int cantidad = (Integer) table.getValueAt(row, 2);
        String distribuidor = (String) table.getValueAt(row, 3);
        String sucursal = (String) table.getValueAt(row, 4);

        // Crear un cuadro de confirmación con más detalles
        String mensaje = "<html><div style='text-align: center;'>"
                + cantidad + " unidades del " + tipo + " " + medicamento
                + "<br>Distribuido por: " + distribuidor
                + "<br>Para la farmacia situada en: " + sucursal
                + "<br>¿Desea continuar con el pedido?</div></html>";

        int opcion = JOptionPane.showConfirmDialog(button, mensaje, "Confirmación de Pedido", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            // Confirmar pedido
            model.setValueAt("Confirmado", row, 5); // Actualizar estado
            JOptionPane.showMessageDialog(button, "Pedido confirmado.");
        } else {
            // Cancelar pedido
            model.setValueAt("Cancelado", row, 5); // Actualizar estado
            JOptionPane.showMessageDialog(button, "Pedido cancelado.");
        }
    }
    clicked = false;
    return label;
}



    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
