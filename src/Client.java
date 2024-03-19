import java.io.*;
import java.net.Socket;

public class Client implements Runnable{

    public String serverIP = "127.0.0.1";
    public int serverPort = 6868;
    public Socket socket;
    String calculation;

    String result;

    Client() {
        try {
            this.socket = new Socket(serverIP, serverPort);
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

    public void sendCalculation() {

        try {
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);
            writer.println(calculation);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

    }

    public void getResult() {

        try {
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            result = reader.readLine();
            System.out.println("Result");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

    }

    @Override
    public void run() {

        while (true) {
            getCalculation();
            sendCalculation();
            getResult();
        }

    }

    public static void main(String[] args) {

        Client client = new Client();
        client.run();

    }
}
