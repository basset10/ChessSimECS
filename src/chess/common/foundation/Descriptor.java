package chess.common.foundation;

import com.osreboot.hvol2.base.HvlGameInfo;
import com.osreboot.hvol2.foundation.client.ClientDescriptor;
import com.osreboot.hvol2.foundation.server.ServerDescriptor;

public class Descriptor implements ClientDescriptor, ServerDescriptor{

	public static final String
	STATE_LOBBY = "LOBBY",
	STATE_GAME = "GAME";

	public static final String
	KEY_LOBBY_STATUS = "lobby.status",
	KEY_GAME_MOVES = "game.moves";

	@Override
	public HvlGameInfo getGameInfo(){
		return new HvlGameInfo("chess_sim_ecs", "1.0");
	}

	@Override
	public int getTickRate(){
		return 16;
	}

	@Override
	public String getHostAddress(){
		return "localhost";
	}

	@Override
	public int getHostPort(){
		return 25565;
	}

}
