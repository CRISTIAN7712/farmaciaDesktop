package ui;

import model.Pedido;
import controller.Validador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FormularioPedido extends JFrame {

    private JTextField txtMedicamento;
    private JComboBox<String> cbTipoMedicamento;
    private JTextField txtCantidad;
    private JRadioButton rbCofarma, rbEmpsephar, rbCemefar;
    private JCheckBox chkPrincipal, chkSecundaria;
    private JButton btnBorrar, btnConfirmar;
    private ButtonGroup distribuidorGroup;
    private DefaultTableModel pedidosModel;
    private List<Pedido> pedidosConfirmados = new ArrayList<>();

    public FormularioPedido() {
        setTitle("Gestión de Pedidos de Farmacia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(900, 600); // Tamaño ajustado
        setResizable(false);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Crear pestañas
        tabbedPane.addTab("Formulario de Pedido", crearFormularioPanel());
        tabbedPane.addTab("Pedidos Confirmados", crearPedidosPanel());

        add(tabbedPane);
    }

    private JPanel crearFormularioPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Título
        JLabel titulo = new JLabel("Gestión de Pedidos", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setForeground(new Color(33, 150, 243));
        mainPanel.add(titulo, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos del formulario
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre del Medicamento:"), gbc);
        gbc.gridx = 1;
        txtMedicamento = new JTextField(20);
        formPanel.add(txtMedicamento, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Tipo de Medicamento:"), gbc);
        gbc.gridx = 1;
        cbTipoMedicamento = new JComboBox<>(new String[]{"Analgésico", "Analéptico", "Anestésico", "Antiácido", "Antidepresivo", "Antibiótico"});
        formPanel.add(cbTipoMedicamento, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Cantidad:"), gbc);
        gbc.gridx = 1;
        txtCantidad = new JTextField(10);
        formPanel.add(txtCantidad, gbc);

        // Distribuidores
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JPanel distribuidorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        distribuidorPanel.setBorder(BorderFactory.createTitledBorder("Distribuidor"));
        rbCofarma = new JRadioButton("Cofarma");
        rbEmpsephar = new JRadioButton("Empsephar");
        rbCemefar = new JRadioButton("Cemefar");
        distribuidorGroup = new ButtonGroup();
        distribuidorGroup.add(rbCofarma);
        distribuidorGroup.add(rbEmpsephar);
        distribuidorGroup.add(rbCemefar);
        distribuidorPanel.add(rbCofarma);
        distribuidorPanel.add(rbEmpsephar);
        distribuidorPanel.add(rbCemefar);
        formPanel.add(distribuidorPanel, gbc);

        // Sucursales
        gbc.gridy = 4;
        JPanel sucursalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sucursalPanel.setBorder(BorderFactory.createTitledBorder("Sucursal"));
        chkPrincipal = new JCheckBox("Principal");
        chkSecundaria = new JCheckBox("Secundaria");
        sucursalPanel.add(chkPrincipal);
        sucursalPanel.add(chkSecundaria);
        formPanel.add(sucursalPanel, gbc);

        // Botones
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnBorrar = new JButton("Borrar");
        btnBorrar.setBackground(new Color(244, 67, 54));
        btnBorrar.setForeground(Color.WHITE);
        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBackground(new Color(76, 175, 80));
        btnConfirmar.setForeground(Color.WHITE);
        buttonPanel.add(btnBorrar);
        buttonPanel.add(btnConfirmar);
        formPanel.add(buttonPanel, gbc);

        // Eventos de botones
        btnBorrar.addActionListener(e -> limpiarFormulario());
        btnConfirmar.addActionListener(e -> procesarPedido());

        mainPanel.add(formPanel, BorderLayout.CENTER);
        return mainPanel;
    }

    private JPanel crearPedidosPanel() {
    JPanel pedidosPanel = new JPanel(new BorderLayout(10, 10));
    pedidosPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    pedidosPanel.setBackground(new Color(245, 245, 245)); // Fondo claro

    // Modelo de tabla
    pedidosModel = new DefaultTableModel(new String[]{"Medicamento", "Tipo", "Cantidad", "Distribuidor", "Sucursal", "Estado", "Acción"}, 0);
    JTable pedidosTable = new JTable(pedidosModel) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 6; // Solo la columna "Acción" es editable
        }
    };

    // Asignar renderers y editores personalizados a la columna "Acción"
    pedidosTable.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
    pedidosTable.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox(), pedidosConfirmados, pedidosModel));

    // Configurar apariencia de la tabla
    pedidosTable.setFillsViewportHeight(true);
    pedidosTable.setRowHeight(30);
    pedidosTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
    pedidosTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
    pedidosTable.getTableHeader().setBackground(new Color(33, 150, 243));
    pedidosTable.getTableHeader().setForeground(Color.WHITE);

    // Añadir tabla a un JScrollPane
    JScrollPane scrollPane = new JScrollPane(pedidosTable);
    pedidosPanel.add(scrollPane, BorderLayout.CENTER);

    return pedidosPanel;
}


    private void limpiarFormulario() {
        txtMedicamento.setText("");
        txtCantidad.setText("");
        distribuidorGroup.clearSelection();
        chkPrincipal.setSelected(false);
        chkSecundaria.setSelected(false);
        cbTipoMedicamento.setSelectedIndex(0);
    }

    private void procesarPedido() {
    String medicamento = txtMedicamento.getText().trim();
    String tipoMedicamento = (String) cbTipoMedicamento.getSelectedItem();
    String cantidadStr = txtCantidad.getText().trim();
    int cantidad;
    String distribuidor = null;
    if (rbCofarma.isSelected()) distribuidor = "Cofarma";
    if (rbEmpsephar.isSelected()) distribuidor = "Empsephar";
    if (rbCemefar.isSelected()) distribuidor = "Cemefar";
    boolean sucursalPrincipal = chkPrincipal.isSelected();
    boolean sucursalSecundaria = chkSecundaria.isSelected();

    // Validaciones
    if (medicamento.isEmpty() || !medicamento.matches("[a-zA-Z0-9 ]+")) {
        mostrarError("El nombre del medicamento debe contener solo caracteres alfanuméricos.");
        return;
    }
    if (tipoMedicamento == null) {
        mostrarError("Debe seleccionar un tipo de medicamento.");
        return;
    }
    try {
        cantidad = Integer.parseInt(cantidadStr);
        if (cantidad <= 0) throw new NumberFormatException();
    } catch (NumberFormatException e) {
        mostrarError("La cantidad debe ser un número entero positivo.");
        return;
    }
    if (distribuidor == null) {
        mostrarError("Debe seleccionar un distribuidor.");
        return;
    }
    if (!sucursalPrincipal && !sucursalSecundaria) {
        mostrarError("Debe seleccionar al menos una sucursal.");
        return;
    }

    // Concatenar sucursales
    String sucursales = "";
    if (sucursalPrincipal) sucursales += "Principal";
    if (sucursalSecundaria) {
        sucursales += (sucursales.isEmpty() ? "" : " y ") + "Secundaria";
    }

    // Crear el resumen del pedido
    String resumen = "Resumen del Pedido:\n"
                     + "Medicamento: " + medicamento + "\n"
                     + "Tipo: " + tipoMedicamento + "\n"
                     + "Cantidad: " + cantidad + "\n"
                     + "Distribuidor: " + distribuidor + "\n"
                     + "Sucursal Principal: " + (sucursalPrincipal ? "Sí" : "No") + "\n"
                     + "Sucursal Secundaria: " + (sucursalSecundaria ? "Sí" : "No");

    // Mostrar el resumen con la opción de confirmar o cancelar
    int opcion = JOptionPane.showConfirmDialog(this, resumen, "Confirmar Pedido", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

    // Si el usuario confirma, se procede
    if (opcion == JOptionPane.YES_OPTION) {
        // Crear y añadir el pedido a la lista
        Pedido pedido = new Pedido(medicamento, tipoMedicamento, cantidad, distribuidor, sucursales, "Pendiente");
        pedidosConfirmados.add(pedido);

        // Actualizar la tabla
        pedidosModel.addRow(new Object[]{
                pedido.getMedicamento(),
                pedido.getTipoMedicamento(),
                pedido.getCantidad(),
                pedido.getDistribuidor(),
                sucursales, // Se muestra la cadena de sucursales
                pedido.getEstado(),
                "Procesar" // Acción
        });

        limpiarFormulario();
        JOptionPane.showMessageDialog(this, "Pedido confirmado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } else {
        // Si el usuario cancela, muestra un mensaje
        JOptionPane.showMessageDialog(this, "Pedido cancelado.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
    }
}



private void mostrarError(String mensaje) {
    JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
}
    
}
