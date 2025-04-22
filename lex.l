%{
#include <stdio.h>
%}
LETTER [a-z][A-Z]
DIGIT [0-9]
%%
[a-z]+([a-b]|[A-Z])* {printf("Valid %s\n",yytext);}
.+ {printf("Not Valid %s\n",yytext);}
%%
int main(){
	printf("Enter str");
	yylex();
	printf("\n");
	return 0;
}
