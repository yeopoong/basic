package kyun.network.emul;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    private int listenPort;

    public Server(int listenPort) {
        this.listenPort = listenPort;
    }
    
    public void start() {
    	try {
    		init();
            Socket socket = null;
            int no = 1;
            while (serverSocket != null && !serverSocket.isClosed()) {
                try {
    				socket = serverSocket.accept();
                    Logger.debug("Socket 연결됨:" + socket + ":" + no++);
                    /////////////////////////////////
    				new WorkerThread(socket).start();
                    /////////////////////////////////
    			} catch (IOException e) {
    				init();
    			}
            }
            destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void init() {
        try {
            if (serverSocket == null || serverSocket.isClosed()) {
                serverSocket = new ServerSocket(listenPort);
                Logger.debug("LISTENER OPEN PORT : [" + listenPort + "]");
            }
        } catch (IOException e) {
            Logger.error(e);
            serverSocket = null;
        }
    }

    /**
	 * close all
	 */
    public void destroy() {
        try {
            if (serverSocket != null)
                serverSocket.close();
        } catch (IOException e) {
        }
        serverSocket = null;
    }
    
    public static void main(String[] args) {
    	Server server = new Server(8888);
    	server.start();
    }
}

