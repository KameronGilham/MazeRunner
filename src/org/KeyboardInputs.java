package org;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardInputs {
	boolean up = false;
	boolean down = false;
	boolean right = false;
	boolean left = false;

	public void keyboardInput(KeyEvent key, GraphicsContext gc) {
		if (key.getEventType() == KeyEvent.KEY_PRESSED) {
			if (key.getCode() == KeyCode.UP) {
				up = true;
			}
			if (key.getCode() == KeyCode.DOWN) {
				down = true;
			}
			if (key.getCode() == KeyCode.RIGHT) {
				right = true;
			}
			if (key.getCode() == KeyCode.LEFT) {
				left = true;
			}
		}
		if (key.getEventType() == KeyEvent.KEY_RELEASED) {
			if (key.getCode() == KeyCode.UP) {
				up = false;
			}
			if (key.getCode() == KeyCode.DOWN) {
				down = false;
			}
			if (key.getCode() == KeyCode.RIGHT) {
				right = false;
			}
			if (key.getCode() == KeyCode.LEFT) {
				left = false;
			}
		}

	}

}
