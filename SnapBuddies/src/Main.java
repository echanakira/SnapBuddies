import java.io.File;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException  {
		BuddyNetwork net = new BuddyNetwork(3);
		File path = new File("C:\\Users\\Elijah\\eclipse-workspace\\SnapBuddies\\src\\buddies.txt");
		net.init(path);
	}

}
