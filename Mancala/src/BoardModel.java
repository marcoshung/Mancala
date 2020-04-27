
/**
 * BoardModel.java
 * Version 1.21 edited by xuefeng
 * Changes: INTIAL_MARBLES_IN_PITS to regular varible ; Constructor accept field to set init stone.
 * 
*/

import java.util.ArrayList;
import java.util.Stack;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BoardModel {

	private Stack<GameState> history;
	private ArrayList<ChangeListener> listeners;
	private GameManager gameManager;
	private int INTIAL_MARBLES_IN_PITS = 3;
	private int playerAUndoCount, playerBUndoCount = 0;

	public BoardModel(int stones) {
		history = new Stack<GameState>();
		listeners = new ArrayList<ChangeListener>();
		gameManager = new GameManager();
		INTIAL_MARBLES_IN_PITS = stones;
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
		boolean isPlayerA = currentState.get_next_isPlayerA();

		char player = cell.charAt(0);
		int pitNum = Integer.parseInt(cell.substring(1));

		int pitLocation = pitNum - 1;
		if (player == 'b' || player == 'B') {
			pitLocation += 6;
		}
		if (isPlayerA && pitLocation >= 6) {
			System.out.println("Not your turn. It is player A's turn");
			return;
		}
		if (!isPlayerA && pitLocation <= 5) {
			System.out.println("Not your turn. It is player B's turn");
			return;
		}

		// snow's editing
		gameManager.playGame(getCurrentState(), pitLocation);

		GameState toBePushedState = gameManager.load_current_GameState();
		if (toBePushedState.get_state_status() == 1) {
			history.push(toBePushedState);
		}
		// snow's editing done

		for (ChangeListener l : this.listeners) {
			l.stateChanged(new ChangeEvent(this));
		}
	}

	public void undo() {
		// don't do anything if there is only one state in the history, which is the
		// beginning state
		if (history.size() == 1) {
			return;
		}
		GameState currentState = history.peek();
		boolean isPlayerA = currentState.get_cur_isPlayerA();

		if (isPlayerA && playerAUndoCount < 3 && !currentState.get_just_undo()) {
			history.pop();
			GameState prevState = history.peek();
			prevState.set_just_undo(true);
			gameManager.set_stoneInPit(prevState, 0);
			playerAUndoCount++;
			System.out.println(playerAUndoCount);
		} else if (playerBUndoCount < 3 && !currentState.get_just_undo()) {
			history.pop();
			GameState prevState = history.peek();
			prevState.set_just_undo(true);
			gameManager.set_stoneInPit(prevState, 0);
			playerBUndoCount++;
			System.out.println(playerBUndoCount);
		} else if(currentState.get_just_undo()) {
			System.out.println("Cannot undo rn");
		}else {
			System.out.println("All undos have been used");
		}
	}

	public GameManager getGameManager() {
		return this.gameManager;
	}

	public GameState getCurrentState() {
		return history.peek();
	}
}
