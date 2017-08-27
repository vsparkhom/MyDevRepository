
import ua.storchaka.pract5.*;
import java.util.*;
import java.math.*;
import java.io.*;

public class Pract5
{

    public static void main(String args[]) throws Exception
    {
        BigInteger bot = new BigInteger("2");
        BigInteger top = new BigInteger("111");
        RSAKeyGenerator g = new RSAKeyGenerator(bot, top);
        g.generate("primes.txt", "ka.txt");
        
        List abc = readCharsFromFile("abc.txt");
        RSA coder = new RSA(abc);
        
        BufferedReader in = new BufferedReader(new FileReader("in.txt"));
        BufferedWriter out = new BufferedWriter(new FileWriter("out.txt"));
       
        coder.code(in, out, g.getPublicKey());

        out.close();
        in.close();
        
        in = new BufferedReader(new FileReader("out.txt"));
        out = new BufferedWriter(new FileWriter("out1.txt"));
       
        coder.decode(in, out, g.getPrivateKey());
        
        out.close();
        in.close();

    }
    
    public static List readCharsFromFile(String fileName) throws Exception
    {
        Reader in = null;
        List list = new ArrayList();
        try
        {
            in = new BufferedReader(new FileReader(fileName));
            for (int c = in.read(); c != -1; c = in.read())
            {
                list.add(new Integer(c));
            }
        } finally
        {
            if (in != null)
            {
                in.close();
            }
        }
        return list;
    }
    
}