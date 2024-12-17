import java.util.Random;

/**
 * The {@code RandomSumdokuPuzzle} consists in the class which contains the section of the random puzzle generator
 * 
 * This is the second project of 2024 in IP (Introdução à Programação) on FCUL (Faculdade de Ciências - Universidade de Lisboa)
 * 
 * @author Pedro Reinaldo Mendes - nº63729
 * @author Miguel Cabeça - nº63762
 * @version 1.0
 */  
public class RandomSumdokuPuzzle {

    private SumdokuPuzzle[] puzzlesArray;
    private int counter = 0;
    private int gridSize;

    /**
     * Shuffles the puzzles in the puzzlesArray randomly.
     * This ensures that the order of puzzles is randomized.
     *
     * @requires The puzzles array must not be null and must contain more than one puzzle.
     * @ensures The puzzles array is randomized in place.
     */
    private void shufflePuzzles() {
    // Check if the puzzles array is not null and contains more than one element
    if (puzzlesArray != null && puzzlesArray.length > 1) {
        Random random = new Random();
        
        // Perform the Fisher-Yates shuffle
        for (int i = puzzlesArray.length - 1; i > 0; i--) {
            // Select a random index between 0 and i (inclusive)
            int j = random.nextInt(i + 1);
            
            // Swap the elements at indices i and j
            SumdokuPuzzle temp = puzzlesArray[i];
            puzzlesArray[i] = puzzlesArray[j];
            puzzlesArray[j] = temp;
        }
    }
}
    
    /**
     * Constructor that initializes the {@code RandomSumdokuPuzzle} object with a set of puzzles
     * of the specified grid size. The puzzles are shuffled randomly upon initialization.
     *
     * @param size the size of the puzzle grid (e.g., 3, 5, or 6).
     *             If the size is not supported, no puzzles will be initialized.
     * @ensures Puzzles are initialized and shuffled if the size is valid.
     */ 
    public RandomSumdokuPuzzle(int size) {
        // Set the grid size
        this.gridSize = size;
        
        // Initialize the puzzlesArray based on the specified grid size
        switch (size) {
            case 3:
                this.puzzlesArray = getPuzzlesSize3();  // Get puzzles for size 3
                break;
            case 5:
                this.puzzlesArray = getPuzzlesSize5();  // Get puzzles for size 5
                break;
            case 6:
                this.puzzlesArray = getPuzzlesSize6();  // Get puzzles for size 6
                break;
            default:
                this.gridSize = 0;  // Invalid size, set puzzlesArray to null
                break;
        }
    
        // Shuffle the puzzles array to randomize the order
        shufflePuzzles();
    }
    
    /**
     * Retrieves the array of 3x3 Sumdoku puzzles.
     *
     * @return an array of {@code SumdokuPuzzle} objects for 3x3 grids.
     * @requires Predefined membership and value arrays for 3x3 puzzles.
     * @ensures A fully initialized array of {@code SumdokuPuzzle} objects is returned.
     */
    private SumdokuPuzzle[] getPuzzlesSize3() {
        int[][][] puzzlesMembership;
        int[][] puzzlesValues;
    
        // Define the membership matrix for each puzzle
        puzzlesMembership = new int[][][] {
            {{0,0,0},{0,0,1},{0,1,1}},
            {{0,0,2},{0,1,2},{3,3,4}}
        };
    
        // Define the values for each puzzle
        puzzlesValues = new int[][] {
            {14, 4},
            {5, 2, 5, 5, 1}
        };
    
        // Create an array of SumdokuPuzzle objects with the length of the membership matrix
        SumdokuPuzzle[] puzzles = new SumdokuPuzzle[puzzlesMembership.length];
        
        // Initialize each SumdokuPuzzle object using the membership and values
        for (int i = 0; i < puzzlesMembership.length; i++) {
            puzzles[i] = new SumdokuPuzzle(puzzlesMembership[i], puzzlesValues[i]);
        }
    
        // Return the array of SumdokuPuzzle objects
        return puzzles;
    }
        
