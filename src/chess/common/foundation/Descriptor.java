package chess.common.foundation;

import com.osreboot.hvol2.base.HvlGameInfo;
import com.osreboot.hvol2.foundation.client.ClientDescriptor;
import com.osreboot.hvol2.foundation.server.ServerDescriptor;

public class Descriptor implements ClientDescriptor, ServerDescriptor{

	public static final String
	STATE_LOBBY = "LOBBY",
	STATE_GAME = "GAME";

	public static final String
	KEY_LOBBY_STATUS = "lobby.status";

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
		return "73.239.1.166";
	}

	@Override
	public int getHostPort(){
		return 25565;
	}

}
