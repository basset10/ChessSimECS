package chess.common.foundation;

import com.osreboot.hvol2.base.anarchy.HvlIdentityAnarchy;
import com.osreboot.hvol2.foundation.common.fragment.FragmentPlayer;

import chess.common.ecs.TeamColor;

public class FragmentPlayerChessSim extends FragmentPlayer{
	private static final long serialVersionUID = 5157078034965021982L;
	
	public boolean isReady;
	public TeamColor team;
	
	public FragmentPlayerChessSim(HvlIdentityAnarchy identityArg){
		super(identityArg);
		isReady = false;
		team = null;
	}

}
