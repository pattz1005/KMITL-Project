//done
package Store;

import java.io.IOException;

public interface Command {
    void execute(Inventory inventory) throws IOException, ClassNotFoundException;
}
