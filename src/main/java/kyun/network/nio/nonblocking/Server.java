package kyun.network.nio.nonblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class Server {

    public static void main(String[] args) {
        Selector selector = null;
        ServerSocketChannel serverSocketChannel = null;

        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(9999));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                System.out.println("[연결 기다림]");
                int keyCount = selector.select();

                if (keyCount == 0) {
                    continue;
                }

                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();

                ByteBuffer byteBuffer = null;
                Charset charset = Charset.forName("UTF-8");

                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();

                    if (selectionKey.isAcceptable()) {
                        serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = serverSocketChannel.accept();

                        socketChannel.configureBlocking(false);
                        SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);
                        key.attach(socketChannel);

                        InetSocketAddress isa = (InetSocketAddress) socketChannel.getRemoteAddress();
                        System.out.println("[연결 수락함] " + isa.getHostName());

                    } else if (selectionKey.isReadable()) {
                        SocketChannel socketChannel = null;
                        try {
                            socketChannel = (SocketChannel) selectionKey.attachment();

                            byteBuffer = ByteBuffer.allocate(100);
                            int byteCount = socketChannel.read(byteBuffer);
                            byteBuffer.flip();

                            String data = charset.decode(byteBuffer).toString();
                            System.out.println("[데이터 받기 성공]: " + data);

                            selectionKey.interestOps(SelectionKey.OP_WRITE);
                            selector.wakeup();
                        } catch (Exception e) {
                            e.printStackTrace();
                            socketChannel.close();
                        }

                    } else if (selectionKey.isWritable()) {
                        SocketChannel socketChannel = null;
                        try {
                            socketChannel = (SocketChannel) selectionKey.attachment();

                            byteBuffer = charset.encode("Hello Client");
                            socketChannel.write(byteBuffer);
                            System.out.println("[데이터 보내기 성공]");

                            selectionKey.interestOps(SelectionKey.OP_READ);
                            selector.wakeup();
                        } catch (Exception e) {
                            e.printStackTrace();
                            socketChannel.close();
                        }
                    }

                    iterator.remove();
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        if (serverSocketChannel != null && serverSocketChannel.isOpen()) {
            try {
                serverSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (selector != null && selector.isOpen()) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
