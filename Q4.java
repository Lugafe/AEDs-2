import java.util.*;


public class Q4 {

    public static void main(String[] args) {
        
        String palavra = MyIO.readLine();
        
        do {
            if (!palavra.equalsIgnoreCase("FIM")) {
                 palavra = sorteio(palavra);
                 System.out.println(palavra);
            }
            palavra = MyIO.readLine();            
        } while (!palavra.equalsIgnoreCase("FIM"));
    }

    private static String sorteio(String palavra) {
        Random r = new Random();
        r.setSeed(4);
        char c = ((char) ('a' + (Math.abs(r.nextInt()%26))));;
        char d = ((char) ('a' + (Math.abs(r.nextInt()%26))));;
        System.out.println(c);
        System.out.println(d);
        String nova = "";
        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) == c) {
                nova += d;
            }
            else{
                nova += palavra.charAt(i);
            }           
        }
        return nova;
    }

    
}
