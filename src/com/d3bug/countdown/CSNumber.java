package com.d3bug.countdown;

public class CSNumber implements AnswerProvider {
	
	private int number;
	
	public CSNumber(int number) {
		this.number = number;
	}

	@Override
	public int getAnswer() {
		return number;
	}

	@Override
	public String getSolution() {
		return String.valueOf(number);
	}

}
