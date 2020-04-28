
/*
    GameManager.java
    Version 1.14
    Last edit: Ari-14
    bug fixed: if cur pit is empty.
*/
import java.util.ArrayList;

public class GameManager {

    private GameState init_state;
    private GameState load_this_state;

    private int stoneInPit;
    private int gameRound;
    private Boolean cur_isPlayerA;
    private Boolean next_isPlayerA;
    private Boolean isGameOver;
    private Boolean isAvaliableRound;

    private ArrayList<Integer> pits;
    private ArrayList<Integer> mancala;


    private final static int TOTAL_PITS = 12;
    private final static int TOTAL_MANCALA = 2;
    private final static int MAX_STONE_PER = 4;

    // constructure
    public GameManager() {
        init_state = new GameState();
        //assign_state_toGame(init_state);
        init_Game();
        init_state.able_to_load(true);
        store_GameState_to(init_state);
        System.out.println("GameManager->GameManager(): " + "construct game");
    }

    // get user's order to set initial stones per pit
    public void set_stoneInPit(GameState the_init_state, int stoneInPit) {
        
        assign_state_toGame(the_init_state);

        this.stoneInPit = stoneInPit;
        init_Game();
        this.init_state.able_to_load(false);

        load_this_state = new GameState();
        store_GameState_to(load_this_state);
        System.out.println("GameManager->set_stoneInPit: " + "set stone per pit: " + stoneInPit);
    }

    // play game interface
    public void playGame(GameState cur_state, int n_pit) {

        System.out.println("GameManager->playGame:------------------------START playGame " + "at " + n_pit);
        // load data from the latest state to GameManager
        assign_state_toGame(cur_state);

        // run game base on the data
        isAvaliableRound = run_Game(n_pit);

        // check if isgameOver
        if_process_GameOver();

        // store data to a new state
        load_this_state = new GameState();
        store_GameState_to(load_this_state);

    }

    // initialize variables, pit arrary, and mancala array
    private void init_Game() {

        gameRound = 0;
        // Pits and Stone init
        pits = new ArrayList<Integer>();
        for (int i = 0; i < TOTAL_PITS; i++) {
            pits.add(stoneInPit);
        }
        // mancala init
        mancala = new ArrayList<Integer>();
        for (int i = 0; i < TOTAL_MANCALA; i++) {
            mancala.add(0);
        }

        cur_isPlayerA = false;
        next_isPlayerA = false;
        isGameOver = false;
        isAvaliableRound = true;
        System.out.println("GameManager->init_Game(): ");
    }

    // core method for running game
    private boolean run_Game(int n_pit) {

        gameRound++;

        System.out.println("------------------------------------------------GameRound: " + gameRound + " STARTS");

        int take_out_stone;
        int cur_pit = n_pit;

        // init turn(at very first round);
        // check turn
        // check stone(>0) in selected pit
        if (!check_game_avalibility(n_pit) || isGameOver) {
            System.out.println("GameManager->run_Game: " + "at" + n_pit + " => FALSE");
            System.out.println("Err: check_game_avaliablity || isGameOver ");
            return false;
        }

        // if the cur_pit is empty:
        if(pits.get(cur_pit) == 0){
            System.out.println("run_game error: this pit is empty");
            return false;
        }


        // reset the current pit
        take_out_stone = pits.get(cur_pit);
        pits.set(n_pit, 0);

        // jump to next pit
        cur_pit = (cur_pit + 1) % TOTAL_PITS; // cur_pit++

        // run stones:
        while (take_out_stone > 0) {

            // Special cases check:

            // mancala check & sepcial case1 check => update take_out_stone
            take_out_stone = check_for_mancalaCase(cur_pit, take_out_stone);
            // special case2 check => update take_out_stone
            take_out_stone = check_for_specialCase2(cur_pit, take_out_stone);

            // regular operation
            int cur_stone = pits.get(cur_pit);
            if (take_out_stone > 0) {

                if (cur_stone < MAX_STONE_PER) {
                    pits.set(cur_pit, cur_stone + 1);
                    take_out_stone--;
                    if (take_out_stone == 0) {
                        next_isPlayerA = !cur_isPlayerA;
                    }
                    cur_pit = (cur_pit + 1) % TOTAL_PITS; // cur_pit++
                } else {
                    cur_pit = (cur_pit + 1) % TOTAL_PITS; // cur_pit++
                }
            }

        }
        System.out.println("GameManager->run_Game: " + "at " + n_pit + " => Success");
        return true;
    }

    // check turns, and stones in current pit
    private boolean check_game_avalibility(int n_pit) {

        // set cur_isPlayerA
        if (n_pit < 6) {
            cur_isPlayerA = true;
            if (gameRound == 1)
                next_isPlayerA = true;
        } else {
            cur_isPlayerA = false;
            if (gameRound == 1)
                next_isPlayerA = false;
        }

        // check with outside turn
        if (next_isPlayerA != cur_isPlayerA) {
            System.out.println("Err: Turn Error");
            gameRound--;
            return false;
        }

        // check current pit
        if (pits.get(n_pit) == 0) {
            System.out.println("Err: current pit has 0 stone");
            return false;
        }

        return true;
    }

