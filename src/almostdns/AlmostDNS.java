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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
        boolean runProgram = true;
        Tools tools = new Tools();
        String initialIP, newIP;
        
        try
        {
            initialIP = tools.getAddress();
        } catch (IOException ex)
        {
            initialIP = ex.toString();
        }
        
        try
        {
            tools.sendEmail("Program start: " + initialIP);
        } catch (MessagingException ex)
        {
            System.err.println("Messaging exception: " + ex);
        } catch (UnsupportedEncodingException ex)
        {
            System.err.println("Unsupported encoding exception: " + ex);
        }
        
        // Just run constant for now....
        while(runProgram)
        {
            try
            {
                // Test the current address against the initialIP;
                newIP = tools.getAddress();
                if (!initialIP.equals(newIP))
                {
                    //If the ip has changed then send a notification email.
                    initialIP = newIP;
                    tools.sendEmail("Program update: " + initialIP);
                }
                
                // Ten minute sleep.  This can be changed to suit your needs.
                Thread.sleep(600000);
            } catch (InterruptedException ex)
            {
                System.err.println("Interupt exception: " + ex);
            } catch (IOException ex)
            {
                System.err.println("IO exception: " + ex);
            } catch (MessagingException ex)
            {
                System.err.println("Messaging exception: " + ex);
            }
        }
    }
}
