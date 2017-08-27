import java.io.*;

class KriptoOne {

    private int key;
    private String engAlfavit = "abcdefghijklmnopqrstuvwxyz0123456789=";
    private String kriptoAlfavit = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
    private double[] FiC = {0.079,0.017,0.041,0.033,0.122,0.022,0.018,
                            0.038,0.075,0.001,0.008,0.037,0.027,0.068,
                            0.071,0.028,0.002,0.069,0.071,0.091,0.031,
                            0.011,0.013,0.003,0.020,0.002,0.222};

    public String codingDecoding(String message, String alfavit, int k) {
        key = k;
        if (message == null) {
            throw new NullPointerException();
        }
        String t = "";
        for (int i = 0; i < message.length(); i++) {
            t += alfavit.charAt((alfavit.indexOf(message.charAt(i)) + key) % alfavit.length());
        }
        return t;
    }

    public int getID(char c) {
        if (c == ' ') 
            return FiC.length - 1;
        else
            return c - 'A';
    }

    public int kriptoanaliz(String code) {

        double[] freq = new double[kriptoAlfavit.length()];
        int count = 0;

        for (int i = 0; i < code.length(); i++) {
            freq[getID(code.charAt(i))]++;
            count++;
        }
        for (int i = 0; i < freq.length; i++) {
            freq[i] /= count;
        }

        double[] sumRazn = new double[kriptoAlfavit.length()];
        double minD = Double.POSITIVE_INFINITY;
        
        for (int i = 0; i < kriptoAlfavit.length(); i++) {
            sumRazn[i] = 0;
            for (int k = 0; k < kriptoAlfavit.length(); k++) {
                int index = (k - i + kriptoAlfavit.length()) % kriptoAlfavit.length();
                sumRazn[i] += Math.abs(FiC[k] -	freq[index]);
            }
        }

        int findKey = 0;
        minD = sumRazn[0];
        for (int i = 0; i < kriptoAlfavit.length(); i++)
            if (sumRazn[i]<minD){
                minD = sumRazn[i];
                findKey = kriptoAlfavit.length() - i;
            }
        return findKey;
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

    public static void main(String[] args) {
        KriptoOne ko = new KriptoOne();
        String mess = "some=message=for=coding=1990";
        
        String codemess = ko.codingDecoding(mess, ko.engAlfavit, 7);
        System.out.println("coding (" + mess + "):  " + codemess);
        System.out.println("decoding (" + codemess + "):  " + ko.codingDecoding(codemess, new StringBuffer(ko.engAlfavit).reverse().toString(),7));

        mess = ko.readFile("kripto_in.dat");
        System.out.println("\nOpen message read from file: \n" + mess);
		
        codemess = ko.codingDecoding(mess, ko.kriptoAlfavit,2);
        ko.writeFile("kripto_out_1.dat",codemess);
        System.out.println("\nCoding:\n" + codemess + "\nCoded message wrote to file.");
		
        int findKey = ko.kriptoanaliz(codemess);
        mess = ko.codingDecoding(codemess, new StringBuffer(ko.kriptoAlfavit).reverse().toString(),findKey);
        ko.writeFile("kripto_out_2.dat",mess);
        System.out.println("\nKriptoanaliz: \nKey = " + findKey + "\n" + mess + "\nDecoded message wrote to file.");

    }
}
