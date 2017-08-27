#include <iostream>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <fcntl.h>
#include <string.h>

#define RECORDS_COUNT 2

struct PRODUCT {
    char name[20];
    int count;
    float price;
} product;

char fname[] = "list.txt";

int fdesc;
int len = sizeof (product);


void createFile() {
    fdesc = open(fname, O_WRONLY);
    for (int i = 1; i <= RECORDS_COUNT; i++) {
        printf("\nRecord %i\n", i);

        printf("Name: ");
        scanf("%s", &product.name);

        printf("Count: ");
        scanf("%d", &product.count);

        printf("Price: ");
        scanf("%f", &product.price);
        
        int res = write(fdesc, &product, len);
        
        printf("Product saved: %s - %d - %f\n", product.name, product.count, product.price);
    }
    close(fdesc);
}

void printFile(char f[], int count) {
    fdesc = open(f, O_RDONLY);
    printf("%3s |%10s |%6s |%7s\n", "num", "name", "count", "price");
    for (int i = 1; i <= count; i++) {
        int res = read(fdesc, &product, len);
        if (res == 0) break;
        printf("%3d |%10s |%6d |%7.2f\n", i, product.name, product.count, product.price);
    }
    close(fdesc);
}

void changePrice(char f[], int count) {
    
    PRODUCT updatedDevices[count];
    char desiredName[20];
    int isFound = 0;
    
    printf("Enter device name you want to change:");
    scanf("%s", &desiredName);
    
    fdesc = open(f, O_RDONLY);
    for (int i = 0; i < count; i++) {
        int res = read(fdesc, &updatedDevices[i], len);
        if (res == 0) break;
        
        if (!strcmp(updatedDevices[i].name, desiredName)) {
            printf("Device with name \"%s\" found:\n", desiredName);
            printf("%3s |%10s |%6s |%7s\n", "num", "name", "count", "price");
            printf("%3d |%10s |%6d |%7.2f\n", (i+1), updatedDevices[i].name, updatedDevices[i].count, updatedDevices[i].price);
            
            float newPrice;
            printf("Enter new price:");
            scanf("%f", &newPrice);
            
            updatedDevices[i].price = newPrice;
            
            isFound = 1;
        }
    }
    close(fdesc);
    
    if (isFound == 1) {
        fdesc = open(fname, O_WRONLY);
        for(int i=0; i<count; i++) {
            write(fdesc, &updatedDevices[i], len);
        }
        close(fdesc);
        printf("Price has been changed.\n");
    } else {
        printf("Device with name \"%s\" was not found\n", desiredName);
    }
    
}



int main() {
    int r;
    while (1) {
        puts("\nChoose option below:");
        puts("1 - create file");
        puts("2 - print list.txt");
        puts("3 - update record");
        puts("0 - exit:");
        printf("> ");
        scanf("%i", &r);
        switch (r) {
            case 1: 
                createFile();
                printf("\nFile created");
            break;
            case 2: 
                printFile(fname, RECORDS_COUNT);
                printf("\nFile listed");
            break;
            case 3: 
                changePrice(fname, RECORDS_COUNT);
            break;
            case 0: return 0;
            default: printf("No such option!\n");
        }
    }
    return 1;
}