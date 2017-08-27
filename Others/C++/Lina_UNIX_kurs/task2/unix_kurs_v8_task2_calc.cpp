#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void printLength(char* message) {
    
    printf("Message: %s", message);
    
    char *token[50];
    
    token[0] = strtok(message, " ");

    int i = 0;
    while(token[i]!= NULL) {   //ensure a pointer was found
        i++;
        token[i] = strtok(NULL, " "); //continue to tokenize the string
    }
    
    float avg = 0;
    i=0;
    while(token[i]!= NULL) {
        printf("\ntoken[%d]: %s", i, token[i]);
        avg += atof(token[i]);
        i++;
    }
    
    avg /= i;
    printf("\nAverage: %f", avg);
    
  //printf("\nServer function. Full length: %d", strlen(message));
}

int main(int argc, char *argv[]) {
  printf("[DEBUG] CALC started.\n");
  printLength(argv[0]);
}
