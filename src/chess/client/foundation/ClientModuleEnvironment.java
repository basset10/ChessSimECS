package chess.client.foundation;

import com.osreboot.hvol2.foundation.common.fragment.Fragment;
import com.osreboot.hvol2.foundation.common.fragment.FragmentModule;

import chess.common.ecs.Environment;

public class ClientModuleEnvironment extends FragmentModule{
	
	private Environment environment;
	
	@Override
	public void initialize(){
		environment = new Environment();
	}

	@Override
	public void update(Fragment fragment, float delta){
		environment.update(delta);
	}
	
}
