import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

/* Serve para estabelecer conexao Cliente-Gateway
 */

public class SocketListener implements Runnable {
    private final int MAXSIZE = 65536; // 64 kb

    private OutputStream out;
    private InputStream in;
    private Socket client;
    private Socket server;

    // vai ser preciso para a 2 fase
    private Arguments arguments;
    private List<String> peers;
    private String target;
    private int port;

    private byte[] inBuffer; // enviado pelo client
    private byte[] outBuffer; // enviado para o servidor
    private SocketWriter sendback;


    public SocketListener(Socket s, Arguments arg) throws Exception {
        this.peers = arg.getArgumentsOf("peers");
        this.target = arg.getArgumentsOf("target").get(0);
        this.port = Integer.valueOf(arg.getArgumentsOf("port").get(0));
        this.arguments = arg;

        //this.server = new Socket(target,port); //MUDAR AQUI
        this.server = new Socket("127.0.0.1",12543);

        this.in = s.getInputStream();
        this.out = server.getOutputStream();
        this.client = s;

        this.inBuffer = new byte[MAXSIZE];

        this.sendback = new SocketWriter(this.server,this.client,this.target,this.port);
       // this.outBuffer = new byte[MAXSIZE];
    }

    public void run() {
        int len;
        try {
            // recebe bytes do cliente
            while((len = in.read(inBuffer,0, MAXSIZE)) != -1) {
                System.out.println("Client: " + client.getRemoteSocketAddress() + "\n" + Arrays.toString(inBuffer));
                outBuffer = Arrays.copyOf(inBuffer,len);
                out.write(outBuffer);
                out.flush();
            }
           // Thread toServerToClient = new Thread(this.sendback);
           // toServerToClient.start();

            client.shutdownInput();
            client.shutdownOutput();
            client.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
