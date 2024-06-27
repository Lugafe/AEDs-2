#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define MAXTAM 5

typedef struct
{
    char id[100];
    char name[50];
    char alternate_names[10][50];
    int alternate_names_count;
    char house[20];
    char ancestry[20];
    char species[30];
    char patronus[30];
    bool hogwartsStaff;
    bool hogwartsStudent;
    char actorName[50];
    bool alive;
    struct
    {
        int year;
        int month;
        int day;
    } dateOfBirth;
    int yearOfBirth;
    char eyeColour[20];
    char gender[20];
    char hairColour[20];
    bool wizard;
} Personagem;

char **split(char *str, char delim)
{
    int row = 0, col = 0, pos = 0;
    char **result = calloc(20, sizeof(char *));

    while (str[pos] != '\0')
    {

        result[row] = calloc(strlen(str) + 1, sizeof(char));

        while (str[pos] != delim && str[pos] != '\0')
        {
            result[row][col++] = str[pos++];
            result[row][col] = '\0';
        }

        row++;
        col = 0;

        if (str[pos] == delim)
        {
            pos++;
        }
    }

    return result;
}

typedef struct
{
    Personagem array[MAXTAM];
    int primeiro, ultimo;
} Fila;



void ler(char *registro, Personagem *p)
{
    char **values = split(registro, ';');
    char **alt_names = split(values[2], '\'');
    char **date_of_birth = split(values[12], strchr(values[12], '/') ? '/' : '-');
    int pos = 0;

    strcpy(p->id, values[0]);
    strcpy(p->name, values[1]);
    p->alternate_names_count = 0;

    for (int i = 0; i < 10; i++)
    {
        if (alt_names[i] != NULL)
        {
            // if(strcmp(alt_names[i], "[]") != 0 &&strcmp(alt_names[i], "[") != 0 &&strcmp(alt_names[i], "") != 0 && strcmp(alt_names[i], ",") != 0)
            if (strlen(alt_names[i]) > 2)
            {
                strcpy(p->alternate_names[pos++], alt_names[i]);
                p->alternate_names_count++;
            }
        }
        else
        {
            strcpy(p->alternate_names[pos++], "");
        }
    }

    strcpy(p->house, values[3]);
    strcpy(p->ancestry, values[4]);
    strcpy(p->species, values[5]);
    strcpy(p->patronus, values[6]);
    p->hogwartsStaff = strcmp("VERDADEIRO", values[7]) == 0;
    p->hogwartsStudent = strcmp("VERDADEIRO", values[8]) == 0;
    strcpy(p->actorName, values[9]);
    p->alive = strcmp("VERDADEIRO", values[10]) == 0;

    p->dateOfBirth.day = atoi(date_of_birth[0]);
    p->dateOfBirth.month = atoi(date_of_birth[1]);
    p->dateOfBirth.year = atoi(date_of_birth[2]);
    p->yearOfBirth = atoi(values[13]);

    strcpy(p->eyeColour, values[14]);
    strcpy(p->gender, values[15]);
    strcpy(p->hairColour, values[16]);
    p->wizard = strlen(values[17]) == 12;

    for (int v = 0; v < 20; v++)
    {
        free(values[v]);
    }

    free(values);

    for (int v = 0; v < 20; v++)
        free(alt_names[v]);

    free(alt_names);
}

void imprimir(Personagem p)
{
    printf("[%s ## ", p.id);
    printf("%s ## {", p.name);

    for (int i = 0; i < 10; i++)
    {
        if (strlen(p.alternate_names[i]) > 1)
        {
            if (i < p.alternate_names_count - 1)
            {
                printf("%s, ", p.alternate_names[i]);
            }
            else
            {
                printf("%s", p.alternate_names[i]);
            }
        }
    }

    printf("} ## %s ", p.house);
    printf("## %s ", p.ancestry);
    printf("## %s ", p.species);
    printf("## %s ", p.patronus);
    printf("## %s ", p.hogwartsStaff ? "true" : "false");
    printf("## %s ", p.hogwartsStudent ? "true" : "false");
    printf("## %s ", p.actorName);
    printf("## %s ", p.alive ? "true" : "false");
    printf("## %02d-%02d-%d ", p.dateOfBirth.day, p.dateOfBirth.month, p.dateOfBirth.year);
    printf("## %d ", p.yearOfBirth);
    printf("## %s ", p.eyeColour);
    printf("## %s ", p.gender);
    printf("## %s ", p.hairColour);
    printf("## %s]\n", p.wizard ? "true" : "false");
}

