package state;

public class Paused implements State {

    private RemoteDevice remote;

    public Paused(RemoteDevice remote) {
        this.remote = remote;
    }

    public boolean pressPlay() {
        System.out.println("The player is now playing.");
        remote.setState(remote.getPlayingState());
        remote.setPosition(remote.getPosition() + 1);

        return true;
    }

    public boolean pressPause() {
        System.out.println("The player is already paused.");

        return false;
    }

    public boolean pressStop() {
        System.out.println("The player is now stopped.");
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
