package state;

public class Lock implements State {
    
    private RemoteDevice remote;
    private State beforeLockState;
    
    public Lock(RemoteDevice remote) {
        this.remote = remote;
        beforeLockState = null;
    }

    public boolean pressPlay() {
        System.out.println("The remote is locked.");
        
        return false;
    }

    public boolean pressPause() {
        System.out.println("The remote is locked.");
        
        return false;
    }

    public boolean pressStop() {
        System.out.println("The remote is locked.");
        
        return false;
    }

    public boolean pressRewind() {
        System.out.println("The remote is locked.");
        
        return false;
    }

    public boolean pressLock() {
        System.out.println("Unlocking remote");
        remote.setState(beforeLockState);
        
        return true;
    }
    
    public void setBeforeLockState(State beforeLockState) {
        this.beforeLockState = beforeLockState;
    }
    
}
