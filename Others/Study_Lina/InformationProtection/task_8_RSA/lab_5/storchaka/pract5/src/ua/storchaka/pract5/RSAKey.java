package ua.storchaka.pract5;

import java.math.*;

public class RSAKey
{
    private BigInteger key;
    private BigInteger n;
    
    
    public RSAKey()
    {
        key = BigInteger.ZERO;
        n = BigInteger.ZERO;
    }
    
    public RSAKey(BigInteger key, BigInteger n)
    {
        this.key = key;
        this.n = n;
    }
    
    public BigInteger getKey()
    {
        return key;
    }
    
    public BigInteger getN()
    {
        return n;
    }
    
    public void setKey(BigInteger key)
    {
        this.key = key;
    }
    
    public void setN(BigInteger n)
    {
        this.n = n;
    }    
}