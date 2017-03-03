package com.vlpa.javamail.main.sender;

public class SSLMailSender extends AbstractMailSender {

    public static final String CONFIG_FILE = "ssl_config.properties";

    public SSLMailSender(String toEmail, String subject, String mailText) {
        super(toEmail, subject, mailText);
    }

    @Override
    public String getConfigFileLocation() {
        return CONFIG_FILE;
    }
}
