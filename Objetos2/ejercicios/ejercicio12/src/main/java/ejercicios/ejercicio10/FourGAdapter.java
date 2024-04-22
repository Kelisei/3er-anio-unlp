package ejercicios.ejercicio10;

public class FourGAdapter implements Connection {
    private FourGConnection conexion;
    public FourGAdapter(FourGConnection conexion){
        this.conexion = conexion;
    }
    public String sendData(String data, int crc){
        return this.conexion.transmit(data, crc);
    }
    public String pict(){
        return this.conexion.getSymb();
    }
}
