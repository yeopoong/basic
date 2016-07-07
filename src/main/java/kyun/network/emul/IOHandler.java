package kyun.network.emul;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class IOHandler {
	
	private Socket sock = null;
    private String channelName = null;

    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    
    boolean isClosed;
    
    public IOHandler(String channelName, Socket sock) {
        this.channelName = channelName;
    	this.sock = sock;
    	openStream();
        Logger.debug(this + "서비스 초기화 되었습니다.");
    }
    
    public void openStream() {
        if (dis == null) {
            dis = openInputStream();
        }
        if (dos == null) {
            dos = openOutputStream();
        }
    }

    public DataInputStream openInputStream() {
        if (sock == null || sock.isClosed()) {
            throw new RuntimeException(this + "socket not opened...");
        }
        try {
            dis = new DataInputStream(sock.getInputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(this + "FAIL TO OPEN INPUT STREAM");
        }
        return dis;
    }
    
    public DataOutputStream openOutputStream() {
        if (sock == null || sock.isClosed()) {
            throw new RuntimeException(this + "socket not opened...");
        }
        try {
            dos = new DataOutputStream(sock.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(this + "FAIL TO OPEN OUTPUT STREAM");
        }
        return dos;
    }
    
    /**
     * 전문에 대해 바이트의 데이터를 전송한다.
     * 
     * @throws IOException
     */
    public int writeFully(byte[] data) throws IOException {
        if (dos == null) {
            throw new RuntimeException(this + "OUTPUT STREAM이 열려 있지 않습니다.");
        }
        try {
            Logger.debug(this + ">>>>> 송신[" + data.length + "]byte : [" + new String(data) + "]");
			dos.write(data, 0, data.length);
			dos.flush();
		} catch (IOException e) {
			throw e;
		}
        return data.length;
    }
    
    /**
     * 해당 바이트에 데이터를 읽어 들인다.
     */
    public byte[] readFully() throws IOException {
        if (dis == null) {
            throw new RuntimeException(this + "INPUT STREAM이 열려 있지 않습니다.");
        }
        
        byte[] headerMsg = new byte[4];
        byte[] receiveMsg = null;
        
        try {
        	
            dis.readFully(headerMsg);
        	
            // 전문길이 필드(4byte)를 제외한 전체 전문 길이(ex. 60 - 4)
			int msgLen = Integer.parseInt(new String(headerMsg));
        	
            // 전체 수신 메시지
			receiveMsg = new byte[msgLen + 4];
            System.arraycopy(headerMsg, 0, receiveMsg, 0, 4);  // receiveMsg 에 해더부 저장
            dis.readFully(receiveMsg, 4, msgLen);  // receiveMsg 에 body부 저장
		
            Logger.debug(this + "<<<<< 수신[" + receiveMsg.length + "]byte : [" + new String(receiveMsg) + "]");
        } catch (IOException e) {
            throw e; 
        }
        return receiveMsg;
    }
    
    public void close() {
		try {
			if (dis != null) {
				dis.close();
			}
		} catch (Exception e) {
		}

		try {
			if (dos != null) {
				dos.close();
			}
		} catch (Exception e) {
		}
		
		dis = null;
		dos = null;
		isClosed = true;
		
        Logger.debug(this + "서비스 종료 되었습니다.");
	}
	
	public String toString() {
	    return "[" + channelName +"]";
	}
}
