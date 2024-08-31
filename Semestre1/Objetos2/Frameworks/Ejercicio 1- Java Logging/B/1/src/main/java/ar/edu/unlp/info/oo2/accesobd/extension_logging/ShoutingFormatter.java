package ar.edu.unlp.info.oo2.accesobd.extension_logging;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class ShoutingFormatter extends SimpleFormatter {

    @Override
    public String format(LogRecord record) {
        return super.format(record).toUpperCase();
    }

}
