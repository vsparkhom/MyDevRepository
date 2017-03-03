package com.vlpa.javamail.main.sender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractMailSender implements MailSender {

    private String toEmail;
    private String subject;
    private String mailText;

    public AbstractMailSender(String toEmail, String subject, String mailText) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.mailText = mailText;
    }

    public void send() throws IOException, MessagingException {

        Properties props = getMailProperties(getConfigFileLocation());

        String username = (String) props.get("username");
        String password = (String) props.get("password");

        Session session = getSession(props, username, password);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject(subject);
        message.setText(mailText);

        Transport.send(message);

        System.out.println("Message has been sent!");
    }

    public abstract String getConfigFileLocation();

    private Properties getMailProperties(String configFileName) throws IOException {
        Properties props = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);
        if (inputStream != null) {
            props.load(inputStream);
        } else {
            throw new FileNotFoundException("Configuration properties file '" + configFileName + "' not found!");
        }
        return props;
    }

    private Session getSession(Properties props, final String username, final String password) {

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        return session;
    }

}
