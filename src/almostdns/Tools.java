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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
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
    private final String to = "ianweeks2004@gmail.com";

    // Check exteranl ip address.
    public String getAddress() throws MalformedURLException, IOException
    {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
        return in.readLine(); //you get the IP as a String
    }

    // Send an email.
    public void sendEmail(String ipAddress) throws AddressException, MessagingException
    {
        //Set email sending settings.
        Properties myProps = new Properties();
        myProps.put("mail.smtp.host", host);
        myProps.put("mail.smtp.port", "465");
        myProps.put("mail.debug", "false");
        myProps.put("mail.smtp.auth", true);
        myProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        myProps.put("mail.smtp.starttls.enable", "true");

        //Set username and password
        Session mySession = Session.getInstance(myProps, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("festival.project.ashleyandian@gmail.com", "festivalprojectashleyandian");
            }
        });

        Message theMsg = new MimeMessage(mySession);
        theMsg.setFrom(new InternetAddress(from));
        InternetAddress sendTo = new InternetAddress(to);
        theMsg.setRecipient(Message.RecipientType.TO, sendTo);
        theMsg.setSubject("Admin alert.");
        theMsg.setSentDate(new Date());
        theMsg.setContent(ipAddress, "text/plain");
        Transport.send(theMsg);

    }
}
