import java.util.ArrayList;

/**
 * Class for storing game data that are used by GameManager, GameModel, and
 * BoardView
 */
public class GameState {
    private int stoneInPit;
    private int gameRound;
    private ArrayList<Integer> pits;
    private ArrayList<Integer> mancala;
    private Boolean cur_isPlayerA;
    private Boolean next_isPlayerA;
    private Boolean isGameOver;
    private Boolean isInitState;
    private boolean just_undo;
    private int state_status;

    /**
     * Constructor
     */
    public GameState() {
        this.stoneInPit = 0;
        this.gameRound = 0;
        this.pits = null;
        this.mancala = null;
        this.cur_isPlayerA = false;
        this.next_isPlayerA = false;
        this.isGameOver = false;
        this.isInitState = false;
        this.just_undo = false;
        this.state_status = 0;
    }

    /**
     * To check if this GameState object is able to be loaded. ONLY used for
     * init_state of GameManager
     * 
     * @param flag the avalibility of current GameState Object
     */
    public void able_to_load(Boolean flag) { // ONLY use for init_state
        this.isInitState = flag;
    }

    /**
     * Set initial stones for each pit at the begining of the game
     * 
     * @param stoneInPit number of stones that to be set
     */
    public void set_stoneInPit(int stoneInPit) {
        this.stoneInPit = stoneInPit;
    }

    /**
     * change game round variable of the GameState object
     * 
     * @param gameRound the new gameRound number
     */
    public void set_gameRound(int gameRound) {
        this.gameRound = gameRound;
    }

    /**
     * Set the pits array
     * 
     * @param pits to be set as pits array for the current GameState object
     */
    public void set_pits(ArrayList<Integer> pits) {
        this.pits = new ArrayList<Integer>();
        this.pits = pits;
    }

    /**
     * set the mancala array
     * 
     * @param mancala to be set as mancala array for the current GameState object
     */
    public void set_mancala(ArrayList<Integer> mancala) {
        this.mancala = new ArrayList<Integer>();
        this.mancala = mancala;
    }

    /**
     * set current turn of player
     * 
     * @param cur_isPlayerA a boolean variable which true represents playerA, false
     *                      represents playerB
     */
    public void set_cur_isPlayerA(Boolean cur_isPlayerA) {
        this.cur_isPlayerA = cur_isPlayerA;
    }

    /**
     * Set the flag for the GameState object to assert if the game is over
     * 
     * @param isGameOver new flag
     */
    public void set_isGameOver(Boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    /**
     * Set the player order for the next player
     * 
     * @param next_isPlayerA new player order
     */
    public void set_next_isPlayerA(Boolean next_isPlayerA) {
        this.next_isPlayerA = next_isPlayerA;
    }

    /**
     * Set the current state statues
     * @param new_state_status new state statues
     */
    public void set_state_status(int new_state_status) {
        state_status = new_state_status;
    }

    /**
     * Set undo param
     * @param undid did the undo has been done
    */
    public void set_just_undo(boolean undid) {
        this.just_undo = undid;
    }

    /**
     * get just_undo
     * @return just_undo 
    */
    public Boolean get_just_undo() {
        return just_undo;
    }
    /**
     * To get initial stones of each pit
     * 
     * @return stone number
     */
    public int getStoneInPit() {
        return stoneInPit;
    }

    /**
     * To get current game round
     * 
     * @return gameRound number
     */
    public int getGameRound() {
        return gameRound;
    }

    /**
     * To get current pit array
     * 
     * @return pit array
     */
    public ArrayList<Integer> getPits() {
        return pits;
    }

    /**
     * To get current mancala array
     * 
     * @return macala array
     */
    public ArrayList<Integer> getMancala() {
        return mancala;
    }

    /**
     * To get current player order
     * 
     * @return current player order
     */
    public Boolean get_cur_isPlayerA() {
        return cur_isPlayerA;
    }

    /**
     * To get next player order
     * 
     * @return next player order
     */
    public Boolean get_next_isPlayerA() {
        return next_isPlayerA;
    }

    /**
     * To check if the game is over
     * 
     * @return game over statue
     */
    public Boolean getIsGameOver() {
        return isGameOver;
    }

    /**
     * To check if the game state is able to load (for GameManager use ONLY)
     * 
     * @return isInitState
     */
    public Boolean isAble_to_load() {
        return isInitState;
    }

    /**
     * To check the current game state status
     * 
     * @return state_status
     */
    public int get_state_status() {
        return state_status;
    }

}