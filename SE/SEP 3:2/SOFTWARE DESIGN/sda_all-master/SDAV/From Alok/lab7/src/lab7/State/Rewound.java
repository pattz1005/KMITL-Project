/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7.State;

/**
 *
 * @author alok
 */
public class Rewound implements State {
	private RemoteDevice remote;
	
	public Rewound(RemoteDevice remote) {
            this.remote = remote;
	}

	// the Play button is pressed
	public boolean pressPlay() {
            System.out.println("The player is now playing.");
            remote.setState(remote.getPlayingState());
            return true;
	}

	// the Pause button is pressed
	public boolean pressPause() {
            System.out.println("The player is now paused.");
            remote.setState(remote.getPausedState());
            return true;
	}


	// the Stop button is pressed
	public boolean pressStop() {
            System.out.println("The player is now stopped.");
            remote.setState(remote.getStoppedState());
            return true;
	}
        
        
        public boolean pressRewind(){
            System.out.println("Can't rewind.");
            return false;
        }
        
        public boolean pressLock(){
                System.out.println("System is now locked");
                remote.setState(remote.getLockedState());
                return true;
        }
}
