import java.util.Arrays;

/**
 * The {@code SumdokuTest} tests the main elements of the second IP2425 project. 
 * 
 * 		Compile: javac SumdokuTest.java
 * 		Execute: java  SumdokuTest
 * 
 * @author malopes IP2425@LEI-FCUL 
 * @version 1
 */

public class SumdokuTest {

	public static void main(String[] args) {
		System.out.println ("Testing Sumdoku classes \n");

		testSumdokuGrid ();
		testSumdokuPuzzle ();
		testRandomSumdokuPuzzle ();
	}

	private static void testSumdokuGrid () {
		System.out.println ("-----Testing SumdokuGrid class--------------------");
		testIsFilled();
		testValue();
		System.out.println ("----------------------------------------------------");
	}
	
	private static void testSumdokuPuzzle () {
		System.out.println ("-----Testing SumdokuPuzzle class--------------------");
		testDefinesPuzzle();
		testGroupNumber();
		testValueGroup();
		testIsSolvedBy();
		testIsPartiallySolvedBy();
		testCluesToString();
		System.out.println ("----------------------------------------------------");
	}
	
	private static void testRandomSumdokuPuzzle () {
		System.out.println ("-----Testing RandomSumdokuPuzzle class---------------");
		testNext();
		System.out.println ("----------------------------------------------------");
	}


	private static void testIsFilled() {
		String methodName = "SumdokuGrid.isFilled";
		System.out.println ("Testing "+ methodName);
		boolean error = false;
		
		SumdokuGrid g;
		int row;
		int col;
		boolean expected;
		boolean obtained;
		
		g = new SumdokuGrid(3);	
		row = 1;
		col = 1;
		expected = false;
		obtained = g.isFilled (row, col);
		error = checkEqual(expected, obtained, g.toString(), Integer.toString(row), Integer.toString(col)) || error;
		
		row = 3;
		col = 2;
		expected = false;
		error = checkEqual(expected, obtained, g.toString(), Integer.toString(row), Integer.toString(col)) || error;
	
		g = new SumdokuGrid(5);
		row = 1;
		col = 1;
		
		g.fill(row, col, 4);
		expected = true;
		obtained = g.isFilled (row, col);
		error = checkEqual(expected, obtained, g.toString(), Integer.toString(row), Integer.toString(col)) || error;
		
		row = 3;
		col = 2;
		expected = false;
		obtained = g.isFilled (row, col);
		error = checkEqual(expected, obtained, g.toString(), Integer.toString(row), Integer.toString(col)) || error;

		g = grid5();
		
		row = 2;
		col = 2;
		expected = true;
		obtained = g.isFilled (row, col);
		error = checkEqual(expected, obtained, g.toString(), Integer.toString(row), Integer.toString(col)) || error;
		
		row = 5;
		col = 5;
		expected = true;
		error = checkEqual(expected, obtained, g.toString(), Integer.toString(row), Integer.toString(col)) || error;

		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	
	}

	private static void testValue() {
		String methodName = "SumdokuGrid.value";
		System.out.println ("Testing "+ methodName);
		boolean error = false;
		
		SumdokuGrid g;
		int row;
		int col;
		int expected;
		int obtained;

		g = new SumdokuGrid(5);
		row = 1;
		col = 1;	
		g.fill(row, col, 4);
		expected = 4;
		obtained = g.value (row, col);
		error = checkEqual(expected, obtained, Integer.toString(row), Integer.toString(col)) || error;
		
		row = 3;
		col = 2;	
		g.fill(row, col, 5);
		expected = 5;
		obtained = g.value (row, col);
		error = checkEqual(expected, obtained, g.toString(), Integer.toString(row), Integer.toString(col)) || error;
		
		g = grid5();
		
		row = 2;
		col = 2;
		expected = 3;
		obtained = g.value (row, col);
		error = checkEqual(expected, obtained, g.toString(), Integer.toString(row), Integer.toString(col)) || error;
		
		row = 5;
		col = 5;
		expected = 2;
		obtained = g.value (row, col);		
		error = checkEqual(expected, obtained, g.toString(), Integer.toString(row), Integer.toString(col)) || error;
		
		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	
	}	
	

