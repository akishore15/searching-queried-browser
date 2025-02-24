#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void search_with_engine(const char *engine, const char *query);

int main() {
    char engine[10];
    char query[100];

    printf("Select a search engine (google, bing, yandex, duckduckgo, yahoo, brave):\n");
    scanf("%s", engine);

    printf("Enter search query:\n");
    scanf(" %[^\n]", query);

    search_with_engine(engine, query);

    return 0;
}
