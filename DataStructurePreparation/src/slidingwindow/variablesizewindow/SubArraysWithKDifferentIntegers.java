package slidingwindow.variablesizewindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.educative.io/courses/grokking-coding-interview/solution-subarrays-with-k-different-integers
 *
 * Agood subarrayvis a contiguous subarray that contains exactly k distinct integers. For example, in the array
 * [1,2,3,1,2] the subarray[1,2,3] [1,2,3]
 * contains3 distinct integers: 1,2 and 3
 *
 *
 */
public class SubArraysWithKDifferentIntegers {

    public static void main(String[] args) {
        int[][] testCases = {
                {3, 3, 3},
                {1},
                {1, 2, 3, 4, 5},
                {1, 2, 1, 2, 3},
                {1, 2, 1, 3, 4}
        };

        int[] ks = {2, 1, 3, 2, 3};


        for (int i = 0; i < testCases.length; i++) {
            int result = maxSubArrays(testCases[i], ks[i]);
            System.out.println((i + 1) + ".\tnums: " + Arrays.toString(testCases[i]) + ", k: " + ks[i]);
            System.out.println("\tresult: " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static int maxSubArrays(int[] numbers, int k) {

        if (numbers == null || numbers.length == 0 || k <= 0) return -1;

        int start = 0, end = 0;

        int maxSubArrays = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();

        while (end < numbers.length) {
            int currNum = numbers[end];
            freqMap.put(currNum, freqMap.getOrDefault(currNum, 0) + 1);

            while (freqMap.size() >= k) {
                maxSubArrays++;
                freqMap.put(numbers[start], freqMap.get(numbers[start]) - 1);
                if (freqMap.get(numbers[start]) == 0) {
                    freqMap.remove(numbers[start]);
                }
                start++;
            }
            end++;

        }
        return maxSubArrays;
    }
}
