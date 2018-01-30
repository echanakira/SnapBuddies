import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

public class BuddyNetwork {

	private int week;
	private Random rand;
	private ArrayList<Buddy> network;

	/*
	 * @param current week of buddies
	 */
	public BuddyNetwork(int week) {
		this.week = week;
		network = new ArrayList<Buddy>();
		ran = new Random();
	}

	public static void main(String[] args) throws IOException  {
		BuddyNetwork net = new BuddyNetwork(3);
		File path = new File("C:\\Users\\Elijah\\eclipse-workspace\\SnapBuddies\\src\\buddies.txt");
		net.init(path);
		connect();
		for(Buddy bud : network){
			System.out.println("Buddy: " + bud.getName() +"Matches = ");
			for(Buddy b : bud.getCurrBuds()){
				System.out.print(b.getName() + "\t");
			}
		}
	}

	/** Receives buddies from STDIN and adds buddies to network list
	 * @param filePath				Location of output
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
		//System.setOut(stdout);											//clear output.txt
		return true;
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

/**
 * Shuffle the network
 */
 public boolean connect(){
	 for(Buddy bud : network){
		 do{
			 int index = rand.nextInt(bud.getBuddiesLeft().size());			//Choose a random number between 0 and size of Network
			 Buddy companion = bud.getBuddiesLeft().get(index);
			 if(!bud.getPrevBuds().containsKey(companion) && !bud.getCurrBuds().containsKey(companion)){
				 bud.match(companion)
			 }
		 }while(bud.getBuddyCount() > 0)
 	 }
	 return true;
 }
