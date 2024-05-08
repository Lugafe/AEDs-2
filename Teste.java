
import java.io.IOException;
import java.io.File;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Teste {
   public static void main(String[] args) throws Exception {
      Scanner sc = new Scanner(System.in);
      Lista personagens = new Lista();
      Personagem p = new Personagem();
      //String ids[] = new String[500];
      Lista personagens2 = new Lista();
      String s = "";
      int i = 0;
      personagens = arquivo();
      s = sc.nextLine();
      do {
         if (!s.equalsIgnoreCase("FIM")) {
            p = personagens.pesquisarP(s);
            if (i == 0) {
               personagens2.inserirInicio(p);                              
               i++;            
            }else{
               personagens2.inserirFim(p);
            }
            p = new Personagem();
            s = sc.nextLine();
         }
      } while (!s.equalsIgnoreCase("FIM"));   
      int c = sc.nextInt();
      for (int j = 0; j < c; j++) {
         s = sc.nextLine();
         String sp[] = s.split(" ");
         switch (sp[0]) {
            case "II":
               personagens2.inserirInicio(personagens.pesquisarP(sp[1]));
               break;
            case "I*":
               personagens2.inserir(personagens.pesquisarP(sp[2]),Integer.parseInt(sp[1]));
               break;
            case "IF":
               personagens2.inserirFim(personagens.pesquisarP(sp[1]));
               break;
            case "RI":
               personagens2.removerInicio();
               break;
            case "R*":
               personagens2.remover(Integer.parseInt(sp[1]));
               break;
            case "RF":
               personagens2.removerFim();
               break;
         
            default:
               break;
         }
      }    
      personagens2.mostrar();
      sc.close();
   }

   public static Lista arquivo() throws Exception {
      File file = new File("characters.csv");
      //File file = new File("/tmp/characters.csv");
      Lista personagens = new Lista();
      Personagem p = new Personagem();
      //String line;
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

   // [9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8 ## Harry Potter ## {The Boy Who Lived,
   // The Chosen One, Undesirable No. 1, Potty} ## Gryffindor ## half-blood ##
   // human ## stag ## false ## false ## Daniel Radcliffe ## false ## 31-07-1980 ##
   // 1980 ## green ## male ## black ## false]
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

   private String arruma(LocalDate d) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = d.format(formatter);
        return dataString.replace("/","-");
        /* 
        String[] nova = dataString.split("/");
        String aux = nova[0];
        nova[0] = nova[2];
        nova[2] = aux;
        return String.join("-", nova);
        */
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

class Celula{
   Personagem p;
   Celula prox;
   public Celula() {
     this.p = new Personagem();
     this.prox = null;
   }
   public Celula(Personagem p, Celula prox) {
      this.p = p;
      this.prox = prox;
   }
   public Celula(Personagem p) {
      this.p = p;
      this.prox = null;
   }
   

   
}
class Lista {
   Celula primeiro, ultimo;

   public Lista(Celula primeiro, Celula ultimo) {
      this.primeiro = primeiro;
      this.ultimo = ultimo;
   }

   public Lista() {
      primeiro = new Celula();
      ultimo = primeiro;
   }

   public void inserirInicio(Personagem p){
      Celula tmp = new Celula(p);
      tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) {                 
			ultimo = tmp;
		}
      tmp = null;
   }

   
   public void inserirFim(Personagem p){
      ultimo.prox = new Celula(p);
      ultimo = ultimo.prox;
   }

   public Personagem removerInicio(){
      if (primeiro == ultimo) {
         System.out.println("Lista vazia");
         return null;
      }
      Celula tmp = primeiro;
      primeiro = primeiro.prox;
      Personagem per = tmp.p;
      tmp.prox = null;
      tmp = null;
      return per;
   }

   public Personagem removerFim(){
      if (primeiro == ultimo) {
         System.out.println("Lista vazia");
         return null;
      }
      Celula i;
      for (i = primeiro; i != ultimo; i=i.prox);      
      ultimo = i;
      Personagem per = i.prox.p;
      i = ultimo.prox = null;
      return per;
   }

   public void inserir(Personagem p, int pos){
      Celula i = primeiro;
      for (int j = 0; j < pos; j++, i = i.prox);
      Celula tmp = new Celula(p);
      tmp.prox = i.prox;
      i.prox = tmp;
      tmp = i = null;
   }

   public Personagem remover(int pos){
      Celula i = primeiro;
      for (int j = 0; j < pos; j++, i = i.prox);
      Celula tmp = i.prox;
      Personagem resp = tmp.p;
      i.prox = tmp.prox;
      tmp.prox = null;
      i = tmp = null;
      return resp;
   }

   public void mostrar(){
      for (Celula i = primeiro.prox; i != null; i=i.prox) {
         i.p.imprimir();
      }
   }


   public Personagem pesquisarP(String x) {
      Personagem retorno = new Personagem();
      if (x != null) {
         for (Celula i = primeiro; i != null; i = i.prox) {
            if (i.p.getId() != null) {
               if (((i.p.getId()).equals(x))) {
                  retorno = i.p;
               }
            }
         }

      }
      return retorno;
   }
}
