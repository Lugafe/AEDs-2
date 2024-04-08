#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct 
{
    char id[500];
    char name[500];
    char alternate_names[500];
    char house[500];															
    char ancestry[500];
    char species[500];
    char patronus[500];
    bool hogwartsStaff;
    bool hogwartsStudent;
    char actorName[500];
    bool alive;
    char alternate_actors[500];
    char dateOfBirth[500];
    int yearOfBirth;
    char eyeColour[500];    
    char gender[500];
    char hairColour[500];
    bool wizard;
} personagem;


int main(){
    
    char S[500];
    char *ptr;

    FILE *fptr;

    fptr = fopen("characters.csv","r");

    while (EOF != fscanf(fptr, "%[^\n]\n", S))
    {
        
        ptr = strtok(S, ";");
        while (ptr != NULL)
        {
            printf("'%s' ", ptr);
            ptr = strtok(NULL,";");
        }
        printf("%s\n", "");
        
    }
    fclose(fptr);
    return 0;

}