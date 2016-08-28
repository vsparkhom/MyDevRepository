package com.vlpa.javamail.main;

import com.vlpa.javamail.main.sender.MailSender;
import com.vlpa.javamail.main.sender.SSLMailSender;
import com.vlpa.javamail.main.sender.TLSMailSender;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FormMailSenderServlet extends  HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>GET request for a FormMailSender Servlet.</h1>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        out.println("<html>");
        out.println("<body>");
        out.println("<h1>POST request for a FormMailSender Servlet.</h1>");

        String toMail = request.getParameter("to_email");
        String subject = request.getParameter("subject");
        String mailText = request.getParameter("mail_text");

        out.println("<br>to_email: " + toMail);
        out.println("<br>subject: " + subject);
        out.println("<br>mail_text: " + mailText);

        String sendResult = "<EMPTY_RESPONSE>";
        try {
//            MailSender mailSender = new TLSMailSender(toMail, subject, mailText);
            MailSender mailSender = new SSLMailSender(toMail, subject, mailText);
            mailSender.send();
            sendResult = "<span style='color: green'>Message has been sent!</span>";
        } catch (MessagingException | IOException e) {
            sendResult = "<span style='color: red'>" + e.toString() + "</span>";
            e.printStackTrace();
        }
        out.println("<br><br>Send result: " + sendResult);

        out.println("</body>");
        out.println("</html>");
    }

}
