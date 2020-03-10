// done
package Store;

import java.io.IOException;
import java.io.Serializable;

public class Command_SellBook implements Command, Serializable {
    private int id, quantity;

    public Command_SellBook(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    @Override
    public void execute(Inventory inventory) throws IOException, ClassNotFoundException {
        inventory.sellBook(id, quantity);
    }
}
