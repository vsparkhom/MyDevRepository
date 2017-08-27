package com.infoprotect.task6;

import java.io.*;

public class CaesarCipher {

    public static final String ALPHABET_A1 = "0123456789АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ#";
    public static final String ALPHABET_A2 = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ ";

    private final double[] freqA2 = new double[]{
            0.065, 0.014, 0.038, 0.013, 0.025, 0.072, 0.007, 0.016, 0.062, 0.010, 0.028, 0.035, 0.026, 0.053, 0.090,
            0.023, 0.040, 0.045, 0.053, 0.021, 0.002, 0.009, 0.004, 0.012, 0.006, 0.003, 0.016, 0.014, 0.019, 0.003,
            0.006, 0.018, 0.175
    };

    public String encrypt(String text, int key, final String alphabet) {
        return encryptDecrypt(text, key, alphabet, new CryptographicFunction() {
            @Override
            public int execute(int currentPosition, int key, int alphabetSize) {
                return (currentPosition + key) % alphabet.length();
            }
        });
    }

    public String decrypt(String text, int key, final String alphabet) {
        return encryptDecrypt(text, key, alphabet, new CryptographicFunction() {
            @Override
            public int execute(int currentPosition, int key, int alphabetSize) {
                return (currentPosition - key + alphabet.length()) % alphabet.length();
            }
        });
    }

    public String encryptDecrypt(String text, int key, final String alphabet, CryptographicFunction function) {
        StringBuilder decodedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int currentPosition = alphabet.indexOf(text.charAt(i));
            if (currentPosition == -1) {
                throw new IllegalArgumentException("Char '" + text.charAt(i) + "' at position " + i
                        + " is not present in current alphabet [" + alphabet + "]!");
            }
            int newPosition = function.execute(currentPosition, key, alphabet.length());
            decodedText.append(alphabet.charAt(newPosition));
        }
        return decodedText.toString();
    }

    public int cryptoAnalysis(String encryptedText) {

        double[] freq = new double[ALPHABET_A2.length()];
        int count = 0;

        for (int i = 0; i < encryptedText.length(); i++) {
            int currentSymbolPosition = ALPHABET_A2.indexOf(encryptedText.charAt(i));
            if (currentSymbolPosition == -1) {
                throw new IllegalArgumentException("Char '" + encryptedText.charAt(i)
                        + "' at position " + i + " is not present in current alphabet [" + ALPHABET_A2 + "]!");
            }
            freq[currentSymbolPosition]++;
            count++;
        }

//        System.out.println("freq: " + Arrays.toString(freq));
//        System.out.println("count: " + count);

        for (int i = 0; i < freq.length; i++) {
            freq[i] /= count;
        }

        double[] sumDifferences = new double[ALPHABET_A2.length()];

        for (int i = 0; i < ALPHABET_A2.length(); i++) {
            sumDifferences[i] = 0;
            for (int k = 0; k < ALPHABET_A2.length(); k++) {
                int index = (k - i + ALPHABET_A2.length()) % ALPHABET_A2.length();
                sumDifferences[i] += Math.abs(freqA2[k] - freq[index]);
            }
        }

        int foundKey = 0;
        double minD = sumDifferences[0];
        for (int i = 0; i < ALPHABET_A2.length(); i++)
            if (sumDifferences[i] < minD) {
                minD = sumDifferences[i];
                foundKey = ALPHABET_A2.length() - i;
            }
        return foundKey;
    }

    public String readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String buffer = br.readLine();
            br.close();
            return buffer;
        } catch(IOException ex) {
            System.out.println(ex.toString());
            return "";
        }
    }

    public void writeFile(String fileName, String message){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write(message);
            bw.close();
        } catch(IOException ex){
            System.out.println(ex.toString());
        }
    }

}
