package chess.common.ecs;

public final class SystemSelectMove {

	private SystemSelectMove() {}
	
	public static void run(Environment e) {
		for(EntityPiece p : e.pieces) {
			System.out.println(p.type.toString());
		}
	}
	
}
