package ejercicios.ejercicio10;

public class Dispositivo {
    private CRC_Calculator crcCalc;
    private Ringer ringer;
    private Connection connection;
    private Display display;

    public Dispositivo() {
        this.crcCalc = new CRC16_Calculator();
        this.ringer = new Ringer();
        this.connection = new FourGAdapter(new FourGConnection("4G"));
        this.display = new Display();
    }
    public void configurarCRC(CRC_Calculator crc) {
        this.crcCalc = crc;
    }
    public void configurarConexion(Connection connection){
        this.connection = connection;
        this.display.showBanner(connection.pict());
        this.ringer.ring();
    }
    public String send(String data) {
        int crc = this.crcCalc.crcFor(data);
        return this.connection.sendData(data, crc);
    }
}
