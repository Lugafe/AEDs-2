import java.util.*;


public class Q4 {

    public static void main(String[] args) {
        MyIO.setCharset("UTF-8");
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
        
        char c = randomizer();
        char d = randomizer();      
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

    private static char randomizer() {
        Random r = new Random();
        r.setSeed(4);
        return ((char) ('a' + (Math.abs(r.nextInt()%26))));
    }
    
    
}
