package search;

import search.menu_operations.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Acts as flow controller and as the holder of data.
 * Ideally data would be split out into a repository class or database. However, this was not the focus in this exercise.
 */
public class SearchEngine {
    private static final int EXIT = 0;

    private final Scanner scanner;
    private final List<String[]> data = new ArrayList<>();
    private final Map<String, Set<Integer>> invertedIndex = new HashMap<>();

    public SearchEngine() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Load data from specified file into the data attribute
     *
     * Example of data in file:
     * "firstname lastname email"
     *
     * @param filePath Path of file containing data.
     */
    public void initData(String filePath) {
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                data.add(fileScanner.nextLine().split(" "));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading datafile", e);
        }

        initInvertedIndex();
    }

    /**
     * Add words to inverted index {word, line number} as lowercase
     */
    private void initInvertedIndex() {
        for (int line = 0; line < data.size(); line++) {
            for (String word : data.get(line)) {
                addToInvertedIndex(word.toLowerCase(), line);
            }
        }
    }

    private void addToInvertedIndex(String word, Integer lineNumber) {
        var setOfLines = invertedIndex.getOrDefault(word, new HashSet<>());
        setOfLines.add(lineNumber);
        invertedIndex.put(word, setOfLines);
    }

    /**
     * Flow controller method, that handles the menu.
     * Implemented using the Strategy Pattern, so that this method should not change when adding new menu options
     */
    public void run() {
        // Init
        MenuOperationContainer menuOperator = new MenuOperationContainer();
        var actions = getActions(data);

        // Perform menu actions until exit
        while (true) {
            showMenu();
            int action = Integer.parseInt(scanner.nextLine());
            System.out.println();

            if (action == EXIT) {
                break;
            }

            menuOperator.setMenuOperation(
                    actions.getOrDefault(action, new IncorrectOptionMenuOperation()));
            menuOperator.performMenuOperation();
            System.out.println();
        }

        System.out.println("Bye!");
    }

    /**
     * Map containing all menu options implemented according to the Strategy Pattern.
     * Each new menu option should be added to this map with a corresponding number, as the one to be shown in the menu.
     * @return Returns a new instance of the appropriate menu option
     */
    private Map<Integer, MenuOperation> getActions(List<String[]> data) {
        Map<Integer, MenuOperation> actions = new HashMap<>();
        actions.put(1, new SearchInformationMenuOperation(invertedIndex, data, scanner));
        actions.put(2, new PrintAllDataMenuOperation(data));
        return actions;
    }

    private void showMenu() {
        System.out.println("1. Search information.");
        System.out.println("2. Print all data.");
        System.out.println("0. Exit.");
    }
}
