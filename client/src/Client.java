import java.io.*;
import java.net.*;

public class Client
{
    public static void main(String[] args)
    {
        try (Socket socket = new Socket("localhost", 5000))
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder fullSonnet = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null)
            {
                fullSonnet.append(line).append("\n");
            }
            System.out.println("Полученный сонет:\n" + fullSonnet.toString());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
