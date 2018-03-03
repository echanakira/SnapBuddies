import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Main {
	
	public static void main(String[] args) throws IOException  {
		PrintStream stdout = System.out;
		BuddyNetwork net = new BuddyNetwork(3);
		File path = new File("C:\\Users\\Elijah\\git\\SnapBuddies\\SnapBuddies\\src\\buddies.txt");
		System.setOut(new PrintStream(
				"C:\\Users\\Elijah\\git\\SnapBuddies\\SnapBuddies\\src\\output.txt"));		//Sets stdout to output.txt file
		net.init(path);
		net.pairPledges();
		net.pairAll();
		printAllPairs(net);
	//	System.setOut(stdout);											//clear output.txt
	}
	
	public static void printAllPairs(BuddyNetwork net) {
		for(Buddy b : net.getBuddies()) {
			System.out.print(b.getName() +": \t|");
			b.getCurrentBuds();
			System.out.println();
		}
	}

}
