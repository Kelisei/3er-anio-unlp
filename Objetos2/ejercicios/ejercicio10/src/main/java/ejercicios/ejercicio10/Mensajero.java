package ejercicios.ejercicio10;

public class Mensajero {
    private Conexion conexion;

    public Mensajero(Conexion conexion) {
        this.conexion = conexion;
    }

    public void enviarMensaje(String mensaje) {
        conexion.enviarMensaje(mensaje);
    }
}
