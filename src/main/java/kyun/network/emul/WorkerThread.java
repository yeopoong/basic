package kyun.network.emul;

import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;


public class WorkerThread extends Thread {
    
    private boolean exit = false;
    
    private Socket socket;

    public WorkerThread(Socket socket) {
    	this.socket = socket;
    }

    public void run() {
    	
        IOHandler ioHandler = new IOHandler("Server", socket); 
		while (!exit) {
			try {
                //1.요청을 수신한다.
                byte[] src = ioHandler.readFully();
                byte[] dest = new byte[62];
                
                //2.요청을 처리한다.
                System.arraycopy("0058".getBytes(), 0, dest, 0, 4);
                System.arraycopy(src, 4, dest, 4, 56);
                System.arraycopy("OK".getBytes(), 0, dest, 60, 2);
                
                //3.응답을 송신한다.
                ioHandler.writeFully(dest);
                exit = true;
			} catch (SocketTimeoutException e) {
                Logger.error("송신채널에서 통신종료 패킷을 수신하였습니다.", e);
				exit = true;
			} catch (EOFException e) {
                Logger.error("송신채널에서 통신종료 패킷을 수신하였습니다.", e);
				exit = true;
			} catch (SocketException e) {
                Logger.error("송신채널에서 통신연결이 종료되었습니다.", e);
				exit = true;
			} catch (Exception e) {
                Logger.error("송신채널에러입니다.", e);
				exit = true;
			}
		}
		ioHandler.close();
		close();
	}
    
    public void close() {
		try {
            socket.close();
            Logger.debug("Socket 종료됨:" + socket + ":" + socket.isClosed());
		    Logger.debug("=================================================");
        } catch (IOException e) {
        	Logger.error(e);
        }
    }
}

