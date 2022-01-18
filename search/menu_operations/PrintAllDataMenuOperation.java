package search.menu_operations;

import search.utils.PrintUtils;

import java.util.List;

/**
 * Prints all data as lines in the same format they were received in.
 */
public class PrintAllDataMenuOperation implements MenuOperation {
    private final List<String[]> data;

    public PrintAllDataMenuOperation(List<String[]> data) {
        this.data = data;
    }

    @Override
    public void execute() {
        data.stream()
                .map(PrintUtils::getDisplayFormat)
                .forEach(System.out::println);
    }
}
