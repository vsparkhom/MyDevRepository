package com.infoprotect.task6;

public class Main {

    public static final String FILE_INPUT_TEXT = "res/input.txt";
    public static final String FILE_ENCRYPTED_TEXT = "res/encrypted.txt";
    public static final String FILE_DECRYPTED_TEXt = "res/decrypted.txt";

    public static final String FILE_CRYPTOANALYSIS_INPUT_TEXT = "res/cryptoanalysis_input_text.txt";
    public static final String FILE_CRYPTOANALYSIS_ENCRYPTED_TEXT = "res/cryptoanalysis_encrypted_text.txt";
    public static final String FILE_CRYPTOANALYSIS_DECRYPTED_TEXT = "res/cryptoanalysis_decrypted_text.txt";

    public static void main(String[] args) {

        CaesarCipher caesarCipher = new CaesarCipher();
        System.out.println("ALPHABET A1 (size=" + CaesarCipher.ALPHABET_A1.length() + "): " + CaesarCipher.ALPHABET_A1);
        System.out.println("ALPHABET A2 (size=" + CaesarCipher.ALPHABET_A2.length() + "): " + CaesarCipher.ALPHABET_A2);

        int key = 13;
        System.out.println("Key=" + key);
        String testPhrase = "ТЕСТ123#ШИФРА#ЦЕЗАРЯ#ТАКЖЕ#ИЗВЕСТНЫЙ#КАК#ШИФР#СДВИГА4567";

//        String originalText = caesarCipher.readFile(FILE_INPUT_TEXT);
        String originalText = testPhrase;
        System.out.println("\n------------- ENCRYPTION AND DECRYPTION -------------\n");

        String encryptedText = caesarCipher.encrypt(originalText, key, CaesarCipher.ALPHABET_A1);
        System.out.println("originalText=" + originalText + " encryptedText=" + encryptedText);
        caesarCipher.writeFile(FILE_ENCRYPTED_TEXT, encryptedText);

        String decryptedText = caesarCipher.decrypt(encryptedText, key, CaesarCipher.ALPHABET_A1);
        System.out.println("encryptedText=" + encryptedText + " decryptedText=" + decryptedText);
        caesarCipher.writeFile(FILE_DECRYPTED_TEXt, encryptedText);

        System.out.println("\n------------- CRYPTOANALYSIS -------------\n");

        String originalTextForAnalysis = caesarCipher.readFile(FILE_CRYPTOANALYSIS_INPUT_TEXT);
        System.out.println("originalTextForAnalysis: " + originalTextForAnalysis);
        String encryptedTextForAnalysis = caesarCipher.encrypt(originalTextForAnalysis, 7, CaesarCipher.ALPHABET_A2);
        System.out.println("encryptedTextForAnalysis (with key=7): " + encryptedTextForAnalysis);
        caesarCipher.writeFile(FILE_CRYPTOANALYSIS_ENCRYPTED_TEXT, encryptedTextForAnalysis);

        int foundKey = caesarCipher.cryptoAnalysis(encryptedTextForAnalysis);
        System.out.println("foundKey=" + foundKey);

        String decryptedTextForAnalysis = caesarCipher.decrypt(encryptedTextForAnalysis, foundKey, CaesarCipher.ALPHABET_A2);
        System.out.println("decryptedTextForAnalysis: " + decryptedTextForAnalysis);
        caesarCipher.writeFile(FILE_CRYPTOANALYSIS_DECRYPTED_TEXT, decryptedTextForAnalysis);
    }
}
