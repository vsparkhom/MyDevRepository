package vlpa.java.practices.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ExplicitChannelWrite {

    private static String FILE_PATH = "res/test-write.txt";
    private static int alphabetCount = 26;

    public static void main(String[] args) {
        try (FileChannel ch = (FileChannel) Files.newByteChannel(Paths.get(FILE_PATH),
                StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {

            ByteBuffer buff = ByteBuffer.allocate(alphabetCount);
            for(int i=0; i<alphabetCount; i++) {
                buff.put((byte)('A' + i));
            }

            buff.rewind();
            ch.write(buff);
            System.out.printf("File saved: %s", FILE_PATH);

        } catch (IOException e) {
           e.printStackTrace();
        }
    }

}
