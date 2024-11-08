import java.util.Scanner;

public class InputUtils {
    public static String getInput(Scanner scan) {
        if (scan.hasNextLine()) {
            return scan.nextLine().toLowerCase().trim();
        }
        return ""; // Retorna uma string vazia se não houver entrada
    }
}