	private static void testDefinesPuzzle () {
		String methodName = "SumdokuPuzzle.definesPuzzle";
		System.out.println ("Testing "+ methodName);
		boolean error = false;
		boolean expected;
		boolean obtained;
	
		//too small!
		int[][] membership0 = {{0,0},{0,1}}; 
		int[] values0 = {5,1};	
		expected = false; 
		obtained = SumdokuPuzzle.definesPuzzle(membership0, values0);
		error = checkEqual(expected, obtained, toString(membership0),  Arrays.toString(values0)) || error;

		//a good puzzle
		int[][] membership1 = {{0,0,2},{0,1,2},{3,3,4}};
		int[] values1 = {5,2,5,5,1};	
		expected = true;
		obtained = SumdokuPuzzle.definesPuzzle(membership1, values1);
		error = checkEqual(expected, obtained, toString(membership1),  Arrays.toString(values1)) || error;

		//changing it so it is not puzzle
		membership1[2][2] = 5;	//not valid group number
		expected = false;
		obtained = SumdokuPuzzle.definesPuzzle(membership1, values1);
		error = checkEqual(expected, obtained, toString(membership1),  Arrays.toString(values1)) || error;

		//changing it so it is not a puzzle
		membership1[2][2] = 3;	//group 4 is empty		
		expected = false;
		obtained = SumdokuPuzzle.definesPuzzle(membership1, values1);
		error = checkEqual(expected, obtained, toString(membership1),  Arrays.toString(values1)) || error;

		//another bad combination - more than one solution
		int[][] membership2 = {{0,0,0},{0,0,0},{0,0,0}};
		int[] values2 = {18}; 		
		expected = false;
		obtained = SumdokuPuzzle.definesPuzzle(membership2, values2);
		error = checkEqual(expected, obtained, toString(membership2),  Arrays.toString(values2)) || error;

		//another good puzzle
		int[][] membership3 = {{0,0,0},{0,0,1},{0,1,1}}; 
		int[] values3 = {14,4};
		expected = true;
		obtained = SumdokuPuzzle.definesPuzzle(membership3, values3);
		error = checkEqual(expected, obtained, toString(membership3),  Arrays.toString(values3)) || error;
		
		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	
	}
	
	
	private static void testGroupNumber() {
		String methodName = "SumdokuPuzzle.groupNumber";
		System.out.println ("Testing "+ methodName);
		boolean error = false;
		
		SumdokuPuzzle puzzle;
		int row;
		int col;
		int expected;
		int obtained;
		
		int[][] membership1 = {{0,0,2},{0,1,2},{3,3,4}};
		int[] values1 = {5,2,5,5,1};
		puzzle = new SumdokuPuzzle(membership1, values1);

		row = 3;
		col = 3;
		expected = 5;
		obtained = puzzle.groupNumber(row, col);
		error = checkEqual(expected, obtained, Integer.toString(row), Integer.toString(col)) || error;
	
		row = 1;
		col = 1;
		expected = 1;
		obtained = puzzle.groupNumber(row, col);
		error = checkEqual(expected, obtained, Integer.toString(row), Integer.toString(col)) || error;
	
		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	
	}
	
	private static void testValueGroup() {
		String methodName = "SumdokuPuzzle.valueGroup";
		System.out.println ("Testing "+ methodName);
		boolean error = false;
		
		SumdokuPuzzle puzzle;
		int group;
		int expected;
		int obtained;
		
		int[][] membership1 = {{0,0,2},{0,1,2},{3,3,4}};
		int[] values1 = {5,2,5,5,1};
		puzzle = new SumdokuPuzzle(membership1, values1);

		group = 3;
		expected = 5;
		obtained = puzzle.valueGroup(group);
		error = checkEqual(expected, obtained, Integer.toString(group)) || error;
	
		group = 2;
		expected = 2;
		obtained = puzzle.valueGroup(group);
		error = checkEqual(expected, obtained, Integer.toString(group)) || error;

		group = 5;
		expected = 1;
		obtained = puzzle.valueGroup(group);
		error = checkEqual(expected, obtained, Integer.toString(group)) || error;

		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	
	}

