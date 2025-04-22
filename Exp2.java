import java.io.*;
import java.util.*;

class MDT {
    String label, opcode, operand;

    MDT(String label, String opcode, String operand) {
        this.label = label;
        this.opcode = opcode;
        this.operand = operand;
    }
}

public class Exp2 {
    public static void main(String[] args) {
        List<MDT> mdt = new ArrayList<>();
        String macroname = "";
        int lines = 0;

        try (
            Scanner sc = new Scanner(new File("input.txt"));
            PrintWriter out = new PrintWriter("output.txt");
            PrintWriter mdtOut = new PrintWriter("MDT.txt");
        ) {

            String label = sc.next();
            String opcode = sc.next();
            String operand = sc.next();

            while (!opcode.equals("END")) {
                if (opcode.equals("MACRO")) {
                    macroname = label;
                    label = sc.next();
                    opcode = sc.next();
                    operand = sc.next();
                    lines = 0;

                    while (!opcode.equals("MEND")) {
                        mdt.add(new MDT(label, opcode, operand));
                        mdtOut.printf("%s\t%s\t%s\n", label, opcode, operand);
                        label = sc.next();
                        opcode = sc.next();
                        operand = sc.next();
                        lines++;
                    }
                } else if (opcode.equals(macroname)) {
                    System.out.println("lines=" + lines);
                    for (MDT entry : mdt) {
                        out.printf("%s\t%s\t%s\n", entry.label, entry.opcode, entry.operand);
                        System.out.printf("DLAB=%s\nDOPC=%s\nDOPER=%s\n", entry.label, entry.opcode, entry.operand);
                    }
                } else {
                    out.printf("%s\t%s\t%s\n", label, opcode, operand);
                }

                label = sc.next();
                opcode = sc.next();
                operand = sc.next();
            }

            out.printf("%s\t%s\t%s\n", label, opcode, operand);
            System.out.println("FINISHED");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

