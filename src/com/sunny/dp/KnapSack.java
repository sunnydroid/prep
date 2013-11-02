package com.sunny.dp;

import com.sunny.common.Logger;

/*
 * Item 	Weight 	Benefit
 * 1		2		65
 * 2		3		80		
 * 3		1		30
 * 
 * Given the items above and a knapsack of weight 5, which items should go into to the knapsack to maximize benefit
 */

public class KnapSack {

	public static void main(String[] args) {
		int maxWeight = 9;
		int[] itemWeight = { 2, 3, 1 };
		int[] benefit = { 3, 5, 1 };

		int[][] solution = findSolutionMatrix(itemWeight, benefit, maxWeight);
		int[] optimalSolution = findOptimalSolution(solution, itemWeight,
				maxWeight);
		Logger.log("For max capacity of " + maxWeight
				+ " solution matrix is as follows with max benefit = "
				+ solution[itemWeight.length][maxWeight]);
		Logger.log2DArray(solution);
		Logger.log("Optimal solution consists of the following weights: ");
		Logger.logArray(optimalSolution);
	}

	public static int[][] findSolutionMatrix(int[] itemWeight, int[] benefit,
			int maxWeight) {

		int[][] solution = new int[itemWeight.length + 1][maxWeight + 1];

		/* find sub optimal solutions for all smaller weights and build up */
		for (int j = 1; j <= itemWeight.length; j++) {
			for (int i = 0; i <= maxWeight; i++) {
				/*
				 * if item's weight is greater than current weight, use previous
				 * sub optimal solution AT that weight
				 */
				if (itemWeight[j - 1] > i) {
					/* itemWeight needs to be offset by 1  because j starts from 1 and not 0 */
					solution[j][i] = solution[j - 1][i];
				} else {
					/*
					 * find current item's benefit + sub optimal solution at
					 * [weight - current item's weight], if benefit is greater
					 * than previous sub optimal solution us it.
					 */
					solution[j][i] = Math
							.max(solution[j - 1][i], benefit[j - 1]
									+ solution[j][i - itemWeight[j - 1]]);
				}

			}
		}

		return solution;
	}

	public static int[] findOptimalSolution(int[][] solution, int[] weights,
			int cap) {
		int[] optimalSolution = new int[10];
		int optimalSolutionIndex = 0;

		int maxWeight = cap;
		/* iterate through weights max weight -> 0 */
		for (int j = maxWeight; j > 0; ) {
			/*
			 * for the current weight column, if current weight is part of the
			 * optimal solution, the value in the row above, same column will be
			 * different. If so, subtract current weight from weight column and
			 * move to resulting weight column and repeat.
			 */
			for (int i = solution.length - 1; i > 0; i--) {
				if (solution[i][j] != solution[i - 1][j]) {
					/* current weight was part of the optimal solution.
					 * max weight reduces by current, find next sub optimal
					 * solution at new max weight
					 */
					optimalSolution[optimalSolutionIndex] = weights[i-1];
					j -= optimalSolution[optimalSolutionIndex];
					optimalSolutionIndex++;
					break;
				} 
			}
		}

		return optimalSolution;
	}

}
