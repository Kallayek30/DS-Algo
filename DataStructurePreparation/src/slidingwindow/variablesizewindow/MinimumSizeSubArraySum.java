package slidingwindow.variablesizewindow;

import java.util.Arrays;

public class MinimumSizeSubArraySum {

    public static void main(String[] args) {
        int[] target = {7, 4, 11, 10, 5, 15};
        int[][] inputArr = {
                {2, 3, 1, 2, 4, 3},
                {1, 4, 4},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 2, 3, 4},
                {1, 2, 1, 3},
                {5, 4, 9, 8, 11, 3, 7, 12, 15, 44}
        };
        for (int i = 0; i < target.length; i++) {
            int windowSize = minimumWindow(inputArr[i], target[i]);
            System.out.print((i + 1) + ".\tInput array: " + Arrays.toString(inputArr[i]));
            System.out.print("\n\tTarget: " + target[i]);
            System.out.println("\n\tMinimum Length of Subarray: " + windowSize);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static int minimumWindow(int [] numbers, int target){

        if(numbers == null ) return -1;
        int start = 0, end = 0;
        int sum = 0;
        int minWindow = Integer.MAX_VALUE;

        while(end < numbers.length){
            sum+=numbers[end];
            while(sum >= target){
                minWindow = Integer.min( end + 1 - start, minWindow);
                sum-=numbers[start];
                start++;
            }
            end++;
        }
        if(minWindow != Integer.MAX_VALUE) return minWindow;

        return 0;
    }
}
