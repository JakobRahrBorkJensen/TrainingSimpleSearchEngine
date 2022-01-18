package search.search_strategy;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Contains the NONE search strategy, meaning each matching line must contain NONE of the query words.
 * As the design of the Template pattern (see {@link SearchStrategy} expects to receive matching lines,
 * this strategy can only point out the ones that contains query words, not the ones that don't.
 * To overcome this, the number of lines is passed in the constructor, allowing a full set of lines to be constructed,
 * from which the ANY strategy can be used to collect a set of lines, that should be removed from the full set.
 */
public class NoneSearchStrategy extends SearchStrategy {
    private Set<Integer> setOfAllLines;

    public NoneSearchStrategy(int numberOfLinesInData) {
        initSetOfAllLines(numberOfLinesInData);
    }

    private void initSetOfAllLines(int numberOfLinesInData) {
        setOfAllLines = new HashSet<>();
        for (int i = 0; i < numberOfLinesInData; i++) {
            setOfAllLines.add(i);
        }
    }

    @Override
    public Set<Integer> search(Map<String, Set<Integer>> invertedIndex, String[] queryWords) {
        // Retrieve all "contaminated" lines by using the ANY strategy
        var contaminatedLines = new AnySearchStrategy().search(invertedIndex, queryWords);
        // Remove them from set of all lines
        setOfAllLines.removeAll(contaminatedLines);
        return setOfAllLines;
    }
}
