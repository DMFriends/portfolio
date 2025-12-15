import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LoadTextFile2
{
    public static List<String> loadWords(String filePath)
    {
        List<String> words = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                words.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        return words;
    }

    public static void main(String[] args) {
        List<String> words = loadWords("words.txt");

        for (String word : words) {
            System.out.println(word);
        }
    }
}