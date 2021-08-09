package chess.common.ecs;

public class EntityPiece {
	
	public ComponentPosition pos;
	public PieceType type;
	public TeamColor team;
	
	public EntityPiece(ComponentPosition posArg, PieceType typeArg, TeamColor teamArg) {
		pos = posArg;
		type = typeArg;
		team = teamArg;
	}
	
}
