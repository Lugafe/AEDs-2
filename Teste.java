
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Teste {
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
      personagens2.sort();
      personagens2.mostrar();
   }

   public static Lista arquivo() throws Exception {
      // File file = new File("characters.csv");
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
            .println("[" + this.id + " ## " + this.name + " ## " + this.alternate_names + " ## " + this.house + " ## "
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

class No {
   int elemento;
   No esq, dir;
   int nivel;

   public No(int elemento) {
      this(elemento, null, null, 1);
   }

   public No(int x, No e, No d, int n) {
      elemento = x;
      esq = e;
      dir = d;
      nivel = n;
   }

   public int getNivel(No no) {
      return (no == null) ? 0 : no.nivel;
   }

   public void setNivel() {
      nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
   }

}

class AVL {
   No raiz;

   public AVL() {
      raiz = null;
   }

   public void caminharCentral() {
      caminharCentral(raiz);
   }

   public void caminharCentral(No i) {
      if (i != null) {
         caminharCentral(i.esq);
         System.err.println(i.elemento + " " + i.nivel);
         caminharCentral(i.dir);
      }
   }

   public boolean pesquisar(int x) {
      return pesquisar(raiz, x);
   }

   private boolean pesquisar(No i, int x) {
      boolean ok = false;
      if (i == null) {
         ok = false;
      } else if (x < i.elemento) {
         ok = pesquisar(i.esq, x);
      } else if (x > i.elemento) {
         ok = pesquisar(i.dir, x);
      } else if (x == i.elemento) {
         ok = true;
      }
      return ok;
   }

   public void inserir(int x) {
      raiz = inserir(raiz, x);
   }

   private No inserir(No i, int x) {
      if (i == null) {
         i = new No(x);
      } else if (i.elemento > x) {
         i.esq = inserir(i.esq, x);
      } else if (i.elemento < x) {
         i.dir = inserir(i.dir, x);
      } else {
         System.out.println("erro");
      }
      return balancear(i);
   }

   private No balancear(No no) {
      if (no != null) {
         int fator = no.getNivel(no.dir) - no.getNivel(no.esq);
         if (Math.abs(fator) <= 1) {
            no.setNivel();
         } else if (fator == 2) {
            int fatorFilhoDir = no.getNivel(no.dir.dir) - no.getNivel(no.dir.esq);

            if (fatorFilhoDir == -1) {
               no.dir = rotDir(no);
            }
            no = rotEsq(no);
         } else if (fator == -2) {
            int fatorFilhoEsq = no.getNivel(no.esq.dir) - no.getNivel(no.esq.esq);

            if (fatorFilhoEsq == 1) {
               no.esq = rotEsq(no);
            }
            no = rotDir(no);
         } else {
            System.out.println(" Erro ao balancear");
         }
      }
      return no;
   }

   private No rotEsq(No i) {
      No noDir = i.dir;
      No noDirEsq = noDir.esq;

      noDir.esq = i;
      i.dir = noDirEsq;

      i.setNivel();
      noDir.setNivel();
      return noDir;
   }

   private No rotDir(No i) {
      No noEsq = i.esq;
      No noEsqDir = noEsq.dir;

      noEsq.dir = i;
      i.esq = noEsqDir;      
      
      i.setNivel();
      noEsq.setNivel();
      return noEsq;
   }

}

