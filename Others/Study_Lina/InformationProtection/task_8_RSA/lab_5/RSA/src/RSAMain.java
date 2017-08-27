
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class RSAMain {

    private static String alfavit = "abcdefghijklmnopqrstuvwxyz0123456789=";

    private static int[] codeDecode(int[] inputText, RSAUser user) {
        int[] outputText = new int[inputText.length];
        for (int i = 0; i < inputText.length; i++) {
            BigInteger temp = new BigInteger("" + inputText[i]);
            outputText[i] = temp.modPow(user.getKey(), user.getN()).intValue();
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
                array[i] = alfavit.indexOf((char)element.intValue());
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
            System.out.print(alfavit.charAt(i));
        }
    }

    public static void main(String[] args) {
        RSAGenerator obj = new RSAGenerator();
        BigInteger openKey = obj.Ka;
        BigInteger privateKey = obj.Kb;
        BigInteger N = obj.n;
        RSAUser userA = new RSAUser(openKey, N);
        RSAUser userB = new RSAUser(privateKey, N);
        System.out.println("Openkey: " + openKey);
        System.out.println("PrivateKey: " + privateKey);
        System.out.println("N value: " + N);
        
        int[] openText = getOpenText("file.txt");
        intArray2String(openText, "Input text");
        int[] closeText = codeDecode(openText, userA);
        
        System.out.print("\nCoded indexes: ");
        for (int el : closeText) {
            System.out.print(el + " ");
        }
        int[] decodedText = codeDecode(closeText, userB);
        System.out.print("\nDecoded indexes: ");
        for (int el : decodedText) {
            System.out.print(el + " ");
        }
        intArray2String(decodedText, "\nDecode text");
    }
}
