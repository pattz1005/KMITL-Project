package lab7.State;


import lab7.State.RemoteDevice;

/**
 * Stopped.java
 *
 * This file represents the Stopped state.
 */

public class Stopped implements State {
	private RemoteDevice remote;
	
	public Stopped(RemoteDevice remote) {
		this.remote = remote;
	}

	// the Play button is pressed
	public boolean pressPlay() {
		System.out.println("The player is now playing.");
		remote.setState(remote.getPlayingState());
		remote.setPosition(remote.getPosition()+1);
		
		return true;
	}

	// the Paused button is pressed
	public boolean pressPause() {
		System.out.println("The player is now paused.");
		remote.setState(remote.getPausedState());
		
		return true;
	}

	// the  Stop button is pressed
	public boolean pressStop() {
		System.out.println("The player is already stopped.");
		
		return false;
	}

        
        public boolean pressRewind() {
                System.out.println("rewinding...");
                remote.setPosition(0);
                remote.setState(remote.getRewoundState());
                remote.pressPlay();
                return true;
        }
        
        public boolean pressLock(){
                System.out.println("System is now locked");
                remote.setState(remote.getLockedState());
                return true;
        }

}
