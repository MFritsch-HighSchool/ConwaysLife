import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class ConwayLife {

	// private data should include a 2D array.  How will you choose to store organism?
	// lots of room for you to choose :)
	// maybe a generation count, too.
	int gene = 0;
	boolean re = false;
	File f;
	Scanner s;
	int[][] grid;
	int row = 0;
	int column = 0;
	public static void main(String[] args) {
		new ConwayLife().start();

	}

	private void start() {
		loadFromFile();
		List<String> optionList = new ArrayList<String>();
		 optionList.add("Yes!");
		 Object[] options = optionList.toArray();
		 Object value = JOptionPane.showInputDialog(null, "Ready to begin the game of life?", "input box", 0, null, options, 0);
		 int index = optionList.indexOf(value)  ;
		 if(index == 0){
			 // probably loop here and a pause between generations?
			 display();// displays in console
			 displayGraphically(); // displays the grid graphically.  
			 nextGen();// advances grid to the next generation.
		 }
	}

	private void displayGraphically() {
		// Ideas here can include adding drawing on a panel that is placed into a 
		// frame.  
		
	}

	private void loadFromFile() {
		try {
			f = new File("life100.txt");
			s = new Scanner(f);
			row = s.nextInt();
			column = s.nextInt();
			grid = new int[row][column];
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		while(s.hasNext())
			grid[s.nextInt()-1][s.nextInt()-1] = 1;
//		for(int loop = 0; loop < column; loop++){
//			for(int l = 0; l < row; l++){
//				//System.out.println(grid[l][loop]);
//			}
//		}
	}

	private void display() {
		// Displays the contents of the 2D array to the console.  Use an * to denote an organism
		// and a space to indicate nothing living there.
		for(int loop = 0; loop < column; loop++){
			for(int l = 0; l < row; l++){
				if(grid[l][loop] == 1)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		
	}
	
	

	private void nextGen() {
		re = false;
		for(int overall = 0; overall < 1; overall++){
			for(int loop = 0; loop < column; loop++){
				for(int l = 0; l < row; l++){
					int count = 0;
					try{
						if(grid[l+1][loop] ==1)
							count++;
					}
					catch(IndexOutOfBoundsException e){
						//System.out.println(e.getCause());
					}
					try{
						if(grid[l+1][loop+1] ==1)
							count++;
					}
					catch(IndexOutOfBoundsException e){
						//System.out.println(e.getCause());
					}
					try{
						if(grid[l][loop+1] ==1)
							count++;
					}
					catch(IndexOutOfBoundsException e){
						//System.out.println(e.getCause());
					}
					try{
						if(grid[l-1][loop] ==1)
							count++;
					}
					catch(IndexOutOfBoundsException e){
						//System.out.println(e.getCause());
					}
					try{
						if(grid[l-1][loop-1] ==1)
							count++;
				}
					catch(IndexOutOfBoundsException e){
						//System.out.println(e.getCause());
					}
					try{
						if(grid[l][loop-1] ==1)
							count++;
					}
					catch(IndexOutOfBoundsException e){
						//System.out.println(e.getCause());
					}
					try{
						if(grid[l+1][loop-1] ==1)
							count++;
					}
					catch(IndexOutOfBoundsException e){
						//System.out.println(e.getCause());
					}
					try{
						if(grid[l-1][loop+1] ==1)
							count++;
					}
					catch(IndexOutOfBoundsException e){
						//System.out.println(e.getCause());
					}
					//System.out.println(count +" is count.");
					if(grid[l][loop] == 1 && count > 3 || grid[l][loop] == 1 && count < 2){
						grid[l][loop] = 0;
						re = true;
					}
					if(grid[l][loop] == 0 && count == 3){
						grid[l][loop] = 1;
						re = true;
					}
				//want it to be able to make changes and then restart
				//so if it makes changes, re = true
				//if it does not, and we get to indexes 19 19 with re as false
				//then we stop running
				}
			}
			gene++;
			if(re == true){
				display();
				List<String> optionList = new ArrayList<String>();
				optionList.add("Sure!");
				optionList.add("Nah!");
				Object[] options = optionList.toArray();
				Object value = JOptionPane.showInputDialog(null, "Would you like another generation? Current is "+gene, "input box", 0, null, options, 0);
				int index = optionList.indexOf(value)  ;
				if(index == 0)
					overall = -1;
				else{
					System.out.println("Done!");
					display();	
				}
				
			}
			else{
				System.out.println("done");
				display();
			}
		}
		// makes the grid move on to the next generation.  Must not mix generations!!
		// reminder of rules:
		// if a cell contains a living organism, it lives on in the next generation 
		//       if it has 2 or 3 neighbors
		// if a cell is empty, it comes to life if it has exactly 3 neighbors
		// check every cell
		
	}

}
