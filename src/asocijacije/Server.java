package asocijacije;

import java.io.IOException;
import java.net.*;

public class Server {
    public static int port = 12345;

    public static void main(String[] args) {
        try(ServerSocket socket = new ServerSocket(12345)){
            System.out.println("server radi");
            while(true) {

                Socket client = socket.accept();
                new Thread(new ClientThread(client)).start();;

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
