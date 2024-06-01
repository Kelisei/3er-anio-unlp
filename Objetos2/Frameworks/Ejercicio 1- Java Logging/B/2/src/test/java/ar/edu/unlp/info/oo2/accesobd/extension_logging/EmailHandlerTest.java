package ar.edu.unlp.info.oo2.accesobd.extension_logging;



import java.util.logging.Level;
import java.util.logging.LogRecord;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class EmailHandlerTest {

    @Test
    public void testPublishWithValidInput() {
        EmailHandler emailHandler = new EmailHandler();
        LogRecord logRecord = new LogRecord(Level.INFO, "Test log message");

        assertDoesNotThrow(() -> emailHandler.publish(logRecord));
    }

    @Test
    public void testPublishWithNullLogRecord() {
        EmailHandler emailHandler = new EmailHandler();

        assertThrows(NullPointerException.class, () -> emailHandler.publish(null));
    }

    @Test
    public void testPublishWithEmptyLogMessage() {
        EmailHandler emailHandler = new EmailHandler();
        LogRecord logRecord = new LogRecord(Level.INFO, "");

        assertDoesNotThrow(() -> emailHandler.publish(logRecord));
    }

    @Test
    public void testPublishWithDifferentLogLevels() {
        EmailHandler emailHandler = new EmailHandler();

        assertDoesNotThrow(() -> emailHandler.publish(new LogRecord(Level.INFO, "Info message")));
        assertDoesNotThrow(() -> emailHandler.publish(new LogRecord(Level.WARNING, "Warning message")));
        assertDoesNotThrow(() -> emailHandler.publish(new LogRecord(Level.SEVERE, "Severe message")));
    }
}
