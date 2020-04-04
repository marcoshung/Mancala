import java.util.ArrayList;

public class GameManager {

    private final ArrayList<GameState> game_states;

    private int stoneInPit;
    private int gameRound;
    private ArrayList<Integer> pits;
    private ArrayList<Integer> mancala;
    private Boolean cur_isPlayerA;
    private Boolean next_isPlayerA;
    private Boolean isGameOver;
    private Boolean isAvaliableRound;

    private final static int TOTAL_PITS = 12;
    private final static int TOTAL_MANCALA = 2;
    private final static int MAX_STONE_PER = 4;

    //
    public GameManager() {

        game_states = new ArrayList<GameState>();
        init_Game();
        store_GameState();

    }

    public void set_stoneInPit(int stoneInPit) {
        this.stoneInPit = stoneInPit;
        init_Game();
        store_GameState();
    }

    public void playGame(int n_pit) {
        // load data from the latest state to GameManager
        assign_state_toGame(load_current_GameState());

        // run game base on the data
        isAvaliableRound = run_Game(n_pit);

        // store data to a new state
        store_GameState();

    }

    public void undoGame() {

        // undo to which step???
        int target_gameRound = 0;
        /////////////////////////
        load_GameState(target_gameRound);

    }

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

    }

    private boolean run_Game(int n_pit) {
        int take_out_stone;
        int cur_pit = n_pit;
        gameRound++;

        // init turn(at very first round);
        // check turn
        // check stone(>0) in selected pit
        if(!check_game_avalibility(n_pit)){
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

            // mancala check & sepcial case1 check
            check_for_mancalaCase(cur_pit, take_out_stone);
            // special case2 check
            check_for_specialCase2(cur_pit, take_out_stone);

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
        return true;
    }

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

        //check current pit
        if(pits.get(n_pit) == 0){
            System.out.println("Err: current pit has 0 stone");
            return false;
        }


        return true;
    }

    /*
     * check_for_mancalaCase 1. to check if there is the user's mancala involved 2.
     * if so, check if the user can get a free turn
     */
    private void check_for_mancalaCase(int cur_pit, int take_out_stone) {
        int add_to_mancala;
        if (cur_pit == 0 && !cur_isPlayerA) {
            add_to_mancala = mancala.get(1); // for mancala B
            mancala.set(1, add_to_mancala + 1);
            take_out_stone--;
            if (take_out_stone == 0) {
                next_isPlayerA = false; // Special cases1 (B keep play): last drop is your own Mancala
            }
        } else if (cur_pit == 6 && cur_isPlayerA) { // for mancala A
            add_to_mancala = mancala.get(0);
            mancala.set(0, add_to_mancala + 1);
            take_out_stone--;
            if (take_out_stone == 0) {
                next_isPlayerA = true; // Special cases1 (A keep play): last drop is your own Mancala
            }
        }
    }

    /*
     * If the last stone you drop is in an empty pit on your side, you get to take
     * that stone and all of your opponents stones that are in the opposite pit.
     * Place all captured stones in your own Mancala.
     */
    private void check_for_specialCase2(int cur_pit, int take_out_stone) {
        // this round ends after execution
        if (pits.get(cur_pit) == 0 && take_out_stone == 1) {

            int add_to_mancala;
            if (cur_isPlayerA == true && cur_pit <= 5) { // if it ends in A's side

                add_to_mancala = mancala.get(0);
                add_to_mancala += pits.get(11 - cur_pit) + 1;
                take_out_stone = 0;
                pits.set(11 - cur_pit, 0);
                mancala.set(0, add_to_mancala);
                next_isPlayerA = false; // next round will be B's turn

            } else if (cur_isPlayerA == false && cur_pit > 5) { // if it ends in B's side

                add_to_mancala = mancala.get(1);
                add_to_mancala += pits.get(11 - cur_pit) + 1;
                pits.set(11 - cur_pit, 0);
                take_out_stone = 0;
                mancala.set(1, add_to_mancala);
                next_isPlayerA = true; // next round will be A' turn

            }
        }

    }

    private void store_GameState() {
        if (isAvaliableRound) {
            System.out.println("isAvaliableRound: " + isAvaliableRound);
            GameState newState = new GameState();
            newState.set_gameRound(gameRound);
            newState.set_isGameOver(isGameOver);
            newState.set_cur_isPlayerA(cur_isPlayerA);
            newState.set_next_isPlayerA(next_isPlayerA);
            newState.set_mancala(mancala);
            newState.set_pits(pits);
            newState.set_stoneInPit(stoneInPit);

            // add to "state array"
            game_states.add(newState);
        }

    }

    private void load_GameState(int toWhich_gameRound) {
        for (int i = game_states.size(); i >= 0; i--) {
            if (game_states.get(i).getGameRound() == toWhich_gameRound) {
                break;
            } else {
                game_states.remove(i);
            }
        }
    }

    private void assign_state_toGame(GameState cur_state) {
        this.gameRound = cur_state.getGameRound();
        this.isGameOver = cur_state.getIsGameOver();
        this.cur_isPlayerA = cur_state.get_cur_isPlayerA();
        this.next_isPlayerA = cur_state.get_next_isPlayerA();
        this.mancala = cur_state.getMancala();
        this.pits = cur_state.getPits();
        this.stoneInPit = cur_state.getStoneInPit();
    }

    public GameState load_current_GameState() {
        int index = game_states.size() - 1;
        return game_states.get(index);
    }

    // ---------------------------------------
    // for test purpose:
    public ArrayList<GameState> get_StateList() {
        return game_states;
    }

}