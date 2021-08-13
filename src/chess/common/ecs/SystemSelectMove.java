package chess.common.ecs;

import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl2.HvlCoord;

import chess.common.Util;

public final class SystemSelectMove {

	private SystemSelectMove() {}
	
	public static void run(Environment e) {
		for(EntityPiece p : e.pieces) {
			//System.out.println(p.type.toString());
			
			if(pieceClicked(p)) {
				System.out.println("You clicked a " + p.team.toString() + " " + p.type.toString() + " on space [ " +
						p.pos.x + ", " + p.pos.y + " ]");
			}
			
		}
	}
	
	private static HvlCoord getScreenCoords(int x, int y){
		return new HvlCoord((Display.getWidth() / 2f) + ((x - 4) * SystemRender.SIZE_BOARD_SPACE),
				(Display.getHeight() / 2f) + ((y - 4) * SystemRender.SIZE_BOARD_SPACE));
	}
	
	private static boolean pieceClicked(EntityPiece p) {
		
		if(Util.getCursorX() >= getScreenCoords(p.pos.x, p.pos.y).x + (SystemRender.SIZE_BOARD_SPACE / 2f) - (SystemRender.SIZE_BOARD_SPACE * SystemRender.SIZE_PIECE_SCALE)/2 &&
				Util.getCursorX() <= getScreenCoords(p.pos.x, p.pos.y).x + (SystemRender.SIZE_BOARD_SPACE / 2f) + (SystemRender.SIZE_BOARD_SPACE * SystemRender.SIZE_PIECE_SCALE)/2 &&
				Util.getCursorY() >= getScreenCoords(p.pos.x, p.pos.y).y + (SystemRender.SIZE_BOARD_SPACE / 2f) - (SystemRender.SIZE_BOARD_SPACE * SystemRender.SIZE_PIECE_SCALE)/2 &&
				Util.getCursorY() <= getScreenCoords(p.pos.x, p.pos.y).y + (SystemRender.SIZE_BOARD_SPACE / 2f) + (SystemRender.SIZE_BOARD_SPACE * SystemRender.SIZE_PIECE_SCALE)/2 &&
				Util.leftMouseClick()) {
			return true;
		}else {
			return false;
		}
		
	}
	
}
