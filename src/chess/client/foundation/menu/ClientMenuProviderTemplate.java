package chess.client.foundation.menu;

import static com.osreboot.ridhvl2.HvlStatics.hvlColor;
import static com.osreboot.ridhvl2.HvlStatics.hvlDraw;
import static com.osreboot.ridhvl2.HvlStatics.hvlFont;
import static com.osreboot.ridhvl2.HvlStatics.hvlQuad;
import static com.osreboot.ridhvl2.HvlStatics.hvlTexture;

import org.newdawn.slick.Color;

import com.osreboot.hvol2.foundation.client.menu.ClientMenuProvider;
import com.osreboot.ridhvl2.menu.HvlComponent;
import com.osreboot.ridhvl2.menu.HvlDefault;
import com.osreboot.ridhvl2.menu.component.HvlArranger;
import com.osreboot.ridhvl2.menu.component.HvlButtonLabeled;
import com.osreboot.ridhvl2.menu.component.HvlLabel;
import com.osreboot.ridhvl2.menu.component.HvlRule;
import com.osreboot.ridhvl2.menu.component.HvlSpacer;
import com.osreboot.ridhvl2.menu.component.HvlButton.HvlButtonState;

import chess.client.ClientMain;
import chess.common.hvl.HvlField;

public class ClientMenuProviderTemplate extends ClientMenuProvider{

	public static final float
	PADDING_MENU = 16f,
	SPACER_LARGE = 32f,
	OFFSET_TEXT_X = 8f,
	OFFSET_TEXT_Y = 4f;
	
	public static HvlComponent menuMain, menuConnecting, menuDisconnected, menuLobby, menuGame;
	
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
	}
	
	protected static void drawMovingGradient(float x, float y, float width, float height, Color color){
		float timer = ClientMain.newest().getTimer().getTotalTime() * 0.1f;
		hvlDraw(hvlQuad(x, y, width, height, 0f, timer, 1f, timer + (height / 4096f)), hvlTexture(ClientMain.INDEX_BUTTON_MASK), color);
	}
	
}
