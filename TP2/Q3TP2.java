package TP2;
import java.io.IOException;
import java.io.File;
import java.util.*;

public class Q3TP2 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // File file = new File("characters.csv");
        // Scanner sc = new Scanner(System.in);

        Lista personagens = new Lista();
        Personagem p = new Personagem();
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
        s = sc.nextLine();
        do {
            if (!s.equalsIgnoreCase("FIM")) {
                if (personagens2.pesquisar(s)) {
                    System.out.println("SIM");
                } else {
                    System.out.println("NAO");
                }
                s = sc.nextLine();
            }
        } while (!s.equalsIgnoreCase("FIM"));
        sc.close();
    }

    public static Lista arquivo() throws Exception {
        File file = new File("characters.csv");
        //File file = new File("/tmp/characters.csv");
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

    public void ler(String line) {
        int c = 0;
        int c2 = 0;
        int c3 = 0;
        boolean ok = true;
        boolean ok2 = true;
        String atores = "";
        String atores2 = "";
        if (line.length() >= 14) {

            // separar a linha
            String separa[] = line.split(";");
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
    }

    public void imprimir() {
        String nomes = nomeacao(this.alternate_names);
        String atores = nomeacao(this.alternate_actors);
        System.out.println("[" + this.id + " ## " + this.actorName + " ## {" + nomes + "} ## " + this.house + " ## "
                + this.ancestry + " ## " + this.species + " ## " + this.patronus + " ## false ## false ## "
                + this.actorName
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

    public void inserirFim(Personagem x) throws Exception {

        // validar insercao
        if (n >= array.length) {
            throw new Exception("Erro ao inserir!");
        }

        array[n] = x;
        n++;
    }

    public boolean pesquisar(String x) {
        boolean retorno = false;
        if (x != null) {
            for (int i = 0; i < n && retorno == false; i++) {
                if (array[i] != null) {
                    retorno = (array[i].getName().equalsIgnoreCase(x));
                }
            }
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
