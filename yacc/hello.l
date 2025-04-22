%{
#include <stdio.h>
#include "y.tab.h"  // Include token definitions from Yacc
%}

%%
a   { return A; }
b   { return B; }
\n  { return END; }
.   { return 0; }  // Handle other characters
%%

int yywrap() {
    return 1;
}

