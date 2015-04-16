import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
/**
 * Project 2 - Sequence Comparison
 * @author Dylan Parris (8007022231)
 * @version 4/8/2015
 */

public class Main {
	
	/**
	 * Main method of project 2
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		if(args.length != 2){
			System.out.println("Usage Information: ");
			System.out.println("Main.java path/to/in1.txt path/to/in2.txt");
			System.exit(-1);
		}
		String in1File 	= args[0];
		String in2File 	= args[1];
		
		String strand1 = readFile(in1File);
		String strand2 = readFile(in2File);
		
		String LCS = buildDynamicTable(strand1, strand2);
		
		int MED = calculateMinimumEdit(strand1, strand2);
		
		float percentSame = ((float)strand1.length() + (float)strand2.length() - (float)MED)
						/((float)strand1.length() + (float)strand2.length());
		//Official Output
		System.out.println(percentSame + LCS);
	}
	
	/**
	 * Reads the input file specified in command line
	 * @param fileName String of path to file name
	 * @return the string in the first line of the file
	 */
	public static String readFile(String fileName){
		Scanner in = null;
		String temp;
		try {
			 in = new Scanner(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}
		temp = in.next();
		in.close();
		return temp;
	}
	
	/**
	 * Builds dynamic table of edit distances
	 * @param strand1 - first strand of dna
	 * @param strand2 - second strand of dna
	 */
	public static String buildDynamicTable(String strand1, String strand2){
		
		char[] dna1 = stringToArray(strand1);
		char[] dna2 = stringToArray(strand2);
		
		int numCols = strand2.length() + 1;
		int numRows = strand1.length() + 1;
		
		int[][] table = new int[numRows][numCols];
		
		//initialize first row of table
		for(int j = 0; j < numCols; j++){
			table[0][j] = j;
		}
		
		for(int i = 1; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				if(dna1[i] == dna2[j] ){ // if match found
					if ( j != 0 && i != 0){ // checks upper and left cell
						if(table[i][j-1] > table[i-1][j]){ 
							table[i][j] = table[i][j-1] - 1;
						} else {
							table[i][j] = table[i-1][j] - 1;
						}
					} else { // adds from left cell
						table[i][j] = table[i - 1][j] - 1;
					}
				} else if (j != 0 && i != 0){ //if match not found and not on edge
					if(table[i][j-1] < table[i-1][j]){
						table[i][j] = table[i][j-1] + 1;
					} else {
						table[i][j] = table[i-1][j] + 1;
					}
				} else { // if(j - 1 < 0){
					table[i][j] = table[i-1][j] + 1;
				}	
			}
		}
		
		//For debugging purposes
		//printTable(table,numRows,numCols);
		return computeLCS(table, dna1, dna2);
	}
		
	/**
	 * Computes the longest common string (LCS) recursively based on dynamic edit distance table
	 * @param table dynamic table which contains edit distances
	 * @param row - row index at which to begin operations
	 * @param col - column index at which to begin operations
	 * @param dna1 - char[] of strand 1
	 * @param dna2 - char[] of strand 2
	 * @param result - string of LCS to be built
	 * @return result - LCS
	 */
	public static String computeLCS(int[][] table, char[] dna1, char[] dna2){
		
		String result = "";
		int row = dna1.length - 1;
		int col = dna2.length - 1;
		
		while(row != 0 || col != 0){ // while not at cell 0,0
			if(dna1[row] == dna2[col]){ //if match found
				if(row == 0 && col == 0){
					return result;
				} else if (row == 0) {
					result = dna1[row] + " " + result;
					col = col - 1;
				} else if (col == 0) {
					result = dna1[row] + " " + result;
					row = row - 1;
				} else {
					result = dna1[row] + " " + result;
					row = row - 1;
					col = col - 1;
				}
			}else { // if no match found
				if(row != 0 && col != 0){
					if(table[row -1][col] < table[row][col -1]){
						row =  row - 1;
					} else {
						col = col - 1;
					}
				} else if (row == 0) {
					col = col - 1;
				} else if (col == 0) {
					row = row - 1;
				} else {
					row = row - 1;
				}
			}
		}
		return result;
	}
	
	/**
	 * Prints the table for debugging purposes
	 * @param table - dynamic table
	 * @param numRows - number of rows
	 * @param numCols - number of columns
	 */
	public static void printTable(int[][] table, int numRows, int numCols){
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				System.out.print("| " + table[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Calculates the minimum edit distance of the 2 strings using linear memory
	 * @param strand1 - string to be compared
	 * @param strand2 - string to be compared
	 * @return integer representing number of deletes needed
	 */
	public static int calculateMinimumEdit(String strand1, String strand2){
		
		char[] dna1 = stringToArray(strand1);
		char[] dna2 = stringToArray(strand2);
		
		int [] topArray = new int[dna2.length];
		int [] bottomArray = new int[dna2.length];
		
		//initialize top array
		for(int i = 0; i < topArray.length; i++){
			topArray[i]=i;
		}
		
		for(int j = 1; j < dna1.length; j++){
			for(int i = 0; i < topArray.length; i++){

				if(dna2[i] == dna1[j]){ // if match found
					if(i == 0){
						bottomArray[i] = topArray[i] - 1;
					} else if(topArray[i] < bottomArray[i-1]){
						bottomArray[i] = bottomArray[i - 1] - 1;
					} else {
						bottomArray[i] = topArray[i] - 1;
					}
				} else { // if match not found
					if(i == 0){
						bottomArray[i] = topArray[i] + 1;
					} else if(topArray[i] >= bottomArray[i-1]){
						bottomArray[i] = bottomArray[i - 1] + 1;
					} else {
						bottomArray[i] = topArray[i] + 1;
					}
				}
			}
			topArray = bottomArray;
			bottomArray = new int[topArray.length];
		}
		return topArray[topArray.length - 1];
	}

	/**
	 * Converts into array of characters
	 * @param string to be converted
	 * @return array of characters
	 */
	public static char[] stringToArray(String string){
		char[] array = new char[string.length()+1];
		
		for(int i = 1; i < array.length; i++){
			array[i] = string.charAt(i-1);
		}
		return array;
	}
}
