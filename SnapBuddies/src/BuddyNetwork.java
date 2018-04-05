import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;


public class BuddyNetwork {
	
	private ArrayList<Buddy> network;
	private ArrayList<Buddy> pNetwork;
	private ArrayList<Buddy> unmatched; 
	private ArrayList<Buddy> pUnmatched; 


	/*
	 * @param current week of buddies
	 */
	public BuddyNetwork(int week) {
		network =  new ArrayList<Buddy>();
		unmatched = new ArrayList<Buddy>();
		pNetwork = new ArrayList<Buddy>();
		pUnmatched = new ArrayList<Buddy>();
	}

	/* Init sets file to stdin and then parses the file. 
	 * Each line in the file is converted into a Buddy Object.
	 * @param filePath		The file which contains the bdudies to be initialized
	 */
	public boolean init(File filePath) throws FileNotFoundException {
		String line;
		String[] arr;
		Scanner scan = new Scanner(filePath);							//Sets stdin to file
		

		/* Read from stdin and then add the buddy to the network*/
		do {
			line = scan.nextLine();	
			arr = line.split("\t");
			Buddy newBud = new Buddy(arr[0].concat("-Snapchat: ").concat(arr[1]), arr[2]);
			if(this.doesNotContainsBuddy(newBud)) {
				updateAll(newBud);					//Adds the new buddy to the list of matchable buddies
				for(Buddy b : network) {			//Adds all the remaining buddies to newBud's buddiesLeft
					if(newBud != b) {
						newBud.addToBuddiesLeft(b);
					}
				}
				if( arr[2].equals("Pledge")) {			//Adds the newBud to the pledge network
					pNetwork.add(newBud);
					pUnmatched.add(newBud);
				}
				network.add(newBud);
				unmatched.add(newBud);
			}
		} while (scan.hasNextLine());
		return true;
	}
	
	public void updateAll(Buddy bud) {
		if(bud.getStatus().equals("Pledge")) {
			for(Buddy pledge : pUnmatched) {
				pledge.addToPledgesLeft(bud);
			}
		}
		for(Buddy b : unmatched) {
			b.addToBuddiesLeft(bud);
		}
	}
	
	/* Pairs all buddies within the network */
	public boolean pairAll() {
		for(Buddy bud1 : network) {
			int index = 0;
			bud1.shuffleBuddiesLeft();
			if(unmatched.size() == 0) {
				break;
			}
			if(bud1.getStatus() == "Pledge") {
				findBuddy(bud1);
			}
			while(bud1.getPairableCount() != 0 && index < bud1.getBuddiesLeft().size()) {
				Buddy bud2 = findBuddy(bud1.getBuddiesLeft().get(index));
				if(!this.pair(bud1, bud2)) {
					index++;
				}
			}
		}
		return true;
	}
	
	public boolean pairPledges(){
		for(Buddy pledge1 : pNetwork) {
			int index = 0;
			pledge1.shufflePledgesLeft();
			if(pUnmatched.size() <=1) {
				break;
			}else {
				while(pledge1.getPledgeCount() != 0) {
					Buddy pledge2 = findPledge(pledge1.getPledgesLeft().get(index));
					if(!this.pair(pledge1, pledge2)) {
						index++;
					}
				}
			}
		}
		return true;
	}
	
