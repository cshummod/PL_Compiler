package pseudocode.compiler;

import com.creativewidgetworks.goldparser.engine.ParserException;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

/**
 * An interpreter for the Pseudocode Language Grammar language. Source files can
 * be executed and parse trees can be generated from the input stream.
 */
public class Parser {

    /**
     * Executes a program written in Pseudocode Language Grammar.
     *
     * @param sourceCode to interpret
     * @param wantTree true if the parse tree should be generated
     * @return parse tree if generate parse tree open is set
     */
    public String errorMessage;

    public String executeProgram(String sourceCode, boolean wantTree) throws IOException {
        // Use the compiled grammar file inside the jar
        GOLDParser parser = new GOLDParser(
                new FileInputStream("Pseudocode Language Grammar.egt"), // compiled grammar table
                "Pseudocode Language Grammar", // rule handler package (fully qualified package)
                true);                                         // trim reductions

        // Controls whether or not a parse tree is returned or the program executed.
        parser.setGenerateTree(wantTree);

        String tree = null;
        try {
            // Parse the source statements to see if it is syntactically correct
            boolean parsedWithoutError = parser.parseSourceStatements(sourceCode);

            // Holds the parse tree if setGenerateTree(true) was called
            tree = parser.getParseTree();

            // Either execute the code or print any error message
            if (parsedWithoutError) {
                parser.getCurrentReduction().execute();
            } else {
                errorMessage = parser.getErrorMessage();
            }
        } catch (ParserException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return tree;
    }

    /*----------------------------------------------------------------------------*/
    /**
     * Load a source file to be interpreted by the engine.
     *
     * @param filename of a source file
     * @return source code to be interpreted
     * @throws IOException
     */
    public String loadSourceFile(String filename) throws IOException {
        File file = new File(filename);
        FileInputStream fis = new FileInputStream(file);
        byte[] buf = new byte[(int) file.length()];
        fis.read(buf);
        fis.close();
        return new String(buf);
    }

    /*----------------------------------------------------------------------------*/
    public void parse() throws Exception {
        printConsole();
        errorMessage = null;
        System.out.println("\n\n");
        System.out.println("             ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~               ");
        System.out.println("~~~~~~~~~~~~~~     Syntax Analyzer       ~~~~~~~~~~~~~~~~");
        System.out.println("             ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~             \n");
        String source = loadSourceFile("input.txt");
        boolean wantTree = true;
        String tree = executeProgram(source, wantTree);
        if (wantTree) {
            System.out.println(tree);
        }

    }

    public static void printConsole() {
        try {
            PrintStream out = new PrintStream("parserOutput.txt");
            System.setOut(out);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
