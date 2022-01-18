package search.search_strategy;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Contains the ALL search strategy, meaning each matching line must include all the query words.
 */
public class AllSearchStrategy extends SearchStrategy {
    @Override
    public Set<Integer> search(Map<String, Set<Integer>> invertedIndex, String[] queryWords) {
        // Add initial set for first query word
        var setOfLineNumbers = invertedIndex.get(queryWords[0]);

        if (null == setOfLineNumbers) {
            return new HashSet<>();
        }

        // Go through rest of query words, and only retain lines that are matching for all words
        if (queryWords.length > 1) {
            for (int i = 1; i < queryWords.length; i++) {
                var matchingLines = invertedIndex.get(queryWords[i]);
                setOfLineNumbers.retainAll(matchingLines);
            }
        }

        return setOfLineNumbers;
    }
}
