package kyun.network.nio.blocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Server {

    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null;

        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(true);
            serverSocketChannel.bind(new InetSocketAddress(9999));

            while (true) {
                System.out.println("[연결 기다림]");
                SocketChannel socketChannel = serverSocketChannel.accept();
                InetSocketAddress isa = (InetSocketAddress) socketChannel.getRemoteAddress();
                System.out.println("[연결 수락함] " + isa.getHostName());

                ByteBuffer byteBuffer = null;
                Charset charset = Charset.forName("UTF-8");

                byteBuffer = ByteBuffer.allocate(100);
                int byteCount = socketChannel.read(byteBuffer);
                byteBuffer.flip();

                String data = charset.decode(byteBuffer).toString();
                System.out.println("[데이터 받기 성공]: " + data);

                byteBuffer = charset.encode("Hello Client");
                socketChannel.write(byteBuffer);
                System.out.println("[데이터 보내기 성공]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (serverSocketChannel.isOpen()) {
            try {
                serverSocketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
