import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.FileOutputStream;

/*import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;*/


public class Main {
	/*public static Workbook workbook = new HSSFWorkbook();
	public static Sheet master = workbook.createSheet("Master");			// Creates a sheet and places it into the workbook
	public static Sheet  brothers = workbook.createSheet("Brothers");			// Creates a sheet and places it into the workbook
	public static Sheet pledges = workbook.createSheet("Pledges");			// Creates a sheet and places it into the workbook
	*/
	public static void main(String[] args) throws IOException  {
		Scanner scan = new Scanner(System.in);
		int month = 2;
		
		PrintStream stdout = System.out;
		BuddyNetwork net = new BuddyNetwork(3);
		File path = new File("C:\\Users\\Elijah\\git\\SnapBuddies\\SnapBuddies\\src\\buddies.txt");
		File outPath =  new File("C:\\Users\\Elijah\\git\\SnapBuddies\\SnapBuddies\\src\\output.txt");
		System.setOut(new PrintStream(
				"C:\\Users\\Elijah\\git\\SnapBuddies\\SnapBuddies\\src\\output.txt"));		//Sets stdout to output.txt file

		//writeToBook(master);
		
		/*try {
			FileOutputStream output = new FileOutputStream("output.xls");
			workbook.write(output);
			output.close();
		}catch(Exception e) {
			e.printStackTrace();
		}*/
		
		if(month == 1) {
			net.init(path);
			net.pairPledges();
			net.pairAll();
			printAllPairs(net);
			System.setOut(stdout);											//clear output.txt
		}else {
			File newPath = new File("C:\\Users\\Elijah\\git\\SnapBuddies\\SnapBuddies\\src\\all_new.txt");
			net.reset(outPath);
			net.init(newPath);
			printAllPairs(net);
			System.setOut(stdout);											//clear output.txt
		}
	}
	
	public static void printAllPairs(BuddyNetwork net) {
		for(Buddy b : net.getBuddies()) {
			System.out.print(b.getName() +": \t");
			b.printBuds();
			System.out.println();
		}
	}
	
	/* Initializes first row of workbook 
	public static boolean writeToBook(Sheet sheet) {
		Row row = sheet.createRow(0);
		
		Cell cell = row.createCell(0);
		cell.setCellValue("Brother");
		Cell cell1 = row.createCell(1);
		cell1.setCellValue("Snapchat");
		Cell cell2 = row.createCell(2);
		cell2.setCellValue("Permanent Match 1");
		Cell cell3 = row.createCell(3);
		cell3.setCellValue("Permanent Match 2");
		Cell cell4 = row.createCell(4);
		cell4.setCellValue("Match 3");
		Cell cell5 = row.createCell(5);
		cell5.setCellValue("Match 4");
		Cell cell6 = row.createCell(6);
		cell6.setCellValue("Match 5");
		Cell cell7 = row.createCell(7);
		cell7.setCellValue("Match 6");
		return true;
	}*/
	

}
