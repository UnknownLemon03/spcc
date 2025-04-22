import java.util.*;
// OperatorPrecedenceParser
public class Exp5 {

    static String input;
    static int i = 0;
    static char[] stack = new char[50];
    static String[] handles = {")E(", "E*E", "E+E", "i", "E^E"};
    static char[][] prec = {
        //        +    -    *    /    ^    i    (    )    $
        /* + */ {'>', '>', '<', '<', '<', '<', '<', '>', '>'},
        /* - */ {'>', '>', '<', '<', '<', '<', '<', '>', '>'},
        /* * */ {'>', '>', '>', '>', '<', '<', '<', '>', '>'},
        /* / */ {'>', '>', '>', '>', '<', '<', '<', '>', '>'},
        /* ^ */ {'>', '>', '>', '>', '<', '<', '<', '>', '>'},
        /* i */ {'>', '>', '>', '>', '>', 'e', 'e', '>', '>'},
        /* ( */ {'<', '<', '<', '<', '<', '<', '<', '>', 'e'},
        /* ) */ {'>', '>', '>', '>', '>', 'e', 'e', '>', '>'},
        /* $ */ {'<', '<', '<', '<', '<', '<', '<', '<', '>'}
    };
    static int top = 0;
    static String lastHandle = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the input string (use 'i' for id): ");
        input = sc.nextLine();
        input += "$";
        stack[top] = '$';

        System.out.println("\nSTACK\tINPUT\tACTION");

        while (i < input.length()) {
            shift();
            display("Shift");

            while (getPrecedence(stack[top], input.charAt(i)) == '>') {
                if (!reduce()) {
                    break;
                }
                display("Reduced: E->" + lastHandle);
            }
        }

        if (String.valueOf(stack, 0, top + 1).equals("$E$")) {
            System.out.println("Accepted;");
        } else {
            System.out.println("Not Accepted;");
        }
    }

    static void shift() {
        stack[++top] = input.charAt(i++);
    }

    static boolean reduce() {
        for (String handle : handles) {
            int len = handle.length();
            if (top + 1 >= len) {
                boolean found = true;
                for (int j = 0; j < len; j++) {
                    if (stack[top - j] != handle.charAt(j)) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    top = top - len + 1;
                    stack[top] = 'E';
                    lastHandle = handle;
                    return true;
                }
            }
        }
        return false;
    }

    static char getPrecedence(char a, char b) {
        return prec[getIndex(a)][getIndex(b)];
    }

    static int getIndex(char c) {
        switch (c) {
            case '+': return 0;
            case '-': return 1;
            case '*': return 2;
            case '/': return 3;
            case '^': return 4;
            case 'i': return 5;
            case '(': return 6;
            case ')': return 7;
            case '$': return 8;
            default: return -1;
        }
    }

    static void display(String action) {
        System.out.print("\n");
        for (int j = 0; j <= top; j++) System.out.print(stack[j]);
        System.out.print("\t");
        for (int j = i; j < input.length(); j++) System.out.print(input.charAt(j));
        System.out.print("\t" + action);
    }
}

