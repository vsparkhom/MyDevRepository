package ua.storchaka.pract5;

import java.math.*;
import java.util.*;
import java.io.*;

import ua.storchaka.pract5.exceptions.*;

public class RSA
{

    private List abc;
    
    public RSA(List abc)
    {
        this.abc = abc;
    }
    
    public int code(BufferedReader in, BufferedWriter out, RSAKey key) throws CoderException
    {
        try
        {
            int count = 0;
            for (int c = in.read(); c != -1; c = in.read())
            {
                int pos = abc.indexOf(new Integer(c));
                out.write(process(new BigInteger(new Integer(pos).toString()), key) + "\r\n");
                count++;
            }
            return count;
        } catch (IOException e)
        {
            throw new CoderException("Cannot code data", e);
        }
    }
    
    public int decode(BufferedReader in, BufferedWriter out, RSAKey key) throws CoderException
    {
        int count = 0;
        String s = "";
        try
        {

            for (s = in.readLine(); s != null; s = in.readLine())
            {
                out.write(((Integer)abc.get(process(new BigInteger(s), key).intValue())).intValue());
                count++;
            }
        } catch (IOException e)
        {
            throw new CoderException("Cannot code data", e);
        }
        return count;        
    }
    
    private BigInteger process(BigInteger n, RSAKey key) throws CoderException
    {
        return n.modPow(key.getKey(), key.getN());
    }
  
}