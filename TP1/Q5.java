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
        MyIO.setCharset("UTF-8");
        String nova = "";
        Boolean first = false;
        for (int i = palavra.length(); i > 0; i--) {
            // para quando acha um parenteses de abertura
            if (palavra.charAt(i) == '(') {
                String troca = "";
                troca += palavra.charAt(i+1);// cria uma string com valor do primeiro digito da expressao
                int p = i;
                while (palavra.charAt(i) != ')' || first == false) {// enquanto for diferente do parenteses que fecha ele continua copiando
                    first = true;                                  
                    nova += palavra.charAt(i);
                    p++;
                }
                int t = nova.length();
                switch (palavra.charAt(i - 1)) {
                    case 't':
                        if (troca.charAt(1) == '0') {//caso a letra antes seja t troca o valor recebido para o inverso
                            nova = "1";
                        } else {
                            nova = "0";
                        }
                        break;
                    case 'd'://caso a letra antes seja d troca fazendo a operação and
                        if (troca.charAt(0) == '0' || nova.charAt(t - 1) == '0') {
                            nova = "1";
                        } else {
                            nova = "0";
                        }
                        break;
                    case 'r'://caso a letra antes seja r troca fazendo a operação or
                        if (troca.charAt(0) == '1' || nova.charAt(t - 1) == '1') {
                            nova = "1";
                        } else {
                            nova = "0";
                        }
                        break;
                    default:

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
