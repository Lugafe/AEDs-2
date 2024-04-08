import java.util.*;

public class Q6 {
    public static void main(String[] args) {
        MyIO.setCharset("UTF-8");
        String palavra = MyIO.readLine();

        do {
            if (!palavra.equalsIgnoreCase("FIM")) {                
                Is(palavra);                
            }
            palavra = MyIO.readLine();
        } while (!palavra.equalsIgnoreCase("FIM"));
    }

    private static void Is(String palavra) {
        boolean real = false ; boolean inteiro = false; boolean v = false; boolean c = false;
        int cv = 0;int cc = 0;int ci = 0;
        for (int i = 0; i < palavra.length(); i++) {
            if (isLetter(palavra.charAt(i))) {                
                if (vogal(palavra.charAt(i))) {
                    cv++;
                }
                else if (!vogal(palavra.charAt(i))) {
                    cc++;   
                }
            }
            else if(!isLetter(palavra.charAt(i))){
                if (idNumber(palavra.charAt(i))) {
                    ci++;
                }
                else if (palavra.charAt(i) == '.' || palavra.charAt(i) == ',' ) {
                    if (i != palavra.length()-1) {
                        if (idNumber(palavra.charAt(i+1))) {
                            real = true;
                        }
                    }                    
                }
                
            }
        }
        if (cv == palavra.length()) {
            v = true;
        }
        if (cc == palavra.length()) {
            c = true;
        }
        if (ci == palavra.length()) {
            inteiro = true;
        }
        imprimir(real, inteiro, c, v);
    }

    private static void imprimir(boolean real, boolean inteiro, boolean c, boolean v) {
        if (v) {
            System.out.print("SIM ");
        }
        else {
            System.out.print("NAO ");
        }

        if (c) {
            System.out.print("SIM ");
        }
        else{
            System.out.print("NAO ");
        }
        
        if (inteiro) {
            System.out.print("SIM ");
        }
        else {
            System.out.print("NAO ");
        }

        if (real) {
            System.out.println("SIM");
        }
        else{
            System.out.println("NAO");
        }  


    }

    private static boolean idNumber(char charAt) {
        if (charAt >= '0' && charAt <= '9') {
            return true;
        }
        return false;
    }

    private static boolean isLetter(char charAt) {
        if (charAt >= 'a' && charAt <= 'z' || charAt >= 'A' && charAt <= 'Z' ) {
            return true;
        }
        return false;
    }

    private static boolean vogal(char charAt) {
        if (charAt == 'a' || charAt == 'e' ||charAt == 'i' ||charAt == 'o' ||charAt == 'u' ||charAt == 'A' ||charAt == 'E' ||charAt == 'I' ||charAt == 'O' ||charAt == 'U' ) {
            return true;
        }
        return false;
    } 

    
    
}
