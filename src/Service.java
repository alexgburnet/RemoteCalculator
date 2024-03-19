import java.io.*;
import java.net.Socket;

public class Service extends Thread{

    Socket socket;

    String[] request;

    String returnValue;

    Service(Socket socket) {
        this.socket = socket;
    }

    public void interpretRequest() {
        try {
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String tmp = reader.readLine();
            // Remove the terminating character '#'
            tmp = tmp.substring(0, tmp.length() - 1);

            request = tmp.split(" ");

            System.out.println("Request: " + tmp);

        } catch (IOException e) {
            System.out.println("Socket Exception: " + e);
        }
    }

    public void handleRequest() {

        int num1 = Integer.parseInt(request[1]);
        int num2 = Integer.parseInt(request[2]);

        String operation = request[0];

        if (operation.equals("+")) {
            returnValue = String.valueOf(num1 + num2);
        } else if (operation.equals("*")) {
            returnValue = String.valueOf(num1 * num2);
        }
    }

    public void returnResult() {

        try {

            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);

            if (returnValue == null) {
                returnValue = "An error occurred";
            }

            writer.println(returnValue);
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }


    @Override
    public void run() {

        interpretRequest();
        handleRequest();
        returnResult();
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
