import java.util.*;



public class Exp1 {

    static String[][] code = {
        {"PRG1", "START", "", ""},
        {"", "USING", "*", "15"},
        {"", "L", "", ""},
        {"", "A", "", ""},
        {"", "ST", "", ""},
        {"FOUR", "DC", "F", ""},
        {"FIVE", "DC", "F", ""},
        {"TEMP", "DS", "1F", ""},
        {"", "END", "", ""}
    };

    static int[] lc = new int[code.length]; // Location counters
    static char[] avail = new char[15];     // Base register availability

    public static void main(String[] args) {
        initializeAvail();
        printHeader();
        calculateLC();
        printSymbolTable();
        printBaseTable();
        printPass2Table();
    }

    // Initialize base register availability
    static void initializeAvail() {
        Arrays.fill(avail, 'N');
    }

    // Print source code table
    static void printHeader() {
        System.out.println("----------------------------------------------------");
        System.out.println("LABEL\t\tOPCODE\t\tOPERAND1\tOPERAND2");
        System.out.println("----------------------------------------------------");

        for (String[] line : code) {
            for (String part : line) {
                System.out.print(part + "\t\t");
            }
            System.out.println();
        }
    }

    // Calculate and print LC values
    static void calculateLC() {
        System.out.println("-----------------------------------------------------");
        System.out.println("VALUES FOR LC : \n");

        int loc = 0;

        for (int i = 0; i < code.length; i++) {
            String opcode = code[i][1];
            if (!opcode.equals("START") && !opcode.equals("USING") && !opcode.equals("L")) {
                lc[i] = lc[i - 1] + 4;
            }
            System.out.print(lc[i] + "\t");
        }
        System.out.println();
    }

    // Print symbol table with location info
    static void printSymbolTable() {
        int loc = 0;

        System.out.println("\n\nSYMBOL TABLE:");
        System.out.println("----------------------------------------------------");
        System.out.println("SYMBOL\t\tVALUE\t\tLENGTH\t\tR/A");
        System.out.println("----------------------------------------------------");

        for (String[] line : code) {
            String label = line[0];
            String opcode = line[1];

            if (opcode.equals("START") && !label.isEmpty()) {
                System.out.printf("%s\t\t%d\t\t4\t\tR\n", label, loc);
            } else if (!label.isEmpty()) {
                System.out.printf("%s\t\t%d\t\t4\t\tR\n", label, loc);
                loc += 4;
            } else if (!opcode.equals("USING")) {
                loc += 4;
            }
        }

        System.out.println("----------------------------------------------------");
    }

    // Print base register table
    static void printBaseTable() {
        System.out.println("\nBASE TABLE:");
        System.out.println("-------------------------------------------------------");
        System.out.println("REG NO\t\tAVAILABILITY");
        System.out.println("-------------------------------------------------------");

        int regNum = -1;

        for (String[] line : code) {
            if (line[1].equals("USING")) {
                regNum = Integer.parseInt(line[3]);
                break;
            }
        }

        if (regNum > 0 && regNum <= 15) {
            avail[regNum - 1] = 'Y';
        }

        for (int i = 0; i < 15; i++) {
            System.out.printf("%d\t\t%c\n", i + 1, avail[i]);
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("Continue..??");
    }

    // Print pass2 table
    static void printPass2Table() {
        System.out.println("\nPASS2 TABLE:");
        printHeader();
    }
}

