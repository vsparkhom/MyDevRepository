#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>

int main(int argc, char *argv[]) {
    //const char* MSG1 = "Hello world from client";
    char MSG1[256];
    
    int port = atoi(argv[1]);
    printf("[DEBUG] port: %d\n", port);
    
    char host[20], buf[80];
    int sid, len, nsid;
    int domain = AF_INET;
    struct sockaddr_in addr;
    struct hostent* hp;
    
    strcpy(host, argv[2]);
    printf("[DEBUG] host: %s\n", host);
    
    sid = socket(domain, SOCK_STREAM, 0);
    addr.sin_family = domain;
    hp = gethostbyname(host);
    memcpy(&addr.sin_addr.s_addr, hp -> h_addr, 4);
    addr.sin_port = htons(port);
    len = sizeof (addr);
    connect(sid, (struct sockaddr*) &addr, len);
    
    printf("Message: ");
    memset(MSG1, 0, 256);
    gets(MSG1);
    send(sid, MSG1, strlen(MSG1) + 1, 0);
    printf("Message sent\n");
    shutdown(sid, 2);
}
