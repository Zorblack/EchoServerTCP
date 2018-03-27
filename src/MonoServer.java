import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MonoServer implements Runnable {

    private static Socket clientSocket;

    public MonoServer (Socket client)
    {
        MonoServer.clientSocket = client;
    }

    @Override
    public void run()
    {
        try {

            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            DataInputStream in = new DataInputStream(clientSocket.getInputStream());

            System.out.println("DataInputStream created");

            System.out.println("DataOutputStream  created");

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // основная рабочая часть //
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            while (!clientSocket.isClosed())
            {
                System.out.println("Server reading from channel");

                String entry = in.readUTF();

                System.out.println("READ from clientDialog message - " + entry);

                if (entry.equalsIgnoreCase("quit"))
                {

                    System.out.println("Client initialize connections suicide ...");

                    out.writeUTF("Server reply - " + entry + " - OK");

                    Thread.sleep(3000);

                    break;
                }

                System.out.println("Server try writing to channel");

                out.writeUTF("Server reply - " + entry + " - OK\n");

                out.flush();
            }

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println("Closing connections & channels.");


            in.close();
            out.close();

            clientSocket.close();

            System.out.println("Closing connections & channels - DONE.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
