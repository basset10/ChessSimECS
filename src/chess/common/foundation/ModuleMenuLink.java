package chess.common.foundation;

import java.util.ArrayList;
import java.util.Arrays;

import com.osreboot.hvol2.foundation.common.fragment.Fragment;
import com.osreboot.hvol2.foundation.common.fragment.FragmentModule;
import com.osreboot.ridhvl2.menu.HvlComponent;
import com.osreboot.ridhvl2.menu.HvlMenu;

public class ModuleMenuLink extends FragmentModule{

	private ArrayList<HvlComponent> menus;
	
	public ModuleMenuLink(HvlComponent... menusArg){
		menus = new ArrayList<>(Arrays.asList(menusArg));
	}
	
	@Override
	public void initialize(){}

	@Override
	public void update(Fragment fragment, float delta){
		if(!menus.contains(HvlMenu.get().get(0))) HvlMenu.set(menus.get(0));
	}
	
}
