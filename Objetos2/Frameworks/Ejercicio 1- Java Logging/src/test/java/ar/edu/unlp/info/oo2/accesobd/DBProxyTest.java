package ar.edu.unlp.info.oo2.accesobd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DBProxyTest {
    private DBProxy database;

    @BeforeEach
    void setUp() throws Exception {
        this.database = new DBProxy(new DatabaseRealAccess(), "123456");
    }

    @Test
    void testGetSearchResults() {
        database.LogIn("123456");
        assertEquals(Arrays.asList("Spiderman", "Marvel"), this.database.getSearchResults("select * from comics where id=1"));
        assertEquals(Collections.emptyList(), this.database.getSearchResults("select * from comics where id=10"));
        database.LogOut();
        assertThrows(RuntimeException.class, () -> {
            database.getSearchResults("select * from comics where id=10");
        });
    }

    @Test
    void testInsertNewRow() {
        database.LogIn("123456");
        assertEquals(3, this.database.insertNewRow(Arrays.asList("Patoruzú", "La flor")));
        assertEquals(Arrays.asList("Patoruzú", "La flor"), this.database.getSearchResults("select * from comics where id=3"));
        database.LogOut();
        assertThrows(RuntimeException.class, () -> {
            database.insertNewRow(Arrays.asList("Patoruzú", "La flor"));
        });
    }
}