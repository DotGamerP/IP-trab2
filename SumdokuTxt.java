import java.util.Scanner;

/**
 * The {@code SumdokuTxt} consists in the class which contains the main section of the project
 * 
 * This is the second project of 2024 in IP (Introdução à Programação) on FCUL (Faculdade de Ciências - Universidade de Lisboa)
 * 
 * @author Pedro Reinaldo Mendes - nº63729
 * @author Miguel Cabeça - nº63762
 * @version 1.0
 */  
public class SumdokuTxt {

    /**
     * Entry point for the Sumdoku puzzle program
     * 
     * If a puzzle size is provided as a command-line argument, the program generates a sequence of puzzles of the specified size.
     * The user can play through each puzzle and decide whether to proceed to the next one
     *
     * @param args an optional array of command-line arguments. If provided, the first argument should be 
     *             an integer specifying the size of the Sumdoku puzzle grid.
     */
    public static void main(String[] args) {
        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);
        
        // Check if a command-line argument is provided
        if (args.length > 0) {
            // Parse the first argument to get the puzzle size
            int defArgSize = Integer.parseInt(args[0]);
            
            // Create a RandomSumdokuPuzzle instance with the specified size
            RandomSumdokuPuzzle puzzleArgs = new RandomSumdokuPuzzle(defArgSize);
            
            boolean validToContinue;
            do {
                validToContinue = false;
                
                // Calculate the full grid size (number of cells in the grid)
                int fullGridSize = puzzleArgs.size() * puzzleArgs.size();
                
                // Start the game by playing the current puzzle
                play(puzzleArgs.getCurrentPuzzle(), fullGridSize, scanner);
                
                // Ask the user if they want to play again
                validToContinue = askPlayAgain(puzzleArgs, scanner);

            } while (validToContinue);  // Continue if the user chooses to play again
            
            // Print a message when the user finishes playing
            System.out.println("Obrigado por jogar!!!");
        } else {
            // If no arguments are passed, prompt the user for a puzzle size
            System.out.println("Zero Argumentos Passados (Por favor passe um argumento)");
        }
    }
    
    /**
     * Asks the player if they want to play again.
     *
     * @param puzzleArgs The puzzle manager containing the current and next puzzles.
     * @param reader The Scanner to read user input.
     * @return true if the player wants to play again and there is another puzzle; false otherwise.
     */
    private static boolean askPlayAgain(RandomSumdokuPuzzle puzzleArgs,Scanner reader){     
        // Prompt the user if they want to play again
        System.out.println("Queres jogar outra vez (true/false):");
        
        // Read the user's response (true/false)
        boolean usrOption = reader.nextBoolean();   
        
        // If the user wants to play again
        if (usrOption) {
            // Check if there is a next puzzle available
            if (puzzleArgs.hasNextPuzzle()) {
                // Move to the next puzzle
                puzzleArgs.nextPuzzle();
                return true;  // Indicate that the game should continue
            } else {
                // Inform the user there are no more puzzles
                System.out.println("Não há mais Puzzles para jogar");
            }
        }
        // Return false if the user doesn't want to play again or no puzzles are available
        return false;
    }

    /**
     * Calculates the row index for a given square number in the grid.
     *
     * @param square   The square number (1-based index).
     * @param gridSize The size of the grid.
     * @return The row index of the square.
     * @requires square must be between 1 and gridSize^2; gridSize must be > 0.
     */
    private static int rowOfSquare(int square, int gridSize) { 
        // Calculate the row number based on the square's position in a grid
        int row = (square - 1) / gridSize + 1;
        // Return the calculated row number
        return row;
    }

    /**
     * Calculates the column index for a given square number in the grid.
     *
     * @param square   The square number (1-based index).
     * @param gridSize The size of the grid.
     * @return The column index of the square.
     * @requires square must be between 1 and gridSize^2; gridSize must be > 0.
     */
    private static int columnOfSquare(int square, int gridSize) {
        // Calculate the column number based on the square's position in a grid
        int column = (square - 1) % gridSize + 1;
        // Return the calculated column number
        return column;
    }

    /**
     * Reads an integer from the user, ensuring it is within a specified range.
     *
     * @param min    The minimum allowed value.
     * @param max    The maximum allowed value.
     * @param reader The Scanner to read user input.
     * @return An integer within the range [min, max].
     */
    private static int readIntInInterval (int min, int max, Scanner reader) { 
        // Read the initial input from the user
        int input = reader.nextInt();
        
        // Keep asking for input if the value is outside the valid range
        while (input > max || input < min) {
            // Print error message when input is invalid
            System.out.println("Valor inválido. Tem que estar entre " + min + " e " + max + ".");
            // Read the next input from the user
            input = reader.nextInt();
        }
        
        // Return the valid input
        return input;
    }

    /**
     * Asks the user for the position of the square they want to fill.
     *
     * @param totalSize The total number of squares in the grid.
     * @param reader    The Scanner to read user input.
     * @return The square number chosen by the player.
     */
    private static int askSquarePosition(int totalSize,Scanner reader){
        // Pede o numero da casa a preencher
        System.out.println("Casa a preencher?:");
        int casa=readIntInInterval(1, totalSize, reader);
        return casa;
    }
    
    /**
     * Asks the user for the value they want to fill in a square.
     *
     * @param size   The maximum allowed value.
     * @param reader The Scanner to read user input.
     * @return The value chosen by the player.
     */
    private static int askValue(int size, Scanner reader){
        //Pede o valor que se quer preencher na casa escolhida
        System.out.println("Valor da casa a preencher?:");
        int valor=readIntInInterval(1,size, reader);
        return valor;
    }

    /**
     * Plays a round of the Sumdoku game.
     *
     * @param puzzle      The current puzzle to solve.
     * @param maxAttempts The maximum number of attempts the player has.
     * @param reader      The Scanner to read user input.
     */
    private static void play(SumdokuPuzzle puzzle, int maxAttempts,Scanner reader){
        int size = puzzle.size();
        final int LASTATTEMPT = 1;
        int totalSize=size*size;
        SumdokuGrid playedGrid = new SumdokuGrid(size);

        //Da print as informações das pistas
        System.out.println("Neste jogo a grelha tem tamanho "+size+" e tens estas pistas:");
        System.out.println(puzzle.cluesToString());
        
        //Itera pelo numero de tentativas que o utilizador tem
        for (int i = maxAttempts; i >= LASTATTEMPT; i--) {
            //Ask the user for a square position and value.
            int casa = askSquarePosition(totalSize, reader);
            int value = askValue(size, reader);
            //Define a linha e a coluna da casa escolhida
            int linha = rowOfSquare(casa, size);
            int coluna = columnOfSquare(casa, size);
            //Preenche na grelha do utilizador
            playedGrid.fill(linha, coluna, value);
            //Escreve como esta a grelha do jogador
            System.out.println(playedGrid.toString());
        }
            //verifica se após o puzzle ter sido completado se esta igual ao original
            if (puzzle.isSolvedBy(playedGrid)) {
                System.out.println("Ganhaste!!!!!");
            }else{
                System.out.println("Tentativas Esgotadas. Tenta outra vez!");
            }
    }
}