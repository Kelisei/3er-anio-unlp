package ar.edu.unlp.info.oo2.accesobd.extension_logging;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlacklistHandlerTest {

    private BlacklistHandler handler;
    private List<String> blacklist;

    @BeforeEach
    void setUp() {
        blacklist = new ArrayList<>();
        blacklist.add("secret");
        handler = new BlacklistHandler(blacklist);
    }

    @Test
    void testPublish_WithBlacklistedWord() {
        LogRecord record = new LogRecord(Level.INFO, "This is a secret message.");
        handler.publish(record);
        assertEquals("This is a *** message.", record.getMessage());
    }

    @Test
    void testPublish_WithoutBlacklistedWord() {
        LogRecord record = new LogRecord(Level.INFO, "This is a normal message.");
        handler.publish(record);
        assertEquals("This is a normal message.", record.getMessage());
    }

    @Test
    void testAddToBlacklist() {
        handler.addToBlacklist("confidential");
        LogRecord record = new LogRecord(Level.INFO, "This is a confidential message.");
        handler.publish(record);
        assertEquals("This is a *** message.", record.getMessage());
    }

    @Test
    void testRemoveFromBlacklist() {
        handler.removeFromBlackList("secret");
        LogRecord record = new LogRecord(Level.INFO, "This is a secret message.");
        handler.publish(record);
        assertEquals("This is a secret message.", record.getMessage());
    }

    @Test
    void testPublish_WithMultipleBlacklistedWords() {
        blacklist.add("sensitive");
        LogRecord record = new LogRecord(Level.INFO, "This is a secret and sensitive message.");
        handler.publish(record);
        assertEquals("This is a *** and *** message.", record.getMessage());
    }
}