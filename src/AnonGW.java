import java.net.ServerSocket;
import java.net.Socket;

public class AnonGW {

    public static void main(String... args) throws Exception {
        ServerSocket sSocket = new ServerSocket(12345);
        Arguments arg = new Arguments(args);

        while (true) {
            Socket clSock = sSocket.accept();
            SocketListener worker = new SocketListener(clSock, arg);

            System.out.println("Assigning new thread to new client");

            Thread newClient = new Thread(worker);
            newClient.start();
        }
    }
}
