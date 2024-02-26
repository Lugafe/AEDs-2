import java.util.*;;


public class Q1 {
    public static void main(String[] args) {
        MyIO.setCharset("UTF-8");
        String palavra = MyIO.readLine();

        do {
            if (!palavra.equalsIgnoreCase("FIM")) {
                if (palindrom(palavra)) {
                    System.out.println("SIM");
                }
                else{
                    System.out.println("NAO");
                }   
            }
            palavra = MyIO.readLine();            
        } while (!palavra.equalsIgnoreCase("FIM"));

    }

    private static boolean palindrom(String palavra) {
        
        int fim = palavra.length()-1;
        int i = 0;
        boolean ok = true;
        while (i < fim) {
            if (palavra.charAt(i) != palavra.charAt(fim)) {
                ok = false;
            }
            fim--;
            i++;
        }
        
        return ok;        
    }    
}