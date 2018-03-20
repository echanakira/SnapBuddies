import java.io.*;
import java.util.*;

public class Buddy {
	
	private String name;
	//private ArrayList<Buddy> prevBuds = new ArrayList<Buddy>();
	private ArrayList<Buddy> currBuds;
	//private ArrayList<Buddy> totalPrev = new ArrayList<Buddy>();
	private ArrayList<Buddy> buddiesLeft;
	private ArrayList<Buddy> pledgesLeft;
	private ArrayList<Buddy> allPreviousBuds;
	private int pairableCount;
	private String status;
	private int pledgeCount;

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
	
	public Buddy(String name, String status ) {
		this.name = name;
		this.status = status;
		if(status.equals("Pledge")) {
			pledgesLeft = new ArrayList<Buddy>();
			this.pledgeCount = 2;
		}
		this.pairableCount = 6;
		this.currBuds = new ArrayList<Buddy>();
		this.buddiesLeft = new ArrayList<Buddy>();
		this.allPreviousBuds = new ArrayList<Buddy>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public int getPairableCount() {
		return pairableCount;
	}
	
	public int getPledgeCount() {
		return pledgeCount;
	}
	/* Retrieves previous buddies */
	/*public ArrayList<Buddy> getPrevBuds() {
		return prevBuds;
	}*/

	/* Retrieves previous buddies */
	public void printBuds() {
		for(Buddy b : currBuds) {
			System.out.print(b.getName() + "\t");
		}
	}
	
	public ArrayList<Buddy> getCurrBuds() {
		return currBuds;
	}
	

	public ArrayList<Buddy> getAllPreviousBuds() {
		return allPreviousBuds;
	}
	
	/* Adds all of the current buddies to previous buddies */
	/*public void setPrevBuds(ArrayList<Buddy> currBuds) {
		this.prevBuds.addAll(currBuds);
	}*/

	/* Retrieves the buddies remaining */
	public ArrayList<Buddy> getBuddiesLeft() {
		return buddiesLeft;
	}
	
	public ArrayList<Buddy> getPledgesLeft() {
		return pledgesLeft;
	}

	public Boolean removeBud(int index) {
		if(buddiesLeft.remove(index) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public Boolean updatePairCount(int val) {
		pairableCount += val;
		return true;
	}
	
	/* Removes current buddies from the buddies remaining */
	public void shuffleBuddiesLeft() {
		Collections.shuffle(buddiesLeft);
	}
	
	public void shufflePledgesLeft() {
		Collections.shuffle(pledgesLeft);
	}
	
	public void addToBuddiesLeft(Buddy b) {
		this.buddiesLeft.add(b);
	}
	
	public void addToPledgesLeft(Buddy b) {
		this.pledgesLeft.add(b);
	}
	
	public void addToAllPreviousBuds(Buddy b) {
		this.allPreviousBuds.add(b);
	}


	/* Updates buddy count */
	public void updateBuddy(int buddyCount) {
		this.pairableCount += buddyCount;
	}

	
	/* Adds new buddy to the list of current buddies  */
	public boolean add(Buddy b) {
		if(buddiesLeft.contains(b)) {
			if(this.status.equals("Pledge") && b.getStatus().equals("Pledge")) {
				 if(pledgeCount != 0) {
					 pledgesLeft.remove(b);
					 pledgeCount--;
					 currBuds.add(b);
					 buddiesLeft.remove(b);
					this.pairableCount--;
					return true;
				 }
			}
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
	
	
	public String toString() {
		if(status.equals("Pledge")) {
			return this.name + ": count = " + this.pairableCount + "| pledgeCount = " + this.pledgeCount;
		}else {
			return this.name + ": count = " + this.pairableCount;

		}
	}
	
	
}
