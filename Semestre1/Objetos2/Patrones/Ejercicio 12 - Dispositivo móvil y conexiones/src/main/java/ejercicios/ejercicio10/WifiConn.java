package ejercicios.ejercicio10;

public class WifiConn implements Connection {
    private String pict;

    public WifiConn(String pict){
        this.pict = pict;
    }

    public String sendData(String data, int crc){
        return "Wifi Connection: " + data + " " + crc;
    }
    public String pict(){
        return this.pict;
    }
}
