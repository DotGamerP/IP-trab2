public class SumdokuPuzzle{
    
    // We must have the two main attributes for this object
    private int[][] groupMembership; // The matrix that defines each square's group
    private int[] groupsValues; // The vector that defines each group's total values sum
    
    // For simplicity, we can also use a variable for the size of the puzzle
    private int puzzleSize;

    public static boolean definesPuzzle(int[][] groupMembership, int[] groupsValues){
        
        // We create a variable to store this temporary puzzle size
        int size = groupMembership.length;

        // We verify if groupMembership size is between 3 and 9
        //

        // We verify if groupMembership is a square matrix (size*size)
        //

        // We verify that the length of groupsValues is between 1 and size*size
        //

        // We verify that every value in groupsValues is between 1 and [size^3+size^2]/2 (in the case there's only one group)
        //

        // We verify that every value in groupMembership is between 0 and the number of groups minus 1
        //

        // We verify that there is at least one square of the puzzle for each possible group described in groupsValues
        //

        // We verify if the puzzle has only one possible solution using the SumdokuSolver class
        //

        // If no problem has been found, then return true
        return true;
    }
    
    /**
     * Creates a Sumdoku puzzle with the given group membership matrix and group values.
     * 
     * @param groupMembership a square two-dimensional matrix representing group memberships
     * @param groupsValues a one-dimensional array with the target sum for each group
     * @requires {@code definesPuzzle(groupMembership, groupsValues)}
     */
    public SumdokuPuzzle(int[][] groupMembership, int[] groupsValues){

        // We now have the puzzle size
        this.puzzleSize = groupMembership.length;
        
        // We'll now replicate each value in the matrix groupMembership to the groupMembership atribute
        this.groupMembership = copySquareIntMatrix(this.puzzleSize, groupMembership);

        // We'll now replicate each value in the vector groupValues to the groupValues atribute
        this.groupsValues = copyIntVector(groupsValues);
    }

    /**
     * Copies each value from a square two-dimension integer-only matrix into a new one that will be returned
     * 
     * @param matrixSize the size of the matrix
     * @param matrix the base matrix from where we want to copy every value
     * @requires {@code matrix != null && matrix.length == matrixSize} and every row in {@code matrix} has length == {@code matrixSize},
     *           {@code matrixSize > 0}
     * @return a two-dimension matrix with all the copied values from {@code matrix}
     */
    private int[][] copySquareIntMatrix(int matrixSize, int[][] matrix){

        // We create a new matrix where we're gonna put the same values as the base matrix
        int[][] copy = new int[puzzleSize][puzzleSize];

        // In each position, we'll replicate the base matrix's values
        for (int i = 0; i < puzzleSize; i++){
            for (int j = 0; j < puzzleSize; i++){
            copy[i][j] = matrix[i][j];
            }
        }

        // We finally return the copy of the base matrix
        return copy;
    }

    /**
     * Copies each value from a one-dimensional integer-only array into a new one that will be returned.
     * 
     * @param vector the base array from where we want to copy every value
     * @requires {@code vector != null && vector.length > 0}
     * @return a one-dimensional array with all the copied values from {@code vector}
     */
    private int[] copyIntVector(int[] vector){

        // We create a new matrix where we're gonna put the same values as the base matrix
        int[] copy = new int[vector.length];

        // In each position, we'll replicate the base vector's values
        for (int i = 0; i < puzzleSize; i++){
            copy[i] = vector[i];
        }

        // We finally return the copy of the base matrix
        return copy;
    }

    public int size(){
        //
        return 0;
    }

    public int numberOfGroups(){
        //
        return 0;
    }

    public int groupNumber(int col, int row){
        //
        return 0;
    }

    public int valueGroup(int group){
        //
        return 0;
    }

    public boolean isSolvedBy(SumdokuGrid playedGrid){
        //
        return false;
    }

    public boolean isPartiallySolvedBy(SumdokuGrid playedGrid){
        //
        return false;
    }

    public String cluesToString(){
        //
        return "";
    }

    public String toString(){
        //
        return "";
    }
}
