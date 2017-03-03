package com.vlpa.javamail.main.sender;

public class TLSMailSender extends AbstractMailSender {

    public static final String CONFIG_FILE = "tls_config.properties";

    public TLSMailSender(String toEmail, String subject, String mailText) {
        super(toEmail, subject, mailText);
    }

    @Override
    public String getConfigFileLocation() {
        return CONFIG_FILE;
    }


}
