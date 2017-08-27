
import java.math.BigInteger;

public class RSAUser {
    private BigInteger key;
    private BigInteger n;

    public RSAUser() {
        key = BigInteger.ZERO;
        n = BigInteger.ZERO;
    }

    public RSAUser(BigInteger key, BigInteger n) {
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
