import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WorkerTCP implements Runnable {

    private BufferedReader in;
    private PrintWriter out;
    private Socket s;

    public WorkerTCP(Socket s) throws Exception {
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out = new PrintWriter(s.getOutputStream());
        this.s = s;
    }

    public void run() {
        String input;
        while(true) {
            try {
                input=in.readLine();
                if(input!=null && !input.equals("q")) {
                    out.println("You typed: " + input);
                    out.flush();
                }
                else {
                    s.shutdownInput();
                    s.shutdownOutput();
                    s.close();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
