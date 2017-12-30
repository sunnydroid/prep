package com.sunny.dp;

import com.sunny.common.Logger;

/**
 * Item 	Weight 	Benefit
 * 1		2		65
 * 2		3		80		
 * 3		1		30
 * 
 * Given the items above and a knapsack with weight capacity of 5, find combination of items that would maximize
 * benefit for that capacity.
 */
public class KnapSack {

	/**
	 * Approach: This is a classic DP problem with both DP properties:
     *  1) Overlapping sub problems
     *      - higher level solutions are built on top of previously calculated lower level solutions
     *      - to calculate max benefit at weight 3 we need to calculate max benefit at weight 2, for which we need
     *          to calculate max benefit at weight 1
     *  2) Optimal sub structure
     *      - optimal solution can be obtained by using previously calculated optimal solutions.
     *      - Optimal solution at each weight is decided up by comparing optimal benefit at lower weights and current
     *          input
	 *
	 * 	To find the current suboptimal benefit at input n, choose max value between (sub optimal value without the
	 * 	current weight + current weight and previous optimal value, i.e at input n-1).
	 * 		Max of (sub optimal solution at (current weight - items weight) + items weight, previous optimal sub solution)
	 * 		Example:
     * 		    solution for weight = 3, sub optimal solution = 95
	 * 		    solution for weight = 4, sub optimal solution = 130
	 * 		    current weight = 5
	 * 		        current item weight = 2, benefit = 65
	 * 		        find sub optimal benefit at (5-2) = 3
	 * 			        130 + 65 = 195
	 * 			        compare it to the optimal solution with other weights
	 * 			        choose the highest weight.
	 *
	 *
	 *  items = [1, 2, 3] , weights = [30, 65, 80]
	 *
	 * 		weights | 0	| 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
	 * 	items
	 * 			1	| 0	| 1 | 0 | 1 | 0 | 1 | 0 | 1 | 0 | 1
	 * 			2	| 0	| 0 | 1 | 1 | 2 | 2 | 3 | 3 | 4 | 4
	 * 			3	| 0	| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0
	 *
	 * 	Max Benefit	| 0 |30 |65 |95 |130|160|195|225|260|290
	 *
	 * Algorithm:
	 * 	for capacity c = 0 to max capacity:
	 * 		for each item in weights array:
	 * 			find previous sub optimal solution at capacity - current items' weight
	 * 			if previous sub optimal solution + item's benefit is > previous solution at capacity-1:
	 * 				this becomes current sub optimal solution
	 * 				copy over column from previous sub optimal solution and increment the quantity of current weight
	 * 				by 1
	 *
	 * 	return weights array at capacity
     *
	 * @param weights
	 * @param benefit
	 * @param maxCapacity
	 * @return
	 */
	public static int[] solveKnapSack(int[] weights, int[] benefit, int maxCapacity) {


		/** keep track of weight allocation that give the optimal value
		 * also allocate column for weight = 0
		 */
		int[][] subOptimalWeights = new int[weights.length + 1][maxCapacity + 1];
		/** keep track of the sub optimal values */
		int[] subOptimalValues = new int[maxCapacity + 1];

		/** optimal solution for capacity = 0 will be 0 for all weights */
		for(int capacity = 1; capacity <= maxCapacity; capacity++) {
			for(int j = 0; j < weights.length; j++) {
				int currentWeight = weights[j];
				int previousSubOptimalValue = subOptimalValues[capacity];
				int previousSubSolutionIndex = capacity - currentWeight;
				/** if previous sub solution index is less than 0, we cannot use the current weight */
                if (previousSubSolutionIndex < 0) {
                    continue;
                }

                int subSolutionLessCurrentWeight = subOptimalValues[previousSubSolutionIndex];
				int currentSubOptimalSolution = subSolutionLessCurrentWeight + benefit[j];

				/** check if the sub solution less current weight + current weight is higher than previous sub solution
				 * and sub optimal solutions calculated with rest of the weights
				 */
				if ((currentSubOptimalSolution > previousSubOptimalValue) && (currentSubOptimalSolution > subOptimalValues[capacity])) {
					/** copy over subOptimalWeights from column (capacity - current weight) and increment quantity of
					 * current weight
					 */
					for(int k = 0; k < weights.length; k++) {
                        subOptimalWeights[k][capacity] = subOptimalWeights[k][capacity - currentWeight];
                    }
                    /** increment quantity of current weight */
					subOptimalWeights[j][capacity]++;
					/** store sub optimal value to be used later */
					subOptimalValues[capacity] = currentSubOptimalSolution;
				}
			}
		}

		/** return values in last column */
		int[] optimalWeights = new int[weights.length];
		for(int l = 0; l < weights.length; l++) {
            optimalWeights[l] = subOptimalWeights[l][maxCapacity];
        }
        return optimalWeights;
	}

	public static void main(String[] args) {
		int maxWeight = 9;
		int[] itemWeight = { 1, 2, 3 };
		int[] benefit = { 30, 65, 80 };

		int[] quantity = solveKnapSack(itemWeight, benefit, maxWeight);

		int maxBenefit = 0;
		for(int i = 0; i < quantity.length; i++) {
			Logger.log(quantity[i] + " x " + itemWeight[i]);
			maxBenefit += quantity[i] * benefit[i];
		}

		Logger.log("Total max benefit at capacity = " + maxWeight + " is " + maxBenefit);

//		int[][] solution = findSolutionMatrix(itemWeight, benefit, maxWeight);
//		int[] optimalSolution = findOptimalSolution(solution, itemWeight,
//				maxWeight);
//		Logger.log("For max capacity of " + maxWeight
//				+ " solution matrix is as follows with max benefit = "
//				+ solution[itemWeight.length][maxWeight]);
//		Logger.log2DArray(solution);
//		Logger.log("Optimal solution consists of the following weights: ");
//		Logger.logArray(optimalSolution);
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
