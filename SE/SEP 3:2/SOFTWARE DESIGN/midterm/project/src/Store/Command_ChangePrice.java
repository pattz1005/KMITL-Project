//done
package Store;

import java.io.IOException;
import java.io.Serializable;

public class Command_ChangePrice implements Command, Serializable {
    private int id;
    private double newPrice;

    public Command_ChangePrice(int id, double newPrice) {
        this.id = id;
        this.newPrice = newPrice;
    }

    @Override
    public void execute(Inventory inventory) throws IOException, ClassNotFoundException {
        inventory.changeBookPrice(id, newPrice);
    }
}
