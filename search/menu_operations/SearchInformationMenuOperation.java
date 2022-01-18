package search.menu_operations;

import search.search_strategy.*;
import search.utils.PrintUtils;

import java.util.*;

/**
 * Search in the data
 */
public class SearchInformationMenuOperation implements MenuOperation {
    private static final String NOT_FOUND = "Not found";

    private final Map<String, Set<Integer>> invertedIndex;
    private final List<String[]> data;
    private final Scanner scanner;

    public SearchInformationMenuOperation(Map<String, Set<Integer>> invertedIndex, List<String[]> data, Scanner scanner) {
        this.invertedIndex = invertedIndex;
        this.data = data;
        this.scanner = scanner;
    }

    /**
     * Choose a search strategy (implemented according to Template pattern),
     * then execute the search and finally print matching lines
     */
    @Override
    public void execute() {
        // Get chosen search strategy
        SearchStrategies searchStrategy = receiveSearchStrategy();
        SearchStrategy chosenStrategy = instantiateStrategy(searchStrategy);

        // Get query words
        var queryWords = receiveQuery();

        // Execute template method
        var matchingLines = chosenStrategy.search(invertedIndex, queryWords);

        // Print resulting matching lines
        printMatchingLines(matchingLines);
    }

    /**
     * Receive and convert search strategy to proper enum value to ensure valid option was chosen
     */
    private SearchStrategies receiveSearchStrategy() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        var searchStrategy = SearchStrategies.valueOf(scanner.nextLine().toUpperCase().trim());
        System.out.println();
        return searchStrategy;
    }

    /**
     * Translates search strategy into corresponding strategy class.
     * @return new instance of corresponding strategy class.
     */
    private SearchStrategy instantiateStrategy(SearchStrategies searchStrategy) {
        switch (searchStrategy) {
            case ALL:
                return new AllSearchStrategy();
            case ANY:
                return new AnySearchStrategy();
            case NONE:
                return new NoneSearchStrategy(data.size());
            default:
                throw new RuntimeException("Unknown search strategy");
        }
    }

    private String[] receiveQuery() {
        return scanner.nextLine().trim().toLowerCase().split(" ");
    }

    /**
     * Prints the lines to the console
     * @param matchingLines indexes of matching line numbers as a set of integers
     */
    private void printMatchingLines(Set<Integer> matchingLines) {
        if (null == matchingLines) {
            System.out.println(NOT_FOUND);
            return;
        }

        matchingLines.stream()
                .map(data::get)
                .map(PrintUtils::getDisplayFormat)
                .forEach(System.out::println);
    }
}