void imprimirPosicao(Personagem p, int pos)
{
    printf("[%i ## ", pos);
    printf("%s ## ", p.id);
    printf("%s ## {", p.name);

    for (int i = 0; i < 10; i++)
    {
        if (strlen(p.alternate_names[i]) > 1)
        {
            if (i < p.alternate_names_count - 1)
            {
                printf("%s, ", p.alternate_names[i]);
            }
            else
            {
                printf("%s", p.alternate_names[i]);
            }
        }
    }

    printf("} ## %s ", p.house);
    printf("## %s ", p.ancestry);
    printf("## %s ", p.species);
    printf("## %s ", p.patronus);
    printf("## %s ", p.hogwartsStaff ? "true" : "false");
    printf("## %s ", p.hogwartsStudent ? "true" : "false");
    printf("## %s ", p.actorName);
    printf("## %s ", p.alive ? "true" : "false");
    printf("## %02d-%02d-%d ", p.dateOfBirth.day, p.dateOfBirth.month, p.dateOfBirth.year);
    printf("## %d ", p.yearOfBirth);
    printf("## %s ", p.eyeColour);
    printf("## %s ", p.gender);
    printf("## %s ", p.hairColour);
    printf("## %s]\n", p.wizard ? "true" : "false");
}












void start(Fila *Fila)
{
    Fila->primeiro = -1;
    Fila->ultimo = -1;
}

int isFull(Fila* fila) {
    if ((fila->ultimo + 1) % MAXTAM == fila->primeiro) {
        return 1;
    }
    return 0;
}










Personagem remover(Fila* fila) {
    Personagem p;

    if (isEmpty(fila)) {
        printf("A fila está vazia\n");
        return p;
    }
    p = fila->array[fila->primeiro];
    if (fila->primeiro == fila->ultimo) {
        fila->primeiro = -1;
        fila->ultimo = -1;
    } else {
        fila->primeiro = (fila->primeiro + 1) % MAXTAM;
    }

    return p;
}

int isEmpty(Fila* fila) {
    if (fila->primeiro == -1) {
        return 1;
    }
    return 0;


}




int averageYear(Fila *fila){

    if (isEmpty(fila)) {
        return 0; // Fila vazia
    }

    int cont = 0, soma = 0;
    int i = fila->primeiro;

    do {
        soma += fila->array[i].yearOfBirth;
        cont++;
        i = (i + 1) % MAXTAM;
    } while (i != (fila->ultimo + 1) % MAXTAM);

    return soma / cont;
}




















void mostrar(Fila *fila)
{
    if (isEmpty(fila)) {
        printf("A fila está vazia\n");
        return;
    }

    int pos = 0;

    printf("[ Head ]\n");
    int i = fila->primeiro;
    while (1) {
        imprimirPosicao(fila->array[i], pos++);
        if (i == fila->ultimo) {
            break;
        }
        i = (i + 1) % MAXTAM;
    }
    printf("[ Tail ]\n");
}

void inserir(Fila* fila, Personagem p) {
    if (isFull(fila)) {
        printf("A fila está cheia\n");
        return;
    }
    if (fila->primeiro == -1) {
        fila->primeiro = 0;
    }
    fila->ultimo = (fila->ultimo + 1) % MAXTAM;
    fila->array[fila->ultimo] = p;
}



int main()
{
    FILE *file;
    Fila strline;
    Personagem personagens[500];
    char line[500];
    char id[100];
    char instrucao[100];
    int n, pos = 0;

    file = fopen("/tmp/characters.csv", "r");
    if (file == NULL)
    {
        perror("Error opening the file");
        return 1;
    }

    start(&strline);

    fgets(line, 500, file); // eliminar a primeira linha
    while (fgets(line, 500, file) != NULL)
    {
        Personagem p;
        ler(line, &p);

        personagens[pos++] = p; //popular array principal com dados do csv
    }

    fclose(file);

    while (scanf(" %[^\r\n]%*c", id) == 1 && strcmp(id, "FIM") != 0) {

        for (int i = 0; i < 500; i++) {

            if(strcmp(personagens[i].id, id) == 0) {

                if(isFull(&strline)){
                    remover(&strline);
                }
                inserir(&strline, personagens[i]);
                break;
            }
        }
        // printf("p: %i u: %i\n", strline.primeiro, strline.ultimo);

        // mostrar(&strline);
        printf(">> Year Birthday Average: %i\n", averageYear(&strline));
    }
    

    scanf("%i", &n);
    for (int i = 0; i < n; i++)
    {

        scanf(" %[^\r\n]%*c", instrucao);

        char **tokens = split(instrucao, ' ');
        Personagem personagem;

        if (tokens[0][0] == 'I')
        {
            for (int j = 0; j < 500; j++)
            {
                if (strcmp(personagens[j].id, tokens[1]) == 0)
                {

                    personagem = personagens[j];
                    break;
                }
            }
        }

        if (strcmp(tokens[0], "I") == 0)
        {
            if(isFull(&strline)){
                remover(&strline);
            }
            
            inserir(&strline, personagem);
            printf(">> Year Birthday Average: %i\n", averageYear(&strline));
        }
        else if (strcmp(tokens[0], "R") == 0)
        {
            personagem = remover(&strline);
            printf("(R) %s\n", personagem.name);
        }

        
    }

    mostrar(&strline);

    return 0;
}
