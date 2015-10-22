package com.d3bug.countdown.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CountdownSolverView extends Application {
	
	public static void main(String[] args) {
		Application.launch(CountdownSolverView.class, (java.lang.String[])null);
    }

	@Override
    public void start(Stage primaryStage) {
        try {
            AnchorPane page = (AnchorPane) FXMLLoader.load(CountdownSolverView.class.getResource("CountdownSolverView.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Countdown Solver");
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(CountdownSolverView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

