/*
    gameTester.java
    Version 1.1
    Last edit: Ari-05
*/

import java.util.ArrayList;

public class gameTester {

    public static void main(String[] args) {
        GameManager gameManager1;
        GameState init_game_state;
        GameState set_stone_state;
        GameState diff_manager_state;
        
        gameManager1 = new GameManager();
        init_game_state = gameManager1.load_current_GameState();

        gameManager1.set_stoneInPit(init_game_state, 3);
        set_stone_state = gameManager1.load_current_GameState();

        gameManager1.playGame(set_stone_state, 0);
        diff_manager_state = gameManager1.load_current_GameState();

        init_game_state.print_state();
        set_stone_state.print_state();
        diff_manager_state.print_state();


        
        printBoard(init_game_state);
        printBoard(set_stone_state);
        printBoard(diff_manager_state);
    







        /*
         printGameState(game_state);
         printGameState(game_state1);
         printGameState(game_state2);
        */
       
        



    }

    public static void printGameState(GameState state){
        //Title:
        System.out.printf("\n\n%-10s %-15s %-10s %-10s %s\n", "gameRound", "cur_isPlayerA", "next_isPlayerA", "isGameOver","init_state isAble_to_load");

        //For Each List:
        
        System.out.printf("%-10s %-15s %-15s %-10s %s\n\n" , state.getGameRound(), state.get_cur_isPlayerA(), state.get_next_isPlayerA(),state.getIsGameOver(),state.isAble_to_load());
        
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