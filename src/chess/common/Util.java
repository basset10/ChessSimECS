package chess.common;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Util {

	private static boolean leftMouseState = false;
	private static boolean leftMouseClick = false;


	public static int getCursorX(){
		return Mouse.getX();
	}

	public static int getCursorY(){
		return Display.getHeight() - Mouse.getY();
	}

	public static void update() {
		if(leftMouseClick) {
			leftMouseClick = false;
		}else {
			if(!leftMouseState) {
				if(!Mouse.isButtonDown(0)) {
					leftMouseClick = false;
				}else {
					leftMouseClick = true;
				}
			}
		}
		leftMouseState = Mouse.isButtonDown(0);
	}

	public static boolean leftMouseClick() {
		return leftMouseClick;
	}
}



