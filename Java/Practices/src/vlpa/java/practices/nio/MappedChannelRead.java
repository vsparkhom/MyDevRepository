package vlpa.java.practices.nio;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MappedChannelRead {

    private static String FILE_PATH = "res/test.txt";

    public static void main(String[] args) {

        try(FileChannel ch = (FileChannel)Files.newByteChannel(Paths.get(FILE_PATH))) {

            long fileSize = ch.size();
            MappedByteBuffer buff = ch.map(FileChannel.MapMode.READ_ONLY, 0, fileSize);

            for(int i=0; i<fileSize; i++) {
                System.out.print((char)buff.get());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
