package ar.edu.unlp.info.oo2.accesobd.extension_logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class JsonFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        return "{ \"message\": \"" + record.getMessage() + "\", \"level\": \"" + record.getLevel().toString() + "\" }";
    }

}