	private static void testIsSolvedBy() {
		String methodName = "SumdokuGrid.isSolvedBy";
		System.out.println ("Testing "+ methodName);
		
		boolean error = false;
		boolean expected;
		boolean obtained;
		SumdokuPuzzle puzzle;
		SumdokuGrid playedGrid;
	
		int[][] membership1 = {{0,0,2},{0,1,2},{3,3,4}};
		int[] values1 = {5,2,5,5,1};
		puzzle = new SumdokuPuzzle(membership1, values1);
		
		playedGrid = oneGrid3();
		expected = true;
		obtained = puzzle.isSolvedBy(playedGrid);
		error = checkEqual(expected, obtained, playedGrid.toString(),  puzzle.toString()) || error;
		
		playedGrid = new SumdokuGrid(3);
		playedGrid.fill(1, 1, 3);
		playedGrid.fill(2, 2, 2);
		playedGrid.fill(1, 2, 1);
		playedGrid.fill(2, 3, 3);
		
		//unfilled squares
		expected = false;
		obtained = puzzle.isSolvedBy(playedGrid);
		error = checkEqual(expected, obtained, playedGrid.toString(),  puzzle.toString()) || error;
		
		//squares filled with wrong numbers
		playedGrid.fill(1, 3, 2);
		playedGrid.fill(2, 1, 1); 
		playedGrid.fill(3, 1, 2);
		playedGrid.fill(3, 2, 1); //wrong number
		playedGrid.fill(3, 3, 3); //wrong number
	
		expected = false;
		obtained = puzzle.isSolvedBy(playedGrid);
		error = checkEqual(expected, obtained, playedGrid.toString(),  puzzle.toString()) || error;		
		
		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	
	}
	
	private static void testIsPartiallySolvedBy() {
		String methodName = "SumdokuGrid.isPartiallySolvedBy";
		System.out.println ("Testing "+ methodName);
		
		boolean error = false;
		boolean expected;
		boolean obtained;
		SumdokuPuzzle puzzle;
		SumdokuGrid playedGrid;
	
		int[][] membership1 = {{0,0,2},{0,1,2},{3,3,4}};
		int[] values1 = {5,2,5,5,1};
		puzzle = new SumdokuPuzzle(membership1, values1);
		
		playedGrid = oneGrid3();
		expected = true;
		obtained = puzzle.isPartiallySolvedBy(playedGrid);
		error = checkEqual(expected, obtained, playedGrid.toString(),  puzzle.toString()) || error;

		playedGrid = new SumdokuGrid(3);
		
		//unfilled squares (empty)
		expected = true;
		obtained = puzzle.isPartiallySolvedBy(playedGrid);
		error = checkEqual(expected, obtained, playedGrid.toString(),  puzzle.toString()) || error;
	
	
		playedGrid = new SumdokuGrid(3);
		playedGrid.fill(1, 1, 3);
		playedGrid.fill(2, 2, 2);
		playedGrid.fill(1, 2, 1);
		playedGrid.fill(2, 3, 3);
		
		//unfilled squares (but not empty)
		expected = true;
		obtained = puzzle.isPartiallySolvedBy(playedGrid);
		error = checkEqual(expected, obtained, playedGrid.toString(),  puzzle.toString()) || error;
		
		//squares filled with wrong numbers
		playedGrid.fill(1, 3, 2);
		playedGrid.fill(2, 1, 1); 
		playedGrid.fill(3, 1, 2);
		playedGrid.fill(3, 2, 1); //wrong number
		playedGrid.fill(3, 3, 3); //wrong number
	
		expected = false;
		obtained = puzzle.isPartiallySolvedBy(playedGrid);
		error = checkEqual(expected, obtained, playedGrid.toString(),  puzzle.toString()) || error;		
		
		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	
	}


