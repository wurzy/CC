import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

    public static void main(String... args) throws Exception {
        ServerSocket sSocket = new ServerSocket(12345);

        while (true) {
            Socket clSock = sSocket.accept();
            WorkerTCP worker = new WorkerTCP(clSock);

            System.out.println("Assigning new thread to new client");

            Thread newClient = new Thread(worker);
            newClient.start();
        }
    }
}
