import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class SocketListener implements Runnable {
    private final int MAXSIZE = 65536; // 64 kb

    private BufferedReader in;
    private PrintWriter out;
    private Socket s;

    private Arguments arguments;
    private List<String> peers;
    private String target;

    private byte[] inBuffer;
    private byte[] outBuffer;
    private SocketWriter server;


    public SocketListener(Socket s, Arguments arg) throws Exception {
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out = new PrintWriter(s.getOutputStream());
        this.s = s;
        this.arguments = arg;

        this.inBuffer = new byte[MAXSIZE];
        this.outBuffer = new byte[MAXSIZE];

        peers = arg.getArgumentsOf("peers");
        target = arg.getArgumentsOf("target").get(0);
    }

    public void run() {
        int len;
        try {
            while((len = s.getInputStream().read(inBuffer,0, MAXSIZE)) != -1) {
                System.out.println("You typed: " + Arrays.toString(inBuffer));
                outBuffer = Arrays.copyOf(inBuffer,len);
                s.getOutputStream().write(outBuffer);
                s.getOutputStream().flush();
            }
            s.shutdownInput();
            s.shutdownOutput();
            s.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