	private static void testCluesToString () {
		String methodName = "SumdokuPuzzle.cluesToString";
		System.out.println ("Testing "+ methodName);		
		boolean error = false;
		
		String expected;
		String obtained;
		SumdokuPuzzle puzzle;
	
		int[][] membership1 = {{0,0,2},{0,1,2},{3,3,4}};
		int[] values1 = {5,2,5,5,1};
		puzzle = new SumdokuPuzzle(membership1, values1);
		expected = " 1 1 3\n"
				+ " 1 2 3\n"
				+ " 4 4 5\n"
				+ "G1 = 5 G2 = 2 G3 = 5 G4 = 5 G5 = 1 \n";
		obtained = puzzle.cluesToString();
		error = !obtained.equals(expected) || error;
		if (!obtained.equals(expected)) {
			System.out.println (">>> expected:\n" + expected);
			System.out.println (">>> obtained:\n" + obtained);
		}
		
		int[][] membership2 = {{0,0,0},{0,0,1},{0,1,1}};
		int[] values2 = {14,4};
		puzzle = new SumdokuPuzzle(membership2, values2);
		expected = " 1 1 1\n"
				+ " 1 1 2\n"
				+ " 1 2 2\n"
				+ "G1 = 14 G2 = 4 \n";
		obtained = puzzle.cluesToString();
		error = !obtained.equals(expected) || error;
		if (!obtained.equals(expected)) {
			System.out.println (">>> expected:\n" + expected);
			System.out.println (">>> obtained:\n" + obtained);
		}
		
		int[][] membership3 = {{0,0,0,1,2},{3,3,0,1,2},{4,5,6,6,7},{4,5,8,8,7},{9,9,9,10,10}};
		int[] values3 = {14,3,5,8,5,3,9,8,5,8,7};
		puzzle = new SumdokuPuzzle(membership3, values3);
		expected = " 1 1 1 2 3\n"
				+ " 4 4 1 2 3\n"
				+ " 5 6 7 7 8\n"
				+ " 5 6 9 9 8\n"
				+ " 10 10 10 11 11\n"
				+ "G1 = 14 G2 = 3 G3 = 5 G4 = 8 G5 = 5 G6 = 3 G7 = 9 G8 = 8 G9 = 5 G10 = 8 G11 = 7 \n";
		obtained = puzzle.cluesToString();
		error = !obtained.equals(expected) || error;
		if (!obtained.equals(expected)) {
			System.out.println (">>> expected:\n" + expected);
			System.out.println (">>> obtained:\n" + obtained);
		}

		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	
	}
	
	

