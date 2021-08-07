package chess.client.foundation;

import com.osreboot.hvol2.direct.HvlDirect;
import com.osreboot.hvol2.foundation.common.fragment.Fragment;
import com.osreboot.hvol2.foundation.common.fragment.FragmentModule;

import chess.common.ecs.TeamColor;
import chess.common.foundation.Descriptor;
import chess.common.packet.PacketLobbyStatus;

public class ClientModuleLobbyManager extends FragmentModule{

	@Override
	public void initialize(){}

	@Override
	public void update(Fragment fragment, float delta){
		fragment.getEventsOf(ClientEventMenuInteract.class).forEach(e -> {
			if(e.subject == ClientEventMenuInteract.Subject.LOBBY_TEAM){
				if(e.selection instanceof TeamColor || e.selection == null){
					HvlDirect.writeTCP(Descriptor.KEY_LOBBY_STATUS, new PacketLobbyStatus(false, (TeamColor)e.selection));
				}
			}else if(e.subject == ClientEventMenuInteract.Subject.LOBBY_READY){
				HvlDirect.writeTCP(Descriptor.KEY_LOBBY_STATUS, new PacketLobbyStatus(true, null));
			}
		});
	}

}
