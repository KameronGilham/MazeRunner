package org;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Maze {

	int rectX;
	int rectY;
	int rectW;
	int rectH;
	Color rectColor;
	
	public Maze(int x, int y, int w, int h) {
		rectX = x;
		rectY = y;
		rectW = w;
		rectH = h;
		rectColor = Color.BLACK;
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(rectColor);
		gc.save();
		gc.fillRect(rectX, rectY, rectW, rectH);		
		gc.restore();
		
	}
	
}
