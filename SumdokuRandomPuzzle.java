import java.util.Random;

public class SumdokuRandomPuzzle {
    
    private int counter;
    private Random rand = new Random();
    SumdokuGrid grid;
    private int count;
    private SumdokuGrid[] puzzleArray;

    public SumdokuRandomPuzzle(int size){

    }

    public int size(){
        return 0;
    }

    public boolean hasNextPuzzle(){
        return true;
    }

    public SumdokuPuzzle nextPuzzle(){
        return null;
    }
}