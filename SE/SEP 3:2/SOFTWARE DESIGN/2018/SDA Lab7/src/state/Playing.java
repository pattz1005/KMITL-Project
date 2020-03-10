package state;

public class Playing implements State {

    private RemoteDevice remote;

    public Playing(RemoteDevice remote) {
        this.remote = remote;
    }

    public boolean pressPlay() {
        System.out.println("The player is already playing.");

        return false;
    }

    public boolean pressPause() {
        System.out.println("The player is paused.");
        remote.setState(remote.getPausedState());

        return true;
    }

    public boolean pressStop() {
        System.out.println("The player is stopped.");
        remote.setState(remote.getStoppedState());
        remote.setPosition(0);

        return true;
    }
    
    public boolean pressRewind() {
        System.out.println("Pressed rewind. Nothing happens.");
        
        return false;
    }
    
    public boolean pressLock() {
        System.out.println("Locking Remote.");
        remote.setState(remote.getLockState());
        ((Lock) remote.getLockState()).setBeforeLockState(this);
        
        return true;
    }

}