    /**
     * Retrieves the array of 5x5 Sumdoku puzzles.
     *
     * @return an array of {@code SumdokuPuzzle} objects for 5x5 grids.
     * @requires Predefined membership and value arrays for 5x5 puzzles.
     * @ensures A fully initialized array of {@code SumdokuPuzzle} objects is returned.
     */
    private SumdokuPuzzle[] getPuzzlesSize5() {
        int[][][] puzzlesMembership;
        int[][] puzzlesValues;
    
        // Define the membership matrix for the puzzle
        puzzlesMembership = new int[][][] {
            {
                {0,0,0,1,2},
                {3,3,0,1,2},
                {4,5,6,6,7},
                {4,5,8,8,7},
                {9,9,9,10,10}
            }
        };
    
        // Define the values for the puzzle
        puzzlesValues = new int[][] {
            {14, 3, 5, 8, 5, 3, 9, 8, 5, 8, 7}
        };
    
        // Create an array of SumdokuPuzzle objects based on the length of the membership matrix
        SumdokuPuzzle[] puzzles = new SumdokuPuzzle[puzzlesMembership.length];
    
        // Initialize each SumdokuPuzzle object with the corresponding membership and values
        for (int i = 0; i < puzzlesMembership.length; i++) {
            puzzles[i] = new SumdokuPuzzle(puzzlesMembership[i], puzzlesValues[i]);
        }
    
        // Return the array of SumdokuPuzzle objects
        return puzzles;
    }

    /**
     * Retrieves the array of 6x6 Sumdoku puzzles.
     *
     * @return an array of {@code SumdokuPuzzle} objects for 6x6 grids.
     * @requires Predefined membership and value arrays for 6x6 puzzles.
     * @ensures A fully initialized array of {@code SumdokuPuzzle} objects is returned.
     */    
    private SumdokuPuzzle[] getPuzzlesSize6() {
        int[][][] puzzlesMembership;
        int[][] puzzlesValues;
    
        // Define the membership matrix for the puzzle
        puzzlesMembership = new int[][][] {
            {
                {0, 0, 0, 1, 1, 1},
                {0, 2, 2, 3, 1, 1},
                {4, 2, 2, 3, 3, 5},
                {4, 4, 6, 6, 3, 5},
                {7, 7, 6, 8, 8, 8},
                {7, 7, 9, 9, 9, 8}
            }
        };
    
        // Define the values for the puzzle
        puzzlesValues = new int[][] {
            {15, 20, 10, 15, 8, 9, 12, 11, 10, 14}
        };
    
        // Create an array of SumdokuPuzzle objects based on the length of the membership matrix
        SumdokuPuzzle[] puzzles = new SumdokuPuzzle[puzzlesMembership.length];
    
        // Initialize each SumdokuPuzzle object with the corresponding membership and values
        for (int i = 0; i < puzzlesMembership.length; i++) {
            puzzles[i] = new SumdokuPuzzle(puzzlesMembership[i], puzzlesValues[i]);
        }
    
        // Return the array of SumdokuPuzzle objects
        return puzzles;
    }

   
    /**
     * Retrieves the grid size of the puzzles.
     *
     * @return the size of the grid.
     * @requires The grid size must have been set during object initialization.
     */
    public int size(){
        return this.gridSize; // Returns the grid size
    }

    /**
     * Checks if there are more puzzles available in the sequence.
     *
     * @return true if there are more puzzles, false otherwise.
     */
    public boolean hasNextPuzzle(){
        return this.counter < this.puzzlesArray.length; // Return if it has a next puzzle
        
    }

    /**
     * Retrieves the next puzzle in the sequence.
     *
     * @return the next {@code SumdokuPuzzle} if available;  null if no more puzzles exist.
     * @requires There must be a next puzzle available.
     * @ensures The counter is incremented if a puzzle is returned.
     */
    public SumdokuPuzzle nextPuzzle() {
        // Return the next puzzle and increment the counter
        return this.puzzlesArray[counter++];
    }
}