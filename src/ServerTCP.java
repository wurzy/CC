import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTCP {
    public static void main(String... args) throws Exception{
        ServerSocket sSocket = new ServerSocket(12543);
        while (true) {
            //System.out.println(arg.getArgumentsOf("port").get(0));
            Socket clSock = sSocket.accept();
            Worker worker = new Worker(clSock);

            System.out.println("Assigning new thread to new gateway");
            Thread newClient = new Thread(worker);
            newClient.start();
        }


    /*
    public static void main(String[] args){
        Arguments arg = new Arguments();
        arg.setArgs(args);
        arg.printOut();
    }
    */
}

}
