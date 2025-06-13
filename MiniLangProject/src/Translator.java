import java.io.*;
import java.nio.file.*;

public class Translator {

    public static String translate(String sourceCode) {
        Parser parser = new Parser(sourceCode);
        ProgramNode program = parser.parseProgram();
        return program.toPython();
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Paths.get("input.minilang"));
            String pythonCode = translate(input);
            Files.writeString(Paths.get("output.py"), pythonCode);
            System.out.println("Translation complete! Output written to output.py");
        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        }
    }
}
