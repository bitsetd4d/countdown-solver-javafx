package com.d3bug.countdown;

/**
 * Given a 'recipe' and chosen numbers compute solution. If
 * the recipe leads to an invalid solution abort the
 * operation.
 */
public class AnswerMaker implements AnswerProvider {
	
	private String recipe;
	private CSNumber[] numbers;
	
	private int answer;
	private boolean abort;
	
	private char currentOp;
	private StringBuilder solution = new StringBuilder();
	
	public AnswerMaker(String recipe, CSNumber[] numbers) {
		this.recipe = recipe;
		this.numbers = numbers;
	}

	@Override
	public int getAnswer() {
		currentOp = 0;
		for (int i=0; i<recipe.length(); i++) {
			char c = recipe.charAt(i);
			if (Character.isDigit(c)) {
				applyNumber(c);
			} else {
				applyOp(c);
			}
			if (abort) return -1;
		}
		return answer;
	}

	private void applyOp(char c) {
		currentOp = c;
		if (answer == 1 && currentOp == '*') {
			abort();
			return;
		}
	}

	private void applyNumber(char c) {
		int idx = c - '0';
		if (currentOp == 0) {
			answer = numbers[idx].getAnswer();
			solution.append(answer);
			return;
		}
		int operand = numbers[idx].getAnswer();
		if (operand == 1 && currentOp == '/') {
			abort();
			return;
		}
		if (operand == 1 && currentOp == '*') {
			abort();
			return;
		}

		double newAnswer = applyOp(operand);
		if (Math.abs(newAnswer - ((int)newAnswer)) > 0.0000001) {
			abort();
			return;
		}
		if (newAnswer < 0) {
			abort();
			return;
		}
		answer = (int)newAnswer;
	}

	private void abort() {
		abort = true;
	}

	private double applyOp(int operand) {
		solution.append(currentOp);
		solution.append(operand);
		switch (currentOp) {
		case '+' : return answer + operand;
		case '-' : return answer - operand;
		case '*' : return answer * operand;
		case '/' : return (double)answer / operand;
		}
		throw new RuntimeException("Don't know "+currentOp);
	}

	@Override
	public String getSolution() {
		return solution.toString();
	}

	

}
