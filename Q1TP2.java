import java.io.IOException;
import java.io.File;
import java.util.*;


public class Q1TP2 {
   public static void main(String[] args) throws Exception {
      // File file = new File("characters.csv"); 
      Scanner sc = new Scanner(System.in);
      Lista personagens = new Lista();
      MyIO.setCharset("UTF-8");
      String ids[] = new String[500];
      String s = "";int i = 0;
      // recebe os valores dos ids
      personagens = arquivo();
      s = sc.nextLine();
      do {    
         if (!s.equalsIgnoreCase("FIM")) {
            ids[i] = s;
            i++;            
            s = sc.nextLine();
         }          
      } while (!s.equalsIgnoreCase("FIM"));
      
      for (int j = 0; j < ids.length-1; j++) {
         personagens.pesquisarId(ids[j]);
      }
   }

   public static Lista arquivo() throws Exception {
      File file = new File("characters.csv");
      Lista personagens = new Lista();
      Personagem p = new Personagem();
      String line;
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
      return personagens;
   }

}

class Personagem {
   private String id;
   private String name;
   private String alternate_names[];
   private String house;
   private String ancestry;
   private String species;
   private String patronus;
   private boolean hogwartsStaff;
   private boolean hogwartsStudent;
   private String actorName;
   private boolean alive;
   private String alternate_actors[];
   private String dateOfBirth;
   private int yearOfBirth;
   private String eyeColour;
   private String gender;
   private String hairColour;
   private boolean wizard;

   // id,name,alternate_names,house,ancestry,species,patronus,
   // hogwartsStaff,hogwartsStudent
   // ,actorName,alive,alternate_actors,dateOfBirth,yearOfBirth,eyeColour,gender,hairColour,wizard

   public void ler(String line) {
      int c = 0;
      int c2 = 0;
      int c3 = 0;
      boolean ok = true;
      boolean ok2 = true;
      String atores = "";
      String atores2 = "";
      if (line.equalsIgnoreCase("FIM")) {
         System.out.println("oh hell nah");
      }
      // separar a linha
      String separa[] = line.split(",");
      for (int i = 0; i < separa.length - 1; i++) {
         if (separa[i].length() > 0 && separa[i].charAt(1) == '[') {
            while (ok) {
               for (int j = 0; j < separa[i].length(); j++) {
                  atores += separa[i].charAt(j);
                  if (separa[i].charAt(j) == ']') {
                     ok = false;
                     break;
                  }
               }
               c++;
               i++;
            }
            // indica que passou pelo primeiro e agora o proximo pode definir os atores
            ok2 = true;
            c2 = i;
         }
      }
      if (ok2) {
         ok = true;
         for (int i = c2; i < separa.length - 1; i++) {
            if (separa[i].length() > 0 && separa[i].charAt(1) == '[') {
               while (ok) {
                  for (int j = 0; j < separa[i].length(); j++) {
                     atores2 += separa[i].charAt(j);
                     if (separa[i].charAt(j) == ']') {
                        ok = false;
                        break;
                     }
                  }
                  c3++;
                  i++;
               }
            }
         }
      }

      if (c != 0) {
         c--;
      }
      if (c3 != 0) {
         c--;
      }
      this.id = separa[0];
      this.name = separa[1];
      // System.out.println(atores);
      // System.out.println(atores2);
      this.house = separa[3 + c];
      this.ancestry = separa[4 + c];
      this.species = separa[5 + c];
      this.patronus = separa[6 + c];

      if (separa[7 + c].equalsIgnoreCase("True")) {
         this.hogwartsStaff = true;
      } else {
         this.hogwartsStaff = false;
      }
      if (separa[8 + c].equalsIgnoreCase("True")) {
         this.hogwartsStudent = true;
      } else {
         this.hogwartsStudent = false;
      }
      if (separa[10 + c].equalsIgnoreCase("True")) {
         this.alive = true;
      } else {
         this.alive = false;
      }
      if (separa[17 + c + c3].equalsIgnoreCase("True")) {
         this.wizard = true;
      } else {
         this.wizard = false;
      }

      this.actorName = separa[9 + c];
      this.alternate_names = atores.split("'");
      this.alternate_actors = atores2.split(" ");
      this.dateOfBirth = separa[12 + c + c3];
      if (isNumber(separa[13 + c + c3])) {
         this.yearOfBirth = Integer.parseInt(separa[13 + c + c3]);
      } else {
         this.yearOfBirth = 0000;
      }
      this.eyeColour = separa[14 + c + c3];
      this.gender = separa[15 + c + c3];
      this.hairColour = separa[16 + c + c3];

      // System.out.println("ok");

   }

