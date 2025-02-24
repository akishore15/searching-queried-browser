#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void search_with_engine(const char *engine, const char *query) {
    char command[200];
    snprintf(command, sizeof(command), "./rust_backend %s \"%s\"", engine, query);

    FILE *fp = popen(command, "r");
    if (fp == NULL) {
        printf("Failed to run command\n");
        exit(1);
    }

    char result[1024];
    while (fgets(result, sizeof(result), fp) != NULL) {
        printf("%s", result);
    }

    pclose(fp);
}
