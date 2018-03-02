import java.io.*;
import java.util.*;

public class Buddy {
	
	private String name;
	//private ArrayList<Buddy> prevBuds = new ArrayList<Buddy>();
	private ArrayList<Buddy> currBuds;
	//private ArrayList<Buddy> totalPrev = new ArrayList<Buddy>();
	private ArrayList<Buddy> buddiesLeft;
	private int pairableCount = 4;

	/**
	 *  A buddy is an individual
	 *  @param name
	 *  @param prevBuds			Buddies from previous session
	 *  @param currBuds			Current list of buddies
	 *  @param totalPrev		List of total past buddies
	 *  @param budsLeft 		List of total buddies remaining
	 *  @param buddyCount		Number of buddies current object can be paired with
	 */
	/*public Buddy(String name, ArrayList<Buddy> previousBuds, 
			ArrayList<Buddy> currBuds, ArrayList<Buddy> totalPrevious, 
			ArrayList<Buddy> buddiesLeft, int buddyCount) {
		this.name = name;
		this.prevBuds = previousBuds;
		this.currBuds = currBuds;
		this.totalPrev = totalPrevious;
		this.buddiesLeft = buddiesLeft;
		this.pairableCount = buddyCount;
		
	}*/
	
	public Buddy(String name) {
		this.name = name;
		this.currBuds = new ArrayList<Buddy>();
		this.buddiesLeft = new ArrayList<Buddy>();
		this.pairableCount = 2;
	}
	
	public String getName() {
		return this.name;
	}
	
	/* Retrieves previous buddies */
	/*public ArrayList<Buddy> getPrevBuds() {
		return prevBuds;
	}*/

	/* Retrieves previous buddies */
	public void getCurrentBuds() {
		for(Buddy b : currBuds) {
			System.out.print(" " + b.getName() + "| \t");
		}
	}

	
	/* Adds all of the current buddies to previous buddies */
	/*public void setPrevBuds(ArrayList<Buddy> currBuds) {
		this.prevBuds.addAll(currBuds);
	}*/

	/* Retrieves the buddies remaining */
	public ArrayList<Buddy> getBuddiesLeft() {
		return buddiesLeft;
	}

	/* Removes current buddies from the buddies remaining */
	public void setBuddiesLeft(ArrayList<Buddy> currBuddies) {
		this.buddiesLeft.removeAll(currBuddies);
	}
	
	public int getPairableCount() {
		return pairableCount;
	}
	
	public void addToBuddiesLeft(Buddy b) {
		this.buddiesLeft.add(b);
	}

	/* Updates buddy count */
	public void updateBuddy(int buddyCount) {
		this.pairableCount += buddyCount;
	}

	
	/* Adds new buddy to the list of current buddies  */
	public boolean add(Buddy b) {
		if(buddiesLeft.contains(b)) {
			currBuds.add(b);
			buddiesLeft.remove(b);
			this.pairableCount--;
			return true;
		}else {
			return false;
		}
	}
	
	/** Moves a Buddy to a separate list of Buddies
	 * @param dest				List Buddy is moving to
	 */
	public boolean moveTo( ArrayList<Buddy> dest) {
		if(!dest.contains(this)) {
			return dest.add(this);
		}else {
			return false;
		}
	}
	
	
	
}
