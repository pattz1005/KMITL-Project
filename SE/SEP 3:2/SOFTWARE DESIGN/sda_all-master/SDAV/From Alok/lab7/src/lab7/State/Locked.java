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
public class Locked implements State {
	private RemoteDevice remote;
	
	public Locked(RemoteDevice remote) {
		this.remote = remote;
	}

	// the Play button is pressed
	public boolean pressPlay() {
		System.out.println("Can't play while locked.");
		
		return false;
	}

	// the Pause button is pressed
	public boolean pressPause() {
		System.out.println("Can't pause while locked.");
		
		return false;
	}


	// the Stop button is pressed
	public boolean pressStop() {
		System.out.println("Can't stop while locked.");
		
		return false;
	}

        
        public boolean pressRewind() {
                System.out.println("Can't pause while locked.");
		
		return false;
        }
        
        public boolean pressLock(){
                System.out.println("System is now unlocked");
                remote.setState(remote.getStoppedState());
                return true;
        }
}