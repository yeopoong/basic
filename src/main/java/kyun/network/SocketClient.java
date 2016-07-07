package kyun.network;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

public class SocketClient {

	public static void main(String[] args) {
		SocketClient sample = new SocketClient();

		for (int loop = 0; loop < 3; loop++) {
            sample.sendSocketData("I like Java at " + new Date());
        }

        sample.sendSocketData("EXIT");
	}

	public void sendSocketData(String data) {
		Socket socket = null;
		
		try {
			System.out.println("Client:Connecting");
			socket = new Socket("127.0.0.1", 9999);
			System.out.println("Client:Connect status=" + socket.isConnected());
			
			Thread.sleep(1000);
			
			OutputStream os = socket.getOutputStream();
			BufferedOutputStream out = new BufferedOutputStream(os);

			byte[] bytes = data.getBytes();
			out.write(bytes);
			System.out.println("Client:Sent data");

			out.close();
            System.out.println("Client:closed");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
