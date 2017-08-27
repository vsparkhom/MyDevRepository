#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <unistd.h>
#include <iostream>

int main(int argc, char *argv[]) {
    int port = atoi(argv[1]);//atoi - converts the string argument str to an integer (type int).
    
    printf("[DEBUG] port: %d\n", port);
    
    char host[20];
    int sid, len, nsid;
    int domain = AF_INET; //AF_INET is an address family that is used to designate the type of addresses that your socket can communicate with (in this case, Internet Protocol v4 addresses)
    char buf1[256];
    struct sockaddr_in addr;
    struct hostent* hp;
    
    strcpy(host, argv[2]);
    printf("[DEBUG] host: %s\n", host);
    
    sid = socket(domain, SOCK_STREAM, 0); //creates socket with specific type and with defined protocol for exact domain
    
    addr.sin_family = domain;
    hp = gethostbyname(host);
    
    memcpy(&addr.sin_addr.s_addr, hp -> h_addr, 4);
    addr.sin_port = htons(port); //htons - function converts the unsigned integer hostlong from host byte order to network byte order.
        
    len = sizeof (addr);
    
    printf("[DEBUG] Binding...\n");
    bind(sid, (struct sockaddr*) &addr, len);
    
    printf("[DEBUG] Listening...\n");
    listen(sid, 5);
    
    printf("[DEBUG] Accepting...\n");
    nsid = accept(sid, 0, 0);
    
    printf("[DEBUG] Receiving...\n");
    recv(nsid, buf1, sizeof (buf1), 0);
    
    printf("Server  got message: %s\n", buf1);
    
    //const char *buf1 = "1 2 3 4 5";//DEBUG
    
    execl("./unix_kurs_v8_task2_calc", buf1, NULL);

}
