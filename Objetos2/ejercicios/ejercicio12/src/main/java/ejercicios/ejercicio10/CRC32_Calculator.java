package ejercicios.ejercicio10;
import java.util.zip.CRC32;
public class CRC32_Calculator implements CRC_Calculator {
    public int crcFor(String data) {
        CRC32 crc = new CRC32();
        crc.update(data.getBytes());
        return (int) crc.getValue();
    }
}
