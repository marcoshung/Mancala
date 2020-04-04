import java.util.ArrayList;

public class GameState {
    private int stoneInPit;
    private int gameRound;
    private ArrayList <Integer> pits;
    private ArrayList <Integer> mancala;
    private Boolean cur_isPlayerA;
    private Boolean next_isPlayerA;
    private Boolean isGameOver;

    public GameState(){
        this.stoneInPit=0;
        this.gameRound=0;
        this.pits = null;
        this.mancala = null;
        this.cur_isPlayerA = false;
        this.next_isPlayerA = false;
        this.isGameOver = false;
    }


    
   

    public void set_stoneInPit(int stoneInPit){
        this.stoneInPit = stoneInPit;
    }

	public void set_gameRound(int gameRound){
        this.gameRound = gameRound;
    }
    public void set_pits (ArrayList<Integer> pits){
        this.pits = pits;
    }
    public void set_mancala (ArrayList<Integer> mancala){
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
    public int getStoneInPit(){return stoneInPit;}
    public int getGameRound(){return gameRound;}
    public ArrayList<Integer> getPits(){return pits;}
    public ArrayList<Integer> getMancala(){return mancala;}
    public Boolean get_cur_isPlayerA(){return cur_isPlayerA;}
    public Boolean get_next_isPlayerA(){return next_isPlayerA;}
    public Boolean getIsGameOver(){return isGameOver;}

}