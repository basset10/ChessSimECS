package chess.client;

import static com.osreboot.ridhvl2.HvlStatics.hvlColor;
import static com.osreboot.ridhvl2.HvlStatics.hvlDraw;
import static com.osreboot.ridhvl2.HvlStatics.hvlEnvironment;
import static com.osreboot.ridhvl2.HvlStatics.hvlFont;
import static com.osreboot.ridhvl2.HvlStatics.hvlQuad;
import static com.osreboot.ridhvl2.HvlStatics.hvlTexture;

import org.lwjgl.opengl.Display;
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

	public static final float
	PADDING_MENU = 16f,
	SPACER_LARGE = 32f,
	OFFSET_TEXT_X = 8f,
	OFFSET_TEXT_Y = 4f;
	
	public static HvlArranger menuMain, menuConnecting, menuLobby, menuGame;
	
	public static void initialize(){
		HvlDefault.put(new HvlArranger(false, 0f, 0.5f));
		HvlDefault.put(new HvlSpacer(8f));
		HvlDefault.put(new HvlLabel(hvlFont(0), "DEFAULT TEXT", Color.white, 1f).offset(OFFSET_TEXT_X, OFFSET_TEXT_Y).align(0f, 0.5f).overrideHeight(32f).set(HvlLabel.TAG_DRAW, (d, e, c) -> {
			hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), hvlColor(0.1f, 1f));
			drawMovingGradient(e.getX(), e.getY(), e.getWidth(), e.getHeight(), hvlColor(0.5f, 1f));
			HvlLabel.DEFAULT_DRAW.run(d, e, c);
		}));
		HvlDefault.put(new HvlButtonLabeled(hvlFont(0), "DEFAULT TEXT", Color.white, 1f, (d, e, b, s) -> {
			if(s == HvlButtonState.OFF) hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), hvlColor(0.5f, 1f));
			if(s == HvlButtonState.HOVER) hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), hvlColor(0.4f, 1f));
			if(s == HvlButtonState.ON) hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), hvlColor(0.3f, 1f));
			drawMovingGradient(e.getX(), e.getY(), e.getWidth(), e.getHeight(), hvlColor(1f, 1f));
		}).offset(OFFSET_TEXT_X, OFFSET_TEXT_Y).align(0f, 0.5f).overrideHeight(32f));
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
		menuMain.add(new HvlSpacer(SPACER_LARGE));
		menuMain.add(HvlButtonLabeled.fromDefault().text("Quit").clicked(b -> {
			ClientMain.newest().setExiting();
		}));
		
		menuConnecting = HvlArranger.fromDefault();
		menuConnecting.add(HvlLabel.fromDefault().text("Connecting..."));
		menuConnecting.add(HvlSpacer.fromDefault());
		menuConnecting.add(HvlButtonLabeled.fromDefault().text("Cancel").clicked(b -> {
			ClientNetwork.disconnect();
			HvlMenu.set(menuMain);
		}));
		
		menuLobby = HvlArranger.fromDefault();
		menuLobby.add(HvlLabel.fromDefault().text("Server Lobby"));
		menuLobby.add(new HvlSpacer(SPACER_LARGE));
		menuLobby.add(HvlLabel.fromDefault().text("Spectators"));
		menuLobby.add(HvlSpacer.fromDefault());
		menuLobby.add(HvlButtonLabeled.fromDefault().align(0f, 0f).overrideHeight(128f).set(HvlButtonLabeled.TAG_UPDATE, (d, e, c) -> {
			String players = "";
			for(FragmentPlayer player : ClientNetwork.getFragment().getPlayers()){
				players += "\\n" + player.uid;
			}
			((HvlButtonLabeled)c).text(players);
			HvlButtonLabeled.DEFAULT_UPDATE.run(d, e, c);
		}).set(HvlButtonLabeled.TAG_DRAW, (d, e, c) -> {
			HvlButtonLabeled.DEFAULT_DRAW.run(d, e, c);
			drawMovingGradient(e.getX(), e.getY(), e.getWidth(), e.getHeight(), hvlColor(0f, 0.5f));
		}));
		menuLobby.add(new HvlSpacer(SPACER_LARGE));
		menuLobby.add(HvlLabel.fromDefault().text("Black"));
		menuLobby.add(HvlSpacer.fromDefault());
		menuLobby.add(HvlButtonLabeled.fromDefault().align(0f, 0f).overrideHeight(64f).set(HvlButtonLabeled.TAG_UPDATE, (d, e, c) -> {
			String players = "";
			for(FragmentPlayer player : ClientNetwork.getFragment().getPlayers()){
				players += "\\n" + player.uid;
			}
			((HvlButtonLabeled)c).text(players);
			HvlButtonLabeled.DEFAULT_UPDATE.run(d, e, c);
		}).set(HvlButtonLabeled.TAG_DRAW, (d, e, c) -> {
			HvlButtonLabeled.DEFAULT_DRAW.run(d, e, c);
			drawMovingGradient(e.getX(), e.getY(), e.getWidth(), e.getHeight(), hvlColor(0f, 0.5f));
		}));
		menuLobby.add(new HvlSpacer(SPACER_LARGE));
		menuLobby.add(HvlLabel.fromDefault().text("White"));
		menuLobby.add(HvlSpacer.fromDefault());
		menuLobby.add(HvlButtonLabeled.fromDefault().align(0f, 0f).overrideHeight(64f).set(HvlButtonLabeled.TAG_UPDATE, (d, e, c) -> {
			String players = "";
			for(FragmentPlayer player : ClientNetwork.getFragment().getPlayers()){
				players += "\\n" + player.uid;
			}
			((HvlButtonLabeled)c).text(players);
			HvlButtonLabeled.DEFAULT_UPDATE.run(d, e, c);
		}).set(HvlButtonLabeled.TAG_DRAW, (d, e, c) -> {
			HvlButtonLabeled.DEFAULT_DRAW.run(d, e, c);
			drawMovingGradient(e.getX(), e.getY(), e.getWidth(), e.getHeight(), hvlColor(0f, 0.5f));
		}));
		menuLobby.add(new HvlSpacer(SPACER_LARGE));
		menuLobby.add(HvlButtonLabeled.fromDefault().text("Disconnect").clicked(b -> {
			ClientNetwork.disconnect();
			HvlMenu.set(menuMain);
		}));
		
		menuGame = HvlArranger.fromDefault();
		
		HvlMenu.set(menuMain);
	}
	
	public static void update(float delta){
		HvlMenu.operate(delta, hvlEnvironment(PADDING_MENU, PADDING_MENU, Display.getWidth() - 2f * PADDING_MENU, Display.getHeight() - 2f * PADDING_MENU));
	}
	
	private static void drawMovingGradient(float x, float y, float width, float height, Color color){
		float timer = ClientMain.newest().getTimer().getTotalTime() * 0.1f;
		hvlDraw(hvlQuad(x, y, width, height, 0f, timer, 1f, timer + (height / 4096f)), hvlTexture(0), color);
	}
	
}
