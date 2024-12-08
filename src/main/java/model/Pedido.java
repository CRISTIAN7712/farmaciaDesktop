package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private String medicamento;
    private String tipoMedicamento;
    private int cantidad;
    private String distribuidor;
    private String sucursal; 
    private String estado;   

    
   /* public Pedido(String medicamento, String tipoMedicamento, int cantidad, String distribuidor,
                  String sucursal, String estado) {
        this.medicamento = medicamento;
        this.tipoMedicamento = tipoMedicamento;
        this.cantidad = cantidad;
        this.distribuidor = distribuidor;
        this.sucursal = sucursal; // Texto concatenado de sucursales
        this.estado = estado;
    }

    // Getters y Setters
    public String getMedicamento() { return medicamento; }
    public String getTipoMedicamento() { return tipoMedicamento; }
    public int getCantidad() { return cantidad; }
    public String getDistribuidor() { return distribuidor; }
    public String getSucursal() { return sucursal; } // Texto de sucursales
    public String getEstado() { return estado; }     // Estado del pedido*/
}
