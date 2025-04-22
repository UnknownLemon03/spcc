%{
#include <stdio.h>
#include <stdlib.h>
#include "y.tab.h"  // Include token definitions from Lex
extern void yyerror(char *s);
extern int yylex(void);  
%}

%token A B END

%%
start: s END { printf("Valid String\n"); return 0; }
s: A s B { }
  | 
  ;
%%

int main() {
    printf("Enter the string: ");
    if (yyparse() == 0) {
        printf("Valid String\n");
    } else {
        printf("Not accepted\n");
    }
    return 0;
}

void yyerror(char *s) {
    printf("Error: ");
    exit(1);
}

