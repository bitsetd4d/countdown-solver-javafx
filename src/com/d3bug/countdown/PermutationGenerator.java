package com.d3bug.countdown;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Generate permutations of how numbers can be combined with
 * operations to produce solutions
 */
public class PermutationGenerator {

	private List<String> numberPermutations = new ArrayList<>();
	private Set<String> choicePermutations = new HashSet<>();
	private Set<String> operationPermutations = new HashSet<>();
	
	private Set<String> numberPickPermutations = new HashSet<>();
	private Set<String> finalPermutations = new HashSet<>();
	
	public static void main(String[] args) {
		PermutationGenerator g = new PermutationGenerator();
		System.out.println("Numbers : "+g.numberPermutations.size());
		System.out.println("Choices : "+g.choicePermutations.size());
		System.out.println("Ops     : "+g.operationPermutations.size());
		
		System.out.println("Pick   : "+g.numberPickPermutations.size());
		System.out.println("Final   : "+g.finalPermutations.size());
	}

	public PermutationGenerator() {
		permutation("012345",numberPermutations);
		permutation("100000",choicePermutations);
		permutation("110000",choicePermutations);
		permutation("111000",choicePermutations);
		permutation("111100",choicePermutations);
		permutation("111110",choicePermutations);
		permutation("111111",choicePermutations);
		generateOperations();
		collectAllNumberChoices();
		applyOperations();
	}
	
	private void generateOperations() {
		int i=0;
		while (true) {
			String x = Integer.toString(i, 5);
			if (x.length() == 6) break;
			if (!x.contains("0")) {
				operationPermutations.add(x);
			}
			i++;
		}
	}

	public Set<String> getFinalPermutations() {
		return finalPermutations;
	}
	
	private void applyOperations() {
		for (int i=0; i<6; i++) {
			finalPermutations.add(String.valueOf(i));
		}
		for (String pick : numberPickPermutations) {
			List<String> ops = opsFor(pick.length());
			for (String opCombo : ops) {
				StringBuilder sb = new StringBuilder();
				for (int i=0; i<opCombo.length(); i++) {
					char n1 = pick.charAt(i);
					char o1 = opCombo.charAt(i);
					sb.append(n1);
					sb.append(o1);
				}
				sb.append(pick.charAt(pick.length()-1));
				finalPermutations.add(sb.toString());
			}
		}
	}
	
	private List<String> opsFor(int length) {
		List<String> codes = operationPermutations.stream().filter(p -> p.length() == length-1).collect(Collectors.toList());
		List<String> ops = new ArrayList<>(codes.size());
		for (String x : codes) {
			String translated = x.replace('1', '+');
			translated = translated.replace('2', '-');
			translated = translated.replace('3', '*');
			translated = translated.replace('4', '/');
			ops.add(translated);
		}
		return ops;
	}

	private void collectAllNumberChoices() {
		StringBuilder sb = new StringBuilder();
		for (String numbers : numberPermutations) {
			for (String choice : choicePermutations) {
				sb.setLength(0);
				for (int i=0; i<6; i++) {
					char c = choice.charAt(i);
					if (c == '1') {
						sb.append(numbers.charAt(i));
					}
				}
				numberPickPermutations.add(sb.toString());
			}
		}
	}

	private void permutation(String str,Collection<String> collect) {
		permutation("", str, collect);
	}

	private void permutation(String prefix, String str, Collection<String> collect) {
		int n = str.length();
		if (n == 0)
			collect.add(prefix);
		else {
			for (int i = 0; i < n; i++) {
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), collect);
			}
		}
	}

}
