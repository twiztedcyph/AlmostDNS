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

/**
 *
 * @author Twiztedcyph
 */
public class Tools
{
    // Check for internet connection.
    public boolean isConnected()
    {
        return false;
    }
    
    // Check exteranl ip address.
    public String getAddress()
    {
        String output = new String();
        try
        {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            
            output = in.readLine(); //you get the IP as a String
        } catch (MalformedURLException ex)
        {
            output = "MalformedURLException " + ex;
        } catch (IOException ex)
        {
            output = "IOException " + ex;
        }
        return output;
    }
    
    // Send an email.
    public void sendEmail(String message)
    {
        
    }
}
