package state;

import java.util.HashSet;

public class Stopped implements State {

    private RemoteDevice remote;

    public Stopped(RemoteDevice remote) {
        this.remote = remote;
    }

    public boolean pressPlay() {
        System.out.println("The player is now playing.");
        remote.setState(remote.getPlayingState());
        remote.setPosition(remote.getPosition() + 1);

        return true;
    }

    public boolean pressPause() {
        System.out.println("The player is now paused.");
        remote.setState(remote.getPausedState());

        return true;
    }

    public boolean pressStop() {
        System.out.println("The player is already stopped.");

        return false;
    }
    
    public boolean pressRewind() {
        System.out.println("The media is rewind.");
        remote.setPosition(0);    
        
        return false;
    }
    
    public boolean pressLock() {
        System.out.println("Locking Remote.");
        remote.setState(remote.getLockState());
        ((Lock) remote.getLockState()).setBeforeLockState(this);
        
        return true;
    }

}
