package javaapplication4;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;


public class RSA {
   private final static BigInteger one      = new BigInteger("1");
   private final static SecureRandom random = new SecureRandom();

   private BigInteger privateKey;
   private BigInteger publicKey;
   private BigInteger modulus;
BigInteger phi;
BigInteger p;
BigInteger q;


   private boolean isPrime(BigInteger value) {
        if (value.equals(new BigInteger("2"))) {
            return true;
        } else if (value.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
            return false;
        }
        BigInteger to = value.divide(new BigInteger("3"));
        BigInteger i = new BigInteger("3");
        for (; i.compareTo(to) != 1; i = i.add(new BigInteger("2"))) {
            if (value.mod(i).equals(BigInteger.ZERO)) {
                return false;
            }
        }
        return true;
    }
 
   RSA(int N) {
      //BigInteger p = BigInteger.probablePrime(N/2, random);
      //BigInteger q = BigInteger.probablePrime(N/2, random);
      //BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

      Random r = new Random();
      do {
        p = new BigInteger(new Integer(r.nextInt(2000)+17000).toString());
        p = new BigInteger(new Integer(r.nextInt(2000)+17000).toString());
      } while (isPrime(p) && isPrime(q));

      phi = (p.subtract(one)).multiply(q.subtract(one));
    
      modulus    = p.multiply(q);
      publicKey  = key1();
      privateKey = publicKey.modInverse(phi);
   }

  public BigInteger key1(){
    BigInteger i;
    for (i= new BigInteger("2"); i.compareTo(phi)!=1; i=i.add(BigInteger.ONE ))
             {
        if(i.gcd(phi).equals(BigInteger.ONE))
            break;
    }
    return i;
  }


   BigInteger encrypt(BigInteger message) {
      return message.modPow(publicKey, modulus);
   }

   BigInteger decrypt(BigInteger encrypted) {
      return encrypted.modPow(privateKey, modulus);
   }

   public String toString() {
      String s = "";
      s += "public  = " + publicKey  + "\n";
      s += "private = " + privateKey + "\n";
      s += "modulus = " + modulus;
      return s;
   }

   public static void main(String[] args) {
      int N = 50;
      RSA key = new RSA(N);
      System.out.println(key);

      String alf = "0123456789!?*АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЮЯ" ;
      String word = "37Б*П";
      String s = "";
      word = word.toUpperCase();
       for (int i = 0 ; i <word.length(); i++)
         s+=alf.indexOf(word.charAt(i));

       BigInteger message = new BigInteger(s);

      BigInteger encrypt = key.encrypt(message);
      BigInteger decrypt = key.decrypt(encrypt);
      System.out.println("message   = " + message);
      System.out.println("encrpyted = " + encrypt);
      System.out.println("decrypted = " + decrypt);
   }
}