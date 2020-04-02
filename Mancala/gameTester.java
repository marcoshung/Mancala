import java.util.ArrayList;
import java.util.Formatter;

public class gameTester {

    public static void main(String[] args) {
        GameManager game;
    
        //1. start a new game:
        game = new GameManager();
        printGameState(game.get_StateList());
        printBoard(game.load_current_GameState());

        //2. set stones per pit = 3
        game.set_stoneInPit(3);
        printGameState(game.get_StateList());
        printBoard(game.load_current_GameState());


    }

    public static void printGameState(ArrayList<GameState> thisList){
        //Title:
        System.out.printf("\n\n%-10s %-10s %-10s %s\n", "In List", "gameRound", "isPlayerA", "isGameOver");

        //For Each List:
        for(int i=0; i<thisList.size(); i++){
            GameState state = thisList.get(i);
            System.out.printf("%-10s %-10s %-10s %s\n\n",i , state.getGameRound(), state.getIsPlayerA(), state.getIsGameOver());
        }
    }

    public static void printBoard(GameState state){
        GameState cur_state = state;
        ArrayList<Integer> mancala = state.getMancala();
        ArrayList<Integer> pits = state.getPits();
        int size = pits.size();

        // print pitsB:
        System.out.printf("%-2s", "");
        for(int i = size-1; i>=6; i--){
            System.out.printf("%-2s", pits.get(i));
        }
        System.out.printf("%-2s\n", "");

        System.out.printf("%-1s %-1s %-1s %-1s %-1s %-1s %-1s %-1s\n", mancala.get(1),"a","b","c","d","e","f",mancala.get(0));

        System.out.printf("%-2s", "");
        for(int i = 0; i<6; i++){
            System.out.printf("%-2s", pits.get(i));
        }
        System.out.printf("%-2s\n\n\n", "");

    }



}