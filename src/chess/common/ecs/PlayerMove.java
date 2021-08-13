package chess.common.ecs;

import java.io.Serializable;

public class PlayerMove implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public int currentX;
	public int currentY;
	public int intendedX;
	public int intendedY;
	public int turnNumber;
	
	public PlayerMove(int currentXArg, int currentYArg, int intendedXArg, int intendedYArg, int turnNumberArg) {
		currentX = currentXArg;
		currentY = currentYArg;
		intendedX = intendedXArg;
		intendedY = intendedYArg;
		turnNumber = turnNumberArg;
	}

}
