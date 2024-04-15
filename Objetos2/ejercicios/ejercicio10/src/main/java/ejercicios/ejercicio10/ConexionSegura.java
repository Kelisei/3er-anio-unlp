package ejercicios.ejercicio10;

public class ConexionSegura extends Conexion {
    private Encriptador encriptador;

    public ConexionSegura(Encriptador encriptador) {
        this.encriptador = encriptador;
    }

    public void enviarMensaje(String mensaje){
        String encriptado = encriptador.encriptar(mensaje);
        System.out.println("Enviando mensaje: " + encriptado);
    }

    public Encriptador getEncriptador(){
        return encriptador;
    }

    public void setEncriptador(Encriptador encriptador){
        this.encriptador = encriptador;
    }
}
