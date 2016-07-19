package kyun.basic.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

public class FilterStream {

    public static void main(String[] args) throws IOException {
        Reader in = new FileReader("in.dat");
        BufferedReader br = new BufferedReader(in);

        Writer out = new FileWriter("out.dat");
        BufferedWriter bw = new BufferedWriter(out);
        PrintWriter pw = new PrintWriter(bw);

        int count = 0;
        for (String line; (line = br.readLine()) != null; count++) {
            bw.write(line);
            bw.newLine();
//            pw.println(line);
        }

        System.out.println(count + " 행 복사됨.");

        br.close();
        in.close();
        pw.close();
        bw.close();
        out.close();
    }
}
