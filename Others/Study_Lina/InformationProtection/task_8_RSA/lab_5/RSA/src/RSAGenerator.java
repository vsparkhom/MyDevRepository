
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class RSAGenerator {

    private BigInteger p;
    private BigInteger q;
    protected BigInteger n;
    private BigInteger phi;
    protected BigInteger Ka;
    protected BigInteger Kb;
    private static final int a = 111;
    private static final int b = 513;

    public RSAGenerator() {
        ArrayList<BigInteger> mas = generateAB();
        writeFile(mas, "prime.txt");
        generatePQ(mas);
        System.out.println("P: " + p);
        System.out.println("Q: " + q);
        generateN();
        System.out.println("N: " + n);
        generatePhi();
        System.out.println("PHI: " + phi);
        getKa(generateKeys("keys.txt"));
        System.out.println("Ka: " + Ka);
        getKb();
        System.out.println("Kb: " + Kb);
    }

    private void writeFile(ArrayList<BigInteger> array, String fileName) {
        BufferedWriter file = null;
        try {
            file = new BufferedWriter(new FileWriter(fileName));
            for (BigInteger element : array) {
                file.write(element + "\r\n");
            }
            file.close();
        } catch (IOException e) {
            System.out.print(e.toString());
        }

    }

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

    private ArrayList<BigInteger> generateAB() {
        ArrayList<BigInteger> arrayList = new ArrayList<BigInteger>();
        for (int i = 0; i < b - a; i++) {
            BigInteger currentValue = new BigInteger(new Integer(a + i).toString());
            if (isPrime(currentValue)) {
                arrayList.add(currentValue);
            }
        }
        return arrayList;

    }

    private void generatePQ(ArrayList<BigInteger> array) {
        Random r = new Random();
        p = array.get(r.nextInt(array.size()));
        q = array.get(r.nextInt(array.size()));
    }

    private void generateN() {
        n = p.multiply(q);
    }

    private void generatePhi() {
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    }

    private ArrayList<BigInteger> generateKeys(String fileName) {
        BufferedWriter file = null;
        ArrayList<BigInteger> arrayKeys = new ArrayList<BigInteger>();
        try {
            file = new BufferedWriter(new FileWriter(fileName));
            for (BigInteger i = new BigInteger("2"); i.compareTo(phi) != 1; i = i.add(BigInteger.ONE)) {
                if (i.gcd(phi).equals(BigInteger.ONE)) {
                    arrayKeys.add(i);
                    //System.out.println("find key: "+i);
                    file.write(i + "\r\n");
                }
            }
            file.close();
        } catch (IOException e) {
            System.out.print(e.toString());
        } finally {
            return arrayKeys;
        }
    }

    private void getKa(ArrayList<BigInteger> arrayKeys) {
        Random r = new Random();
        Ka = arrayKeys.get(r.nextInt(arrayKeys.size()));
    }

    private void getKb() {
        Kb = Ka.modInverse(phi);
    }
}
