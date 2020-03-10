package lab7.State;


public class TestDrive {
	public static void main(String[] args) {
		
		// what about a singleton here???
		RemoteDevice remote = new RemoteDevice();
		
		remote.pressPlay();
		
		remote.pressPause();
		
		remote.pressStop();
		
                remote.pressRewind();
                
                remote.pressPlay();
                
		remote.pressPlay();
                
                remote.pressLock();
                
		remote.pressPause();
                
                remote.pressLock();
                
                remote.pressPause();

	}

}