	private static void testNext() {
		String methodName = "RandomSumdokuPuzzle.next";
		System.out.println ("Testing "+ methodName);
		boolean error = false;
		
		SumdokuPuzzle puzzle;
		RandomSumdokuPuzzle rsp = new RandomSumdokuPuzzle(3);
		int count = 0;
		int found = 0;
	
		while (rsp.hasNextPuzzle()) {
			puzzle = rsp.nextPuzzle();
			count++;
			if (puzzle.isSolvedBy(oneGrid3()) || puzzle.isSolvedBy(anotherGrid3()))
				found++;
		}
	
		boolean obtained = count >= 2;
		boolean expected = true;
		error = checkEqual(expected, obtained, "Puzzles 3x3 available > " + Integer.toString(count)) 
				|| error;
	
		
		obtained = found == 2;
		expected = true;
		error = checkEqual(expected, obtained, "Required puzzles 3x3 available > " + Integer.toString(found)) 
				|| error;
	
		rsp = new RandomSumdokuPuzzle(5);
		count = 0;
		found = 0;
	
		while (rsp.hasNextPuzzle()) {
			puzzle = rsp.nextPuzzle();
			count++;
			if (puzzle.isSolvedBy(grid5()))
				found++;
		}
	
		obtained = count >= 1;
		expected = true;
		error = checkEqual(expected, obtained, "Puzzles 5x5 available > " + Integer.toString(count)) 
				|| error;
	
		obtained = found == 1;
		expected = true;
		error = checkEqual(expected, obtained, "Required puzzles 5x5 available > " + Integer.toString(found)) 
				|| error;
	
		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));			
	}
	
	//grids that are the solutions of 3 diferent puzzles used in these tests
	
	private static SumdokuGrid  oneGrid3() {
		// solution to {{0,0,2},{0,1,2},{3,3,4}}  +
		//             {5,2,5,5,1};
		SumdokuGrid grid = new SumdokuGrid(3);
		grid.fill(1, 1, 3);
		grid.fill(1, 2, 1);
		grid.fill(1, 3, 2);
		grid.fill(2, 1, 1);
		grid.fill(2, 2, 2);
		grid.fill(2, 3, 3);
		grid.fill(3, 1, 2);
		grid.fill(3, 2, 3);
		grid.fill(3, 3, 1);
		return grid;
	}
	

	private static SumdokuGrid  anotherGrid3() {
		// solution to  {{0,0,0},{0,0,1},{0,1,1}}} + 
		//              {14,4}
		SumdokuGrid grid = new SumdokuGrid(3);
		grid.fill(1, 1, 1);
		grid.fill(1, 2, 2);
		grid.fill(1, 3, 3);
		grid.fill(2, 1, 2);
		grid.fill(2, 2, 3);
		grid.fill(2, 3, 1);
		grid.fill(3, 1, 3);
		grid.fill(3, 2, 1);
		grid.fill(3, 3, 2); 
		return grid;
	}

	private static SumdokuGrid  grid5() {
		// solution to {{0,0,0,1,2},{3,3,0,1,2},{4,5,6,6,7},{4,5,8,8,7},{9,9,9,10,10}}  +   
		// 			   {14,3,5,8,5,3,9,8,5,8,7}
		SumdokuGrid grid = new SumdokuGrid(5);
		grid.fill(1, 1, 2);
		grid.fill(1, 2, 5);
		grid.fill(1, 3, 3);
		grid.fill(1, 4, 1);
		grid.fill(1, 5, 4);
		grid.fill(2, 1, 5);
		grid.fill(2, 2, 3);
		grid.fill(2, 3, 4);
		grid.fill(2, 4, 2);
		grid.fill(2, 5, 1);
		grid.fill(3, 1, 1);
		grid.fill(3, 2, 2);
		grid.fill(3, 3, 5);
		grid.fill(3, 4, 4);
		grid.fill(3, 5, 3);
		grid.fill(4, 1, 4);
		grid.fill(4, 2, 1);
		grid.fill(4, 3, 2);
		grid.fill(4, 4, 3);
		grid.fill(4, 5, 5);
		grid.fill(5, 1, 3);
		grid.fill(5, 2, 4);
		grid.fill(5, 3, 1);
		grid.fill(5, 4, 5);
		grid.fill(5, 5, 2);
		return grid;
	}

	//auxiliary procedures for helping to write the tests
	
	private static boolean checkEqual(int expected, int obtained, String... args) {
		boolean error = false;
		if (obtained != expected) {
			System.out.println(Arrays.toString(args));
			System.out.printf (">>> expected: %d obtained: %d %n", expected, obtained);
			error = true;
		}
		return error;
	}

	private static boolean checkEqual(boolean expected, boolean obtained, String... args) {
		boolean error = false;
		if (expected != obtained) {
			System.out.println(Arrays.toString(args));
			System.out.printf (">>> expected: %b obtained: %b %n", expected, obtained);
	
			error = true;
		}
		return error;
	}

	private static String toString(int[][] membership) {
		StringBuilder sb = new StringBuilder("\n");
		for (int[] v: membership)
			sb.append(Arrays.toString(v)+"\n");
		return sb.toString();
	}
}
