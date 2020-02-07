package vlpa.java.practices.nio;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MappedChannelWrite {

    private static String FILE_PATH = "res/test-write.txt";
    private static int alphabetCount = 26;

    public static void main(String[] args) {

        try (FileChannel ch = (FileChannel) Files.newByteChannel(Paths.get(FILE_PATH), StandardOpenOption.WRITE
                , StandardOpenOption.READ, StandardOpenOption.CREATE)) {

            MappedByteBuffer buff = ch.map(FileChannel.MapMode.READ_WRITE, 0, alphabetCount);
            for(int i=0; i<alphabetCount; i++) {
                buff.put((byte)('a' + i));
            }

        } catch (IOException e) {
             e.printStackTrace();
        }

    }

}
