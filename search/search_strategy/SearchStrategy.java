package search.search_strategy;

import java.util.Map;
import java.util.Set;

/**
 * Base of the Template method used for all search strategies.
 */
public abstract class SearchStrategy {
    public abstract Set<Integer> search(
            Map<String, Set<Integer>> invertedIndex,
            String[] queryWords);
}
