package breakfast;

public abstract class EggRecipe {

    public final void make(int n) {
        crackEggs(n);
        prepareEggs();
        cook();
        if (isAddSaltPepper()) {
            addSaltPepper();
        }
        serve();
    }

    public void crackEggs(int n) {
        System.out.println("Cracking " + n + " eggs");
    }

    public abstract void prepareEggs();

    public abstract void cook();

    public boolean isAddSaltPepper() {
        return true;
    }

    public void addSaltPepper() {
        System.out.println("Adding Salt and Pepper");
    }

    public void serve() {
        System.out.println("Putting the egg on the plate");
    }
}
