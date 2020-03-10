package state;

public class TestDrive {

    public static void main(String[] args) {

        RemoteDevice remote = new RemoteDevice();

        remote.pressPlay();

        remote.pressPause();

        remote.pressStop();

        remote.pressPlay();
        remote.pressPlay();
        
        remote.pressRewind();
        remote.pressStop();
        remote.pressRewind();
        
        remote.pressPlay();
        remote.pressLock();
        remote.pressPause();
        remote.pressLock();
        remote.pressPlay();
    }
}
