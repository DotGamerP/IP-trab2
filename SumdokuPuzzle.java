/**
 * The {@code SumdokuPuzzle} consists in the class which contains a big section of the entire project
 * 
 * This is the second project of 2024 in IP (Introdução à Programação) on FCUL (Faculdade de Ciências - Universidade de Lisboa)
 * 
 * @author Pedro Reinaldo Mendes - nº63729
 * @author Miguel Cabeça - nº63762
 * @version 1.0
 */  
public class SumdokuPuzzle{
    
    // We must have the two main attributes for this object
    private final int[][] groupMembership; // The matrix that defines each square's group
    private final int[] groupsValues; // The vector that defines each group's total values sum
    
    // For simplicity, we can also use a variable for the size of the puzzle and the number of groups
    private final int puzzleSize;
    private final int numOfGroups;


    /**
     * Determines if the given groupMembership matrix and groupsValues array define a valid puzzle.
     *
     * @param groupMembership a two-dimensional integer-only array where each element indicates the group a cell belongs to
     * @param groupsValues a one-dimensional integer-only array representing the sum of the cells associated with each group (i.e., first group represented in groupsValues[0])
     * @requires Nothing (we already check everything inside the function)
     * @return {@code true} if the inputs define a valid puzzle, {@code false} otherwise.
     */
    public static boolean definesPuzzle(int[][] groupMembership, int[] groupsValues){
        
        // We create a variable to store the temporary puzzle size and the number of groups
        int size = groupMembership.length;
        int numOfGroups = groupsValues.length;
 
        // We verify if groupMembership size is between 3 and 9
        if (size < 3 || size > 9)
            return false; // If not, then it doesn't defines a puzzle

        // We verify if groupMembership is a square matrix (size*size)
        if (!isSquareMatrix(groupMembership))
            return false; // If not...

        // We verify that the length of groupsValues (number of groups) is between 1 and size*size
        if (numOfGroups < 1 || numOfGroups > size*size)
            return false; // If not...

        // We verify that every value in groupsValues is between 1 and [size^3+size^2]/2
        int maxValue = (int) (Math.pow(size, 3) + Math.pow(size, 2))/2; // We first create a variable that can be forced to an integer because it won't never have decimal places
        
        if (!everyValueBetween(groupsValues, 1, maxValue))
            return false; // If not...

        // We verify that every value in groupMembership is between 0 and the number of groups minus 1
        if (!everyValueBetween(groupMembership, 0, numOfGroups - 1))
            return false; // If not...

        // We verify that there is at least one square of the puzzle for each possible group described in groupsValues
        if (!noEmptyGroups(groupMembership, numOfGroups))
            return false; // If not...

        // We verify if the puzzle has only one possible solution using the SumdokuSolver class
        SumdokuSolver solver = new SumdokuSolver(groupMembership, groupsValues);

        if (!(solver.howManySolutions(2) == 1))
            return false; // If not...

        // If no problem has been found, then return true
        return true;
    }

    /**
     * Checks if the given matrix is a square matrix (each row has the same number of columns as the number of rows).
     *
     * @param matrix a two-dimensional integer-only array to be checked
     * @return {@code true} if the matrix is square, {@code false} otherwise.
     */
    private static boolean isSquareMatrix(int[][] matrix){
        
        // For simplicity, we'll store the size in a variable
        int size = matrix.length;

        // We'll go through each row of the matrix and compare it with the size
        for (int r = 0; r < size; r++){

            // If we find any row which has a different number of columns than the size, we return false
            if (matrix[r].length != size)
                return false;
        }

        return true; // If everything went smoothly, we have a square matrix (size * size)
    }

    /**
     * Checks if every value in the given array falls within the specified range [minValue, maxValue].
     *
     * @param array a one-dimensional integer-only array to be checked
     * @param minValue the minimum allowable value (inclusive)
     * @param maxValue the maximum allowable value (inclusive)
     * @return {@code true} if all values in the array are between {@code minValue} and {@code maxValue}, {@code false} otherwise.
     */
    private static boolean everyValueBetween(int[] array, int minValue, int maxValue){

        // We'll go through every position of the array
        for (int i = 0; i < array.length; i++){
            
            // If we find any value outside our minValue-maxValue interval, we return false
            if (array[i] < minValue || array[i] > maxValue)
                return false;
        }

        return true; // If everything went smoothly, it means all the values are between minValue and maxValue
    }

