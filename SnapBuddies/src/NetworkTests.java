import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;

import org.junit.jupiter.api.Test;

class NetworkTests {
	BuddyNetwork initNet = new BuddyNetwork(0);
	BuddyNetwork n = new BuddyNetwork(0);
	File path = new File("C:\\Users\\Elijah\\eclipse-workspace\\SnapBuddies\\src\\buddies.txt");
	
	@Test
	void testInitSuccess() throws FileNotFoundException {
		assertTrue(n.init(path));
	}
	
	@Test
	void testPair() throws FileNotFoundException {
		Buddy b1 = new Buddy("Bud1", "Brother");
		Buddy b2 = new Buddy("Bud2", "Pledge");
		n.init(path);
		System.setOut(System.out);
		
		assertTrue(n.pair(b1, b2));
		assertTrue(b1.getPairableCount()  == 3);
	}

}
