package search;

import java.util.Arrays;
import java.util.List;

/**
 * Acts as initializer, handling command line arguments to prep data if needed.
 */
public class Main {
    public static void main(String[] args) {
        List<String> argList = Arrays.asList(args);
        int dataIndex = argList.indexOf("--data");
        String filePath = dataIndex >= 0 ? argList.get(dataIndex+1) : null;

        SearchEngine engine = new SearchEngine();
        if (null != filePath) {
            engine.initData(filePath);
        }

        engine.run();
    }
}
