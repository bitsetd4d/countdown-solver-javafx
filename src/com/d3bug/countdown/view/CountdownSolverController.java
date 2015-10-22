package com.d3bug.countdown.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.d3bug.countdown.SolveThePuzzle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Controller for main window
 */
public class CountdownSolverController implements Initializable {
	
	@FXML private Button solveButton;
	@FXML private TextField n1;
	@FXML private TextField n2;
	@FXML private TextField n3;
	@FXML private TextField n4;
	@FXML private TextField n5;
	@FXML private TextField n6;
	@FXML private TextField target;
	@FXML private ListView<String> output;
	
	private SolveThePuzzle solver = new SolveThePuzzle();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		solveButton.setOnAction( ev -> onButtonPressed());
	}

	private void onButtonPressed() {
		int nn1 = Integer.parseInt(n1.getText());
		int nn2 = Integer.parseInt(n2.getText());
		int nn3 = Integer.parseInt(n3.getText());
		int nn4 = Integer.parseInt(n4.getText());
		int nn5 = Integer.parseInt(n5.getText());
		int nn6 = Integer.parseInt(n6.getText());
		
		int nnTarget = Integer.parseInt(target.getText());
		
		List<String> answer = solver.calculate(nn1,nn2,nn3,nn4,nn5,nn6,nnTarget);
		output.getItems().clear();
		output.getItems().addAll(answer);
		output.scrollTo(answer.size());
		
	}

}
