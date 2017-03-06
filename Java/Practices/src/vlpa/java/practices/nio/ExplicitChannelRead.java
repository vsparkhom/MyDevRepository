package vlpa.java.practices.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExplicitChannelRead {

    private static String FILE_PATH = "res/test.txt";

    public static void main(String[] args) {

        try(SeekableByteChannel ch = Files.newByteChannel(Paths.get(FILE_PATH))) {

            int count;
            ByteBuffer buff = ByteBuffer.allocate(128);

            do {
                count = ch.read(buff);
                if (count != -1) {
                    buff.rewind();
                    for (int i=0; i<count; i++) {
                        System.out.print((char)buff.get());
                    }
                }

            } while (count != -1);

        } catch (IOException e) {
            System.err.println(e);
        }

    }

}
