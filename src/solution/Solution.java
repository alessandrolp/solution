package solution;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		System.out.println("Número informado: " + args[0]);
		int[] numbersSorted = sortNumbers(args[0]);
		printNumbers(numbersSorted, "Números ordenados");
		int qtdNumbers = countNumbers(numbersSorted);
		System.out.println("Quantidade total de números: " + qtdNumbers);
	}

	private static int[] sortNumbers(String numberReceived) {
		String[] numberReceivedArray = numberReceived.split("");
		int[] numbersSorted = new int[numberReceivedArray.length];

		for (int i = 0; i < numberReceivedArray.length; i++) {
			int numberRead = Integer.parseInt(numberReceivedArray[i]);
			if (i == 0) {
				numbersSorted[i] = numberRead;
			} else {
				int count = i - 1;
				boolean notIncluded = true;
				while (count >= 0) {
					int numberSortedCurrent = numbersSorted[count];
					if (numberSortedCurrent > numberRead) {
						numbersSorted[count] = numberRead;
						numbersSorted[count + 1] = numberSortedCurrent;
					} else if (notIncluded) {
						numbersSorted[i] = numberRead;
					}
					notIncluded = false;
					count--;
				}
			}
		}
		return numbersSorted;
	}

	private static int countNumbers(int[] numbersSorted) {
		List<Integer> numbers = new ArrayList<>();
		int firstNumber = numbersSorted[0];
		numbers.add(firstNumber);
		boolean hasZero = firstNumber == 0;

		for (int i = 1; i < numbersSorted.length; i++) {
			int numberRead = numbersSorted[i];
			if (numberRead == 0 && hasZero) {
				continue;
			}
			if (numberRead != numbersSorted[i - 1]) {
				numbers.add(numberRead);
			}
			int aux = i - 1;
			int lastNumberCreated = numberRead;
			while (aux >= 0) {
				Integer numberSortedCurrent = numbersSorted[aux];
				if (numberSortedCurrent > 0) {
					lastNumberCreated = Integer.valueOf(numberSortedCurrent + "" + lastNumberCreated);
					numbers.add(lastNumberCreated);
				}
				aux--;
			}
		}
		printNumbers(numbers.stream().mapToInt(i -> i).toArray(), "Números possíveis");
		return numbers.size();
	}
	
	/**
	 * Implementação otimizada pois não ha necessidade de salvar em uma coleção,
	 * sendo apenas obtido a quantidade total de números possíveis. 
	 */
//	private static int countNumbers(int[] numbersSorted) {
//		int firstNumber = numbersSorted[0];
//		boolean hasZero = firstNumber == 0;
//		int countNumber = 1;
//
//		for (int i = 1; i < numbersSorted.length; i++) {
//			int numberRead = numbersSorted[i];
//			if (numberRead == 0 && hasZero) {
//				continue;
//			}
//			if (numberRead != numbersSorted[i - 1]) {
//				countNumber++;
//			}
//			int aux = i - 1;
//			while (aux >= 0) {
//				Integer numberSortedCurrent = numbersSorted[aux];
//				if (numberSortedCurrent > 0) {
//					countNumber++;
//				}
//				aux--;
//			}
//		}
//		return countNumber;
//	}

	private static void printNumbers(int[] numbersSorted, String message) {
		StringBuilder sb = new StringBuilder();
		sb.append(message);
		sb.append(" [");
		for (int i = 0; i < numbersSorted.length; i++) {
			sb.append(numbersSorted[i]);
			sb.append(", ");
		}
		sb.append("]");
		System.out.println(sb.toString());
	}
}
