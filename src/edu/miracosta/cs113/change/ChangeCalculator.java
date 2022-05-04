package edu.miracosta.cs113.change;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 *
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 *
 * NOTE: An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to
 * verify that all given coin combinations are unique.
 */
public class ChangeCalculator {
	
	private static final int QUARTER_VALUE = 25;
	private static final int DIME_VALUE = 10;
	private static final int NICKEL_VALUE = 5;
	private static final int PENNY_VALUE = 1;
	
	static File FILE_NAME = new File("src/edu.miracosta.cs113/change/CoinCombinations.txt");
	private static TreeSet<String> combos = new TreeSet<>();
	

    /**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
     *
     * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
    public static int calculateChange(int cents) {
    	combos.clear();
    	return calculateChange(cents, 0, 0, 0, cents);
    }
    
    /**
     * Helper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     *
     *
     * @param integers total, quarters, dimes, nickels, and pennies to track coin counts
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
    public static int calculateChange(int total, int quarters, int dimes, int nickels, int pennies) {
        // TODO:
        // Implement a recursive solution following the given documentation.
    	//Cents is the number of pennies
    	//for every five cents, add 1 nickel
    	//for every 10 cents, add 1 dime
    	//for every 25 cents, add 1 quarter
    	//base case
    	
    	String change = "[" + quarters + ", " + dimes + ", " + nickels + ", " + pennies + "]";
    	
    	
    	if (!combos.contains(change) && (quarters*QUARTER_VALUE + dimes*DIME_VALUE + nickels*NICKEL_VALUE + pennies*PENNY_VALUE == total)) {
    		combos.add(change);
    	}
    	
    	if (pennies == 0) {
    		return combos.size();//base case is (0, 0, 0, pennies)?
    	}
    	if (pennies >= 5) {
    		calculateChange(total, quarters, dimes, nickels + 1, pennies - 5);
    	}
    	if (pennies >= 10) {
    		calculateChange(total, quarters, dimes + 1, nickels, pennies - 10);
    	}
    	if (pennies >= 25) {
    		calculateChange(total, quarters + 1, dimes, nickels, pennies - 25);
    	}
    

        return combos.size();
    }

    /**
     * Calls upon calculateChange(int) to calculate and print all possible unique combinations of quarters, dimes,
     * nickels, and pennies that equal the given value in cents.
     *
     * Similar to calculateChange's function in printing each combination to the console, this method will also
     * produce a text file named "CoinCombinations.txt", writing each combination to separate lines.
     *
     * @param cents a monetary value in cents
     */
    public static void printCombinationsToFile(int cents) {
        // TODO:
        // This when calculateChange is complete. Note that the text file must be created within this directory.
    	calculateChange(cents);
    	
    	try {
    		PrintWriter pw = new PrintWriter(FILE_NAME);
    		Iterator<String> iterator = combos.descendingIterator();
    		
    		while (iterator.hasNext()) {
    			pw.append(iterator.next());
    			pw.append("\n");
    		}
    		
    		pw.flush();
    		pw.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("Error locating file: " + e);
    	}
    }

} // End of class ChangeCalculator
