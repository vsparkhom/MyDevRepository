import java.io.*;

public class Vizhener {
    private String alfavit;

    public Vizhener() {
        alfavit = "abcdefghijklmnopqrstuvwxyz0123456789=";
    }

    public char[][] makeTable() {
        char[][] table = new char[alfavit.length()][alfavit.length()];
        for(int i=0; i<alfavit.length(); i++)
            for(int j=0; j<alfavit.length(); j++) {
                int index = (j+i)%alfavit.length();
                table[i][j] = alfavit.charAt(index);
        }
        return table;
    }

    /*public String codingOne(String message, String key) {
        String code = "";
        for(int i=0; i<message.length(); i++) {
            char c = alfavit.charAt((alfavit.indexOf(message.charAt(i)) + alfavit.indexOf(key.charAt(i%key.length())))%alfavit.length());
            //System.out.println(message.charAt(i)+" + "+key.charAt(i%key.length())+": "+c);
            code += c;
        }
        return code;
    }

    public String decodingOne(String code, String key) {
        String message = "";
        for(int i=0; i<code.length(); i++) {
            char c = ' ';
            if (alfavit.indexOf(code.charAt(i)) - alfavit.indexOf(key.charAt(i%key.length())) < 0) {
                System.out.println(code.charAt(i) +" - "+ key.charAt(i%key.length())+"  "+(alfavit.indexOf(code.charAt(i)) - alfavit.indexOf(key.charAt(i%key.length())))+"  length:"+alfavit.length());
                c = alfavit.charAt(alfavit.length() - Math.abs((alfavit.indexOf(code.charAt(i)) - alfavit.indexOf(key.charAt(i%key.length())))%alfavit.length()));
            } else
                c = alfavit.charAt((alfavit.indexOf(code.charAt(i)) - alfavit.indexOf(key.charAt(i % key.length()))) % alfavit.length());
            message += c;
        }
        return message;
    }*/

    public String coding(String message, String key) {
        String code = "";
        char[][] table = makeTable();
        for(int i=0,k1=0,k2=0; i<message.length(); i++) {
            k1 = k2 = 0;
            while(k1<alfavit.length()) {
                if (message.charAt(i) == table[0][k1])
                    break;
                k1++;
            }
            while(k2<alfavit.length()) {
                if (key.charAt(i%key.length()) == table[k2][0])
                    break;
                k2++;
            }
            //System.out.println(table[k1][k2]);
            code += table[k1][k2];
        }
        return code;
    }

    public String decoding(String code, String key) {
        String message = "";
        char[][] table = makeTable();
        for(int i=0,k1=0,k2=0; i<code.length(); i++) {
            k1 = k2 = 0;
            while(k1<alfavit.length()) {
                if (key.charAt(i%key.length()) == table[0][k1])
                    break;
                k1++;
            }
            while(k2<alfavit.length()) {
                if (code.charAt(i) == table[k2][k1])
                    break;
                k2++;
            }
            message += table[k2][0];
        }
        return message;
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

    public void writeFile(String text, String fileName) {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
            pw.print(text);
            pw.close();
        } catch(IOException ex){
            System.out.println(ex.toString());
        }
    }

    public static void main(String[] args) {
        Vizhener v = new Vizhener();
        char[][] table = v.makeTable();
        
        for(int i=0; i<v.alfavit.length(); i++){
            System.out.print("\n"+i+") ");
            for(int j=0; j<v.alfavit.length(); j++)
                System.out.print(table[i][j]);
        }
        
        System.out.println("\nRead from file in.dat");
        String message = v.readFile("in.dat");
        System.out.println("\nmessage :"+message);
        System.out.println("Wrote to file code.dat" );
        v.writeFile(v.coding(message, "independetkey"),"code.dat");
        System.out.println("\ncode :"+v.coding(message, "independetkey"));
        
        System.out.println("\nRead from file in.dat");
        message = v.readFile("code.dat");
        System.out.println("Wrote to file message.dat" );
        v.writeFile(v.decoding(message, "independetkey"),"message.dat");

    }

}
