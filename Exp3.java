import java.io.*;
import java.util.*;

public class Exp3 {

    static Set<String> keywords = new HashSet<>(Arrays.asList(
        "int", "long", "float", "char", "double", "bool", "void", "extern", "unsigned",
        "goto", "static", "class", "struct", "for", "if", "else", "return", 
        "register", "while", "do"
    ));
    static Set<String> preprocessor = new HashSet<>(Arrays.asList("include", "define"));
    static Set<String> headers = new HashSet<>(Arrays.asList(
        "stdio.h", "conio.h", "malloc.h", "process.h", "string.h", "ctype.h"
    ));
    static Set<Character> delimiters = new HashSet<>(Arrays.asList(
        ' ', '\t', '\n', ',', ';', '(', ')', '{', '}', '[', ']', '#', '<', '>'
    ));
    static Set<Character> operators = new HashSet<>(Arrays.asList(
        '+', '-', '*', '/', '=', '%', '<', '>', '&', '|', '!'
    ));

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filename = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println();
            while ((line = br.readLine()) != null) {
                analyzeLine(line);
            }
            System.out.println("\nEnd of file");
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    static void analyzeLine(String line) {
        int i = 0;
        StringBuilder token = new StringBuilder();

        while (i < line.length()) {
            char c = line.charAt(i);

            if (Character.isLetterOrDigit(c) || c == '#') {
                token.append(c);
            } else {
                if (token.length() > 0) {
                    classifyToken(token.toString());
                    token.setLength(0);
                }

                if (delimiters.contains(c)) {
                    System.out.println("Delimiter\t" + c);
                } else if (operators.contains(c)) {
                    System.out.println("Operator\t" + c);
                }
            }
            i++;
        }

        if (token.length() > 0) {
            classifyToken(token.toString());
        }
    }

    static void classifyToken(String token) {
        if (keywords.contains(token)) {
            System.out.println("Keyword\t\t" + token);
        } else if (preprocessor.contains(token)) {
            System.out.println("Preprocessor\t" + token);
        } else if (headers.contains(token)) {
            System.out.println("Header File\t" + token);
        } else if (token.matches("\\d+")) {
            System.out.println("Number\t\t" + token);
        } else {
            System.out.println("Identifier\t" + token);
        }
    }
}

