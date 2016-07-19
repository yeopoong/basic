package kyun.basic.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileByteStream {

    public static void main(String[] args) throws IOException {
        copyByte();
        copyBuff();
    }

    private static void copyByte() throws IOException {
        InputStream in = new FileInputStream("in.dat");
        OutputStream out = new FileOutputStream("out.dat");

        int count = 0;
        for (int b; (b = in.read()) != -1; ++count) {
            out.write(b);
        }

        System.out.println(count + " 바이트 복사됨.");
        in.close();
        out.close();
    }

    private static void copyBuff() throws IOException {
        InputStream in = new FileInputStream("in.dat");
        OutputStream out = new FileOutputStream("out.dat");

        int count = 0;
        byte buf[] = new byte[1024];
        for (int cnt; (cnt = in.read(buf)) != -1; count += cnt) {
            out.write(buf, 0, cnt);
        }

        System.out.println(count + " 바이트 복사됨.");
        in.close();
        out.close();
    }
}
