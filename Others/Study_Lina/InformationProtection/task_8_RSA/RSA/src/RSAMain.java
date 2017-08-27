
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class RSAMain {

    public static final String FILE_INPUT_TXT = "res/input.txt";
    private static String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789=";

    private static int[] codeDecode(int[] inputText, RSAUser user) {
        int[] outputText = new int[inputText.length];
        for (int i = 0; i < inputText.length; i++) {
            BigInteger temp = new BigInteger("" + inputText[i]);
            outputText[i] = temp.modPow(user.getKey(), user.getN()).intValue();
//            System.out.printf("# %d - code: %d - pow(key: %d, N: %d)=%d\n", i, inputText[i], user.getKey(), user.getN(), outputText[i]);
        }
        return outputText;
    }

    private static int[] getOpenText(String fileName) {
        BufferedReader file = null;
        int[] array = null;
        try {
            ArrayList<Integer> positions = new ArrayList<Integer>();
            file = new BufferedReader(new FileReader(fileName));
            int c;
            while((c = file.read())!=-1) {
                positions.add(new Integer(c));
            }
            file.close();
            array = new int[positions.size()];
            int i = 0;
            for(Integer element: positions) {
                array[i] = alphabet.indexOf((char)element.intValue());
                i++;
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            return array;
        }
    }

    private static void intArray2String(int[] array, String message) {
        System.out.print(message + ": ");
        for(int i: array) {
            System.out.print(alphabet.charAt(i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Alphabet: " + alphabet);
        RSAGenerator obj = new RSAGenerator();
        BigInteger openKey = obj.Ka;
        BigInteger privateKey = obj.Kb;
        BigInteger N = obj.n;
        RSAUser userA = new RSAUser(openKey, N);
        RSAUser userB = new RSAUser(privateKey, N);

//        System.out.println("Openkey: " + openKey);
//        System.out.println("PrivateKey: " + privateKey);
//        System.out.println("N value: " + N);
        
        int[] openText = getOpenText(FILE_INPUT_TXT);
        intArray2String(openText, "Open text");
        System.out.println("Open codes: " + Arrays.toString(openText));
        System.out.println();

        int[] closeText = codeDecode(openText, userA);
        System.out.println("Coded codes: " + Arrays.toString(closeText));
        System.out.println();

        int[] decodedText = codeDecode(closeText, userB);
        System.out.println("Decrypted codes: " + Arrays.toString(decodedText));
        intArray2String(decodedText, "Decoded text");
    }
}
