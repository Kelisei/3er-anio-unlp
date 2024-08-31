package ejercicios.ejercicio10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DispositivoTest {

    private Dispositivo dispositivo;

    @BeforeEach
    void setUp() throws Exception{
        dispositivo = new Dispositivo();
    }
    @Test
    void testConectarCon() {
    	assertEquals("Connection changed",this.dispositivo.conectarCon(new FourGAdapter(new FourGConnection("4G NASHE"))));
    }
    @Test
    void testConfigurarCRC() {
    	assertEquals("CRC changed",this.dispositivo.configurarCRC(new CRC16_Calculator()));
    }
    @Test
    void testSendWifi(){
        this.dispositivo.conectarCon(new WifiConn("WIFI NASHE"));
        assertEquals("Wifi Connection: Hola, mundo! 8245", this.dispositivo.send("Hola, mundo!"));
    }
    @Test
    void testSend4G(){
        this.dispositivo.conectarCon(new FourGAdapter(new FourGConnection("4G Nashe")));
        assertEquals("4G Connection: Hola, mundo! 8245", this.dispositivo.send("Hola, mundo!"));
    }
}
