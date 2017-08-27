
public class CryptoResult {

    public static final int ENCRYPT = 0;
    public static final int DECRYPT = 1;

    private char[] openText;
    private int[] openTextIndexes;
    private char[] keyText;
    private int[] keyIndexes;
    private char[] encryptedText;
    private int[] encryptedTextIndexes;
    private int cryptoType;
    private String result;


    public CryptoResult(int size, int cryptoType) {
        this.cryptoType = cryptoType;
        openText = new char[size];
        openTextIndexes = new int[size];
        keyText = new char[size];
        keyIndexes = new int[size];
        encryptedText = new char[size];
        encryptedTextIndexes = new int[size];
    }

    public void setOpenTextCharacter(int position, char c) {
        this.openText[position] = c;
    }

    public void setOpenTextIndex(int position, int index) {
        this.openTextIndexes[position] = index;
    }

    public void setKeyTextCharacter(int position, char c) {
        this.keyText[position] = c;
    }

    public void setKeyIndex(int position, int index) {
        this.keyIndexes[position] = index;
    }

    public void setEncryptedTextCharacter(int position, char c) {
        this.encryptedText[position] = c;
    }

    public void setEncryptedTextIndex(int position, int index) {
        this.encryptedTextIndexes[position] = index;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n-------------------------------------------------------------------------------------------------\n");

        if (cryptoType == ENCRYPT) {
            appendCharacters(sb, "Initial msg               |", openText);
            appendIntegerNumbers(sb, "\nSymbol # of init. msg     |", openTextIndexes);
        } else {
            appendCharacters(sb, "Encrypted msg             |", encryptedText);
            appendIntegerNumbers(sb, "\nSymbol # of encrypted msg |", encryptedTextIndexes);
        }

        appendCharacters(sb, "\nKey                       |", keyText);
        appendIntegerNumbers(sb, "\nSymbol # of  key          |", keyIndexes);

        if (cryptoType == ENCRYPT) {
            appendIntegerNumbers(sb, "\nSymbol # of encrypted msg |", encryptedTextIndexes);
            appendCharacters(sb, "\nEncrypted msg             |", encryptedText);
        } else {
            appendIntegerNumbers(sb, "\nSymbol # of init. msg     |", openTextIndexes);
            appendCharacters(sb, "\nInitial msg               |", openText);
        }

        sb.append("\n-------------------------------------------------------------------------------------------------\n");
        return sb.toString();
    }

    private void appendCharacters(StringBuilder sb, String title, char[] chars) {
        sb.append(title);
        for (int i = 0; i < chars.length; i++) {
            sb.append(String.format("%3c |", chars[i]));
        }
    }

    private void appendIntegerNumbers(StringBuilder sb, String title, int[] numbers) {
        sb.append(title);
        for (int i = 0; i < numbers.length; i++) {
            sb.append(String.format("%3d |", numbers[i]));
        }
    }
}
