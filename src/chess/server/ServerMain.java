package chess.server;

import static com.osreboot.ridhvl2.HvlStatics.hvlLoad;

import com.osreboot.hvol2.direct.HvlDirect;
import com.osreboot.hvol2.foundation.common.fragment.FragmentState;
import com.osreboot.hvol2.foundation.server.ServerBalancer;
import com.osreboot.hvol2.foundation.server.ServerFragment;
import com.osreboot.hvol2.foundation.server.ServerNetwork;
import com.osreboot.hvol2.foundation.server.balancer.ServerBalancerSingleton;
import com.osreboot.ridhvl2.template.HvlChronology;
import com.osreboot.ridhvl2.template.HvlDisplayWindowed;
import com.osreboot.ridhvl2.template.HvlTemplateI;

import chess.common.foundation.Descriptor;
import chess.common.foundation.PlayerChessSim;
import chess.server.foundation.ServerModuleEnvironment;
import chess.server.foundation.ServerModuleLobbyManager;

public class ServerMain extends HvlTemplateI{
	
	public static void main(String args[]){
		HvlChronology.registerChronology(HvlDirect.class);
		new ServerMain();
	}

	public ServerMain(){
		super(new HvlDisplayWindowed(144, 300, 300, "Chess Simulator ECS - Server", false));
	}

	@Override
	public void initialize(){
		hvlLoad("INOF.hvlft");
		
		ServerBalancer balancer = new ServerBalancerSingleton(() -> createFragment());
		ServerNetwork.initialize(new Descriptor(), balancer);
	}

	@Override
	public void update(float delta){
		ServerNetwork.update(delta);
	}
	
	private ServerFragment createFragment(){
		ServerFragment fragment = new ServerFragment(i -> new PlayerChessSim(i));
		
		FragmentState stateLobby = new FragmentState(fragment, Descriptor.STATE_LOBBY);
		stateLobby.add(new ServerModuleLobbyManager());
		fragment.add(stateLobby);
		
		FragmentState stateGame = new FragmentState(fragment, Descriptor.STATE_GAME);
		stateGame.add(new ServerModuleEnvironment());
		fragment.add(stateGame);
		
		fragment.setState(Descriptor.STATE_LOBBY);
		return fragment;
	}
	
}
