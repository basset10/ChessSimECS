package chess.client.foundation.menu;

import static com.osreboot.ridhvl2.HvlStatics.hvlColor;

import com.osreboot.hvol2.foundation.client.ClientNetwork;
import com.osreboot.ridhvl2.menu.HvlMenu;
import com.osreboot.ridhvl2.menu.component.HvlButtonLabeled;
import com.osreboot.ridhvl2.menu.component.HvlLabel;
import com.osreboot.ridhvl2.menu.component.HvlSpacer;

import chess.client.foundation.ClientEventMenuInteract;
import chess.common.ecs.TeamColor;
import chess.common.foundation.PlayerChessSim;

public class MenuLobby extends ClientMenuProviderTemplate{

	public MenuLobby(){
		super();
		arranger.add(HvlLabel.fromDefault().text("Server Lobby"));
		arranger.add(new HvlSpacer(SPACER_LARGE));
		arranger.add(HvlLabel.fromDefault().text("Spectators"));
		arranger.add(HvlSpacer.fromDefault());
		arranger.add(HvlButtonLabeled.fromDefault().align(0f, 0f).overrideHeight(128f).set(HvlButtonLabeled.TAG_UPDATE, (d, e, c) -> {
			String players = "";
			for(PlayerChessSim player : ClientNetwork.getFragment().<PlayerChessSim>getPlayers()){
				if(player.team == null)
					players += "\\n" + player.uid + (player.isReady ? " [READY]" : " [ ]");
			}
			((HvlButtonLabeled)c).text(players);
			HvlButtonLabeled.DEFAULT_UPDATE.run(d, e, c);
		}).set(HvlButtonLabeled.TAG_DRAW, (d, e, c) -> {
			HvlButtonLabeled.DEFAULT_DRAW.run(d, e, c);
			drawMovingGradient(e.getX(), e.getY(), e.getWidth(), e.getHeight(), hvlColor(0f, 0.5f));
		}).set(HvlButtonLabeled.TAG_CLICKED, b -> {
			ClientNetwork.getFragment().emit(new ClientEventMenuInteract(ClientEventMenuInteract.Subject.LOBBY_TEAM, null));
		}));
		arranger.add(new HvlSpacer(SPACER_LARGE));
		arranger.add(HvlLabel.fromDefault().text("Black"));
		arranger.add(HvlSpacer.fromDefault());
		arranger.add(HvlButtonLabeled.fromDefault().align(0f, 0f).overrideHeight(64f).set(HvlButtonLabeled.TAG_UPDATE, (d, e, c) -> {
			String players = "";
			for(PlayerChessSim player : ClientNetwork.getFragment().<PlayerChessSim>getPlayers()){
				if(player.team == TeamColor.BLACK)
					players += "\\n" + player.uid + (player.isReady ? " [READY]" : " [ ]");
			}
			((HvlButtonLabeled)c).text(players);
			HvlButtonLabeled.DEFAULT_UPDATE.run(d, e, c);
		}).set(HvlButtonLabeled.TAG_DRAW, (d, e, c) -> {
			HvlButtonLabeled.DEFAULT_DRAW.run(d, e, c);
			drawMovingGradient(e.getX(), e.getY(), e.getWidth(), e.getHeight(), hvlColor(0f, 0.5f));
		}).set(HvlButtonLabeled.TAG_CLICKED, b -> {
			ClientNetwork.getFragment().emit(new ClientEventMenuInteract(ClientEventMenuInteract.Subject.LOBBY_TEAM, TeamColor.BLACK));
		}));
		arranger.add(new HvlSpacer(SPACER_LARGE));
		arranger.add(HvlLabel.fromDefault().text("White"));
		arranger.add(HvlSpacer.fromDefault());
		arranger.add(HvlButtonLabeled.fromDefault().align(0f, 0f).overrideHeight(64f).set(HvlButtonLabeled.TAG_UPDATE, (d, e, c) -> {
			String players = "";
			for(PlayerChessSim player : ClientNetwork.getFragment().<PlayerChessSim>getPlayers()){
				if(player.team == TeamColor.WHITE)
					players += "\\n" + player.uid + (player.isReady ? " [READY]" : " [ ]");
			}
			((HvlButtonLabeled)c).text(players);
			HvlButtonLabeled.DEFAULT_UPDATE.run(d, e, c);
		}).set(HvlButtonLabeled.TAG_DRAW, (d, e, c) -> {
			HvlButtonLabeled.DEFAULT_DRAW.run(d, e, c);
			drawMovingGradient(e.getX(), e.getY(), e.getWidth(), e.getHeight(), hvlColor(0f, 0.5f));
		}).set(HvlButtonLabeled.TAG_CLICKED, b -> {
			ClientNetwork.getFragment().emit(new ClientEventMenuInteract(ClientEventMenuInteract.Subject.LOBBY_TEAM, TeamColor.WHITE));
		}));
		arranger.add(new HvlSpacer(SPACER_LARGE));
		arranger.add(HvlButtonLabeled.fromDefault().text("Toggle Ready").clicked(b -> {
			ClientNetwork.getFragment().emit(new ClientEventMenuInteract(ClientEventMenuInteract.Subject.LOBBY_READY, null));
		}));
		arranger.add(new HvlSpacer(SPACER_LARGE));
		arranger.add(HvlButtonLabeled.fromDefault().text("Disconnect").clicked(b -> {
			ClientNetwork.disconnect();
			HvlMenu.set(menuMain);
		}));
		
		menuLobby = get();
	}
	
}
