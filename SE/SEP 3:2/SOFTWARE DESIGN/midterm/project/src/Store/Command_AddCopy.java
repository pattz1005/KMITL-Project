//done
package Store;

import java.io.IOException;
import java.io.Serializable;

public class Command_AddCopy implements Command, Serializable {
    private int id, quantity;

    public Command_AddCopy(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    @Override
    public void execute(Inventory inventory) throws IOException, ClassNotFoundException {
        inventory.addCopy(id, quantity);
    }
}
