package kyun.network.emul;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

    public static void main(String[] args) {
    	Socket sock = null;
    	IOHandler ioHandler = null;
    	try {
            //1.소켓을 연결한다.
			sock = new Socket("localhost",8888);
			
            //2.요청을 송신한다.
			ioHandler = new IOHandler("Client", sock);
			byte[] data = "0056L201306041020300000000000000000000000000      0         ".getBytes();
			ioHandler.writeFully(data);
			
            //3.응답을 수신한다.
			data = ioHandler.readFully();
		} catch (UnknownHostException e) {
			Logger.error(e);
		} catch (IOException e) {
			Logger.error(e);
		} finally {
	        try {
	        	ioHandler.close();
				sock.close();
			} catch (IOException e) {
				Logger.error(e);
			}
		}
    }
}

