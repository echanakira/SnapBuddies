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
		if(bud1.getBuddiesLeft().contains(bud2)) {
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
		

		/* Read from stdin and then add the buddy to the network*/
		do {
			line = scan.nextLine();	
			arr = line.split("\t");
			Buddy bud = new Buddy(arr[0], "Brother");
			for( Buddy b : network) {
				bud.addToBuddiesLeft(b);
			}
			network.add(bud);
			unmatched.add(bud);
			lineNum++;
		}while (lineNum != 59);
		
		do {
			line = scan.nextLine();	
			arr = line.split("\t");
			Buddy bud = new Buddy(arr[0], "Pledge"); 
			for( Buddy b : network) {
				bud.addToBuddiesLeft(b);
			}
			network.add(bud);
			pNetwork.add(bud);
			unmatched.add(bud);
			lineNum++;
		}while (lineNum != 82);
		
		Scanner scan2 = new Scanner(filePath);	
		for(Buddy b: network) {
			line = scan.nextLine();
			arr = line.split("\t");
			if(b.getStatus() == "Pledge") {
				for(int i = 0; i < 6; i++) {
					//find buddy using name
					//if buddy is a pledge and i > 2 then updateNetwork
					//otherwise pair the two permanent pledges
					//update pledge paircount
				}
				unmatched.add(b);
			}else {
				//find buddy
				//add the buddy to bud's allPreviousBuddies
			}
			unmatched.add(b);
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
