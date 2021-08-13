package chess.client.foundation.menu;

import com.osreboot.hvol2.foundation.client.ClientNetwork;
import com.osreboot.ridhvl2.menu.HvlMenu;
import com.osreboot.ridhvl2.menu.component.HvlButtonLabeled;
import com.osreboot.ridhvl2.menu.component.HvlLabel;
import com.osreboot.ridhvl2.menu.component.HvlSpacer;

import chess.client.ClientMain;

public class MenuMain extends ClientMenuProviderTemplate{

	public MenuMain(){
		super();
		arranger.add(HvlLabel.fromDefault().text("Chess Sim ECS"));
		arranger.add(HvlSpacer.fromDefault());
		arranger.add(HvlButtonLabeled.fromDefault().text("Connect").clicked(b -> {
			ClientNetwork.connect();
			HvlMenu.set(menuConnecting);
		}));
		arranger.add(new HvlSpacer(SPACER_LARGE));
		arranger.add(HvlButtonLabeled.fromDefault().text("Quit").clicked(b -> {
			ClientMain.newest().setExiting();
		}));
		
		menuMain = get();
	}
	
}
