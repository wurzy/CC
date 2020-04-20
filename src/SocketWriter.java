import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
/* Serve para estabelecer conexao Gateway-Server
 */
public class SocketWriter implements Runnable {
    private final int MAXSIZE = 65536; // 64 kb

    private Socket server;
    private Socket client;
    private int port;
    private String ip;

    private OutputStream out;
    private InputStream in;

    private byte[] inBuffer; // enviado pelo servidor
    private byte[] outBuffer; // enviado para o cliente


    public SocketWriter(Socket server, Socket cl, String ip, int port) throws Exception{
        this.server = server;
        this.client = cl;
        this.ip = ip;
        this.port = 123543;//port;
        this.in = this.server.getInputStream();
        this.out = this.client.getOutputStream();
    }

    public void run() {
        int len;
        try {
            while((len = in.read(inBuffer,0, MAXSIZE)) != -1) {
                System.out.println("Client: " + client.getRemoteSocketAddress() + "\n" + Arrays.toString(inBuffer));
                outBuffer = Arrays.copyOf(inBuffer,len);
                out.write(outBuffer);
                out.flush();
            }
            server.shutdownInput();
            server.shutdownOutput();
            server.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
