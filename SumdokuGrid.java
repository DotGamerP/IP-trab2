/**
 * The {@code SumdokuGrid} consists in the class which contains the section of the grid of the game
 * 
 * This is the second project of 2024 in IP (Introdução à Programação) on FCUL (Faculdade de Ciências - Universidade de Lisboa)
 * 
 * @author Pedro Reinaldo Mendes - nº63729
 * @author Miguel Cabeça - nº63762
 * @version 1.0
 */  
public class SumdokuGrid {
    private int[][] grid;
    private int size;

    /**
     * Constructor for the SumdokuGrid class.
     * Initializes an empty grid with the specified size.
     *
     * @param gridSize the size of the grid (number of rows and columns)
     * @requires {@code gridSize > 0}
     */
    public SumdokuGrid(int gridSize) {
        // Initialize a 2D array to represent the grid
        this.grid = new int[gridSize][gridSize];
        
        // Set the grid size
        this.size = gridSize;
    }
    
    /**
     * Gets the value stored in a specific cell of the grid.
     *
     * @param row the row of the cell (1-indexed)
     * @param column the column of the cell (1-indexed)
     * @return the value stored in the specified cell
     * @requires {@code row > 0 && row <= size}
     *           {@code column > 0 && column <= size}
     */
    public int value(int row, int column) {
        // Return the value from the specified row and column, adjusting for 0-based indexing
        return this.grid[row - 1][column - 1];
    }

    /**
     * Fills a specific cell in the grid with the provided value.
     *
     * @param row the row of the cell (1-indexed)
     * @param column the column of the cell (1-indexed)
     * @param value the value to be inserted into the cell
     * @requires {@code row > 0 && row <= size}
     *           {@code column > 0 && column <= size}
     */
    public void fill(int row, int column, int value) {
        // Set the value in the specified cell, adjusting for 0-based indexing
        this.grid[row - 1][column - 1] = value;
    }

    /**
     * Converts the grid into a textual representation.
     * Filled cells display their values, while empty cells display a ".".
     *
     * @return a textual representation of the grid
     */
    public String toString() {
        // Create a StringBuilder to build the grid's string representation
        StringBuilder output = new StringBuilder();
        
        // Get the number of rows and columns in the grid
        int gridSizeRow = this.grid.length;
        int gridSizeColumn = this.grid[0].length;
        
        // Append the grid's top boundary (if applicable)
        // output.append(gridEnds());
        
        // Iterate through each cell in the grid
        for (int r = 1; r <= gridSizeRow; r++) {
            // We add a space before each line (as indicated in the project's instructions)
            output.append(" ");
            for (int c = 1; c <= gridSizeColumn; c++) {
                // Check if the cell is filled and append the appropriate symbol
                if (this.isFilled(r, c)) {
                    output.append(this.value(r, c));  // Append the value if the cell is filled
                } else {
                    output.append(".");  // Append a dot if the cell is empty
                }
                output.append(" ");  // Add space between cells for readability
            }
            output.append("\n");  // Add a newline after each row
        }
        
        // Append the grid's bottom boundary (if applicable)
        // output.append(gridEnds());

        // Return the complete string representation of the grid
        return output.toString();
    }
    
    /**
     * Generates the divider line used in the textual representation of the grid.
     *
     * @return a string representing the divider line of the grid
     */
    private String gridEnds() {
        // Create a StringBuilder to build the divider line
        StringBuilder divider = new StringBuilder();
        
        // Append the appropriate number of dashes for each column in the grid
        for (int i = 0; i < this.grid.length; i++) {
            divider.append("--");
        }
        
        // Append a newline at the end of the divider line
        divider.append("\n");
        
        // Return the complete divider string
        return divider.toString();
    }

    /**
     * Checks if a specific cell in the grid is filled.
     *
     * @param row the row of the cell (1-indexed)
     * @param column the column of the cell (1-indexed)
     * @return {@code true} if the cell is filled (value different from 0), {@code false} otherwise
     * @requires {@code row > 0 && row <= size}
     *           {@code column > 0 && column <= size}
     */
    public boolean isFilled(int row, int column) {
        // Check if the value in the specified cell is different from 0, indicating it is filled
        return this.grid[row - 1][column - 1] != 0;
    }
}
