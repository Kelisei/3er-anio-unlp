package ar.edu.unlp.info.oo2.biblioteca;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BibliotecaTest {
	Biblioteca b1;
	@BeforeEach
	void setUp() throws Exception {
		b1 = new Biblioteca();
	}
	
	@Test
	void testExportacion() throws ParseException {
		b1.agregarSocio(new Socio("Arya Stark", "needle@stark.com", "5234-5"));
		b1.agregarSocio(new Socio("Tyron Lannister", "tyron@thelannisters.com", "2345-2"));
		JSONParser parseadorsito = new JSONParser();
		assertEquals(parseadorsito.parse(b1.exportarSocios()), parseadorsito.parse("[\r\n"
				+ "    {\r\n"
				+ "   	 \"nombre\": \"Arya Stark\",\r\n"
				+ "   	 \"email\": \"needle@stark.com\",\r\n"
				+ "   	 \"legajo\": \"5234-5\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "   	 \"nombre\": \"Tyron Lannister\",\r\n"
				+ "   	 \"email\": \"tyron@thelannisters.com\",\r\n"
				+ "   	 \"legajo\": \"2345-2\"\r\n"
				+ "    }\r\n"
				+ "]\r\n"
				+ ""));
	}


}
