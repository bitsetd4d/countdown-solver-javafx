package com.d3bug.countdown;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolveThePuzzle {
	
	private PermutationGenerator perms = new PermutationGenerator();
	
	private CSNumber[] numbers = new CSNumber[6];
	{
		numbers[0] = new CSNumber(75); 
		numbers[1] = new CSNumber(50); 
		numbers[2] = new CSNumber(5); 
		numbers[3] = new CSNumber(1); 
		numbers[4] = new CSNumber(7); 
		numbers[5] = new CSNumber(3);
	}
	
	private static int answer = 50;
	
	public List<AnswerProvider> generateAllAnswerProviders() {
		List<AnswerProvider> answers = new ArrayList<>();
		for (String p : perms.getFinalPermutations()) {
			AnswerMaker maker = new AnswerMaker(p,numbers);
			answers.add(maker);
		}
		return answers;
	}
	
	public static void main(String[] args) {
		SolveThePuzzle g = new SolveThePuzzle();
		List<AnswerProvider> list = g.generateAllAnswerProviders();
		List<String> solutions = new ArrayList<>();
		for (AnswerProvider ap : list) {
			int candidate = ap.getAnswer();
			if (candidate == answer) {
				String solution = ap.getSolution()+" = "+candidate;
				solutions.add(solution);
			} 
		}
			
		Collections.sort(solutions, (a,b) -> b.length() - a.length());
		for (String s : solutions) {
			System.out.println(s);
		}
		
	}

	public List<String> calculate(int nn1, int nn2, int nn3, int nn4, int nn5, int nn6,int nnTarget) {
		numbers[0] = new CSNumber(nn1); 
		numbers[1] = new CSNumber(nn2); 
		numbers[2] = new CSNumber(nn3); 
		numbers[3] = new CSNumber(nn4); 
		numbers[4] = new CSNumber(nn5); 
		numbers[5] = new CSNumber(nn6);
		System.out.println("Calculating");
		List<AnswerProvider> list = generateAllAnswerProviders();
		List<String> solutions = new ArrayList<>();
		for (AnswerProvider ap : list) {
			int candidate = ap.getAnswer();
			if (candidate == nnTarget) {
				String solution = ap.getSolution()+" = "+candidate;
				solutions.add(solution);
			} else if (Math.abs(candidate - nnTarget) < 10) {
				String solution = ap.getSolution()+" = "+candidate+" ("+Math.abs(candidate - nnTarget)+" away...)";
				solutions.add(solution);
			}
		}
			
		Collections.sort(solutions, (a,b) -> b.length() - a.length());
		if (solutions.isEmpty()) {
			solutions.add("No solution :(");
		}
		return solutions;
	}
	
}
