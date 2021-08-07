package chess.common.packet;

import java.io.Serializable;

import chess.common.ecs.TeamColor;

public class PacketLobbyStatus implements Serializable{
	private static final long serialVersionUID = -714452244669153770L;
	
	public boolean toggleReady;
	public TeamColor team;
	
	public PacketLobbyStatus(boolean toggleReadyArg, TeamColor teamArg){
		toggleReady = toggleReadyArg;
		team = teamArg;
	}

}
