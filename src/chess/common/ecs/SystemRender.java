package chess.common.ecs;

import static com.osreboot.ridhvl2.HvlStatics.hvlColor;
import static com.osreboot.ridhvl2.HvlStatics.hvlDraw;
import static com.osreboot.ridhvl2.HvlStatics.hvlFont;
import static com.osreboot.ridhvl2.HvlStatics.hvlQuad;
import static com.osreboot.ridhvl2.HvlStatics.hvlQuadc;
import static com.osreboot.ridhvl2.HvlStatics.hvlRotate;
import static com.osreboot.ridhvl2.HvlStatics.hvlTexture;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import com.osreboot.hvol2.foundation.client.ClientNetwork;
import com.osreboot.ridhvl2.HvlCoord;

import chess.client.ClientMain;
import chess.common.foundation.PlayerChessSim;
import chess.common.hvl.HvlRenderFrame;
import chess.common.hvl.HvlShader;

public final class SystemRender {

	private SystemRender(){}

	public static final float
	SIZE_BOARD_SPACE = 64f,
	SIZE_PIECE_SCALE = 0.8f,
	SPACING_PLAYER_UI = 32f,
	WIDTH_PLAYER_UI_NAME = 256f,
	WIDTH_PLAYER_UI_ROLE = 96f,
	WIDTH_PLAYER_UI = WIDTH_PLAYER_UI_NAME + WIDTH_PLAYER_UI_ROLE,
	HEIGHT_PLAYER_UI_NAME = 32f,
	HEIGHT_PLAYER_UI_ROLE = 20f,
	HEIGHT_PLAYER_UI = HEIGHT_PLAYER_UI_NAME + HEIGHT_PLAYER_UI_ROLE;
	
	private static HvlCoord lastDisplaySize;
	
	private static HvlRenderFrame renderFrameReflection;
	private static HvlShader shaderReflection;
	
	public static void initialize(){
		initializeRenderFrames();

		shaderReflection = new HvlShader("res/shader/Reflection.frag");
//		System.out.println(shaderReflection.getFragLog());
	}
	
	private static void initializeRenderFrames(){
		lastDisplaySize = new HvlCoord(Display.getWidth(), Display.getHeight());

		renderFrameReflection = new HvlRenderFrame(Display.getWidth(), Display.getHeight());
	}
	
