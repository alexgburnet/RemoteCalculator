import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    int serverPort = 6868;
    ServerSocket socket;

    Server() {

        // Initialise socket

        try {
            socket = new ServerSocket(serverPort);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

    }

    public void executeCucle() {

        // Listen for connections, accept and start a new thread for each.

        while (true) {
            try {

                Socket client = socket.accept();
                Service s = new Service(client);
                s.start();

            } catch (IOException e) {
                System.out.println("Exception: " + e);
            }
        }

    }

    public void shutdown() {
        try {
            socket.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public static void main(String args[]) {

        Server server = new Server();
        server.executeCucle();
    }


}
