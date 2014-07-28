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
    private final String from = "festival.project.ashleyandian@gmail.com";
    private final String to = "ianweeks05@gmail.com";

    // Check exteranl ip address.
    public String getAddress() throws MalformedURLException, IOException
    {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
        return in.readLine(); //you get the IP as a String
    }

    // Send an email.
    public void sendEmail(String ipAddress) throws AddressException, MessagingException, UnsupportedEncodingException
    {
        Properties propsSSL = new Properties();

        // EVEN IF YOU SKIP THESE TWO PROP IT WOULD WORK
        propsSSL.put("mail.transport.protocol", "smtps");
        propsSSL.put("mail.smtps.host", "smtp.gmail.com");

        // THIS IS THE MOST IMPORTANT PROP --> "mail.smtps.auth"
        propsSSL.put("mail.smtps.auth", "true");

        Session sessionSSL = Session.getInstance(propsSSL);
        sessionSSL.setDebug(true);

        Message messageSSL = new MimeMessage(sessionSSL);
        messageSSL.setFrom(new InternetAddress(from, "DNS Admin"));
        messageSSL.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // real recipient
        messageSSL.setSubject("IP Change");
        messageSSL.setText(ipAddress);

        Transport transportSSL = sessionSSL.getTransport();
        // EVEN IF YOU SKIP PORT NUMBER , IT WOULD WORK
        transportSSL.connect("smtp.gmail.com", 465, "festival.project.ashleyandian@gmail.com", "festivalprojectashleyandian"); // account used
        transportSSL.sendMessage(messageSSL, messageSSL.getAllRecipients());
        transportSSL.close();

        System.out.println("Email sent.");
    }
}
