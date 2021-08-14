package chess.common.ecs;

import static com.osreboot.ridhvl2.HvlStatics.hvlDraw;
import static com.osreboot.ridhvl2.HvlStatics.hvlQuadc;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

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
			
				e.validMoves = getValidMoves(p, e);
				for(ChessCoord c : e.validMoves) {
					System.out.println("Legal move on [ " + c.x + ", " + c.y + " ]");
				}
				
			}
		}		
	}
	
	private static boolean isSpaceFree(Environment e, int x, int y) {
		for(EntityPiece p : e.pieces) {
			if(p.pos.x == x && p.pos.y == y) {
				return false;
			}
		}
		return true;
	}

	private static ArrayList<ChessCoord> getValidMoves(EntityPiece p, Environment e){
		ArrayList<ChessCoord> moves = new ArrayList<ChessCoord>();
		if(p.type == PieceType.ROOK) {
			moves = rookMoveCheck(p, e);
		}
		return moves;
	}
	
	private static ArrayList<ChessCoord> rookMoveCheck(EntityPiece p, Environment e){
		ArrayList<ChessCoord> moves = new ArrayList<ChessCoord>();
		
		//Right squares
		for(int i = p.pos.x+1; i <= 7; i++) {
			boolean escape = false;
			if(isSpaceFree(e, i, p.pos.y)) {
						moves.add(new ChessCoord(i, p.pos.y));
			}else {			
					//Check if piece on this square is opposite the player's color
					escape = true;
				}
			if(escape) break;
			}			
		
		//Left squares
		for(int i = p.pos.x-1; i >= 0; i--) {
			boolean escape = false;
			if(isSpaceFree(e, i, p.pos.y)) {
						moves.add(new ChessCoord(i, p.pos.y));
			}else {				
					escape = true;
				}
			if(escape) break;
			}			
		
		
		//Lower squares
		for(int i = p.pos.y+1; i <= 7; i++) {
			boolean escape = false;
			if(isSpaceFree(e, p.pos.x, i)) {
						moves.add(new ChessCoord(p.pos.x, i));
			}else {				
					escape = true;
				}
			if(escape) break;
			}			
		
		//Upper squares
		for(int i = p.pos.y-1; i >= 0; i--) {
			boolean escape = false;
			if(isSpaceFree(e, p.pos.x, i)) {
						moves.add(new ChessCoord(p.pos.x, i));
			}else {				
					escape = true;
				}
			if(escape) break;
			}			
		
		return moves;
		
	}
	
	

	private static HvlCoord getScreenCoords(int x, int y){
		return new HvlCoord((Display.getWidth() / 2f) + ((x - 4) * SystemRender.SIZE_BOARD_SPACE) + (SystemRender.SIZE_BOARD_SPACE / 2f),
				(Display.getHeight() / 2f) + ((y - 4) * SystemRender.SIZE_BOARD_SPACE) + (SystemRender.SIZE_BOARD_SPACE / 2f));
	}

	private static boolean pieceClicked(EntityPiece p) {	
		if(Util.getCursorX() >= getScreenCoords(p.pos.x, p.pos.y).x - (SystemRender.SIZE_BOARD_SPACE * SystemRender.SIZE_PIECE_SCALE)/2 &&
				Util.getCursorX() <= getScreenCoords(p.pos.x, p.pos.y).x + (SystemRender.SIZE_BOARD_SPACE * SystemRender.SIZE_PIECE_SCALE)/2 &&
				Util.getCursorY() >= getScreenCoords(p.pos.x, p.pos.y).y  - (SystemRender.SIZE_BOARD_SPACE * SystemRender.SIZE_PIECE_SCALE)/2 &&
				Util.getCursorY() <= getScreenCoords(p.pos.x, p.pos.y).y + (SystemRender.SIZE_BOARD_SPACE * SystemRender.SIZE_PIECE_SCALE)/2 &&
				Util.leftMouseClick()) {
			return true;
		}else {
			return false;
		}

	}

}
