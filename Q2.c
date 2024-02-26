#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

bool palindrom(char *p)
{
    
    int i = 0;
    int fim = strlen(p) - 1;
    bool ok = true;
    
    while (i < fim/2)
    {
        if (p[i] != p[fim])
        {
            ok = false;            
        }
        fim--;
        i++;
    }
    return ok;
}

int main()
{   
    char *p = (char *)malloc(10 * sizeof(char));
    scanf("%s", p);
    do
    {
        if (strcmp(p, "FIM") != 0)
        {
            if (palindrom(p))
            {
                printf("SIM\n");
            }
            else
            {
                printf("NAO\n");
            }
        }
        scanf("%s", p);

    } while (strcmp(p, "FIM") != 0);

    free(p);
}
