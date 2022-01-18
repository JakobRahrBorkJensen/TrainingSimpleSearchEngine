package search.menu_operations;

/**
 * Additional menu option strategy to handle cases with non-implemented options.
 */
public class IncorrectOptionMenuOperation implements MenuOperation {

    @Override
    public void execute() {
        System.out.println("Incorrect option! Try again.");
    }
}