	public static void run(float delta, Environment environment){
		if(lastDisplaySize.x != Display.getWidth() || lastDisplaySize.y != Display.getHeight()){
			initializeRenderFrames();
		}
		
		renderFrameReflection.doCapture(() -> {
			// Render background
			float backgroundXOffset = ClientMain.newest().getTimer().getTotalTime() / 64f;
			hvlDraw(hvlQuad(0, 0, Display.getWidth(), Display.getHeight(),
					backgroundXOffset, 0f, Display.getWidth() / 2048f + backgroundXOffset, Display.getHeight() / 2048f),
					hvlTexture(ClientMain.INDEX_PLASMA), hvlColor(0.4f, 1f));
			
			// Render player names / tags
			PlayerChessSim playerBlack = null;
			PlayerChessSim playerWhite = null;
			ArrayList<PlayerChessSim> playerSpectators = new ArrayList<>();
			for(PlayerChessSim player : ClientNetwork.getFragment().<PlayerChessSim>getPlayers()){
				if(player.team == TeamColor.BLACK) playerBlack = player;
				else if(player.team == TeamColor.WHITE) playerWhite = player;
				else playerSpectators.add(player);
			}
			if(playerBlack != null) drawPlayerTag(90f, getScreenCoords(8, 0).x + HEIGHT_PLAYER_UI + SPACING_PLAYER_UI, getScreenCoords(8, 0).y, playerBlack);
			if(playerWhite != null) drawPlayerTag(-90f, getScreenCoords(0, 8).x - HEIGHT_PLAYER_UI - SPACING_PLAYER_UI, getScreenCoords(0, 8).y, playerWhite);
			float playerSpectatorXOffset = 0f;
			for(PlayerChessSim player : playerSpectators){
				drawPlayerTag(0f, playerSpectatorXOffset + SPACING_PLAYER_UI, SPACING_PLAYER_UI, player);
				playerSpectatorXOffset += WIDTH_PLAYER_UI + SPACING_PLAYER_UI;
			}

			// Render board
			for(int x = 0; x < 8; x++){
				for(int y = 0; y < 8; y++){
					HvlCoord screen = getScreenCoords(x, y);
					hvlDraw(hvlQuad(screen.x, screen.y, SIZE_BOARD_SPACE, SIZE_BOARD_SPACE), hvlColor((x + y) % 2 == 1 ? 0.4f : 0.8f, 1f));
				}
			}

			// Render pieces
			for(EntityPiece piece : environment.pieces){
				HvlCoord screen = getScreenCoords(piece.pos.x, piece.pos.y);
				hvlDraw(hvlQuadc(screen.x + (SIZE_BOARD_SPACE / 2f), screen.y + (SIZE_BOARD_SPACE / 2f), SIZE_BOARD_SPACE * SIZE_PIECE_SCALE, SIZE_BOARD_SPACE * SIZE_PIECE_SCALE),
						hvlTexture(piece.team == TeamColor.BLACK ? piece.type.textureBlack : piece.type.textureWhite));
			}
		});
		
		shaderReflection.doShade(() -> {
			shaderReflection.send("resolution", new HvlCoord(Display.getWidth(), Display.getHeight()));
			shaderReflection.send("locationMirror", getScreenCoords(0, 8).y + 8f);
			
			hvlDraw(hvlQuad(0, 0, Display.getWidth(), Display.getHeight(), 0, 1, 1, 0), renderFrameReflection.getTexture());
		});
	}

	private static HvlCoord getScreenCoords(int x, int y){
		return new HvlCoord((Display.getWidth() / 2f) + ((x - 4) * SIZE_BOARD_SPACE),
				(Display.getHeight() / 2f) + ((y - 4) * SIZE_BOARD_SPACE));
	}

	private static void drawPlayerTag(float rotate, float x, float y, PlayerChessSim player){
		float valueTeam = player.team == TeamColor.BLACK ? 0.1f : player.team == TeamColor.WHITE ? 0.9f : 0.5f;
		float valueTeamFont = player.team == TeamColor.BLACK ? 0.9f : player.team == TeamColor.WHITE ? 0.1f : 1f;
		String nameTeam = player.team == TeamColor.BLACK ? "BLACK" : player.team == TeamColor.WHITE ? "WHITE" : "SPECTATOR";
		
		hvlRotate(x, y, rotate, () -> {
			hvlDraw(hvlQuad(x - 1f, y - 1f, WIDTH_PLAYER_UI + 2f, HEIGHT_PLAYER_UI_NAME + 2f), hvlColor(1f, 1f));
			hvlDraw(hvlQuad(x - 1f, y + HEIGHT_PLAYER_UI_NAME - 1f, WIDTH_PLAYER_UI_ROLE + 2f, HEIGHT_PLAYER_UI_ROLE + 2f), hvlColor(1f, 1f));
			hvlDraw(hvlQuad(x, y, WIDTH_PLAYER_UI, HEIGHT_PLAYER_UI_NAME), hvlColor(0.2f, 1f));
			hvlDraw(hvlQuad(x, y + HEIGHT_PLAYER_UI_NAME, WIDTH_PLAYER_UI_ROLE, HEIGHT_PLAYER_UI_ROLE), hvlColor(valueTeam, 1f));
			hvlFont(0).draw(player.uid, x + 4f, y + 6f);
			hvlFont(0).draw(nameTeam, x + 4f, y + HEIGHT_PLAYER_UI_NAME + 6f, hvlColor(valueTeamFont, 1f), 0.5f);
		});
	}

}
