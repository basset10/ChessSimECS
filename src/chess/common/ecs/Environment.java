package chess.common.ecs;

import java.util.ArrayList;

public class Environment {
	
	public ArrayList<EntityPiece> pieces = new ArrayList<EntityPiece>();
	
	public Environment() {
		pieces.add(new EntityPiece(new ComponentPosition(0, 0), PieceType.ROOK, TeamColor.BLACK));
		pieces.add(new EntityPiece(new ComponentPosition(1, 0), PieceType.KNIGHT, TeamColor.BLACK));
		pieces.add(new EntityPiece(new ComponentPosition(2, 0), PieceType.BISHOP, TeamColor.BLACK));
		pieces.add(new EntityPiece(new ComponentPosition(3, 0), PieceType.QUEEN, TeamColor.BLACK));
		pieces.add(new EntityPiece(new ComponentPosition(4, 0), PieceType.KING, TeamColor.BLACK));
		pieces.add(new EntityPiece(new ComponentPosition(5, 0), PieceType.BISHOP, TeamColor.BLACK));
		pieces.add(new EntityPiece(new ComponentPosition(6, 0), PieceType.KNIGHT, TeamColor.BLACK));
		pieces.add(new EntityPiece(new ComponentPosition(7, 0), PieceType.ROOK, TeamColor.BLACK));
		
		pieces.add(new EntityPiece(new ComponentPosition(0, 1), PieceType.PAWN, TeamColor.BLACK));
		pieces.add(new EntityPiece(new ComponentPosition(1, 1), PieceType.PAWN, TeamColor.BLACK));
		pieces.add(new EntityPiece(new ComponentPosition(2, 1), PieceType.PAWN, TeamColor.BLACK));
		pieces.add(new EntityPiece(new ComponentPosition(3, 1), PieceType.PAWN, TeamColor.BLACK));
		pieces.add(new EntityPiece(new ComponentPosition(4, 1), PieceType.PAWN, TeamColor.BLACK));
		pieces.add(new EntityPiece(new ComponentPosition(5, 1), PieceType.PAWN, TeamColor.BLACK));
		pieces.add(new EntityPiece(new ComponentPosition(6, 1), PieceType.PAWN, TeamColor.BLACK));
		pieces.add(new EntityPiece(new ComponentPosition(7, 1), PieceType.PAWN, TeamColor.BLACK));
		
		pieces.add(new EntityPiece(new ComponentPosition(0, 6), PieceType.PAWN, TeamColor.WHITE));
		pieces.add(new EntityPiece(new ComponentPosition(1, 6), PieceType.PAWN, TeamColor.WHITE));
		pieces.add(new EntityPiece(new ComponentPosition(2, 6), PieceType.PAWN, TeamColor.WHITE));
		pieces.add(new EntityPiece(new ComponentPosition(3, 6), PieceType.PAWN, TeamColor.WHITE));
		pieces.add(new EntityPiece(new ComponentPosition(4, 6), PieceType.PAWN, TeamColor.WHITE));
		pieces.add(new EntityPiece(new ComponentPosition(5, 6), PieceType.PAWN, TeamColor.WHITE));
		pieces.add(new EntityPiece(new ComponentPosition(6, 6), PieceType.PAWN, TeamColor.WHITE));
		pieces.add(new EntityPiece(new ComponentPosition(7, 6), PieceType.PAWN, TeamColor.WHITE));
		
		pieces.add(new EntityPiece(new ComponentPosition(0, 7), PieceType.ROOK, TeamColor.WHITE));
		pieces.add(new EntityPiece(new ComponentPosition(1, 7), PieceType.KNIGHT, TeamColor.WHITE));
		pieces.add(new EntityPiece(new ComponentPosition(2, 7), PieceType.BISHOP, TeamColor.WHITE));
		pieces.add(new EntityPiece(new ComponentPosition(3, 7), PieceType.QUEEN, TeamColor.WHITE));
		pieces.add(new EntityPiece(new ComponentPosition(4, 7), PieceType.KING, TeamColor.WHITE));
		pieces.add(new EntityPiece(new ComponentPosition(5, 7), PieceType.BISHOP, TeamColor.WHITE));
		pieces.add(new EntityPiece(new ComponentPosition(6, 7), PieceType.KNIGHT, TeamColor.WHITE));
		pieces.add(new EntityPiece(new ComponentPosition(7, 7), PieceType.ROOK, TeamColor.WHITE));
		
		SystemRender.initialize();
	}
	
	public void update(float delta) {
		SystemSelectMove.run(this);
		SystemRender.run(delta, this);
	}
	
}