    /**
     * Checks if every value in the given matrix falls within the specified range [minValue, maxValue].
     *
     * @param matrix a two-dimensional integer-only matrix to be checked
     * @param minValue the minimum allowable value (inclusive)
     * @param maxValue the maximum allowable value (inclusive)
     * @return {@code true} if all values in the matrix are between {@code minValue} and {@code maxValue}, {@code false} otherwise.
     */
    private static boolean everyValueBetween(int[][] matrix, int minValue, int maxValue){

        // We'll go through every position of the matrix
        for (int r = 0; r < matrix.length; r++){
            for (int c = 0; c < matrix.length; c++){
                
                // If we find any value outside our minValue-maxValue interval, we return false
                if (matrix[r][c] < minValue || matrix[r][c] > maxValue)
                    return false;
            }
        }

        return true; // If everything went smoothly, it means all the values are between minValue and maxValue
    }

    /**
     * Checks if all groups (0 to [numOfGroups - 1]) are represented in the groupMembership matrix
     *
     * @param groupMembership a two-dimension integer-only array where each element indicates the group a cell belongs to
     * @param numOfGroups the total number of groups
     * @requires {@code groupMembership} must not be null and must be a square matrix
     *           {@code numOfGroups > 0}
     *           Every value in groupMembership must be between 0 and [numOfGroups - 1]
     * @return {@code true} if all groups are represented in the groupMembership matrix, {@code false} otherwise
     */
    private static boolean noEmptyGroups(int[][] groupMembership, int numOfGroups){

        /*
         * Our goal will be to fill a new array that we're going to create with 1's or 0's values
         * 
         * Note: in this situation, we'll mention the group 0 as the first one
         * 
         * The position with the index 2, for example, in this new array will be associated to the group 2
         * If this position is filled with 1 it means there's at least one cell in the group 2
         * If the value in the position is 0, it means the group 2 is empty
        */

        // For simplicity, we create a variable to store the groupMembership's size
        int size = groupMembership.length;

        // We create an only-integer array with a length equal to the number of groups
        int[] groups = new int[numOfGroups];

        // We'll go through every position in groupMembership
        for (int r = 0; r < size; r++){
            for (int c = 0; c < size; c++){

                // We'll fill the position of the array 'groups' with a 1
                groups[groupMembership[r][c]] = 1;
            }
        }

        // We'll check that there isn't any 0 value in the array
        for (int i = 0; i < groups.length; i++){
            if (groups[i] == 0)
                return false; // If not...
        }

        return true; // If everything went smoothly, we don't have empty groups
    }
    
    
    /**
     * Creates a Sumdoku puzzle with the given group membership matrix and group values.
     * 
     * @param groupMembership a square two-dimensional matrix representing group memberships
     * @param groupsValues a one-dimensional array with the target sum for each group
     * @requires {@code definesPuzzle(groupMembership, groupsValues)}
     */
    public SumdokuPuzzle(int[][] groupMembership, int[] groupsValues){

        // We now have the puzzle size and number of groups
        this.puzzleSize = groupMembership.length;
        this.numOfGroups = groupsValues.length;
        
        // We'll now replicate each value in the matrix groupMembership to the groupMembership atribute
        this.groupMembership = copySquareMatrix(this.puzzleSize, groupMembership);

        // We'll now replicate each value in the vector groupValues to the groupValues atribute
        this.groupsValues = copyVector(groupsValues);
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
    private int[][] copySquareMatrix(int matrixSize, int[][] matrix){

        // We create a new matrix where we're gonna put the same values as the base matrix
        int[][] copy = new int[puzzleSize][puzzleSize];

        // In each position, we'll replicate the base matrix's values
        for (int i = 0; i < puzzleSize; i++){
            for (int j = 0; j < puzzleSize; j++){
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
    private int[] copyVector(int[] vector){

        // We create a new matrix where we're gonna put the same values as the base matrix
        int[] copy = new int[vector.length];

        // In each position, we'll replicate the base vector's values
        for (int i = 0; i < vector.length; i++){
            copy[i] = vector[i];
        }

        // We finally return the copy of the base matrix
        return copy;
    }


    /**
     * Retrieves the size of the puzzle
     *
     * @return the size of the puzzle as an integer
     */
    public int size(){
        
        // We return the puzzle's size
        return this.puzzleSize;
    }


    /**
     * Retrieves the number of groups in the puzzle
     *
     * @return the number of groups in the puzzle as an integer
     */
    public int numberOfGroups(){
        
        // We return the number of groups
        return this.numOfGroups;
    }


    /**
     * Retrieves the group number for the specified cell in the puzzle
     *
     * @param col the column index of the cell
     * @param row the row index of the cell
     * @requires {@code 1 <= row <= this.puzzleSize} and {@code 1 <= col <= this.puzzleSize}
     * @return the group number of the cell at the specified {@code row} and {@code col} (the first group equals one)
     */
    public int groupNumber(int col, int row){
        
        // We'll search the position (row - 1, col - 1) of groupMembership and return it's value plus one (because the first group equals to the value zero)
        return (this.groupMembership[row - 1][col - 1] + 1);
    }


    /**
     * Retrieves the sum of the values associated with the specified group.
     *
     * @param group the group number (starting from 1)
     * @requires {@code 1 <= group <= this.numOfGroups}.
     * @return the sum of the values associated with the specified {@code group}.
     */
    public int valueGroup(int group){
        
        // We'll get the value from our groupValues with the index: group - 1
        // The group 1, for example, will have their sum in our groupsValues[0]
        return this.groupsValues[group - 1];
    }


    /**
     * Checks if the given {@code playedGrid} solves the puzzle.
     *
     * @param playedGrid the {@code SumdokuGrid} representing the played grid to be verified
     * @requires {@code playedGrid} must not be null and must have the same dimensions as the puzzle.
     * @return {@code true} if the {@code playedGrid} correctly solves the puzzle, {@code false} otherwise.
     */
    public boolean isSolvedBy(SumdokuGrid playedGrid){
        
        // Because we only have one possible solution for this puzzle, we will find it using the SumdokuSolver class
        SumdokuSolver sumdokuSolver = new SumdokuSolver(this.groupMembership, this.groupsValues);
        int[][] solution = sumdokuSolver.findSolutions(1)[0];
        
        
        /* After investigating the documentation of SumdokuSolver's class, I can
         * suppose that the method 'findSolutions' returns a three-dimension
         * matrix instead of a two-dimension because the first one is to indicate
         * which of the solutions we're talking about. That's why I suppose that
         * if we have the index 0 in the first dimension, we're referring to the
         * first and, in this case, only possible solution. */
        // Now, we must compare each value of the playedGrid with the ones in the solution

        if (!hasSameValues(playedGrid, solution)){
            return false; // If the values of the matrix and SumdokuGrid are different, we return false
        }
        
        return true; // If everything went smoothly, the playedGrid solves the puzzle
    }

    /**
     * Compares the values in the given {@code playedGrid} with the provided solution grid.
     *
     * @param playedGrid the {@code SumdokuGrid} representing the played grid
     * @param solution a two-dimensional integer array representing the solution grid
     * @requires {@code playedGrid} must not be null and must have the same dimensions as {@code solution}.
     * @return {@code true} if all corresponding values in {@code playedGrid} and {@code solution} are the same; {@code false} otherwise.
     */
    private boolean hasSameValues(SumdokuGrid playedGrid, int[][] solution){
        
        // We'll compare each position of the SumdokuGrid with each position of the solution
        for (int r = 0; r < this.puzzleSize; r++){
            for (int c = 0; c < this.puzzleSize; c++){

                // If the values are different, we'll return false
                if (playedGrid.value(r + 1, c + 1) != solution[r][c])
                    return false;
            }
        }

        return true; // If everything went smoothly, we have the same values
    }


    /**
     * Checks if the given {@code playedGrid} partially solves the puzzle.
     * 
     * A grid is considered partially solved if all non-zero values in the {@code playedGrid} match the corresponding values in the solution grid.
     *
     * @param playedGrid the {@code SumdokuGrid} representing the played grid to be verified
     * @requires {@code playedGrid} must not be null and must have the same dimensions as the puzzle.
     * @return {@code true} if the {@code playedGrid} partially solves the puzzle, {@code false} otherwise.
     */
    public boolean isPartiallySolvedBy(SumdokuGrid playedGrid){
        
        // We'll use the same process than in 'isSolvedBy' but adding 0 as an excluded value of the funtion 'hasSameValues'
        // Because 0 is the default value in the SumdokuGrid, meaning the user hasn't filled the cell yet
        SumdokuSolver sumdokuSolver = new SumdokuSolver(this.groupMembership, this.groupsValues);
        int[][] solution = sumdokuSolver.findSolutions(1)[0];

        if (!hasSameValues(playedGrid, solution, 0)){
            return false; // If the values of the matrix and SumdokuGrid are different, we return false
        }

        return true; // If everything went smoothly, the playedGrid solves the puzzle
    }

    /**
     * Compares the values in the given {@code playedGrid} with the provided solution grid, 
     * allowing for an excluded value to be ignored during the comparison
     *
     * @param playedGrid the {@code SumdokuGrid} representing the played grid
     * @param solution a two-dimensional integer array representing the solution grid
     * @param excludedValue the value to be ignored in the comparison
     * @requires {@code playedGrid} must not be null and must have the same dimensions as {@code solution}.
     * @return {@code true} if all corresponding values in {@code playedGrid} and {@code solution} are the same, 
     *         ignoring positions where either grid contains the {@code excludedValue}. {@code false} otherwise
     */
    private boolean hasSameValues(SumdokuGrid playedGrid, int[][] solution, int excludedValue){
        
        // We'll compare each position of the SumdokuGrid with each position of the solution
        for (int r = 0; r < this.puzzleSize; r++){
            for (int c = 0; c < this.puzzleSize; c++){

                // If the values are different, we'll return false (except if the excludedValue is in any of both object's position)
                if (playedGrid.value(r + 1, c + 1) != solution[r][c] && !(playedGrid.value(r + 1, c + 1) == excludedValue || solution[r][c] == excludedValue))
                    return false;
            }
        }

        return true; // If everything went smoothly, we have the same values (excluding if there was in any position an excludedValue)
    }


    /**
     * Converts the puzzle clues (group membership and groups values) into a string representation.
     *
     * @return a {@code String} representing the puzzle's group membership followed by the groups values as clues.
     */
    public String cluesToString(){
        
        // We'll first create a StringBuilder that we'll be editing throught this function
        StringBuilder result = new StringBuilder(); 

        // We add the matrix with the groups corresponding to each cell to the result (we must increment 1 to every value of the group membership)
        result.append(matrixToString(this.groupMembership, " ", 1)); // Note that these two addittional arguments will be explained on the function's javadoc
        
        // We add a line break
        result.append("\n");

        // We now add the groups clues
        result.append(groupsClues(this.groupsValues));

        // We finally return our result converted to a String
        return result.toString();
    }

    /**
     * Converts a two-dimension integer-only matrix into a string representation, with each element modified by a specified incrementation.
     * Each row is prefixed with a starting mark and followed by the transformed values.
     *
     * @param matrix a two-dimensional integer-only matrix to be converted into a string
     * @param startingMark the string to prefix each row of the matrix
     * @param incrementation the value to add to each element when converting it to a string
     * @requires {@code matrix} must not be null, must be a valid two-dimension matrix and must have at least one value
     * @return a {@code String} representing the matrix with the starting mark and the incremented values of each element.
     */
    private String matrixToString(int[][] matrix, String startingMark, int incrementation){

        // We'll first create a StringBuilder that we'll be editing throught this function
        StringBuilder result = new StringBuilder(); // Note that we won't do a line break at the start, as indicated in our project instructions

        // First, we'll add the first row, that it's always there
        result.append(startingMark);
        result.append(arrayToString(matrix[0], incrementation));

        // We'll now add the rest of the rows with a line break, a starting mark and the row with our incrementation on each value (using 'arrayToString')
        for (int r = 1; r < matrix.length; r++){
            result.append("\n");
            result.append(startingMark);
            result.append(arrayToString(matrix[r], incrementation));
        }

        // We finally return our result converted to a String
        return result.toString();
    }

    /**
     * Converts a one-dimensional integer-only array into a string representation, 
     * with each element incremented by a specified value
     *
     * @param array a one-dimensional integer array to be converted into a string
     * @param incrementation the value to add to each element in the array
     * @requires {@code array} must not be null, must be a valid array and must contain at least one element.
     * @return a {@code String} representing the array with each element incremented by the specified value
     */
    private String arrayToString(int[] array, int incrementation){

        // We'll first create a StringBuilder that we'll be editing throught this function
        StringBuilder result = new StringBuilder(); 

        // We append the first value, that is always there (always doing the given incrementation)
        result.append(array[0] + incrementation);

        // Now, we can append the other values of the array (this time with a space before the value)
        for (int i = 1; i < array.length; i++){
            result.append(" ");
            result.append(array[i] + incrementation);
        }

        // We finally return our result converted to a String
        return result.toString();
    }

    /**
     * Converts the groups values into a string of clues, with each clue separated by a space.
     *
     * @param groupsValues an array of integers representing the sum values of each group
     * @requires {@code groupsValues} must not be null and must contain at least one element.
     * @return a {@code String} representing the group clues, each separated by a space.
     */
    private String groupsClues(int[] groupsValues){

        // We'll first create a StringBuilder that we'll be editing throught this function
        StringBuilder result = new StringBuilder();

        // We can append the clues of every group
        for (int g = 1; g <= groupsValues.length; g++){
            result.append(groupClue(groupsValues, g));
            result.append(" ");
        }

        // Following the project tests, we must end with a line break
        result.append("\n");

        // We finally return our result converted to a String
        return result.toString();
    }

    /**
     * Creates a string representation of a group clue, formatted as "G<group> = <value>".
     *
     * @param groupsValues an array of integers representing the values of each group
     * @param group the group number (starting from 1)
     * @requires {@code groupsValues} must not be null, must contain at least {@code group} elements, and {@code group > 0}
     * @return a {@code String} representing the clue for the specified group in the format "G<group> = <value>".
     */
    private String groupClue(int[] groupsValues, int group){

        // We'll first create a StringBuilder that we'll be editing throught this function
        StringBuilder result = new StringBuilder("G"); // We already put the prefix of our clue: 'G'

        // Now, we add the group number
        result.append(group);

        // We add the equal sign and spaces
        result.append(" = ");

        // We add the corresponding value of our group
        result.append(groupsValues[group-1]); // We must subtract 1 because the position with a certain index is about the group with that index plus 1

        // We finally return our result converted to a String
        return result.toString();
    }


    /**
     * Returns a string representation of the Sumdoku puzzle (i.e., the solution of the puzzle)
     * The puzzle is solved using the {@code SumdokuSolver} class and then formatted as a string
     * using the {@code matrixToString} method.
     *
     * @return a {@code String} representing the solved puzzle in matrix form.
     */
    public String toString(){
        
        /*
         * In order to display the SumdokuPuzzle, we're going to find the solution, based
         * on the information we have (check the function 'isSolvedBy' to get additional
         * information), and we're going to return it as a String with the help of a 
         * slightly modified previously created function: 'matrixToString'
        */

        // Because we only have one possible solution for this puzzle, we will find it using the SumdokuSolver class
        SumdokuSolver sumdokuSolver = new SumdokuSolver(this.groupMembership, this.groupsValues);
        int[][] solution = sumdokuSolver.findSolutions(1)[0];

        // We'll now return the matrix with the solution of the puzzle, so basically: the puzzle
        return matrixToString(solution);
    }

    /**
     * Converts a two-dimension integer-only matrix into a string representation
     *
     * @param matrix a two-dimensional integer-only matrix to be converted into a string
     * @requires {@code matrix} must not be null, must be a valid two-dimension matrix and must have at least one value
     * @return a {@code String} representing the matrix
     */
    private String matrixToString(int[][] matrix){

        // We'll first create a StringBuilder that we'll be editing throught this function
        StringBuilder result = new StringBuilder(); // Note that we won't do a line break at the start, as indicated in our project instructions

        // First, we'll add the first row, that it's always there
        result.append(arrayToString(matrix[0]));

        // We'll now add the rest of the rows with a line break and the row (using 'arrayToString')
        for (int r = 1; r < matrix.length; r++){
            result.append("\n");
            result.append(arrayToString(matrix[r]));
        }

        // We finally return our result converted to a String
        return result.toString();
    }

    /**
     * Converts a one-dimensional integer-only array into a string representation
     *
     * @param array a one-dimensional integer array to be converted into a string
     * @requires {@code array} must not be null, must be a valid array and must contain at least one element.
     * @return a {@code String} representing the array
     */
    private String arrayToString(int[] array){

        // We'll first create a StringBuilder that we'll be editing throught this function
        StringBuilder result = new StringBuilder(); 

        // We append the first value, that is always there
        result.append(array[0]);

        // Now, we can append the other values of the array (this time with a space before the value)
        for (int i = 1; i < array.length; i++){
            result.append(" ");
            result.append(array[i]);
        }

        // We finally return our result converted to a String
        return result.toString();
    }

/*-------------------NOTE 1------------------------
|                                                 |
| We are using 'this.<variableName>' for a better |
|    reading and understanding of the code        |
|                                                 |
-------------------------------------------------*/

}