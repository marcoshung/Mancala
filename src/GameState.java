/*
    GameState.java
    Version 1.14
    Last edit: Ari-14
    add: field: state_status: 0: default state, 1:state with meaningful values
*/
import java.util.ArrayList;

public class GameState {
    private int stoneInPit;
    private int gameRound;
    private ArrayList <Integer> pits;
    private ArrayList <Integer> mancala;
    private Boolean cur_isPlayerA;
    private Boolean next_isPlayerA;
    private Boolean isGameOver;
    private Boolean isInitState;
    private int state_status;

    public GameState(){
        this.stoneInPit=0;
        this.gameRound=0;
        this.pits = null;
        this.mancala = null;
        this.cur_isPlayerA = false;
        this.next_isPlayerA = false;
        this.isGameOver = false;
        this.isInitState = false;
        this.state_status = 0;
    }



    
   
    public void able_to_load(Boolean flag){   // ONLY use for init_state
        this.isInitState = flag;
    }
    public void set_stoneInPit(int stoneInPit){
        this.stoneInPit = stoneInPit;
    }

	public void set_gameRound(int gameRound){
        this.gameRound = gameRound;
    }
    public void set_pits (ArrayList<Integer> pits){
        this.pits = new ArrayList<Integer>();
        this.pits = pits;
    }
    public void set_mancala (ArrayList<Integer> mancala){
        this.mancala = new ArrayList<Integer>();
        this.mancala = mancala;
    }
    public void set_cur_isPlayerA (Boolean cur_isPlayerA){
        this.cur_isPlayerA = cur_isPlayerA;
    }
    public void set_isGameOver (Boolean isGameOver){
        this.isGameOver = isGameOver;
    }
    public void set_next_isPlayerA(Boolean next_isPlayerA){
        this.next_isPlayerA = next_isPlayerA;
    }
    public void set_state_status(int new_state_status){
        state_status = new_state_status;
    }
    public int getStoneInPit(){return stoneInPit;}
    public int getGameRound(){return gameRound;}
    public ArrayList<Integer> getPits(){return pits;}
    public ArrayList<Integer> getMancala(){return mancala;}
    public Boolean get_cur_isPlayerA(){return cur_isPlayerA;}
    public Boolean get_next_isPlayerA(){return next_isPlayerA;}
    public Boolean getIsGameOver(){return isGameOver;}
    public Boolean isAble_to_load(){return isInitState;}
    public int get_state_status(){return state_status;}


    // For test purpose:
    public void print_state(){
        System.out.println("stoneInPit: " + stoneInPit);    
        System.out.println("gameRound: " + gameRound);
        System.out.println("cur_isPlayerA: " + cur_isPlayerA);
        System.out.println("next_isPlayerA: " + next_isPlayerA);
        System.out.println("isGameOver: " + isGameOver);
        System.out.println("isInitState: " + isInitState);
        System.out.println("state_status: " + state_status);
        System.out.println("\n");
    }

}