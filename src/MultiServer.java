import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MultiServer
{
    static ExecutorService executeIt = Executors.newFixedThreadPool(10);

    public static void main(String[] args)
    {
        try
        {
            ServerSocket server = new ServerSocket(2508);

            server.setSoTimeout(1800000);

            System.out.println("Server socket created");

            while (!server.isClosed())
            {
                Socket client = server.accept();

                executeIt.execute(new MonoServer(client));

                System.out.print("Connection accepted.");
            }

            executeIt.shutdown();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
