package kyun.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) {
        ServerSocket server = null;
        Socket client = null;

        try {
            server = new ServerSocket(9999);
            while (true) {
                System.out.println("Server:Waiting for request.");
                client = server.accept();
                System.out.println("Server:Accepted.");

                InputStream is = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String data = null;
                StringBuilder receivedData = new StringBuilder();
                while ((data = br.readLine()) != null) {
                    receivedData.append(data);
                }
                System.out.println("Received data:" + receivedData);

                br.close();
                is.close();
                client.close();

                if (receivedData.toString().equals("EXIT")) {
                    System.out.println("Stop SocketServer");
                    break;
                }
                System.out.println("-----------------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
