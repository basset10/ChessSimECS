package chess.client.foundation.menu;

import com.osreboot.hvol2.foundation.client.ClientNetwork;
import com.osreboot.ridhvl2.menu.HvlMenu;
import com.osreboot.ridhvl2.menu.component.HvlButtonLabeled;
import com.osreboot.ridhvl2.menu.component.HvlLabel;
import com.osreboot.ridhvl2.menu.component.HvlSpacer;

public class MenuConnecting extends ClientMenuProviderTemplate{

	public MenuConnecting(){
		super();
		arranger.add(HvlLabel.fromDefault().text("Connecting..."));
		arranger.add(HvlSpacer.fromDefault());
		arranger.add(HvlButtonLabeled.fromDefault().text("Cancel").clicked(b -> {
			ClientNetwork.disconnect();
			HvlMenu.set(menuMain);
		}));
		
		menuConnecting = get();
	}
	
}
