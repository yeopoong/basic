package kyun.basic.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class FileCharStream {

    public static void main(String[] args) throws IOException {
        copyChar();
    }

    private static void copyChar() throws IOException {
        Reader in = new FileReader("in.dat");
        Writer out = new FileWriter("out.dat");

        int count = 0;
        for (int ch; (ch = in.read()) != -1; count++) {
            out.write(ch);
        }

        System.out.println(count + " 문자 복사됨.");
        in.close();
        out.close();
    }
}
