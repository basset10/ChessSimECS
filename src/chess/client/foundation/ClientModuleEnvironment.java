package chess.client.foundation;

import java.io.Serializable;

import com.osreboot.hvol2.base.HvlKey;
import com.osreboot.hvol2.base.anarchy.HvlIdentityAnarchy;
import com.osreboot.hvol2.direct.HvlDirect;
import com.osreboot.hvol2.foundation.common.fragment.Fragment;
import com.osreboot.hvol2.foundation.common.fragment.FragmentModule;

import chess.common.ecs.Environment;
import chess.common.ecs.PlayerMove;
import chess.common.foundation.Descriptor;

public class ClientModuleEnvironment extends FragmentModule{
	
	private Environment environment;
	
	@Override
	public void initialize(){
		environment = new Environment();
	}

	@Override
	public void update(Fragment fragment, float delta){
		environment.update(delta);
		PlayerMove move = null;
		while((move = environment.readOutgoingMove()) != null){
			HvlDirect.writeTCP(Descriptor.KEY_GAME_MOVES + "." + move.turnNumber, move);
		}
	}
	
	@Override
	public boolean persist(Fragment fragment, HvlIdentityAnarchy identity, String key, Serializable value){
		if(HvlKey.isParent(Descriptor.KEY_GAME_MOVES, key)){
			environment.insertIncomingMove((PlayerMove)value);
		}
		return false;
	}
	
}
