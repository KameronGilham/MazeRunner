package org;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MazeRunnerGame extends Application {

	int canvasW = 800;
	int canvasH = 800;
	boolean gameRunning;
	Runner runner;
	Maze wall;
	String message;
	String buttonText;
	List<Maze> walls = new ArrayList<Maze>();

	public MazeRunnerGame() {
		gameRunning = false;
		message = "Press Start to Begin";
	}

	@Override
	public void start(Stage stage) throws Exception {

		Canvas canvas = new Canvas(canvasW, canvasH);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		KeyboardInputs keyboarding = new KeyboardInputs();

		Button startButton = new Button("Start");
		startButton.setOnAction(e -> {
			message = "";
		});
		startButton.setOnAction(e -> {
			gameRunning = true;
		});

		Button quitButton = new Button("Quit");
		quitButton.setOnAction(e -> Platform.exit());

		HBox buttonBar = new HBox(canvasW / 3, startButton, quitButton);
		buttonBar.setAlignment(Pos.BOTTOM_CENTER);

		BorderPane game = new BorderPane();
		game.setBottom(buttonBar);

		Scene scene = new Scene(game, canvasW, canvasH + 80, Color.WHITE);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> keyboarding.keyboardInput(key, gc));
		scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> keyboarding.keyboardInput(key, gc));
		game.setCenter(canvas);
		stage.setScene(scene);
		stage.setTitle("MazeRunner");
		stage.show();

		gc.setFont(Font.font(50));
		gc.setLineWidth(5.0);
		gc.setFill(Color.BLACK);
		gc.fillText(message, canvasW / 5, canvasH / 2);

		new AnimationTimer() {

			@Override
			public void handle(long now) {

				if (gameRunning) {
					gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
					
					gc.setFont(Font.font(80));
					gc.setStroke(Color.BLUE);
					gc.strokeText("Start", 50, 80);
					gc.setStroke(Color.GREEN);
					gc.strokeText("End", canvasW - 160, canvasH - 10);

					runner.update(keyboarding, gc);
					runner.draw(gc);
					if(runner.runnerY > 700) {
						gc.setStroke(Color.HOTPINK);
						gc.strokeText("You Win!!!", canvasW/4, canvasH/2);
						
					}

					Iterator<Maze> wallIter = walls.iterator();
					while (wallIter.hasNext()) {
						Maze wallQ = wallIter.next();
						wallQ.draw(gc);
					}

					

				}
			}

		}.start();

	}

	@Override
	public void init() {

		runner = new Runner(25, 25, 25, 25, Color.TAN);
		wall = new Maze(100, 100, 700, 10);
		walls.add(wall);
		wall = new Maze(0, 600, 700, 10);
		walls.add(wall);
		for (int i = 200; i <= 800; i += 200) {
			wall = new Maze(i, i - 100, 10, 200);
			walls.add(wall);
		}
		for (int i = 800; i >= 200; i -= 200) {
			wall = new Maze(i, 100, 10, 100);
			walls.add(wall);
		}
		for (int i = 600; i >= 100; i -= 100) {
			wall = new Maze(i, i + 100, 150, 10);
			walls.add(wall);
		}
		for (int i = 100; i < 600; i += 100) {
			wall = new Maze(i, 600 - i, 50, 50);
			walls.add(wall);

		}
		for (int i = 800; i >= 200; i -= 200) {
			wall = new Maze(i, 500, 10, 100);
			walls.add(wall);
		}

	}
	
	public void collision() {
		
	}
}
