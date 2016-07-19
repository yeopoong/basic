package kyun.basic.io.nio;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelExample {

    public static void main(String[] args) throws Exception {
        Path from = Paths.get("in.dat");
        Path to = Paths.get("out.dat");

        FileChannel fileChannelFrom = FileChannel.open(from, StandardOpenOption.READ);
        FileChannel fileChannelTo = FileChannel.open(to, StandardOpenOption.WRITE);

        ByteBuffer buffer = ByteBuffer.allocateDirect(100);

        int byteCount;
        while (true) {
            buffer.clear();
            byteCount = fileChannelFrom.read(buffer);

            if (byteCount == -1)
                break;

            buffer.flip();
            fileChannelTo.write(buffer);
        }

        fileChannelFrom.close();
        fileChannelTo.close();

        System.out.println("파일 복사 성공!!");
    }
}
