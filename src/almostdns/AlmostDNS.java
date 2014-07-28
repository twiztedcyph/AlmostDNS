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

import javax.mail.MessagingException;

/**
 * Main
 *
 * @author Twiztedcyph
 * @version 1
 */
public class AlmostDNS
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        final Tools t = new Tools();
        
        Thread emailThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    t.sendEmail("192.168.0.1");
                    System.out.println("Email sent.");
                } catch (MessagingException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        
        emailThread.start();
    }
}
