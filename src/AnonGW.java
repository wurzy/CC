import java.net.ServerSocket;
import java.net.Socket;

public class AnonGW {
    /* Fica a escuta na porta 80 por TCP
     */
    public static void main(String... args) throws Exception {
        Arguments arg = new Arguments(args);
        //ServerSocket sSocket = new ServerSocket(Integer.valueOf(arg.getArgumentsOf("port").get(0)));
        ServerSocket sSocket = new ServerSocket(12346);

        while (true) {
            Socket clSock = sSocket.accept();
            SocketListener worker = new SocketListener(clSock, arg);
            System.out.println("Assigning new thread to new client");
            Thread newClient = new Thread(worker);
            newClient.start();
        }
    }
}
