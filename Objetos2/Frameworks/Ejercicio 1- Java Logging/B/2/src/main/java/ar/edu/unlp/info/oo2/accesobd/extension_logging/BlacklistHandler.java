package ar.edu.unlp.info.oo2.accesobd.extension_logging;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;

public class BlacklistHandler extends ConsoleHandler {

    List<String> blacklist;

    public BlacklistHandler() {
        super();
        this.blacklist = new ArrayList<String>();
    }

    public BlacklistHandler(List<String> blackList) {
        super();
        this.blacklist = blackList;
    }

    public void addToBlacklist(String message) {
        this.blacklist.add(message);
    }

    public void removeFromBlackList(String message) {
        this.blacklist.remove(message);
    }

    @Override
    public void publish(LogRecord logRecord) {
        String newMessage = logRecord.getMessage();
        for (String message : this.blacklist) {
            if (newMessage.contains(message)) {
                newMessage = newMessage.replace(message, "***");
            }
        }
        logRecord.setMessage(newMessage);
        super.publish(logRecord);
    }

}
