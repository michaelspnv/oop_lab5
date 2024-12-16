import java.io.*;
import java.net.*;
import java.util.*;

public class Server
{
    public static void main(String[] args)
    {
        try (ServerSocket serverSocket = new ServerSocket(5000))
        {
            System.out.println("Сервер запущен, ожидаем подключения...");

            while (true)
            {
                try (Socket clientSocket = serverSocket.accept())
                {
                    System.out.println("Клиент подключен");
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    String sonnet = getRandomSonnet();
                    out.println(sonnet);
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static String getRandomSonnet()
    {
        List<String> sonnets = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/sonnets.txt")))
        {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                if (line.trim().isEmpty())
                {
                    if (sb.length() > 0)
                    {
                        sonnets.add(sb.toString().trim());
                        sb.setLength(0);
                    }
                } else {
                    sb.append(line).append("\n");
                }
            }
            if (sb.length() > 0)
            {
                sonnets.add(sb.toString().trim());
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        Collections.shuffle(sonnets);
        return sonnets.isEmpty() ? "Сонетов не найдено." : sonnets.get(0);
    }
}
