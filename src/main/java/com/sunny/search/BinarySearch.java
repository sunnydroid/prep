package com.sunny.search;

import com.sunny.common.Logger;

/**
 * Created by sunshah on 7/22/16.
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] sortedArray = {1, 3, 5, 6, 7, 9, 12};
        int target = 4;
        int targetIndex = binarySearchIterative(sortedArray, target);
        Logger.log("index of target " + target + " : " + targetIndex);
    }

    /**
     * Binary search algorithm that searches for target element by reducing
     * number of elements that need to be searched by half at each iteration
     * @param array
     * @param target
     * @return
     */
    public static int binarySearchIterative(int[] array, int target) {
        int startIndex, middleIndex, endIndex, targetIndex = -1, iterations;
        /* if array is empty or target value is larger/smaller than biggest, smallest element in array, return */
        if(array.length < 1 || array[0] > target || array[array.length - 1] < target) {
            System.out.println("array length = " + array.length);
            System.out.println("first element  = " + array[0]);
            System.out.println("last element = " + array[array.length -1]);
            return targetIndex;
        }

        startIndex = iterations = 0;
        endIndex = array.length - 1;

        while(endIndex > startIndex) {
            /* get the middle element */
            middleIndex = (endIndex + startIndex) / 2;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Start index= ").append(startIndex).append(", end index=").append(endIndex).append("; iteration=").append(iterations);
            Logger.log(stringBuilder.toString());

            /* check if center element is target we are looking for */
            if(array[middleIndex] == target) {
                targetIndex = middleIndex;
                break;
            }
            /* search smaller half if middle element is bigger than target, else search bigger half */
            if(array[middleIndex] > target) {
                /* start index remains the same */
                endIndex = middleIndex;
            } else {
                /* end index remains the same */
                startIndex = middleIndex+1;
            }
            iterations++;

        }
        return targetIndex;
    }
}
