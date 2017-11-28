import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;


public class BuddyNetwork {

	private int week;
	private Random randomGenerator;
	private ArrayList<Buddy> network = new ArrayList<Buddy>();

	/*
	 * @param current week of buddies
	 */
	public BuddyNetwork(int week) {
		this.week = week;
		randomGenerator = new Random();
	}

	/* Init sets file to stdin and then parses the file. 
	 * Each line in the file is converted into a Buddy Object.
	 * @param filePath		The file which contains the bdudies to be initialized
	 */
	public boolean init(File filePath) throws FileNotFoundException {
		String line;
		PrintStream stdout = System.out;
		Scanner scan = new Scanner(filePath);							//Sets stdin to file
		System.setOut(new PrintStream(
				"C:\\Users\\Elijah\\eclipse-workspace\\SnapBuddies\\src\\output.txt"));		//Sets stdout to output.txt file

		/* Read from stdin and then add the buddy to the network*/
		do {
			line = scan.nextLine();										
			if(this.containsBuddy(new Buddy(line))) {		
				network.add(new Buddy(line));			
			}
		} while (scan.hasNextLine());
		
		for(Buddy b: network) {
			System.out.println(b.getName());
		}
		System.setOut(stdout);											//clear output.txt
		return true;
	}
	
	/* Pairs all buddies within the network */
	public boolean pairAll() {
		for(Buddy b : network) {
			while(b.getPairableCount() != 0) {
				int index = randomGenerator.nextInt(b.getBuddiesLeft().size());
				this.pair(b, b.getBuddiesLeft().get(index));
			}
		}
		return true;
	}
	
	/* Pairs two buddies together 
	 * @param bud1		first buddy being paired
	 * @param bud2		second buddy being paired
	 */
	public boolean pair(Buddy bud1, Buddy bud2) {
		if(bud2.getBuddiesLeft().contains(bud2)) {
			if(bud1.getPairableCount() != 0 && bud2.getPairableCount() != 0) {
				bud1.add(bud2);
				bud2.add(bud1);
				return true;
			}
		}
		return false;
	}
	
	
	/* Searches through BuddyNetwork to see if a buddy with a similar name is present */
	public boolean containsBuddy(Buddy bud) {
		for(Buddy b: this.network) {
			if(b.getName().equals(bud.getName())) {
				return false;
			}
		}
		return true;
	}
	
	
}
