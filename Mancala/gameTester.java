import java.util.ArrayList;

public class gameTester {

    public static void main(String[] args) {
        GameManager game;
   
        //1. start a new game:
        game = new GameManager();
    
        //2. set stones per pit = 3
       /* game.set_stoneInPit(3);

        game.playGame(3);
        game.playGame(4);
        game.playGame(6);
        game.playGame(7);
        game.playGame(5);
        game.playGame(6);
        game.playGame(0);
        game.playGame(1);
        game.playGame(2);
        game.playGame(3);
        game.playGame(4);
        game.playGame(6);
        game.playGame(5);
        */
        //3. set stones per pit = 4
        game.set_stoneInPit(4);

        game.playGame(6);
        game.playGame(5);

        printGameState(game.get_StateList());
        printBoard(game.load_current_GameState());
  


    }

    public static void printGameState(ArrayList<GameState> thisList){
        //Title:
        System.out.printf("\n\n%-10s %-10s %-15s %-10s %s\n", "In List", "gameRound", "cur_isPlayerA", "next_isPlayerA", "isGameOver");

        //For Each List:
        for(int i=0; i<thisList.size(); i++){
            GameState state = thisList.get(i);
            System.out.printf("%-10s %-10s %-15s %-15s %s\n\n",i , state.getGameRound(), state.get_cur_isPlayerA(), state.get_next_isPlayerA(),state.getIsGameOver());
        }
    }

    public static void printBoard(GameState state){
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