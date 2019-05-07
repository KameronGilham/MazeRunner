package org;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Runner {
	Color runnerColor;
	boolean gameRunning;
	static int runnerX;
	static int runnerY;
	static int runnerW;
	static int runnerH;
	int runnerFrames;

	public Runner(int x, int y, int w, int h, Color color) {
		gameRunning = true;
		runnerX = x;
		runnerY = y;
		runnerW = w;
		runnerH = h;
		runnerColor = color;
		runnerFrames = 0;
	}

	public void draw(GraphicsContext gc) {

		gc.setFill(runnerColor);
		gc.save();
		gc.fillRect(runnerX - runnerW / 2, runnerY - runnerH / 2, runnerW, runnerH);
		gc.restore();
	}

	public void update(KeyboardInputs keyboarding, GraphicsContext gc) {
		
		if (gameRunning) {

			if (keyboarding.up) {
				if (runnerY > runnerH / 2) {
					runnerY--;
				} else {
					keyboarding.up = false;
				}
			}
			if (keyboarding.down) {
				if (runnerY < gc.getCanvas().getHeight() - runnerH / 2) {
					runnerY++;
				} else {
					keyboarding.down = false;
				}
			}
			if (keyboarding.left) {
				if (runnerX > runnerW / 2) {
					runnerX--;
				} else {
					keyboarding.left = false;
				}
			}
			if (keyboarding.right) {
				if (runnerX < gc.getCanvas().getWidth() - runnerW / 2) {
					runnerX++;
				} else {
					keyboarding.right = false;
				}
			}

		}

	}

}
