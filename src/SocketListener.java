import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketListener implements Runnable {

    private BufferedReader in;
    private PrintWriter out;
    private Socket s;
    Arguments arguments;

    public SocketListener(Socket s, Arguments arg) throws Exception {
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out = new PrintWriter(s.getOutputStream());
        this.s = s;
        this.arguments = arg;
    }

    public void run() {
        String input;
        try {

            while ((input = in.readLine()) != null && !input.equals("q")) {
                out.println("You typed: " + input);
                out.flush();
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
