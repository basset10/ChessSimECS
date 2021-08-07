package chess.client;

import static com.osreboot.ridhvl2.HvlStatics.hvlColor;
import static com.osreboot.ridhvl2.HvlStatics.hvlDraw;
import static com.osreboot.ridhvl2.HvlStatics.hvlFont;
import static com.osreboot.ridhvl2.HvlStatics.hvlQuad;

import org.newdawn.slick.Color;

import com.osreboot.hvol2.foundation.client.ClientNetwork;
import com.osreboot.hvol2.foundation.common.fragment.FragmentPlayer;
import com.osreboot.ridhvl2.menu.HvlDefault;
import com.osreboot.ridhvl2.menu.HvlMenu;
import com.osreboot.ridhvl2.menu.component.HvlArranger;
import com.osreboot.ridhvl2.menu.component.HvlButton.HvlButtonState;
import com.osreboot.ridhvl2.menu.component.HvlButtonLabeled;
import com.osreboot.ridhvl2.menu.component.HvlLabel;
import com.osreboot.ridhvl2.menu.component.HvlRule;
import com.osreboot.ridhvl2.menu.component.HvlSpacer;

import chess.common.hvl.HvlField;

public final class MenuManager {

	private MenuManager(){}
	
	public static HvlArranger menuMain, menuConnecting, menuLobby, menuGame;
	
	public static void initialize(){
		HvlDefault.put(new HvlArranger(false, 0f, 0.5f));
		HvlDefault.put(new HvlSpacer(8f));
		HvlDefault.put(new HvlLabel(hvlFont(0), "DEFAULT TEXT", Color.white, 1f).align(0f, 0.5f).overrideHeight(32f).set(HvlLabel.TAG_DRAW, (d, e, c) -> {
			hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), hvlColor(0.1f, 1f));
			HvlLabel.DEFAULT_DRAW.run(d, e, c);
		}));
		HvlDefault.put(new HvlButtonLabeled(hvlFont(0), "DEFAULT TEXT", Color.white, 1f, (d, e, b, s) -> {
			if(s == HvlButtonState.OFF) hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), hvlColor(0.5f, 1f));
			if(s == HvlButtonState.HOVER) hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), hvlColor(0.4f, 1f));
			if(s == HvlButtonState.ON) hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), hvlColor(0.3f, 1f));
		}).align(0f, 0.5f).overrideHeight(32f));
		HvlDefault.put(new HvlRule(true, 1f, 2f, new Color(0.5f, 0.5f, 0.5f)).align(0.5f, 0.5f).overrideHeight(16f));
		HvlDefault.put(new HvlField(hvlFont(0), "", Color.white, 1f, (d, e, b, s) -> {
			if(s == HvlButtonState.OFF) hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), hvlColor(0.5f, 1f));
			if(s == HvlButtonState.HOVER) hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), hvlColor(0.4f, 1f));
			if(s == HvlButtonState.ON) hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), hvlColor(0.3f, 1f));
		}).align(0.5f, 0.5f).overrideSize(256f, 32f));
		
		menuMain = HvlArranger.fromDefault();
		menuMain.add(HvlLabel.fromDefault().text("Chess Sim ECS"));
		menuMain.add(HvlSpacer.fromDefault());
		menuMain.add(HvlButtonLabeled.fromDefault().text("Connect").clicked(b -> {
			ClientNetwork.connect();
			HvlMenu.set(menuConnecting);
		}));
		
		menuConnecting = HvlArranger.fromDefault();
		menuConnecting.add(HvlLabel.fromDefault().text("Connecting..."));
		
		menuLobby = HvlArranger.fromDefault();
		menuLobby.add(HvlLabel.fromDefault().text("Chess Sim ECS Server Lobby"));
		menuLobby.add(HvlSpacer.fromDefault());
		menuLobby.add(HvlLabel.fromDefault().align(0f, 0f).offset(4f, 4f).overrideHeight(512f).set(HvlLabel.TAG_UPDATE, (d, e, c) -> {
			String players = "";
			for(FragmentPlayer player : ClientNetwork.getFragment().getPlayers()){
				players += "\\n" + player.uid;
			}
			((HvlLabel)c).text(players);
			HvlLabel.DEFAULT_UPDATE.run(d, e, c);
		}).set(HvlLabel.TAG_DRAW, (d, e, c) -> {
			hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), hvlColor(0.2f, 1f));
			HvlLabel.DEFAULT_DRAW.run(d, e, c);
		}));
		menuLobby.add(HvlSpacer.fromDefault());
		menuLobby.add(HvlButtonLabeled.fromDefault().text("Disconnect").clicked(b -> {
			ClientNetwork.disconnect();
			HvlMenu.set(menuMain);
		}));
		
		menuGame = HvlArranger.fromDefault();
		
		HvlMenu.set(menuMain);
	}
	
	public static void update(float delta){
		HvlMenu.operate(delta);
	}
	
}
