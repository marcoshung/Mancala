import java.util.ArrayList;
import java.util.Stack;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BoardModel {
	
	private Stack<GameState> history;
	private ArrayList<ChangeListener> listeners;
	
	public BoardModel() {
		history = new Stack<GameState>();
		listeners = new ArrayList<ChangeListener>();
	}
	
	public void attach(ChangeListener c) {
		this.listeners.add(c);
	}
	
	public GameState getState() {
		return history.peek();
	}
	
	public void update(String cell) {
		char player = cell.charAt(0);
		int pitNum = cell.charAt(1);
		
		int pitLocation = pitNum - 1;
		if(player == 'b' || player == 'B'){
			pitLocation += 6;
		}
		
		ArrayList<Integer> currentState = getState().getPits();
		int marbles = currentState.get(pitLocation);
		currentState.set(pitLocation, 0);
		int position = pitLocation + 1;
		for(int i = 0; i < marbles; i++) {
			if(position > 12) {
				position = 0;
			}
			currentState.set(position, currentState.get(position) + 1);
			position++;
		}
		
		for(ChangeListener l : this.listeners) {
			l.stateChanged(new ChangeEvent(this));
		}
	}
	
	public void undo() {
		history.pop();
	}
	
}
