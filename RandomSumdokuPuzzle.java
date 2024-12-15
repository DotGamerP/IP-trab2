import java.util.Random;

public class RandomSumdokuPuzzle {
    
        


    private SumdokuPuzzle[] puzzlesArray;

    private int counter = 0;
    private int gridSize;

    
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
    }
    
    private SumdokuPuzzle[] getPuzzlesSize3(){
        int[][][] puzzlesMembership;
        int[][] puzzlesValues;

        puzzlesMembership = new int[][][] {{{0,1,2},{1,2,0},{2,0,1}},{{0,0,2},{0,1,2},{3,3,4}}};
        puzzlesValues = new int[][] {{5, 5, 2, 5, 1},{5,2,5,5,1}};
        SumdokuPuzzle[] puzzles = new SumdokuPuzzle[puzzlesMembership.length];
        for (int i = 0; i < puzzlesMembership.length; i++) {
            puzzles[i] = new SumdokuPuzzle(puzzlesMembership[i], puzzlesValues[i]);
        }

        return puzzles;
    }

    private SumdokuPuzzle[] getPuzzlesSize5(){
        int[][][] puzzlesMembership;
        int[][] puzzlesValues;
        puzzlesMembership = new int[][][]{{
            {0, 0, 0, 1, 1},
            {2, 2, 0, 1, 1},
            {2, 2, 3, 3, 4},
            {5, 5, 3, 4, 4},
            {5, 5, 6, 6, 6}
        }};
        puzzlesValues = new int[][] {{10, 8, 6, 7, 9, 10, 12}};
        SumdokuPuzzle[] puzzles = new SumdokuPuzzle[puzzlesMembership.length];
        
        
        for (int i = 0; i < puzzlesMembership.length; i++) {
            puzzles[i] = new SumdokuPuzzle(puzzlesMembership[i], puzzlesValues[i]);
        }

        return puzzles;
    }

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

   

    public int size(){
        return this.gridSize;
    }

    public boolean hasNextPuzzle(){
        return this.counter < this.puzzlesArray.length;
        
    }

    public SumdokuPuzzle nextPuzzle(){

        if(this.hasNextPuzzle()){
            return this.puzzlesArray[counter++];
        } else {
            return null;
        }
       
    }
}