    /*
     * check_for_mancalaCase 1.to check if there is the user's mancala involved 2.if
     * so, check if the user can get a free turn
     */
    private int check_for_mancalaCase(int cur_pit, int take_out_stone) {
        int add_to_mancala;
        if (cur_pit == 0 && !cur_isPlayerA) {
            System.out.println("GameManager->check_for_mancalaCase: " + "because meet Mancala B");
            add_to_mancala = mancala.get(1); // for mancala B
            mancala.set(1, add_to_mancala + 1);
            take_out_stone--;
            if (take_out_stone == 0) {
                System.out.println("GameManager->check_for_mancalaCase: " + "Player B gets free turn");
                next_isPlayerA = cur_isPlayerA; // Special cases1 (B keep play): last drop is your own Mancala
            }
        } else if (cur_pit == 6 && cur_isPlayerA) { // for mancala A
            System.out.println("GameManager->check_for_mancalaCase: " + "because meet Mancala A");
            add_to_mancala = mancala.get(0);
            mancala.set(0, add_to_mancala + 1);
            take_out_stone--;
            if (take_out_stone == 0) {
                System.out.println("GameManager->check_for_mancalaCase: " + "Player A gets free turn");
                next_isPlayerA = cur_isPlayerA; // Special cases1 (A keep play): last drop is your own Mancala
            }
        }
        return take_out_stone;
    }

    /*
     * Special case: last drop in sameside with empty pit If the last stone you drop
     * is in an empty pit on your side, you get to take that stone and all of your
     * opponents stones that are in the opposite pit. Place all captured stones in
     * your own Mancala.
     */
    private int check_for_specialCase2(int cur_pit, int take_out_stone) {
        // this round ends after execution
        if (pits.get(cur_pit) == 0 && take_out_stone == 1) {

            int add_to_mancala;
            if (cur_isPlayerA == true && cur_pit <= 5) { // if it ends in A's side
                System.out.println("GameManager->check_for_specialCase2: " + "because take_out_stone = "
                        + take_out_stone + " and stone in cur_pit = " + pits.get(cur_pit));

                add_to_mancala = mancala.get(0);
                add_to_mancala += pits.get(11 - cur_pit) + 1;
                take_out_stone = 0;
                pits.set(11 - cur_pit, 0);
                mancala.set(0, add_to_mancala);
                next_isPlayerA = false; // next round will be B's turn

            } else if (cur_isPlayerA == false && cur_pit > 5) { // if it ends in B's side
                System.out.println("GameManager->check_for_specialCase2: " + "because take_out_stone = "
                        + take_out_stone + " and stone in cur_pit = " + pits.get(cur_pit));

                add_to_mancala = mancala.get(1);
                add_to_mancala += pits.get(11 - cur_pit) + 1;
                pits.set(11 - cur_pit, 0);
                take_out_stone = 0;
                mancala.set(1, add_to_mancala);
                next_isPlayerA = true; // next round will be A' turn

            }
        }
        return take_out_stone;
    }

     // the game read data from the game_state array
     private void assign_state_toGame(GameState cur_state) {
        gameRound = cur_state.getGameRound();
        isGameOver = cur_state.getIsGameOver();
        cur_isPlayerA = cur_state.get_cur_isPlayerA();
        next_isPlayerA = cur_state.get_next_isPlayerA();
       
        mancala = new ArrayList<Integer>();
        ArrayList<Integer> oldMancala = cur_state.getMancala();
        for(int i=0; i<oldMancala.size(); i++){
            mancala.add(oldMancala.get(i));
        }

        // set new fucking pits......
        pits = new ArrayList<Integer>();
        ArrayList<Integer> oldPits = cur_state.getPits();
        for(int i=0; i<oldPits.size(); i++){
            pits.add(oldPits.get(i));   
        }
                
        stoneInPit = cur_state.getStoneInPit();
        System.out.println("GameManager->assign_state_toGame...");
    }


    // if run_game executed successfully, store it into game_state array
    private void store_GameState_to(GameState new_state) {
        if (isAvaliableRound) {

            new_state.set_gameRound(gameRound);
            new_state.set_isGameOver(isGameOver);
            new_state.set_cur_isPlayerA(cur_isPlayerA);
            new_state.set_next_isPlayerA(next_isPlayerA);
            
            new_state.set_mancala(mancala);
            new_state.set_pits(pits);

            new_state.set_stoneInPit(stoneInPit);
            new_state.set_state_status(1);
            
        }
    }

   
    // return the latest GameState ==> for BoardView usage.
    public GameState load_current_GameState() {
        if(init_state.isAble_to_load()){
            return init_state;
        }else{
            return load_this_state;
        }
    }

    // check if isGameOver, and add macala
    private void if_process_GameOver() {
        int stones_in_playerA = 0;
        int stones_in_playerB = 0;
        int temp_mancala = 0;
        int i;
        for (i = 0; i < 11; i++) {
            if (i < 6) {
                stones_in_playerA += pits.get(i);
            } else {
                stones_in_playerB += pits.get(i);
            }
        }
        if (stones_in_playerA == 0) { // if A side stones=0, then B gets all his side's stone to his mancala
            isGameOver = true;
            temp_mancala = mancala.get(1);
            mancala.set(1, temp_mancala + stones_in_playerB);
            System.out.println("GameManager->if_process_GameOver: Proceed.  With isGameOver = " + isGameOver);
        } else if (stones_in_playerB == 0) {
            isGameOver = true;
            temp_mancala = mancala.get(0);
            mancala.set(0, temp_mancala + stones_in_playerA);
            System.out.println("GameManager->if_process_GameOver: Proceed.  With isGameOver = " + isGameOver);
        }

    }

 
}