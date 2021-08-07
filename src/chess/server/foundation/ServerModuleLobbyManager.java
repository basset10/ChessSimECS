package chess.server.foundation;

import com.osreboot.hvol2.base.anarchy.HvlIdentityAnarchy;
import com.osreboot.hvol2.direct.HvlDirect;
import com.osreboot.hvol2.foundation.common.fragment.Fragment;
import com.osreboot.hvol2.foundation.common.fragment.FragmentModule;
import com.osreboot.hvol2.foundation.common.fragment.FragmentPlayer;
import com.osreboot.hvol2.foundation.common.fragment.event.EventPlayerUpdated;
import com.osreboot.hvol2.foundation.server.ServerFragment;

import chess.common.ecs.TeamColor;
import chess.common.foundation.Descriptor;
import chess.common.foundation.FragmentPlayerChessSim;
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
		for(FragmentPlayer player : fragment.getPlayers()){
			if(((FragmentPlayerChessSim)player).team == TeamColor.BLACK){
				playersBlack++;
				if(((FragmentPlayerChessSim)player).isReady) playersBlackReady++;
			}
			if(((FragmentPlayerChessSim)player).team == TeamColor.WHITE){
				playersWhite++;
				if(((FragmentPlayerChessSim)player).isReady) playersWhiteReady++;
			}
		}
		if(playersBlack == 1 && playersWhite == 1){
			if(playersBlackReady == playersBlack && playersWhiteReady == playersWhite){
				((ServerFragment)fragment).setState(Descriptor.STATE_GAME);
			}
		}
	}
	
	public boolean persist(Fragment fragment, String key, HvlIdentityAnarchy identity){
		// Handle lobby status packets
		if(key.equals(Descriptor.KEY_LOBBY_STATUS) && !HvlDirect.getKeys(identity).contains(Descriptor.KEY_LOBBY_STATUS)) return true;
		if(key.equals(Descriptor.KEY_LOBBY_STATUS)){
			FragmentPlayerChessSim player = null;
			for(FragmentPlayer playerSearch : fragment.getPlayers()){
				if(playerSearch.identity.equals(identity)){
					player = (FragmentPlayerChessSim)playerSearch;
					break;
				}
			}
			if(player != null){
				PacketLobbyStatus packet = HvlDirect.getValue(identity, key);
				if(packet.toggleReady) player.isReady = !player.isReady;
				else player.team = packet.team;
				fragment.emit(new EventPlayerUpdated(player));
			}
		}
		return false;
	}
	
}
