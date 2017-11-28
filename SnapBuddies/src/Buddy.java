import java.io.*;
import java.util.*;

public class Buddy {
	
	private String name;
	private ArrayList<Buddy> prevBuds;
	private ArrayList<Buddy> currBuds;
	private ArrayList<Buddy> totalPrev;
	private ArrayList<Buddy> buddiesLeft;
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
	public Buddy(String name, ArrayList<Buddy> previousBuds, 
			ArrayList<Buddy> currBuds, ArrayList<Buddy> totalPrevious, 
			ArrayList<Buddy> buddiesLeft, int buddyCount) {
		name = this.name;
		previousBuds = this.prevBuds;
		currBuds = this.currBuds;
		totalPrevious = this.totalPrev;
		buddiesLeft = this.buddiesLeft;
		buddyCount = this.buddyCount;
		
	}
	
	
	/* Retrieves previous buddies */
	public ArrayList<Buddy> getPrevBuds() {
		return prevBuds;
	}

	/* Adds all of the current buddies to previous buddies */
	public void setPrevBuds(ArrayList<Buddy> currBuds) {
		this.prevBuds.addAll(currBuds);
	}

	/* Retrieves the buddies remaining */
	public ArrayList<Buddy> getBuddiesLeft() {
		return buddiesLeft;
	}

	/* Removes current buddies from the buddies remaining */
	public void setBuddiesLeft(ArrayList<Buddy> currBuddies) {
		this.buddiesLeft.removeAll(currBuddies);
	}
	
	public int getBuddyCount() {
		return buddyCount;
	}

	/* Updates buddy count */
	public void updateBuddy(int buddyCount) {
		this.buddyCount += buddyCount;
	}

	
	/* Adds new buddy to the list of remaining buddies  */
	public boolean add() {
		if(buddiesLeft.contains(this)) {
			return false;
		}else {
			return buddiesLeft.add(this);
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
	
	
	public static void main(String[] args) throws IOException  {
		String line;
		PrintStream stdout = System.out;
		Scanner scan = new Scanner(new File("C:\\Users\\Elijah\\eclipse-workspace\\SnapBuddies\\src\\buddies.txt"));
		System.setOut(new PrintStream("C:\\Users\\Elijah\\eclipse-workspace\\SnapBuddies\\src\\output.txt"));
		do {
		line = scan.nextLine();
		System.out.println(line);
		}while(scan.hasNextLine());
		System.setOut(stdout);
	}
}
