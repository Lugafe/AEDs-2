package TP2;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Q9TP2 {
   public static void main(String[] args) throws Exception {
      Scanner sc = new Scanner(System.in);
      Lista personagens = new Lista();
      Personagem p = new Personagem();
      String ids[] = new String[500];
      Lista personagens2 = new Lista();
      String s = "";
      int i = 0;
      // recebe os valores dos ids
      personagens = arquivo();
      s = sc.nextLine();
      do {
         if (!s.equalsIgnoreCase("FIM")) {
            p = personagens.pesquisarP(s);
            personagens2.inserirFim(p);
            p = new Personagem();
            s = sc.nextLine();
         }
      } while (!s.equalsIgnoreCase("FIM"));
      personagens2.heapsort();
      personagens2.mostrar();
   }

   public static Lista arquivo() throws Exception {
      //File file = new File("characters.csv");
      File file = new File("/tmp/characters.csv");
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
   private String alternate_names;
   private String house;
   private String ancestry;
   private String species;
   private String patronus;
   private boolean hogwartsStaff;
   private boolean hogwartsStudent;
   private String actorName;
   private boolean alive;
   private String alternate_actors;
   private LocalDate dateOfBirth;
   private String yearOfBirth;
   private String eyeColour;
   private String gender;
   private String hairColour;
   private boolean wizard;

   // id,name,alternate_names,house,ancestry,species,patronus,
   // hogwartsStaff,hogwartsStudent
   // ,actorName,alive,alternate_actors,dateOfBirth,yearOfBirth,eyeColour,gender,hairColour,wizard

   public void ler(String line) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy");

      if (line.equalsIgnoreCase("FIM")) {
         System.out.println("oh hell nah");
      }
      // separar a linha
      String separa[] = line.split(";");
      String arruma = separa[2].replace("[", "{").replace("]", "}").replace("'", "");

      separa[2] = arruma;
      this.id = separa[0];
      this.name = separa[1];
      this.alternate_names = separa[2];
      this.house = separa[3];
      this.ancestry = separa[4];
      this.species = separa[5];
      this.patronus = separa[6];
      if (separa[7].equalsIgnoreCase("VERDADEIRO")) {
         this.hogwartsStaff = true;
      } else {
         this.hogwartsStaff = false;
      }
      if (separa[8].equalsIgnoreCase("VERDADEIRO")) {
         this.hogwartsStudent = true;
      } else {
         this.hogwartsStudent = false;
      }
      if (separa[10].equalsIgnoreCase("VERDADEIRO")) {
         this.alive = true;
      } else {
         this.alive = false;
      }
      if (separa[17].equalsIgnoreCase("VERDADEIRO")) {
         this.wizard = true;
      } else {
         this.wizard = false;
      }
      this.actorName = separa[9];
      this.alternate_actors = "";
      if (separa[12].charAt(1) >= '0' && separa[12].charAt(1) <= '9') {
         LocalDate data = LocalDate.parse(separa[12], formatter);
         this.dateOfBirth = data;
      }

      this.yearOfBirth = separa[13];
      this.eyeColour = separa[14];
      this.gender = separa[15];
      this.hairColour = separa[16];

   }

   public void imprimir() {
      String datas = arruma(this.dateOfBirth);
      // String atores = nomeacao(this.alternate_actors);
      System.out
            .println("[" + this.id + " ## " + this.name + " ## " + this.alternate_names + " ## " + this.house
                  + " ## "
                  + this.ancestry + " ## " + this.species + " ## " + this.patronus + " ## " + this.hogwartsStaff
                  + " ## " + this.hogwartsStudent + " ## " + this.actorName
                  + " ## " + this.alive + " ## " + datas + " ## " + this.yearOfBirth + " ## "
                  + this.eyeColour + " ## "
                  + this.gender + " ## " + this.hairColour + " ## " + this.wizard + "]");
   }

   public String arruma(LocalDate d) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      String dataString = d.format(formatter);
      return dataString.replace("/", "-");

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

   public String getAlternate_names() {
      return alternate_names;
   }

   public void setAlternate_names(String alternate_names) {
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

   public String getAlternate_actors() {
      return alternate_actors;
   }

   public void setAlternate_actors(String alternate_actors) {
      this.alternate_actors = alternate_actors;
   }

   public LocalDate getDateOfBirth() {
      return dateOfBirth;
   }

   public void setDateOfBirth(LocalDate dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
   }

   public String getYearOfBirth() {
      return yearOfBirth;
   }

   public void setYearOfBirth(String yearOfBirth) {
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

   public void heapsort() {
      int qRepeticoes = 0, nMovimentacoes = 0;
      for (int i = n / 2 - 1; i >= 0; i--) {
         aplicarHeap(n,i);
      }
      for (int j = n+1; j > 0; j--) {
         Personagem aux = array[0];
         array[0]=array[j];
         array[j]=aux;

         aplicarHeap(j,0);
      }

      log(qRepeticoes, nMovimentacoes);
   }

   private void aplicarHeap(int n, int i) {
      int raiz = i;
      int esquerda = 2 * i + 1;
      int direita = 2 * i + 2;
      if (array[esquerda] != null && array[raiz] != null) {
         if (esquerda < n && teste1(array[esquerda],array[raiz]) > 0) {
            raiz = esquerda;            
         }
      }
      if (array[direita] != null && array[raiz] != null) {
         if (direita < n && teste1(array[direita],array[raiz]) > 0) {
            raiz = direita;
         }
      }
      if (raiz != i) {
         Personagem aux = array[i];
         array[i] = array[raiz];
         array[raiz] = aux;
         aplicarHeap(n,raiz);
      }
   }
   private int teste1(Personagem j, Personagem tmp) {
      int valor = j.getHairColour().compareTo(tmp.getHairColour());
      return valor != 0 ? valor : j.getName().compareTo(tmp.getName());
   
   }
   private void log(int qRepeticoes, int nMovimentacoes) {
      try (FileWriter myWriter = new FileWriter("793599_heapsort.txt")) {
         myWriter.write("788060\t" + System.currentTimeMillis() + "\t" + qRepeticoes + "\t" + nMovimentacoes);
         myWriter.close();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private int compara(Personagem j, Personagem tmp) {
      int valor = j.getDateOfBirth().compareTo(tmp.getDateOfBirth());
      return valor != 0 ? valor : j.getName().compareTo(tmp.getName());

   }

   public Lista(int tamanho) {
      array = new Personagem[tamanho];
      n = 0;
   }

   public void inserirFim(Personagem x) throws Exception {

      // validar heapsort
      if (n >= array.length) {
         throw new Exception("Erro ao inserir!");
      }

      array[n] = x;
      n++;
   }

   public void mostrar() {
      for (int i = 0; i < n; i++) {
         if(array[i] != null){
            array[i].imprimir();
         }
      }
   }

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

   public Personagem pesquisarP(String x) {
      Personagem retorno = new Personagem();
      if (x != null) {
         for (int i = 0; i < n; i++) {
            if (array[i].getId() != null) {
               if (((array[i].getId()).equals(x))) {
                  retorno = array[i];
               }
            }
         }

      }
      return retorno;
   }
}

