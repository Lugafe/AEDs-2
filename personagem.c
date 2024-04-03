#include <stdio.h>
#include <stdlib.h>
#include <string.h>
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