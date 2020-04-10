import java.util.ArrayList;
import java.util.Stack;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BoardModel {
	
	private Stack<GameState> history;
	private ArrayList<ChangeListener> listeners;
	private GameManager gameManager;
	private final int INTIAL_MARBLES_IN_PITS = 3;
	public BoardModel() {
		history = new Stack<GameState>();
		listeners = new ArrayList<ChangeListener>();
		gameManager = new GameManager();
		gameManager.set_stoneInPit(gameManager.load_current_GameState(), INTIAL_MARBLES_IN_PITS);
		history.push(gameManager.load_current_GameState());
	}
	
	public void attach(ChangeListener c) {
		this.listeners.add(c);
	}
	
	public GameState getState() {
		return history.peek();
	}
	
	public void update(String cell) {
		GameState currentState = history.peek();
		boolean isPlayerA = currentState.get_cur_isPlayerA();
		
		char player = cell.charAt(0);
		int pitNum = Integer.parseInt(cell.substring(1));
		
		int pitLocation = pitNum - 1;
		if(player == 'b' || player == 'B'){
			pitLocation += 6;
		}
		
		if(isPlayerA && pitLocation >= 6) {
			System.out.println("Not your turn. It is player A's turn");
			return;
		}
		if(!isPlayerA && pitLocation <= 5) {
			System.out.println("Not your turn. It is player B's turn");
			return;
		}
		gameManager.playGame(gameManager.load_current_GameState(), pitLocation);
		history.push(gameManager.load_current_GameState());
		for(ChangeListener l : this.listeners) {
			l.stateChanged(new ChangeEvent(this));
		}
	}
	
	public void undo() {
		history.pop();
		GameState prevState = history.peek();
		gameManager.set_stoneInPit(prevState, 0);
	}
	
	public GameManager getGameManager() {
		return this.gameManager;
	}
	
	public GameState getCurrentState() {
		return history.peek();
	}
}
