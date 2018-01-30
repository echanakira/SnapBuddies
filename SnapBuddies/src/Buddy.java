import java.io.*;
import java.util.HashMap;


public class Buddy {

	private String name;
	private HashMap<Buddy, Boolean> prevBuds = new ArrayList<Buddy>();
	private ArrayList<Buddy> currBuds = new ArrayList<Buddy>();
	private HashMap<Buddy, Boolean> totalPrev = new ArrayList<Buddy>();
	private ArrayList<Buddy> buddiesLeft = new ArrayList<Buddy>();
	private int buddyCount;

	/**
	 *  A buddy is an individual
	 *  @param name
	 *  @param prevBuds			Buddies from previous session
	 *  @param currBuds			Current list of buddies
	 *  @param totalPrev		List of total past buddies
	 *  @param budsLeft 		List of total buddies remaining
	 *  @param buddyCoutn		Number of buddies current object can be paired with
	 */
	public Buddy(String name, HashMap<Buddy, Boolean> previousBuds,
			ArrayList<Buddy> currBuds, HashMap<Buddy, Boolean> totalPrevious,
			ArrayList<Buddy> buddiesLeft, int buddyCount) {
		name = this.name;
		previousBuds = this.prevBuds;
		currBuds = this.currBuds;
		totalPrevious = this.totalPrev;
		buddiesLeft = this.buddiesLeft;
		buddyCount = this.buddyCount;

	}

	public Buddy(String name) {
		this.name = name;
		this.buddyCount = 4;
	}

	public String getName() {
		return this.name;
	}

	/* Retrieves previous buddies */
	public HashMap<Buddy, Boolean> getPrevBuds() {
		return prevBuds;
	}

	/* Retrieves the buddies remaining */
	public ArrayList<Buddy> getBuddiesLeft() {
		return buddiesLeft;
	}

	public ArrayList<Buddy> totalPrev;

	/* Adds all of the current buddies to previous buddies */
	//UPDATE
	public void setPrevBuds(HashMap<Buddy, Boolean> currBuds) {
		//this.prevBuds.addAll(currBuds);
		throw new ImplementMethodException("This method needs to be implemented.");
	}


	/* Removes current buddies from the buddies remaining */
	public void setBuddiesLeft(ArrayList<Buddy> currBuddies) {
		//this.buddiesLeft.removeAll(currBuddies);
		throw new ImplementMethodException("This method needs to be implemented.");
	}

	public int getBuddyCount() {
		return buddyCount;
	}

	/* Updates buddy count */
	public void updateBuddy(int buddyCount) {
		this.buddyCount += buddyCount;
	}



	/* Adds new buddy to the list of remaining buddies  */
	public boolean addToRemaining() {
		if(buddiesLeft.containsKey(this)) {
			return false;
		}else {
			return buddiesLeft.put(this, true);
		}
	}

	/** Moves a Buddy to a separate list of Buddies
	 * @param dest				List Buddy is moving to
	 */
	public boolean moveTo( HashMap<Buddy, Boolean> dest) {
		if(!dest.containsKey(this)) {
			dest.put(this, true);
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Create method that matches matches two buddies.
	 * When to buddies match, their buddycount is decremented and
	 * They remove one another from buddiesLeft, add each other to currentBuddies.
	 */
	 //UPDATE: Before a new set of buddies is added clear current buddies and add to prev
	 public boolean match(Buddy bud){
		 throw new ImplementMethodException("This method needs to be implemented.");
		 buddiesLeft.remove(bud);
		 bud.buddiesLeft.remove(this);
		 currentBuddies.add(bud);
		 bud.currentBuddies.add(this);
		 buddyCount--;
		 bud.buddyCount--;
		 return true;
	 }

}
