package controller;

public class Validador {
    public static boolean validarMedicamento(String medicamento) {
        return medicamento != null && medicamento.matches("[a-zA-Z0-9 ]+");
    }

    public static boolean validarCantidad(String cantidad) {
        try {
            int value = Integer.parseInt(cantidad);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarSeleccion(String seleccion) {
        return seleccion != null && !seleccion.isEmpty();
    }
}
