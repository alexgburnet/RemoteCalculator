import java.io.*;
import java.net.Socket;

public class Client implements Runnable{

    public String serverIP = "127.0.0.1";
    public int serverPort = 6868;
    public Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    private OutputStream out;
    private
    String calculation;

    String result;

    Client() {
        try {
            this.socket = new Socket(serverIP, serverPort);
            InputStream in = socket.getInputStream();
            this.reader = new BufferedReader(new InputStreamReader(in));
            this.out = socket.getOutputStream();
            this.writer = new PrintWriter(out, true);
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    public void getCalculation() {
        System.out.println("Input a calculation:");
        BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            calculation = inReader.readLine();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public synchronized void sendCalculation() {
        writer.println(calculation + "#");
    }

    public synchronized void getResult() {
        try {
            result = reader.readLine();
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    @Override
    public void run() {
        getCalculation();
        sendCalculation();
        getResult();
    }

    public static void main(String[] args) {
        while(true) {
            Client client = new Client();
            client.run();
        }
    }
}
