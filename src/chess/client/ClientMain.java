package chess.client;

import static com.osreboot.ridhvl2.HvlStatics.hvlLoad;

import com.osreboot.hvol2.direct.HvlDirect;
import com.osreboot.hvol2.foundation.client.ClientFragment;
import com.osreboot.hvol2.foundation.client.ClientNetwork;
import com.osreboot.hvol2.foundation.common.fragment.FragmentState;
import com.osreboot.ridhvl2.template.HvlChronology;
import com.osreboot.ridhvl2.template.HvlDisplayWindowed;
import com.osreboot.ridhvl2.template.HvlTemplateI;

import chess.client.foundation.ClientModuleEnvironment;
import chess.client.foundation.ClientModuleLobbyManager;
import chess.client.foundation.ClientModuleMenuLink;
import chess.common.foundation.Descriptor;

public class ClientMain extends HvlTemplateI{

	public static void main(String args[]){
		HvlChronology.registerChronology(HvlDirect.class);
		new ClientMain();
	}

	public ClientMain(){
		super(new HvlDisplayWindowed(144, 1280, 720, "Chess Simulator ECS - Client", true));
	}

	@Override
	public void initialize(){
		hvlLoad("ButtonMask.png");
		
		hvlLoad("INOF.hvlft");

		ClientNetwork.initialize(new Descriptor(), () -> createFragment());

		MenuManager.initialize();
	}

	@Override
	public void update(float delta){
		ClientNetwork.update(delta);
		
		MenuManager.update(delta);
	}
	
	private ClientFragment createFragment(){
		ClientFragment fragment = new ClientFragment();
		
		FragmentState stateLobby = new FragmentState(fragment, Descriptor.STATE_LOBBY);
		stateLobby.add(new ClientModuleMenuLink(MenuManager.menuLobby));
		stateLobby.add(new ClientModuleLobbyManager());
		fragment.add(stateLobby);
		
		FragmentState stateGame = new FragmentState(fragment, Descriptor.STATE_GAME);
		stateGame.add(new ClientModuleMenuLink(MenuManager.menuGame));
		stateGame.add(new ClientModuleEnvironment());
		fragment.add(stateGame);
		
		return fragment;
	}

}
