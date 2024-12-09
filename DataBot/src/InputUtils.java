import java.text.Normalizer;
import java.util.Scanner;

public class InputUtils {
    public static String getInput(Scanner scan) {
        if (scan.hasNextLine()) {
            String input = scan.nextLine().toLowerCase().trim();
            return removeAccents(input);
        }
        return ""; // Retorna uma string vazia se não houver entrada
    }

    public static String getInputMudanca(Scanner scan) {
        if (scan.hasNextLine()) {
            String input = scan.nextLine().toLowerCase().trim();
            return input;
        }
        return ""; // Retorna uma string vazia se não houver entrada
    }

    public static String removeAccents(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                         .replaceAll("\\p{M}", "");
    }
}