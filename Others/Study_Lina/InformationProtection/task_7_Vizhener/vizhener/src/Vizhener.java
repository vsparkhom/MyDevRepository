import java.io.*;

public class Vizhener {

    public static final String FILE_IN = "res/in.txt";
    public static final String FILE_ENCRYPTED = "res/encrypted.txt";
    public static final String FILE_DECRYPTED = "res/decrypted.txt";

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789=";

    public CryptoResult encrypt(String message, String key) {
        StringBuffer code = new StringBuffer();
        CryptoResult cr = new CryptoResult(message.length(), CryptoResult.ENCRYPT);
        for (int i = 0; i < message.length(); i++) {

            char miChar = message.charAt(i);
            int miIndex = ALPHABET.indexOf(miChar);
            cr.setOpenTextCharacter(i, miChar);
            cr.setOpenTextIndex(i, miIndex);

            char kiChar = key.charAt(i % key.length());
            int kiIndex = ALPHABET.indexOf(kiChar);
            cr.setKeyTextCharacter(i, kiChar);
            cr.setKeyIndex(i, kiIndex);

            int cIndex = (miIndex + kiIndex) % ALPHABET.length();
            char c = ALPHABET.charAt(cIndex);
            cr.setEncryptedTextCharacter(i, c);
            cr.setEncryptedTextIndex(i, cIndex);

//            System.out.printf("\ni:%d M=%c[%d] K=%c[%d] => (%d) mod %d = %c(%d)\n", i, miChar, miIndex, kiChar, kiIndex, (miIndex+ kiIndex), ALPHABET.length(), c, cIndex);
            code.append(c);
        }

        cr.setResult(code.toString());

        return cr;
    }

    public CryptoResult decrypt(String code, String key) {
        StringBuffer messageBuffer = new StringBuffer();
        CryptoResult cr = new CryptoResult(code.length(), CryptoResult.DECRYPT);
        for (int i = 0; i < code.length(); i++) {
            int mIndex;
            int alphabetSize = ALPHABET.length();

            char ciChar = code.charAt(i);
            int ciIndex = ALPHABET.indexOf(ciChar);
            cr.setEncryptedTextCharacter(i, ciChar);
            cr.setEncryptedTextIndex(i, ciIndex);

            char kiChar = key.charAt(i % key.length());
            int kiIndex = ALPHABET.indexOf(kiChar);
            cr.setKeyTextCharacter(i, kiChar);
            cr.setKeyIndex(i, kiIndex);

            if (ciIndex - ALPHABET.indexOf(key.charAt(i % key.length())) < 0) {
                mIndex = alphabetSize - Math.abs((ciIndex - kiIndex % alphabetSize));
            } else {
                mIndex = (ciIndex - kiIndex) % alphabetSize;
            }
            char mChar = ALPHABET.charAt(mIndex);
            cr.setOpenTextCharacter(i, mChar);
            cr.setOpenTextIndex(i, mIndex);
//            System.out.printf("\ni:%d C=%c[%d] K=%c[%d] => (%d) mod %d = %c(%d)\n", i, ciChar, ciIndex, kiChar, kiIndex, (ciIndex - kiIndex), ALPHABET.length(), m, mIndex);
            messageBuffer.append(mChar);
        }

        cr.setResult(messageBuffer.toString());

        return cr;
    }

    public String readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String buffer = br.readLine();
            br.close();
            return buffer;
        } catch (IOException ex) {
            System.out.println(ex.toString());
            return "";
        }
    }

    public void writeFile(String text, String fileName) {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
            pw.print(text);
            pw.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void main(String[] args) {
        Vizhener v = new Vizhener();

        String encryptionKey = "mysecretkey";

        v.outputAlphabet();

//        System.out.println("Initial message has been read from file " + FILE_IN);
//        String message = v.readFile(FILE_IN);
        String message = "alea=jacta=est";

        System.out.println("message: " + message);
        System.out.println("encryptionKey: " + encryptionKey);

        CryptoResult encryptedResult = v.encrypt(message, encryptionKey);
        String encryptedText = encryptedResult.getResult();
        System.out.println("\nencryptedText: " + encryptedText);
        v.writeFile(encryptedText, FILE_ENCRYPTED);
        System.out.println("Result has been saved to file " + FILE_ENCRYPTED);
        System.out.println(encryptedResult);

        CryptoResult decryptedResult = v.decrypt(encryptedText, encryptionKey);
        String decryptedText = decryptedResult.getResult();
        System.out.println("decryptedText: " + decryptedText);
        v.writeFile(decryptedText, FILE_DECRYPTED);
        System.out.println("Result has been saved to file " + FILE_DECRYPTED);
        System.out.println(decryptedResult);
    }

    private void outputAlphabet() {

        System.out.println("Alphabet: ");
        System.out.print("------------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.print("|");
        for (int i = 0; i < ALPHABET.length(); i++) {
            System.out.printf("%3c |", ALPHABET.charAt(i));
        }
        System.out.println();
        System.out.print("|");
        for (int i = 0; i < ALPHABET.length(); i++) {
            System.out.printf("%3d |", i);
        }
        System.out.print("\n------------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------------------\n");
    }

}
