import java.util.ArrayList;

public class GameManager {
    
    private final ArrayList<GameState> game_states;

    private int stoneInPit;
    private int gameRound;
    private ArrayList<Integer> pits;
    private ArrayList<Integer> mancala;
    private Boolean isPlayerA;
    private Boolean isGameOver;

    private final static int TOTAL_PITS = 12;
    private final static int TOTAL_MANCALA = 2;
    private final static int MAX_STONE_PER = 4;

    //
    public GameManager() {

        game_states = new ArrayList<GameState>();
        init_Game();
        store_GameState();

    }

    public void set_stoneInPit(final int stoneInPit) {
        this.stoneInPit = stoneInPit;
        init_Game();
        store_GameState();
    }

    public void playGame(final int n_pit) {

        // from which state:

        gameRound++;
        // run game base on this state
        run_Game(n_pit);

        // store data to a new state
        store_GameState();

    }

    public void undoGame() {

        // undo to which step???
        final int target_gameRound = 0;
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

        isPlayerA = true;

        isGameOver = false;

    }

    private void run_Game(final int n_pit) {
        int out_stone;
        int cur_pit = n_pit;
        int tmp_mancala;

        // check turns:
        if (cur_pit < 6)
            isPlayerA = true;

        // reset the current pit
        out_stone = pits.get(cur_pit);
        pits.set(n_pit, 0);

        // run stones:
        while (out_stone > 0) {

            cur_pit = (cur_pit + 1) % TOTAL_PITS;

            // Meet mancala: mancala operation
            if (cur_pit == 0 && !isPlayerA) {
                tmp_mancala = mancala.get(1); // for mancala B
                mancala.set(1, tmp_mancala + 1);
                out_stone--;
            } else if (cur_pit == 6 && isPlayerA) { // for mancala A
                tmp_mancala = mancala.get(0);
                mancala.set(0, tmp_mancala + 1);
                out_stone--;
            } else {

                // pits operation
                final int cur_stone = pits.get(cur_pit);

                // Special operatation: (last stone to empty pits: get stone from oppenents)
                if (out_stone == 1 && cur_stone == 0) {
                    if (cur_pit < 6 && isPlayerA) { // if in the playerA's side
                        tmp_mancala = mancala.get(0);
                        mancala.set(0, tmp_mancala + 1 + pits.get(11 - cur_pit));
                        isPlayerA = false; // turnaround
                    } else if (cur_pit > 6 && !isPlayerA) { // if in the playerB's side
                        tmp_mancala = mancala.get(1);
                        mancala.set(1, tmp_mancala + 1 + pits.get(11 - cur_pit));
                        isPlayerA = true; // turnaround
                    }
                }
                // regular operation
                if (cur_stone <= MAX_STONE_PER) {
                    pits.set(cur_pit, cur_stone + 1);
                    out_stone--;
                }
            }
        }

        // turn management:
        if (cur_pit < 6 && isPlayerA) {
            isPlayerA = true;
        } else if (cur_pit > 5 && !isPlayerA) {
            isPlayerA = false;
        } else {
            // reverse turn:
            isPlayerA = reverseTurn();
        }
    }

    private boolean reverseTurn() {
        if (isPlayerA)
            return false;
        return true;
    }

    private void store_GameState() {
        final GameState newState = new GameState();
        newState.set_gameRound(gameRound);
        newState.set_isGameOver(isGameOver);
        newState.set_isPlayerA(isPlayerA);
        newState.set_mancala(mancala);
        newState.set_pits(pits);
        newState.set_stoneInPit(stoneInPit);
       
        // add to "state array"
        game_states.add(newState);
    }

    private void load_GameState(final int toWhich_gameRound) {
        for (int i = game_states.size(); i >= 0; i--) {
            if (game_states.get(i).getGameRound() == toWhich_gameRound) {
                break;
            } else {
                game_states.remove(i);
            }
        }
    }

    public GameState load_current_GameState() {
        final int index = game_states.size() - 1;
        return game_states.get(index);
    }






    //---------------------------------------
    // for test purpose:
    public ArrayList<GameState> get_StateList(){
        return game_states;
    }
    
}