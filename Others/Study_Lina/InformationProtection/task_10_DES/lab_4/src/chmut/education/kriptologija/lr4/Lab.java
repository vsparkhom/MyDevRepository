package chmut.education.kriptologija.lr4;

import java.io.*;

public class Lab {
    public static void main(String args[]) throws Exception {

        String originalFile = "C:/original.txt";
        String encodedFile = "C:/encoded.txt";
        String decodedFile = "C:/decoded.txt";
        String keyFile = "C:/key.txt";

        codeFile(originalFile,encodedFile,keyFile, false);
        System.out.println();
        codeFile(encodedFile, decodedFile, keyFile, true);
    }

    public static void printInfo(String key, String in, String out, boolean isDecode) throws IOException {
        System.out.println(isDecode?"Decoding:":"Encoding:");
        System.out.println("\tKey file:        " + key);
        System.out.print("\tKey:             ");
        printFile(key);
        System.out.println("\tInput:           "+in);
        printFile(in);
        System.out.println("\tOutput:          "+out);
        printFile(out);
    }

    public static void printFile(String fileName) throws IOException {
        Reader in = null;
        try {
            in = new BufferedReader(new FileReader(fileName));
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char)c);
            }
            System.out.println();
        } finally {
            in.close();
        }
    }

    public static long getKey(String fileName) throws IOException {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(fileName));
            byte[] buf = new byte[8];
            int readed = in.read(buf);
            long l = 0;
            for (int i = readed-1; i >= 0; i--) {
                l <<= 8;
                l |= buf[i];
            }
            return l;
        } finally {
            in.close();
        }
    }

    public static void codeFile(String inFile, String outFile, String keyFile, boolean isDecode) throws IOException {
        DES des = new DES(getKey(keyFile));
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(inFile));
            out = new BufferedOutputStream(new FileOutputStream(outFile));
            byte[] buf = new byte[8];
            int readed;
            while ((readed = in.read(buf)) != -1) {
                long l = 0;
                for (int i = readed-1; i >= 0; i--) {
                    l <<= 8;
                    l |= (buf[i] & 0xFFl);
                }
                if (readed < 8) {
                    for (int i = 0; i < 8 - readed; i++) {
                        buf[i] = 0;
                    }
                }
                long res;
                if (!isDecode) {
                    res = des.encode(l);
                } else {
                    res = des.decode(l);
                }
                for (int i = 0; i < 8; i++) {
                    buf[i] = (byte)(res & 0xFF);
                    res >>= 8;
                    if (isDecode && buf[i] == 0) {
                        out.write(buf, 0, i);
                        return;
                    }
                }
                out.write(buf);
            }
        } finally {
            in.close();
            out.close();
            printInfo(keyFile, inFile, outFile, isDecode);
        }
    }

}