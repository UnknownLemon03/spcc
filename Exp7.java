import java.io.*;
import java.util.*;

public class Exp7 {

    public static void main(String[] args) {
        String op, arg1, arg2, result;

        // Using try-with-resources to ensure that resources are automatically closed
        try (
              Scanner scanner = new Scanner(new File("input2.txt"));
              PrintWriter out = new PrintWriter("output.txt");
             ) {

            // Process each line from the input file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Split the line into parts
                String[] parts = line.split(" ");
         
                if (parts.length < 4) continue;
 
                op = parts[0];
                arg1 = parts[1];
                arg2 = parts[2];
                result = parts[3];
                // Process the operation and generate the corresponding assembly code
                switch (op) {
                    case "+":
                        out.printf("MOV R0,%s\n", arg1);
                        out.printf("ADD R0,%s\n", arg2);
                        out.printf("MOV %s,R0\n", result);
                        break;
                    case "-":
                        out.printf("MOV R0,%s\n", arg1);
                        out.printf("SUB R0,%s\n", arg2);
                        out.printf("MOV %s,R0\n", result);
                        break;
                    case "*":
                        out.printf("MOV R0,%s\n", arg1);
                        out.printf("MUL R0,%s\n", arg2);
                        out.printf("MOV %s,R0\n", result);
                        break;
                    case "/":
                        out.printf("MOV R0,%s\n", arg1);
                        out.printf("DIV R0,%s\n", arg2);
                        out.printf("MOV %s,R0\n", result);
                        break;
                    case "=":
                        out.printf("MOV R0,%s\n", arg1);
                        out.printf("MOV %s,R0\n", result);
                        break;
                    default:
                        System.out.println("Unknown operation: " + op);
                }
                out.printf("done");
            }

            System.out.println("Conversion completed. Check output2.txt.");

        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
    }
}

