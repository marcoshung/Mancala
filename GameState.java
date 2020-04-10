
/*
    GameState.java
    Version 1.10
    Last edit: Ari-10
    getters and setters name correction
*/
import java.util.ArrayList;

public class GameState {
    private int stoneInPit;
    private int gameRound;
    private ArrayList<Integer> pits;
    private ArrayList<Integer> mancala;
    private Boolean cur_isPlayerA;
    private Boolean next_isPlayerA;
    private Boolean isGameOver;
    private Boolean isInitState;

    public GameState() {
        this.stoneInPit = 0;
        this.gameRound = 0;
        this.pits = null;
        this.mancala = null;
        this.cur_isPlayerA = false;
        this.next_isPlayerA = false;
        this.isGameOver = false;
        this.isInitState = false;
    }

    public void able_to_load(Boolean flag) { 
        this.isInitState = flag;
    }

    public void set_stoneInPit(int stoneInPit) {
        this.stoneInPit = stoneInPit;
    }

    public void set_gameRound(int gameRound) {
        this.gameRound = gameRound;
    }

    public void set_pits(ArrayList<Integer> pits) {
        this.pits = new ArrayList<Integer>();
        this.pits = pits;
    }

    public void set_mancala(ArrayList<Integer> mancala) {
        this.mancala = new ArrayList<Integer>();
        this.mancala = mancala;
    }

    public void set_cur_isPlayerA(Boolean cur_isPlayerA) {
        this.cur_isPlayerA = cur_isPlayerA;
    }

    public void set_isGameOver(Boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public void set_next_isPlayerA(Boolean next_isPlayerA) {
        this.next_isPlayerA = next_isPlayerA;
    }

    public int getStoneInPit() {
        return stoneInPit;
    }

    public int getGameRound() {
        return gameRound;
    }

    public ArrayList<Integer> getPits() {
        return pits;
    }

    public ArrayList<Integer> getMancala() {
        return mancala;
    }

    public Boolean get_cur_isPlayerA() {
        return cur_isPlayerA;
    }

    public Boolean get_next_isPlayerA() {
        return next_isPlayerA;
    }

    public Boolean getIsGameOver() {
        return isGameOver;
    }

    public Boolean isAble_to_load() {
        return isInitState;
    }

    // For test purpose:
    public void print_state() {
        System.out.println("stoneInPit: " + stoneInPit);
        System.out.println("gameRound: " + gameRound);
        System.out.println("cur_isPlayerA: " + cur_isPlayerA);
        System.out.println("next_isPlayerA: " + next_isPlayerA);
        System.out.println("isGameOver: " + isGameOver);
        System.out.println("isInitState: " + isInitState);
        System.out.println("\n");
    }

}