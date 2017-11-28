import java.io.*;
import java.util.*;

public class BuddyNetwork {

	private int week;
	private ArrayList<Buddy> network = new ArrayList<Buddy>();

	/*
	 * @param current week of buddies
	 */
	public BuddyNetwork(int week) {
		this.week = week;
	}

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
	
	public static void main(String[] args) throws IOException  {
		BuddyNetwork net = new BuddyNetwork(3);
		File path = new File("C:\\Users\\Elijah\\eclipse-workspace\\SnapBuddies\\src\\buddies.txt");
		net.init(path);
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
