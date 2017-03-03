package com.vlpa.javamail.main.sender;

import javax.mail.MessagingException;
import java.io.IOException;

public interface MailSender {

    public void send() throws IOException, MessagingException;
}
