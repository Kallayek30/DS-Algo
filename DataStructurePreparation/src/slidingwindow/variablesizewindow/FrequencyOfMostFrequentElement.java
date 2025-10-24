package slidingwindow.variablesizewindow;

import java.util.Arrays;

/**
 * You are given an integer array, nums, and an integer k, representing the maximum number of operations you can perform. In each operation, you may select any index in nums and increment its value by 1.
 * <p>
 * Your task is to determine the maximum possible frequency of a single element in the final array after performing at most k operations. You can choose to increase any elements in a way that results in one particular element appearing as often as possible (within k operations). For example, if nums = [2, 2, 3] and k = 4, you can increment the first and the second element, 2, once to match the third element, 3, achieving a maximum frequency of 3.
 * <p>
 * Return the highest frequency that can be achieved for any element in nums after at most k operations.
 */
public class FrequencyOfMostFrequentElement {

    public static void main(String[] args) {
        int[][] testCases = {
                {1, 2, 4},
                {1, 4, 8, 13},
                {3, 6, 9},
                {2, 3, 5},
                {1, 1, 2},
                {4, 6, 8, 10},
                {10, 12, 5, 1, 15, 20, 13, 4, 7, 3, 9, 14, 2, 8, 6, 16, 11, 18, 19, 17},
                {5, 5, 5, 5, 6, 7, 8, 9, 10, 10, 10, 10, 10, 11, 12, 13, 14, 15, 16, 17}
        };
        int[] kValues = {5, 5, 2, 3, 2, 7, 50, 30};

        for (int i = 0; i < testCases.length; i++) {
            System.out.println((i + 1) + ".\tnums = " + Arrays.toString(testCases[i]));
            System.out.println("\tk = " + kValues[i]);
            System.out.println("\n\tOutput = " + maxFrequency(testCases[i], kValues[i]));
            System.out.println("-" + new String(new char[100]).replace('\0', '-') + "\n");
        }
    }

    public static int maxFrequency(int[] numbers, int k) {

        if (numbers == null || numbers.length == 0 || k <= 0) return -1;

        int start = 0, end = 0;
        int maxFreq = Integer.MIN_VALUE;
        int target = 0, windowSum = 0;


        while (end < numbers.length) {
            target = numbers[end];
            windowSum += target;

            while ((end - start + 1) * target > windowSum + k) {
                windowSum -= numbers[start];
                start++;
            }
            maxFreq = Math.max(maxFreq, end - start + 1);
            end++;
        }

        return maxFreq;
    }
}
