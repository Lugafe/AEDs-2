#include <stdio.h>
#include<stdbool.h>
#include<stdlib.h>
/*
bool palindrom(char *p){
    int i = 0;    
    int fim = sizeof(palavra);
    bool ok = true;

    while (i < fim)
    {
        if (palavra[i] != palavra[fim])
        {
            ok = false;
        }
        fim--;
        i++;        
    }
    return ok;
}
*/
int main(){
    char *p;
    p = (char *)malloc(5*sizeof(char));
    scanf("%s", &p); 
    printf("%s", p);

    
    free(p);

}
