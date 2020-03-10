package sample;

public class demo {
    public static void main(String[] args) throws MatchNotFoundException {
        InventoryInterface i = new Inventory();
        CareTaker c;
        Movie mov1 = new Movie("Kop1", 3, 10);
        Movie mov2 = new Movie("Kop2", 4, 12);
        Movie mov3 = new Movie("Kop3", 5, 2);
        Movie mov4 = new Movie("Kop4", 6, 4);
        i.addMovie(mov1);
        i.addMovie(mov2);
        i.addMovie(mov3);
        i.addMovie(mov4);
        i.addCopy("Kop1", 3);
    }
}
