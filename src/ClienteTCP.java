import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {

    public static void main(String... args) throws Exception{
        Socket socket = new Socket("127.0.0.1",12345);
        String input;
        Scanner sc = new Scanner(System.in);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        while((input=sc.nextLine())!=null && !input.equals("exit")) {
            out.println(input);
            out.flush();
            System.out.println("Server Sent: " + in.readLine());
        }
        socket.shutdownOutput();
        socket.shutdownInput();
        socket.close();
    }
}
