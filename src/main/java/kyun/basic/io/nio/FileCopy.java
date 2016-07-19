package kyun.basic.io.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileCopy {

    public static void main(String[] args) throws IOException {
        Path from = Paths.get("in.dat");
        Path to = Paths.get("out.dat");

        Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("파일 복사 성공!");
    }
}
