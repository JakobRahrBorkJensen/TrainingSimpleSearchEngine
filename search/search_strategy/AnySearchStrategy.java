package search.search_strategy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Contains the ANY search strategy, meaning each matching line should just contain any one (or multiple)
 * of the query words
 */
public class AnySearchStrategy extends SearchStrategy {
    @Override
    public Set<Integer> search(Map<String, Set<Integer>> invertedIndex, String[] queryWords) {
        return Arrays.stream(queryWords)
                .map(invertedIndex::get)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
