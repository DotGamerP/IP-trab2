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
    

    public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);
        

        if (args.length > 0){
            int defArgSize = Integer.parseInt(args[0]);
            RandomSumdokuPuzzle puzzleArgs = new RandomSumdokuPuzzle(defArgSize);
            boolean validToContinue;
            boolean usrOption;
            do {
                validToContinue = false;
                int fullGridSize = puzzleArgs.size()*puzzleArgs.size();
                
                play(puzzleArgs.getCurrentPuzzle(), fullGridSize, scanner);
                
                
                usrOption = scanner.nextBoolean();
                
                if(usrOption){
                    if(puzzleArgs.hasNextPuzzle()){
                        puzzleArgs.nextPuzzle();
                        validToContinue = true;
                    }else{
                        System.out.println("Não há mais Puzzles para jogar");
                    }
                }

            }while(validToContinue);
            

        }else{
            System.out.println("Zero Argumentos");
        }
        
    }

    /**
    * Calcula o índice da linha para um dado número de quadrado na grelha.
    *
    * @param square   o número do quadrado (índice baseado em 1)
    * @param gridSize o tamanho da grelha
    * @return o número da linha do quadrado
    * @requires square deve estar entre 1 e gridSize^2; gridSize deve ser > 0.
    */
    public static int rowOfSquare(int square, int gridSize) {
        
        int row = (square - 1) / gridSize + 1;
        return row;
    }

    /**
    * Calcula o índice da coluna para um dado número de quadrado na grelha.
    *
    * @param square   o número do quadrado (índice baseado em 1)
    * @param gridSize o tamanho da grelha
    * @return o número da coluna do quadrado
    * @requires square deve estar entre 1 e gridSize^2; gridSize deve ser > 0.
    */
    public static int columnOfSquare(int square, int gridSize) {
        int column= (square - 1) % gridSize+1;
        return column;
    }



    public static int readIntInInterval (int min, int max, Scanner reader) { 
        int input = reader.nextInt(); 
        while (input > max || input < min) {
            System.out.println("Valor inválido. Tem que estar entre " + min + " e " + max + ".");
            input = reader.nextInt();
        }
        return input;
    }

    static void play(SumdokuPuzzle puzzle, int maxAttempts,Scanner reader){
        int tamanho = puzzle.size();
        int tamanhoTotal=tamanho*tamanho;
        int casa;
        int valor;
        SumdokuGrid playedGrid = new SumdokuGrid(tamanho);

        //Da print as informações das pistas
        System.out.println("Neste jogo a grelha tem tamanho "+tamanho+" e tens estas pistas:");
        System.out.println(puzzle.toString());
        System.out.println(puzzle.cluesToString());
        
        //Itera pelo numero de tentativas que o utilizador tem
        for (int i = maxAttempts; i >= 1; i--) {

                
                // Pede o numero da casa a preencher
                System.out.println("Casa a preencher?:");
                casa=readIntInInterval(1, tamanhoTotal, reader);
                
                //pede o valor que se quer preencher na casa escolhida
                System.out.println("Valor da casa a preencher?:");
                valor=readIntInInterval(1,tamanho, reader);

                //Define a linha e a coluna da casa escolhida
                int linha = rowOfSquare(casa, tamanho);
                int coluna = columnOfSquare(casa, tamanho);
                
                //preenche a casa pedida;
                playedGrid.fill(linha, coluna, valor);
                
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