package chess.client;

import com.osreboot.hvol2.direct.HvlDirect;
import com.osreboot.ridhvl2.template.HvlChronology;
import com.osreboot.ridhvl2.template.HvlDisplayWindowed;
import com.osreboot.ridhvl2.template.HvlTemplateI;

import chess.common.Util;

public class ClientMain extends HvlTemplateI{

	//Automatic draw when only kings are on the board
	//50-move rule draw
	//Vote to draw
	//Full server indication
	//CLEANING
	//extras?
	
	//Currently disconnects as soon as the server relays the other player lost connection.
	//May need to add some buffer time in case of packet delay
	

	public static void main(String args[]) {
		HvlChronology.registerChronology(HvlDirect.class);
		new ClientMain();
	}

	public ClientMain() {
		super(new HvlDisplayWindowed(144, 1280, 720, "Chess Simulator", false));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
	}

	@Override
	public void update(float delta) {;		
	}

}
