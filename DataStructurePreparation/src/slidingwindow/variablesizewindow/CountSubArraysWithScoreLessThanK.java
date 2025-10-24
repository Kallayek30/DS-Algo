package slidingwindow.variablesizewindow;

/**
 * An array score is defined as the sum of the array elements multiplied by its length. For example, if the array is
 * [2,1,5] then its score is
 * (2+1+5)×3=24
 * Given an array of positive integers, nums, and a positive integer k, count and return the number of non-empty subarrays of nums whose score is strictly less than k.
 */
public class CountSubArraysWithScoreLessThanK {

    public static void main(String[] args) {

        int[][] testArrays = {
                {2, 1, 4, 3, 5},
                {10, 1, 2},
                {12, 2, 2, 3},
                {5, 4, 2, 10},
                {7, 2, 9, 4, 6},
                {20, 30, 40},
                {11, 1, 3},
                {15, 22, 18, 30, 14, 28, 33, 19, 26, 12},
                {45, 31, 27, 38, 40, 29, 22, 47, 36, 25, 44, 33, 21, 30, 26},
                {100, 200, 300, 400, 500, 99, 98, 97}
        };

        long[] ks = {
                10, 15, 18, 25, 40, 10, 10, 600, 1000, 50
        };

        for (int i = 0; i < testArrays.length; i++) {
            int[] nums = testArrays[i];
            long k = ks[i];

            System.out.println((i + 1) + ".\tnums: " + java.util.Arrays.toString(nums));
            System.out.println("\tk: " + k);
            System.out.println("\n\tCount of subarrays = " + getCountOfSubArrays(nums, k));
            System.out.println("-".repeat(100));
        }

    }

    public static int getCountOfSubArrays(int[] numbers, long k) {

        if (numbers == null || numbers.length == 0 || k <= 0) return -1;

        int start = 0, end = 0;
        int sum = 0;
        int maxNoOfSubArrays = 0, score = 0;
        while (end < numbers.length) {
            sum += numbers[end];
            score = sum * (end -start + 1);
            while ((long) score >= k) {
                sum -= numbers[start];
                start++;
                score = sum *(end - start + 1);
            }
            maxNoOfSubArrays+= end - start + 1;
            end++;
        }
        return maxNoOfSubArrays;
    }

}
