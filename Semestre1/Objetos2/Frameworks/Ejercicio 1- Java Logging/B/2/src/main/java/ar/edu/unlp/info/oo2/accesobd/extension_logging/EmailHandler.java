package ar.edu.unlp.info.oo2.accesobd.extension_logging;

import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailHandler extends ConsoleHandler {

    public EmailHandler() {
        super();
    }

    @Override
    public void publish(LogRecord record) {
        try {
            String from = "example@logger.com";
            String to = "destination@mail.com";

            // credenciales
            String username = "b5ca4bd7747722"; // Completar con su username de mailtrap
            String password = "85cfad08bc7c8a"; // Completar con su password de mailtrap

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.mailtrap.io");
            props.put("mail.smtp.port", "587");
            Session session = Session.getInstance(props,
                    new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from, "Java logging mail"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(record.getLevel().toString());
            message.setText(record.getMessage());
            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
