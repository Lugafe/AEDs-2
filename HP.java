import java.io.IOException;
import java.io.File;
import java.util.*;

public class HP {
   public static void main(String[] args) throws Exception {
      File file = new File("characters.csv");
      String ids[] = new String[100];
      Personagem p = new Personagem();
      Lista personagens = new Lista();
      String all[] = new String[4000];
      String s = "";
      int j = 0;
      s = MyIO.readLine();
      // recebe os valores dos ids
      do {
         if (!s.equalsIgnoreCase("FIM")) {
            ids[j] = s;
            s = MyIO.readLine();
            j++;
         }
      } while (!s.equalsIgnoreCase("FIM"));
      try {
         Scanner sc = new Scanner(file);
         while (sc.hasNextLine()) {
            p.ler(sc.nextLine());
            personagens.inserirFim(p);
            p = new Personagem();
         }
         sc.close();
      } catch (IOException e) {
         System.err.printf("%s: %s%n", e, e.getMessage());
      }

   }
}

class Personagem {
   String id;
   String name;
   String alternate_names;
   String house;
   String ancestry;
   String species;
   String patronus;
   boolean hogwartsStaff;
   boolean hogwartsStudent;
   String actorName;
   boolean alive;
   String alternate_actors;
   String dateOfBirth;
   int yearOfBirth;
   String eyeColour;
   String gender;
   String hairColour;
   boolean wizard;

   // id,name,alternate_names,house,ancestry,species,patronus,hogwartsStaff,
   // hogwartsStudent,actorName,alive,alternate_actors,dateOfBirth,yearOfBirth,
   // eyeColour,gender,hairColour,wizard
   public void ler(String line) {
      //separar a linha
      String separa[] = line.split(",");
      //fazer
      
      
      
   }
}

class Lista {
   private Personagem[] array;
   private int n;

   /**
    * Construtor da classe.
    */
   public Lista() {
      this(500);
   }

   /**
    * Construtor da classe.
    * 
    * @param tamanho Tamanho da lista.
    */
   public Lista(int tamanho) {
      array = new Personagem[tamanho];
      n = 0;
   }

   /**
    * Insere um elemento na primeira posicao da lista e move os demais
    * elementos para o fim da lista.
    * 
    * @param x int elemento a ser inserido.
    * @throws Exception Se a lista estiver cheia.
    */
   public void inserirInicio(Personagem x) throws Exception {

      // validar insercao
      if (n >= array.length) {
         throw new Exception("Erro ao inserir!");
      }

      // levar elementos para o fim do array
      for (int i = n; i > 0; i--) {
         array[i] = array[i - 1];
      }

      array[0] = x;
      n++;
   }

   /**
    * Insere um elemento na ultima posicao da lista.
    * 
    * @param x int elemento a ser inserido.
    * @throws Exception Se a lista estiver cheia.
    */
   public void inserirFim(Personagem x) throws Exception {

      // validar insercao
      if (n >= array.length) {
         throw new Exception("Erro ao inserir!");
      }

      array[n] = x;
      n++;
   }

   /**
    * Insere um elemento em uma posicao especifica e move os demais
    * elementos para o fim da lista.
    * 
    * @param x   int elemento a ser inserido.
    * @param pos Posicao de insercao.
    * @throws Exception Se a lista estiver cheia ou a posicao invalida.
    */
   public void inserir(Personagem x, int pos) throws Exception {

      // validar insercao
      if (n >= array.length || pos < 0 || pos > n) {
         throw new Exception("Erro ao inserir!");
      }

      // levar elementos para o fim do array
      for (int i = n; i > pos; i--) {
         array[i] = array[i - 1];
      }

      array[pos] = x;
      n++;
   }

   /**
    * Remove um elemento da primeira posicao da lista e movimenta
    * os demais elementos para o inicio da mesma.
    * 
    * @return resp int elemento a ser removido.
    * @throws Exception Se a lista estiver vazia.
    */
   public Personagem removerInicio() throws Exception {

      // validar remocao
      if (n == 0) {
         throw new Exception("Erro ao remover!");
      }

      Personagem resp = array[0];
      n--;

      for (int i = 0; i < n; i++) {
         array[i] = array[i + 1];
      }

      return resp;
   }

   /**
    * Remove um elemento da ultima posicao da lista.
    * 
    * @return resp int elemento a ser removido.
    * @throws Exception Se a lista estiver vazia.
    */
   public Personagem removerFim() throws Exception {

      // validar remocao
      if (n == 0) {
         throw new Exception("Erro ao remover!");
      }

      return array[--n];
   }

   /**
    * Remove um elemento de uma posicao especifica da lista e
    * movimenta os demais elementos para o inicio da mesma.
    * 
    * @param pos Posicao de remocao.
    * @return resp int elemento a ser removido.
    * @throws Exception Se a lista estiver vazia ou a posicao for invalida.
    */
   public Personagem remover(int pos) throws Exception {

      // validar remocao
      if (n == 0 || pos < 0 || pos >= n) {
         throw new Exception("Erro ao remover!");
      }

      Personagem resp = array[pos];
      n--;

      for (int i = pos; i < n; i++) {
         array[i] = array[i + 1];
      }

      return resp;
   }

   /**
    * Mostra os elementos da lista separados por espacos.
    */
   public void mostrar() {
      System.out.print("[ ");
      for (int i = 0; i < n; i++) {
         System.out.print(array[i] + " ");
      }
      System.out.println("]");
   }

   /**
    * Procura um elemento e retorna se ele existe.
    * 
    * @param x int elemento a ser pesquisado.
    * @return <code>true</code> se o array existir,
    *         <code>false</code> em caso contrario.
    */
   public boolean pesquisar(Personagem x) {
      boolean retorno = false;
      for (int i = 0; i < n && retorno == false; i++) {
         retorno = (array[i] == x);
      }
      return retorno;
   }
}
