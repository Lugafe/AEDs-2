import java.util.*;

public class Q5 {
    public static void main(String[] args) {

        String palavra = MyIO.readLine();

        do {
            if (!palavra.equalsIgnoreCase("FIM")) {
                palavra = algebra(palavra);
                System.out.println(palavra);
            }
            palavra = MyIO.readLine();
        } while (!palavra.equalsIgnoreCase("FIM"));
    }

    private static String algebra(String palavra) {

        int a = 0;
        int b = 0;
        int c = 0;
        palavra = noSpace(palavra);
        // receber quantas entradas vai ter
        int i = (int) palavra.charAt(0);
        // separar os valores de A B C
        switch (i) {
            case 2:
                a = (int) palavra.charAt(1);
                b = (int) palavra.charAt(2);
                palavra = subs(palavra, a, b);
                break;

            case 3:
                a = (int) palavra.charAt(1);
                b = (int) palavra.charAt(2);
                c = (int) palavra.charAt(2);
                palavra = subs(palavra, a, b, c);
                break;

            default:
                break;
        }
        // dar o resltado em uma expressão real
        palavra = expression(palavra);
        return palavra;
    }

    private static String expression(String palavra) {
        String nova = "";
        
        for (int i = palavra.length(); i > 0; i--) {
            if (palavra.charAt(i) == '(') {
                switch (palavra.charAt(i - 1)) {
                    case 't':
                        if (palavra.charAt(i + 1) == 1) {
                            nova += '0';
                        } else {
                            nova += '1';
                        }
                    break;
                    case 'd':
                        if (palavra.charAt(i + 1) == '0') {
                            nova += '0';
                        } else if (palavra.charAt(i + 3) == '0') {
                            nova += '0';
                        } else {
                            nova += '1';
                        }
                    break;
                    case 'r':
                        if (palavra.charAt(i + 1) == '1') {
                            nova += '1';
                        } else if (palavra.charAt(i + 3) == '1') {
                            nova += '1';
                        } else {
                            nova += '0';
                        }
                    break;
                    default:
                        nova += palavra.charAt(i);
                    break;
                }
            }
        }
        return nova;
    }

    private static String subs(String palavra, int a, int b, int c) {
        String nova = "";
        // substituir os valores na expressão
        for (int i = 0; i < palavra.length(); i++) {
            switch (palavra.charAt(i)) {
                case 'A':
                    if (a == 1) {
                        nova += '1';
                    } else {
                        nova += '0';
                    }
                    break;
                case 'B':
                    if (b == 1) {
                        nova += '1';
                    } else {
                        nova += '0';
                    }
                    break;
                case 'C':
                    if (c == 1) {
                        nova += '1';
                    } else {
                        nova += '0';
                    }
                    break;

                default:
                    nova += palavra.charAt(i);
                    break;
            }
        }
        return nova;
    }

    private static String subs(String palavra, int a, int b) {
        String nova = "";
        // substituir os valores na expressão
        for (int i = 0; i < palavra.length(); i++) {
            switch (palavra.charAt(i)) {
                case 'A':
                    if (a == 1) {
                        nova += '1';
                    } else {
                        nova += '0';
                    }
                    break;
                case 'B':
                    if (b == 1) {
                        nova += '1';
                    } else {
                        nova += '0';
                    }
                    break;

                default:
                    nova += palavra.charAt(i);
                    break;
            }
        }
        return nova;
    }

    private static String noSpace(String palavra) {
        String nova = "";
        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) != ' ') {
                nova += palavra.charAt(i);
            }
        }
        return nova;
    }
}
