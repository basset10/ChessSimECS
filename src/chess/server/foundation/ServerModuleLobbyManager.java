package chess.server.foundation;

import java.io.Serializable;

import com.osreboot.hvol2.base.anarchy.HvlIdentityAnarchy;
import com.osreboot.hvol2.foundation.common.fragment.Fragment;
import com.osreboot.hvol2.foundation.common.fragment.FragmentModule;
import com.osreboot.hvol2.foundation.common.fragment.event.EventPlayerUpdated;
import com.osreboot.hvol2.foundation.server.ServerFragment;

import chess.common.ecs.TeamColor;
import chess.common.foundation.Descriptor;
import chess.common.foundation.PlayerChessSim;
import chess.common.packet.PacketLobbyStatus;

public class ServerModuleLobbyManager extends FragmentModule{
	
	@Override
	public void initialize(){
		
	}

	@Override
	public void update(Fragment fragment, float delta){
		// Move to game state if ready
		int playersBlack = 0;
		int playersBlackReady = 0;
		int playersWhite = 0;
		int playersWhiteReady = 0;
		for(PlayerChessSim player : fragment.<PlayerChessSim>getPlayers()){
			if(player.team == TeamColor.BLACK){
				playersBlack++;
				if(player.isReady) playersBlackReady++;
			}
			if(player.team == TeamColor.WHITE){
				playersWhite++;
				if(player.isReady) playersWhiteReady++;
			}
		}
		if(playersBlack == 1 && playersWhite == 1){
			if(playersBlackReady == playersBlack && playersWhiteReady == playersWhite){
				((ServerFragment)fragment).setState(Descriptor.STATE_GAME);
			}
		}
	}
	
	@Override
	public boolean persist(Fragment fragment, HvlIdentityAnarchy identity, String key, Serializable value){
		// Handle lobby status packets
		if(key.equals(Descriptor.KEY_LOBBY_STATUS)){
			PlayerChessSim player = ((ServerFragment)fragment).getPlayer(identity);
			if(player != null){
				PacketLobbyStatus packet = (PacketLobbyStatus)value;
				if(packet.toggleReady) player.isReady = !player.isReady;
				else player.team = packet.team;
				fragment.emit(new EventPlayerUpdated(player));
			}
		}
		return false;
	}
	
}
