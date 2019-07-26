package pseudocode.compiler;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 *
 * @author Mohammed
 */
public class PseudocodeCompiler {

    public String errorMessage;
    public static int lineNumber;

    public PseudocodeCompiler() {
        printConsole();
        errorMessage = null;
        lineNumber = 1;

        String currentLexema;
        Lexer lexer = new Lexer("input.txt");
        draw();

        while (!lexer.isExhausted()) {
            currentLexema = lexer.currentLexema();
            System.out.printf(lineNumber + "\t%-20s :  %s \n", currentLexema, lexer.currentToken());
            lexer.moveAhead();
            if ("{".equals(currentLexema) || "}".equals(currentLexema) || ";".equals(currentLexema)) {
                lineNumber++;
            }
            if (!lexer.isSuccessful()) {
                errorMessage = lexer.errorMessage();
            }
        }

    }

    public static void printConsole() {
        try {
            PrintStream out = new PrintStream("lexerOutput.txt");
            System.setOut(out);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    private void draw() {
        System.out.println("  ____                     _                     _      \n"
                + " |  _ \\ ___  ___ _   _  __| | ___   ___ ___   __| | ___ \n"
                + " | |_) / __|/ _ \\ | | |/ _` |/ _ \\ / __/ _ \\ / _` |/ _ \\\n"
                + " |  __/\\__ \\  __/ |_| | (_| | (_) | (_| (_) | (_| |  __/\n"
                + " |_|   |___/\\___|\\__,_|\\__,_|\\___/ \\___\\___/ \\__,_|\\___|\n"
                + "  _                                                     \n"
                + " | |    __ _ _ __   __ _ _   _  __ _  __ _  ___         \n"
                + " | |   / _` | '_ \\ / _` | | | |/ _` |/ _` |/ _ \\        \n"
                + " | |__| (_| | | | | (_| | |_| | (_| | (_| |  __/        \n"
                + " |_____\\__,_|_| |_|\\__, |\\__,_|\\__,_|\\__, |\\___|        \n"
                + "                   |___/             |___/              ");
        System.out.println("\n\n                                                     ");
        System.out.println("             ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~               ");
        System.out.println("~~~~~~~~~~~~~~     Lexical Analyzer      ~~~~~~~~~~~~~~~~");
        System.out.println("             ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~               ");
        System.out.println("---------------------------------------------------------");
        System.out.println("#\tToken\t\t\t\tType");
        System.out.println("---------------------------------------------------------");
    }

}
