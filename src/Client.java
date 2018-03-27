import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    static Socket socket;

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        Boolean quit = false;

        String message;

        System.out.println("Enter the host address");

        String host = sc.next();

        System.out.println("Enter the port");

        int port = sc.nextInt();

        System.out.println("The client will send a message to: " + host + ":" + port);

        try
        {

            socket = new Socket(host, port);

            System.out.println("Client connected to socket");

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            DataInputStream dis = new DataInputStream(socket.getInputStream());

            while (!quit)
            {
                message = sc.next();

                if (message.equalsIgnoreCase("quit"))
                {
                    quit = true;
                }
                else
                {
                    dos.writeUTF(message);

                    dos.flush();

                    Thread.sleep(10);

                    System.out.println("Client wrote & start waiting for data from server...");

                    System.out.println("reading...");

                    String in = dis.readUTF();

                    System.out.println(in);
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
