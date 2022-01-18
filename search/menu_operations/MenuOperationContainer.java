package search.menu_operations;

/**
 * Container for the menu options Strategy
 */
public class MenuOperationContainer {
    private MenuOperation menuOperation;

    public void setMenuOperation(MenuOperation menuOperation) {
        this.menuOperation = menuOperation;
    }

    public void performMenuOperation() {
        menuOperation.execute();
    }
}
