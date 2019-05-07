package org;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Maze {

	int rectX;
	int rectY;
	int rectW;
	int rectH;
	Color rectColor;
	
	public Maze(int x, int y, int w, int h, Color c) {
		rectX = x;
		rectY = y;
		rectW = w;
		rectH = h;
		rectColor = c;
	}
	
	public void draw(GraphicsContext gc) {
		gc.setStroke(rectColor);
		gc.setLineWidth(1.0);
		
		gc.save();
		
		gc.strokeRect(rectX, rectY, rectW, rectH);
			
		gc.restore();
		
	}
	
}
