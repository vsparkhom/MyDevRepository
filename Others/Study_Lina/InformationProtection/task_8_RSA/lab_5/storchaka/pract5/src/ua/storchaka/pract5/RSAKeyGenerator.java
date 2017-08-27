package ua.storchaka.pract5;

import java.math.*;
import java.util.*;
import java.io.*;

public class RSAKeyGenerator
{

    private BigInteger top;
    private BigInteger bot;

    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger phi;
    
    private RSAKey publicKey;
    private RSAKey privateKey;

    private String primesOut;
    private String kaOut;
    
    public static BigInteger BIG_INT_TWO = new BigInteger("2");
    

    public RSAKeyGenerator(BigInteger bot, BigInteger top)
    {
        this.top = top;
        this.bot = bot;
    }
    
    
    public void generate(String primesOut, String kaOut)
    {
        this.primesOut = primesOut; 
        this.kaOut = kaOut; 
        try
        {
            generatePQ();
            n = p.multiply(q);
            phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
            System.out.println("p = " + p);
            System.out.println("q = " + q);
            System.out.println("N = " + n);
            System.out.println("phi(N) = " + phi); 
            BigInteger ka = generateKa();
            System.out.println("Ka = " + ka);
            BigInteger kb = ka.modInverse(phi);
            publicKey = new RSAKey(ka, n);
            privateKey = new RSAKey(kb, n);
            System.out.println("Kb = " + kb);
        } catch (IOException e)
        {
            throw new RuntimeException("IO problems", e);
        }
    }
    
    public RSAKey getPublicKey()
    {
        return publicKey;
    }
    
    public RSAKey getPrivateKey()
    {
        return privateKey;
    }
    
    
    private boolean isPrime(BigInteger n)
    {
        if (RSAKeyGenerator.BIG_INT_TWO.equals(n)) 
        {
            return true;
        }
        if (n.mod(RSAKeyGenerator.BIG_INT_TWO).equals(BigInteger.ZERO))
        {
            return false;
        }
        BigInteger to = n.divide(new BigInteger("3"));
        BigInteger i = new BigInteger("3");
        for (; i.compareTo(to) != 1; i = i.add(RSAKeyGenerator.BIG_INT_TWO))
        {
            if (n.mod(i).equals(BigInteger.ZERO))
            {
                return false;
            }
        }
        return true;
    }
    
    private void generatePQ() throws IOException
    {
        BufferedWriter out = null;
        List l = new LinkedList();
        try
        {
            out = new BufferedWriter(new FileWriter(primesOut));
            BigInteger i = bot;
            for (; i.compareTo(top) != 1; i = i.add(BigInteger.ONE))
            {
                if (isPrime(i))
                {
                    out.write(i + "\r\n");
                    l.add(i);
                }
            }
            Random r = new Random();
            p = (BigInteger)l.get(r.nextInt(l.size()-1));
            q = (BigInteger)l.get(r.nextInt(l.size()-1));
        } finally
        {
            if (out != null)
            {
                out.close();
            }
        }
    }
    
    private BigInteger generateKa() throws IOException
    {
        BufferedWriter out = null;
        try
        {    
            out = new BufferedWriter(new FileWriter(kaOut));
            BigInteger i = RSAKeyGenerator.BIG_INT_TWO;
            long count = 0;
            for (; i.compareTo(phi) != 1; i = i.add(BigInteger.ONE))
            {
                if (i.gcd(phi).equals(BigInteger.ONE))
                {
                    out.write(i + "\r\n");
                    count++;
                }
            }
            out.close();
            Random r = new Random();
            return getNumFromFile(kaOut, Math.abs(r.nextLong())%count);
        } finally
        {
            if (out != null)
            {
                out.close();
            }
        }
    }
    
    private BigInteger getNumFromFile(String fileName, long c) throws IOException
    {
        BufferedReader in = null;
        long count = 0;
        try
        {
            in = new BufferedReader(new FileReader(fileName));
            for (String s = in.readLine(); s != null; s = in.readLine())
            {
                if (count == c)
                {
                    return new BigInteger(s);
                }
                count++;
            }
        } finally
        {
            if (in != null)
            {
                in.close();
            }
        }
        return BigInteger.ZERO;
    }
   
}