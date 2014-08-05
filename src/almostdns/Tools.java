/*
 * Writing this program to keep track of my external home ip address.
 * 
 * As it is almost impossible to get a static ip in the UK as a home
 * customer I decided to write a program that will send an email to a
 * given account each time my external IP address changes.
 * 
 * This program is inteded for use alongside a secure messaging app
 * server program that I will be writing.
 * 
 */
package almostdns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Twiztedcyph
 */
public class Tools
{

    private final String host = "smtp.gmail.com";
    private final String from = "Sender email goes here...";
    private final String to = "Recipient email goes here...";

    // Check exteranl ip address.
    public String getAddress() throws MalformedURLException, IOException
    {
        // Amazon ip checking service.
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
        
        // Get ip as a string.
        return in.readLine();
    }

    // Send an email.
    public void sendEmail(String ipAddress) throws AddressException, MessagingException, UnsupportedEncodingException
    {
        Properties propsSSL = new Properties();

        propsSSL.put("mail.transport.protocol", "smtps");
        propsSSL.put("mail.smtps.host", "smtp.gmail.com");

        propsSSL.put("mail.smtps.auth", "true");

        Session sessionSSL = Session.getInstance(propsSSL);
        sessionSSL.setDebug(true);

        Message messageSSL = new MimeMessage(sessionSSL);
        messageSSL.setFrom(new InternetAddress(from, "DNS Admin"));
        messageSSL.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        messageSSL.setSubject("IP Change");
        messageSSL.setText(ipAddress);

        Transport transportSSL = sessionSSL.getTransport();
        transportSSL.connect("smtp.gmail.com", 465, "Email goes here...", "Password goes here...");
        transportSSL.sendMessage(messageSSL, messageSSL.getAllRecipients());
        transportSSL.close();
    }
}
