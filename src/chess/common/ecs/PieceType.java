package chess.common.ecs;

import chess.client.ClientMain;

public enum PieceType {

	KING(ClientMain.INDEX_KING_BLACK, ClientMain.INDEX_KING_WHITE),
	QUEEN(ClientMain.INDEX_QUEEN_BLACK, ClientMain.INDEX_QUEEN_WHITE),
	ROOK(ClientMain.INDEX_ROOK_BLACK, ClientMain.INDEX_ROOK_WHITE),
	BISHOP(ClientMain.INDEX_BISHOP_BLACK, ClientMain.INDEX_BISHOP_WHITE),
	KNIGHT(ClientMain.INDEX_KNIGHT_BLACK, ClientMain.INDEX_KNIGHT_WHITE),
	PAWN(ClientMain.INDEX_PAWN_BLACK, ClientMain.INDEX_PAWN_WHITE);
	
	public final int textureBlack, textureWhite;
	
	PieceType(int textureBlackArg, int textureWhiteArg){
		textureBlack = textureBlackArg;
		textureWhite = textureWhiteArg;
	}
	
}
