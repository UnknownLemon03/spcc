import java.util.*;
// CodeOptimizerSimple
public class Exp6 {

    // A class to represent each expression (like a = b+c)
    static class Expression {
        char left;
        String right;

        Expression(char left, String right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Expression> original = new ArrayList<>();
        List<Expression> useful = new ArrayList<>();

        System.out.print("Enter the number of expressions: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume the leftover newline

        // Input expressions
        for (int i = 0; i < n; i++) {
            System.out.print("left: ");
            char left = scanner.nextLine().charAt(0);
            System.out.print("right: ");
            String right = scanner.nextLine();
            original.add(new Expression(left, right));
        }

        // Show Intermediate Code
        System.out.println("\nIntermediate Code:");
        for (Expression expr : original) {
            System.out.println(expr.left + " = " + expr.right);
        }

        // Dead Code Elimination
        for (int i = 0; i < n - 1; i++) {
            char resultVar = original.get(i).left;
            boolean isUsed = false;
            for (int j = i + 1; j < n; j++) {
                if (original.get(j).right.indexOf(resultVar) != -1) {
                    isUsed = true;
                    break;
                }
            }
            if (isUsed) {
                useful.add(original.get(i));
            }
        }

        // Last expression is always useful
        useful.add(original.get(n - 1));

        System.out.println("\nAfter Dead Code Elimination:");
        for (Expression expr : useful) {
            System.out.println(expr.left + " = " + expr.right);
        }

        // Common Subexpression Elimination
        for (int i = 0; i < useful.size(); i++) {
            for (int j = i + 1; j < useful.size(); j++) {
                if (useful.get(i).right.equals(useful.get(j).right)) {
                    char oldVar = useful.get(j).left;
                    char newVar = useful.get(i).left;
                    useful.get(j).left = newVar;

                    // Replace oldVar with newVar in all right-hand sides
                    for (Expression expr : useful) {
                        expr.right = expr.right.replace(oldVar, newVar);
                    }
                }
            }
        }

        System.out.println("\nEliminate Common Expressions:");
        for (Expression expr : useful) {
            System.out.println(expr.left + " = " + expr.right);
        }

        // Final Optimized Code: remove duplicates
        Set<String> seen = new HashSet<>();
        System.out.println("\nOptimized Code:");
        for (Expression expr : useful) {
            String key = expr.left + "=" + expr.right;
            if (!seen.contains(key)) {
                seen.add(key);
                System.out.println(expr.left + " = " + expr.right);
            }
        }

        scanner.close();
    }
}

