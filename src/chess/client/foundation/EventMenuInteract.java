package chess.client.foundation;

import com.osreboot.hvol2.foundation.common.fragment.FragmentEvent;

public class EventMenuInteract extends FragmentEvent{
	private static final long serialVersionUID = -396071421790210683L;

	public static enum Subject{
		LOBBY_TEAM,
		LOBBY_READY
	}
	
	public final Subject subject;
	public final Object selection;
	
	public EventMenuInteract(Subject subjectArg, Object selectionArg){
		super(false);
		subject = subjectArg;
		selection = selectionArg;
	}

}
