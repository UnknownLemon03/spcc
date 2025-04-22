import java.util.Scanner;
// shift reducer parcer 
public class Exp4 {
    static int i = 0, j = 0, k = 0, z = 0, c = 0;
    static char[] a = new char[20];       // input string
    static char[] stk = new char[20];     // stack
    static String act = "SHIFT->";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("GRAMMAR is E->E+E \n E->E*E \n E->(E) \n E->id");
        System.out.print("Enter input string: ");
        String input = sc.nextLine();

        a = input.toCharArray();
        c = a.length;

        System.out.println("stack\tinput\taction");

        for (k = 0, i = 0; j < c; k++, i++, j++) {
            if (j + 1 < c && a[j] == 'i' && a[j + 1] == 'd') {
                stk[i] = a[j];
                stk[i + 1] = a[j + 1];
                stk[i + 2] = '\0';
                a[j] = ' ';
                a[j + 1] = ' ';
                j++;
                printStep("id");
                check();
            } else {
                stk[i] = a[j];
                stk[i + 1] = '\0';
                a[j] = ' ';
                printStep("symbols");
                check();
            }
        }
        System.out.println();
    }

    static void check() {
        String ac = "REDUCE TO E";

        for (z = 0; z < c; z++) {
            if (stk[z] == 'i' && stk[z + 1] == 'd') {
                stk[z] = 'E';
                stk[z + 1] = '\0';
                printReduction(ac);
            }
        }
        for (z = 0; z < c; z++) {
            if (stk[z] == 'E' && stk[z + 1] == '+' && stk[z + 2] == 'E') {
                stk[z] = 'E';
                stk[z + 1] = '\0';
                stk[z + 2] = '\0';
                printReduction(ac);
                i -= 2;
            }
        }
        for (z = 0; z < c; z++) {
            if (stk[z] == 'E' && stk[z + 1] == '*' && stk[z + 2] == 'E') {
                stk[z] = 'E';
                stk[z + 1] = '\0';
                stk[z + 2] = '\0';
                printReduction(ac);
                i -= 2;
            }
        }
        for (z = 0; z < c; z++) {
            if (stk[z] == '(' && stk[z + 1] == 'E' && stk[z + 2] == ')') {
                stk[z] = 'E';
                stk[z + 1] = '\0';
                stk[z + 2] = '\0';
                printReduction(ac);
                i -= 2;
            }
        }
    }

    static void printStep(String action) {
        System.out.printf("\n$%s\t%s$\t%s%s",
                new String(stk).trim(),
                new String(a).replace('\0', ' ').trim(),
                act, action);
    }

    static void printReduction(String action) {
        System.out.printf("\n$%s\t%s$\t%s",
                new String(stk).trim(),
                new String(a).replace('\0', ' ').trim(),
                action);
    }
}

