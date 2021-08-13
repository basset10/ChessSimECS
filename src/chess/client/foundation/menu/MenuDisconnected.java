package chess.client.foundation.menu;

import com.osreboot.ridhvl2.menu.HvlMenu;
import com.osreboot.ridhvl2.menu.component.HvlButtonLabeled;
import com.osreboot.ridhvl2.menu.component.HvlLabel;
import com.osreboot.ridhvl2.menu.component.HvlSpacer;

public class MenuDisconnected extends ClientMenuProviderTemplate{

	public MenuDisconnected(){
		super();
		arranger.add(HvlLabel.fromDefault().text("Disconnected from the server!"));
		arranger.add(HvlSpacer.fromDefault());
		arranger.add(HvlButtonLabeled.fromDefault().text("RIP...").clicked(b -> {
			HvlMenu.set(menuMain);
		}));
		
		menuDisconnected = get();
	}
	
}
