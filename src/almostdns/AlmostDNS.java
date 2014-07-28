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
        
        
//        Thread emailThread = new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                try
//                {
//                    t.sendEmail(t.getAddress());
//                    System.out.println("Email sent.");
//                } catch (MessagingException ex)
//                {
//                    System.out.println("Email not sent.\n" + ex);
//                } catch (UnsupportedEncodingException ex)
//                {
//                    System.out.println("Email not sent.\n" + ex);
//                } catch (IOException ex)
//                {
//                    System.out.println("IP address cannot be found.\n" + ex);
//                }
//            }
//        });
        
//        emailThread.start();
        
        /*
         * on program start send an email with the current ip. then enter
         * the sleep loop and check if the ip has changed once every 10 minutes.
         * 
         * If the ip has changed then send an email, if not then restart the loop
         * timer.
         */
    }
}
