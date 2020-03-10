package memento_example;

import java.util.ArrayList;

class Caretaker {
    private ArrayList<Memento> mementos = new ArrayList<>();

    public void addMemento(Memento m) {
        mementos.add(m);
    }

    public Memento getMemento(int at) {
        return mementos.get(at);
    }
}