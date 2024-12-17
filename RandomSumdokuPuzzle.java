import java.util.Random;

public class RandomSumdokuPuzzle {
    
    private SumdokuPuzzle[] puzzlesArray;
    private int counter = 0;
    private int gridSize;
    public SumdokuPuzzle currentPuzzle;

    /**
     * Shuffles the puzzles in the puzzlesArray randomly.
     * This ensures that the order of puzzles is randomized.
     *
     * @requires The puzzles array must not be null and must contain more than one puzzle.
     * @ensures The puzzles array is randomized in place.
     */
    private void shufflePuzzles() {
        if (puzzlesArray != null && puzzlesArray.length > 1) {
            Random random = new Random();
            
            for (int i = puzzlesArray.length - 1; i > 0; i--) {
                
                int j = random.nextInt(i + 1);
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
    public RandomSumdokuPuzzle(int size){
        
        this.gridSize=size;
        switch (size) {
            case 3:
                this.puzzlesArray = getPuzzlesSize3();
            break;
            case 5:
                this.puzzlesArray = getPuzzlesSize5();
            break;
            case 6:
                this.puzzlesArray = getPuzzlesSize6();
            break;
        
            default:
                this.puzzlesArray = null;
            break;
        }
        
        shufflePuzzles();

        this.currentPuzzle = puzzlesArray[counter];
    }
    
    /**
     * Retrieves the array of 3x3 Sumdoku puzzles.
     *
     * @return an array of {@code SumdokuPuzzle} objects for 3x3 grids.
     * @requires Predefined membership and value arrays for 3x3 puzzles.
     * @ensures A fully initialized array of {@code SumdokuPuzzle} objects is returned.
     */
    private SumdokuPuzzle[] getPuzzlesSize3(){
        int[][][] puzzlesMembership;
        int[][] puzzlesValues;

        puzzlesMembership = new int[][][] {{{0,0,0},{0,0,1},{0,1,1}},{{0,0,2},{0,1,2},{3,3,4}}};
        puzzlesValues = new int[][] {{14,4},{5,2,5,5,1}};
        SumdokuPuzzle[] puzzles = new SumdokuPuzzle[puzzlesMembership.length];
        for (int i = 0; i < puzzlesMembership.length; i++) {
            puzzles[i] = new SumdokuPuzzle(puzzlesMembership[i], puzzlesValues[i]);
        }

        return puzzles;
    }
        
    /**
     * Retrieves the array of 5x5 Sumdoku puzzles.
     *
     * @return an array of {@code SumdokuPuzzle} objects for 5x5 grids.
     * @requires Predefined membership and value arrays for 5x5 puzzles.
     * @ensures A fully initialized array of {@code SumdokuPuzzle} objects is returned.
     */
    private SumdokuPuzzle[] getPuzzlesSize5(){
        int[][][] puzzlesMembership;
        int[][] puzzlesValues;
        puzzlesMembership = new int[][][]{{
            {0,0,0,1,2},{3,3,0,1,2},{4,5,6,6,7},{4,5,8,8,7},{9,9,9,10,10}
        }};
        puzzlesValues = new int[][] {{14,3,5,8,5,3,9,8,5,8,7}};
        SumdokuPuzzle[] puzzles = new SumdokuPuzzle[puzzlesMembership.length];
        
        
        for (int i = 0; i < puzzlesMembership.length; i++) {
            puzzles[i] = new SumdokuPuzzle(puzzlesMembership[i], puzzlesValues[i]);
        }

        return puzzles;
    }

    /**
     * Retrieves the array of 6x6 Sumdoku puzzles.
     *
     * @return an array of {@code SumdokuPuzzle} objects for 6x6 grids.
     * @requires Predefined membership and value arrays for 6x6 puzzles.
     * @ensures A fully initialized array of {@code SumdokuPuzzle} objects is returned.
     */    
    private SumdokuPuzzle[] getPuzzlesSize6(){
        int[][][] puzzlesMembership;
        int[][] puzzlesValues;
        puzzlesMembership = new int[][][]{{
            
                {0, 0, 0, 1, 1, 1},
                {0, 2, 2, 3, 1, 1},
                {4, 2, 2, 3, 3, 5},
                {4, 4, 6, 6, 3, 5},
                {7, 7, 6, 8, 8, 8},
                {7, 7, 9, 9, 9, 8}
        }};
        puzzlesValues = new int[][] {{15, 20, 10, 15, 8, 9, 12, 11, 10, 14}};
        SumdokuPuzzle[] puzzles = new SumdokuPuzzle[puzzlesMembership.length];

        for (int i = 0; i < puzzlesMembership.length; i++) {
            puzzles[i] = new SumdokuPuzzle(puzzlesMembership[i], puzzlesValues[i]);
        }

        return puzzles;
    }

   
    /**
     * Retrieves the grid size of the puzzles.
     *
     * @return the size of the grid.
     * @requires The grid size must have been set during object initialization.
     */
    public int size(){
        return this.gridSize;
    }

    /**
     * Checks if there are more puzzles available in the sequence.
     *
     * @return true if there are more puzzles, false otherwise.
     */
    public boolean hasNextPuzzle(){
        return this.counter < this.puzzlesArray.length;
        
    }
    
    public SumdokuPuzzle getCurrentPuzzle(){
        return puzzlesArray[counter];
    } 

    /**
     * Retrieves the next puzzle in the sequence.
     *
     * @return the next {@code SumdokuPuzzle} if available;  null if no more puzzles exist.
     * @requires There must be a next puzzle available.
     * @ensures The counter is incremented if a puzzle is returned.
     */
    public SumdokuPuzzle nextPuzzle(){

        if(this.hasNextPuzzle()){
            return this.puzzlesArray[counter++];
        } else {
            return null;
        }
       
    }
}