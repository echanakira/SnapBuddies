import java.io.File;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException  {
		BuddyNetwork net = new BuddyNetwork(3);
		System.out.println("Initializing Network\n");
		File path = new File("C:\\Users\\Elijah\\git\\SnapBuddies\\SnapBuddies\\src\\buddies.txt");
		net.init(path);
		System.out.println("Pairing Brothers \n");
		net.pairAll();
		printAllPairs(net);
	}
	
	public static void printAllPairs(BuddyNetwork net) {
		for(Buddy b : net.getBuddies()) {
			System.out.print(b.getName() +": \t");
			b.getCurrentBuds();
			System.out.println();
		}
	}

}
