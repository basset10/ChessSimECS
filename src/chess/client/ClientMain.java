package chess.client;

import static com.osreboot.ridhvl2.HvlStatics.hvlEnvironment;
import static com.osreboot.ridhvl2.HvlStatics.hvlLoad;

import org.lwjgl.opengl.Display;

import com.osreboot.hvol2.direct.HvlDirect;
import com.osreboot.hvol2.foundation.client.ClientFragment;
import com.osreboot.hvol2.foundation.client.ClientNetwork;
import com.osreboot.hvol2.foundation.client.menu.ClientMenuController;
import com.osreboot.hvol2.foundation.client.menu.ClientMenuController.Role;
import com.osreboot.hvol2.foundation.common.fragment.FragmentState;
import com.osreboot.hvol2.foundation.common.fragment.state.module.ModuleClientMenuLink;
import com.osreboot.ridhvl2.menu.HvlMenu;
import com.osreboot.ridhvl2.template.HvlChronology;
import com.osreboot.ridhvl2.template.HvlDisplayWindowed;
import com.osreboot.ridhvl2.template.HvlTemplateI;

import chess.client.foundation.ClientModuleEnvironment;
import chess.client.foundation.ClientModuleLobbyManager;
import chess.common.Util;
import chess.client.foundation.menu.ClientMenuProviderTemplate;
import chess.client.foundation.menu.MenuConnecting;
import chess.client.foundation.menu.MenuDisconnected;
import chess.client.foundation.menu.MenuGame;
import chess.client.foundation.menu.MenuLobby;
import chess.client.foundation.menu.MenuMain;
import chess.common.foundation.Descriptor;

public class ClientMain extends HvlTemplateI{

	public static void main(String args[]){
		HvlChronology.registerChronology(HvlDirect.class);
		new ClientMain();
	}

	public static final int
	INDEX_PAWN_WHITE = 0,
	INDEX_ROOK_WHITE = 1,
	INDEX_KNIGHT_WHITE = 2,
	INDEX_BISHOP_WHITE = 3,
	INDEX_QUEEN_WHITE = 4,
	INDEX_KING_WHITE = 5,
	INDEX_PAWN_BLACK = 6,
	INDEX_ROOK_BLACK = 7,
	INDEX_KNIGHT_BLACK = 8,
	INDEX_BISHOP_BLACK = 9,
	INDEX_QUEEN_BLACK = 10,
	INDEX_KING_BLACK = 11,
	INDEX_BUTTON_MASK = 12,
	INDEX_PLASMA = 13;

	public ClientMain(){
		super(new HvlDisplayWindowed(144, 1280, 720, "Chess Simulator ECS - Client", true));
	}

	@Override
	public void initialize(){
		hvlLoad("INOF.hvlft");

		hvlLoad("PawnWhite.png");		// 0
		hvlLoad("RookWhite.png");		// 1
		hvlLoad("KnightWhite.png"); 	// 2
		hvlLoad("BishopWhite.png");		// 3
		hvlLoad("QueenWhite.png");		// 4
		hvlLoad("KingWhite.png");		// 5
		hvlLoad("PawnBlack.png");		// 6
		hvlLoad("RookBlack.png");		// 7
		hvlLoad("KnightBlack.png");		// 8
		hvlLoad("BishopBlack.png");		// 9
		hvlLoad("QueenBlack.png");		// 10
		hvlLoad("KingBlack.png");		// 11

		hvlLoad("ButtonMask.png");		// 12
		hvlLoad("Plasma.png");			// 13

		ClientMenuProviderTemplate.initialize();
		
		ClientMenuController menuController = new ClientMenuController(){
			@Override
			public void update(float delta){
				HvlMenu.operate(delta, hvlEnvironment(ClientMenuProviderTemplate.PADDING_MENU, ClientMenuProviderTemplate.PADDING_MENU,
						Display.getWidth() - 2f * ClientMenuProviderTemplate.PADDING_MENU, Display.getHeight() - 2f * ClientMenuProviderTemplate.PADDING_MENU));
			}
		};
		menuController.add(new MenuMain());
		menuController.add(new MenuConnecting());
		menuController.add(new MenuDisconnected(), Role.DESTINATION_DISCONNECT);
		menuController.add(new MenuGame());
		menuController.add(new MenuLobby());
		HvlMenu.set(ClientMenuProviderTemplate.menuMain);
		
		ClientNetwork.initialize(new Descriptor(), menuController, () -> createFragment());
	}

	@Override
	public void update(float delta){
		ClientNetwork.update(delta);
		
		Util.update();
	}

	private ClientFragment createFragment(){
		ClientFragment fragment = new ClientFragment();

		FragmentState stateLobby = new FragmentState(fragment, Descriptor.STATE_LOBBY);
		stateLobby.add(new ModuleClientMenuLink(ClientMenuProviderTemplate.menuLobby));
		stateLobby.add(new ClientModuleLobbyManager());
		fragment.add(stateLobby);

		FragmentState stateGame = new FragmentState(fragment, Descriptor.STATE_GAME);
		stateGame.add(new ModuleClientMenuLink(ClientMenuProviderTemplate.menuGame));
		stateGame.add(new ClientModuleEnvironment());
		fragment.add(stateGame);

		return fragment;
	}

}
