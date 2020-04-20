import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Worker implements Runnable{
    private BufferedReader in;
    private PrintWriter out;
    private Socket s;

    public Worker(Socket s) throws Exception{
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out = new PrintWriter(s.getOutputStream());
        this.s = s;
    }

    public void run() {
        String input;
        try {
            while ((input = in.readLine()) != null && !input.equals("exit")) {
                System.out.println("Received: "+ input);
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
