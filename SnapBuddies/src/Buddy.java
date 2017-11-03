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
	
	/* Adds new buddy to the list of remaining buddies */
	public boolean addBuddy(Buddy bud) {
		if( buddiesLeft.contains(bud)) {
			return false;
		}else {
			return buddiesLeft.add(bud);
		}
	}
	
	public boolean moveTo(Buddy bud, ArrayList<Buddy> dest) {
		if(!dest.contains(bud)) {
			return dest.add(bud);
		}else {
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException  {
		Scanner scan = new Scanner(new File("C:\\Users\\Elijah\\eclipse-workspace\\SnapBuddies\\src\\buddies.txt"));
		System.setOut(new PrintStream("C:\\Users\\Elijah\\eclipse-workspace\\SnapBuddies\\src\\output.txt"));
		String line = scan.nextLine();
		System.out.println(line);
		
	}
}