   // [9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8 ## Harry Potter ## {The Boy Who Lived,
   // The Chosen One, Undesirable No. 1, Potty} ## Gryffindor ## half-blood ##
   // human ## stag ## false ## false ## Daniel Radcliffe ## false ## 31-07-1980 ##
   // 1980 ## green ## male ## black ## false]
   public void imprimir() {
      String nomes = nomeacao(this.alternate_names);
      String atores = nomeacao(this.alternate_actors);
      System.out.println("[" + this.id + " ## " + this.actorName + " ## {" + nomes + "} ## " + this.house + " ## "
            + this.ancestry + " ## " + this.species + " ## " + this.patronus + " ## false ## false ## " + this.actorName
            + " ## false ## " + this.dateOfBirth + " ## " + this.yearOfBirth + " ## " + this.eyeColour + " ## "
            + this.gender + " ## " + this.hairColour + " ## false]");
   }

   private String nomeacao(String[] str) {
      String nova = "";
      for (int i = 0; i < str.length; i++) {
         if (i % 2 != 0) {
            if (i == 1) {
               nova += str[i] + ",";
            } else if (i == str.length - 2) {
               nova += " " + str[i];
            } else {
               nova += " " + str[i] + ",";
            }
         }
      }

      return nova;
   }

   private boolean isNumber(String string) {
      for (int i = 0; i < string.length(); i++) {
         if (string.charAt(i) >= '0' && string.charAt(i) <= '9') {
            return true;
         }
      }
      return false;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String[] getAlternate_names() {
      return alternate_names;
   }

   public void setAlternate_names(String alternate_names[]) {
      this.alternate_names = alternate_names;
   }

   public String getHouse() {
      return house;
   }

   public void setHouse(String house) {
      this.house = house;
   }

   public String getAncestry() {
      return ancestry;
   }

   public void setAncestry(String ancestry) {
      this.ancestry = ancestry;
   }

   public String getSpecies() {
      return species;
   }

   public void setSpecies(String species) {
      this.species = species;
   }

   public String getPatronus() {
      return patronus;
   }

   public void setPatronus(String patronus) {
      this.patronus = patronus;
   }

   public boolean isHogwartsStaff() {
      return hogwartsStaff;
   }

   public void setHogwartsStaff(boolean hogwartsStaff) {
      this.hogwartsStaff = hogwartsStaff;
   }

   public boolean isHogwartsStudent() {
      return hogwartsStudent;
   }

   public void setHogwartsStudent(boolean hogwartsStudent) {
      this.hogwartsStudent = hogwartsStudent;
   }

   public String getActorName() {
      return actorName;
   }

   public void setActorName(String actorName) {
      this.actorName = actorName;
   }

   public boolean isAlive() {
      return alive;
   }

   public void setAlive(boolean alive) {
      this.alive = alive;
   }

   public String[] getAlternate_actors() {
      return alternate_actors;
   }

   public void setAlternate_actors(String alternate_actors[]) {
      this.alternate_actors = alternate_actors;
   }

   public String getDateOfBirth() {
      return dateOfBirth;
   }

   public void setDateOfBirth(String dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
   }

   public int getYearOfBirth() {
      return yearOfBirth;
   }

   public void setYearOfBirth(int yearOfBirth) {
      this.yearOfBirth = yearOfBirth;
   }

   public String getEyeColour() {
      return eyeColour;
   }

   public void setEyeColour(String eyeColour) {
      this.eyeColour = eyeColour;
   }

   public String getGender() {
      return gender;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public String getHairColour() {
      return hairColour;
   }

   public void setHairColour(String hairColour) {
      this.hairColour = hairColour;
   }

   public boolean isWizard() {
      return wizard;
   }

   public void setWizard(boolean wizard) {
      this.wizard = wizard;
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

   public void pesquisarId(String x) {
      boolean retorno = false;
      int c = 0;
      if (x != null) {
         for (int i = 0; i < n && retorno == false; i++) {
            if (array[i].getId() != null) {
               retorno = ((array[i].getId()).equals(x));
               c = i;
            }
         }
         if (retorno) {
            array[c].imprimir();
         }
      }
   }
}

/*
 * while (i < separa[2].length()-1 && separa[2].charAt(i) != ']') {
 * if (separa[2].charAt(i) != '"' || separa[2].charAt(i) != '[' ||
 * separa[2].charAt(i) != '[') {
 * atores += separa[2].charAt(i);
 * i++;
 * }
 * }
 */