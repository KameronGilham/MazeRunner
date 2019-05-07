package org;

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
	static boolean gameRunning;
	Runner runner;
	String message;

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
		startButton.setOnAction( e -> {message = "";});
		startButton.setOnAction(e -> {gameRunning = true;});
		Button quitButton = new Button("Quit");
		quitButton.setOnAction( e -> Platform.exit() );

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
		gc.fillText(message, canvasW/5, canvasH/2);

		new AnimationTimer() {

			@Override
			public void handle(long now) {

				if (gameRunning) {

					gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
					runner.update(keyboarding, gc);
					runner.draw(gc);
					gc.setFont(Font.font(canvasH / 10));
					gc.setStroke(Color.BLUE);
					gc.strokeText("Start", canvasW / 10, canvasH / 10);
					gc.setStroke(Color.GREEN);
					gc.strokeText("End", canvasW * 8 / 10, canvasH * 9 / 10);
					
					

				}
			}

		}.start();

	}

	@Override
	public void init() {

		runner = new Runner(canvasW / 20, canvasH / 20, canvasW / 20, canvasH / 20, Color.TAN);

	}

}
