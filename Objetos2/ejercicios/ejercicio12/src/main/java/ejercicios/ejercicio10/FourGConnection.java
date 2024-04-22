package ejercicios.ejercicio10;

public class FourGConnection {
    private String symb;

    public FourGConnection(String symb){
        this.symb = symb;
    }

    public String transmit(String data, int crc){
        return "4G Connection: " + data + " " + crc;
    }
    public String getSymb(){
        return this.symb;
    }
}
