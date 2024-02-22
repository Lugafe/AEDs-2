import java.util.*;

public class Q3 {

    public static void main(String[] args) {
        
        String palavra = MyIO.readLine();
    
        do {
            if (!palavra.equalsIgnoreCase("FIM")) {
                 palavra = ciframento(palavra);
                 System.out.println(palavra);
            }
            palavra = MyIO.readLine();            
        } while (!palavra.equalsIgnoreCase("FIM"));
    }

    private static String ciframento(String palavra) {
        String nova = "";
        int j =  0; 
        for (int i = 0; i < palavra.length(); i++) {
            j = (int)palavra.charAt(i)+3;
            nova += (char)j;
        }
        
        return nova;
    }



}