	/* Pairs two buddies together 
	 * @param bud1		first buddy being paired
	 * @param bud2		second buddy being paired
	 */
	public boolean pair(Buddy bud1, Buddy bud2) {
		if(bud1.getBuddiesLeft().contains(bud2) && !bud1.getAllPreviousBuds().contains(bud2)) {
			if(bud1.getPairableCount() != 0 && bud2.getPairableCount() != 0) {
				bud1.add(bud2);
				bud2.add(bud1);
				if(bud1.getStatus().equals("Pledge") && bud2.getStatus().equals("Pledge")) {
					if(bud2.getPledgeCount() ==0) {
						if(!pUnmatched.remove(bud2) && bud2.getPairableCount() == 0) {
							unmatched.remove(bud2);
						}
					}
					if(bud1.getPledgeCount() ==0) {
						if(!pUnmatched.remove(bud1) && bud1.getPairableCount() ==0) {
							unmatched.remove(bud1);
						}
					}
				}else {
					if(bud1.getPairableCount() == 0) {
						unmatched.remove(bud1);
					}
					if(bud2.getPairableCount() == 0) {
						unmatched.remove(bud2);
					}
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean reset(File filePath) throws FileNotFoundException {
		String line;
		int lineNum = 1;
		String[] arr;
		Scanner scan = new Scanner(filePath);							//Sets stdin to file
		

		/* Add all to network*/
		do {
			line = scan.nextLine();	
			arr = line.split("\t");
			arr[0] = arr[0].substring(0,arr[0].indexOf("-"));
			Buddy bud = new Buddy(arr[0], "Brother");
			addToNetwork(bud);
			lineNum++;
		}while (lineNum != 57);
		
		do {
			line = scan.nextLine();	
			arr = line.split("\t");
			arr[0] = arr[0].substring(0,arr[0].indexOf("-"));
			Buddy bud = new Buddy(arr[0], "Pledge"); 
			addToNetwork(bud);
			lineNum++;
		}while (lineNum != 83);
			Buddy maggie = new Buddy("Maggie McGuckin", "Pledge");
			addToNetwork(maggie);
		
		
		/* Add to previous */
		Scanner scan2 = new Scanner(filePath);							//Sets stdin to file
		lineNum = 1;
		do {
			line = scan2.nextLine();	
			arr = line.split("\t");
			arr[0] = arr[0].substring(0,arr[0].indexOf("-"));
			Buddy bud = findBuddy(arr[0]);
			savePrev(bud, arr);
			for(Buddy b : network) {
				if(!bud.getAllPreviousBuds().contains(bud)) {
					if(bud.getName() != b.getName()) {
						bud.addToBuddiesLeft(b);
					}				
				}
			}
			lineNum++;
		}while (lineNum != 57);
		
		do {
			line = scan2.nextLine();	
			arr = line.split("\t");
			arr[0] = arr[0].substring(0,arr[0].indexOf("-"));
			Buddy bud = findBuddy(arr[0]);
			savePrev(bud, arr);
			for(Buddy b : network) {
				if(!bud.getAllPreviousBuds().contains(bud)) {
					if(bud.getName() != b.getName()) {
						bud.addToBuddiesLeft(b);
					}
				}
			}
			lineNum++;
		}while (lineNum != 83);
		savePrev(maggie, null);
		for(Buddy b : network) {
			if(!maggie.getAllPreviousBuds().contains(b)) {
				if(maggie.getName() != b.getName()) {
					maggie.addToBuddiesLeft(b);
				}
			}
		}
		return true;
	}
	
	
	
	public boolean addToNetwork(Buddy bud) {
			if(bud.getStatus() == "Brother") {
				network.add(bud);
				unmatched.add(bud);
			}else {
				unmatched.add(bud);
				network.add(bud);
			}
			return true;
	}
	
	public Buddy findBuddy(String name) {
		for( Buddy b : network) {
			if(b.getName().equals(name)) {
				return b;
			}
		}
		return null;
	}
	
	public Boolean savePrev(Buddy bud, String[] arr) {
		if(arr != null) {
		if(bud.getStatus() == "Brother") {
			for(int i = 1; i < 6; i++) {
				bud.addToAllPreviousBuds(findBuddy(arr[i]));
			}
		}else {
			for(int i = 2; i < 6; i++) {
				bud.addToAllPreviousBuds(findBuddy(arr[i]));
			}
		}
		}
		return true;
	}
	
	
	public Boolean updateNetwork(Buddy main, Buddy side) {
		main.addToAllPreviousBuds(side);
		main.updatePairCount(1);
		return true;
	}
	
	
	public Buddy findBuddy(Buddy b) {
		int index = network.indexOf(b);
		return network.get(index);
	}
	
	public Buddy findPledge(Buddy b) {
		int index = pNetwork.indexOf(b);
		return pNetwork.get(index);
	}
	
	
	/* Searches through BuddyNetwork to see if a buddy with a similar name is present */
	public boolean doesNotContainsBuddy(Buddy bud) {
		for(Buddy b: this.network) {
			if(b.getName().equals(bud.getName())) {
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<Buddy> getBuddies() {
		return network;
	}
	
}
